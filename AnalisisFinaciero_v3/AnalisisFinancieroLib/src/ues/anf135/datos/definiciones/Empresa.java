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
@Table(name = "empresa", catalog = "financiero", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "Empresa.findByNit", query = "SELECT e FROM Empresa e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empresa.findByNombreCorto", query = "SELECT e FROM Empresa e WHERE e.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "Empresa.findByNombreLegal", query = "SELECT e FROM Empresa e WHERE e.nombreLegal = :nombreLegal"),
    @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresa.findByTelefonos", query = "SELECT e FROM Empresa e WHERE e.telefonos = :telefonos"),
    @NamedQuery(name = "Empresa.findByCorreo", query = "SELECT e FROM Empresa e WHERE e.correo = :correo"),
    @NamedQuery(name = "Empresa.findByRepresentanteLegal", query = "SELECT e FROM Empresa e WHERE e.representanteLegal = :representanteLegal"),
    @NamedQuery(name = "Empresa.findByContador", query = "SELECT e FROM Empresa e WHERE e.contador = :contador"),
    @NamedQuery(name = "Empresa.findByAuditor", query = "SELECT e FROM Empresa e WHERE e.auditor = :auditor"),
    @NamedQuery(name = "Empresa.findByNrc", query = "SELECT e FROM Empresa e WHERE e.nrc = :nrc"),
    @NamedQuery(name = "Empresa.findByIsss", query = "SELECT e FROM Empresa e WHERE e.isss = :isss"),
    @NamedQuery(name = "Empresa.findByAfp", query = "SELECT e FROM Empresa e WHERE e.afp = :afp"),
    @NamedQuery(name = "Empresa.findByEstado", query = "SELECT e FROM Empresa e WHERE e.estado = :estado")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Basic(optional = false)
    @Column(name = "nit", nullable = false, length = 17)
    private String nit;
    @Basic(optional = false)
    @Column(name = "nombre_corto", nullable = false, length = 255)
    private String nombreCorto;
    @Basic(optional = false)
    @Column(name = "nombre_legal", nullable = false, length = 255)
    private String nombreLegal;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefonos", nullable = false, length = 2147483647)
    private String telefonos;
    @Basic(optional = false)
    @Column(name = "correo", nullable = false, length = 255)
    private String correo;
    @Basic(optional = false)
    @Column(name = "representante_legal", nullable = false, length = 255)
    private String representanteLegal;
    @Basic(optional = false)
    @Column(name = "contador", nullable = false, length = 255)
    private String contador;
    @Basic(optional = false)
    @Column(name = "auditor", nullable = false, length = 255)
    private String auditor;
    @Basic(optional = false)
    @Column(name = "nrc", nullable = false, length = 8)
    private String nrc;
    @Basic(optional = false)
    @Column(name = "isss", nullable = false)
    private int isss;
    @Basic(optional = false)
    @Column(name = "afp", nullable = false)
    private int afp;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @JoinColumn(name = "tipo_empresa_id_tipo_empresa", referencedColumnName = "id_tipo_empresa", nullable = false)
    @ManyToOne(optional = false)
    private TipoEmpresa tipoEmpresaIdTipoEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<EstadoFinanciero> estadoFinancieroList;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(Integer idEmpresa, String nit, String nombreCorto, String nombreLegal, String direccion, String telefonos, String correo, String representanteLegal, String contador, String auditor, String nrc, int isss, int afp, boolean estado) {
        this.idEmpresa = idEmpresa;
        this.nit = nit;
        this.nombreCorto = nombreCorto;
        this.nombreLegal = nombreLegal;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.correo = correo;
        this.representanteLegal = representanteLegal;
        this.contador = contador;
        this.auditor = auditor;
        this.nrc = nrc;
        this.isss = isss;
        this.afp = afp;
        this.estado = estado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getNombreLegal() {
        return nombreLegal;
    }

    public void setNombreLegal(String nombreLegal) {
        this.nombreLegal = nombreLegal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public int getIsss() {
        return isss;
    }

    public void setIsss(int isss) {
        this.isss = isss;
    }

    public int getAfp() {
        return afp;
    }

    public void setAfp(int afp) {
        this.afp = afp;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public TipoEmpresa getTipoEmpresaIdTipoEmpresa() {
        return tipoEmpresaIdTipoEmpresa;
    }

    public void setTipoEmpresaIdTipoEmpresa(TipoEmpresa tipoEmpresaIdTipoEmpresa) {
        this.tipoEmpresaIdTipoEmpresa = tipoEmpresaIdTipoEmpresa;
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
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
