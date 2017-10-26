/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ues.anf135.datos.acceso.DetalleEstadoFinancieroFacadeLocal;
import ues.anf135.datos.acceso.EmpresaFacadeLocal;
import ues.anf135.datos.acceso.EstadoFinancieroFacadeLocal;
import ues.anf135.datos.calculo.FuentesUsosFacadeLocal;
import ues.anf135.datos.calculo.IndicadoresFacadeLocal;
import ues.anf135.datos.definiciones.Empresa;
import ues.anf135.datos.definiciones.FuenteUso;

/**
 *
 * @author olmos
 */
@Named(value = "controllerIndicadores")
@ViewScoped
public class ControllerIndicadores implements Serializable {

   @EJB
    private DetalleEstadoFinancieroFacadeLocal defFacade;

    @EJB
    private EmpresaFacadeLocal empresaFacade;

    @EJB
    private FuentesUsosFacadeLocal fuentesUsosFacade;

    @EJB
    private EstadoFinancieroFacadeLocal estadoFinancieroFacade;

    @EJB
    private IndicadoresFacadeLocal indicadoresFacade;

    private List<Empresa> lstEmpresas;
    private int idEmpresa;

    private Date fecha;
    private int anio;

    private double fuentes = 0.0;
    private double usos = 0.0;

    // <editor-fold desc="Booleans Define." defaultstate="collapsed">
    // Liquidez
    private boolean liquidezCorrienteVisible;
    private boolean pruebaAcidaVisible;
    private boolean capitalTrabajoVisible;
    private boolean nivelDependenciaInventarioVisible;
    // Actividad 
    private boolean numeroDiasCarteraManoVisible;
    private boolean numeroDiasInventarioManoVisible;
    private boolean rotacionInventarioVisible;
    private boolean cicloOperativoVisible;
    private boolean rotacionCarteraVisible;
    private boolean rotacionActivoTotalVisible;
    // Endeudamiento
    private boolean endeudamientoSobreActivosTotalesVisible;
    private boolean apalancamientoVisible;
    // Rentabilidad
    private boolean margenUtilidadNetaVisible;
    private boolean margenUtilidadBrutaVisible;
    private boolean margenUtilidadOperativaVisible;
    private boolean margenUtilidadAntesImpuestosReservaVisible;
    private boolean roiVisible;
    private boolean roiOperativoVisible;
    private boolean roeVisible;

    // Liquidez
    private boolean liquidezCorrienteSelected;
    private boolean pruebaAcidaSelected;
    private boolean capitalTrabajoSelected;
    private boolean nivelDependenciaInventarioSelected;
    // Actividad
    private boolean numeroDiasCarteraManoSelected;
    private boolean numeroDiasInventarioManoSelected;
    private boolean rotacionInventarioSelected;
    private boolean cicloOperativoSelected;
    private boolean rotacionCarteraSelected;
    private boolean rotacionActivoTotalSelected;
    // Endeudamiento
    private boolean endeudamientoSobreActivosTotalesSelected;
    private boolean apalancamientoSelected;
    // Rentabilidad
    private boolean margenUtilidadNetaSelected;
    private boolean margenUtilidadOperativaSelected;
    private boolean margenUtilidadBrutaSelected;
    private boolean margenUtilidadAntesImpuestosReservaSelected;
    private boolean roiSelected;
    private boolean roiOperativoSelected;
    private boolean roeSelected;
    // </editor-fold>

    private List<FuenteUso> lstFuentesUsos;
    private List<Date> lstFechas;
    private List<String> lstAnios;
    private int anioInicio;
    private int anioFin;

    @PostConstruct
    public void inicializar() {
        setAnio(0);
        setAnioInicio(0);
        setAnioFin(0);
        setIdEmpresa(1);
        inicializarEstados();
        llenarListasFechas();
        llenarListaEmpresas();
    }

    public void inicializarEstados() {
        // Liquidez
        liquidezCorrienteVisible = true;
        pruebaAcidaVisible = true;
        capitalTrabajoVisible = true;
        nivelDependenciaInventarioVisible = true;
        // Actividad 
        numeroDiasCarteraManoVisible = true;
        numeroDiasInventarioManoVisible = true;
        rotacionInventarioVisible = true;
        cicloOperativoVisible = true;
        rotacionCarteraVisible = true;
        rotacionActivoTotalVisible = true;
        // Endeudamiento
        endeudamientoSobreActivosTotalesVisible = true;
        apalancamientoVisible = true;
        // Rentabilidad
        margenUtilidadNetaVisible = true;
        margenUtilidadBrutaVisible = true;
        margenUtilidadOperativaVisible = true;
        roiVisible = true;
        setRoiOperativoVisible(true);
        roeVisible = true;

        // Liquidez
        liquidezCorrienteSelected = true;
        pruebaAcidaSelected = true;
        capitalTrabajoSelected = true;
        nivelDependenciaInventarioSelected = true;
        // Actividad
        numeroDiasCarteraManoSelected = true;
        numeroDiasInventarioManoSelected = true;
        rotacionInventarioSelected = true;
        cicloOperativoSelected = true;
        rotacionCarteraSelected = true;
        rotacionActivoTotalSelected = true;
        // Endeudamiento
        endeudamientoSobreActivosTotalesSelected = true;
        apalancamientoSelected = true;
        // Rentabilidad
        margenUtilidadNetaSelected = true;
        margenUtilidadOperativaSelected = true;
        margenUtilidadBrutaSelected = true;
        roiSelected = true;
        setRoiOperativoSelected(true);
        roeSelected = true;
    }

    public void establecerFuentesUsos() {
        setLstFuentesUsos(fuentesUsos());
    }

    public double pruebaAcida() {
        if (isPruebaAcidaSelected() && isPruebaAcidaVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.pruebaAcida(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.pruebaAcida");
                    e.printStackTrace();
                }
            }
        }

        return 0.0;
    }

    public double liquidezCorriente() {
        if (isLiquidezCorrienteSelected() && isLiquidezCorrienteVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.liquidezCorriente(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.liquidezCorrriente");
                    e.printStackTrace();
                }
            }
        }

        return 0.0;
    }

    public double capitalTrabajo() {
        if (isCapitalTrabajoSelected() && isCapitalTrabajoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.capitalTrabajo(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.capitalTrabajo");
                    e.printStackTrace();
                }
            }
        }

        return 0.0;
    }

    public double nivelDependenciaInventario() {
        if (isNivelDependenciaInventarioSelected() && isNivelDependenciaInventarioVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.nivelDependenciaInventarios(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.nivelDependenciaInventario");
                    e.printStackTrace();
                }
            }
        }

        return 0.0;
    }

    public double numeroDiasCarteraMano() {
        if (isNumeroDiasCarteraManoSelected() && isNumeroDiasCarteraManoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.numeroDiasCarteraMano(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.numeroDiasCarteraMano");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double numeroDiasInventarioMano() {
        if (isNumeroDiasInventarioManoSelected() && isNumeroDiasInventarioManoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.numeroDiasInventarioMano(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.numeroDiasInventarioMano");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double rotacionCartera() {
        if (isRotacionCarteraSelected() && isRotacionCarteraVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.rotacionCartera(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.rotacionCartera");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double rotacionInventario() {
        if (isRotacionInventarioSelected() && isRotacionInventarioVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.rotacionInventario(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.rotacionInventario");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double cicloOperativo() {
        if (isCicloOperativoSelected() && isCicloOperativoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.cicloOperativo(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.cicloOperativo");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double rotacionActivoTotal() {
        if (isRotacionActivoTotalSelected() && isRotacionActivoTotalVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.rotacionActivoTotal(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.rotacionActivoTotal");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double endeudamientoActivoTotal() {
        if (isEndeudamientoSobreActivosTotalesSelected() && isEndeudamientoSobreActivosTotalesVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.endeudamientoActivoTotal(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.endeudamientoActivoTotal");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double endeudamientoLeverage() {
        if (isApalancamientoSelected() && isApalancamientoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.endeudamientoLeverage(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.endeudamientoLeverage");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double margenUtilidadNeta() {
        if (isMargenUtilidadNetaSelected() && isMargenUtilidadNetaVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.margenUtilidadNeta(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.margenUtilidadNeta");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double margenUtilidadOperativa() {
        if (isMargenUtilidadOperativaSelected() && isMargenUtilidadOperativaVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.margenUtilidadOperativa(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.margenUtilidadOperativa");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double margenUtilidadBruta() {
        if (isMargenUtilidadBrutaSelected() && isMargenUtilidadBrutaVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.margenUtilidadBruta(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.margenUtilidadBruta");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double margenUtilidadAntesImpuestosReserva() {
        if (isMargenUtilidadAntesImpuestosReservaSelected() && isMargenUtilidadAntesImpuestosReservaVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.margenUtilidadAntesImpuestoReserva(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.margenUtilidadAntesImpuestosReserva");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double roi() {
        if (isRoiSelected() && isRoiVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.roa(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.roa");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double roiOperativo() {
        if (isRoiOperativoSelected() && isRoiOperativoVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.roaOperativo(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.roaOper");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public double roe() {
        if (isRoeSelected() && isRoeVisible()) {
            if (getIdEmpresa() > 0 && getAnio() > 0) {
                try {
                    return indicadoresFacade.roe(getIdEmpresa(), getAnio());
                } catch (Exception e) {
                    System.out.println("Error... ControllerIndicadores.roe");
                    e.printStackTrace();
                }
            }
        }
        return 0.0;
    }

    public List<FuenteUso> fuentesUsos() {
        List<FuenteUso> salida = null;

        try {
            System.out.println("idEmpresa: " + idEmpresa);
            System.out.println("anioInicio: " + anioInicio);
            System.out.println("anioFin: " + anioFin);
            if (idEmpresa > 0 && anioInicio > 0 && anioFin > 0 && anioInicio < anioFin) {
                Date fechaInicio = null;
                Date fechaFin = null;
                for (Date d : lstFechas) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    if (cal.get(Calendar.YEAR) == anioInicio) {
                        fechaInicio = cal.getTime();
                    } else if (cal.get(Calendar.YEAR) == anioFin) {
                        fechaFin = cal.getTime();
                    }
                }

                if (fechaInicio != null && fechaFin != null) {
                    System.out.println("Datos para generar Fuentes y Usos");
                    System.out.println("idEmpresa: " + idEmpresa);
                    System.out.println("fechaInicio: " + fechaInicio);
                    System.out.println("fechaFin: " + fechaFin);
                    salida = fuentesUsosFacade.obtenerFuentesUsos(fechaInicio, fechaFin, idEmpresa);

                    setFuentes(0.0);
                    setUsos(0.0);

                    for (FuenteUso fuenteUso : salida) {
                        setFuentes(getFuentes() + fuenteUso.getFuente());
                        setUsos(getUsos() + fuenteUso.getUso());
                    }

                    System.out.println("\n");
                    System.out.println("Total Fuentes: " + getFuentes());
                    System.out.println("Total Usos: " + getUsos());
                }
            } else {
                setFuentes(0.0);
                setUsos(0.0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error... ControllerIndicadores.fuentesUsos");
            setFuentes(0.0);
            setUsos(0.0);
        } finally {
            if (salida == null) {
                salida = new ArrayList<>();
            }
        }

        return salida;
    }

    public void llenarListasFechas() {
        if (defFacade != null) {
            lstFechas = estadoFinancieroFacade.obtenerEstadosPorEmpresa(idEmpresa);
            if (lstFechas == null) {
                lstFechas = new ArrayList<>();
            } else {
                Calendar cal = Calendar.getInstance();
                lstAnios = new ArrayList<>();
                for (Date d : lstFechas) {
                    cal.setTime(d);
                    lstAnios.add(String.valueOf(cal.get(Calendar.YEAR)));
                }
            }
        }
    }

    public void llenarListaEmpresas() {
        if (empresaFacade != null) {
            try {
                setLstEmpresas(empresaFacade.findAll());
                if (getLstEmpresas() == null || getLstEmpresas().isEmpty()) {
                    setLstEmpresas(new ArrayList<>());
                }
            } catch (Exception e) {
                System.out.println("Error... ControllerIndicadores.llenarListaEmpresas");
            }
        }
    }

    public List<Empresa> getLstEmpresas() {
        return lstEmpresas;
    }

    public void setLstEmpresas(List<Empresa> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<FuenteUso> getLstFuentesUsos() {
        return lstFuentesUsos;
    }

    public void setLstFuentesUsos(List<FuenteUso> lstFuentesUsos) {
        this.lstFuentesUsos = lstFuentesUsos;
    }

    public List<Date> getLstFechas() {
        return lstFechas;
    }

    public void setLstFechas(List<Date> lstFechas) {
        this.lstFechas = lstFechas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<String> getLstAnios() {
        return lstAnios;
    }

    public void setLstAnios(List<String> lstAnios) {
        this.lstAnios = lstAnios;
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }

    public int getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(int anioFin) {
        this.anioFin = anioFin;
    }

    // <editor-fold desc="Getter & Setters Booleans." defaultstate="collapsed">
    public boolean isLiquidezCorrienteVisible() {
        return liquidezCorrienteVisible;
    }

    public void setLiquidezCorrienteVisible(boolean liquidezCorrienteVisible) {
        this.liquidezCorrienteVisible = liquidezCorrienteVisible;
    }

    public boolean isPruebaAcidaVisible() {
        return pruebaAcidaVisible;
    }

    public void setPruebaAcidaVisible(boolean pruebaAcidaVisible) {
        this.pruebaAcidaVisible = pruebaAcidaVisible;
    }

    public boolean isCapitalTrabajoVisible() {
        return capitalTrabajoVisible;
    }

    public void setCapitalTrabajoVisible(boolean capitalTrabajoVisible) {
        this.capitalTrabajoVisible = capitalTrabajoVisible;
    }

    public boolean isNivelDependenciaInventarioVisible() {
        return nivelDependenciaInventarioVisible;
    }

    public void setNivelDependenciaInventarioVisible(boolean nivelDependenciaInventarioVisible) {
        this.nivelDependenciaInventarioVisible = nivelDependenciaInventarioVisible;
    }

    public boolean isNumeroDiasCarteraManoVisible() {
        return numeroDiasCarteraManoVisible;
    }

    public void setNumeroDiasCarteraManoVisible(boolean numeroDiasCarteraManoVisible) {
        this.numeroDiasCarteraManoVisible = numeroDiasCarteraManoVisible;
    }

    public boolean isNumeroDiasInventarioManoVisible() {
        return numeroDiasInventarioManoVisible;
    }

    public void setNumeroDiasInventarioManoVisible(boolean numeroDiasInventarioManoVisible) {
        this.numeroDiasInventarioManoVisible = numeroDiasInventarioManoVisible;
    }

    public boolean isRotacionInventarioVisible() {
        return rotacionInventarioVisible;
    }

    public void setRotacionInventarioVisible(boolean rotacionInventarioVisible) {
        this.rotacionInventarioVisible = rotacionInventarioVisible;
    }

    public boolean isCicloOperativoVisible() {
        return cicloOperativoVisible;
    }

    public void setCicloOperativoVisible(boolean cicloOperativoVisible) {
        this.cicloOperativoVisible = cicloOperativoVisible;
    }

    public boolean isRotacionCarteraVisible() {
        return rotacionCarteraVisible;
    }

    public void setRotacionCarteraVisible(boolean rotacionCarteraVisible) {
        this.rotacionCarteraVisible = rotacionCarteraVisible;
    }

    public boolean isRotacionActivoTotalVisible() {
        return rotacionActivoTotalVisible;
    }

    public void setRotacionActivoTotalVisible(boolean rotacionActivoTotalVisible) {
        this.rotacionActivoTotalVisible = rotacionActivoTotalVisible;
    }

    public boolean isEndeudamientoSobreActivosTotalesVisible() {
        return endeudamientoSobreActivosTotalesVisible;
    }

    public void setEndeudamientoSobreActivosTotalesVisible(boolean endeudamientoSobreActivosTotalesVisible) {
        this.endeudamientoSobreActivosTotalesVisible = endeudamientoSobreActivosTotalesVisible;
    }

    public boolean isApalancamientoVisible() {
        return apalancamientoVisible;
    }

    public void setApalancamientoVisible(boolean apalancamientoVisible) {
        this.apalancamientoVisible = apalancamientoVisible;
    }

    public boolean isMargenUtilidadNetaVisible() {
        return margenUtilidadNetaVisible;
    }

    public void setMargenUtilidadNetaVisible(boolean margenUtilidadNetaVisible) {
        this.margenUtilidadNetaVisible = margenUtilidadNetaVisible;
    }

    public boolean isRoiVisible() {
        return roiVisible;
    }

    public void setRoiVisible(boolean roiVisible) {
        this.roiVisible = roiVisible;
    }

    public boolean isRoeVisible() {
        return roeVisible;
    }

    public void setRoeVisible(boolean roeVisible) {
        this.roeVisible = roeVisible;
    }

    public boolean isLiquidezCorrienteSelected() {
        return liquidezCorrienteSelected;
    }

    public void setLiquidezCorrienteSelected(boolean liquidezCorrienteSelected) {
        this.liquidezCorrienteSelected = liquidezCorrienteSelected;
    }

    public boolean isPruebaAcidaSelected() {
        return pruebaAcidaSelected;
    }

    public void setPruebaAcidaSelected(boolean pruebaAcidaSelected) {
        this.pruebaAcidaSelected = pruebaAcidaSelected;
    }

    public boolean isCapitalTrabajoSelected() {
        return capitalTrabajoSelected;
    }

    public void setCapitalTrabajoSelected(boolean capitalTrabajoSelected) {
        this.capitalTrabajoSelected = capitalTrabajoSelected;
    }

    public boolean isNivelDependenciaInventarioSelected() {
        return nivelDependenciaInventarioSelected;
    }

    public void setNivelDependenciaInventarioSelected(boolean nivelDependenciaInventarioSelected) {
        this.nivelDependenciaInventarioSelected = nivelDependenciaInventarioSelected;
    }

    public boolean isNumeroDiasCarteraManoSelected() {
        return numeroDiasCarteraManoSelected;
    }

    public void setNumeroDiasCarteraManoSelected(boolean numeroDiasCarteraManoSelected) {
        this.numeroDiasCarteraManoSelected = numeroDiasCarteraManoSelected;
    }

    public boolean isNumeroDiasInventarioManoSelected() {
        return numeroDiasInventarioManoSelected;
    }

    public void setNumeroDiasInventarioManoSelected(boolean numeroDiasInventarioManoSelected) {
        this.numeroDiasInventarioManoSelected = numeroDiasInventarioManoSelected;
    }

    public boolean isRotacionInventarioSelected() {
        return rotacionInventarioSelected;
    }

    public void setRotacionInventarioSelected(boolean rotacionInventarioSelected) {
        this.rotacionInventarioSelected = rotacionInventarioSelected;
    }

    public boolean isCicloOperativoSelected() {
        return cicloOperativoSelected;
    }

    public void setCicloOperativoSelected(boolean cicloOperativoSelected) {
        this.cicloOperativoSelected = cicloOperativoSelected;
    }

    public boolean isRotacionCarteraSelected() {
        return rotacionCarteraSelected;
    }

    public void setRotacionCarteraSelected(boolean rotacionCarteraSelected) {
        this.rotacionCarteraSelected = rotacionCarteraSelected;
    }

    public boolean isRotacionActivoTotalSelected() {
        return rotacionActivoTotalSelected;
    }

    public void setRotacionActivoTotalSelected(boolean rotacionActivoTotalSelected) {
        this.rotacionActivoTotalSelected = rotacionActivoTotalSelected;
    }

    public boolean isEndeudamientoSobreActivosTotalesSelected() {
        return endeudamientoSobreActivosTotalesSelected;
    }

    public void setEndeudamientoSobreActivosTotalesSelected(boolean endeudamientoSobreActivosTotalesSelected) {
        this.endeudamientoSobreActivosTotalesSelected = endeudamientoSobreActivosTotalesSelected;
    }

    public boolean isApalancamientoSelected() {
        return apalancamientoSelected;
    }

    public void setApalancamientoSelected(boolean apalancamientoSelected) {
        this.apalancamientoSelected = apalancamientoSelected;
    }

    public boolean isMargenUtilidadNetaSelected() {
        return margenUtilidadNetaSelected;
    }

    public void setMargenUtilidadNetaSelected(boolean margenUtilidadNetaSelected) {
        this.margenUtilidadNetaSelected = margenUtilidadNetaSelected;
    }

    public boolean isRoiSelected() {
        return roiSelected;
    }

    public void setRoiSelected(boolean roiSelected) {
        this.roiSelected = roiSelected;
    }

    public boolean isRoeSelected() {
        return roeSelected;
    }

    public void setRoeSelected(boolean roeSelected) {
        this.roeSelected = roeSelected;
    }
    // </editor-fold>

    public double getFuentes() {
        return fuentes;
    }

    public void setFuentes(double fuentes) {
        this.fuentes = fuentes;
    }

    public double getUsos() {
        return usos;
    }

    public void setUsos(double usos) {
        this.usos = usos;
    }

    public boolean isMargenUtilidadOperativaSelected() {
        return margenUtilidadOperativaSelected;
    }

    public void setMargenUtilidadOperativaSelected(boolean margenUtilidadOperativaSelected) {
        this.margenUtilidadOperativaSelected = margenUtilidadOperativaSelected;
    }

    public boolean isMargenUtilidadBrutaSelected() {
        return margenUtilidadBrutaSelected;
    }

    public void setMargenUtilidadBrutaSelected(boolean margenUtilidadBrutaSelected) {
        this.margenUtilidadBrutaSelected = margenUtilidadBrutaSelected;
    }

    public boolean isMargenUtilidadAntesImpuestosReservaSelected() {
        return margenUtilidadAntesImpuestosReservaSelected;
    }

    public void setMargenUtilidadAntesImpuestosReservaSelected(boolean margenUtilidadAntesImpuestosReservaSelected) {
        this.margenUtilidadAntesImpuestosReservaSelected = margenUtilidadAntesImpuestosReservaSelected;
    }

    public boolean isMargenUtilidadBrutaVisible() {
        return margenUtilidadBrutaVisible;
    }

    public void setMargenUtilidadBrutaVisible(boolean margenUtilidadBrutaVisible) {
        this.margenUtilidadBrutaVisible = margenUtilidadBrutaVisible;
    }

    public boolean isMargenUtilidadOperativaVisible() {
        return margenUtilidadOperativaVisible;
    }

    public void setMargenUtilidadOperativaVisible(boolean margenUtilidadOperativaVisible) {
        this.margenUtilidadOperativaVisible = margenUtilidadOperativaVisible;
    }

    public boolean isMargenUtilidadAntesImpuestosReservaVisible() {
        return margenUtilidadAntesImpuestosReservaVisible;
    }

    public void setMargenUtilidadAntesImpuestosReservaVisible(boolean margenUtilidadAntesImpuestosReservaVisible) {
        this.margenUtilidadAntesImpuestosReservaVisible = margenUtilidadAntesImpuestosReservaVisible;
    }

    public boolean isRoiOperativoSelected() {
        return roiOperativoSelected;
    }

    public void setRoiOperativoSelected(boolean roiOperativoSelected) {
        this.roiOperativoSelected = roiOperativoSelected;
    }

    public boolean isRoiOperativoVisible() {
        return roiOperativoVisible;
    }

    public void setRoiOperativoVisible(boolean roiOperativoVisible) {
        this.roiOperativoVisible = roiOperativoVisible;
    }

}