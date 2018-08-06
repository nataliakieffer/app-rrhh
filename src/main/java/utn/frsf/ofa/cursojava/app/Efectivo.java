package utn.frsf.ofa.cursojava.app;

import java.util.Date;

/**
 * Efectivo: empleados en relacion de dependecia que poseen como remuneracion un
 * sueldo basico, comisiones y ademas un pago por horas extras
 *
 * @author Natalia Kieffer
 */
public class Efectivo extends Empleado {

    public Double sueldoBasico;
    public Double comision;
    public Integer horasMinimasObligatorias;

    public Efectivo() {
        super();
    }

    public Efectivo(Double sueldoBasico, Double comision, Integer horasMinimasObligatorias, String nombre, String correoElectronico, String cuil, Date fechaIngreso, Integer horasTrabajadas) {
        super(nombre, correoElectronico, cuil, fechaIngreso, horasTrabajadas);
        this.sueldoBasico = sueldoBasico;
        this.comision = comision;
        this.horasMinimasObligatorias = horasMinimasObligatorias;

    }

    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Integer getHorasMinimasObligatorias() {
        return horasMinimasObligatorias;
    }

    public void setHorasMinimasObligatorias(Integer horasMinimasObligatorias) {
        this.horasMinimasObligatorias = horasMinimasObligatorias;
    }

    @Override
    public Double salario() {
        Double salario;
        Integer horasExtras;

        if (horasMinimasObligatorias < getHorasTrabajadas()) {
            horasExtras = getHorasTrabajadas() - horasMinimasObligatorias;
            salario = sueldoBasico + comision + (horasExtras * (sueldoBasico / 2));
        } else {
            salario = sueldoBasico + comision;
        }

        return salario;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
