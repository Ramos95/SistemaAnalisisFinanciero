/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.definiciones;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zywel
 */
@Entity
@Table(name = "estado_financiero", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoFinanciero.findAll", query = "SELECT e FROM EstadoFinanciero e"),
    @NamedQuery(name = "EstadoFinanciero.obtenerTodos", query = "SELECT e FROM EstadoFinanciero e WHERE e.idTipoEstadoFinanciero.idTipoEstadoFinanciero = 2"),
    @NamedQuery(name = "EstadoFinanciero.findByIdEstadoFinanciero", query = "SELECT e FROM EstadoFinanciero e WHERE e.estadoFinancieroPK.idEstadoFinanciero = :idEstadoFinanciero"),
    @NamedQuery(name = "EstadoFinanciero.findByIdEmpresa", query = "SELECT e FROM EstadoFinanciero e WHERE e.estadoFinancieroPK.idEmpresa = :idEmpresa ORDER BY e.fechaDesde ASC"),
    @NamedQuery(name = "EstadoFinanciero.findByObservaciones", query = "SELECT e FROM EstadoFinanciero e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "EstadoFinanciero.findByFechaDesde", query = "SELECT e FROM EstadoFinanciero e WHERE e.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "EstadoFinanciero.findByFechaHasta", query = "SELECT e FROM EstadoFinanciero e WHERE e.fechaHasta = :fechaHasta")})
public class EstadoFinanciero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoFinancieroPK estadoFinancieroPK;
    @Column(name = "observaciones", length = 255)
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "fecha_desde", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @Column(name = "fecha_hasta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;
    @JoinColumn(name = "id_tipo_estado_financiero", referencedColumnName = "id_tipo_estado_financiero", nullable = false)
    @ManyToOne(optional = false)
    private TipoEstadoFinanciero idTipoEstadoFinanciero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoFinanciero")
    private List<DetalleEstadoFinanciero> detalleEstadoFinancieroList;

    public EstadoFinanciero() {
    }

    public EstadoFinanciero(EstadoFinancieroPK estadoFinancieroPK) {
        this.estadoFinancieroPK = estadoFinancieroPK;
    }

    public EstadoFinanciero(EstadoFinancieroPK estadoFinancieroPK, Date fechaDesde, Date fechaHasta) {
        this.estadoFinancieroPK = estadoFinancieroPK;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public EstadoFinanciero(int idEstadoFinanciero, int idEmpresa) {
        this.estadoFinancieroPK = new EstadoFinancieroPK(idEstadoFinanciero, idEmpresa);
    }

    public EstadoFinancieroPK getEstadoFinancieroPK() {
        return estadoFinancieroPK;
    }

    public void setEstadoFinancieroPK(EstadoFinancieroPK estadoFinancieroPK) {
        this.estadoFinancieroPK = estadoFinancieroPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaDesde() {
        return fechaDesde;
        //return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoEstadoFinanciero getIdTipoEstadoFinanciero() {
        return idTipoEstadoFinanciero;
    }

    public void setIdTipoEstadoFinanciero(TipoEstadoFinanciero idTipoEstadoFinanciero) {
        this.idTipoEstadoFinanciero = idTipoEstadoFinanciero;
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
        hash += (estadoFinancieroPK != null ? estadoFinancieroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoFinanciero)) {
            return false;
        }
        EstadoFinanciero other = (EstadoFinanciero) object;
        if ((this.estadoFinancieroPK == null && other.estadoFinancieroPK != null) || (this.estadoFinancieroPK != null && !this.estadoFinancieroPK.equals(other.estadoFinancieroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.EstadoFinanciero[ estadoFinancieroPK=" + estadoFinancieroPK + " ]";
    }
    
}
