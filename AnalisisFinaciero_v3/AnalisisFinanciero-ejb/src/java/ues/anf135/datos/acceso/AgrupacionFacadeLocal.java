/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.Agrupacion;

/**
 *
 * @author zywel
 */
@Local
public interface AgrupacionFacadeLocal extends AbstractFacadeInterface<Agrupacion> {

    /*void create(Agrupacion agrupacion);

    void edit(Agrupacion agrupacion);

    void remove(Agrupacion agrupacion);

    Agrupacion find(Object id);

    List<Agrupacion> findAll();

    List<Agrupacion> findRange(int[] range);

    int count();
    */
    
    List<Agrupacion> obtenerPorElementoFinanciero(int idElementoFinanciero);
}
