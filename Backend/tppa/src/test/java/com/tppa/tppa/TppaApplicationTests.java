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
import com.tppa.tppa.Services.RangoService;
import com.tppa.tppa.strategies.costoStrategies.EstrategiasCostoDefinition;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TppaApplicationTests 
{

	@Autowired 
    RangoService rangoService;

	@Test
	void testCalculoImpuestoExtranjeroYAmerica() 
	{
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(253000D, a.getCosto());	
	}

	@Test
	void testCalculoImpuestoNacionalRango1() 
	{

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(40000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(45000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango2() 
	{
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(60000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(70000D, a.getCosto());
	}

	@Test
	void testCalculoImpuestoNacionalRango3() 
	{
		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(0);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(110000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(160000D, a.getCosto());
	}

	//GANANCIA

	@Test
	void testCalculoGananciaImpuestoExtranjeroYAmerica() 
	{

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(230000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(23000D, a.getGanancia());	
	}

    @Test
	void testCalculoGananciaImpuestoNacionalRango1() 
	{

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(110000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(11000D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango2() 
	{

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(41000D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(4100D, a.getGanancia());
	}

	@Test
	void testCalculoGananciaImpuestoNacionalRango3() 
	{

		Auto auto = new Auto();
		Pais pais = new Pais();
		Categoria categoria = new Categoria();
		EstrategiasCostoDefinition ecd = new EstrategiasCostoDefinition();

		pais.setNombrePais("testPais");
		categoria.setPorcentaje(10);
		pais.setCategoria(categoria);
		auto.setPais(pais);

		auto.setPrecio(235440D);

		var a = ecd.calcularCosto(auto);
        
		assertEquals(23544D, a.getGanancia());
	}
}
