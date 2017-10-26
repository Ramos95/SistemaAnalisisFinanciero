/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.anf135.datos.definiciones.DetalleEstadoFinanciero;

/**
 *
 * @author zywel
 */
@Stateless
public class DetalleEstadoFinancieroFacade extends AbstractFacade<DetalleEstadoFinanciero> implements DetalleEstadoFinancieroFacadeLocal {

    @PersistenceContext(unitName = "AnalisisFinanciero-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleEstadoFinancieroFacade() {
        super(DetalleEstadoFinanciero.class);
    }

    private List<DetalleEstadoFinanciero> validar(List<DetalleEstadoFinanciero> lista) {
        if (lista == null || lista.isEmpty()) {
            return new ArrayList<DetalleEstadoFinanciero>();
        }
        return lista;
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerActivosTotales() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerActivosTotales");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerActivosCorrientes() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerActivosCorrientes");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerActivosNoCorrientes() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerActivosNoCorrientes");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerPasivosTotales() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerPasivosTotales");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerPasivosCorriente() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerPasivosCorrientes");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerPasivosNoCorriente() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerPasivosNoCorrientes");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerInventario() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerInventario");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerCapital() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerCapital");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerCostosNetos() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerCostosNetos");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerVentasNetas() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerVentasNetas");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerPorEmpresaEstadoFinanciero(int idEmpresa, int idEstadoFinanciero) {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerPorEmpresaEstadoFinanciero");
        q.setParameter("idEmpresa", idEmpresa);
        q.setParameter("idEstadoFinanciero", idEstadoFinanciero);
        return q.getResultList();
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerCaja() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerCaja");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerBanco() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerBanco");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerValoresRealizables() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerValoresRealizables");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerCuentasPorCobrar() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerCuentasPorCobrar");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerMercanciaTransito() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerMercanciaTransito");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerUtilidadNeta() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerUtilidadNeta");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerPorFecha(Integer idEmpresa, Date fecha) {
        List<DetalleEstadoFinanciero> salida = null;

        try {
            if (getEntityManager() != null) {
                Query query = em.createNamedQuery("DetalleEstadoFinanciero.obtenerPorFecha");
                query.setParameter("idEmpresa", idEmpresa);
                query.setParameter("fecha", fecha);
                salida = query.getResultList();

                System.out.println("Salida obtenerPorFecha");
                for (DetalleEstadoFinanciero detalle : salida) {
                    System.out.println("-----------------------");
                    System.out.println("idDetalle: " + detalle.getDetalleEstadoFinancieroPK().getIdEstadoFinanciero());
                    System.out.println("cuenta: " + detalle.getCuenta().getNombre());
                    System.out.println("empresa: " + detalle.getEstadoFinanciero().getEmpresa().getNombreCorto());
                    System.out.println("fecha: " + detalle.getEstadoFinanciero().getFechaDesde().toString());
                    System.out.println("saldo: " + detalle.getSaldo());
                }
                System.out.println("-----------------------");
            }

        } catch (Exception e) {
            System.out.println("Error... obtenerPorFecha Fuentes&Usos");
            e.printStackTrace();
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }

        return salida;
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerUtilidadBruta() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerUtilidadBruta");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerUtilidadAntesImpuestoReserva() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerUtilidadAntesImpuestoReserva");
        return validar(q.getResultList());
    }

    @Override
    public List<DetalleEstadoFinanciero> obtenerUtilidadOperativa() {
        Query q = getEntityManager().createNamedQuery("DetalleEstadoFinanciero.obtenerUtilidadOperativa");
        return validar(q.getResultList());
    }

}
