import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class addEmpleado extends Pantallas {

	private int tipo, nide;
	private String s;
	
	public addEmpleado(){
		setDefaultCloseOperation(0);
		this.setSize(400, 400);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void pant() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Empleados | Agregar ");
		marco.setBackground(Color.black);
		
		lb8 = new JLabel();
		lb8.setBounds(0, 30, getWidth(), 25);
		lb8.setFont(new Font("Arial", 1, 20));
		lb8.setForeground(Color.white);
		lb8.setHorizontalAlignment(SwingConstants.CENTER);
		lb8.setText("* AGREGAR NUEVO EMPLEADO *");
		marco.add(lb8);
		
		int x = 20, y = 80, l = 110, a = 25, ix = 120, iy = 30;
		
		lb1 = new JLabel();
		lb1.setBounds(x, y, l, a);
		lb1.setFont(new Font("Arial", 1, 16));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Empleado: ");
		marco.add(lb1);
		
		lb2 = new JLabel();
		lb2.setBounds(x, y + iy, l, a);
		lb2.setFont(new Font("Arial", 1, 16));
		lb2.setForeground(Color.white);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		lb2.setText("Nombre: ");
		marco.add(lb2);
		
		lb3 = new JLabel();
		lb3.setBounds(x, y + 2*iy, l, a);
		lb3.setFont(new Font("Arial", 1, 16));
		lb3.setForeground(Color.white);
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		lb3.setText("Apellidos: ");
		marco.add(lb3);
		
		lb4 = new JLabel();
		lb4.setBounds(x, y + 3*iy, l, a);
		lb4.setFont(new Font("Arial", 1, 16));
		lb4.setForeground(Color.white);
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		lb4.setText("Tipo Emp.: ");
		marco.add(lb4);
		
		lb5 = new JLabel();
		lb5.setBounds(x, y + 4*iy, l, a);
		lb5.setFont(new Font("Arial", 1, 16));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		lb5.setText("Usuario: ");
		marco.add(lb5);
		
		lb6 = new JLabel();
		lb6.setBounds(x, y + 5*iy, l, a);
		lb6.setFont(new Font("Arial", 1, 16));
		lb6.setForeground(Color.white);
		lb6.setHorizontalAlignment(SwingConstants.RIGHT);
		lb6.setText("Contraseña: ");
		marco.add(lb6);
		
		lb7 = new JLabel();
		lb7.setBounds(x, y + 6*iy, l, a);
		lb7.setFont(new Font("Arial", 1, 16));
		lb7.setForeground(Color.white);
		lb7.setHorizontalAlignment(SwingConstants.RIGHT);
		lb7.setText("Repetir: ");
		marco.add(lb7);
		
		tf1 = new JTextField();
		tf1.setBounds(x + ix, y, 2*l, a);
		tf1.setFont(new Font("Arial", 5, 16));
		autoid();
		tf1.setText(""+nide);
		tf1.setEditable(false);
		marco.add(tf1);
		
		tf2 = new JTextField();
		tf2.setBounds(x + ix, y + iy, 2*l, a);
		tf2.setFont(new Font("Arial", 5, 16));
		valNombres(tf2);
		tf2.setText("");  
		marco.add(tf2);
		
		tf3 = new JTextField();
		tf3.setBounds(x + ix, y + 2*iy, 2*l, a);
		tf3.setFont(new Font("Arial", 5, 16));
		valNombres(tf3);
		tf3.setText("");
		marco.add(tf3);
		
		String args [] = {"Seleccione una opción", "Empleado", "Administrador"};
		cb1 = new JComboBox<Object>(args);
		cb1.setBounds(x + ix, y + 3*iy, 2*l, a);
		cb1.setFont(new Font("Arial", 5, 16));
		cb1.addActionListener(this);
		marco.add(cb1);
		
		tf4 = new JTextField();
		tf4.setBounds(x + ix, y + 4*iy, 2*l, a);
		tf4.setFont(new Font("Arial", 5, 16));
		tf4.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			private char caracter;
			public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if (tf4.getText().length()==5){
			            e.consume();
			      }
			   }                          
			});
		tf4.setText("");
		marco.add(tf4);
		
		pf5 = new JPasswordField();
		pf5.setBounds(x + ix, y+5*iy, 2*l, a);
		pf5.setEchoChar('☻');
		pf5.setFont(new Font("Arial", 5, 16));
		pf5.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			private char caracter;
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if (pf5.getText().length()==10){
			            e.consume();
			      }
			   }                          
			});
		pf5.setText("");
        marco.add(pf5);
		
        pf6 = new JPasswordField();
		pf6.setBounds(x + ix, y+6*iy, 2*l, a);
		pf6.setEchoChar('☻');
		pf6.setFont(new Font("Arial", 5, 16));
		pf6.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			private char caracter;
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if (pf6.getText().length()==10){
			            e.consume();
			      }
			   }                          
			});
		pf6.setText("");
        marco.add(pf6);
       
		int xb = 15, yb = 320, lb = 140, ab = 25;
		
		bt1 = new JButton();
		bt1.setBounds(xb, yb, lb, ab);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Agregar");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds((getWidth()/2)+15, yb, lb, ab);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Cancelar");
		bt2.addActionListener(this);
		marco.add(bt2);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cb1){
			s=(String) cb1.getSelectedItem();
			if(s.equals("Empleado")){
				tipo=2;
			}
			else{
				tipo=1;
			}
		}
		if(e.getSource() == bt1){
			String item=(String) cb1.getSelectedItem();
			if(pf5.getText().equals(pf6.getText())){
				if(tf2.getText().equals("")||tf3.getText().equals("")||tf4.getText().equals("")||pf5.getText().equals("")||item.equals("Seleccione una opción")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Falta llenar uno o más campos o elegir tipo de usuario","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else{
					agregar();
					this.dispose();
				}
			}
			else{
				 colordialogo();
		    JOptionPane.showOptionDialog(null,"La contraseña no coincide","Error en la contraseña",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);

			}
		}
		if(e.getSource() == bt2){
			
			if(!tf1.getText().equals("")||!tf2.getText().equals("")||!tf3.getText().equals("")||!tf4.getText().equals("")||!pf5.getText().equals("")||!pf6.getText().equals("")){
				colordialogo();
				int op = JOptionPane.showOptionDialog(
					    this,
				   "¿Seguro que desea cancelar?", 
					    "Guardar datos",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
						    null,null,null);
				if(op==0){
					this.dispose();
				}	
			}
		}
	}

	private void agregar() {
		trycatch();
		try {
			int eli = 0;
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			@SuppressWarnings({ "unused", "deprecation" })
			int r = st.executeUpdate("Insert into Personal (per_id, per_nom, per_ape, per_tip, per_use, per_pas, per_eli) values ("
					+Integer.parseInt(tf1.getText())+", '"
					+tf2.getText().toUpperCase()+"', '"
					+tf3.getText().toUpperCase()+"'," 
					+tipo+", '"
					+tf4.getText()+"', '"
					+pf5.getText()+"', "
					+eli+")");	
			cn.close();
			if(tipo==1){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"Administrador guardado","Altas de personal",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			} else{
				colordialogo();
				JOptionPane.showOptionDialog(null,"Empleado guardado","Altas de personal",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			colordialogo();
			JOptionPane.showOptionDialog(null,"Error: Ese usuario ya fue registrado","Altas de personal",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		}
	}
	private void autoid() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(per_id) as max from Personal");
			res.next();
			int ide = Integer.parseInt(res.getString(1));
			if(ide == 0){
				nide = 10001;
			} else {
				nide = 1 + ide;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
