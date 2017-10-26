/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.TipoEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Local
public interface TipoEstadoFinancieroFacadeLocal extends AbstractFacadeInterface<TipoEstadoFinanciero>{

    /*void create(TipoEstadoFinanciero tipoEstadoFinanciero);

    void edit(TipoEstadoFinanciero tipoEstadoFinanciero);

    void remove(TipoEstadoFinanciero tipoEstadoFinanciero);

    TipoEstadoFinanciero find(Object id);

    List<TipoEstadoFinanciero> findAll();

    List<TipoEstadoFinanciero> findRange(int[] range);

    int count();
    */
}
