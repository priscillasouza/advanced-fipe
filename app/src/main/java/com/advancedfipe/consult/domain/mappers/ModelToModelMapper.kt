package com.advancedfipe.consult.domain.mappers

import com.advancedfipe.consult.data.source.remote.entity.ModelResponse
import com.advancedfipe.consult.domain.model.Model

class ModelToModelMapper: IMapper<ModelResponse, Model> {
    override fun transform(entity: ModelResponse): Model {
       return Model(
           name = entity.name,
           code = entity.code
       )
    }
}