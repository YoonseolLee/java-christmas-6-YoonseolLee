package christmas;

import christmas.domain.event.badge.EventBadge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {
    @DisplayName("GetBadgeByTotalBenefitAmount() 유효성 테스트")
    @Test
    public void 총혜택금액과_알맞는_배지를_가져오면_정상실행() {
        Assertions.assertEquals("산타", EventBadge.getBadgeByTotalBenefitAmount(20000));
        Assertions.assertEquals("트리", EventBadge.getBadgeByTotalBenefitAmount(15000));
        Assertions.assertEquals("별", EventBadge.getBadgeByTotalBenefitAmount(5000));
        Assertions.assertEquals("없음", EventBadge.getBadgeByTotalBenefitAmount(3000));
    }
}
