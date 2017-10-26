/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.others;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author olmos
 */
@Named(value = "controllerInfo")
@ViewScoped
public class ControllerInfo implements Serializable {

    private List<RazonFinaciera> razonesLiquidezList;
    private List<RazonFinaciera> razonesActividadList;
    private List<RazonFinaciera> razonesEndeudamientoList;
    private List<RazonFinaciera> razonesRentabilidadList;

    public ControllerInfo() {
    }

    @PostConstruct
    public void inicializar() {
        generarListaLiquidez();
        generarListaActividad();
        generarListaEndeudamiento();
        generarListaRentabilidad();
    }

    public void generarListaLiquidez() {
        setRazonesLiquidezList(new ArrayList<>());
        RazonFinaciera razon = new RazonFinaciera();
        razon.setNombre("Liquidez Corriente");
        razon.setFormula("PASIVOS CORRIENTES / ACTIVOS CORRIENTES");
        razon.setSignificado("Capacidad de pago en el corto plazo.");
        razon.setInterpretacion("A mayor razón, mejor posición de liquidez, aunque podría representar poco nivel de endeudamiento a corto plazo o exceso de activos circulantes.");
        razonesLiquidezList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Prueba Ácida");
        razon.setFormula("(ACTIVOS CORRIENTES - INVENTARIO) / PASIVOS CORRIENTES");
        razon.setSignificado("Capacidad de pago en el corto plazo con recursos convertibles rápidamente en dinero.");
        razon.setInterpretacion("A mayor razón, mejor posición de liquidez, aunque posea una razón alta, podría representar poco endeudamiento a corto plazo o exceso de activos circulantes.");
        razonesLiquidezList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Capital de Trabajo");
        razon.setFormula("ACTIVOS CORRIENTES - PASIVOS CORRIENTES");
        razon.setSignificado("Es el excedente o déficit de los activos realizables a corto plazo, después de cancelar las deudas a corto plazo para responder a las necesidades de operación.");
        razon.setInterpretacion("A mayor razón, mejor posición de liquidez, es decir, que se cuenta con más recursos para hacer frente a las obligaciones de la empresa.");
        razonesLiquidezList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Nivel de dependencia de Inventarios");
        razon.setFormula("[PASIVOS CORRIENTES - (CAJA + BANCOS + CARTERA + VALORES REALIZABLES)] / INVENTARIO");
        razon.setSignificado("Porcentaje mínimo del valor de los inventarios, que deberá ser convertido a efectivo, después de liquidar la caja, bancos, las cuentas por cobrar y los valores realizables, para cubrir los pasivos a corto plazo.");
        razon.setInterpretacion("A mayor razón, mayor será la dependencia de los inventario para poder saldar los pasivos a corto plazo.");
        razonesLiquidezList.add(razon);
    }

    public void generarListaActividad() {
        setRazonesActividadList(new ArrayList<>());
        RazonFinaciera razon = new RazonFinaciera();
        razon.setNombre("Numero de Días Cartera a Mano");
        razon.setFormula("CUENTAS POR COBRAR * 360 / VENTAS NETAS");
        razon.setSignificado("Mide el tiempo promedio concedido a Los clientes, como plazo para pagar el crédito.");
        razon.setInterpretacion("A mayor Razón mayor número de días o mayor solvencia de la empresa para dar crédito.");
        razonesActividadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Número de días Inventario a Mano");
        razon.setFormula("INVENTARIO * 360 / COSTO DE LO VENDIDO");
        razon.setSignificado("Representa el número de días que tiene la empresa en inventario, para atender la demanda de sus productos.");
        razon.setInterpretacion("A mayor razón mayor número de días o solvencia de la empresa para atender la demanda de sus clientes.");
        razonesActividadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Rotación de Activos Totales");
        razon.setFormula("TOTAL VENTAS / ACTIVOS TOTALES");
        razon.setSignificado("Mide la efectividad con que la empresa utiliza sus activos para generar ventas.");
        razon.setInterpretacion("A mayor razón mayor existe una mayor eficiencia en la utilización de los activos.");
        razonesActividadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Rotación de Inventarios");
        razon.setFormula("COSTO DE VENTAS / INVENTARIO");
        razon.setSignificado("Veces que el inventario se vende o se renueva en el periodo. También mide la liquidez del inventario.");
        razon.setInterpretacion("A mayor razón, mayor eficiencia en administración de inventarios. También puede representar bajo nivel de inventarios que puede provocar pérdidas de ventas.");
        razonesActividadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Ciclo Operativo");
        razon.setFormula("NÚMERO DE DIAS INVENTARIO A MANO + NÚMERO DE DIAS CARTERA A MANO");
        razon.setSignificado("Es la suma del número de días cartera a mano y el número de días inventario a mano.");
        razon.setInterpretacion("A mayor sumatoria mayor será el periodo que la empresa tiene para dar credito y atender a la demanda de sus clientes.");
        razonesActividadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Rotación de Cartera");
        razon.setFormula("360 / NÚMERO DE DIAS CARTERA A MANO");
        razon.setSignificado("Nos indica el número de veces que el total de las cuentas comerciales por cobrar, son convertidas a efectivo durante el año.");
        razon.setInterpretacion("A mayor razón, más son las veces al año que las cuentas por cobrar son convertidas en efectivo.");
        razonesActividadList.add(razon);
    }

    public void generarListaEndeudamiento() {
        setRazonesEndeudamientoList(new ArrayList<>());
        RazonFinaciera razon = new RazonFinaciera();
        razon.setNombre("Endeudamiento Sobre Activos Totales");
        razon.setFormula("PASIVOS TOTALES / ACTIVOS TOTALES");
        razon.setSignificado("Es la cantidad de dólares que la empresa adeuda a sus proveedores, por cada dólar que posee en activos.");
        razon.setInterpretacion("A mayor razón, mayor es la cantidad de recursos de terceros que la empresa utiliza para tratar de generar utilidades.");
        razonesEndeudamientoList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Apalancamiento");
        razon.setFormula("PASIVOS TOTALES / CAPITAL SOCIAL");
        razon.setSignificado("Es la cantidad de veces que la empresa tiene comprometido su patrimonio con sus proveedores.");
        razon.setInterpretacion("A mayor razón, más riesgo corre la entidad de sobre endeudarse. Entre una y dos veces es un riesgo medio o aceptable.");
        razonesEndeudamientoList.add(razon);
    }

    public void generarListaRentabilidad() {
        setRazonesRentabilidadList(new ArrayList<>());
        RazonFinaciera razon = new RazonFinaciera();
        razon.setNombre("Margen de Utilidad Neta");
        razon.setFormula("UAII / VENTAS NETAS");
        razon.setSignificado("Porcentaje de las ventas que queda a la empresa, luego de deducir todos sus costos y gastos.");
        razon.setInterpretacion("A mayor razón, mayor capacidad de la empresa para generar utilidades.");
        razonesRentabilidadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Margen de Utilidad Bruta");
        razon.setFormula("UTILIDAD BRUTA / VENTAS NETAS");
        razon.setSignificado("Muestra el porcentaje de las ventas que le queda a la empresa, luego de pagar el costo de las mismas.");
        razon.setInterpretacion("A mayor razón, menor el el costo relativo de los artículos vendidos y mejor capacidad para generar utilidad antes de gastos de operación.");
        razonesRentabilidadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Margen de Utilidad Operativa");
        razon.setFormula("UTILIDAD OPERATIVA / VENTAS NETAS");
        razon.setSignificado("Representa cual ha sido la eficiencia de la empresa de generar ingresos durante un periodo determinado.");
        razon.setInterpretacion("A mayor razón, mayor eficiencia existe de la empresa para generar utilidad con respecto a las ventas.");
        razonesRentabilidadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Rendimiento sobre Activos (ROA, ROI)");
        razon.setFormula("UAII / ACTIVOS TOTALES");
        razon.setSignificado("Capacidad de generar utilidades de recursos invertidos en la empresa (ROA: Return on Assets).");
        razon.setInterpretacion("A mayor razón, mayor es la capacidad de los activos de la empresa para generar utilidades.");
        razonesRentabilidadList.add(razon);
        razon = new RazonFinaciera();
        razon.setNombre("Rendimiento sobre Capital (ROE)");
        razon.setFormula("UAII / CAPITAL CONTABLE");
        razon.setSignificado("Rendimiento obtenido sobre inversión de los dueños de la empresa (ROE: Return on Equity).");
        razon.setInterpretacion("A mayor razón, mejor es el rendimiento obtenido por los propietarios por los recursos invertidos en la empresa.");
        razonesRentabilidadList.add(razon);
    }

    public List<RazonFinaciera> getRazonesLiquidezList() {
        return razonesLiquidezList;
    }

    public void setRazonesLiquidezList(List<RazonFinaciera> razonesLiquidezList) {
        this.razonesLiquidezList = razonesLiquidezList;
    }

    public List<RazonFinaciera> getRazonesActividadList() {
        return razonesActividadList;
    }

    public void setRazonesActividadList(List<RazonFinaciera> razonesActividadList) {
        this.razonesActividadList = razonesActividadList;
    }

    public List<RazonFinaciera> getRazonesEndeudamientoList() {
        return razonesEndeudamientoList;
    }

    public void setRazonesEndeudamientoList(List<RazonFinaciera> razonesEndeudamientoList) {
        this.razonesEndeudamientoList = razonesEndeudamientoList;
    }

    public List<RazonFinaciera> getRazonesRentabilidadList() {
        return razonesRentabilidadList;
    }

    public void setRazonesRentabilidadList(List<RazonFinaciera> razonesRentabilidadList) {
        this.razonesRentabilidadList = razonesRentabilidadList;
    }

}
