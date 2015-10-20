import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class ventas extends Pantallas implements ListSelectionListener,KeyListener{
	
	String IDpelicula, IDpel, cat, ent, emp;
	int precio, resta, suma, tot, nide, dias, IDcopia, dispo;
		
	SimpleDateFormat formateador = new SimpleDateFormat(
			"dd'/'MM'/'yyyy", new Locale("ES"));
	Date fechaDate = new Date();
	String fecha = formateador.format(fechaDate);
	
	public ventas(String e){
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
		this.setTitle("Empleado | Menu | Ventas");
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
		lb5.setText("Numero Venta: ");
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
		
		modelolista = new DefaultListModel <Object> ();
		
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(((x)-(x-3))/2+20, y+70, x+300, 150);
		marco.add(sc2);
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
			validarcobro();
		}
		if(e.getSource() == bt5){
			new referencia1();
		}
	}
	private void validarcobro(){
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

	private void borrarDetalleVenta(String opcion) {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Delete * From Detvent "+opcion);
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();	
		} modificar();
	}
	
	private void modificar() {
		trycatch();
		try {

			System.out.println("Idcopiaaaaaaaa vale "+ IDcopia);
			st = cn.createStatement();
			String updt = "Update Pvta set pvt_ven = 0 where pvt_id="+IDcopia;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}

	private void mod(String ideee) {
		trycatch();
		System.out.println("idee: "+ideee);
		try {
			st = cn.createStatement();
			String updt = "Update Pvta set pvt_ven = 0 where pvt_id="+ideee;
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
			System.out.println("Idcopia vale "+ IDcopia);
			st = cn.createStatement();
			res = st.executeQuery("select * from Pvta where pvt_id="+IDcopia);
			if(res.next()==true){
				IDpelicula = res.getString("pvt_pel");
				precio = res.getInt("pvt_pre");
			} else {
				colordialogo();
				JOptionPane.showOptionDialog(null,"No se encontró la película","Ventas",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
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
			guardarVenta();
		}
	}

	private void guardarVenta() {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Ventas(ven_id, ven_per, ven_tot ) values"+" ("
					+Integer.parseInt(tf6.getText())+", "
					+emp+", "
					+tf4.getText()+")");	
			cn.close();
			JOptionPane.showOptionDialog(null,"Transacción exitosa","Vender Películas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}
	}

	private void guardarDetallesVenta() {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Detvent(dtv_ven, dtv_pvt, dtv_fec, dtv_sub) values("
					+Integer.parseInt(tf6.getText())+", '"
					+Integer.parseInt(tf1.getText())+"', '"
					+fecha+"',"
					+precio+")");	
			cn.close();
			tf2.setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} algo();
	}

	private void algo() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pvta set pvt_ven = 1 where pvt_id="+IDcopia;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
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
			validar();
			if(dispo==1){
				JOptionPane.showOptionDialog(null,
						 "Esa pelicula fue Vendida",
						 "¡Atención!",
						 JOptionPane.DEFAULT_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,null,null);
			}else{
			suma(precio);
			String elemento=" "+tf1.getText()+"              "+tf2.getText()+"                           " +
					"                                                                                     "+precio+".00";
			modelolista.addElement(elemento);
			guardarDetallesVenta();
		}
		}
	}

	private void validar() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pvta WHERE pvt_id="+tf1.getText());
			while(res.next()){
				dispo = res.getInt("pvt_ven");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
					null,null, 0);
			if(op==0){
				borrarPel();
				modelolista.remove(indice);
			}
		}
	}

	private void borrarPel() {
		String valor=(String) lista.getSelectedValue();
		String IDborrar=valor.trim().substring(0,5);
		getIDpel(IDborrar);
		String cond = "where dtv_pvt="+IDpel+" and dtv_ven="+tf6.getText();
		borrarDetalleVenta(cond);
		getPrecio(IDborrar);
		mod(IDborrar);
		suma(-resta);
	}

	private void getPrecio(String iDborrar) {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pvta WHERE pvt_id="+iDborrar);
			while(res.next()){
				resta = res.getInt("pvt_pre");
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

	private void getIDpel(String IDcopia) {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pvta WHERE pvt_id="+IDcopia);
			while(res.next()){
				IDpel = res.getString("pvt_pel");
				precio = res.getInt("pvt_pre");
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
			res = st.executeQuery("select max(ven_id) as max from Ventas");
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
				String cond ="where dtv_ven="+tf6.getText();
				borrarDetalleVenta(cond);
				this.dispose();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent et) {
		if(et.getKeyCode()==KeyEvent.VK_P){
			new referencia1();
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
		if(et.getKeyCode()==KeyEvent.VK_R){
			regresar();
		}
		if(et.getKeyCode()==KeyEvent.VK_C){
			validarcobro();
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