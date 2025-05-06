package com.dahye.usercouponservice.repository

import com.dahye.usercouponservice.model.entity.UserCoupon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCouponRepository : MongoRepository<UserCoupon, String> {
    fun findByUserId(userId: String): List<UserCoupon>?
}
