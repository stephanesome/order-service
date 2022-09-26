package com.polarbookshop.orderservice.book

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.io.IOException


internal class BookClientTests {
    private var mockWebServer: MockWebServer? = null
    private var bookClient: BookClient? = null

    @BeforeEach
    @Throws(IOException::class)
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer!!.start()
        val webClient = WebClient.builder()
            .baseUrl(mockWebServer!!.url("/").toUri().toString())
            .build()
        bookClient = BookClient(webClient)
    }

    @AfterEach
    @Throws(IOException::class)
    fun clean() {
        mockWebServer!!.shutdown()
    }

    @Test
    fun whenBookExistsThenReturnBook() {
        val bookIsbn = "1234567890"
        val mockResponse = MockResponse()
            .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody("""
                {
                    "isbn": %s,
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "publisher": "Polarsophia"
                }
                """.format(bookIsbn))
        mockWebServer!!.enqueue(mockResponse)
        val book: Mono<Book> = bookClient!!.getBookByIsbn(bookIsbn)
        StepVerifier.create(book)
            .expectNextMatches{b -> b.isbn == bookIsbn }
        .verifyComplete()
    }
}
