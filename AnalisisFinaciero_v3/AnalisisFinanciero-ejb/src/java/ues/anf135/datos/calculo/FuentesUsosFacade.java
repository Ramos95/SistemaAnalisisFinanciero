/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.calculo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ues.anf135.datos.acceso.CuentaFacadeLocal;
import ues.anf135.datos.acceso.DetalleEstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.EmpresaFacadeLocal;
import ues.anf135.datos.definiciones.Cuenta;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;
import ues.anf135.datos.definiciones.Empresa;
import ues.anf135.datos.definiciones.FuenteUso;

/**
 *
 * @author olmos
 */
@Stateless
public class FuentesUsosFacade implements FuentesUsosFacadeLocal {

    @EJB
    DetalleEstadoFinancieroFacadeLocal defFacade;

    @EJB
    EmpresaFacadeLocal empresaFacade;

    @EJB
    CuentaFacadeLocal cuentaFacade;

    public FuentesUsosFacade() {
    }

    private FuenteUso calcularFuenteUso(FuenteUso fuenteUso) {
        try {
            if (fuenteUso != null) {
                fuenteUso.setDiferencia(fuenteUso.getSaldoFecha2() - fuenteUso.getSaldoFecha1());
                fuenteUso.setFuente(0);
                fuenteUso.setUso(0);

                //ACTIVOS = 1, PASIVOS = 2, CAPITAL = 3;
                int idAgrupacion = fuenteUso.getCuenta().getIdCuentaMayor().getIdAgrupacion().getIdElementoFinanciero().getIdElementoFinanciero();
                double diferencia = fuenteUso.getDiferencia();

                if (diferencia > 0) {
                    diferencia = Math.abs(diferencia);
                    if (idAgrupacion == 2 || idAgrupacion == 3) {
                        fuenteUso.setFuente(diferencia);
                    } else if (idAgrupacion == 1) {
                        fuenteUso.setUso(diferencia);
                    }
                } else if (diferencia < 0) {
                    diferencia = Math.abs(diferencia);
                    if (idAgrupacion == 2 || idAgrupacion == 3) {
                        fuenteUso.setUso(diferencia);
                    } else if (idAgrupacion == 1) {
                        fuenteUso.setFuente(diferencia);
                    }
                }
            }

            System.out.println("Salida calcularFuenteUso:");
            System.out.println("------------------------");
            System.out.println("cuenta: " + fuenteUso.getCuenta().getNombre());
            System.out.println("agrupación: " + fuenteUso.getCuenta().getIdCuentaMayor().getIdAgrupacion().getNombre());
            System.out.println("empresa: " + fuenteUso.getEmpresa().getNombreCorto());
            System.out.println("saldo1: " + fuenteUso.getSaldoFecha1());
            System.out.println("saldo2: " + fuenteUso.getSaldoFecha2());
            System.out.println("diff: " + fuenteUso.getDiferencia());
            System.out.println("fuente: " + fuenteUso.getFuente());
            System.out.println("uso: " + fuenteUso.getUso());
            System.out.println("------------------------");
            System.out.println("------------------------");
        } catch (Exception e) {
            System.out.println("Error... calcularFuenteUso FuentesUsosFacade");
            e.printStackTrace();
        }

        return fuenteUso;
    }

    @Override
    public List<FuenteUso> obtenerFuentesUsos(Date fechaInicio, Date fechaFin, Integer idEmpresa) {
        List<FuenteUso> salida = null;

        try {
            if (idEmpresa != null && idEmpresa.compareTo(0) > 0) {
                if (empresaFacade != null && defFacade != null) {
                    Empresa empresa = empresaFacade.find(idEmpresa);

                    if (empresa != null) {
                        Calendar calInicio = Calendar.getInstance();
                        Calendar calFin = Calendar.getInstance();
                        calInicio.setTime(fechaInicio);
                        calInicio.setTime(fechaFin);

                        if (calInicio.before(calFin) || calInicio.equals(calFin)) {
                            List<Cuenta> listaCuenta = cuentaFacade.obtenerPorBalanceGeneral();

                            List<DetalleEstadoFinanciero> listaInicio = defFacade.obtenerPorFecha(idEmpresa, fechaInicio); // Change to new method
                            List<DetalleEstadoFinanciero> listaFin = defFacade.obtenerPorFecha(idEmpresa, fechaFin); // Change to new method

                            if (listaInicio != null || !listaInicio.isEmpty() || listaFin != null || !listaFin.isEmpty()) {
                                salida = new ArrayList<>();
                                for (Cuenta cuenta : listaCuenta) {
                                    boolean contienen = false;
                                    FuenteUso fuenteUso = new FuenteUso();

                                    for (DetalleEstadoFinanciero detalle : listaInicio) {
                                        if (detalle.getCuenta().getIdCuenta() == cuenta.getIdCuenta()) {
                                            fuenteUso.setSaldoFecha1(detalle.getSaldo());
                                            contienen = true;
                                            break;
                                        }
                                    }
                                    for (DetalleEstadoFinanciero detalle : listaFin) {
                                        if (detalle.getCuenta().getIdCuenta() == cuenta.getIdCuenta()) {
                                            fuenteUso.setSaldoFecha2(detalle.getSaldo());
                                            contienen = (contienen && true);
                                            break;
                                        }
                                    }

                                    if (contienen) {
                                        fuenteUso.setCuenta(cuenta);
                                        fuenteUso.setEmpresa(empresa);
                                        fuenteUso.setDiferencia(fuenteUso.getSaldoFecha2() - fuenteUso.getSaldoFecha2());
                                        fuenteUso.setFuente(0);
                                        fuenteUso.setUso(0);
                                        fuenteUso = calcularFuenteUso(fuenteUso);
                                        salida.add(fuenteUso);
                                    }
                                }

                                System.out.println("Salida:");
                                for (FuenteUso fu : salida) {
                                    System.out.println("------------------------");
                                    System.out.println("cuenta: " + fu.getCuenta().getNombre());
                                    System.out.println("agrupación: " + fu.getCuenta().getIdCuentaMayor().getIdAgrupacion().getNombre());
                                    System.out.println("empresa: " + fu.getEmpresa().getNombreCorto());
                                    System.out.println("saldo1: " + fu.getSaldoFecha1());
                                    System.out.println("saldo2: " + fu.getSaldoFecha2());
                                    System.out.println("diff: " + fu.getDiferencia());
                                    System.out.println("fuente: " + fu.getFuente());
                                    System.out.println("uso: " + fu.getUso());
                                }
                                System.out.println("------------------------");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error... obtenerFuentesUsos FuentesUsosFacade");
            e.printStackTrace();
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }

        return salida;
    }
}
