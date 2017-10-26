/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.anf135.datos.definiciones.TipoEmpresa;

/**
 *
 * @author zywel
 */
@Stateless
public class TipoEmpresaFacade extends AbstractFacade<TipoEmpresa> implements TipoEmpresaFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEmpresaFacade() {
        super(TipoEmpresa.class);
    }
    
}
