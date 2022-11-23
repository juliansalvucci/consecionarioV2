package com.tppa.tppa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;


@SpringBootTest
class TppaApplicationTests {

	@Test
	void contextLoads() {

		EstrategiaCostoAmericaExtranjero ece = new EstrategiaCostoAmericaExtranjero();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230.000);

		var a = ece.calcularCosto(auto);
        
		assertEquals(23.000, a.getCosto());
	}

}
