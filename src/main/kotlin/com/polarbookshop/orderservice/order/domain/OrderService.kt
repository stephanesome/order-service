package com.polarbookshop.orderservice.order.domain

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono





@Service
class OrderService(private val orderRepository: OrderRepository) {
    val allOrders: Flux<Order>
        get() = orderRepository.findAll()

    fun submitOrder(isbn: String?, quantity: Int): Mono<Order>? {
        return Mono.just(buildRejectedOrder(isbn, quantity))
            .flatMap { entity: Order ->
                orderRepository.save(
                    entity
                )
            }
    }

    fun buildRejectedOrder(bookIsbn: String?, quantity: Int): Order {
        return orderOf(bookIsbn, null, null, quantity, OrderStatus.REJECTED)
    }
}
