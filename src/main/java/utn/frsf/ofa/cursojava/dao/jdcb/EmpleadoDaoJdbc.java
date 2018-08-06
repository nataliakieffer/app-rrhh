package utn.frsf.ofa.cursojava.dao.jdcb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utn.frsf.ofa.cursojava.app.Contratado;
import utn.frsf.ofa.cursojava.app.Efectivo;
import utn.frsf.ofa.cursojava.app.Empleado;
import utn.frsf.ofa.cursojava.dao.ConexionJDBC;
import utn.frsf.ofa.cursojava.dao.EmpleadoDAO;

/*
 *@autor Natalia Kieffer
 */
public class EmpleadoDaoJdbc implements EmpleadoDAO {

    private final String SQL_INSERT_EMPLEADO = "INSERT INTO EMPLEADO (NOMBRE, CORREO, CUIL, "
            + "FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, "
            + "COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";

    private final String SQL_UPDATE_EMPLEADO = "UPDATE Empleado SET nombre=?, correo=?, cuil=?, fecha_ingreso=?, "
            + "hs_trabajadas=?, sueldo_basico=?, comisiones=?, hs_minimas=?, "
            + "costo_hora=?, tipo_empleado=? WHERE id=?;";
    
    private final String SQL_DELETE_EMPLEADO = "DELETE FROM Empleado WHERE id=?;";


    @Override
    public void crear(Empleado emp) {
        Connection conn;
        PreparedStatement ps;
        // Verifico que la instancia de Empleado a actualizar no sea nula
        if (emp != null) {
            try {
                conn = ConexionJDBC.getConnection();
                ps = conn.prepareStatement(SQL_INSERT_EMPLEADO);
                // Seteo el valor de los parámetros de la consulta
                ps.setString(1, emp.getNombre());
                ps.setString(2, emp.getCorreoElectronico());
                ps.setString(3, emp.getCuil());
                ps.setDate(4, new Date(emp.getFechaIngreso().getTime()));
                ps.setInt(5, emp.getHorasTrabajadas());
                if (emp instanceof Efectivo) {
                    Efectivo empEfectivo = (Efectivo) emp;
                    ps.setDouble(6, empEfectivo.getSueldoBasico());
                    ps.setDouble(7, empEfectivo.getComision());
                    ps.setInt(8, empEfectivo.getHorasMinimasObligatorias());
                    ps.setDouble(9, 0.0);
                    ps.setInt(10, 1);
                }
                if (emp instanceof Contratado) {
                    Contratado empContratado = (Contratado) emp;
                    ps.setDouble(6, 0.0);
                    ps.setDouble(7, 0.0);
                    ps.setInt(8, 0);
                    ps.setDouble(9, empContratado.getMontoPorHora());
                    ps.setInt(10, 2);
                }
                // Ejecuto la consulta
                int filaInsertada = ps.executeUpdate();
                if (filaInsertada == 1) {
                    System.out.println("La acción para insertar un Empleado se completo con éxito.");
                }
                // Cierro los recursos con la BD
                ps.close();
                ConexionJDBC.liberarConexion();

            } catch (SQLException sqle) {
                System.out.println("La acción para crear un Empleado ha fallado.");
                System.out.println("Motivo: " + sqle.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int actualizar(Empleado emp) {
        int rtaActualizar = 0;
        Connection conn;
        PreparedStatement ps;
        try {
            // Verifico que no sea nula la instancia pasada como parámetro
            if (emp != null) {
                conn = ConexionJDBC.getConnection();
                ps = conn.prepareStatement(SQL_UPDATE_EMPLEADO);
                // Seteo el valor de los parámetros de la consulta
                ps.setString(1, emp.getNombre());
                ps.setString(2, emp.getCorreoElectronico());
                ps.setString(3, emp.getCuil());
                ps.setDate(4, new Date(emp.getFechaIngreso().getTime()));
                ps.setInt(5, emp.getHorasTrabajadas());

                if (emp instanceof Efectivo) {
                    Efectivo empEfectivo = (Efectivo) emp;
                    ps.setDouble(6, empEfectivo.getSueldoBasico());
                    ps.setDouble(7, empEfectivo.getComision());
                    ps.setInt(8, empEfectivo.getHorasMinimasObligatorias());
                    ps.setInt(9, 0);
                    ps.setInt(10, 1);
                }
                if (emp instanceof Contratado) {
                    Contratado empContratado = (Contratado) emp;
                    ps.setDouble(6, 0.0);
                    ps.setDouble(7, 0.0);
                    ps.setInt(8, 0);
                    ps.setDouble(9, empContratado.getMontoPorHora());
                    ps.setInt(10, 2);
                }
                ps.setInt(11, emp.getId());

                // Ejecuto la consulta
                rtaActualizar = ps.executeUpdate();
                if (rtaActualizar == 1) {
                    System.out.println("Se actualizó con éxito los datos del empleado " + emp.getNombre());
                }
                // Cierro los recursos con la BD
                ps.close();
                ConexionJDBC.liberarConexion();
            }

        } catch (SQLException sqle) {
            System.out.println("La acción para actualizar un Empleado ha fallado.");
            System.out.println("Motivo: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rtaActualizar;
    }

    @Override
    public void eliminar(Empleado emp) {
        Connection conn;
        PreparedStatement ps;
        try {
            if (emp != null) {
                conn = ConexionJDBC.getConnection();
                ps = conn.prepareStatement(SQL_DELETE_EMPLEADO);
                // Seteo los parametros para la consulta
                ps.setInt(1, emp.getId());

                // Ejecuto la consulta                
                int rta = ps.executeUpdate();
                System.out.println("rta eliminar: " + rta);

                // Cierro los recursos con la BD
                ps.close();
                ConexionJDBC.liberarConexion();
            } else {
                System.out.println("Dado que el parametro es nulo no puede ejecutarse la acción de eliminar");
            }
        } catch (SQLException sqle) {
            System.out.println("La acción para eliminar un Empleado ha fallado.");
            System.out.println("Motivo: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        // si quisiera devolver un boolean luego de eliminar el objeto
        //return rpta;
    }

    @Override
    public Empleado buscarPorId(Integer id
    ) {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        Empleado emp = null;

        if (id != null) {
            try {
                conn = ConexionJDBC.getConnection();
                ps = conn.prepareStatement("SELECT * FROM empleado WHERE id = ?");
                ps.setInt(1, id);
                // Ejecuto la consulta
                rs = ps.executeQuery();

                if (rs.next()) {
                    String tipoEmp;
                    // Consulto de que tipo de empleado se trata para poder instanciarlo
                    if (rs.getInt("tipo_empleado") == 1) {
                        emp = new Efectivo(rs.getDouble("sueldo_basico"), rs.getDouble("comisiones"), rs.getInt("hs_minimas"), rs.getString("nombre"), rs.getString("correo"), rs.getString("cuil"), rs.getDate("fecha_ingreso"), rs.getInt("hs_trabajadas"));
                        emp.setId(rs.getInt("id"));
                        tipoEmp = "EFECTIVO";
                    } else {
                        emp = new Contratado(rs.getDouble("costo_hora"), rs.getString("nombre"), rs.getString("correo"), rs.getString("cuil"), rs.getDate("fecha_ingreso"), rs.getInt("hs_trabajadas"));
                        emp.setId(rs.getInt("id"));
                        tipoEmp = "CONTRATADO";
                    }
                    System.out.println("El empleado de id " + id + " es " + rs.getString("nombre")
                            + " y es del tipo : " + tipoEmp);
                } else {
                    // muestra un cuadro de mensaje por pantalla
                    //JOptionPane.showMessageDialog(null, "No existe un empleado con la clave: " + id);
                    System.out.print("No existe un empleado con la clave: " + id);
                }

                // Cierro los recursos con la BD
                ps.close();
                ConexionJDBC.liberarConexion();

            } catch (SQLException sqle) {
                System.out.println("La acción para buscar un Empleado ha fallado.");
                System.out.println("Motivo: " + sqle.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Dado que el parametro id es nulo no puede ejecutarse la acción de búsqueda");
        }

        // Retorno el Empleado recuperado
        return emp;
    }

    @Override
    public List<Empleado> buscarTodos() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        Empleado emp;
        List<Empleado> listaEmpleados = new ArrayList<>();

        try {
            conn = ConexionJDBC.getConnection();
            ps = conn.prepareStatement("SELECT * FROM empleado ORDER BY nombre");
            // ejecuto la consulta
            rs = ps.executeQuery();
            // Recorro cada fila del ResultSet para instanciar a cada empleado 
            // y agregarlo a la lista de empleados.
            while (rs.next()) {
                // Consulto de que tipo de empleado se trata para poder instanciarlo
                if (rs.getInt("tipo_empleado") == 1) {
                    // Instancia el nuevo EMPLEADO EFECTIVO
                    emp = new Efectivo(rs.getDouble("sueldo_basico"), rs.getDouble("comisiones"), rs.getInt("hs_minimas"), rs.getString("nombre"), rs.getString("correo"), rs.getString("cuil"), rs.getDate("fecha_ingreso"), rs.getInt("hs_trabajadas"));
                } else {
                    // Instancia el nuevo EMPLEADO CONTRATADO
                    emp = new Contratado(rs.getDouble("costo_hora"), rs.getString("nombre"), rs.getString("correo"), rs.getString("cuil"), rs.getDate("fecha_ingreso"), rs.getInt("hs_trabajadas"));
                }
                emp.setId(rs.getInt("id"));
                // Agrego el empleado a la lista.
                listaEmpleados.add(emp);

            }
            // Cierro los recursos con la BD
            ps.close();
            ConexionJDBC.liberarConexion();

        } catch (SQLException sqle) {
            System.out.println("La acción para buscar la lista de Empleados ha fallado.");
            System.out.println("Motivo: " + sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Cantidad de empleados en la lista a retornar: " + listaEmpleados.size());
        // Retorno el contenido de la Lista
        return listaEmpleados;

    }

}
