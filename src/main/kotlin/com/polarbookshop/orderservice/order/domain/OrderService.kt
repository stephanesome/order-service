package com.polarbookshop.orderservice.order.domain

import com.polarbookshop.orderservice.book.Book
import com.polarbookshop.orderservice.book.BookClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrderService(private val bookClient: BookClient,
    private val orderRepository: OrderRepository) {
    val allOrders: Flux<Order>
        get() = orderRepository.findAll()

    fun submitOrder(isbn: String, quantity: Int): Mono<Order>? {
        return bookClient.getBookByIsbn(isbn)
            .map { book -> buildAcceptedOrder(book, quantity) }
            .defaultIfEmpty(
                buildRejectedOrder(isbn, quantity)
            )
            .flatMap { entity: Order -> orderRepository.save(entity) }
    }

}

fun buildAcceptedOrder(book: Book, quantity: Int): Order {
    return orderOf(
        book.isbn, book.title.toString() + " - " + book.author,
        book.price, quantity, OrderStatus.ACCEPTED
    )
}

fun buildRejectedOrder(bookIsbn: String, quantity: Int): Order {
    return orderOf(bookIsbn, null, null, quantity, OrderStatus.REJECTED)
}
