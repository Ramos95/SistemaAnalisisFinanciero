/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.CuentaMayor;

/**
 *
 * @author zywel
 */
@Local
public interface CuentaMayorFacadeLocal extends AbstractFacadeInterface<CuentaMayor> {
    /*
    void create(CuentaMayor cuentaMayor);

    void edit(CuentaMayor cuentaMayor);

    void remove(CuentaMayor cuentaMayor);

    CuentaMayor find(Object id);

    List<CuentaMayor> findAll();

    List<CuentaMayor> findRange(int[] range);

    int count();
     */
    
    
    List<CuentaMayor> obtenerPorAgrupacion(int idAgrupacion);
    
    
}
