import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class opVentas extends Pantallas implements KeyListener{
	
	static String resultado = "";
	static JTextField txt;
	
	public opVentas(){
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
		this.setTitle("Administrador | Menu | Inventario | Ventas");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel();
		lb1.setBounds(10, 25, 90, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Copia: ");
		marco.add(lb1);
		
		txt = new JTextField("");
		txt.addKeyListener(this);
		actualizar(null);
		txt.setBounds(((x-(x-50))/2)+90, 25, 140, 30);
		txt.setFont(new Font("Arial", 4, 14));
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
		
		bt3 = new JButton();
		bt3.setBounds(x + ((x-l)/2), y + i, l, a);
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
		bt7.setBounds(x + ((x-l)/2), y + i, l, a);
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
		new verCopiasVenta();
	}

	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == bt1){
			txt.setText("");
			ta1.setText("");
			new addVenta();
		}
		if(a.getSource() == bt3){
			new verCopiasVenta();
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
				String busca = "select * from Pvta where pvt_id="+txt.getText();
				new buscar(busca, 4, ta1);
			}
		}
		if(a.getSource()==bt7){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				if(txt.getText().equals("")||ta1.getText().equals("")){
					 colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else{
					String eliminar = "delete from Pvta where pvt_id="+txt.getText();
					new eliminar(eliminar);
					ta1.setText("");
					txt.setText("");
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_B){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				ta1.setText("");
			} else{
				String busca = "select * from Pvta where pvt_id="+txt.getText();
				new buscar(busca, 4, ta1);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_N){
			txt.setText("");
			ta1.setText("");
			new addVenta();
		}
		if(e.getKeyCode()==KeyEvent.VK_E){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
			}else{
				if(txt.getText().equals("")||ta1.getText().equals("")){
					 colordialogo();
					JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID del cliente.\n-No presionó el botón buscar.\n-El cliente que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
				}else{
					String eliminar = "delete from Pvta where pvt_id="+txt.getText();
					new eliminar(eliminar);
					ta1.setText("");
					txt.setText("");
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_T){
			new verCopiasVenta();
		}
		if(e.getKeyCode()==KeyEvent.VK_R){
			this.dispose();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}