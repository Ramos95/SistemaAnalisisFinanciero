/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.anf135.datos.definiciones.TipoEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Stateless
public class TipoEstadoFinancieroFacade extends AbstractFacade<TipoEstadoFinanciero> implements TipoEstadoFinancieroFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstadoFinancieroFacade() {
        super(TipoEstadoFinanciero.class);
    }
    
}
