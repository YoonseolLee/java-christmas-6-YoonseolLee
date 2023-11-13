package christmas.domain;

import christmas.view.OutputView;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PromotionManager {
    public <T> T getValidInput(Supplier<T> inputSupplier) {
        return Stream.generate(() -> tryGet(inputSupplier))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow();
    }

    private <T> Optional<T> tryGet(Supplier<T> inputSupplier) {
        try {
            return Optional.of(inputSupplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return Optional.empty();
        }
    }
}
