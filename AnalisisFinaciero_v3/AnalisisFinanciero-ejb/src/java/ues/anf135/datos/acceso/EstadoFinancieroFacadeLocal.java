/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.EstadoFinanciero;

/**
 *
 * @author zywel
 */
@Local
public interface EstadoFinancieroFacadeLocal extends AbstractFacadeInterface<EstadoFinanciero> {

    /*void create(EstadoFinanciero estadoFinanciero);

    void edit(EstadoFinanciero estadoFinanciero);

    void remove(EstadoFinanciero estadoFinanciero);

    EstadoFinanciero find(Object id);

    List<EstadoFinanciero> findAll();

    List<EstadoFinanciero> findRange(int[] range);

    int count();
     */
    List<EstadoFinanciero> obtenerTodos();

    public List<Date> obtenerEstadosPorEmpresa(Integer idEmpresa);
}
