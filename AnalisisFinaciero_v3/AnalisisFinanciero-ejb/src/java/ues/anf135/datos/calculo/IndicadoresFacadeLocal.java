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
public interface IndicadoresFacadeLocal {

    //Liquidez
    double liquidezCorriente(int idEmpresa, int fecha);

    double pruebaAcida(int idEmpresa, int fecha);

    double capitalTrabajo(int idEmpresa, int fecha);

    double nivelDependenciaInventarios(int idEmpresa, int fecha);

    //Actividad
    double numeroDiasCarteraMano(int idEmpresa, int fecha);

    double rotacionCartera(int idEmpresa, int fecha);

    //double numeroDiasInventarioMano(double inventario, double ventasNetas);
    double numeroDiasInventarioMano(int idEmpresa, int fecha);

    double rotacionInventario(int idEmpresa, int fecha);

    double cicloOperativo(int idEmpresa, int fecha);

    double rotacionActivoTotal(int idEmpresa, int fecha);

    //Endeudamiento
    double endeudamientoActivoTotal(int idEmpresa, int fecha);

    double endeudamientoLeverage(int idEmpresa, int fecha);

    //Rentabilidad
    double margenUtilidadNeta(int idEmpresa, int fecha);

    double margenUtilidadBruta(int idEmpresa, int fecha);

    double margenUtilidadOperativa(int idEmpresa, int fecha);

    double margenUtilidadAntesImpuestoReserva(int idEmpresa, int fecha);

    double roa(int idEmpresa, int fecha);
    
    double roaOperativo(int idEmpresa, int fecha);

    double roe(int idEmpresa, int fecha);
}
