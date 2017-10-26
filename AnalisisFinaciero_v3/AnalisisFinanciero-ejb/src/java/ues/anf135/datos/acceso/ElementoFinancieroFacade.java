/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.anf135.datos.definiciones.Cuenta;
import ues.anf135.datos.definiciones.ElementoFinanciero;

/**
 *
 * @author zywel
 */
@Stateless
public class ElementoFinancieroFacade extends AbstractFacade<ElementoFinanciero> implements ElementoFinancieroFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementoFinancieroFacade() {
        super(ElementoFinanciero.class);
    }

    @Override
    public List<ElementoFinanciero> obtenerPorEstadoResultados() {
        Query q = getEntityManager().createNamedQuery("ElementoFinanciero.obtenerPorEstadoResultados");
        return q.getResultList();
    }

    @Override
    public List<ElementoFinanciero> obtenerPorBalanceGeneral() {
        Query q = getEntityManager().createNamedQuery("ElementoFinanciero.obtenerPorBalanceGeneral");
        return q.getResultList();
    }


    
}
