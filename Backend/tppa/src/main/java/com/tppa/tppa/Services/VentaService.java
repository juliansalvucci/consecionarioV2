package com.tppa.tppa.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;
import com.tppa.tppa.Repositories.VentaRepositories.VentaCustomRepository;
import com.tppa.tppa.Repositories.VentaRepositories.VentaRepository;

@Service
public class VentaService
{
    @Autowired
    VentaRepository repository;
    @Autowired
    VentaCustomRepository customRepository;
    
    public ArrayList<Venta> obtener()
    {
        return (ArrayList<Venta>) repository.findAll();
    }


    public Venta guardar(Venta venta)
    {
        return repository.save(venta);
    }

    public Optional<Venta> obtenerPorId(Long id)
    {
        return repository.findById(id);
    }  

    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        return customRepository.obtenerPorRangoDeCostosYFechas(bar);
    }
}
