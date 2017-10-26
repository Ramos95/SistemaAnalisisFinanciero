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
public class DetalleEstadoFinancieroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_estado_financiero", nullable = false)
    private int idEstadoFinanciero;
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private int idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_cuenta", nullable = false)
    private int idCuenta;

    public DetalleEstadoFinancieroPK() {
    }

    public DetalleEstadoFinancieroPK(int idEstadoFinanciero, int idEmpresa, int idCuenta) {
        this.idEstadoFinanciero = idEstadoFinanciero;
        this.idEmpresa = idEmpresa;
        this.idCuenta = idCuenta;
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

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEstadoFinanciero;
        hash += (int) idEmpresa;
        hash += (int) idCuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEstadoFinancieroPK)) {
            return false;
        }
        DetalleEstadoFinancieroPK other = (DetalleEstadoFinancieroPK) object;
        if (this.idEstadoFinanciero != other.idEstadoFinanciero) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idCuenta != other.idCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.DetalleEstadoFinancieroPK[ idEstadoFinanciero=" + idEstadoFinanciero + ", idEmpresa=" + idEmpresa + ", idCuenta=" + idCuenta + " ]";
    }
    
}
