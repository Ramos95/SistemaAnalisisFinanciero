/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.calculo;

import javax.ejb.Local;

/**
 *
 * @author zywel
 */
@Local
public interface CalculosFacadeLocal {

    double calcularActivoCorriente(int fecha, int idEmpresa);
    double calcularActivoNoCorriente(int fecha, int idEmpresa);
    double calcularActivoTotal(int fecha, int idEmpresa);
    
    double calcularPasivoTotal(int fecha, int idEmpresa);
    double calcularPasivoCorriente(int fecha, int idEmpresa);
    double calcularPasivoNoCorriente(int fecha, int idEmpresa);
    
    double calcularPatrimonio(int fecha, int idEmpresa);
    double calcularInventario(int fecha, int idEmpresa);
    double calcularCaja(int fecha, int idEmpresa);
    double calcularBanco(int fecha, int idEmpresa);
    double calcularCuentasCobrar(int fecha, int idEmpresa);
    double calcularValoresRealizables(int fecha, int idEmpresa);
    double calcularMercanciaTransito(int fecha, int idEmpresa);

    double calcularVentasNetas(int fecha, int idEmpresa);
    double calcularCostosNetos(int fecha, int idEmpresa);
    double calcularUtilidadNeta(int fecha, int idEmpresa);
    double calcularUtilidadBruta(int fecha, int idEmpresa);
    double calcularUtilidadOperativa(int fecha, int idEmpresa);
    double calcularUtilidadAntesImpuestosReserva(int fecha, int idEmpresa);
}
