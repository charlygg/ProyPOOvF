import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

@SuppressWarnings("serial")
public class opEmpleados extends Pantallas implements KeyListener{

	static String resultado = "", adm, act;
	static JTextField txt;
	
	public opEmpleados(String id){
		adm = id;
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
		this.setTitle("Administrador | Menu | Empleados");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds(0, 25, 110, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Empleado: ");
		marco.add(lb1);
		
		txt = new JTextField("");
		txt.addKeyListener(this);
		actualizar(null);
		txt.setBounds(((x-(x-50))/2)+90, 25, 140, 30);
		txt.setFont(new Font("Arial", 4, 14));
		
		txt.setEditable(false);
		valID(txt);
		marco.add(txt);
		
		bt4 = new JButton();
		bt4.setBounds(((x-(x-50))/2)+80+160, 25, 100, 30);
		bt4.setFont(new Font("Arial", 1, 14));
		bt4.setText("Buscar");
		bt4.addActionListener(this);
		marco.add(bt4);		
		
		bt1 = new JButton();
		bt1.setBounds(x + ((x-l)/2), y, l, a);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Nuevo");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt5 = new JButton();
		bt5.setBounds(x + ((x-l)/2), y + i*2, l, a);
		bt5.setFont(new Font("Arial", 1, 14));
		bt5.setText("Ver Todo");
		bt5.addActionListener(this);
		marco.add(bt5);
		
		bt2 = new JButton();
		bt2.setBounds(x + ((x-l)/2), y + i, l, a);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Eliminar");
		bt2.addActionListener(this);
		marco.add(bt2);
		
		bt3 = new JButton();
		bt3.setBounds((x-(x-50))/2, 320, l, a);
		bt3.setFont(new Font("Arial", 1, 14));
		bt3.setText("Regresar");
		bt3.addActionListener(this);
		marco.add(bt3);
		
		ta1 = new JTextArea();
		ta1.setFont(new Font("Arial", 4, 18));
		ta1.setEditable(false);

		sc1 = new JScrollPane(ta1);
		sc1.setBounds((x-(x-50))/2, y, x, 220);
		marco.add(sc1);
		new verEmpleados();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			new addEmpleado();
		}
		if(e.getSource() == bt2){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				if(txt.getText().equals("")||ta1.getText().equals("")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else if(ta1.getText().equals("Error:\nEl ID a buscar no fue \nencontrado")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else{
					validar();
					if(act.equals(adm)){
						colordialogo();
						JOptionPane.showOptionDialog(null,"Error: No se puede eliminar a si mismo","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
					}else{
						String eliminar = "update Personal set per_eli=1, per_use='', per_pas='' where per_id="+txt.getText();
						new eliminar(eliminar);
						ta1.setText("");
						txt.setText("");
					}
				}	
			}
		}
		if(e.getSource() == bt3){
			this.dispose();
		}
		if(e.getSource() == bt4){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				ta1.setText("");
			} else{
				String busca = "select * from Personal where per_id="+txt.getText();
				new buscar(busca, 3, ta1);
			}
		}
		if(e.getSource() == bt5){
			new verEmpleados();
		}
	}

	private void validar() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Personal where per_id="+txt.getText());
			if(res.next()){
				act = res.getString("per_id");
			} cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent et) {
		if(et.getKeyCode()==KeyEvent.VK_B){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				ta1.setText("");
			} else{
				String busca = "select * from Personal where per_id="+txt.getText();
				new buscar(busca, 3, ta1);
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_N){
			new addEmpleado();
		}
		if(et.getKeyCode()==KeyEvent.VK_E){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				if(txt.getText().equals("")||ta1.getText().equals("")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else if(ta1.getText().equals("Error:\nEl ID a buscar no fue \nencontrado")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del empleado.\n-No presionó el botón buscar.\n-El empleado que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else{
					String eliminar = "delete from Personal where per_id="+txt.getText();
					new eliminar(eliminar);
					ta1.setText("");
					txt.setText("");
				}	
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_T){
			new verEmpleados();
		}
		if(et.getKeyCode()==KeyEvent.VK_R){
			this.dispose();
		}
	}
	
	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}