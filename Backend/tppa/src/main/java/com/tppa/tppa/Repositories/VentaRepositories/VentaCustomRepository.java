package com.tppa.tppa.Repositories.VentaRepositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.tppa.tppa.Models.Venta;
import com.tppa.tppa.Models.ReportesModels.DetalleVentasPorEmpleado;
import com.tppa.tppa.Models.ReportesModels.GananaciaPorMarca;
import com.tppa.tppa.Models.ReportesModels.GananciaPorCategoria;
import com.tppa.tppa.Models.ReportesModels.GananciaPorEmpleado;
import com.tppa.tppa.Models.ReportesModels.GananciaPorModeloYMarca;
import com.tppa.tppa.Models.ReportesModels.GananciaYCantidadEnPeriodo;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaRequest;
import com.tppa.tppa.Models.Requests.BusquedaAvanzadaV2Request;

@Repository
public class VentaCustomRepository {
    EntityManager em;

    public VentaCustomRepository(EntityManager em){
        this.em = em;
    }

    public List<Venta> busquedaAvanzada(BusquedaAvanzadaV2Request bar) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<Venta> cq = cb.createQuery(Venta.class);
        final Root<Venta> root = cq.from(Venta.class);

        Set<Predicate> predicates = new HashSet<>(6);

        predicates.add(cb.between(root.get("fechaVenta"),bar.getFechaDesde(),bar.getFechaHasta()));
        

        if(bar.getMontoInicial() !=0 && bar.getMontoFinal() !=0){
            predicates.add(cb.between(root.get("costo"),bar.getMontoInicial(),bar.getMontoFinal()));
        }

        if (bar.getIdModelo() != 0) {
            predicates.add(cb.equal(root.get("auto").get("modelo").get("id"), bar.getIdModelo()));
        }

        if (bar.getIdMarca() != 0) {
            predicates.add(cb.equal(root.get("auto").get("modelo").get("marca").get("id"), bar.getIdMarca()));
        }

        if (bar.getIdCiente() != 0) {
            predicates.add(cb.equal(root.get("cliente").get("id"), bar.getIdCiente()));
        }

        if (bar.getIdVendedor() != 0) {
            predicates.add(cb.equal(root.get("vendedor").get("id"), bar.getIdVendedor()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return em.createQuery(cq).getResultList();
    }

    public List<Venta> obtenerPorRangoDeCostosYFechas(@RequestBody BusquedaAvanzadaRequest bar) 
    {
        String CONSULTA = "SELECT venta FROM Venta venta WHERE venta.costo > :montoInicial AND venta.costo < :montoFinal AND  venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        @SuppressWarnings("unchecked")
        List<Venta> registros = em.createQuery(CONSULTA)
        .setParameter("montoInicial", bar.getMontoInicial())
        .setParameter("montoFinal", bar.getMontoFinal())
        .setParameter("fechaDesde", bar.getFechaDesde())
        .setParameter("fechaHasta", bar.getFechaHasta())
        .getResultList();

        em.close();
        return registros;
    }

    public List<Venta> obtenerPorRangoDeCostos(Double montoInicial, Double montoFinal) {
        String CONSULTA = "SELECT venta FROM Venta venta WHERE venta.costo > :montoInicial AND venta.costo < :montoFinal ";
        @SuppressWarnings("unchecked")
        List<Venta> registros = em.createQuery(CONSULTA)
        .setParameter("montoInicial", montoInicial)
        .setParameter("montoFinal", montoFinal)
        .getResultList();

        em.close();
        return registros;
    }

    public List<Object> getReporteCantidadYGananciaPorMarca(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.modelo.marca.nombreMarca as marca FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY marca";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananaciaPorMarca gpm = new GananaciaPorMarca();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var marca = obj[2].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setMarca(marca);
            
            test.add(gpm);
        }


        em.close();
        return test;
    }

    public List<Object> getReporteCantidadYGananciaPorMarcaYModelo(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.modelo.marca.nombreMarca as marca, venta.auto.modelo.nombreModelo as modelo FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY modelo,marca";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorModeloYMarca gpm = new GananciaPorModeloYMarca();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var marca = obj[2].toString();
            var modelo = obj[3].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setMarca(marca);
            gpm.setModelo(modelo);
            
            test.add(gpm);
        }

        em.close();
        return test;
    }

    public List<Object> getReporteCantidadYGananciaPorEmpleado(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.empleado.nombre as empleadoNombre, venta.empleado.apellido as empleadoApellido FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY empleadoNombre, empleadoApellido";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorEmpleado gpm = new GananciaPorEmpleado();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var empleadoNombre = obj[2].toString();
            var empleadoApellido = obj[3].toString();


            gpm.setCantidadVentas(cantidad);
            gpm.setCosto(costo);
            gpm.setEmpleadoNombre(empleadoNombre);
            gpm.setEmpleadoApellido(empleadoApellido);
            
            test.add(gpm);
        }

        em.close();
        return test;
    }

    public List<Object> getReporteVentasPorCategoria(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as costo, venta.auto.pais.categoria.nombreCategoria as categoria FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY categoria";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            GananciaPorCategoria gpc = new GananciaPorCategoria();

            var cantidad = obj[0].toString();
            var costo = obj[1].toString();
            var categoria = obj[2].toString();


            gpc.setCantidadVentas(cantidad);
            gpc.setCosto(costo);
            gpc.setCategoria(categoria);
            
            test.add(gpc);
        }

        em.close();
        return test;
    }

    public List<Object> getReporteDetalleVentasPorEmpleado(String fechaDesde, String fechaHasta) {        
        String CONSULTA = "SELECT COUNT(venta) as cantidadVentas, SUM(venta.costo) as ganancia, venta.auto.modelo.nombreModelo as modelo , venta.auto.modelo.marca.nombreMarca as marca, venta.auto.pais.categoria.nombreCategoria as categoria, venta.cliente.nombre as clienteNombre, venta.cliente.apellido as clienteApellido ,venta.empleado.nombre as empleadoNombre, venta.empleado.apellido as empleadoApellido FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta GROUP BY modelo,marca,categoria,clienteNombre,clienteApellido,empleadoNombre,empleadoApellido";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta)
        .getResultList();

        List<Object> test = new ArrayList<>();

        for(int i = 0; i < registros.size(); i++ ){
            Object[] obj = (Object[])registros.get(i);
            DetalleVentasPorEmpleado dvpe = new DetalleVentasPorEmpleado();

            var cantidad = obj[0].toString();
            var ganancia = obj[1].toString();
            var modelo = obj[2].toString();
            var marca = obj[3].toString();
            var categoria = obj[4].toString();
            var nombreCliente = obj[5].toString();
            var apellidoCliente = obj[6].toString();
            var nombreVendedor = obj[7].toString();
            var apellidoVendedor = obj[8].toString();
            
            
            dvpe.setCantidadVentas(cantidad);
            dvpe.setGanancia(ganancia);
            dvpe.setModelo(modelo);
            dvpe.setMarca(marca);
            dvpe.setCategoria(categoria);
            dvpe.setNombreCliente(nombreCliente);
            dvpe.setApellidoCliente(apellidoCliente);
            dvpe.setNombreVendedor(nombreVendedor);
            dvpe.setApellidoVendedor(apellidoVendedor);
            
            test.add(dvpe);
        }
        

        em.close();
        return test;
    }

    public Object obtenerGananciaTotal(String fechaDesde, String fechaHasta) {
        String CONSULTA = "SELECT COUNT(venta), SUM(venta.costo) as costo FROM Venta venta WHERE venta.fechaVenta > :fechaDesde AND venta.fechaVenta < :fechaHasta";
        var registros = em.createQuery(CONSULTA)
        .setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta).getSingleResult();

        Object[] obj = (Object[])registros;
        GananciaYCantidadEnPeriodo gpc = new GananciaYCantidadEnPeriodo();

        if(obj[0]!= null && obj[1] != null){   //No se itera porque se devuelve un solo registro.
            var cantidad = obj[0].toString();
            var total = obj[1].toString();

            gpc.setCantidadVentas(cantidad);
            gpc.setTotal(total);
        }else{
            gpc = null; //Sino se encuentran resultado, devolver un objeto nulo.
        }
        
        em.close();
        return gpc;
    }
}
