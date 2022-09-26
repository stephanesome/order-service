package com.polarbookshop.orderservice.order.web

import com.polarbookshop.orderservice.order.domain.Order
import com.polarbookshop.orderservice.order.domain.OrderService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid


@RestController
@RequestMapping("orders")
class OrderController(private val orderService: OrderService) {
    @get:GetMapping
    val allOrders: Flux<Order>
        get() = orderService.allOrders

    @PostMapping
    fun submitOrder(@RequestBody orderRequest: @Valid OrderRequest): Mono<Order>? {
        return orderService.submitOrder(
            orderRequest.isbn, orderRequest.quantity
        )
    }
}
