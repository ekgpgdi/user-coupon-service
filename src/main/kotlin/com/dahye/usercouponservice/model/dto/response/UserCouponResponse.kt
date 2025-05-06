package com.dahye.usercouponservice.model.dto.response

import com.dahye.usercouponservice.model.enums.CouponType
import java.time.LocalDateTime

data class UserCouponResponse(
    val userId: String,
    val coupons: List<CouponResponse>,
)

data class CouponResponse(
    val couponId: String,
    val title: String,
    val content: String,
    val type: CouponType,
    val availableFrom: LocalDateTime,
    val availableTo: LocalDateTime,
    val isUsed: Boolean,
    val usedAt: LocalDateTime? = null,
)
