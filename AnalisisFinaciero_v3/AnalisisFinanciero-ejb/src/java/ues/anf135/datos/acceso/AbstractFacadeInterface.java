/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Olmos
 */
public interface AbstractFacadeInterface<T> {

    boolean crear(T entity);

    T create(T entity);
    
    boolean editar(T entity);

    T edit(T entity);
    
    boolean eliminar(T entity);

    T remove(T entity);
    
    T find(Object id);

    List<T> findAll();

    List<T> findRange(int[] range);
    
    List<T> findRango(int desde, int tamanioPagina);
    
    List<T> findRango(int desde, int tamanioPagina, String orderField);

    int count();
    
    List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina);
    
    List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina, String orderField);
    
    int countByDataFilters(Map<String, Object> filtros);
    
}
