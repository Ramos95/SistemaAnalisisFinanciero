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
@Named(value = "controllerCuenta")
@ViewScoped
public class ControllerCuenta implements Serializable {

    @EJB
    CuentaFacadeLocal cuentaFacade;

    @EJB
    IndicadoresFacadeLocal indicadores;

    @EJB
    CalculosFacadeLocal calculos;

    @EJB
    DetalleEstadoFinancieroFacadeLocal defFacade;

    @EJB
    ElementoFinancieroFacadeLocal elementoFinancieroFacade;

    @EJB
    AgrupacionFacadeLocal agrupacionFacade;

    @EJB
    CuentaMayorFacadeLocal cuentaMayorFacade;

    private List<DetalleEstadoFinanciero> lstDetalles;
    private List<DetalleEstadoFinanciero> lstActivosCorrientes;
    private List<DetalleEstadoFinanciero> lstActivosNoCorrientes;
    private List<DetalleEstadoFinanciero> lstActivosTotales;
    private List<DetalleEstadoFinanciero> lstPasivosCorrientes;
    private List<DetalleEstadoFinanciero> lstPasivosNoCorrientes;
    private List<DetalleEstadoFinanciero> lstPasivosTotales;
    private List<DetalleEstadoFinanciero> lstPatrimonio;
    private List<DetalleEstadoFinanciero> lstInventario;

    private List<ElementoFinanciero> lstElementoFinanciero;
    private List<Agrupacion> lstAgrupacion;
    private List<CuentaMayor> lstCuentaMayor;

    private int idElementoFinanciero = 1;
    private int idAgrupacion = 11;
    private int idCuentaMayor = 111;
    private int idCuenta;
    private String nombre;
    private boolean editar;

    private List<Cuenta> lstCuentas;

    double liquidezCorriente;
    double pruebaAcida;
    double capitalTrabajo;
    double nivelDependenciaInventarios;
    double numeroDiasCarteraMano;
    double rotacionCartera;
    double numeroDiasInventarioMano;
    double rotacionInventario;
    double cicloOperativo;
    double rotacionActivoTotal;
    double endeudamientoActivoTotal;
    double endeudamientoLeverage;
    double margenUtilidad;
    double roa;
    double roe;

    private Cuenta registroCuenta;
    private Cuenta seleccionado;

    public ControllerCuenta() {
        this.registroCuenta = new Cuenta();
    }

    @PostConstruct
    public void obtenerActivosTotales() {
        setLstCuentas(cuentaFacade.findAll());

        setLstElementoFinanciero(elementoFinancieroFacade.findAll());
        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(getIdElementoFinanciero()));
        setLstCuentaMayor(cuentaMayorFacade.obtenerPorAgrupacion(getIdAgrupacion()));

        System.out.println("idCuenta: " + this.idCuenta);

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

        this.generarCodigo();
        System.out.println("----> lista de cuenta mayor cambiada");
    }

    public void generarCodigo() {
        if (!editar) {
            this.registroCuenta.setIdCuenta(cuentaFacade.obtenerUltimoPorCuentaMayor(idCuentaMayor).get(0).getIdCuenta() + 1);
        }

        System.out.println("----> nuevo id: " + registroCuenta.getIdCuenta());
        System.out.println("----> lista de cuenta mayor cambiada");
    }

    public String guardarCuenta() {
        this.registroCuenta.setEstado(true);

        if (!this.registroCuenta.getNombre().isEmpty() && !(this.registroCuenta.getNombre() == null)) {
            if (!editar) {
                this.registroCuenta.setIdCuentaMayor(cuentaMayorFacade.find(idCuentaMayor));
                if (!cuentaFacade.crear(registroCuenta)) {
                    System.out.println("Error al crear cuenta");
                }
            } else if (!cuentaFacade.editar(registroCuenta)) {
                System.out.println("Error al editar cuenta");
            }
        } else {
            System.out.println("Faltan Rellenar campos");
        }

        return "cuentas.jsf";
    }

    public String establecerParametros() {
        String dir = "";
        if (seleccionado != null) {
            setIdCuenta(seleccionado.getIdCuenta());
            dir = "editar_cuenta.jsf?faces-redirect=true&includeViewParams=true";
        }
        return dir;
    }

    public void establecerCuentaParametro() {
        setEditar(true);
        registroCuenta = cuentaFacade.find(idCuenta);
        System.out.println("ifcuentamayor: " + registroCuenta.getIdCuentaMayor().getIdCuentaMayor());
        //registroCuenta.setIdCuentaMayor(cuentaMayorFacade.find(this));
    }

    /**
     * @return the lstDetalles
     */
    public List<DetalleEstadoFinanciero> getLstDetalles() {
        return lstDetalles;
    }

    /**
     * @param lstDetalles the lstDetalles to set
     */
    public void setLstDetalles(List<DetalleEstadoFinanciero> lstDetalles) {
        this.lstDetalles = lstDetalles;
    }

    /**
     * @return the lstActivosCorrientes
     */
    public List<DetalleEstadoFinanciero> getLstActivosCorrientes() {
        return lstActivosCorrientes;
    }

    /**
     * @param lstActivosCorrientes the lstActivosCorrientes to set
     */
    public void setLstActivosCorrientes(List<DetalleEstadoFinanciero> lstActivosCorrientes) {
        this.lstActivosCorrientes = lstActivosCorrientes;
    }

    /**
     * @return the lstActivosNoCorrientes
     */
    public List<DetalleEstadoFinanciero> getLstActivosNoCorrientes() {
        return lstActivosNoCorrientes;
    }

    /**
     * @param lstActivosNoCorrientes the lstActivosNoCorrientes to set
     */
    public void setLstActivosNoCorrientes(List<DetalleEstadoFinanciero> lstActivosNoCorrientes) {
        this.lstActivosNoCorrientes = lstActivosNoCorrientes;
    }

    /**
     * @return the lstActivosTotales
     */
    public List<DetalleEstadoFinanciero> getLstActivosTotales() {
        return lstActivosTotales;
    }

    /**
     * @param lstActivosTotales the lstActivosTotales to set
     */
    public void setLstActivosTotales(List<DetalleEstadoFinanciero> lstActivosTotales) {
        this.lstActivosTotales = lstActivosTotales;
    }

    /**
     * @return the lstPasivosCorrientes
     */
    public List<DetalleEstadoFinanciero> getLstPasivosCorrientes() {
        return lstPasivosCorrientes;
    }

    /**
     * @param lstPasivosCorrientes the lstPasivosCorrientes to set
     */
    public void setLstPasivosCorrientes(List<DetalleEstadoFinanciero> lstPasivosCorrientes) {
        this.lstPasivosCorrientes = lstPasivosCorrientes;
    }

    /**
     * @return the lstPasivosNoCorrientes
     */
    public List<DetalleEstadoFinanciero> getLstPasivosNoCorrientes() {
        return lstPasivosNoCorrientes;
    }

    /**
     * @param lstPasivosNoCorrientes the lstPasivosNoCorrientes to set
     */
    public void setLstPasivosNoCorrientes(List<DetalleEstadoFinanciero> lstPasivosNoCorrientes) {
        this.lstPasivosNoCorrientes = lstPasivosNoCorrientes;
    }

    /**
     * @return the lstPasivosTotales
     */
    public List<DetalleEstadoFinanciero> getLstPasivosTotales() {
        return lstPasivosTotales;
    }

    /**
     * @param lstPasivosTotales the lstPasivosTotales to set
     */
    public void setLstPasivosTotales(List<DetalleEstadoFinanciero> lstPasivosTotales) {
        this.lstPasivosTotales = lstPasivosTotales;
    }

    /**
     * @return the lstPatrimonio
     */
    public List<DetalleEstadoFinanciero> getLstPatrimonio() {
        return lstPatrimonio;
    }

    /**
     * @param lstPatrimonio the lstPatrimonio to set
     */
    public void setLstPatrimonio(List<DetalleEstadoFinanciero> lstPatrimonio) {
        this.lstPatrimonio = lstPatrimonio;
    }

    /**
     * @return the lstInventario
     */
    public List<DetalleEstadoFinanciero> getLstInventario() {
        return lstInventario;
    }

    /**
     * @param lstInventario the lstInventario to set
     */
    public void setLstInventario(List<DetalleEstadoFinanciero> lstInventario) {
        this.lstInventario = lstInventario;
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

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

}
