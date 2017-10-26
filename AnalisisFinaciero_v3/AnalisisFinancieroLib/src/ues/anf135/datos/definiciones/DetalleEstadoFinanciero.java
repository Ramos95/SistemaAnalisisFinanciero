/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zywel
 */
@Entity
@Table(name = "detalle_estado_financiero", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEstadoFinanciero.findAll", query = "SELECT d FROM DetalleEstadoFinanciero d"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerPorFecha", query = "SELECT d FROM DetalleEstadoFinanciero d WHERE d.estadoFinanciero.empresa.idEmpresa = :idEmpresa AND d.estadoFinanciero.fechaDesde = :fecha"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerPorEmpresaEstadoFinanciero", query = "SELECT d FROM DetalleEstadoFinanciero d JOIN d.estadoFinanciero ef WHERE ef.estadoFinancieroPK.idEmpresa = :idEmpresa AND ef.estadoFinancieroPK.idEstadoFinanciero = :idEstadoFinanciero"),
    @NamedQuery(name = "DetalleEstadoFinanciero.findByIdEstadoFinanciero", query = "SELECT d FROM DetalleEstadoFinanciero d WHERE d.detalleEstadoFinancieroPK.idEstadoFinanciero = :idEstadoFinanciero"),
    @NamedQuery(name = "DetalleEstadoFinanciero.findByIdEmpresa", query = "SELECT d FROM DetalleEstadoFinanciero d WHERE d.detalleEstadoFinancieroPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "DetalleEstadoFinanciero.findByIdCuenta", query = "SELECT d FROM DetalleEstadoFinanciero d WHERE d.detalleEstadoFinancieroPK.idCuenta = :idCuenta"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerActivosTotales", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion.idElementoFinanciero e WHERE e.nombre LIKE '%ACTIVO%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerActivosCorrientes", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion a WHERE a.nombre LIKE '%ACTIVO CORRIENTE%' OR a.nombre LIKE '%ACTIVOS CORRIENTES%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerActivosNoCorrientes", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion a WHERE a.nombre LIKE '%ACTIVO NO CORRIENTE%' OR a.nombre LIKE '%ACTIVOS NO CORRIENTE%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerPasivosTotales", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion.idElementoFinanciero e WHERE e.nombre LIKE '%PASIVO%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerPasivosCorrientes", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion a WHERE a.nombre LIKE '%PASIVO CORRIENTE%' OR a.nombre LIKE '%PASIVOS CORRIENTES%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerPasivosNoCorrientes", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion a WHERE a.nombre LIKE '%PASIVO NO CORRIENTE%' OR a.nombre LIKE '%PASIVOS NO CORRIENTE%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerCapital", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor.idAgrupacion.idElementoFinanciero e WHERE e.nombre LIKE '%PATRIMONIO%' OR e.nombre LIKE '%CAPITAL%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerInventario", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor cm WHERE cm.nombre LIKE '%INVENTARIO%' OR cm.nombre LIKE '%INVENTARIOS%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerCostosNetos", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Costo de ventas%' OR c.nombre LIKE '%Costo de venta%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerVentasNetas", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Ventas netas%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerCaja", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor cm WHERE cm.nombre LIKE '%CAJA%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerValoresRealizables", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor cm WHERE cm.nombre LIKE '%VALORES REALIZABLES%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerBanco", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta.idCuentaMayor cm WHERE cm.nombre LIKE '%BANCO%' OR cm.nombre LIKE '%BANCOS%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerCuentasPorCobrar", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Cuentas por cobrar%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerMercanciaTransito", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%tránsito%' OR c.nombre LIKE '%transito%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerUtilidadNeta", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Utilidad neta%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerUtilidadBruta", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Utilidad Bruta%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerUtilidadOperativa", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Utilidad de Operacion%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.obtenerUtilidadAntesImpuestoReserva", query = "SELECT def FROM DetalleEstadoFinanciero def JOIN def.cuenta c WHERE c.nombre LIKE '%Utilidad antes de impuesto y reserva%'"),
    @NamedQuery(name = "DetalleEstadoFinanciero.findBySaldo", query = "SELECT d FROM DetalleEstadoFinanciero d WHERE d.saldo = :saldo")})
public class DetalleEstadoFinanciero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleEstadoFinancieroPK detalleEstadoFinancieroPK;
    @Basic(optional = false)
    @Column(name = "saldo", nullable = false)
    private double saldo;
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;
    @JoinColumns({
        @JoinColumn(name = "id_estado_financiero", referencedColumnName = "id_estado_financiero", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EstadoFinanciero estadoFinanciero;

    public DetalleEstadoFinanciero() {
    }

    public DetalleEstadoFinanciero(DetalleEstadoFinancieroPK detalleEstadoFinancieroPK) {
        this.detalleEstadoFinancieroPK = detalleEstadoFinancieroPK;
    }

    public DetalleEstadoFinanciero(DetalleEstadoFinancieroPK detalleEstadoFinancieroPK, double saldo) {
        this.detalleEstadoFinancieroPK = detalleEstadoFinancieroPK;
        this.saldo = saldo;
    }

    public DetalleEstadoFinanciero(int idEstadoFinanciero, int idEmpresa, int idCuenta) {
        this.detalleEstadoFinancieroPK = new DetalleEstadoFinancieroPK(idEstadoFinanciero, idEmpresa, idCuenta);
    }

    public DetalleEstadoFinancieroPK getDetalleEstadoFinancieroPK() {
        return detalleEstadoFinancieroPK;
    }

    public void setDetalleEstadoFinancieroPK(DetalleEstadoFinancieroPK detalleEstadoFinancieroPK) {
        this.detalleEstadoFinancieroPK = detalleEstadoFinancieroPK;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public EstadoFinanciero getEstadoFinanciero() {
        return estadoFinanciero;
    }

    public void setEstadoFinanciero(EstadoFinanciero estadoFinanciero) {
        this.estadoFinanciero = estadoFinanciero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleEstadoFinancieroPK != null ? detalleEstadoFinancieroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEstadoFinanciero)) {
            return false;
        }
        DetalleEstadoFinanciero other = (DetalleEstadoFinanciero) object;
        if ((this.detalleEstadoFinancieroPK == null && other.detalleEstadoFinancieroPK != null) || (this.detalleEstadoFinancieroPK != null && !this.detalleEstadoFinancieroPK.equals(other.detalleEstadoFinancieroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.DetalleEstadoFinanciero[ detalleEstadoFinancieroPK=" + detalleEstadoFinancieroPK + " ]";
    }

}
