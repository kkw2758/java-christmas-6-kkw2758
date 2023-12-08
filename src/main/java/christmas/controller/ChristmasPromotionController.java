package christmas.controller;

import christmas.service.ChristmasPromotionService;
import christmas.view.Inputview;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private static final ChristmasPromotionController instance = new ChristmasPromotionController();
    private final Inputview inputview = Inputview.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final ChristmasPromotionService christmasPromotionService = ChristmasPromotionService.getInstance();

    private ChristmasPromotionController() {
    }

    public static ChristmasPromotionController getInstance() {
        return instance;
    }

    public void run() {
        
    }
}
