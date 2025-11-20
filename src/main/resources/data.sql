DELETE FROM user_misson;
DELETE FROM review;
DELETE FROM misson;
DELETE FROM store;
DELETE FROM user;
DELETE FROM resion;


-- =========================
-- REGION
-- =========================
INSERT INTO resion (resion_id, resion_name)
VALUES (1, '서울'), (2, '부산'), (3, '대구');

-- =========================
-- STORE (각 지역마다 상점 연결)
-- =========================
INSERT INTO store (store_id, resion_id, name, address, food_category, created_at, updated_at)
VALUES
(1, 1, '마라탕천국 강남점', '서울 강남구 어딘가', '중식', NOW(), NOW()),
(2, 1, '반이학생마라탕마라반', '서울 중구 어딘가', '중식', NOW(), NOW()),
(3, 2, '부산마라탕 전문점', '부산 해운대구 어딘가', '중식', NOW(), NOW()),
(4, 3, '대구마라탕', '대구 중구 어딘가', '중식', NOW(), NOW());

-- =========================
-- USERS
-- =========================
INSERT INTO user (user_id, name, gender, email, phone_number, birth, address, status, privacy_agree, id, password, point, created_at, updated_at)
VALUES
(1, '김세진', 'M', 'sejin@example.com', '01012341234', '2001-01-01', '서울', 'ACTIVE', true, 'sejin01', 'pw1', 100, NOW(), NOW()),
(2, '홍길동', 'M', 'hong@example.com', '01055556666', '1995-05-05', '부산', 'ACTIVE', true, 'hong01', 'pw2', 50, NOW(), NOW());

-- =========================
-- MISSION (각 가게별 10개씩 → 페이징 테스트용)
-- =========================
INSERT INTO misson (misson_id, title, description, min_spend, point, starts_at, ends_at, store_id, resion_id)
VALUES
(1, '10,000원 이상 먹기', '가게에서 1만원 이상 주문', 10000, 500, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(2, '2인 방문', '2명 이상 방문 시 도전 가능', NULL, 300, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(3, '마라탕 먹기', '마라탕 아무거나 시키기', 8000, 200, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(4, '꿔바로우 주문', '꿔바로우 먹기', 12000, 400, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(5, '마라샹궈 먹기', '마라샹궈 아무거나 시키기', 15000, 600, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(6, '음료 주문', '음료 1개 필수', NULL, 100, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(7, '공기밥 추가', '공기밥 추가하기', NULL, 50, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(8, '사리 2개 추가', '사리 2개 넣기', 2000, 150, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(9, '마라탕 찍먹하기', '마라탕 찍먹 인증', NULL, 80, NOW(), NOW() + INTERVAL 7 DAY, 1, 1),
(10, '순한맛 마라탕 먹기', '순한맛 인증', NULL, 70, NOW(), NOW() + INTERVAL 7 DAY, 1, 1);

-- =========================
-- USER_MISSION 진행 중 목록용
-- =========================
INSERT INTO user_misson (user_misson_id, verrification_code, status, challenge_at, completed_at, user_id, misson_id, resion_id)
VALUES
(1, 'ABC123', 'PROCESS', NOW(), NULL, 1, 1, 1),
(2, 'XYZ999', 'PROCESS', NOW(), NULL, 1, 2, 1),
(3, 'HELLO22', 'COMPLETE', NOW(), NOW(), 1, 3, 1), -- completed → 조회 제외됨
(4, 'GGGG55', 'PROCESS', NOW(), NULL, 2, 4, 1);

-- =========================
-- REVIEW (내가 작성한 리뷰 10개 만들기 → 페이징 정확)
-- =========================
INSERT INTO review (review_id, rating, content, reply, user_id, store_id, resion_id, created_at, updated_at)
VALUES
(1, 5, '맛있어요!', NULL, 1, 2, 1, NOW(), NOW()),
(2, 4, '진짜 맛있다', NULL, 1, 2, 1, NOW(), NOW()),
(3, 3, '보통이었음', NULL, 1, 2, 1, NOW(), NOW()),
(4, 5, '매콤하니 좋음', NULL, 1, 2, 1, NOW(), NOW()),
(5, 4, '또 올게요', NULL, 1, 2, 1, NOW(), NOW()),
(6, 5, '오늘도 만족!', NULL, 1, 2, 1, NOW(), NOW()),
(7, 3, '나쁘지 않아요', NULL, 1, 2, 1, NOW(), NOW()),
(8, 4, '양 많음', NULL, 1, 2, 1, NOW(), NOW()),
(9, 5, '맛있게 잘 먹음', NULL, 1, 2, 1, NOW(), NOW()),
(10, 2, '너무 짜요', NULL, 1, 2, 1, NOW(), NOW());
