/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.anf135.datos.definiciones.EstadoFinanciero;

/**
 *
 * @author zywel
 */
@Stateless
public class EstadoFinancieroFacade extends AbstractFacade<EstadoFinanciero> implements EstadoFinancieroFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFinancieroFacade() {
        super(EstadoFinanciero.class);
    }

    @Override
    public List<EstadoFinanciero> obtenerTodos() {
        Query q = getEntityManager().createNamedQuery("EstadoFinanciero.obtenerTodos");
        return q.getResultList();
    }

    @Override
    public List<Date> obtenerEstadosPorEmpresa(Integer idEmpresa) {
        List<Date> salida = null;
        List<EstadoFinanciero> lista = null;
        try {
            if (getEntityManager() != null) {
                Query query = em.createNamedQuery("EstadoFinanciero.findByIdEmpresa");
                query.setParameter("idEmpresa", idEmpresa);
                lista = query.getResultList();

                if (lista != null) {
                    salida = new ArrayList<>();
                    for (EstadoFinanciero estado : lista) {
                        /*Calendar cal = Calendar.getInstance();
	                    cal.setTime(estado.getFechaDesde());
	                    cal.set(cal.get(Calendar.YEAR) + 1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	                    if (!salida.contains(cal.getTime())) {
	                        salida.add(cal.getTime());
	                    }*/
                        if (!salida.contains(estado.getFechaDesde())) {
                            salida.add(estado.getFechaDesde());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error... EstadoFinancieroFacade.obtenerEstadosPorEmpresa");
            e.printStackTrace();
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }

        return salida;
    }

}
