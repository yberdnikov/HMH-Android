package com.hmh.hamyeonham.common.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

fun AppCompatActivity.showLoading() {
    supportFragmentManager.commit(allowStateLoss = true) {
        add(LoadingProgressIndicator.newInstance(), LoadingProgressIndicator.TAG)
    }
}

fun AppCompatActivity.hideLoading() {
    supportFragmentManager.findFragmentByTag(LoadingProgressIndicator.TAG)?.let { fragment ->
        supportFragmentManager.commit(allowStateLoss = true) {
            remove(fragment)
        }
    }
}

fun AppCompatActivity.showError() {
    supportFragmentManager.commit(allowStateLoss = true) {
        add(ErrorFullScreenDialogFragment.newInstance(), ErrorFullScreenDialogFragment.TAG)
    }
}

fun AppCompatActivity.hideError() {
    supportFragmentManager.findFragmentByTag(ErrorFullScreenDialogFragment.TAG)?.let { fragment ->
        supportFragmentManager.commit(allowStateLoss = true) {
            remove(fragment)
        }
    }
}
