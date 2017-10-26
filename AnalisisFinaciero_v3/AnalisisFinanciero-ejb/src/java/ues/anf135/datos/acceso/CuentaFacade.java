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

/**
 *
 * @author zywel
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> implements CuentaFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    @Override
    public List<Cuenta> obtenerPorAgrupacion(int idAgrupacion) {
        Query q = getEntityManager().createNamedQuery("Cuenta.obtenerPorAgrupacion");
        q.setParameter("idAgrupacion", idAgrupacion);
        return q.getResultList();
    }

    @Override
    public List<Cuenta> obtenerPorEstadoResultado() {
        Query q = getEntityManager().createNamedQuery("Cuenta.obtenerPorEstadoResultado");
        return q.getResultList();
    }

    @Override
    public List<Cuenta> obtenerPorBalanceGeneral() {
        Query q = getEntityManager().createNamedQuery("Cuenta.obtenerPorBalanceGeneral");
        return q.getResultList();
    }

    @Override
    public Cuenta findByNombre(String nombre) {
        Query q = getEntityManager().createNamedQuery("Cuenta.findByNombre");
        q.setParameter("nombre", nombre);
        return (Cuenta) q.getSingleResult();
    }

    @Override
    public List<Cuenta> obtenerUltimoPorCuentaMayor(int idCuentaMayor) {
        Query q = getEntityManager().createNamedQuery("Cuenta.obtenerUltimoPorCuentaMayor");
        q.setParameter("idCuentaMayor", idCuentaMayor);
        q.setMaxResults(1);
        System.out.println("count: " + q.getResultList().size());
        System.out.println("info: " + q.getResultList().get(0).toString());
        return q.getResultList();
    }

}
