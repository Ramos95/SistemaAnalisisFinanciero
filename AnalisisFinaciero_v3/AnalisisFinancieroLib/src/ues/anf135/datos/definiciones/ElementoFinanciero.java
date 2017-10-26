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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "elemento_financiero", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementoFinanciero.findAll", query = "SELECT e FROM ElementoFinanciero e"),
    @NamedQuery(name = "ElementoFinanciero.findByIdElementoFinanciero", query = "SELECT e FROM ElementoFinanciero e WHERE e.idElementoFinanciero = :idElementoFinanciero"),
    @NamedQuery(name = "ElementoFinanciero.obtenerPorEstadoResultados", query = "SELECT e FROM ElementoFinanciero e WHERE e.idElementoFinanciero >= 4 "),
    @NamedQuery(name = "ElementoFinanciero.obtenerPorBalanceGeneral", query = "SELECT e FROM ElementoFinanciero e WHERE e.idElementoFinanciero BETWEEN 1 AND 3"),
    @NamedQuery(name = "ElementoFinanciero.findByNombre", query = "SELECT e FROM ElementoFinanciero e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ElementoFinanciero.findByDescripcion", query = "SELECT e FROM ElementoFinanciero e WHERE e.descripcion = :descripcion")})
public class ElementoFinanciero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_elemento_financiero", nullable = false)
    private Integer idElementoFinanciero;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElementoFinanciero")
    private List<Agrupacion> agrupacionList;

    public ElementoFinanciero() {
    }

    public ElementoFinanciero(Integer idElementoFinanciero) {
        this.idElementoFinanciero = idElementoFinanciero;
    }

    public ElementoFinanciero(Integer idElementoFinanciero, String nombre) {
        this.idElementoFinanciero = idElementoFinanciero;
        this.nombre = nombre;
    }

    public Integer getIdElementoFinanciero() {
        return idElementoFinanciero;
    }

    public void setIdElementoFinanciero(Integer idElementoFinanciero) {
        this.idElementoFinanciero = idElementoFinanciero;
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

    @XmlTransient
    public List<Agrupacion> getAgrupacionList() {
        return agrupacionList;
    }

    public void setAgrupacionList(List<Agrupacion> agrupacionList) {
        this.agrupacionList = agrupacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElementoFinanciero != null ? idElementoFinanciero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementoFinanciero)) {
            return false;
        }
        ElementoFinanciero other = (ElementoFinanciero) object;
        if ((this.idElementoFinanciero == null && other.idElementoFinanciero != null) || (this.idElementoFinanciero != null && !this.idElementoFinanciero.equals(other.idElementoFinanciero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.ElementoFinanciero[ idElementoFinanciero=" + idElementoFinanciero + " ]";
    }

}
