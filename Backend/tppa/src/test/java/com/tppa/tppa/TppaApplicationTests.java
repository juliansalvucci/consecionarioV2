package com.tppa.tppa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.tppa.tppa.Models.Auto;
import com.tppa.tppa.Models.Categoria;
import com.tppa.tppa.Models.Pais;
import com.tppa.tppa.Models.Rango;
import com.tppa.tppa.Services.RangoService;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoAmericaExtranjero;
import com.tppa.tppa.strategies.costoStrategies.EstrategiaCostoNacional;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TppaApplicationTests {

	@Autowired 
    RangoService rangoService;

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
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(40000D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(45000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango2() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(70000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango3() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(110000D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(160000D, a.getCosto());
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
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(110000D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(50000D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango2() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(41000D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(5000D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango3() {

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiaCostoNacional ecn = new EstrategiaCostoNacional();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(235440D);

		Rango rango = rangoService.obtenerPorPrecio(auto.getPrecio());

		var a = ecn.calcularCosto(auto, rango);
        
		assertEquals(50000D, a.getGanancia());
	}

}
