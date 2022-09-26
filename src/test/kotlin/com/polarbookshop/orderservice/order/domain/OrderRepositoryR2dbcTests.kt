package com.polarbookshop.orderservice.order.domain

import com.polarbookshop.orderservice.config.DataConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import reactor.test.StepVerifier
/*
@DataR2dbcTest
@Import(DataConfig::class)
@Testcontainers
class OrderRepositoryR2dbcTests {
    @Autowired
    private val orderRepository: OrderRepository? = null

    @Test
    fun findOrderByIdWhenNotExisting() {
        StepVerifier.create(orderRepository!!.findById(394L))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun createRejectedOrder() {
        val rejectedOrder: Order = buildRejectedOrder("1234567890", 3)
        StepVerifier.create(orderRepository!!.save(rejectedOrder))
            .expectNextMatches { order: Order ->
                order.status!! == OrderStatus.REJECTED
            }
            .verifyComplete()
    }
}

@Container
var postgresql: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:14.3"))

@DynamicPropertySource
fun postgresqlProperties(registry: DynamicPropertyRegistry) {
    registry.add("spring.r2dbc.url") { r2dbcUrl() }
    registry.add("spring.r2dbc.username") { postgresql.username }
    registry.add("spring.r2dbc.password") { postgresql.password }
    registry.add("spring.flyway.url") { postgresql.jdbcUrl }
}

private fun r2dbcUrl(): String {
    return String.format(
        "r2dbc:postgresql://%s:%s/%s", postgresql.host,
        postgresql.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT), postgresql.databaseName
    )
}
*/
