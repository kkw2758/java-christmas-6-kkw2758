package christmas.domain.event;

import christmas.domain.Day;
import christmas.domain.Orders;
import java.util.Map;

public interface Event {
    String getName();

    Map<Event, Integer> getBenefitInfo(Orders orders, Day day);
}
