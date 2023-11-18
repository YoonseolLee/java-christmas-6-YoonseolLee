package christmas.utils;

public enum GameMessage {
    GREETINGS_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISITING_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ENTER_ORDER_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_DETAILS("<혜택 내역>"),
    NONE_MESSAGE("없음"),
    BENEFIT_PREVIEW_ON_VISITING_DATE_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n"),
    ORDERED_MENU("<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인: %s원%n"),
    WEEKND_DISCOUNT("주말 할인: %s%n"),
    WEEKDAY_DISCOUNT("평일 할인: %s%n"),
    SPECIAL_DISCOUNT("특별 할인: %s원%n"),
    GIVEAWAY_EVENT("증정 이벤트: %s원%n"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    ESTIMATED_PRICE_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>");

    private final String message;

    GameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
