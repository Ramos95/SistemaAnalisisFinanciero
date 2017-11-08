
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ues.anf135.datos.acceso.AbstractFacadeInterface;
import ues.anf135.datos.acceso.EmpresaFacadeLocal;
import ues.anf135.datos.acceso.EstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.TipoEstadoFinancieroFacadeLocal;
import ues.anf135.datos.definiciones.Empresa;
import ues.anf135.datos.definiciones.EstadoFinanciero;
import ues.anf135.datos.definiciones.EstadoFinancieroPK;
import ues.anf135.datos.definiciones.TipoEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Named(value = "controllerEstadoFinanciero")
@ViewScoped
public class ControllerEstadoFinanciero extends DefaultGenerator<EstadoFinanciero> implements Serializable {

    @EJB
    EstadoFinancieroFacadeLocal efFacade;

    @EJB
    EmpresaFacadeLocal empresaFacade;

    @EJB
    TipoEstadoFinancieroFacadeLocal tefFacade;

    private List<EstadoFinanciero> lstEstadoFinanciero;
    private List<Empresa> lstEmpresa;
    private Empresa empresa;

    private int idEmpresa;
    private int idEstadoFinanciero;
    private int anioEstadoFinanciero;
    
    private String fecha;

    private EstadoFinanciero estadoFinanciero;

    public ControllerEstadoFinanciero() {
        registro = new EstadoFinanciero();
    }

    @PostConstruct
    public void obtenerEstadosFinancieros() {
        //setLstEstadoFinanciero(efFacade.findAll());
        setLstEstadoFinanciero(efFacade.obtenerTodos());
        for (EstadoFinanciero da : lstEstadoFinanciero) {
            System.out.println("id: " + da.getIdTipoEstadoFinanciero().getIdTipoEstadoFinanciero() + " tipo: " + da.getIdTipoEstadoFinanciero().getNombre());
        }
        setLstEmpresa(empresaFacade.findAll());
    }

    public void agregarEstadoFinanciero() {
        try {
            registro.setEmpresa(empresaFacade.find(idEmpresa));
            EstadoFinancieroPK efpk = new EstadoFinancieroPK();
            efpk.setIdEmpresa(idEmpresa);
            efpk.setIdEstadoFinanciero(efFacade.count() + 1);
            registro.setEstadoFinancieroPK(efpk);

            SimpleDateFormat sdfFechaDesde = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat sdfFechaHasta = new SimpleDateFormat("yyyy-mm-dd");

            registro.setFechaDesde(sdfFechaDesde.parse(fecha + "-01-01"));
            registro.setFechaHasta(sdfFechaHasta.parse(fecha + "-12-31"));

            registro.setIdTipoEstadoFinanciero(new TipoEstadoFinanciero(1));
            if (efFacade.crear(registro)) {
                System.out.println("---> agregado con exito");
            } else {
                System.out.println("---> error al agregar");
            }

            efpk.setIdEstadoFinanciero(efFacade.count() + 1);
            registro.setEstadoFinancieroPK(efpk);
            registro.setIdTipoEstadoFinanciero(new TipoEstadoFinanciero(2));
            if (efFacade.crear(registro)) {
                System.out.println("---> agregado con exito");
            } else {
                System.out.println("---> error al agregar");
            }

        } catch (ParseException ex) {
            Logger.getLogger(ControllerEstadoFinanciero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String parametros(int accion) {
        //this.idEmpresa = this.registro.getIdEmpresa();
        String dir = "";

        if (accion == 0) { //esto es agregar
            this.agregarEstadoFinanciero();
            this.idEstadoFinanciero = efFacade.count();
            dir = "cuentas_estado_financiero.jsf?faces-redirect=true&includeViewParams=true";
        } else { //esto es editar
            this.idEstadoFinanciero = this.registro.getEstadoFinancieroPK().getIdEstadoFinanciero();
            
            this.idEmpresa = this.registro.getEstadoFinancieroPK().getIdEmpresa();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.registro.getFechaDesde());
            this.anioEstadoFinanciero = calendar.get(Calendar.YEAR);
            
            dir = "editar_cuentas_estado_financiero.jsf?faces-redirect=true&includeViewParams=true";
        }

        System.out.println("estado finan: " + this.idEstadoFinanciero);
        System.out.println("empresa: " + this.idEmpresa);
        System.out.println("??anio estado finan: " + this.anioEstadoFinanciero);
        return dir;
    }

    @Override
    protected AbstractFacadeInterface<EstadoFinanciero> getFacade() {
        return this.efFacade;
    }

    @Override
    protected void generarListas() {
        if (this.efFacade != null || !this.lstEstadoFinanciero.isEmpty()) {
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
        }
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
     * @return the lstEstadoFinanciero
     */
    public List<EstadoFinanciero> getLstEstadoFinanciero() {
        return lstEstadoFinanciero;
    }

    /**
     * @param lstEstadoFinanciero the lstEstadoFinanciero to set
     */
    public void setLstEstadoFinanciero(List<EstadoFinanciero> lstEstadoFinanciero) {
        this.lstEstadoFinanciero = lstEstadoFinanciero;
    }

    /**
     * @return the lstEmpresa
     */
    public List<Empresa> getLstEmpresa() {
        return lstEmpresa;
    }

    /**
     * @param lstEmpresa the lstEmpresa to set
     */
    public void setLstEmpresa(List<Empresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
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

    public int getAnioEstadoFinanciero() {
        return anioEstadoFinanciero;
    }

    public void setAnioEstadoFinanciero(int anioEstadoFinanciero) {
        this.anioEstadoFinanciero = anioEstadoFinanciero;
    }
    
    

}
