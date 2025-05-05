package model.enums

enum class CouponType(
    val description: String,
) {
    SIGN_UP("회원가입 쿠폰"),
    FIRST_PURCHASE("첫 구매 쿠폰"),
    REFERRAL("추천인 쿠폰"),
}
