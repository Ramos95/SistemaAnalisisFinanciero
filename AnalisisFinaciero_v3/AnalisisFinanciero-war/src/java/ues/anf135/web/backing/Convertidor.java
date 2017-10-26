/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ues.anf135.datos.acceso.CuentaFacadeLocal;
import ues.anf135.datos.definiciones.Cuenta;

/**
 *
 * @author zywel
 */
@FacesConverter(value = "convertidor")
public class Convertidor implements Converter {

    @EJB
    CuentaFacadeLocal cuentaFacade;

    @Override
    public Object getAsObject(FacesContext contet, UIComponent component, String value) {
        if (value != null || !value.isEmpty()) {
            try {
                if (cuentaFacade != null) {
                    System.out.println("---> C - getAsString: value = " + value.toString());
                    List<Cuenta> lista = cuentaFacade.findAll();

                    if (lista != null && !lista.isEmpty()) {
                        for (Cuenta cuenta : lista) {
                            if (cuenta.toString().equals(value)) {
                                System.out.println("return getAsObject: " + cuenta.toString());
                                return cuenta;
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Error getAsObject CuentaConveter");
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext contet, UIComponent component, Object value) {
        if (value != null) {
            try {
                System.out.println("---> C - getAsString: value = " + value.toString());
                return value.toString();
            } catch (Exception e) {
                System.out.println("Error getAsString CuentaConveter");
            }
        }

        return null;
    }
}

