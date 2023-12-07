package christmas.dto.response;

import java.util.List;

public record OrdersResponse(
        List<OrderResponse> orderResponses
) {
}
