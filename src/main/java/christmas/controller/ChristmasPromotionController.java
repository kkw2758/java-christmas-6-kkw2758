package christmas.controller;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasPromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {

    private static final ChristmasPromotionController instance = new ChristmasPromotionController();

    private ChristmasPromotionController() {
    }

    public static ChristmasPromotionController getInstance() {
        return instance;
    }

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final ChristmasPromotionService christmasPromotionService = ChristmasPromotionService.getInstance();

    public void run() {
        ExceptionHandler.handle(this::generateVisitDateFromUserInput);
        ExceptionHandler.handle(this::generateOrdersFromUserInput);
    }

    public VisitDate generateVisitDateFromUserInput() {
        return christmasPromotionService.generateVisitDate(inputView.requestVisitDate());
    }

    public Orders generateOrdersFromUserInput() {
        return christmasPromotionService.generateOrders(inputView.requestOrders());
    }
}
