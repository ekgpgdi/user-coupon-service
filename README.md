# 쿠폰 서비스

회원가입 시 쿠폰을 발급하고, 쿠폰 조회 및 사용 처리까지 담당하는 백엔드 서비스입니다. Kafka 기반의 이벤트 드리븐 아키텍처와 MongoDB를 활용하여 구현합니다.

## 🚀 기술 스택

- **Kotlin + Spring Boot**
- **MongoDB**
- **Apache Kafka**
- **QueryDSL**
- **Kotest**
- **OpenAPI Generator**

## 🎯 프로젝트 목표

- **회원가입 쿠폰 발급** (Kafka 이벤트 수신)
- **쿠폰 조회 API** 제공
- **쿠폰 사용 처리** (Kafka 이벤트 수신 및 사용 상태 업데이트)

👉 **Kafka Consumer**와 **MongoDB 연동** 중심으로 구성된 이벤트 기반 마이크로서비스 예제입니다.

## 🛠️ 기능 및 API

| 기능                          | 설명                                                                          |
|-------------------------------|-------------------------------------------------------------------------------|
| 쿠폰 발급 (Kafka)             | 회원가입 시 Kafka 이벤트를 수신하여 쿠폰을 발급합니다.                        |
| 쿠폰 조회 (API)               | 쿠폰 ID로 쿠폰 정보를 조회합니다.                                            |
| 쿠폰 사용 처리 (Kafka)        | 결제 서비스에서 쿠폰 사용 이벤트를 Kafka로 보내면 해당 이벤트를 처리합니다.    |

---

### 1️⃣ 쿠폰 발급 (Kafka Consumer)

- **토픽:** `user.registration`
- **이벤트 예시:**

```json
{
  "userId": "abc123",
  "eventType": "USER_REGISTERED",
  "timestamp": "2025-05-03T12:00:00Z"
}
```
* 동작: USER_REGISTERED 이벤트 수신 시 쿠폰을 발급하고 MongoDB에 저장합니다.

### 2️⃣ 쿠폰 조회 API

- **엔드포인트**: GET /coupons/{couponId}
- **설명**: 쿠폰 ID로 쿠폰 상세 정보를 조회합니다.
- **응답 예시**:

```json
{
  "couponId": "coup-001",
  "userId": "abc123",
  "status": "ISSUED",
  "issuedAt": "2025-05-03T12:00:00Z",
  "redeemedAt": null
}
```

### 3️⃣ 쿠폰 사용 처리 (Kafka Consumer)
- **토픽** : order.payment
- **이벤트 예시**:

```json
{
  "orderId": "order-987",
  "userId": "abc123",
  "couponId": "coup-001",
  "eventType": "COUPON_USED",
  "timestamp": "2025-05-03T12:30:00Z"
}
```
* 동작: 해당 쿠폰을 MongoDB에서 사용 처리(상태 업데이트)합니다.

## 📖 OpenAPI Generator

이 프로젝트에서는 쿠폰 조회 API의 명세를 **OpenAPI(Swagger)** 로 작성하고, 다음과 같은 목적으로 OpenAPI Generator를 사용합니다:

- API 스펙 문서 자동화
- 클라이언트 코드(Java/Kotlin 등) 자동 생성 연습
- Swagger UI 같은 인터페이스로 API 테스트

### 활용 계획

1. `openapi.yaml` 파일 작성
2. OpenAPI Generator로:
   - 클라이언트 SDK 생성 예시
   - 문서 생성 예시

### 실행 예시

```bash
# Kotlin 클라이언트 코드 생성
openapi-generator-cli generate -i openapi.yaml -g kotlin -o ./generated-client
```
