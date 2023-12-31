# [크리스마스 프로모션]

## 진행 순서

식당 방문 날짜 등록 -> 주문메뉴와 주문개수 등록 -> 이벤트 대상 등록 -> 이벤트 혜택 적용 ->
할인 후 결제 금액 계산 -> 12월 이벤트 배지 적용의 순서대로 진행된다.

### 1. 식당 방문 날짜 등록

-[x] 인사말을 출력하는 기능
-[x] 식당 예상 방문 날짜를 입력받는 기능
    -[x] (예외) 입력값이 오로지 양의 정수만으로 이루어졌는지 검증하는 기능
    -[x] (예외) 입력값의 서두에 0이 없는지 검증하는 기능
    -[x] (예외) 입력값이 비어있지는 않은지 검증하는 기능
    -[x] (예외) 입력값이 1 이상 31 이하의 숫자인지 검증하는 기능
    -[x] 예외 발생 시 IllegalArgumentException 발생시킴
    -[x] 예외 발생시 "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받음

### 2. 주문 메뉴와 주문 개수 등록

-[x] 주문할 메뉴와 개수를 입력받는 기능
    -[x] (예외) 공백 여부를 검증하는 기능
    -[x] (예외) "메뉴-수량"과 같이 올바른 포맷인지 검증하는 기능
    -[x] (예외) 주문 수량이 양의 정수인지 검증하는 기능
    -[x] (예외) 고객이 메뉴판에 있는 메뉴를 입력했는지 검증하는 기능
    -[x] (예외) 메뉴의 개수가 1 이상의 숫자인지 검증하는 기능
    -[x] (예외) 메뉴의 중복 여부를 검증하는 기능
    -[x] (예외) 음료만 주문하지는 않았는지 검증하는 기능
    -[x] (예외) 주문한 메뉴의 총 개수가 20개를 넘기지 않았는지 검증하는 기능 (20개까지 허용, 21개부터 예외)
    -[x] 예외 발생 시 IllegalArgumentException 발생시킴
    -[x] 예외 발생시 "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받음
- [x] 주문 메뉴와 개수를 등록하는 기능
- [x] 주문 메뉴와 개수를 출력하는 기능
- [x] 할인 전 총주문 금액을 출력하는 기능

### 3. 이벤트 대상 등록

-[x] 방문 날짜를 기반으로 할인 이벤트 대상 등록
    -[x] 방문 날짜가 12월 1일 ~ 12월 25일 사이라면, 크리스마스 디데이 할인 대상 적용하는 기능
    -[x] 방문 날짜가 평일이면 평일 할인 대상 적용하는 기능
    -[x] 방문 날짜가 주말이면 주말 할인 대상 적용하는 기능
    -[x] 방문 날짜가 일요일이거나 25일이면 특별 할인 적용 대상 등록
-[x] 주문 메뉴와 개수를 기반으로 이벤트 적용 대상 등록
    -[x] 할인 전 총주문 금액이 1만원 이하면 모든 이벤트 대상에서 제외하는 기능
    -[x] 할인 전 총주문 금액이 12만 원 이상일 때, 증정 이벤트 대상 적용하는 기능

### 4. 이벤트 혜택 적용

-[x] 이벤트 혜택을 적용하는 기능
    -[x] 크리스마스 디데이 할인 적용 대상이라면, 해당 날짜에 알맞는 금액 할인하는 기능
    -[x] 평일 할인 대상이라면, 디저트 메뉴 1개 당 2,023원 할인하는 기능
    -[x] 주말(금,토) 할인 대상이라면, 메인 메뉴 1개 당 2,023원 할인하는 기능
    -[x] 특별 할인 대상이라면, 총주문 금액에서 1,000원 할인하는 기능
    -[x] 증정 이벤트 대상이라면, 샴페인 1개 증정하는 기능
-[x] 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제금액을 계산하는 기능
-[x] 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제금액을 출력하는 기능
    -[x] 증정 이벤트에 해당하지 않으면 "없음"을 출력하는 기능
    -[x] 혜택 내역이 모두 없으면 "없음"을 출력하는 기능(하나라도 있으면 0원을 출력해야함)

### 5. 12월 이벤트 배지 적용

-[x] 총 혜택금액 별로 이벤트 배지를 부여하는 기능
    -[x] 5천 원 이상: 별
    -[x] 1만 원 이상: 트리
    -[x] 2만 원 이상: 산타
-[x] 이벤트 배지를 출력하는 기능
    -[x] 이벤트 배지가 부여되지 않은 경우, "없음"을 출력하는 기능

## 핵심 요구사항

-[x] 입력과 출력을 담당하는 클래스를 별도로 구현한다.
-[x] 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI 로직은 제외한다.
-[x] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
-[x] 들여쓰기를 2까지만 작성한다.
-[x] else, switch-case를 쓰지 않는다.
-[x] 함수가 15라인을 넘어가지 않게 작성한다.
-[x] 연관성이 있는 상수는 enum을 사용한다.
-[x] final 키워드를 사용해 값의 변경을 막는다.
-[x] 인스턴스 변수의 접근 제어자는 private으로 구현한다.
-[x] 성공하는 케이스, 예외 케이스 모두 테스트코드를 작성한다.
-[x] 변수 이름에 자료형은 사용하지 않는다(ex.lottoList).
