/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.others;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author olmos
 */
@XmlRootElement
public class RazonFinaciera implements Serializable {

    private String nombre;
    private String formula;
    private String significado;
    private String interpretacion;

    public RazonFinaciera() {
    }

    public RazonFinaciera(String nombre, String formula, String significado, String interpretacion) {
        this.nombre = nombre;
        this.formula = formula;
        this.significado = significado;
        this.interpretacion = interpretacion;
    }

    public String getInterpretacion() {
        return interpretacion;
    }

    public void setInterpretacion(String interpretacion) {
        this.interpretacion = interpretacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }
        
}
