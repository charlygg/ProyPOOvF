import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class addVenta extends Pantallas {
	
	int nide, precio;
	static String resultado = "";
	static JTextField txt;
	
	public addVenta(){
		setDefaultCloseOperation(0);
		this.setSize(350, 220);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);		
	}

	private void pant() {		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Ventas | Altas");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds(10, 25, 80, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("Película: ");
		marco.add(lb1);
		
		lb2 = new JLabel();
		lb2.setBounds(10, 65, 80, 30);
		lb2.setFont(new Font("Arial", 1, 14));
		lb2.setForeground(Color.white);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		lb2.setText("ID copia: ");
		marco.add(lb2);
		
		lb3 = new JLabel();
		lb3.setBounds(10, 105, 80, 30);
		lb3.setFont(new Font("Arial", 1, 14));
		lb3.setForeground(Color.white);
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		lb3.setText("Precio : ");
		marco.add(lb3);
		
		txt = new JTextField("");
		actualizar(null);
		txt.setBounds(100, 25, 50, 30);
		txt.setFont(new Font("Arial", 4, 14));
		txt.setEditable(false);
		marco.add(txt);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 65, 50, 30);
		tf2.setFont(new Font("Arial", 4, 14));
		autoid();
		tf2.setText(""+nide);
		tf2.setEditable(false);
		marco.add(tf2);
		
		String sec [] = {"Elegir opción","$60.00","$50.00","$40.00","$30.00","$25.00"};
		cb1 = new JComboBox<Object>(sec);
		cb1.setBounds(100, 105, 120, 30);
		cb1.setFont(new Font("Arial", 5, 16));
		cb1.addActionListener(this);
		marco.add(cb1);
		
		bt1 = new JButton();
		bt1.setBounds((getWidth()/2-100)/2, 150, 100, 30);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Guardar");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds(getWidth()/2+((getWidth()/2-100)/2), 150, 100, 30);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Cancelar");
		bt2.addActionListener(this);
		marco.add(bt2);
		
		bt3 = new JButton();
		bt3.setBounds(200, 25, 100, 30);
		bt3.setFont(new Font("Arial", 1, 10));
		bt3.setText("Referencias");
		bt3.addActionListener(this);
		marco.add(bt3);
	}

	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}

	private void autoid() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(pvt_id) as max from Pvta");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String s;
		String item=(String) cb1.getSelectedItem();
		
		if(e.getSource() == bt1){
			if(txt.getText().equals("")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Falta ingresar ID de la película","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else if(item.equals("Elegir opción")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Falta seleccionar el precio","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else{
					guardar();
					this.dispose();
				}
		}
		if(e.getSource() == bt2){
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
		if(e.getSource() == bt3){
			new referencias(2);
		}
		if(e.getSource()==cb1){
			s = (String) cb1.getSelectedItem();
			if(s.equals("$60.00")){
				precio = 60;
			} else if(s.equals("$50.00")){
				precio = 50;
			} else if(s.equals("$40.00")){
				precio = 40;
			} else if(s.equals("$30.00")){
				precio = 30;
			} else if(s.equals("$25.00")){
				precio = 25;
			}
		}
	}

	private void guardar() {
		trycatch();
		try {
			st = cn.createStatement();
			int a = 0;
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Pvta (pvt_pel, pvt_id, pvt_pre, pvt_ven) values("
					+Integer.parseInt(resultado)+", "
					+Integer.parseInt(tf2.getText())+", "
					+precio+", "
					+a+")");	
			cn.close();
			 colordialogo();
			JOptionPane.showOptionDialog(null,"La Copia se registró correctamente","Operaciones con clientes",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}