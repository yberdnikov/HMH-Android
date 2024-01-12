package com.hmh.hamyeonham.feature.main.update

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChallengeWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
) : Worker(context, params) {

    override fun doWork(): Result {
        GlobalScope.launch {

        }
        return Result.success()
    }
}
