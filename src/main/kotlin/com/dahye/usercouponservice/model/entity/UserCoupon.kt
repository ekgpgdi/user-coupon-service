package com.dahye.usercouponservice.model.entity

import com.dahye.usercouponservice.model.enums.CouponType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "user_coupon")
data class UserCoupon(
    @Id
    val id: String? = null,
    val userId: String,
    val couponId: String,
    val title: String,
    val content: String,
    val type: CouponType,
    val availableFrom: LocalDateTime,
    val availableTo: LocalDateTime,
    val used: Boolean = false,
    val usedAt: LocalDateTime? = null,
)
