package com.advancedfipe.consult.domain.mappers

import com.advancedfipe.consult.data.source.remote.entity.BrandResponse
import com.advancedfipe.consult.domain.model.Brand

class BrandToModelMapper: IMapper<BrandResponse, Brand> {
    override fun transform(entity: BrandResponse): Brand {
       return Brand(
           name = entity.name,
           code = entity.code
       )
    }
}