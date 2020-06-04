import java.sql.Connection;
import java.sql.SQLException;

public class Teste {
	/**
	 * Propositalmente sem acentuacao
	 */
   public static void main(String[] args) {
      Connection conn = null;
      Temperatura temperatura;
   
      try {
         ConexaoBD bd = new ConexaoBD();
         conn = bd.conectar();
         conn.setAutoCommit(false);
      
      	// *** Inclusao de 100 temperaturas aleatorias entre 0 e 40 graus ***
         for(int i = 0; i < 100; i++){
        	 temperatura = new Temperatura();
        	 //nao vai configurar o id por causa do autoincremento
        	 temperatura.setValor(40*Math.random());
        	 temperatura.incluir(conn);
         }
         conn.commit();
         Termometro termo = new Termometro();
         //pega as temperaturas do ultimos 30 dias
         Temperatura[] temps = termo.ultimosDias(conn, 100);
         //imprime as temperaturas
         for(int i = 0; i < temps.length; i++){
        	 System.out.println(temps[i]); 
         }
         System.out.println("deu certo" );      
         
   		double ac = 0;
         for (Temperatura temp : temps){
            ac = ac + temp.getValor ();
         }
         System.out.println(ac / temps.length );

         double aux = temps[0].getValor();
         for (int i = 1; i < temps.length; i++){
            if (temps[i].getValor() > aux)
               aux = temps[i].getValor();
         }
         System.out.println(aux );
         
         double aux1 = temps[0].getValor();
         for (int i = 1; i < temps.length; i++){
            if (temps[i].getValor() < aux1)
               aux1 = temps[i].getValor();
         }
         System.out.println(aux1 );         
         
      } 
      catch (Exception e) {
         e.printStackTrace();
         if (conn != null) {
            try {
               conn.rollback();
            } 
            catch (SQLException e1) {
               System.out.print(e1.getStackTrace());
            }
         }
      } 
      finally {
         if (conn != null) {
            try {
               conn.close();
            } 
            catch (SQLException e1) {
               System.out.print(e1.getStackTrace());
            }
         }
      }
   }
}