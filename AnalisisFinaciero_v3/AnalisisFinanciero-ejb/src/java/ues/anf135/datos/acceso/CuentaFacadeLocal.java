/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.Cuenta;

/**
 *
 * @author zywel
 */
@Local
public interface CuentaFacadeLocal extends AbstractFacadeInterface<Cuenta> {

    /*void create(Cuenta cuenta);

    void edit(Cuenta cuenta);

    void remove(Cuenta cuenta);

    Cuenta find(Object id);

    List<Cuenta> findAll();
    
    List<Cuenta> findRange(int[] range);

    int count();
     */
    List<Cuenta> obtenerPorAgrupacion(int idAgrupacion);

    List<Cuenta> obtenerPorEstadoResultado();

    List<Cuenta> obtenerPorBalanceGeneral();

    Cuenta findByNombre(String nombre);

    List<Cuenta> obtenerUltimoPorCuentaMayor(int idCuentaMayor);
}
