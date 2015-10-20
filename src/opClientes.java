import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class opClientes extends Pantallas implements KeyListener {
	
	private JButton bt;
	String cliente, emp;
	int nide;
	static String resultado = "";
	static JTextField txt;
	
	public opClientes(String iD2) {
		emp = iD2;
		setDefaultCloseOperation(0);
		this.setSize(380, 400);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void pant() {
		int x = getWidth()/2, y = 90, l = 100, a = 30, i = 50;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Empleado | Menu | Clientes");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds((x-(x-50))/2, 25, 80, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Cliente: ");
		marco.add(lb1);
		
		txt = new JTextField("");
		actualizar(null);
		txt.setBounds(((x-(x-50))/2)+90, 25, 140, 30);
		txt.setFont(new Font("Arial", 4, 14));
		txt.setEditable(false);
		txt.addKeyListener(this);
		valID(txt);
		marco.add(txt);
		
		bt6 = new JButton();
		bt6.setBounds(((x-(x-50))/2)+80+160, 25, 100, 30);
		bt6.setFont(new Font("Arial", 1, 14));
		bt6.setText("Buscar");
		bt6.addActionListener(this);
		marco.add(bt6);		
		
		bt1 = new JButton();
		bt1.setBounds(x + ((x-l)/2), y, l, a);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Nuevo");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds(x + ((x-l)/2), y + i, l, a);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Rentas");
		bt2.addActionListener(this);
		marco.add(bt2);
		
		bt3 = new JButton();
		bt3.setBounds(x + ((x-l)/2), y + i*2, l, a);
		bt3.setFont(new Font("Arial", 1, 14));
		bt3.setText("Eliminar");
		bt3.addActionListener(this);
		marco.add(bt3);
		
		bt4 = new JButton();
		bt4.setBounds(x + ((x-l)/2), y + i*3, l, a);
		bt4.setFont(new Font("Arial", 1, 14));
		bt4.setText("Cambiar");
		bt4.addActionListener(this);
		marco.add(bt4);
		
		bt = new JButton();
		bt.setBounds(x + ((x-l)/2), y + i*4, l, a);
		bt.setFont(new Font("Arial", 1, 14));
		bt.setText("Ver Todo");
		bt.addActionListener(this);
		marco.add(bt);
		
		bt5 = new JButton();
		bt5.setBounds((x-(x-50))/2, 320, l, a);
		bt5.setFont(new Font("Arial", 1, 14));
		bt5.setText("Regresar");
		bt5.addActionListener(this);
		marco.add(bt5);
		
		ta1 = new JTextArea();
		ta1.setFont(new Font("Arial", 4, 18));
		ta1.setEditable(false);
		sc1 = new JScrollPane(ta1);
		sc1.setBounds((x-(x-50))/2, y, x, 220);
		marco.add(sc1);
		new verClientes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			autoid();
			new addCliente(nide);
		}
		if(e.getSource() == bt2){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				if(buscar.cli == false){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Error, el cliente no existe","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				} else {
					cliente = txt.getText();
					new rentas(ta1, cliente, emp);
				}
			}
		}
		if(e.getSource() == bt3){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				String eliminar = "update Clientes set cli_eli = 1 where cli_id="+txt.getText();
				new eliminar(eliminar);
				ta1.setText("");
				txt.setText("");
			}	
		}
		if(e.getSource() == bt4){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				new changeCliente(txt.getText());
			}
		}
		if(e.getSource() == bt5){
			this.dispose();
		}
		if(e.getSource() == bt6){
			search();
		}
		if(e.getSource() == bt){
			new verClientes();
		}
	}

	private void autoid() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(cli_id) as max from Clientes");
			res.next();
			int ide = Integer.parseInt(res.getString(1));
			nide = ide + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}
	
	@Override
	public void keyPressed(KeyEvent et) {
		if(et.getKeyCode()==KeyEvent.VK_B){
			search();
		}
		if(et.getKeyCode()==KeyEvent.VK_N){
			autoid();
			new addCliente(nide);
		}
		if(et.getKeyCode()==KeyEvent.VK_R){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				cliente = txt.getText();
				new rentas(ta1, cliente, emp);
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_E){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				String eliminar = "delete from Clientes where cli_id="+txt.getText();
				new eliminar(eliminar);
				ta1.setText("");
				txt.setText("");
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_C){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			
			}else{
				new changeCliente(txt.getText());
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_T){
			new verClientes();
		}
		if(et.getKeyCode()==KeyEvent.VK_S){
			this.dispose();
		}
	}

	private void search() {
		if(txt.getText().equals("")){
			colordialogo();
			JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
			ta1.setText("");
		} else{
			String busca = "select * from Clientes where cli_id="+txt.getText();
			new buscar(busca, 0, ta1);
		} System.out.println(buscar.cli);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}