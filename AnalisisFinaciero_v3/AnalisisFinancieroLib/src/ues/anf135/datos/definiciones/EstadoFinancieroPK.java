/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author zywel
 */
@Embeddable
public class EstadoFinancieroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_estado_financiero", nullable = false)
    private int idEstadoFinanciero;
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private int idEmpresa;

    public EstadoFinancieroPK() {
    }

    public EstadoFinancieroPK(int idEstadoFinanciero, int idEmpresa) {
        this.idEstadoFinanciero = idEstadoFinanciero;
        this.idEmpresa = idEmpresa;
    }

    public int getIdEstadoFinanciero() {
        return idEstadoFinanciero;
    }

    public void setIdEstadoFinanciero(int idEstadoFinanciero) {
        this.idEstadoFinanciero = idEstadoFinanciero;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEstadoFinanciero;
        hash += (int) idEmpresa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoFinancieroPK)) {
            return false;
        }
        EstadoFinancieroPK other = (EstadoFinancieroPK) object;
        if (this.idEstadoFinanciero != other.idEstadoFinanciero) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.EstadoFinancieroPK[ idEstadoFinanciero=" + idEstadoFinanciero + ", idEmpresa=" + idEmpresa + " ]";
    }
    
}
