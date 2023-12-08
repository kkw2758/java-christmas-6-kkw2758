package christmas.dto.request;

import java.util.List;

public record OrdersRequest(
        List<OrderRequest> orderRequests
) {
}
