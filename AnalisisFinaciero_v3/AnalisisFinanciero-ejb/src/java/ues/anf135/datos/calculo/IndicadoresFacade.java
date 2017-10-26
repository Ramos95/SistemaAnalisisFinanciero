/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.calculo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author zywel
 */
@Stateless
public class IndicadoresFacade implements IndicadoresFacadeLocal {

    @EJB
    CalculosFacadeLocal calculos;

    //Aqui programar todos los metodos necesarios para calcular lon indicadores
    public IndicadoresFacade() {
    }

    @Override
    public double liquidezCorriente(int idEmpresa, int fecha) {
        return (calculos.calcularActivoCorriente(fecha, idEmpresa) / calculos.calcularPasivoCorriente(fecha, idEmpresa));
    }

    @Override
    public double pruebaAcida(int idEmpresa, int fecha) {
        return (calculos.calcularActivoCorriente(fecha, idEmpresa) - calculos.calcularInventario(fecha, idEmpresa) + calculos.calcularMercanciaTransito(fecha, idEmpresa)) / calculos.calcularPasivoCorriente(fecha, idEmpresa);
    }

    @Override
    public double capitalTrabajo(int idEmpresa, int fecha) {
        return calculos.calcularActivoCorriente(fecha, idEmpresa) - calculos.calcularPasivoCorriente(fecha, idEmpresa);
    }

    @Override
    public double nivelDependenciaInventarios(int idEmpresa, int fecha) {
        return (calculos.calcularPasivoCorriente(fecha, idEmpresa)
                - calculos.calcularCaja(fecha, idEmpresa)
                - calculos.calcularBanco(fecha, idEmpresa)
                - calculos.calcularValoresRealizables(fecha, idEmpresa)
                - calculos.calcularCuentasCobrar(fecha, idEmpresa)) / (calculos.calcularInventario(fecha, idEmpresa)
                - calculos.calcularMercanciaTransito(fecha, idEmpresa));
    }

    @Override
    public double numeroDiasCarteraMano(int idEmpresa, int fecha) {
        return (calculos.calcularCuentasCobrar(fecha, idEmpresa) * 360) / calculos.calcularVentasNetas(fecha, idEmpresa);
    }

    @Override
    public double rotacionCartera(int idEmpresa, int fecha) {
        return 360 / this.numeroDiasCarteraMano(idEmpresa, fecha);
    }

    @Override
    public double numeroDiasInventarioMano(int idEmpresa, int fecha) {
        return ((calculos.calcularInventario(fecha, idEmpresa)
                - calculos.calcularMercanciaTransito(fecha, idEmpresa)) * 360) / calculos.calcularCostosNetos(fecha, idEmpresa);
    }

    @Override
    public double rotacionInventario(int idEmpresa, int fecha) {
        return 360 / this.numeroDiasInventarioMano(idEmpresa, fecha);
    }

    @Override
    public double cicloOperativo(int idEmpresa, int fecha) {
        return this.numeroDiasInventarioMano(idEmpresa, fecha) + this.numeroDiasCarteraMano(idEmpresa, fecha);
    }

    @Override
    public double rotacionActivoTotal(int idEmpresa, int fecha) {
        return calculos.calcularVentasNetas(fecha, idEmpresa) / calculos.calcularActivoTotal(fecha, idEmpresa);
    }

    @Override
    public double endeudamientoActivoTotal(int idEmpresa, int fecha) {
        return calculos.calcularPasivoTotal(fecha, idEmpresa) / calculos.calcularActivoTotal(fecha, idEmpresa);
    }

    @Override
    public double endeudamientoLeverage(int idEmpresa, int fecha) {
        return calculos.calcularPasivoTotal(fecha, idEmpresa) / calculos.calcularPatrimonio(fecha, idEmpresa);
    }

    @Override
    public double margenUtilidadNeta(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadNeta(fecha, idEmpresa) / calculos.calcularVentasNetas(fecha, idEmpresa);
    }

    @Override
    public double roa(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadNeta(fecha, idEmpresa) / calculos.calcularActivoTotal(fecha, idEmpresa);
    }

    @Override
    public double roe(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadNeta(fecha, idEmpresa) / calculos.calcularPatrimonio(fecha, idEmpresa);
    }

    @Override
    public double margenUtilidadBruta(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadBruta(fecha, idEmpresa) / calculos.calcularVentasNetas(fecha, idEmpresa);
    }

    @Override
    public double margenUtilidadOperativa(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadOperativa(fecha, idEmpresa) / calculos.calcularVentasNetas(fecha, idEmpresa);
    }

    @Override
    public double margenUtilidadAntesImpuestoReserva(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadAntesImpuestosReserva(fecha, idEmpresa) / calculos.calcularVentasNetas(fecha, idEmpresa);
    }

    @Override
    public double roaOperativo(int idEmpresa, int fecha) {
        return calculos.calcularUtilidadOperativa(fecha, idEmpresa) / calculos.calcularActivoTotal(fecha, idEmpresa);
    }
}
