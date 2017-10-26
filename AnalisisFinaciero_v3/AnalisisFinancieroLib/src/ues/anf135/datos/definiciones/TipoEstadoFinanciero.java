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
@Table(name = "tipo_estado_financiero", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstadoFinanciero.findAll", query = "SELECT t FROM TipoEstadoFinanciero t"),
    @NamedQuery(name = "TipoEstadoFinanciero.findByIdTipoEstadoFinanciero", query = "SELECT t FROM TipoEstadoFinanciero t WHERE t.idTipoEstadoFinanciero = :idTipoEstadoFinanciero"),
    @NamedQuery(name = "TipoEstadoFinanciero.findByNombre", query = "SELECT t FROM TipoEstadoFinanciero t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoEstadoFinanciero.findByDescripcion", query = "SELECT t FROM TipoEstadoFinanciero t WHERE t.descripcion = :descripcion")})
public class TipoEstadoFinanciero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_estado_financiero", nullable = false)
    private Integer idTipoEstadoFinanciero;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 2147483647)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEstadoFinanciero")
    private List<EstadoFinanciero> estadoFinancieroList;

    public TipoEstadoFinanciero() {
    }

    public TipoEstadoFinanciero(Integer idTipoEstadoFinanciero) {
        this.idTipoEstadoFinanciero = idTipoEstadoFinanciero;
    }

    public TipoEstadoFinanciero(Integer idTipoEstadoFinanciero, String nombre) {
        this.idTipoEstadoFinanciero = idTipoEstadoFinanciero;
        this.nombre = nombre;
    }

    public Integer getIdTipoEstadoFinanciero() {
        return idTipoEstadoFinanciero;
    }

    public void setIdTipoEstadoFinanciero(Integer idTipoEstadoFinanciero) {
        this.idTipoEstadoFinanciero = idTipoEstadoFinanciero;
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
    public List<EstadoFinanciero> getEstadoFinancieroList() {
        return estadoFinancieroList;
    }

    public void setEstadoFinancieroList(List<EstadoFinanciero> estadoFinancieroList) {
        this.estadoFinancieroList = estadoFinancieroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEstadoFinanciero != null ? idTipoEstadoFinanciero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstadoFinanciero)) {
            return false;
        }
        TipoEstadoFinanciero other = (TipoEstadoFinanciero) object;
        if ((this.idTipoEstadoFinanciero == null && other.idTipoEstadoFinanciero != null) || (this.idTipoEstadoFinanciero != null && !this.idTipoEstadoFinanciero.equals(other.idTipoEstadoFinanciero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.TipoEstadoFinanciero[ idTipoEstadoFinanciero=" + idTipoEstadoFinanciero + " ]";
    }
    
}
