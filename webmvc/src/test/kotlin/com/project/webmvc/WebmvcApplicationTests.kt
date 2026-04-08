package com.project.webmvc

import com.project.webmvc.presentation.config.PageableConfig
import com.project.webmvc.presentation.config.WebMvcExceptionHandlerConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest(classes = [WebmvcTestApplication::class])
class WebmvcApplicationTests {

	@Test
	fun contextLoads() {
	}

}

@SpringBootApplication
@Import(PageableConfig::class, WebMvcExceptionHandlerConfig::class)
class WebmvcTestApplication
