/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.calculo;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.FuenteUso;

/**
 *
 * @author olmos
 */
@Local
public interface FuentesUsosFacadeLocal {

    List<FuenteUso> obtenerFuentesUsos(Date fechaInicio, Date fechaFin, Integer idEmpresa);

}
