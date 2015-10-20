import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class rentas extends Pantallas implements ListSelectionListener,KeyListener{
	
	String IDpelicula, cliente, categoria, IDpel, cat, dispo, ent, emp, eli;
	int precio, resta, suma, tot, nide, dias, IDcopia;	
	
	SimpleDateFormat formateador = new SimpleDateFormat(
			"dd'/'MM'/'yyyy", new Locale("ES"));
	Date fechaDate = new Date();
	String fecha = formateador.format(fechaDate);
	
	public rentas(JTextArea ta1, String cli, String e){
		this.ta1=ta1;
		this.cliente=cli;
		emp = e;
		setDefaultCloseOperation(0);
		this.setSize(700, 500);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);		
	}

	private void pant() { 
		int x = getWidth()/2, y = 90, l = 110, a = 30;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Empleado | Menu | Clientes | Rentas");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds((x-(x-3))/2, 25, 150, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID de la copia: ");
		marco.add(lb1);
		
		lb2 = new JLabel();
		lb2.setBounds(30, 130, 600, 30);
		lb2.setFont(new Font("Arial", 1, 14));
		lb2.setForeground(Color.white);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		lb2.setText(" ID           Descripción                                                                                    Precio de renta ");
		marco.add(lb2);
		
		lb3 = new JLabel();
		lb3.setBounds(420, 320, 120, 20);
		lb3.setFont(new Font("Arial", 1, 18));
		lb3.setForeground(Color.white);
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		lb3.setText("Paga Con $: ");
		marco.add(lb3);
		
		lb5 = new JLabel();
		lb5.setBounds(440, 350, 100, 20);
		lb5.setFont(new Font("Arial", 1, 18));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		lb5.setText("Total $: ");
		marco.add(lb5);
		
		lb4 = new JLabel();
		lb4.setBounds(440, 380, 100, 20);
		lb4.setFont(new Font("Arial", 1, 18));
		lb4.setForeground(Color.white);
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		lb4.setText("Cambio $: ");
		marco.add(lb4);
		
		lb5 = new JLabel();
		lb5.setBounds(((x)-(x-3))/2+20, 320, 150, 20);
		lb5.setFont(new Font("Arial", 1, 18));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		lb5.setText("Numero Renta: ");
		marco.add(lb5);
		
		tf6 = new JTextField("");
		tf6.setBounds(((x)-(x-3))/2+170, 320, 100, 20);
		tf6.setFont(new Font("Arial", 4, 14));
		autoid();
		tf6.setEditable(false);
		marco.add(tf6);
		
		tf1 = new JTextField("");
		tf1.setBounds(((x-(x-50))/2)+130, 25, 50, 30);
		tf1.setFont(new Font("Arial", 4, 14));
		tf1.addKeyListener(this);
		valID(tf1);
		marco.add(tf1);
		
		tf2 = new JTextField("");
		tf2.setBounds(((x-(x-50))/2)+190, 25, 230, 30);
		tf2.setFont(new Font("Arial", 4, 14));
		tf2.setEditable(false);
		marco.add(tf2);
		
		tf3 = new JTextField("");
		tf3.setBounds(550, 320, 100, 20);
		tf3.setFont(new Font("Arial", 4, 14));
		valID(tf3);
		marco.add(tf3);
		
		tf4 = new JTextField("");
		tf4.setBounds(550, 350, 100, 20);
		tf4.setFont(new Font("Arial", 4, 14));
		tf4.setEditable(false);
		marco.add(tf4);
		
		tf5 = new JTextField("");
		tf5.setBounds(550, 380, 100, 20);
		tf5.setFont(new Font("Arial", 4, 14));
		tf5.setEditable(false);
		marco.add(tf5);
		
		bt1 = new JButton();
		bt1.setBounds(((x-(x-10))/2)+120, 70, 105, 30);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Buscar");
		bt1.addActionListener(this);
		marco.add(bt1);		
		
		bt2 = new JButton();
		bt2.setBounds(((x-(x-10))/2)+230, 70, 105, 30);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Agregar");
		bt2.addActionListener(this);
		marco.add(bt2);
		
		bt3 = new JButton();
		bt3.setBounds((x-l)/2+80, 420, l, a);
		bt3.setFont(new Font("Arial", 1, 14));
		bt3.setText("Regresar");
		bt3.addActionListener(this);
		marco.add(bt3);
		
		bt5 = new JButton();
		bt5.setBounds(10, 70, 110, 30);
		bt5.setFont(new Font("Arial", 1, 14));
		bt5.setText("Peliculas");
		bt5.addActionListener(this);
		marco.add(bt5);
		
		bt4 = new JButton();
		bt4.setBounds((x-l)/2+200, 420, l, a);
		bt4.setFont(new Font("Arial", 1, 14));
		bt4.setText("Cobrar");
		bt4.addActionListener(this);
		marco.add(bt4);
			
		ta1 = new JTextArea(ta1.getText());
		ta1.setFont(new Font("Arial", 4, 15));
		ta1.setEditable(false);
		sc1 = new JScrollPane(ta1);
		sc1.setBounds(((3*x)-(x-70))/2+70, y-65, x-130,100);
		marco.add(sc1);		
		
		modelolista = new DefaultListModel <Object> ();
		
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		lista.addKeyListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(((x)-(x-3))/2+20, y+70, x+300, 150);
		marco.add(sc2);
	}
	
	private void borrarDetalleRenta(String opcion) {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Delete * From Detren "+opcion);
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();	
		} modificar();
	}
	
	private void modificar() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_ent = 'N/A' where pre_id="+IDcopia;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		} disp();
	}

	private void disp() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_dis = 1 where pre_id="+IDcopia;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}

	private void Buscar() {
		trycatch();
		try {
			IDcopia = Integer.parseInt(tf1.getText());
			st = cn.createStatement();
			res = st.executeQuery("select * from Pren where pre_id="+IDcopia);
			if(res.next()==true){
				IDpelicula = res.getString("pre_pel"); 
			} else {
				colordialogo();
				JOptionPane.showOptionDialog(null,"No se encontró la película","Rentas",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				tf1.setText(""); 
				tf2.setText(""); 
			}
			cn.close();
			getNombrePel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getNombrePel() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Peliculas WHERE pel_id="+IDpelicula);
			while(res.next()){
				categoria = res.getString("pel_cat");
				String nom = res.getString("pel_nom");
				tf2.setText(nom);
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	private void cobrar() {
		if(tf3.getText().equals("")){
			colordialogo();
			 JOptionPane.showOptionDialog(
					 null,
					 "Ingrese el pago del cliente",
					 "¡Atención!",
					 JOptionPane.DEFAULT_OPTION,
					 JOptionPane.WARNING_MESSAGE,
					 null,null,null);
		} else {
			int num = Integer.parseInt(tf3.getText());
			tot = num - suma;
			tf5.setText(""+tot);
			guardarRenta();
		}
	}

	private void guardarRenta() {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Renta(ren_id, ren_per, ren_cli, ren_tot) values"+" ("
					+Integer.parseInt(tf6.getText())+", "
					+emp+", "
					+cliente+", "
					+tf4.getText()+")");	
			cn.close();
			JOptionPane.showOptionDialog(null,"Transacción exitosa","Rentar Películas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
	}

	private void guardarDetallesRenta() {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Detren(drt_id, drt_rta, drt_fec, drt_ren) values("
					+Integer.parseInt(tf6.getText())+", '"
					+Integer.parseInt(tf1.getText())+"', '"
					+fecha+"', "
					+precio+")");	
			cn.close();
			tf2.setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
	}

	private void agregar(){
		if(tf2.getText().equals("")){
			 JOptionPane.showOptionDialog(null,
					 "Debe buscar una película",
					 "¡Atención!",
					 JOptionPane.DEFAULT_OPTION,
					 JOptionPane.WARNING_MESSAGE,
					 null,null,null);
		}else{
			validarRentada();
			if(dispo.equals("0")){
				if(eli.equals("0")){
					JOptionPane.showOptionDialog(null,
							 "Esa pelicula esta Rentada\nLa devuelven el: "+ent,
							 "¡Atención!",
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.WARNING_MESSAGE,
							 null,null,null);
				} else {
					JOptionPane.showOptionDialog(null,
							 "Esa pelicula fue Eliminada!",
							 "¡Atención!",
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.WARNING_MESSAGE,
							 null,null,null);
				}
			}else{
				trycatch();
				try {
					st = cn.createStatement();
					res = st.executeQuery("select * from Categorias where cat_id="+categoria);
					while(res.next()){
						precio = res.getInt("cat_pre");
						dias = res.getInt("cat_dia");
						suma(precio);
						new sumarDias(dias, IDcopia);
					}
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String elemento=" "+tf1.getText()+"              "+tf2.getText()+"                           " +
						"                                                                                     "+precio+".00";
				modelolista.addElement(elemento);
				guardarDetallesRenta();
			}	
		}
	}

	private void validarRentada() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pren WHERE pre_id="+tf1.getText());
			while(res.next()){
				dispo = res.getString("pre_dis");
				ent = res.getString("pre_ent");
				eli = res.getString("pre_eli");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void borrarPel() {
		String valor=(String) lista.getSelectedValue();
		String IDborrar=valor.trim().substring(0,5);
		getIDpel(IDborrar);
		getCatpel();
		getPrecio();
		String cond = "where drt_rta="+IDpel+" and drt_id="+tf6.getText();
		borrarDetalleRenta(cond);
		suma(-resta);
	}

	private void getCatpel() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Peliculas WHERE pel_id="+IDpel);
			while(res.next()){
				cat = res.getString("pel_cat");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int suma(int i) {
		suma = suma + i;
		tf4.setText(""+suma+".00");
		return suma;
	}

	private void getPrecio() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Categorias WHERE cat_id="+cat);
			while(res.next()){
				resta = res.getInt("cat_pre");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getIDpel(String IDcopia) {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pren WHERE pre_id="+IDcopia);
			while(res.next()){
				IDpel = res.getString("pre_pel");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void autoid() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(ren_id) as max from Renta");
			if(res.next()){
				int ide = res.getInt(1);
				nide = 1 + ide;
				tf6.setText(""+nide);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void regresar(){
		if(modelolista.getSize()==0){
			this.dispose();
		}else{
			colordialogo();
			int op = JOptionPane.showOptionDialog(
				    this,
				    "¿Seguro que desea cancelar?", 
				    "Cancelar operación",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
					null,null, 1);
			if(op==0){
				String cond ="where drt_id="+tf6.getText();
				borrarDetalleRenta(cond);
				this.dispose();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			if(tf1.getText().equals("")){
				 colordialogo();
				 JOptionPane.showOptionDialog(
						 null,
						 "Ingrese el ID de la película",
						 "¡Atención!",
						 JOptionPane.DEFAULT_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,null,null);
			}else{
				Buscar();
			}
		}
		if(e.getSource() == bt2){
			agregar();
			tf1.setText("");
			tf2.setText("");
		}
		if(e.getSource() == bt3){
			regresar();
		}
		if(e.getSource() == bt4){
			if(tf3.getText().equals("")){
				colordialogo();
				 JOptionPane.showOptionDialog(
						 null,
						 "Ingrese el pago del cliente",
						 "¡Atención!",
						 JOptionPane.DEFAULT_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,null,null);
			}else{
				if(tf4.getText().equals("")||tf4.getText().equals("0.00")){
					colordialogo();
					 JOptionPane.showOptionDialog(
							 null,
							 "La lista de renta esta vacia",
							 "¡Atención!",
							 JOptionPane.DEFAULT_OPTION,
							 JOptionPane.WARNING_MESSAGE,
							 null,null,null);
				} else{
					if(Float.parseFloat(tf3.getText())<Float.parseFloat(tf4.getText())) {
						colordialogo();
						 JOptionPane.showOptionDialog(
								 null,
								 "El monto para pagar no es correcto",
								 "Error",
								 JOptionPane.DEFAULT_OPTION,
								 JOptionPane.ERROR_MESSAGE,
								 null,null,null);
					}
					else{
						cobrar();
						this.dispose();
					}					
				}
			}
		}
		if(e.getSource() == bt5){
			new referencia2();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int indice=lista.getSelectedIndex();
		if(indice >= 0 && indice <= 6){
			colordialogo();
			int op = JOptionPane.showOptionDialog(
				    this,
				    "¿Seguro que desea eliminar esta película?", 
				    "Eliminar película",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
					null,null, 1);
			if(op==0){
				borrarPel();
				modelolista.remove(indice);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent et) {
		if(et.getKeyCode()==KeyEvent.VK_C){
			cobrar();
		}
		if(et.getKeyCode()==KeyEvent.VK_R){
			regresar();
		}
		if(et.getKeyCode()==KeyEvent.VK_B){
			if(tf1.getText().equals("")){
				 colordialogo();
				 JOptionPane.showOptionDialog(
						 null,
						 "Ingrese el ID de la película",
						 "¡Atención!",
						 JOptionPane.DEFAULT_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,null,null);
			}else{
				Buscar();
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_A){
			agregar();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}