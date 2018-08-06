package utn.frsf.ofa.cursojava.app;

import java.util.Date;

/*
*@author Natalia Kieffer
 */
public class Contratado extends Empleado {

    public Double montoPorHora;

    public Contratado() {
        super();
    }

    public Contratado(Double montoPorHora, String nombre, String correoElectronico, String cuil, Date fechaIngreso, Integer horasTrabajadas) {
        super(nombre, correoElectronico, cuil, fechaIngreso, horasTrabajadas);
        this.montoPorHora = montoPorHora;
    }

    public Double getMontoPorHora() {
        return montoPorHora;
    }

    public void setMontoPorHora(Double montoPorHora) {
        this.montoPorHora = montoPorHora;
    }

    @Override
    public Double salario() {
        Double salario = 0.0;
        int hsTrabajadas = getHorasTrabajadas();

        if (hsTrabajadas > 0) {
            salario = hsTrabajadas * montoPorHora;
        }

        return salario;
    }

}
