package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod11_question_delivery.QuestionDeliveryEntity
import com.interview.platform.data.remote.dto.mod11_question_delivery.QuestionItemDto
import com.interview.platform.domain.model.mod11_question_delivery.QuestionDelivery

fun QuestionItemDto.toEntity(): QuestionDeliveryEntity {
    return QuestionDeliveryEntity(id = this.id)
}

fun QuestionDeliveryEntity.toDomain(): QuestionDelivery {
    return QuestionDelivery(id = this.id)
}
