package model.entity

import model.enums.CouponType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "coupons")
data class Coupon(
    @Id
    val id: String? = null,
    val title: String,
    val content: String,
    val type: CouponType,
    val validDays: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
