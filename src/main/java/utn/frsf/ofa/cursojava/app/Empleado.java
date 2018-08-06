/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app;

import java.util.Date;

/**
 * Empleado (datos comunes de los empleados)
 * 
 * @author Natalia Kieffer
 */
public abstract class Empleado {
  
    private int id;
    private String nombre;
    private String correoElectronico;
    private String cuil;
    private Date fechaIngreso;
    private Integer horasTrabajadas;

    public Empleado(){
    }
    
    public Empleado(String nombre, String correoElectronico, String cuil, Date fechaIngreso, Integer horasTrabajadas) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.cuil = cuil;
        this.fechaIngreso = fechaIngreso;
        this.horasTrabajadas = horasTrabajadas;
    }  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public abstract Double salario();
    
    
    
    
    
}
