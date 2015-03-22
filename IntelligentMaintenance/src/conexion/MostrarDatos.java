

package conexion;
import java.sql.*;
/**
 *
 * @author Yamir
 */
public class MostrarDatos {
    static final String DATABASE_URL="jdbc:mysql://localhost/paises";
    
    public static void main(String[] args) {
        Connection conexion=null;
        Statement sentencia=null;
        ResultSet rs=null;
        try{
            //Establecemos la conexion ala base de datos
            conexion=DriverManager.getConnection(DATABASE_URL, "root", "yperez");
            //creamos la sentencia para el query de la base de datos
            sentencia=conexion.createStatement();
            //query de la base de datos
            rs=sentencia.executeQuery("SELECT * FROM country");
            
            //pricesamos los resultados del query
            ResultSetMetaData metaData=rs.getMetaData();
            int columnas=metaData.getColumnCount();
            System.out.println("Los Paises en la Base de datos son:\n");
            
            for(int i=1;i<=columnas;i++)
                System.out.printf("%-8s\t",metaData.getColumnName(i));
            System.out.println();
            
            while(rs.next()){
                for(int i=1;i<=columnas;i++)
                    System.out.printf("%-8s\t",rs.getObject(i));
                System.out.println();
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            try{
                rs.close();
                sentencia.close();
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
