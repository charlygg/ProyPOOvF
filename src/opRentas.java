import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class opRentas extends Pantallas implements KeyListener {
	
	int cat;
	static String resultado = "";
	static JTextField txt;
	
	public opRentas(){
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
		this.setTitle("Administrador | Menu | Inventario | Rentas");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds(10, 25, 90, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Copia: ");
		marco.add(lb1);
		
		txt = new JTextField("");
		actualizar(null);
		txt.setBounds(((x-(x-50))/2)+90, 25, 140, 30);
		txt.setFont(new Font("Arial", 4, 14));
		txt.addKeyListener(this);
		valID(txt);
		txt.setEditable(false);
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
		bt2.setText("Cambiar");
		bt2.addActionListener(this);
		marco.add(bt2);
		
		bt3 = new JButton();
		bt3.setBounds(x + ((x-l)/2), y + i*3, l, a);
		bt3.setFont(new Font("Arial", 1, 14));
		bt3.setText("Ver Todo");
		bt3.addActionListener(this);
		marco.add(bt3);
				
		bt5 = new JButton();
		bt5.setBounds((x-(x-50))/2, 320, l, a);
		bt5.setFont(new Font("Arial", 1, 14));
		bt5.setText("Regresar");
		bt5.addActionListener(this);
		marco.add(bt5);
		
		bt7 = new JButton();
		bt7.setBounds(x + ((x-l)/2), y + i*2, l, a);
		bt7.setFont(new Font("Arial", 1, 14));
		bt7.setText("Eliminar");
		bt7.addActionListener(this);
		marco.add(bt7);
		
		ta1 = new JTextArea();
		ta1.setFont(new Font("Arial", 4, 18));
		ta1.setEditable(false);
		sc1 = new JScrollPane(ta1);
		sc1.setBounds((x-(x-50))/2, y, x, 220);
		marco.add(sc1);
		new verCopiasRenta();
	}

	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == bt1){
			new addRenta();
		}
		if(a.getSource() == bt2){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			
			}else{
				new changeRentas(txt.getText());
			}
		}
		if(a.getSource() == bt3){
			new verCopiasRenta();
		}
		if(a.getSource() == bt5){
			this.dispose();
		}
		if(a.getSource() == bt6){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				ta1.setText("");
			} else{
				String busca = "select * from Pren where pre_id="+txt.getText();
				new buscar(busca, 2, ta1);
			}
		}
		if(a.getSource()==bt7){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				validar();
			}
		}
	}

	private void validar() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Pren WHERE pre_id="+txt.getText());
			while(res.next()){
				String comp = res.getString("pre_dis");
				if(comp.equals("0")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"NO Puede Eliminar una copia RENTADA","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				} else {
					if(txt.getText().equals("")||ta1.getText().equals("")){
						colordialogo();
						JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
					}else{
						String eliminar = "update Pren set pre_eli=1, pre_dis=0, pre_ent='Eliminada' where pre_id="+txt.getText();
						new eliminar(eliminar);
						ta1.setText("");
						txt.setText("");
					}
				}
			cn.close();
			}
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
				String busca = "select * from Pren where pre_id="+txt.getText();
				new buscar(busca, 2, ta1);
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_N){
			new addRenta();
		}
		if(et.getKeyCode()==KeyEvent.VK_C){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			
			}else{
				new changeRentas(txt.getText());
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_E){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				validar();
			}
		}
		if(et.getKeyCode()==KeyEvent.VK_T){
			new verCopiasRenta();
		}
		if(et.getKeyCode()==KeyEvent.VK_R){
			this.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}