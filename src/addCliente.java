import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class addCliente extends Pantallas {

	String ID;
	int ide;
	
	public addCliente(int IDE){
		this.ide = IDE;
		setDefaultCloseOperation(0);
		this.setSize(400, 350);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void pant() {
		int x = 20, y = 80, l = 110, a = 25, ix = 120, iy = 30;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Empleado | Menu | Clientes | Agregar ");
		marco.setBackground(Color.black);
		
		lb6 = new JLabel();
		lb6.setBounds(0, 30, getWidth(), 25);
		lb6.setFont(new Font("Arial", 1, 20));
		lb6.setForeground(Color.white);
		lb6.setHorizontalAlignment(SwingConstants.CENTER);
		lb6.setText("* AGREGAR NUEVO CLIENTE *");
		marco.add(lb6);
		
		lb1 = new JLabel();
		lb1.setBounds(x, y, l, a);
		lb1.setFont(new Font("Arial", 1, 16));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Cliente: ");
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
		lb4.setText("Direccion: ");
		marco.add(lb4);
		
		lb5 = new JLabel();
		lb5.setBounds(x, y + 4*iy, l, a);
		lb5.setFont(new Font("Arial", 1, 16));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.RIGHT);
		lb5.setText("Telefono: ");
		marco.add(lb5);
		
		tf1 = new JTextField();
		tf1.setBounds(x + ix, y, 2*l, a);
		tf1.setFont(new Font("Arial", 5, 16));
		tf1.setEditable(false);
		tf1.setText(""+ide);
		marco.add(tf1);
		
		tf2 = new JTextField();
		tf2.setBounds(x + ix, y + iy, 2*l, a);
		tf2.setFont(new Font("Arial", 5, 16));
		tf2.setText("");
		valNombres(tf2);
		marco.add(tf2);
		
		tf3 = new JTextField();
		tf3.setBounds(x + ix, y + 2*iy, 2*l, a);
		tf3.setFont(new Font("Arial", 5, 16));
		tf3.setText("");
		valNombres(tf3);
		marco.add(tf3);
		
		tf4 = new JTextField();
		tf4.setBounds(x + ix, y + 3*iy, 2*l, a);
		tf4.setFont(new Font("Arial", 5, 16));
		tf4.setText("");
		marco.add(tf4);
		
		tf5 = new JTextField();
		tf5.setBounds(x + ix, y+4*iy, 2*l, a);
		tf5.setFont(new Font("Arial", 5, 16));
		tf5.setText("");
        valTel(tf5);
		marco.add(tf5);
		
		int xb = 15, yb = 260, lb = 140, ab = 25;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals("")||tf4.getText().equals("")||tf5.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"Falta llenar uno o más campos","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
			}else{
				buscar();
			}
		}
		if(e.getSource() == bt2){
			if(!tf1.getText().equals("")||!tf2.getText().equals("")||!tf3.getText().equals("")||!tf4.getText().equals("")||!tf5.getText().equals("")){
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

	private void buscar(){
		trycatch();
		try {
			int ID = Integer.parseInt(tf1.getText());
			this.ID=tf1.getText();
			st = cn.createStatement();
			res = st.executeQuery("select * from Clientes where cli_id="+ID);
			if(res.next()==true){
				colordialogo();
				JOptionPane.showOptionDialog(null,
						"Ya existe un cliente con ese ID, elija otro",
						"Operaciones con clientes",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,null,null);
			} else {
				agregar();
				this.dispose();
			} cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void agregar() {
		trycatch();
		try {
			st = cn.createStatement();
			int eli = 0;
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Clientes (cli_id, cli_nom, cli_ape, cli_dir, cli_tel, cli_eli) values (" 
					+Integer.parseInt(tf1.getText())+", '"
					+tf2.getText().toUpperCase()+"', '"
					+tf3.getText().toUpperCase()+"', '"
					+tf4.getText().toUpperCase()+"', "
					+tf5.getText()+","
					+eli+")");	
			cn.close();
			 colordialogo();
			JOptionPane.showOptionDialog(null,"El cliente se guardó correctamente","Operaciones con clientes",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}