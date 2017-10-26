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
@Table(name = "agrupacion", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrupacion.findAll", query = "SELECT a FROM Agrupacion a"),
    @NamedQuery(name = "Agrupacion.findByIdAgrupacion", query = "SELECT a FROM Agrupacion a WHERE a.idAgrupacion = :idAgrupacion"),
    @NamedQuery(name = "Agrupacion.findByNombre", query = "SELECT a FROM Agrupacion a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Agrupacion.obtenerPorElementoFinanciero", query = "SELECT a FROM Agrupacion a WHERE a.idElementoFinanciero.idElementoFinanciero = :idElementoFinanciero"),
    @NamedQuery(name = "Agrupacion.findByDescripcion", query = "SELECT a FROM Agrupacion a WHERE a.descripcion = :descripcion")})
public class Agrupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_agrupacion", nullable = false)
    private Integer idAgrupacion;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @JoinColumn(name = "id_elemento_financiero", referencedColumnName = "id_elemento_financiero", nullable = false)
    @ManyToOne(optional = false)
    private ElementoFinanciero idElementoFinanciero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgrupacion")
    private List<CuentaMayor> cuentaMayorList;

    public Agrupacion() {
    }

    public Agrupacion(Integer idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
    }

    public Agrupacion(Integer idAgrupacion, String nombre) {
        this.idAgrupacion = idAgrupacion;
        this.nombre = nombre;
    }

    public Integer getIdAgrupacion() {
        return idAgrupacion;
    }

    public void setIdAgrupacion(Integer idAgrupacion) {
        this.idAgrupacion = idAgrupacion;
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

    public ElementoFinanciero getIdElementoFinanciero() {
        return idElementoFinanciero;
    }

    public void setIdElementoFinanciero(ElementoFinanciero idElementoFinanciero) {
        this.idElementoFinanciero = idElementoFinanciero;
    }

    @XmlTransient
    public List<CuentaMayor> getCuentaMayorList() {
        return cuentaMayorList;
    }

    public void setCuentaMayorList(List<CuentaMayor> cuentaMayorList) {
        this.cuentaMayorList = cuentaMayorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgrupacion != null ? idAgrupacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agrupacion)) {
            return false;
        }
        Agrupacion other = (Agrupacion) object;
        if ((this.idAgrupacion == null && other.idAgrupacion != null) || (this.idAgrupacion != null && !this.idAgrupacion.equals(other.idAgrupacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.Agrupacion[ idAgrupacion=" + idAgrupacion + " ]";
    }
    
}
