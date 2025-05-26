# Schedule API 명세서
<details>
<summary>일정 생성 API (POST /schedules)</summary>

#### 설명
새로운 일정을 생성합니다.

**Request Body:**
```json
{
  "username": "유정명",
  "title": "할일 제목",
  "todo": "할일 내용",
  "created_at": "2025-05-19",
  "updated_at": "2025-05-19"
}
````

**Response Body:**

```json
{
  "id": 1,
  "username": "유정명",
  "title": "할일 제목",
  "todo": "할일 내용",
  "created_at": "2025-05-19",
  "updated_at": "2025-05-19"
}
```

</details>

<details>
<summary>일정 목록 조회 API (GET /schedules)</summary>

#### 설명

전체 일정을 조회합니다.

**Response Body:**

```json
[
  {
    "id": 1,
    "username": "유정명",
    "title": "할일 제목",
    "todo": "할일 내용",
    "created_at": "2025-05-19",
    "updated_at": "2025-05-19"
  },
  {
    "id": 2,
    "username": "박지민",
    "title": "다른 할일",
    "todo": "다른 내용",
    "created_at": "2025-05-20",
    "updated_at": "2025-05-20"
  }
]
```

</details>

<details>
<summary>일정 상세 조회 API (GET /schedules/{id})</summary>

#### 설명

지정된 ID의 일정을 조회합니다.

**Response Body:**

```json
{
  "id": 1,
  "username": "유정명",
  "title": "할일 제목",
  "todo": "할일 내용",
  "created_at": "2025-05-19",
  "updated_at": "2025-05-19"
}
```

</details>

<details>
<summary>일정 수정 API (PUT /schedules/{id})</summary>

#### 설명

지정된 ID의 일정을 수정합니다.

**Request Body:**

```json
{
  "title": "수정된 제목",
  "todo": "수정된 내용",
  "updated_at": "2025-05-20"
}
```

**Response Body:**

```json
{
  "id": 1,
  "username": "유정명",
  "title": "수정된 제목",
  "todo": "수정된 내용",
  "created_at": "2025-05-19",
  "updated_at": "2025-05-20"
}
```
</details>

<details>
<summary>일정 삭제 API (DELETE /schedules/{id})</summary>

#### 설명

지정된 ID의 일정을 삭제합니다.

**Response Body:**

```json
{
  "message": "삭제가 완료되었습니다."
}
```
</details>

___
# 유저 API 명세서
<details>
<summary>유저 API (POST /users)</summary>

#### 설명  
새로운 사용자를 등록합니다.

**Request Body:**
```json
{
  "username": "user123",
  "email": "user@example.com",
  "password": "password123"
}
````

**Response Body:**

```json
{
  "id": 1,
  "username": "user123",
  "email": "user@example.com",
  "created_at": "2025-05-26"
}
```

</details>

<details>
<summary>유저 정보 조회 API (GET /users/{id})</summary>

#### 설명

사용자 ID로 회원 정보를 조회합니다.

**Response Body:**

```json
{
  "id": 1,
  "username": "user123",
  "email": "user@example.com",
  "created_at": "2025-05-26"
}
```

</details>

<details>
<summary>유저 수정 API (PUT /users/{id})</summary>

#### 설명

사용자 정보를 수정합니다.

**Request Body:**

```json
{
  "username": "newUser123",
  "email": "newemail@example.com"
}
```

**Response Body:**

```json
{
  "id": 1,
  "username": "newUser123",
  "email": "newemail@example.com",
  "updated_at": "2025-05-26"
}
```

</details>

<details>
<summary>유저 API (DELETE /users/{id})</summary>

#### 설명

사용자를 삭제(탈퇴)합니다.

**Response Body:**

```json
{
  "message": "회원 탈퇴가 완료되었습니다."
}
```

</details>

---

### 3. 로그인 기능 API 명세 예시

<details>
<summary>로그인 API (POST /auth/login)</summary>

#### 설명  
사용자 로그인 요청을 처리합니다.

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
````

**Response Body:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "user123",
    "email": "user@example.com"
  }
}
```

</details>

<details>
<summary>로그아웃 API (POST /auth/logout)</summary>

#### 설명

사용자 로그아웃 요청을 처리합니다.

**Response Body:**

```json
{
  "message": "로그아웃 되었습니다."
}
```

</details>
