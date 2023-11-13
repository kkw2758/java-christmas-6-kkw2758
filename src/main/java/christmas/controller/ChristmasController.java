package christmas.controller;

import christmas.domain.Day;
import christmas.domain.EventResult;
import christmas.domain.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run() {
        OutputView.printStartMessage();
        OutputView.printDayRequestMessage();
        Day day = InputView.inputDay();

        OutputView.printOrderRequestMessage();
        Orders orders = InputView.inputOrders();
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
}
