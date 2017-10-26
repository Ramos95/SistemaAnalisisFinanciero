/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.calculo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ues.anf135.datos.acceso.DetalleEstadoFinancieroFacadeLocal;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Stateless
public class CalculosFacade implements CalculosFacadeLocal {

    //Aqui programar todos los metodos necesarios para calcular los montos de las respectivas variables a utilizar
    public CalculosFacade() {
    }

    @EJB
    DetalleEstadoFinancieroFacadeLocal def;

    public List<DetalleEstadoFinanciero> devolverLista(List<DetalleEstadoFinanciero> lista, int fecha, int idEmpresa) {
        List<DetalleEstadoFinanciero> salida = new ArrayList<DetalleEstadoFinanciero>();
        for (DetalleEstadoFinanciero detalle : lista) {
            if (detalle.getEstadoFinanciero().getEmpresa().getIdEmpresa().equals(idEmpresa)) {
                if (detalle.getEstadoFinanciero().getFechaDesde().equals(fecha)) {
                    salida.add(detalle);
                }

            }
        }
        return salida;
    }

    public double devolverSaldo(List<DetalleEstadoFinanciero> lista, int fecha, int idEmpresa) {
        double saldo = 0;
        for (DetalleEstadoFinanciero detalle : lista) {
            if (detalle.getEstadoFinanciero().getEmpresa().getIdEmpresa().equals(idEmpresa)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                if (Integer.valueOf(sdf.format(detalle.getEstadoFinanciero().getFechaDesde())) == fecha) {
                    saldo += detalle.getSaldo();
                }
            }
        }
        return saldo;
    }

    @Override
    public double calcularActivoCorriente(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerActivosCorrientes(), fecha, idEmpresa);
    }

    @Override
    public double calcularActivoNoCorriente(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerActivosNoCorrientes(), fecha, idEmpresa);
    }

    @Override
    public double calcularActivoTotal(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerActivosTotales(), fecha, idEmpresa);
    }

    @Override
    public double calcularPasivoTotal(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerPasivosTotales(), fecha, idEmpresa);
    }

    @Override
    public double calcularPasivoCorriente(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerPasivosCorriente(), fecha, idEmpresa);
    }

    @Override
    public double calcularPasivoNoCorriente(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerPasivosNoCorriente(), fecha, idEmpresa);
    }

    @Override
    public double calcularPatrimonio(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerCapital(), fecha, idEmpresa);
    }

    @Override
    public double calcularInventario(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerInventario(), fecha, idEmpresa);
    }

    @Override
    public double calcularCaja(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerCaja(), fecha, idEmpresa);
    }

    @Override
    public double calcularBanco(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerBanco(), fecha, idEmpresa);
    }

    @Override
    public double calcularCuentasCobrar(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerCuentasPorCobrar(), fecha, idEmpresa);
    }

    @Override
    public double calcularValoresRealizables(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerValoresRealizables(), fecha, idEmpresa);
    }

    @Override
    public double calcularVentasNetas(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerVentasNetas(), fecha, idEmpresa);
    }

    @Override
    public double calcularMercanciaTransito(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerMercanciaTransito(), fecha, idEmpresa);
    }

    @Override
    public double calcularCostosNetos(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerCostosNetos(), fecha, idEmpresa);
    }

    @Override
    public double calcularUtilidadNeta(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerUtilidadNeta(), fecha, idEmpresa);
    }

    @Override
    public double calcularUtilidadBruta(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerUtilidadBruta(), fecha, idEmpresa);
    }

    @Override
    public double calcularUtilidadOperativa(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerUtilidadOperativa(), fecha, idEmpresa);
    }

    @Override
    public double calcularUtilidadAntesImpuestosReserva(int fecha, int idEmpresa) {
        return devolverSaldo(def.obtenerUtilidadAntesImpuestoReserva(), fecha, idEmpresa);
    }
}
