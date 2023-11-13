package christmas.domain.event;

import christmas.domain.Day;
import christmas.domain.Orders;
import java.util.Map;

public interface Event {
    String getName();

    boolean checkEventTarget(Orders orders, Day day);

    Map<Event, Integer> getBenefitInfo(Orders orders, Day day);
}
