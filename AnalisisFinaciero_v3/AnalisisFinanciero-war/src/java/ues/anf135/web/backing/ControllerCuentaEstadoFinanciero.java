/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ues.anf135.datos.acceso.AbstractFacadeInterface;
import ues.anf135.datos.acceso.AgrupacionFacadeLocal;
import ues.anf135.datos.acceso.CuentaFacadeLocal;
import ues.anf135.datos.acceso.DetalleEstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.ElementoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.EmpresaFacadeLocal;
import ues.anf135.datos.acceso.EstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.TipoEstadoFinancieroFacadeLocal;
import ues.anf135.datos.calculo.CalculosFacadeLocal;
import ues.anf135.datos.definiciones.Agrupacion;
import ues.anf135.datos.definiciones.Cuenta;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;
import ues.anf135.datos.definiciones.ElementoFinanciero;
import ues.anf135.datos.definiciones.Empresa;
import ues.anf135.datos.definiciones.EstadoFinanciero;
import ues.anf135.datos.definiciones.EstadoFinancieroPK;
import ues.anf135.datos.definiciones.TipoEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Named(value = "controllerCuentaEstadoFinanciero")
@ViewScoped
public class ControllerCuentaEstadoFinanciero extends DefaultGenerator<EstadoFinanciero> implements Serializable {

    @EJB
    EstadoFinancieroFacadeLocal efFacade;

    @EJB
    EmpresaFacadeLocal empresaFacade;

    @EJB
    TipoEstadoFinancieroFacadeLocal tefFacade;

    @EJB
    ElementoFinancieroFacadeLocal elementoFinancieroFacade;

    @EJB
    DetalleEstadoFinancieroFacadeLocal defFacade;

    @EJB
    AgrupacionFacadeLocal agrupacionFacade;

    @EJB
    CuentaFacadeLocal cuentaFacade;

    @EJB
    CalculosFacadeLocal calculosFacade;

    private List<ElementoFinanciero> lstElementoFinanciero;
    private List<Agrupacion> lstAgrupacion;
    private List<Cuenta> lstCuenta;
    private List<Cuenta> lstCuentasFiltrado;

    private List<DetalleEstadoFinanciero> lstCuentasEstadoResultados;
    private List<DetalleEstadoFinanciero> lstCuentasBalanceGeneral;

    private Cuenta registroCuenta;
    private int idEmpresa;
    private int idEstadoFinanciero;
    private int anioEstadoFinaciero;
    private int tipoSaldo = 0;
    private double saldo;
    private String nombreCuenta;
    private double activosTotales;
    private double pasivoTotales;
    private double patrimonio;

    private Empresa empresa;
    private int idElementoFinanciero = 4;
    private int idAgrupacion = 41;
    private int idCuenta;
    private String fecha;
    private EstadoFinanciero estadoFinanciero;

    private int tabActual = 0;

    public ControllerCuentaEstadoFinanciero() {
        registro = new EstadoFinanciero();
        lstCuentasEstadoResultados = new ArrayList<DetalleEstadoFinanciero>();
        lstCuentasBalanceGeneral = new ArrayList<DetalleEstadoFinanciero>();
        lstCuentasFiltrado = new ArrayList<Cuenta>();
        registroCuenta = new Cuenta();
    }

    @PostConstruct
    public void actualizarDatos() {
        setLstElementoFinanciero(elementoFinancieroFacade.obtenerPorEstadoResultados());
        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(idElementoFinanciero));
        setLstCuenta(cuentaFacade.obtenerPorAgrupacion(idAgrupacion));

        /*if (tabActual == 0) {
            setLstCuenta(cuentaFacade.obtenerPorEstadoResultado());
        } else {
            setLstCuenta(cuentaFacade.obtenerPorBalanceGeneral());
        }
        
        for (Cuenta c : getLstCuenta()) {
            this.lstCuentasAutocompletado.add(c.getNombre());
        }*/
    }

    public void cargarListasEstadosFinancieros() {
        System.out.println("---> idestadofinniero antes: " + idEstadoFinanciero);
        System.out.println("res: " + idEstadoFinanciero % 2);

        if ((lstCuentasBalanceGeneral == null || lstCuentasBalanceGeneral.isEmpty()) && (lstCuentasEstadoResultados.isEmpty() || lstCuentasEstadoResultados == null)) {
            if ((idEstadoFinanciero % 2) != 0) {
                idEstadoFinanciero += 1;
            }
            System.out.println("---> idestadofinniero dspues: " + idEstadoFinanciero);

            setLstCuentasBalanceGeneral(defFacade.obtenerPorEmpresaEstadoFinanciero(idEmpresa, idEstadoFinanciero - 1));
            setLstCuentasEstadoResultados(defFacade.obtenerPorEmpresaEstadoFinanciero(idEmpresa, idEstadoFinanciero));
        }
    }

    public void mostrarParametros(int idEmpresa, int idEstadoFinanciero, int anioEstadoFinanciero) {
        setIdEmpresa(idEmpresa);
        setIdEstadoFinanciero(idEstadoFinanciero);
        setAnioEstadoFinaciero(anioEstadoFinanciero);
        System.out.println("est finan: " + this.idEstadoFinanciero);
        System.out.println("empresa: " + this.idEmpresa);
        System.err.println("pppppanio estado financiero: " + anioEstadoFinaciero);

        this.cargarListasEstadosFinancieros();
    }

    public void acutalizarCuentasFiltrado() {
        if (tabActual == 0) {
            setLstCuenta(cuentaFacade.obtenerPorEstadoResultado());
        } else {
            setLstCuenta(cuentaFacade.obtenerPorBalanceGeneral());

        }
    }

    public List<Cuenta> obtenerCuentas(String texto) {
        List<Cuenta> salida = new ArrayList<>();

        try {
            if (texto != null && !texto.isEmpty()) {
                if (tabActual == 0) {
                    setLstCuenta(cuentaFacade.obtenerPorEstadoResultado());
                } else {
                    setLstCuenta(cuentaFacade.obtenerPorBalanceGeneral());
                }

                System.out.println("---> Dentro de obtenerCuentas");
                System.out.println("---> " + tabActual);

                if (getLstCuenta() != null && !getLstCuenta().isEmpty()) {
                    for (Cuenta cuenta : getLstCuenta()) {
                        if (cuenta.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                            salida.add(cuenta);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error obtenerCuentas AutoComplete");
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }

        return salida;
    }

    public void cambiarTab() {
        switch (tabActual) {
            case 1:
                System.out.println("cambiando tab a 1");
                this.tabActual = 1;
                break;

            case 0:
                System.out.println("cambiando tab a 0");
                this.tabActual = 0;
                break;

            default:
                System.out.println("Error al cambiar de Tab");
                break;
        }
    }

    public void mostrarRegistroCuenta() {

        System.out.println("----> registro cuenta: " + registroCuenta.getNombre());
    }

    public void cambiarElementosFinancieros() {

        switch (tabActual) {
            case 0:
                setLstElementoFinanciero(elementoFinancieroFacade.obtenerPorEstadoResultados());
                idElementoFinanciero = 4;
                break;

            case 1:
                setLstElementoFinanciero(elementoFinancieroFacade.obtenerPorBalanceGeneral());
                idElementoFinanciero = 1;
                break;

            default:
                System.out.println("Error al cargar listas desplegables");
                break;
        }
        cambiarAgrupacion();
    }

    // aqui llamo los totales de cada rubro
    public double activoTotal() {
        System.out.println("estoy en activoTotal" + anioEstadoFinaciero + " " + idEmpresa);
        activosTotales = calculosFacade.calcularActivoTotal(anioEstadoFinaciero, idEmpresa);

        return activosTotales;
    }

    public double pasivoTotal() {
        pasivoTotales = calculosFacade.calcularPasivoTotal(anioEstadoFinaciero, idEmpresa);
        return pasivoTotales;
    }

    public double patrimonioTotal() {
        patrimonio = calculosFacade.calcularPatrimonio(anioEstadoFinaciero, idEmpresa);
        return patrimonio;
    }

    public double patrimonioMaspasivo() {
        double suma = pasivoTotal() + patrimonioTotal();

        return suma;
    }

    public void cambiarAgrupacion() {
        setLstAgrupacion(agrupacionFacade.obtenerPorElementoFinanciero(idElementoFinanciero));
        idAgrupacion = lstAgrupacion.get(0).getIdAgrupacion();
        cambiarTablaCuentas();
        System.out.println("----> lista de agrupacion cambiada");
    }

    public void cambiarTablaCuentas() {
        setLstCuenta(cuentaFacade.obtenerPorAgrupacion(idAgrupacion));
        System.out.println("----> tabla actualizada");
    }

    public void agregarCuenta() {
        System.out.println("----> dentro de agregarCuenta");
        System.out.println("----> " + this.registroCuenta.getNombre());

        if (this.getTipoSaldo() == 1) {
            this.setSaldo(this.getSaldo() * -1);
        }

        for (DetalleEstadoFinanciero reg : lstCuentasEstadoResultados) {
            if (Objects.equals(registroCuenta.getIdCuenta(), reg.getCuenta().getIdCuenta())) {
                System.out.println("registroCuenta.getIdCuenta(): " + registroCuenta.getIdCuenta());
                System.out.println("reg.getCuenta().getIdCuenta(): " + reg.getCuenta().getIdCuenta());
                reg.setSaldo(reg.getSaldo() + getSaldo());
                return;
            }
        }

        for (DetalleEstadoFinanciero reg : lstCuentasBalanceGeneral) {
            if (Objects.equals(registroCuenta.getIdCuenta(), reg.getCuenta().getIdCuenta())) {
                reg.setSaldo(reg.getSaldo() + getSaldo());
                return;
            }
        }

        //aqui modificar el tab +1 por una variable del numero de estado (incremental 6,7,8,9...., enviado por parametros)
        DetalleEstadoFinanciero def;

        if (tabActual == 0) {
            System.out.println("----> dentro de agregarCuenta, agregando a ER");
            def = new DetalleEstadoFinanciero(idEstadoFinanciero, idEmpresa, registroCuenta.getIdCuenta());
            lstCuentasEstadoResultados.add(0, def);
        } else {
            System.out.println("----> dentro de agregarCuenta, agregando a BG");
            def = new DetalleEstadoFinanciero(idEstadoFinanciero - 1, idEmpresa, registroCuenta.getIdCuenta());
            lstCuentasBalanceGeneral.add(0, def);
        }

        def.setCuenta(registroCuenta);
        def.setSaldo(getSaldo());

    }

    public String guardarDatos() {
        for (DetalleEstadoFinanciero reg : lstCuentasEstadoResultados) {
            if (!defFacade.crear(reg)) {
                System.out.println("Error al crear cuenta de ER");
            }
        }

        for (DetalleEstadoFinanciero reg : lstCuentasBalanceGeneral) {
            if (!defFacade.crear(reg)) {
                System.out.println("Error al crear cuenta de BG");
            }
        }
        return "estado_financiero.jsf";
    }

    public String actualizarDatosDetalleEstadoFinanciero() {
        for (DetalleEstadoFinanciero reg : lstCuentasEstadoResultados) {
            if (!defFacade.editar(reg)) {
                if (!defFacade.crear(reg)) {
                    System.out.println("Error al crear cuenta de ER");
                }
            }
        }

        for (DetalleEstadoFinanciero reg : lstCuentasBalanceGeneral) {
            if (!defFacade.editar(reg)) {
                if (!defFacade.crear(reg)) {
                    System.out.println("Error al crear cuenta de BG");
                }
            }
        }
        return "estado_financiero.jsf";
    }

    @Override
    protected AbstractFacadeInterface<EstadoFinanciero> getFacade() {
        return this.efFacade;
    }

    @Override
    protected void generarListas() {
        /*if (this.efFacade != null || !this.lstEstadoFinanciero.isEmpty()) {
            try {
                if (this.efFacade != null) {
                    this.lstEstadoFinanciero = efFacade.findAll();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                if (lstEstadoFinanciero == null) {
                    this.lstEstadoFinanciero = new ArrayList<>();
                }
            }
        }*/
    }

    @Override
    public LazyDataModel<EstadoFinanciero> getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(LazyDataModel<EstadoFinanciero> modelo) {
        this.modelo = modelo;
    }

    @Override
    protected void generarModeloTabla() {
        this.modelo = new LazyDataModel<EstadoFinanciero>() {

            @Override
            public Object getRowKey(EstadoFinanciero object) {
                if (object != null) {
                    return object.getEstadoFinancieroPK();
                }
                return null;
            }

            @Override
            public EstadoFinanciero getRowData(String rowKey) {
                if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                    try {
                        String buscado = rowKey; //String buscado = new String(rowKey);
                        for (EstadoFinanciero reg : ((List<EstadoFinanciero>) getWrappedData())) {
                            if (reg.getEstadoFinancieroPK().toString().compareTo(buscado) == 0) {
                                return reg;
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
                return null;
            }

            @Override
            public List<EstadoFinanciero> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                try {
                    if (efFacade != null) {
                        if (filters != null && !filters.isEmpty()) {
                            salida = efFacade.findByDataFilters(filters, first, tamanioPagina);
                            this.setRowCount(efFacade.countByDataFilters(filters));
                        } else {
                            salida = efFacade.findRango(first, tamanioPagina);
                            this.setRowCount(efFacade.count());
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
                return salida;
            }

        };
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estadoFinanciero
     */
    public EstadoFinanciero getEstadoFinanciero() {
        return estadoFinanciero;
    }

    /**
     * @param estadoFinanciero the estadoFinanciero to set
     */
    public void setEstadoFinanciero(EstadoFinanciero estadoFinanciero) {
        this.estadoFinanciero = estadoFinanciero;
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
     * @return the lstCuenta
     */
    public List<Cuenta> getLstCuenta() {
        return lstCuenta;
    }

    /**
     * @param lstCuenta the lstCuenta to set
     */
    public void setLstCuenta(List<Cuenta> lstCuenta) {
        this.lstCuenta = lstCuenta;
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
     * @return the tabActual
     */
    public int getTabActual() {
        return tabActual;
    }

    /**
     * @param tabActual the tabActual to set
     */
    public void setTabActual(int tabActual) {
        this.tabActual = tabActual;
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
     * @return the lstCuentasEstadoResultados
     */
    public List<DetalleEstadoFinanciero> getLstCuentasEstadoResultados() {
        return lstCuentasEstadoResultados;
    }

    /**
     * @param lstCuentasEstadoResultados the lstCuentasEstadoResultados to set
     */
    public void setLstCuentasEstadoResultados(List<DetalleEstadoFinanciero> lstCuentasEstadoResultados) {
        this.lstCuentasEstadoResultados = lstCuentasEstadoResultados;
    }

    /**
     * @return the lstCuentasBalanceGeneral
     */
    public List<DetalleEstadoFinanciero> getLstCuentasBalanceGeneral() {
        return lstCuentasBalanceGeneral;
    }

    /**
     * @param lstCuentasBalanceGeneral the lstCuentasBalanceGeneral to set
     */
    public void setLstCuentasBalanceGeneral(List<DetalleEstadoFinanciero> lstCuentasBalanceGeneral) {
        this.lstCuentasBalanceGeneral = lstCuentasBalanceGeneral;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the nombreCuenta
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    /**
     * @param nombreCuenta the nombreCuenta to set
     */
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    /**
     * @return the lstCuentasFiltrado
     */
    public List<Cuenta> getLstCuentasFiltrado() {
        return lstCuentasFiltrado;
    }

    /**
     * @param lstCuentasFiltrado the lstCuentasFiltrado to set
     */
    public void setLstCuentasFiltrado(List<Cuenta> lstCuentasFiltrado) {
        this.lstCuentasFiltrado = lstCuentasFiltrado;
    }

    /**
     * @return the tipoSaldo
     */
    public int getTipoSaldo() {
        return tipoSaldo;
    }

    /**
     * @param tipoSaldo the tipoSaldo to set
     */
    public void setTipoSaldo(int tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }

    /**
     * @return the idEstadoFinanciero
     */
    public int getIdEstadoFinanciero() {
        return idEstadoFinanciero;
    }

    /**
     * @param idEstadoFinanciero the idEstadoFinanciero to set
     */
    public void setIdEstadoFinanciero(int idEstadoFinanciero) {
        this.idEstadoFinanciero = idEstadoFinanciero;
    }

    public double getTotales() {
        return activosTotales;
    }

    public void setTotales(double totales) {
        this.activosTotales = totales;
    }

    public int getAnioEstadoFinaciero() {
        return anioEstadoFinaciero;
    }

    public void setAnioEstadoFinaciero(int anioEstadoFinaciero) {
        this.anioEstadoFinaciero = anioEstadoFinaciero;
    }

    public double getPasivoTotales() {
        return pasivoTotales;
    }

    public void setPasivoTotales(double pasivoTotales) {
        this.pasivoTotales = pasivoTotales;
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(double patrimonio) {
        this.patrimonio = patrimonio;
    }

}
