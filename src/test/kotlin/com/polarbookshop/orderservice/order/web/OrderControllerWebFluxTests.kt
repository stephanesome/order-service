package com.polarbookshop.orderservice.order.web

import com.polarbookshop.orderservice.order.domain.Order
import com.polarbookshop.orderservice.order.domain.OrderService
import com.polarbookshop.orderservice.order.domain.OrderStatus
import com.polarbookshop.orderservice.order.domain.buildRejectedOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono


@WebFluxTest(OrderController::class)
internal class OrderControllerWebFluxTests {
	@Autowired
	private val webClient: WebTestClient? = null

	@MockBean
	private val orderService: OrderService? = null

	@Test
	fun whenBookNotAvailableThenRejectOrder() {
		val orderRequest = OrderRequest("1234567890", 3)
		val expectedOrder: Order = buildRejectedOrder(orderRequest.isbn, orderRequest.quantity)
		given(orderService!!.submitOrder(orderRequest.isbn, orderRequest.quantity))
			.willReturn(Mono.just(expectedOrder))
		webClient!!
			.post()
			.uri("/orders/")
			.bodyValue(orderRequest)
			.exchange()
			.expectStatus().is2xxSuccessful
			.expectBody(Order::class.java).value { actualOrder ->
				assertThat(actualOrder).isNotNull
				assertThat(actualOrder.status).isEqualTo(OrderStatus.REJECTED)
			}
	}
}
