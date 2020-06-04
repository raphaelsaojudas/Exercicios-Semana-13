import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

   static {
      try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public Connection conectar() throws SQLException {
		String servidor = "localhost";
		String porta = "3306";
		String database = "clima";
		return DriverManager.getConnection("jdbc:mysql://"+servidor+":"+porta+"/"
         +database+"?useTimezone=true&serverTimezone=UTC", "root", "1234");
   }

   public static void desconectar(Connection conn) throws SQLException {
      conn.close();
   }
}