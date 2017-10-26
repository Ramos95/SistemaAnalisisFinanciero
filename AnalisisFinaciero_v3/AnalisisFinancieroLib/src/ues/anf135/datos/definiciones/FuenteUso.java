    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.definiciones;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author olmos
 */
@XmlRootElement
public class FuenteUso implements Serializable {

    private Cuenta cuenta;
    private Empresa empresa;
    private double saldoFecha1;
    private double saldoFecha2;
    private double diferencia;
    private double fuente;
    private double uso;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public double getSaldoFecha1() {
        return saldoFecha1;
    }

    public void setSaldoFecha1(double saldoFecha1) {
        this.saldoFecha1 = saldoFecha1;
    }

    public double getSaldoFecha2() {
        return saldoFecha2;
    }

    public void setSaldoFecha2(double saldoFecha2) {
        this.saldoFecha2 = saldoFecha2;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public double getFuente() {
        return fuente;
    }

    public void setFuente(double fuente) {
        this.fuente = fuente;
    }

    public double getUso() {
        return uso;
    }

    public void setUso(double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "ues.anf135.datos.definiciones.FuenteUso{" + "cuenta=" + this.cuenta.toString() + ", empresa=" + this.empresa.toString() + '}';
    }
    
}
