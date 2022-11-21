package com.tppa.tppa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TppaApplicationTests {

	@Test
	void contextLoads() {

		var numero = 1;

		assertEquals(1, numero);
	}

}
