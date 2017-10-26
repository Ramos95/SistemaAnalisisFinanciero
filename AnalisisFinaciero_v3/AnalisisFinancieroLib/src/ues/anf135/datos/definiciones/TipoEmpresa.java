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
@Table(name = "tipo_empresa", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmpresa.findAll", query = "SELECT t FROM TipoEmpresa t"),
    @NamedQuery(name = "TipoEmpresa.findByIdTipoEmpresa", query = "SELECT t FROM TipoEmpresa t WHERE t.idTipoEmpresa = :idTipoEmpresa"),
    @NamedQuery(name = "TipoEmpresa.findByNombre", query = "SELECT t FROM TipoEmpresa t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoEmpresa.findByDescripcion", query = "SELECT t FROM TipoEmpresa t WHERE t.descripcion = :descripcion")})
public class TipoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_empresa", nullable = false)
    private Integer idTipoEmpresa;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEmpresaIdTipoEmpresa")
    private List<Empresa> empresaList;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public TipoEmpresa(Integer idTipoEmpresa, String nombre) {
        this.idTipoEmpresa = idTipoEmpresa;
        this.nombre = nombre;
    }

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
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
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEmpresa != null ? idTipoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpresa)) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) object;
        if ((this.idTipoEmpresa == null && other.idTipoEmpresa != null) || (this.idTipoEmpresa != null && !this.idTipoEmpresa.equals(other.idTipoEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.TipoEmpresa[ idTipoEmpresa=" + idTipoEmpresa + " ]";
    }
    
}
