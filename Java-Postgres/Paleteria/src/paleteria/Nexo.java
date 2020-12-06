package paleteria;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Nexo {
    
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    private DefaultTableModel modelo;


    
   /* public void conexion()
    {
         String URL, Nombre, Password;
        
        URL = "jdbc:postgresql://localhost:5432/Paleteria";
        Nombre = "postgres";
        Password = "postgres";
        
        try{
            Conexion = DriverManager.getConnection(URL,Nombre,Password);
            if(Conexion != null){
                JOptionPane.showMessageDialog(null,"CONEXION EXITOSA");
            }
        }
        catch(Exception w){
            JOptionPane.showMessageDialog(null, w + "NO PUDO CONECTAR A LA BASE");
        }
        
        
    }*/
    
     public static Connection conex()
    {
        Connection Conex = null;
        String URL, Nombre, Password;
        
        URL = "jdbc:postgresql://localhost:5432/Paleteria";
        Nombre = "postgres";
        Password = "postgres";
        
        try{
            Conex = DriverManager.getConnection(URL,Nombre,Password);
            if(Conex != null){
                //JOptionPane.showMessageDialog(null,"CONEXION EXITOSA");
            }
        }
        catch(Exception w){
            JOptionPane.showMessageDialog(null, w + "NO PUDO CONECTAR A LA BDD");
        }
        
        return Conex;
    }
     
    public static void ejecutaSQL(String Qery, boolean verificarcambio)
    {
        try {
                Conexion = Nexo.conex();
                st = Conexion.createStatement();
                rt = st.executeQuery(Qery);

            
            } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error no ejecutado: " + e.getMessage());
            }
    }
    
    public static String obtenultimoid(String tabla, String columna)
    {
           Conexion = Nexo.conex();
           String id = "";
           String query = "SELECT " + columna + " FROM " + tabla +" ORDER BY " + columna + " DESC LIMIT 1";
           try {
                st = Conexion.createStatement();
                rt = st.executeQuery(query);

                while(rt.next()){
                    id = rt.getString(1);
                }
            
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
           return id;
    }
    
    public String obtenEntero(String sentencia)
    {
           String id = "";
           String query = sentencia;
            try {
                st = Conexion.createStatement();
                rt = st.executeQuery(query);

                while(rt.next()){
                    id = rt.getString(1);
                }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
           return id;
    }
    
    
    
    
    
}
