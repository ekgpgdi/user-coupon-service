package com.dahye.usercouponservice.service

import com.dahye.usercouponservice.model.dto.response.CouponResponse
import com.dahye.usercouponservice.model.dto.response.UserCouponResponse
import com.dahye.usercouponservice.repository.UserCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserCouponService(
    private val userCouponRepository: UserCouponRepository,
) {

    @Transactional(readOnly = true)
    fun getUserCoupon(id: String): UserCouponResponse {
        return UserCouponResponse(
            userId = id,
            coupons = userCouponRepository.findByUserId(id)?.map { userCoupon ->
                CouponResponse(
                    couponId = userCoupon.couponId,
                    title = userCoupon.title,
                    content = userCoupon.content,
                    type = userCoupon.type,
                    availableFrom = userCoupon.availableFrom,
                    availableTo = userCoupon.availableTo,
                    isUsed = userCoupon.used,
                    usedAt = userCoupon.usedAt,
                )
            } ?: emptyList(),
        )
    }
}
