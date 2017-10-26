/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
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
import ues.anf135.datos.acceso.TipoEmpresaFacadeLocal;
import ues.anf135.datos.definiciones.Empresa;
import ues.anf135.datos.definiciones.TipoEmpresa;

/**
 *
 * @author zywel
 */
@Named(value = "controllerEmpresa")
@ViewScoped
public class ControllerEmpresa extends DefaultGenerator<Empresa> implements Serializable {

    @EJB
    EmpresaFacadeLocal empresaFacade;

    @EJB
    TipoEmpresaFacadeLocal tipoEmpresaFacade;

    private List<Empresa> lstEmpresas;
    private List<TipoEmpresa> lstTipoEmpresa;

    private Empresa registroEmpresa = new Empresa();

    private TipoEmpresa registroTipoEmpresa;

    private int idUltimo;
    private int idEmpresa;

    private int count;

    public void obtenerEmpresa() {
        registro = empresaFacade.find(idEmpresa);
        this.idUltimo = this.registro.getTipoEmpresaIdTipoEmpresa().getIdTipoEmpresa();
    }

    public String parametros() {
        String dir = "";
        if (registro != null) {
            this.idEmpresa = this.registro.getIdEmpresa();
            dir = "editar_empresa.jsf?faces-redirect=true&includeViewParams=true";
        } else {
            System.out.println("Seleccione una opcion");
        }
        return dir;
    }

    public void llenarListaEmpresas() {
        setLstEmpresas(empresaFacade.findAll());
        setCount(empresaFacade.count() + 1);
        setLstTipoEmpresa(tipoEmpresaFacade.findAll());
    }

    public void agregarEmpresa() {
        setRegistroTipoEmpresa(lstTipoEmpresa.get(idUltimo - 1));
        registro.setTipoEmpresaIdTipoEmpresa(getRegistroTipoEmpresa());
        if (empresaFacade.crear(registro)) {
            System.out.println("agregado con exito");
        }
    }

    public void actualizarEmpresa() {
        setRegistroTipoEmpresa(lstTipoEmpresa.get(idUltimo - 1));
        registro.setTipoEmpresaIdTipoEmpresa(getRegistroTipoEmpresa());
        if (empresaFacade.editar(registro)) {
            System.out.println("actualizado con exito");
        }
    }

    public void obtenerTipoEmpresa() {

    }

    @Override
    public LazyDataModel<Empresa> getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(LazyDataModel<Empresa> modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the lstEmpresas
     */
    public List<Empresa> getLstEmpresas() {
        return lstEmpresas;
    }

    /**
     * @param lstEmpresas the lstEmpresas to set
     */
    public void setLstEmpresas(List<Empresa> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }

    /**
     * @return the registroEmpresa
     */
    public Empresa getRegistroEmpresa() {
        return registroEmpresa;
    }

    /**
     * @param registroEmpresa the registroEmpresa to set
     */
    public void setRegistroEmpresa(Empresa registroEmpresa) {
        this.registroEmpresa = registroEmpresa;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the idUltimo
     */
    public int getIdUltimo() {
        return idUltimo;
    }

    /**
     * @param idUltimo the idUltimo to set
     */
    public void setIdUltimo(int idUltimo) {
        this.idUltimo = idUltimo;
    }

    /**
     * @return the lstTipoEmpresa
     */
    public List<TipoEmpresa> getLstTipoEmpresa() {
        return lstTipoEmpresa;
    }

    /**
     * @param lstTipoEmpresa the lstTipoEmpresa to set
     */
    public void setLstTipoEmpresa(List<TipoEmpresa> lstTipoEmpresa) {
        this.lstTipoEmpresa = lstTipoEmpresa;
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

    @PostConstruct
    public void inicializar() {
        generarListas();
        setCount(empresaFacade.count() + 1);
        generarModeloTabla();
        setTamanioPagina(5);
        setRegistro(new Empresa());
        this.registro.setEstado(true);
    }

    @Override
    protected AbstractFacadeInterface<Empresa> getFacade() {
        return this.empresaFacade;
    }

    @Override
    protected void generarListas() {
        if (this.empresaFacade != null || !this.lstEmpresas.isEmpty()) {
            try {
                if (this.empresaFacade != null) {
                    this.lstEmpresas = empresaFacade.findAll();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                if (lstEmpresas == null) {
                    this.lstEmpresas = new ArrayList<>();
                }
            }
        }

        if (this.tipoEmpresaFacade != null || !this.lstTipoEmpresa.isEmpty()) {
            try {
                if (this.tipoEmpresaFacade != null) {
                    this.lstTipoEmpresa = tipoEmpresaFacade.findAll();
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } finally {
                if (lstTipoEmpresa == null) {
                    this.lstTipoEmpresa = new ArrayList<>();
                }
            }
        }
    }

    @Override
    protected void generarModeloTabla() {
        this.modelo = new LazyDataModel<Empresa>() {

            @Override
            public Object getRowKey(Empresa object) {
                if (object != null) {
                    return object.getIdEmpresa();
                }
                return null;
            }

            @Override
            public Empresa getRowData(String rowKey) {
                if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                    try {
                        String buscado = rowKey; //String buscado = new String(rowKey);
                        for (Empresa reg : ((List<Empresa>) getWrappedData())) {
                            if (reg.getIdEmpresa().toString().compareTo(buscado) == 0) {
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
            public List<Empresa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                try {
                    if (empresaFacade != null) {
                        if (filters != null && !filters.isEmpty()) {
                            salida = empresaFacade.findByDataFilters(filters, first, tamanioPagina);
                            this.setRowCount(empresaFacade.countByDataFilters(filters));
                        } else {
                            salida = empresaFacade.findRango(first, tamanioPagina);
                            this.setRowCount(empresaFacade.count());
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
     * @return the registroTipoEmpresa
     */
    public TipoEmpresa getRegistroTipoEmpresa() {
        return registroTipoEmpresa;
    }

    /**
     * @param registroTipoEmpresa the registroTipoEmpresa to set
     */
    public void setRegistroTipoEmpresa(TipoEmpresa registroTipoEmpresa) {
        this.registroTipoEmpresa = registroTipoEmpresa;
    }
}
