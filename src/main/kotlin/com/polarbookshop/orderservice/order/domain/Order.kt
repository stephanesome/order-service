package com.polarbookshop.orderservice.order.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant


@Table("orders")
class Order {
    @Id
    var id: Long? = null

    var bookIsbn: String? = null
    var bookName: String? = null
    var bookPrice: Double? = null
    var quantity: Int? = null
    var status: OrderStatus? = null

    @CreatedDate
    var createdDate: Instant? = null

    @LastModifiedDate
    var lastModifiedDate: Instant? = null

    @Version
    var version: Int = 0
}

fun orderOf(bookIsbn: String?, bookName: String?, bookPrice: Double?, quantity: Int?, status: OrderStatus?): Order {
    val order = Order()
    order.bookIsbn = bookIsbn
    order.bookName = bookName
    order.bookPrice = bookPrice
    order.quantity = quantity
    order.status = status
    return order
}

