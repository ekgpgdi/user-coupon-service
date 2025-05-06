package com.dahye.usercouponservice.repository

import com.dahye.usercouponservice.model.entity.Coupon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponRepository : MongoRepository<Coupon, String>
