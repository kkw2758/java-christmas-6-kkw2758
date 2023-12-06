package christmas.service;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.dto.request.OrdersRequest;

public class ChristmasPromotionService {
    private static final ChristmasPromotionService instance = new ChristmasPromotionService();

    private ChristmasPromotionService() {
    }

    public static ChristmasPromotionService getInstance() {
        return instance;
    }

    public VisitDate generateVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    public Orders generateOrders(OrdersRequest ordersRequest) {
        return Orders.from(ordersRequest);
    }
}
