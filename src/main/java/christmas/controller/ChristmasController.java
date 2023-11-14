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
        printOrdersInfo(orders, day);
        EventResult eventResult = generateEventResult(orders, day);
        printEventResult(eventResult, orders);
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

    private EventResult generateEventResult(Orders orders, Day day) {
        return EventResult.of(orders, day);
    }

    private void printOrdersInfo(Orders orders, Day day) {
        OutputView.printStartPreviewMessage(day.getDate().getDayOfMonth());
        OutputView.printOrders(orders);
        OutputView.printTotalPriceBeforeSale(orders);
    }

    private void printEventResult(EventResult eventResult, Orders orders) {
        OutputView.printGiftMenu(eventResult.getGiftMenu());
        OutputView.printBenefitsInfo(eventResult.getBenefitInfo());
        OutputView.printTotalBenefitAmount(eventResult.calculateTotalBenefitAmount());
        OutputView.printPriceAfterSale(eventResult.calculatePriceAfterSale(orders.calculatePriceBeforeSale()));
        OutputView.printEventBadge(eventResult.calculateTotalBenefitAmount());
    }
}
