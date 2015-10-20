import java.awt.Color;
import java.awt.event.*;
import java.io.PrintWriter;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public abstract class Pantallas extends JDialog implements ActionListener{
	
	protected Connection cn;
	protected Statement st;
	protected ResultSet res;
	protected String url="jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=VideoFox.mdb";
	
	protected JButton bt1, bt2, bt3, bt4, bt5, bt6,bt7;
	protected JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8,lbllogo;
	protected JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	protected JTextArea ta1, ta2;
	protected JComboBox <Object> cb1, cb2, cb3;
	protected JProgressBar barra;
	protected JTable tb1;
	protected JScrollPane sc1,sc2;
	protected JPasswordField pf5,pf6;
	protected JList<Object> lista;
	protected DefaultListModel<Object> modelolista;
	protected ImageIcon imglogo;
	protected PrintWriter archivosalida;
	protected boolean f=true;
	protected String direccion = "C:/VideoFox/cortes/";
	
	String var1, var2, var3, var4, var5, var6, var7;
	
	protected void valID(final JTextField tx){
		tx.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
			      char caracter = e.getKeyChar();
			      if (tx.getText().length()==5){
			            e.consume();
			      }
			      if(((caracter < '0') ||(caracter > '9')&&(caracter != KeyEvent.VK_BACK_SPACE)) )
			      {
			         e.consume();  
			      }
			   }                          
			});
	}
	
	protected void vallon(final JTextField tx){
		tx.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
			      @SuppressWarnings("unused")
				char caracter = e.getKeyChar();
			      if (tx.getText().length()==25){
			            e.consume();
			      }
			   }                          
			});
	}
	
	protected void valNombres(final JTextField tx){
		tx.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
			      char caracter = e.getKeyChar();
			      if (tx.getText().length()==20){
			            e.consume();
			      }
			      if(((caracter < 'a') ||(caracter > 'z')&&(caracter != KeyEvent.VK_BACK_SPACE)) )
			      {
			         e.consume();  
			      }
			   }                          
			});
	}
	
	protected void valTel(final JTextField tx){
		tx.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
			      char caracter = e.getKeyChar();
			      if (tx.getText().length()==8){
			            e.consume();
			      }
			      if(((caracter < '0') ||(caracter > '9')&&(caracter != KeyEvent.VK_BACK_SPACE)) )
			      {
			         e.consume();  
			      }
			   }                          
			});
	}
	
	protected void colordialogo(){
		UIManager.put("OptionPane.background",Color.black);
        UIManager.put("OptionPane.messageForeground",Color.white);
        UIManager.put("Panel.background",Color.black);
	}
	
	protected void trycatch(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			cn = DriverManager.getConnection(url,"null0","null0");
		} catch (SQLException e1) {
			e1.printStackTrace();
			colordialogo();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}
}