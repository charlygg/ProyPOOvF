import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class pantPrin extends JFrame implements ActionListener,KeyListener {

	private JButton btn, b1, b2, b3, b4, cerrar, cerrar1, sal;
	private JButton bot1, bot2, bot3;
	private JTextArea inf;
	private JTextField usertxt;
	private JComboBox <Object> cb;
	private JPasswordField passtxt;
	private JLabel fec, tit, user, pass;
	private JLabel lblventa,lbllogo,lblclientes,lblcorterentas,lblcorteventas,lblentregas,lblinventario, lblnuevoempleado;
	private ImageIcon imgventa,imgllave,imglogo,imgclientes,imgcorterentas,imgcorteventas,imgentregas,imginventario,imgnuevoempleado;
	String nombre,apellidos, tipo, usuario,ID;
	String url = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=VideoFox.mdb";
	Connection cn;
	Statement st;
	ResultSet rs;
	private int inventario = 0;
	
	public static void main(String[] args){
		new pantPrin();
	}
	
	public pantPrin(){		
		setDefaultCloseOperation(0);
		this.setSize(700, 550);
		this.setLocationRelativeTo(null);
		this.conectar();
		this.setResizable(false);
		this.setVisible(true);
		ImageIcon icono = new ImageIcon("logo.png");
	    this.setIconImage(icono.getImage());
	}
	
	private void principal() {
		int xt = 80, yt = 230, lt = 220, at = 50;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Iniciar Sesión");
		
		imglogo=new ImageIcon("logo.png");
		lbllogo=new JLabel(imglogo);
		
		imgllave=new ImageIcon("llave.png");
		imgclientes=new ImageIcon("clientes.png");
		lblclientes=new JLabel(imgclientes);
		imgcorterentas=new ImageIcon("corterentas.png");
		lblcorterentas=new JLabel(imgcorterentas);
		imgcorteventas=new ImageIcon("corteventas.png");
		lblcorteventas=new JLabel(imgcorteventas);
		imgentregas=new ImageIcon("entregas.png");
		lblentregas=new JLabel(imgentregas);
		imginventario=new ImageIcon("inventario.png");
		lblinventario=new JLabel(imginventario);
		imgnuevoempleado=new ImageIcon("nuevoempleado.png");
		lblnuevoempleado=new JLabel(imgnuevoempleado);
		imgventa=new ImageIcon("renta.png");
		lblventa=new JLabel(imgventa);
		marco.setBackground(Color.black);
		
		SimpleDateFormat formateador = new SimpleDateFormat(
				"EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		Date fechaDate = new Date();
		String fecha = formateador.format(fechaDate);
		fec = new JLabel();		
		fec.setBounds(getWidth()/2, 50, getWidth()/2, 30);
		fec.setForeground(Color.white);
		fec.setFont(new Font("Arial", 5, 20));
		fec.setText(fecha);
		marco.add(fec);
		
		lbllogo.setBounds(30, 10, 140, 140);
		marco.add(lbllogo);
		
		tit = new JLabel();
		tit.setBounds(0, 140, 700, 50);
		tit.setFont(new Font("Arial", 1, 44));
		tit.setHorizontalAlignment(SwingConstants.CENTER);
		tit.setForeground(Color.white);
		tit.setText("   INICIAR SESION   ");
		marco.add(tit);
		
		user = new JLabel();
		user.setBounds(xt, yt, lt, at);
		user.setFont(new Font("Arial", 1, 36));
		user.setHorizontalAlignment(SwingConstants.RIGHT);
		user.setForeground(Color.white);
		user.setText("Usuario: ");
		marco.add(user);
		
		pass = new JLabel();
		pass.setBounds(xt, yt+100, lt, at);
		pass.setFont(new Font("Arial", 1, 36));
		pass.setHorizontalAlignment(SwingConstants.RIGHT);
		pass.setForeground(Color.white);
		pass.setText("Contraseña: ");
		marco.add(pass);
		
		usertxt = new JTextField();
		usertxt.setBounds(xt + lt + 20, yt, lt+50, at);
		usertxt.setFont(new Font("Arial", 4, 36));
		usertxt.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			private char caracter;
			public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if (usertxt.getText().length()==5){
			            e.consume();
			      }
			   }                          
			});
		marco.add(usertxt);

		passtxt = new JPasswordField();
		passtxt.setBounds(xt + lt + 20, yt+100, lt+50, at);
		passtxt.addKeyListener(this);
		passtxt.setEchoChar('☻');
		passtxt.setFont(new Font("Arial", 4, 36));
		passtxt.addKeyListener(new KeyAdapter(){
			@SuppressWarnings("unused")
			private char caracter;
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if (passtxt.getText().length()==10){
			            e.consume();
			      }
			   }                          
			});
		marco.add(passtxt);
		
		btn = new JButton();
		btn.setBounds(415,430, lt, at);
		btn.setFont(new Font("Arial", 1, 28));
		btn.setText("Ingresar");
		btn.addActionListener(this);
		btn.setIcon(imgllave);
		marco.add(btn);
		
		sal = new JButton();
		sal.setBounds(65, 430, lt, at);
		sal.setFont(new Font("Arial", 1, 28));
		sal.setText("Salir");
		sal.addActionListener(this);
		marco.add(sal);
	}
	
	private void limpiar() {		
		tit.setVisible(false);
		user.setVisible(false);
		pass.setVisible(false);
		usertxt.setVisible(false);
		passtxt.setVisible(false);
		btn.setVisible(false);
		sal.setVisible(false);
	}

	private void menuAdm(){
		int xt = getWidth()/2, yt = 150, lt = 200, at = 55, iy = 90;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu");
		
		b1 = new JButton();
		b1.setBounds((xt + ((xt-lt)/2))+(lt/3) +75, yt, 60, at);
		b1.addActionListener(this);
		b1.add(lblinventario);
		marco.add(b1);
		
		String [] q = {"Elegir opción","Películas","Copias p/ Rentar","Copias p/ Vender"};
		cb = new JComboBox <Object> (q);
		cb.setBounds((xt + ((xt-lt)/2)), yt, 130, at);
		cb.addActionListener(this);
		marco.add(cb);
		
		b2 = new JButton();
		b2.setBounds(xt + ((xt-lt)/2), yt + iy, lt, at);
		b2.setFont(new Font("Arial", 1, 15));
		b2.setHorizontalAlignment(SwingConstants.RIGHT);
		b2.setText("Empleados");
		b2.addActionListener(this);
		b2.add(lblnuevoempleado);
		marco.add(b2);
		
		b3 = new JButton();
		b3.setBounds(xt + ((xt-lt)/2), yt + iy*2, lt, at);
		b3.setFont(new Font("Arial", 1, 15));
		b3.setHorizontalAlignment(SwingConstants.RIGHT);
		b3.setText("Corte Ventas");
		b3.addActionListener(this);
		b3.add(lblcorteventas);
		marco.add(b3);
		
		b4 = new JButton();
		b4.setBounds(xt + ((xt-lt)/2), yt + iy*3, lt, at);
		b4.setFont(new Font("Arial", 1, 15));
		b4.setHorizontalAlignment(SwingConstants.RIGHT);
		b4.setText("Corte Rentas");
		b4.addActionListener(this);
		b4.add(lblcorterentas);
		marco.add(b4);
		
		cerrar = new JButton();
		cerrar.setBounds(((xt-(xt-30))/2), 430, 220, 50);
		cerrar.setFont(new Font("Arial", 1, 15));
		cerrar.setText("Cerrar Sesión");
		cerrar.addActionListener(this);
		marco.add(cerrar);
		
		inf = new JTextArea();
		inf.setBounds(((xt-(xt-30))/2), yt, xt, 250);
		inf.setFont(new Font("Arial", 4, 18));
		inf.setText("\nID Adm: "+ID+
					"\nNombre: "+nombre+" "+apellidos+
					"\nTipo: ADMINISTRADOR"+
					"\nUsuario: "+usuario);
		inf.setEditable(false);
		marco.add(inf);
	}
	
	private void menuEmp() {
		int xt = getWidth()/2, yt = 150, lt = 200, at = 55, iy = 90;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Empleado | Menu");
		
		bot1 = new JButton();
		bot1.setBounds(xt + ((xt-lt)/2), yt, lt, at);
		bot1.setFont(new Font("Arial", 1, 15));
		bot1.setHorizontalAlignment(SwingConstants.RIGHT);
		bot1.setText("Clientes");
		bot1.addActionListener(this);
		bot1.add(lblclientes);
		marco.add(bot1);
		
		bot2 = new JButton();
		bot2.setBounds(xt + ((xt-lt)/2), yt + iy, lt, at);
		bot2.setFont(new Font("Arial", 1, 15));
		bot2.setHorizontalAlignment(SwingConstants.RIGHT);
		bot2.setText("Entrega");
		bot2.addActionListener(this);
		bot2.add(lblentregas);
		marco.add(bot2);
		
		bot3 = new JButton();
		bot3.setBounds(xt + ((xt-lt)/2), yt + iy*2, lt, at);
		bot3.setFont(new Font("Arial", 1, 15));
		bot3.setHorizontalAlignment(SwingConstants.RIGHT);
		bot3.setText("Ventas");
		bot3.addActionListener(this);
		bot3.add(lblventa);
		marco.add(bot3);
		
		cerrar1 = new JButton();
		cerrar1.setBounds(((xt-(xt-30))/2), 430, 220, 50);
		cerrar1.setFont(new Font("Arial", 1, 15));
		cerrar1.setText("Cerrar Sesión");
		cerrar1.addActionListener(this);
		marco.add(cerrar1);
		
		inf = new JTextArea();
		inf.setBounds(((xt-(xt-30))/2), yt, xt, 250);
		inf.setFont(new Font("Arial", 4, 18));
		inf.setText("\nID Emp: "+ID+
				"\nNombre: "+nombre+" "+apellidos+
				"\nTipo: EMPLEADO"+
				"\nUsuario: "+usuario);
		inf.setEditable(false);
		marco.add(inf);
	}
	
	@SuppressWarnings("deprecation")
	public void loguear() {
		try {
			st = cn.createStatement();  
			rs = st.executeQuery("select * from Personal where per_use='"+usertxt.getText()+"'"); 
			if(rs.next()==true){
				String password;
				password = rs.getString("per_pas");
				if(passtxt.getText().equals(password)){
					if(Integer.parseInt(rs.getString("per_tip"))==1){
						String ID = rs.getString("per_id");
						this.ID=ID;
						String nombre = rs.getString("per_nom");
						this.nombre=nombre;
						String apellidos = rs.getString("per_ape");
						this.apellidos=apellidos;
						String usuario = rs.getString("per_use");
						this.usuario=usuario;
						colordialogo();
						JOptionPane.showOptionDialog(null,"Bienvenido a VideoFox: " + nombre + " " + apellidos,"Administrador",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
						limpiar();
						menuAdm();
					} else{
						String ID = rs.getString("per_id");
						this.ID=ID;
						String nombre = rs.getString("per_nom");
						this.nombre=nombre;
						String apellidos = rs.getString("per_ape");
						this.apellidos=apellidos;
						String usuario = rs.getString("per_use");
						this.usuario=usuario;
						colordialogo();
						JOptionPane.showOptionDialog(null,"Bienvenido a VideoFox: " + nombre + " " + apellidos,"Empleado",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
						limpiar();
						menuEmp();
					}
					
				}else{
					colordialogo();
					JOptionPane.showOptionDialog(null,"Contraseña incorrecta","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}
			}else{
				if(usertxt.getText().equals("")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Ingrese nombre de usuario","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else{
					colordialogo();
					JOptionPane.showOptionDialog(null,"El usuario no existe","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void desconectar() {
		try {
			cn.close();
			colordialogo();
			JOptionPane.showOptionDialog(null,"La Base de Datos se ha desconectado","Conexión",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	private void conectar() {	
		try {
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			colordialogo();
			JOptionPane.showMessageDialog(rootPane, "Error: "+e1);
		}
		try{ 
			cn = DriverManager.getConnection(url,"null0","null0");
		} catch(Exception e2){
			colordialogo();
			JOptionPane.showMessageDialog(rootPane, "Error: "+e2);
			JOptionPane.showMessageDialog(rootPane, "VideoFox se cerrará... ");
			System.exit(0);
		} this.principal();
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btn){
			loguear();
		}
		if(evt.getSource() == sal){
			desconectar();
			System.exit(0);
		}
		if(evt.getSource() == cerrar){
			b1.setVisible(false);
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(false);
			cb.setVisible(false);
			cerrar.setVisible(false);
			inf.setVisible(false);
			principal();
		}
		if(evt.getSource() == cerrar1){
			bot1.setVisible(false);
			bot2.setVisible(false);
			bot3.setVisible(false);
			cerrar1.setVisible(false);
			inf.setVisible(false);
			principal();
		}
		if(evt.getSource() == bot1){
			new opClientes(ID);
		}
		if(evt.getSource() == bot2){
			new verEntregas();
		}
		if(evt.getSource() == bot3){
			new ventas(ID);
		}
		if(evt.getSource() == b2){
			new opEmpleados(ID);
		}
		if(evt.getSource()==cb){
			String algo = (String) cb.getSelectedItem();
			if(algo.equals("Películas")){
				inventario = 1;
			} else if(algo.equals("Copias p/ Rentar")){
				inventario = 2;
			} else if(algo.equals("Copias p/ Vender")){
				inventario = 3;
			}else if(algo.equals("Elegir opción")){
				inventario = 0;
			}
		}
		if(evt.getSource() == b1){
			if(inventario == 1){
				new opPeliculas();
			} else if(inventario == 2){
				new opRentas();
			} else if(inventario == 3){
				new opVentas();
			} else {
				colordialogo();
				JOptionPane.showOptionDialog(null,"Debe elegir una opcion","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			}
		}
		if(evt.getSource() == b3){
			new corteVentas(ID);
		}
		if(evt.getSource() == b4){
			new corteRentas(ID);
		}
	}
	
	public void colordialogo(){
		UIManager.put("OptionPane.background",Color.black);
        UIManager.put("OptionPane.messageForeground",Color.white);
        UIManager.put("Panel.background",Color.black);
	}

	@Override
	public void keyPressed(KeyEvent et) {
		if(et.getKeyCode()==KeyEvent.VK_ENTER){
			loguear();
		}
	}

	@Override
	public void keyReleased(KeyEvent et) {
	}

	@Override
	public void keyTyped(KeyEvent et) {
	}
}