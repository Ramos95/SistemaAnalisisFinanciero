/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.definiciones;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zywel
 */
@Entity
@Table(name = "cuenta", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByIdCuenta", query = "SELECT c FROM Cuenta c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuenta.findByNombre", query = "SELECT c FROM Cuenta c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cuenta.obtenerPorAgrupacion", query = "SELECT c FROM Cuenta c WHERE c.idCuentaMayor.idAgrupacion.idAgrupacion = :idAgrupacion"),
    @NamedQuery(name = "Cuenta.obtenerUltimoPorCuentaMayor", query = "SELECT c FROM Cuenta c JOIN c.idCuentaMayor cm WHERE cm.idCuentaMayor = :idCuentaMayor ORDER BY c.idCuenta DESC" ),
    @NamedQuery(name = "Cuenta.obtenerPorBalanceGeneral", query = "SELECT c FROM Cuenta c JOIN c.idCuentaMayor.idAgrupacion.idElementoFinanciero e WHERE e.idElementoFinanciero BETWEEN 1 AND 3"),
    @NamedQuery(name = "Cuenta.obtenerPorEstadoResultado", query = "SELECT c FROM Cuenta c JOIN c.idCuentaMayor.idAgrupacion.idElementoFinanciero e WHERE e.idElementoFinanciero >= 4"),
    @NamedQuery(name = "Cuenta.findByEstado", query = "SELECT c FROM Cuenta c WHERE c.estado = :estado")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cuenta", nullable = false)
    private Integer idCuenta;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @JoinColumn(name = "id_cuenta_mayor", referencedColumnName = "id_cuenta_mayor", nullable = false)
    @ManyToOne(optional = false)
    private CuentaMayor idCuentaMayor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<DetalleEstadoFinanciero> detalleEstadoFinancieroList;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String nombre, boolean estado) {
        this.idCuenta = idCuenta;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public CuentaMayor getIdCuentaMayor() {
        return idCuentaMayor;
    }

    public void setIdCuentaMayor(CuentaMayor idCuentaMayor) {
        this.idCuentaMayor = idCuentaMayor;
    }

    @XmlTransient
    public List<DetalleEstadoFinanciero> getDetalleEstadoFinancieroList() {
        return detalleEstadoFinancieroList;
    }

    public void setDetalleEstadoFinancieroList(List<DetalleEstadoFinanciero> detalleEstadoFinancieroList) {
        this.detalleEstadoFinancieroList = detalleEstadoFinancieroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.Cuenta[ idCuenta=" + idCuenta + " ]";
    }

}
