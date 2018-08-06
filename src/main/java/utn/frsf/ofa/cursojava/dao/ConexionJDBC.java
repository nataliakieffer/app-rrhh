package utn.frsf.ofa.cursojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/app-rrhh";
    
    private static Connection CONN;

    /*
    * Establecer la conexion con la BD 
    */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Verifico si no existe una instancia previa de conexion
        if(CONN == null){
            try {
                // Indico a que tipo de BD me conecto => MYSQL
                Class.forName("com.mysql.jdbc.Driver");
                // Obtengo la nueva conexion
                CONN = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
            } catch (SQLException ex) {
                // Excepcion que se lanza si ocurre algun error al intentar conectarse
                throw new SQLException(ex);} 
            catch (ClassNotFoundException ex) {
                // Excepcion que se lanza si no se encuentra la libreria 
                throw new ClassNotFoundException(ex.getMessage());
            }
        }
        return CONN;
    }
    
    /*
    * Cerrar la conexion con la BD
    */
    public static void liberarConexion() throws SQLException {
        // Verifico si existe una abierta
        if(CONN != null){
            try {
                CONN.close();
                CONN = null;
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }
        }
    }
}