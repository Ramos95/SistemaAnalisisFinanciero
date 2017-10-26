/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.Cuenta;
import ues.anf135.datos.definiciones.ElementoFinanciero;

/**
 *
 * @author zywel
 */
@Local
public interface ElementoFinancieroFacadeLocal extends AbstractFacadeInterface<ElementoFinanciero>{
/*
    void create(ElementoFinanciero elementoFinanciero);

    void edit(ElementoFinanciero elementoFinanciero);

    void remove(ElementoFinanciero elementoFinanciero);

    ElementoFinanciero find(Object id);

    List<ElementoFinanciero> findAll();
    
    List<ElementoFinanciero> findRange(int[] range);

    int count();
  */  
    
    List<ElementoFinanciero> obtenerPorEstadoResultados();
    
    List<ElementoFinanciero> obtenerPorBalanceGeneral();
}
