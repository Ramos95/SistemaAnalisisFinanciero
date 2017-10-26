/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import org.primefaces.model.LazyDataModel;
import ues.anf135.datos.acceso.AbstractFacadeInterface;
/**
 *
 * @author Olmos
 */
public abstract class DefaultGenerator<T> {

    protected LazyDataModel<T> modelo;
    protected T registro;
    protected int tamanioPagina;
    
    protected abstract AbstractFacadeInterface<T> getFacade();
    
    protected abstract void generarListas();
    
    protected abstract void generarModeloTabla();
    
    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public int getTamanioPagina() {
        return tamanioPagina;
    }

    public void setTamanioPagina(int tamanioPagina) {
        this.tamanioPagina = tamanioPagina;
    }
     
    
    
}
