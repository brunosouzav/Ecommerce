package com.ecommerce.fit.record;

import com.ecommerce.fit.enums.OrderStatus;

public record UpdateOrderRequest (OrderStatus status) {

}
