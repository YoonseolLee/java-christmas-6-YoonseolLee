package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuBoardTest {
    @DisplayName("getSort() 테스트")
    @Test
    public void 메뉴에_해당하는_sort를_가져오면_정상실행() {
        assertThat(MenuBoard.시저샐러드.getSort()).isEqualTo("Appetizer");
        assertThat(MenuBoard.바비큐립.getSort()).isEqualTo("Main");
        assertThat(MenuBoard.초코케이크.getSort()).isEqualTo("Dessert");
        assertThat(MenuBoard.샴페인.getSort()).isEqualTo("Beverage");
    }

    @DisplayName("getPrice() 테스트")
    @Test
    public void 메뉴에_해당하는_price를_가져오면_정상실행() {
        assertThat(MenuBoard.시저샐러드.getPrice()).isEqualTo(8000);
        assertThat(MenuBoard.바비큐립.getPrice()).isEqualTo(54000);
        assertThat(MenuBoard.초코케이크.getPrice()).isEqualTo(15000);
        assertThat(MenuBoard.샴페인.getPrice()).isEqualTo(25000);
    }
}
