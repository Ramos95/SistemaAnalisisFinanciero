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
@Table(name = "cuenta_mayor", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaMayor.findAll", query = "SELECT c FROM CuentaMayor c"),
    @NamedQuery(name = "CuentaMayor.obtenerPorAgrupacion", query = "SELECT c FROM CuentaMayor c JOIN c.idAgrupacion a WHERE a.idAgrupacion = :idAgrupacion"),
    @NamedQuery(name = "CuentaMayor.obtenerPorCuenta", query = "SELECT cm FROM Cuenta c JOIN c.idCuentaMayor cm WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "CuentaMayor.findByIdCuentaMayor", query = "SELECT c FROM CuentaMayor c WHERE c.idCuentaMayor = :idCuentaMayor"),
    @NamedQuery(name = "CuentaMayor.findByNombre", query = "SELECT c FROM CuentaMayor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CuentaMayor.findByDescripcion", query = "SELECT c FROM CuentaMayor c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CuentaMayor.findByEstado", query = "SELECT c FROM CuentaMayor c WHERE c.estado = :estado")})
public class CuentaMayor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cuenta_mayor", nullable = false)
    private Integer idCuentaMayor;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaMayor")
    private List<Cuenta> cuentaList;
    @JoinColumn(name = "id_agrupacion", referencedColumnName = "id_agrupacion", nullable = false)
    @ManyToOne(optional = false)
    private Agrupacion idAgrupacion;

    public CuentaMayor() {
    }

    public CuentaMayor(Integer idCuentaMayor) {
        this.idCuentaMayor = idCuentaMayor;
    }

    public CuentaMayor(Integer idCuentaMayor, String nombre, boolean estado) {
        this.idCuentaMayor = idCuentaMayor;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdCuentaMayor() {
        return idCuentaMayor;
    }

    public void setIdCuentaMayor(Integer idCuentaMayor) {
        this.idCuentaMayor = idCuentaMayor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    public Agrupacion getIdAgrupacion() {
        return idAgrupacion;
    }

    public void setIdAgrupacion(Agrupacion idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaMayor != null ? idCuentaMayor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaMayor)) {
            return false;
        }
        CuentaMayor other = (CuentaMayor) object;
        if ((this.idCuentaMayor == null && other.idCuentaMayor != null) || (this.idCuentaMayor != null && !this.idCuentaMayor.equals(other.idCuentaMayor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.CuentaMayor[ idCuentaMayor=" + idCuentaMayor + " ]";
    }
    
}
