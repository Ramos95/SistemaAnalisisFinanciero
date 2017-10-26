/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Local
public interface DetalleEstadoFinancieroFacadeLocal extends AbstractFacadeInterface<DetalleEstadoFinanciero> {
/*
    void create(DetalleEstadoFinanciero detalleEstadoFinanciero);

    void edit(DetalleEstadoFinanciero detalleEstadoFinanciero);

    void remove(DetalleEstadoFinanciero detalleEstadoFinanciero);

    DetalleEstadoFinanciero find(Object id);

    List<DetalleEstadoFinanciero> findAll();
*/
    List<DetalleEstadoFinanciero> obtenerActivosTotales();
    List<DetalleEstadoFinanciero> obtenerActivosCorrientes();
    List<DetalleEstadoFinanciero> obtenerActivosNoCorrientes();
    
    List<DetalleEstadoFinanciero> obtenerPasivosTotales();
    List<DetalleEstadoFinanciero> obtenerPasivosCorriente();
    List<DetalleEstadoFinanciero> obtenerPasivosNoCorriente();
    List<DetalleEstadoFinanciero> obtenerCapital();
    
    List<DetalleEstadoFinanciero> obtenerInventario();
    List<DetalleEstadoFinanciero> obtenerCostosNetos();
    List<DetalleEstadoFinanciero> obtenerVentasNetas();
    
    List<DetalleEstadoFinanciero> obtenerCaja();
    List<DetalleEstadoFinanciero> obtenerBanco();
    List<DetalleEstadoFinanciero> obtenerValoresRealizables();
    List<DetalleEstadoFinanciero> obtenerCuentasPorCobrar();
    List<DetalleEstadoFinanciero> obtenerMercanciaTransito();
    
    List<DetalleEstadoFinanciero> obtenerUtilidadNeta();
    List<DetalleEstadoFinanciero> obtenerUtilidadBruta();
    List<DetalleEstadoFinanciero> obtenerUtilidadAntesImpuestoReserva();
    List<DetalleEstadoFinanciero> obtenerUtilidadOperativa();
    
    List<DetalleEstadoFinanciero> obtenerPorEmpresaEstadoFinanciero(int idEmpresa, int idEstadoFinanciero);
    
    
    List<DetalleEstadoFinanciero> obtenerPorFecha(Integer idEmpresa, Date fecha);
/*
    
    
    List<DetalleEstadoFinanciero> findRange(int[] range);

    int count();
*/
}
