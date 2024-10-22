package com.yunho.core.dtos

import com.yunho.core.Dto
import com.yunho.network.model.Sample

data class SampleDto(
    val data: String
) : Dto<Sample> {
    override val transform: () -> Sample
        get() = {
            Sample(
                data = data
            )
        }

}
