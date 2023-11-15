package christmas;

import christmas.domain.event.wrapped.ChampagneCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChampagneCountTest {
    @DisplayName("increment() 유효성 테스트")
    @Test
    void increment_실행후_1_증가하면_정상실행() {
        // Given
        ChampagneCount initialCount = new ChampagneCount(3);

        // When
        ChampagneCount incrementedCount = initialCount.increment();

        // Then
        Assertions.assertEquals(3, initialCount.getCount());
        Assertions.assertEquals(4, incrementedCount.getCount());
    }
}
