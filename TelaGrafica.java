import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaGrafica extends JFrame implements ActionListener {
   private JTextField txtTexto;
   private JLabel lblTexto;
   private JButton btnMostrar, btnLimpa, btnSai;
   
   private JTable tabela;
   private JScrollPane scTabela;
   
   public TelaGrafica (){
      super ("Mostrar Temperatura");
      JPanel painelDadosEntrada = new JPanel (new GridLayout (4, 2));
      JPanel painelEntrada = new JPanel (new FlowLayout());
      txtTexto = new JTextField (30);
      lblTexto = new JLabel ("Digite a quantidade a mostrar:");
   
      painelDadosEntrada.add(lblTexto);
      painelDadosEntrada.add(txtTexto);
      painelEntrada.add(painelDadosEntrada);
            
      JPanel painelBotoes = new JPanel (new FlowLayout());
      btnMostrar = new JButton ("Mostrar");
      btnLimpa = new JButton ("Limpar");
      btnSai = new JButton ("Sair");
      
      btnMostrar.addActionListener(this);
      btnLimpa.addActionListener(this);
      btnSai.addActionListener(this);
      
      painelBotoes.add(btnMostrar);
      painelBotoes.add(btnLimpa);
      painelBotoes.add(btnSai);
      
      Container painelDeConteudo = getContentPane();
      
      painelDeConteudo.setLayout (new BorderLayout());
      painelDeConteudo.add(painelEntrada, BorderLayout.CENTER);
      painelDeConteudo.add(painelBotoes, BorderLayout.SOUTH);
      
      setSize (410, 150);
      setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo (null);
      setVisible(true);
   
   } 
 
   public void actionPerformed (ActionEvent e){
      if (e.getSource() == btnMostrar){

         String [] colunas = {"Id", "Temperatura"};
         Object [][] dados = {
            {"01", 15 },
            {"02", 22 },
            {"03", 34 }
            };
         JTable table = new JTable (dados, colunas);
         JScrollPane scrollPane = new JScrollPane (table);
         JFrame frame = new JFrame ();
         frame.getContentPane().add(scrollPane);
         frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         frame.pack();
         frame.setVisible(true);
        
      }
      else if (e.getSource() == btnLimpa){
         txtTexto.setText("");
      }
      else if (e.getSource() == btnSai){
         System.exit(0);
      }
   }
   
   public static void main (String [] args){
      SwingUtilities.invokeLater(
         new Runnable (){
            public void run (){
               new TelaGrafica();
            }
         });
   }    
}