/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ues.anf135.datos.acceso.AgrupacionFacadeLocal;
import ues.anf135.datos.acceso.CuentaFacadeLocal;
import ues.anf135.datos.acceso.CuentaMayorFacadeLocal;
import ues.anf135.datos.acceso.DetalleEstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.ElementoFinancieroFacadeLocal;
import ues.anf135.datos.calculo.CalculosFacadeLocal;
import ues.anf135.datos.calculo.IndicadoresFacadeLocal;
import ues.anf135.datos.definiciones.Agrupacion;
import ues.anf135.datos.definiciones.Cuenta;
import ues.anf135.datos.definiciones.CuentaMayor;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;
import ues.anf135.datos.definiciones.ElementoFinanciero;

/**
 *
 * @author zywel
 */
@Named(value = "controllerEditarCuenta")
@ViewScoped
public class ControllerEditarCuenta implements Serializable {

    @EJB
    CuentaFacadeLocal cuentaFacade;

    @EJB
    DetalleEstadoFinancieroFacadeLocal defFacade;

    @EJB
    ElementoFinancieroFacadeLocal elementoFinancieroFacade;

    @EJB
    AgrupacionFacadeLocal agrupacionFacade;

    @EJB
    CuentaMayorFacadeLocal cuentaMayorFacade;

    private List<ElementoFinanciero> lstElementoFinanciero;
    private List<Agrupacion> lstAgrupacion;
    private List<CuentaMayor> lstCuentaMayor;

    private int idElementoFinanciero;
    private int idAgrupacion;
    private int idCuentaMayor;
    private int idCuenta;
    private String nombre;

    private List<Cuenta> lstCuentas;

    private Cuenta registroCuenta;
    private Cuenta seleccionado;

    public ControllerEditarCuenta() {
        this.registroCuenta = new Cuenta();
    }

    @PostConstruct
    public void obtenerActivosTotales() {

        setLstElementoFinanciero(elementoFinancieroFacade.findAll());
        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(getIdElementoFinanciero()));
        setLstCuentaMayor(cuentaMayorFacade.obtenerPorAgrupacion(getIdAgrupacion()));

        //this.generarCodigo();
        this.cambiarAgrupacion();
    }

    public void cambiarAgrupacion() {
        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(idElementoFinanciero));
        idAgrupacion = lstAgrupacion.get(0).getIdAgrupacion();
        System.out.println("idagrupacion: " + idAgrupacion);
        this.cambiarCuentaMayor();
        System.out.println("----> lista de agrupacion cambiada");
    }

    public void cambiarCuentaMayor() {
        setLstCuentaMayor(cuentaMayorFacade.obtenerPorAgrupacion(idAgrupacion));
        idCuentaMayor = lstCuentaMayor.get(0).getIdCuentaMayor();
        this.registroCuenta.setIdCuentaMayor(lstCuentaMayor.get(0));
        System.out.println("----> lista de cuenta mayor cambiada");
    }

    public String guardarCuenta() {
        this.registroCuenta.setEstado(true);
        if (!this.registroCuenta.getNombre().isEmpty() && !(this.registroCuenta.getNombre() == null)) {
            if (!cuentaFacade.crear(registroCuenta)) {
                System.out.println("Error al crear cuenta");
            }
        } else {
            System.out.println("Faltan Rellenar campos");
        }

        return "cuentas.jsf";
    }

    public void establecerListas(int idCuenta) {

        System.out.println("---> Estableciendo parametros");
        this.registroCuenta = cuentaFacade.find(idCuenta);

        setLstElementoFinanciero(elementoFinancieroFacade.findAll());
        setIdElementoFinanciero(registroCuenta.getIdCuentaMayor().getIdAgrupacion().getIdElementoFinanciero().getIdElementoFinanciero());

        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(getIdElementoFinanciero()));
        setIdAgrupacion(registroCuenta.getIdCuentaMayor().getIdAgrupacion().getIdAgrupacion());

        setLstCuentaMayor(cuentaMayorFacade.obtenerPorAgrupacion(getIdAgrupacion()));
        setIdCuentaMayor(registroCuenta.getIdCuentaMayor().getIdCuentaMayor());

    }

    /**
     * @return the lstCuentas
     */
    public List<Cuenta> getLstCuentas() {
        return lstCuentas;
    }

    /**
     * @param lstCuentas the lstCuentas to set
     */
    public void setLstCuentas(List<Cuenta> lstCuentas) {
        this.lstCuentas = lstCuentas;
    }

    /**
     * @return the registroCuenta
     */
    public Cuenta getRegistroCuenta() {
        return registroCuenta;
    }

    /**
     * @param registroCuenta the registroCuenta to set
     */
    public void setRegistroCuenta(Cuenta registroCuenta) {
        this.registroCuenta = registroCuenta;
    }

    /**
     * @return the lstElementoFinanciero
     */
    public List<ElementoFinanciero> getLstElementoFinanciero() {
        return lstElementoFinanciero;
    }

    /**
     * @param lstElementoFinanciero the lstElementoFinanciero to set
     */
    public void setLstElementoFinanciero(List<ElementoFinanciero> lstElementoFinanciero) {
        this.lstElementoFinanciero = lstElementoFinanciero;
    }

    /**
     * @return the lstAgrupacion
     */
    public List<Agrupacion> getLstAgrupacion() {
        return lstAgrupacion;
    }

    /**
     * @param lstAgrupacion the lstAgrupacion to set
     */
    public void setLstAgrupacion(List<Agrupacion> lstAgrupacion) {
        this.lstAgrupacion = lstAgrupacion;
    }

    /**
     * @return the lstCuentaMayor
     */
    public List<CuentaMayor> getLstCuentaMayor() {
        return lstCuentaMayor;
    }

    /**
     * @param lstCuentaMayor the lstCuentaMayor to set
     */
    public void setLstCuentaMayor(List<CuentaMayor> lstCuentaMayor) {
        this.lstCuentaMayor = lstCuentaMayor;
    }

    /**
     * @return the idElementoFinanciero
     */
    public int getIdElementoFinanciero() {
        return idElementoFinanciero;
    }

    /**
     * @param idElementoFinanciero the idElementoFinanciero to set
     */
    public void setIdElementoFinanciero(int idElementoFinanciero) {
        this.idElementoFinanciero = idElementoFinanciero;
    }

    /**
     * @return the idAgrupacion
     */
    public int getIdAgrupacion() {
        return idAgrupacion;
    }

    /**
     * @param idAgrupacion the idAgrupacion to set
     */
    public void setIdAgrupacion(int idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    /**
     * @return the idCuentaMayor
     */
    public int getIdCuentaMayor() {
        return idCuentaMayor;
    }

    /**
     * @param idCuentaMayor the idCuentaMayor to set
     */
    public void setIdCuentaMayor(int idCuentaMayor) {
        this.idCuentaMayor = idCuentaMayor;
    }

    /**
     * @return the idCuenta
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the seleccionado
     */
    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }

}
