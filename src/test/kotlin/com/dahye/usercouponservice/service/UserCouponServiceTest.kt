package com.dahye.usercouponservice.service

import com.dahye.usercouponservice.model.entity.UserCoupon
import com.dahye.usercouponservice.model.enums.CouponType
import com.dahye.usercouponservice.repository.UserCouponRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
@Import(SpringExtension::class)
class UserCouponServiceTest(
    val userCouponService: UserCouponService,
    val userCouponRepository: UserCouponRepository,
) : BehaviorSpec({

    Given("유저가 쿠폰을 가지고 있을 때") {
        val userId = "testUser"
        val savedCoupon = userCouponRepository.save(
            UserCoupon(
                userId = userId,
                couponId = "coupon123",
                title = "테스트 쿠폰",
                content = "테스트 내용",
                type = CouponType.SIGN_UP,
                availableFrom = LocalDateTime.now().minusDays(1),
                availableTo = LocalDateTime.now().plusDays(5),
            ),
        )

        When("유저 쿠폰을 조회하면") {
            val result = userCouponService.getUserCoupon(userId)

            Then("쿠폰 리스트가 정상적으로 반환된다") {
                result.shouldNotBeNull()
                result.userId shouldBe userId
                result.coupons shouldHaveSize 1

                val coupon = result.coupons.first()
                coupon.couponId shouldBe savedCoupon.couponId
                coupon.title shouldBe savedCoupon.title
                coupon.content shouldBe savedCoupon.content
                coupon.type shouldBe savedCoupon.type
                coupon.isUsed shouldBe false
            }
        }
    }

    afterEach {
        userCouponRepository.deleteAll()
    }
})

