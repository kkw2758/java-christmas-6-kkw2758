package christmas.controller;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMessage;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasPromotionService;
import christmas.view.Inputview;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private static final ChristmasPromotionController instance = new ChristmasPromotionController();
    private final Inputview inputView = Inputview.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final ChristmasPromotionService christmasPromotionService = ChristmasPromotionService.getInstance();

    private ChristmasPromotionController() {
    }

    public static ChristmasPromotionController getInstance() {
        return instance;
    }

    public void run() {
        VisitDate visitDate = ExceptionHandler.handle(this::generateVisitDateFromUserInput);
        Orders orders = ExceptionHandler.handle(this::generateOrdersFromUserInput);

    }

    private VisitDate generateVisitDateFromUserInput() {
        return christmasPromotionService.generateVisitDate(inputView.requestVisitDate(ErrorMessage.INVALID_VISIT_DATE));
    }

    private Orders generateOrdersFromUserInput() {
        return christmasPromotionService.generateOrders(inputView.requestOrders(ErrorMessage.INVALID_ORDERS));
    }
}
