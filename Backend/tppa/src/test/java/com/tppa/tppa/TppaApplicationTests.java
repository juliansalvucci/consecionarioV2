package com.tppa.tppa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;


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

		Auto auto = new Auto();
		Pais pais = new Pais();
	    Rango rango = new Rango();
		Categoria categoria = new Categoria();

		rango.setMontoInicial(0D);
		rango.setMontoFinal(50000D);
		rango.setValor(5000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        
		assertEquals(65000D, auto.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango2() {

		Auto auto = new Auto();
		Pais pais = new Pais();
	    Rango rango = new Rango();
		Categoria categoria = new Categoria();

		rango.setMontoInicial(50000D);
		rango.setMontoFinal(100000D);
		rango.setValor(50000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        
		assertEquals(110000D, auto.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango3() {

		Auto auto = new Auto();
		Pais pais = new Pais();
	    Rango rango = new Rango();
		Categoria categoria = new Categoria();

		rango.setMontoInicial(100000D);
		rango.setMontoFinal(1000000D);
		rango.setValor(50000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        
		assertEquals(110000D, auto.getCosto());
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

		Auto auto = new Auto();
		Pais pais = new Pais();
		Rango rango = new Rango();
		Categoria categoria = new Categoria();

		rango.setMontoInicial(1000000D);
		rango.setMontoFinal(100000D);
		rango.setValor(50000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(110000D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        auto.setGanancia(valor);

		assertEquals(50000D, auto.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango2() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Rango rango = new Rango();
		Categoria categoria = new Categoria();

		rango.setMontoInicial(0D);
		rango.setMontoFinal(100000D);
		rango.setValor(5000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);
		auto.setPrecio(41000D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        auto.setGanancia(valor);


		assertEquals(5000D, auto.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango3() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Rango rango = new Rango();
		Categoria categoria = new Categoria();


		rango.setMontoInicial(50000D);
		rango.setMontoFinal(10000D);
		rango.setValor(10000D);

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(235440D);

		Double precio = auto.getPrecio();

        Double valor = rango.getValor();

        Double costo = valor + precio;

        auto.setCosto(costo);
        auto.setGanancia(valor);


		assertEquals(10000D, auto.getGanancia());
	}
}
