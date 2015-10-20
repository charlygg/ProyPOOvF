import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class changeCliente extends Pantallas{
	
	String ID;
	
	public changeCliente(String ID){
		this.ID=ID;
		setDefaultCloseOperation(0);
		this.setSize(400, 300);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void pant() {
		int x = 20, y = 40, l = 110, a = 25, ix = 120, iy = 40, yy = 200;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		marco.setBackground(Color.black);
		this.setTitle("Empleado | Menu | Clientes | Cambiar datos");

		lb1 = new JLabel();
		lb1.setBounds(0, 30, getWidth(), 25);
		lb1.setFont(new Font("Arial", 1, 20));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setText("* CAMBIAR DATOS DEL CLIENTE *");
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
		
		tf1 = new JTextField("");
		tf1.setBounds(x + ix, y + iy, 2*l, a);
		tf1.setFont(new Font("Arial", 5, 16));
		valNombres(tf1);
		marco.add(tf1);
		
		tf2 = new JTextField("");
		tf2.setBounds(x + ix, y + 2*iy, 2*l, a);
		tf2.setFont(new Font("Arial", 5, 16));
		valNombres(tf2);
		marco.add(tf2);
		
		tf3 = new JTextField("");
		tf3.setBounds(x + ix, y + 3*iy, 2*l, a);
		tf3.setFont(new Font("Arial", 5, 16));
		marco.add(tf3);
		
		tf4 = new JTextField("");
		tf4.setBounds(x + ix, y + 4*iy, 2*l, a);
		tf4.setFont(new Font("Arial", 5, 16));
		valTel(tf4);
		marco.add(tf4);
		
		bt1 = new JButton("Guardar cambios");
		bt1.setBounds((getWidth()/2-150)/2, yy + iy, 150, a);
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton("Cancelar");
		bt2.setBounds(getWidth()/2+((getWidth()/2-150)/2), yy + iy, 150, a);
		bt2.addActionListener(this);
		marco.add(bt2);
	
		rellenardatos();
		this.dispose();	
	}

	private void rellenardatos() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Clientes where cli_id="+ID);
			if(res.next()==true){
				tf1.setText(res.getString("cli_nom"));
				tf2.setText(res.getString("cli_ape"));
				tf3.setText(res.getString("cli_dir"));
				tf4.setText(res.getString("cli_tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			colordialogo();
			JOptionPane.showMessageDialog(rootPane, "Error, no se ha ingresado ningun cliente para modificar");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt1){
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals("")||tf4.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,
						"Falta llenar uno o más campos",
						"¡Atención!",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,null,null);
			}else{
				try {
				st = cn.createStatement();
				colordialogo();
				int op = JOptionPane.showOptionDialog(
						this, 
						"¿Los datos son correctos?", 
					    "Operaciones con clientes", 
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,null, 0);
					if(op==0){
						st.executeUpdate("update Clientes set cli_nom='"+tf1.getText().toUpperCase()+"', cli_ape='"+tf2.getText().toUpperCase()+
						"', cli_dir='"+ tf3.getText().toUpperCase()+"', cli_tel='"+tf4.getText()+"' where cli_id="+ID+"");
						JOptionPane.showOptionDialog(null,"Cambios guardados","Operaciones con clientes",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
						this.dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == bt2){
			this.dispose();
		}
	}
}