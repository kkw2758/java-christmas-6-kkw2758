package christmas.controller;

import christmas.domain.Day;
import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.view.InputUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run() {
        OutputView.printStartMessage();
        Day day = generateDay();

        Orders orders = generateOrders();
        OutputView.printStartPreviewMessage(day.getDate().getDayOfMonth());

        OutputView.printOrders(orders);
        OutputView.printTotalPriceBeforeSale(orders);

        EventResult eventApplier = EventResult.of(orders, day);
        OutputView.printGiftMenu(eventApplier.getGiftMenu());
        OutputView.printBenefitsInfo(eventApplier.getBenefitInfo());
        OutputView.printTotalBenefitAmount(eventApplier.calculateTotalBenefitAmount());

        OutputView.printPriceAfterSale(eventApplier.calculatePriceAfterSale(orders.calculatePriceBeforeSale()));
        OutputView.printEventBadge(eventApplier.calculateTotalBenefitAmount());
    }

    private Day generateDay() {
        OutputView.printDayRequestMessage();
        return InputUtil.retryOnException(() -> {
            return Day.of(InputView.inputNaturalNumber());
        }, true);
    }

    private Orders generateOrders(){
        OutputView.printOrderRequestMessage();
        return InputUtil.retryOnException(() -> {
            return Orders.of(InputView.readLine());
        }, true);
    }
}
