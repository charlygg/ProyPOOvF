import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class addRenta extends Pantallas {
	
	int nide,estante,nivel;
	char seccion;
	static String resultado = "";
	static JTextField txt;
	
	public addRenta(){
		setDefaultCloseOperation(0);
		this.setSize(350, 300);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);		
	}

	private void pant() {		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Rentas | Altas");
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
		lb3.setText("Sección: ");
		marco.add(lb3);
		
		lb4 = new JLabel();
		lb4.setBounds(10, 145, 80, 30);
		lb4.setFont(new Font("Arial", 1, 14));
		lb4.setForeground(Color.white);
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		lb4.setText("Estante: ");
		marco.add(lb4);
		
		lb5 = new JLabel();
		lb5.setBounds(10, 185, 80, 30);
		lb5.setFont(new Font("Arial", 1, 14));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		lb5.setText("Nivel: ");
		marco.add(lb5);
		
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
		
		String sec [] = {"Elegir opción","A","B"};
		cb1 = new JComboBox<Object>(sec);
		cb1.setBounds(100, 105, 120, 30);
		cb1.setFont(new Font("Arial", 5, 16));
		cb1.addActionListener(this);
		marco.add(cb1);
		
		String est [] = {"Elegir opción","1","2","3","4","5"};
		cb2 = new JComboBox<Object>(est);
		cb2.setBounds(100, 145, 120, 30);
		cb2.setFont(new Font("Arial", 5, 16));
		cb2.addActionListener(this);
		marco.add(cb2);
		
		String niv [] = {"Elegir opción","Abajo","Medio","Arriba"};
		cb3 = new JComboBox<Object>(niv);
		cb3.setBounds(100, 185, 120, 30);
		cb3.setFont(new Font("Arial", 5, 16));
		cb3.addActionListener(this);
		marco.add(cb3);
		
		bt1 = new JButton();
		bt1.setBounds((getWidth()/2-100)/2, 230, 100, 30);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Guardar");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds(getWidth()/2+((getWidth()/2-100)/2), 230, 100, 30);
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
			res = st.executeQuery("select max(pre_id) as max from Pren");
			res.next();
			int ide = Integer.parseInt(res.getString(1));
			nide = ide + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s;
		String item=(String) cb1.getSelectedItem();
		String item2=(String) cb2.getSelectedItem();
		String item3=(String) cb3.getSelectedItem();
		
		if(e.getSource() == bt1){
			if(item.equals("Elegir opción")||item2.equals("Elegir opción")||item3.equals("Elegir opción")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Falta seleccionar sección, estante y/o nivel","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
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
			new referencias(1);
		}
		if(e.getSource()==cb1){
			s = (String) cb1.getSelectedItem();
			if(s.equals("A")){
				seccion = 'A';
			} else if(s.equals("B")){
				seccion = 'B';
			}
		}
		if(e.getSource()==cb2){
			s = (String) cb2.getSelectedItem();
			if(s.equals("1")){
				estante = 1;
			} else if(s.equals("2")){
				estante = 2;
			} else if(s.equals("3")){
				estante = 3;
			} else if(s.equals("4")){
				estante = 4;
			} else if(s.equals("5")){
				estante = 5;
			}
		}
		if(e.getSource()==cb3){
			s = (String) cb3.getSelectedItem();
			if(s.equals("Abajo")){
				nivel = 1;
			} else if(s.equals("Medio")){
				nivel = 2;
		    } else if(s.equals("Arriba")){
				nivel = 3;
		    }
		}
	}

	private void guardar() {
		trycatch();
		try {
			st = cn.createStatement();
			int dis = 1, eli = 0;
			String fecha = "N/A";
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Pren (pre_pel, pre_id, pre_dis, pre_sec, pre_est, pre_niv, pre_ent, pre_eli) values("
					+Integer.parseInt(resultado)+", "
					+Integer.parseInt(tf2.getText())+", "
					+dis+", '"
					+seccion+"', "
					+estante+", "
					+nivel+", '"
					+fecha+"', "
					+eli+")");	
			cn.close();
			 colordialogo();
			JOptionPane.showOptionDialog(null,"La Copia se registró correctamente","Operaciones con clientes",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}