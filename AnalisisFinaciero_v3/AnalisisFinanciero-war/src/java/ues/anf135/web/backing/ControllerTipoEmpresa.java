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
import ues.anf135.datos.acceso.TipoEmpresaFacadeLocal;
import ues.anf135.datos.definiciones.TipoEmpresa;

/**
 *
 * @author zywel
 */

@Named(value = "controllerTipoEmpresa")
@ViewScoped
public class ControllerTipoEmpresa implements Serializable{
    
    @EJB
    TipoEmpresaFacadeLocal tipoEmpresaFacade;
    
    private List<TipoEmpresa> lstTipoEmpresas;
    private List<String> lstTiposNombres;
    
    private TipoEmpresa registroTipoEmpresa;
    
    
    @PostConstruct
    public void llenarListaTipoEmpresas(){
        /*for (TipoEmpresa tipo : tipoEmpresaFacade.findAll()) {
            getLstTiposNombres().add(tipo.getNombre());
        }*/
        
        setLstTipoEmpresas(tipoEmpresaFacade.findAll());
        
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

    /**
     * @return the lstTipoEmpresas
     */
    public List<TipoEmpresa> getLstTipoEmpresas() {
        return lstTipoEmpresas;
    }

    /**
     * @param lstTipoEmpresas the lstTipoEmpresas to set
     */
    public void setLstTipoEmpresas(List<TipoEmpresa> lstTipoEmpresas) {
        this.lstTipoEmpresas = lstTipoEmpresas;
    }

    /**
     * @return the lstTiposNombres
     */
    public List<String> getLstTiposNombres() {
        return lstTiposNombres;
    }

    /**
     * @param lstTiposNombres the lstTiposNombres to set
     */
    public void setLstTiposNombres(List<String> lstTiposNombres) {
        this.lstTiposNombres = lstTiposNombres;
    }

    
    
}
