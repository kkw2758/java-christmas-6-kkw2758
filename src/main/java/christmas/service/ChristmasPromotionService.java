package christmas.service;

public class ChristmasPromotionService {
    private static final ChristmasPromotionService instance = new ChristmasPromotionService();

    private ChristmasPromotionService() {
    }

    public static ChristmasPromotionService getInstance() {
        return instance;
    }
}
