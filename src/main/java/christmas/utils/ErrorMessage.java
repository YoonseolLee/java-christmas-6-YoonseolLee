package christmas.utils;

public enum ErrorMessage {
    VISITING_DATE_NOT_INTEGER_ERROR("[ERROR] 방문일은 정수만 입력 가능합니다."),
    VISITING_DATE_OUT_OF_RANGE_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    VISITING_DATE_EMPTY_ERROR("[ERROR] 방문일은 빈칸일 수 없습니다."),
    VISITING_DATE_LEADING_ZERO_ERROR("[ERROR] 방문일의 맨 앞에 0을 입력할 수 없습니다."),

    ORDER_EMPTY_ERROR("[ERROR] 주문목록은 빈칸일 수 없습니다."),
    ORDER_NO_HYPHEN_ERROR("[ERROR] 주문하실 메뉴와 수량을 하이픈(-)으로 이어주세요."),
    ORDER_MENU_NOT_KOREAN_ERROR("[ERROR] 메뉴는 한국어로 입력해주세요."),
    ORDER_QUANTITY_NOT_INTEGER_ERROR("[ERROR] 주문 수량은 1 이상의 숫자만 입력해주세요."),
    ORDER_CONTAINS_WHITESPACE_ERROR("[ERROR] 주문하실 때 공백을 포함할 수 없습니다.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
