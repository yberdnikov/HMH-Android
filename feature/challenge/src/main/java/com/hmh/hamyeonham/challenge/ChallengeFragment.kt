package com.hmh.hamyeonham.challenge

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmh.hamyeonham.challenge.appadd.AppAddActivity
import com.hmh.hamyeonham.challenge.calendar.ChallengeCalendarAdapter
import com.hmh.hamyeonham.challenge.goals.ChallengeUsageGoalsAdapter
import com.hmh.hamyeonham.challenge.model.Apps
import com.hmh.hamyeonham.challenge.model.ChallengeStatus
import com.hmh.hamyeonham.challenge.model.NewChallenge
import com.hmh.hamyeonham.challenge.newchallenge.NewChallengeActivity
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.dialog.TwoButtonCommonDialog
import com.hmh.hamyeonham.common.fragment.snackBarWithAction
import com.hmh.hamyeonham.common.fragment.toast
import com.hmh.hamyeonham.common.fragment.viewLifeCycle
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.navigation.NavigationProvider
import com.hmh.hamyeonham.common.view.VerticalSpaceItemDecoration
import com.hmh.hamyeonham.common.view.dp
import com.hmh.hamyeonham.common.view.mapBooleanToVisibility
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.core.designsystem.R
import com.hmh.hamyeonham.core.viewmodel.MainState
import com.hmh.hamyeonham.core.viewmodel.MainViewModel
import com.hmh.hamyeonham.feature.challenge.databinding.FragmentChallengeBinding
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeFragment : Fragment() {
    private val binding by viewBinding(FragmentChallengeBinding::bind)
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val viewModel by viewModels<ChallengeViewModel>()
    private lateinit var appSelectionResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var newChallengeResultLauncher: ActivityResultLauncher<Intent>


    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentChallengeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppSelectionResultLauncher()
        initNewChallengeResultLauncher()
        initViews()
        collectMainStateAndProcess()
        collectChallengeStateAndProcess()
        initChallengeCalendar()
    }

    private fun initChallengeCalendar() {
        activityViewModel.mainState.flowWithLifecycle(viewLifeCycle).onEach {
            if (it.isChallengeExist) {
                bindChallengeCalendar(it.challengeStatusList.take(7))
                bindChallengeDate(it.todayIndex, it.startDate)
                if (it.period > 14) {
                    binding.tvCalendarToggle.visibility = View.VISIBLE
                    handleCalendarToggleState()
                } else {
                    binding.tvCalendarToggle.visibility = View.GONE
                }
            }
        }.launchIn(viewLifeCycleScope)
    }

    private fun handleCalendarToggleState() {
        // toggleState 초기값 세팅
        binding.tvCalendarToggle.text =
            getString(com.hmh.hamyeonham.feature.challenge.R.string.tv_calendar_toggle_expand)

        binding.tvCalendarToggle.setOnClickListener {
            when (viewModel.challengeState.value.calendarToggleState) {
                CalendarToggleState.COLLAPSED -> { // 접힌 상태
                    binding.tvCalendarToggle.text =
                        getString(com.hmh.hamyeonham.feature.challenge.R.string.tv_calendar_toggle_expand)
                    viewModel.toggleCalendarState()
                    updateCalendarView(CalendarToggleState.COLLAPSED)
                }

                CalendarToggleState.EXPANDED -> { // 펼쳐진 상태
                    binding.tvCalendarToggle.text =
                        getString(com.hmh.hamyeonham.feature.challenge.R.string.tv_calendar_toggle_collapse)
                    viewModel.toggleCalendarState()
                    updateCalendarView(CalendarToggleState.EXPANDED)
                }
            }
        }
    }

    private fun updateCalendarView(state: CalendarToggleState) {
        val challengeStatusList = activityViewModel.mainState.value.challengeStatusList
        val displayList = when (state) {
            CalendarToggleState.COLLAPSED -> challengeStatusList.take(7)
            CalendarToggleState.EXPANDED -> challengeStatusList
        }
        updateChallengeCalendar(displayList)
    }

    private fun updateChallengeCalendar(challengeStatusList: List<ChallengeStatus.Status>) {
        val challengeAdapter = binding.rvChallengeCalendar.adapter as? ChallengeCalendarAdapter
        challengeAdapter?.updateList(challengeStatusList)
    }

    private fun collectMainStateAndProcess() {
        activityViewModel.mainState.flowWithLifecycle(viewLifeCycle).onEach {
            bindChallengeInfo(it)
            updateUsageStatusAndGoals(it)
        }.launchIn(viewLifeCycleScope)
    }

    private fun bindChallengeInfo(it: MainState) {
        setChallengeCalendarVisibility(it.isChallengeExist)
        bindChallengeCalendar(it.challengeStatusList)
        bindChallengeDate(it.todayIndexAsDate, it.startDate)
    }

    private fun updateUsageStatusAndGoals(it: MainState) {
        if (it.usageStatusAndGoals.isNotEmpty()) viewModel.updateUsageStatusAndGoals(
            activityViewModel.getUsageStatusAndGoalsExceptTotal() + UsageStatusAndGoal()
        )
    }

    private fun collectChallengeStateAndProcess() {
        viewModel.challengeState.flowWithLifecycle(viewLifeCycle).onEach {
            handleModifierButtonState(it.modifierState)
            bindUsageGoals(it.usageGoalsAndModifiers)
        }.launchIn(viewLifeCycleScope)
    }

    private fun handleModifierButtonState(it: ModifierState) {
        val (text, color) = getTextAndColorsOfModifierState(it)
        binding.tvModifierButton.run {
            this.text = text
            setTextColor(color)
        }
    }

    private fun getTextAndColorsOfModifierState(modifierState: ModifierState) =
        when (modifierState) {
            ModifierState.EDIT -> {
                getString(R.string.all_done) to ContextCompat.getColor(
                    requireContext(),
                    R.color.white_text,
                )
            }

            ModifierState.DONE -> {
                getString(R.string.all_edit) to ContextCompat.getColor(
                    requireContext(),
                    R.color.blue_purple_text,
                )
            }
        }

    private fun initModifierButton() {
        binding.tvModifierButton.setOnClickListener {
            when (viewModel.challengeState.value.modifierState) {
                ModifierState.DONE -> {
                    viewModel.updateModifierState(ModifierState.EDIT)
                }

                ModifierState.EDIT -> {
                    viewModel.updateModifierState(ModifierState.DONE)
                }
            }
        }
    }

    private fun initPointButton() {
        val pointButtonImg =
            if (activityViewModel.isPointLeftToCollect()) com.hmh.hamyeonham.common.R.drawable.ic_chellenge_point_exist_24 else com.hmh.hamyeonham.common.R.drawable.ic_chellenge_point_not_exist_24
        binding.tvPointButton.setImageResource(pointButtonImg)

        binding.tvPointButton.setOnClickListener {
            navigateToPointView()
        }
    }

    private fun initChallengeCreateButton() {
        binding.btnChallengeCreate.setOnClickListener {
            if (activityViewModel.isPointLeftToCollect()) {
                snackBarWithAction(
                    anchorView = binding.root,
                    message = getString(com.hmh.hamyeonham.feature.challenge.R.string.challenge_cannot_create),
                    actionMessage = getString(
                        com.hmh.hamyeonham.feature.challenge.R.string.all_move
                    )
                ) {
                    navigateToPointView()
                }
            } else {
                val intent = Intent(requireContext(), NewChallengeActivity::class.java)
                newChallengeResultLauncher.launch(intent)
            }
        }
    }

    private fun navigateToPointView() {
        val intent = navigationProvider.toPoint()
        startActivity(intent)
    }

    private fun initViews() {
        initModifierButton()
        initPointButton()
        initChallengeCreateButton()
        initChallengeGoalsRecyclerView()
        initChallengeCalendarRecyclerView()
    }

    private fun setChallengeCalendarVisibility(isChallengeExist: Boolean) {
        val challengeCreateVisibility = (!isChallengeExist).mapBooleanToVisibility()
        val challengeInfoVisibility = (isChallengeExist).mapBooleanToVisibility()

        binding.btnChallengeCreate.visibility = challengeCreateVisibility
        binding.tvChallengeCreateTitle.visibility = challengeCreateVisibility
        binding.tvChallengeDay.visibility = challengeInfoVisibility
        binding.tvChallengeStartDate.visibility = challengeInfoVisibility
        binding.rvChallengeCalendar.visibility = challengeInfoVisibility
    }

    private fun bindUsageGoals(challengeUsageGoalList: List<ChallengeUsageGoal>) {
        val challengeGoalsAdapter = binding.rvAppUsageGoals.adapter as? ChallengeUsageGoalsAdapter
        challengeGoalsAdapter?.submitList(challengeUsageGoalList)
    }

    private fun bindChallengeCalendar(challengeStatusList: List<ChallengeStatus.Status>) {
        val challengeAdapter = binding.rvChallengeCalendar.adapter as? ChallengeCalendarAdapter
        challengeAdapter?.updateList(challengeStatusList)
    }

    private fun bindChallengeDate(todayIndex: Int, startDate: LocalDate) {
        binding.tvChallengeStartDate.text = getString(
            com.hmh.hamyeonham.feature.challenge.R.string.challenge_start_date,
            startDate.monthNumber,
            startDate.dayOfMonth
        )
        binding.tvChallengeDay.text =
            getString(com.hmh.hamyeonham.feature.challenge.R.string.challenge_day, todayIndex)
    }

    private fun initChallengeCalendarRecyclerView() {
        binding.rvChallengeCalendar.run {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = ChallengeCalendarAdapter(context)
        }
        initChallengeCalendar()
    }

    private fun initAppSelectionResultLauncher() {
        appSelectionResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    addSelectedApps(result)
                }
            }
    }

    private fun initNewChallengeResultLauncher() {
        newChallengeResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val period = result.data?.getIntExtra(NewChallengeActivity.PERIOD, 0)
                    val goalTime = result.data?.getLongExtra(NewChallengeActivity.GOALTIME, 0)
                    viewModel.generateNewChallenge(
                        NewChallenge(
                            period = period ?: 0,
                            goalTime = goalTime ?: 0
                        )
                    )
                }
            }
    }

    private fun addSelectedApps(result: ActivityResult) {
        val selectedApps =
            result.data?.getStringArrayExtra(AppAddActivity.SELECTED_APPS)?.toList() ?: return
        val goalTime = result.data?.getLongExtra(AppAddActivity.GOAL_TIME, 0) ?: return
        val apps = Apps.createFromAppCodeListAndGoalTime(selectedApps, goalTime)
        viewModel.addApp(apps)
    }

    private fun initChallengeGoalsRecyclerView() {
        binding.rvAppUsageGoals.run {
            adapter = ChallengeUsageGoalsAdapter(
                onAppListAddClicked = {
                    val intent = Intent(requireContext(), AppAddActivity::class.java)
                    appSelectionResultLauncher.launch(intent)
                },
                onAppItemClicked = { challengeGoal ->
                    when (viewModel.challengeState.value.modifierState) {
                        ModifierState.EDIT -> {
                            if (challengeGoal.isDeletable) setDeleteAppDialog(challengeGoal.usageStatusAndGoal)
                            else toast(getString(com.hmh.hamyeonham.feature.challenge.R.string.challenge_cannot_delete))
                        }

                        else -> Unit
                    }
                },
            )
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalSpaceItemDecoration(9.dp))
        }
    }

    private fun RecyclerView.setDeleteAppDialog(it: UsageStatusAndGoal) {
        val clickedAppNameToDialog = context.getAppNameFromPackageName(it.packageName)
        TwoButtonCommonDialog.newInstance(
            title = getString(R.string.delete_app_dialog_title, clickedAppNameToDialog),
            description = getString(R.string.delete_app_dialog_description),
            confirmButtonText = getString(R.string.all_okay),
            dismissButtonText = getString(R.string.all_cancel),
        ).apply {
            setConfirmButtonClickListener {
                viewModel.deleteApp(it.packageName)
            }
            setDismissButtonClickListener {}
        }.showAllowingStateLoss(childFragmentManager)
    }
}
