package christmas;

import christmas.domain.order.Menu;
import christmas.domain.order.MenuBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private Menu menuMain;
    private Menu menuDessert;

    @BeforeEach
    public void setUp() {
        menuMain = new Menu(MenuBoard.티본스테이크, 2);
        menuDessert = new Menu(MenuBoard.초코케이크, 1);
    }

    @DisplayName("GetMenuBoard 테스트")
    @Test
    public void getMenuBoard를_실행하면_name을_불러온다() {
        Assertions.assertThat(menuMain.getMenuBoard()).isEqualTo(MenuBoard.티본스테이크);
        Assertions.assertThat(menuDessert.getMenuBoard()).isEqualTo(MenuBoard.초코케이크);
    }

    @DisplayName("GetQuantity 테스트")
    @Test
    public void getQuantity를_실행하면_quantity를_불러온다() {
        Assertions.assertThat(menuMain.getQuantity()).isEqualTo(2);
        Assertions.assertThat(menuDessert.getQuantity()).isEqualTo(1);
    }

    @DisplayName("isDessert 테스트")
    @Test
    public void 디저트_메뉴이면_true를_반환한다() {
        Assertions.assertThat(menuMain.isDessert()).isFalse();
        Assertions.assertThat(menuDessert.isDessert()).isTrue();
    }

    @DisplayName("isMain 테스트")
    @Test
    public void 메인_메뉴이면_true를_반환한다() {
        Assertions.assertThat(menuMain.isMain()).isTrue();
        Assertions.assertThat(menuDessert.isMain()).isFalse();
    }
}
