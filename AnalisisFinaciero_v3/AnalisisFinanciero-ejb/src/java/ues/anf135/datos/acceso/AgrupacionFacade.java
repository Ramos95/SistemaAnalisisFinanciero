
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.anf135.datos.definiciones.Agrupacion;

/**
 *
 * @author zywel
 */
@Stateless
public class AgrupacionFacade extends AbstractFacade<Agrupacion> implements AgrupacionFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgrupacionFacade() {
        super(Agrupacion.class);
    }

    @Override
    public List<Agrupacion> obtenerPorElementoFinanciero(int idElementoFinanciero) {
        Query q = getEntityManager().createNamedQuery("Agrupacion.obtenerPorElementoFinanciero");
        q.setParameter("idElementoFinanciero", idElementoFinanciero);
        return q.getResultList();
    }
    
}
