package com.hmh.hamyeonham.data.challenge.mapper

import com.hmh.hamyeonham.challenge.model.Status

internal fun List<String>.toStatusList(): List<Status> {
    return this.map {
        when (it) {
            Status.NONE.value -> Status.NONE
            Status.UNEARNED.value -> Status.UNEARNED
            Status.EARNED.value -> Status.EARNED
            Status.FAILURE.value -> Status.FAILURE
            else -> Status.NONE
        }
    }
}
