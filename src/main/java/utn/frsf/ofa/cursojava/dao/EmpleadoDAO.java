/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.List;
import utn.frsf.ofa.cursojava.app.Empleado;

/**
 *
 * @author Natalia Kieffer
 */
public interface EmpleadoDAO {

    public void crear(Empleado e);

    public int actualizar(Empleado e);

    public void eliminar(Empleado e);

    public Empleado buscarPorId(Integer id);

    public List<Empleado> buscarTodos();
}
