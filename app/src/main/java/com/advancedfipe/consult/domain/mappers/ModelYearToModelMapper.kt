package com.advancedfipe.consult.domain.mappers

import com.advancedfipe.consult.data.source.remote.entity.ModelYearResponse
import com.advancedfipe.consult.domain.model.ModelYear

class ModelYearToModelMapper: IMapper<ModelYearResponse, ModelYear> {
    override fun transform(entity: ModelYearResponse): ModelYear {
       return ModelYear(
           name = entity.name,
           code = entity.code
       )
    }
}