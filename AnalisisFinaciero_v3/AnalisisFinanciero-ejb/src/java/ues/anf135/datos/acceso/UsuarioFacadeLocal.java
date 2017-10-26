/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.Usuario;

/**
 *
 * @author zywel
 */
@Local
public interface UsuarioFacadeLocal extends AbstractFacadeInterface<Usuario> {

    /*void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    */
}
