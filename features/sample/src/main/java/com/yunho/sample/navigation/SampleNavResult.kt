package com.yunho.sample.navigation

sealed interface SampleNavResult {

    data object MoveBack : SampleNavResult
}
