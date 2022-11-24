package com.tppa.tppa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoNacional;


@SpringBootTest
class TppaApplicationTests {

	@Test
	void testCalculoImpuestoExtranjeroYAmerica() {

		EstrategiaCostoAmericaExtranjero ece = new EstrategiaCostoAmericaExtranjero();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230000D);

		var a = ece.calcularCosto(auto);
        
		assertEquals(253000D, a.getCosto());	
	}

	@Test
	void testCalculoImpuestoNacionalRango1() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230000D);

		var a = ecn.calcularCosto(auto);
        
		assertEquals(280000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango2() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		var a = ecn.calcularCosto(auto);
        
		assertEquals(70000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango3() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(40000D);

		var a = ecn.calcularCosto(auto);
        
		assertEquals(45000D, a.getCosto());
	}

	//GANANCIA

	@Test
	void testCalculoGananciaImpuestoExtranjeroYAmerica() {

		EstrategiaCostoAmericaExtranjero ece = new EstrategiaCostoAmericaExtranjero();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230000D);

		var a = ece.calcularCosto(auto);
        
		assertEquals(23000D, a.getGanancia());	
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango1() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(40000D);

		var a = ecn.calcularCosto(auto);

		assertEquals(5000D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango2() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(51000D);

		var a = ecn.calcularCosto(auto);

		assertEquals(10000D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango3() {

		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(235440D);

		var a = ecn.calcularCosto(auto);

		assertEquals(50000D, a.getGanancia());
	}
}
