import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class changePeliculas extends Pantallas{

	String ID="";
	int cat;
	
	public changePeliculas(String ide){
		this.ID=ide;
		setDefaultCloseOperation(0);
		this.setSize(350, 180);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void pant() {
		Container m = getContentPane();
		m.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Peliculas | Cambiar");
		m.setBackground(Color.black);
		
		JLabel lb = new JLabel();
		lb.setBounds(10, 20, 120, 25);
		lb.setFont(new Font("Arial", 1, 14));
		lb.setForeground(Color.white);
		lb.setHorizontalAlignment(SwingConstants.RIGHT);
		lb.setText("ID Pelicula: ");
		m.add(lb);
		
		JTextField txtid = new JTextField(ID);
		txtid.setBounds(130, 20, 120, 25);
		txtid.setEditable(false);
		m.add(txtid);
		
		JLabel lbl = new JLabel();
		lbl.setBounds(10, 60, 120, 25);
		lbl.setFont(new Font("Arial", 1, 14));
		lbl.setForeground(Color.white);
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl.setText("Categoria: ");
		m.add(lbl);
		
		String cat [] = {"Elija una opción","Catálogo","Clásica"};
		cb2 = new JComboBox <Object> (cat);
		cb2.setBounds(130, 60, 150, 25);
		cb2.setFont(new Font("Arial", 5, 16));
		cb2.addActionListener(this);
		m.add(cb2);
		
		bt1 = new JButton();
		bt1.setBounds(((getWidth()/2)-120)/2, 105, 120, 25);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Guardar");
		bt1.addActionListener(this);
		m.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds((getWidth()/2)+((getWidth()/2)-120)/2, 105, 120, 25);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Cancelar");
		bt2.addActionListener(this);
		m.add(bt2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String algo;
		if(e.getSource()==cb2){
			algo = (String) cb2.getSelectedItem();
			if(algo.equals("Catálogo")){
				cat = 3;
			} else if(algo.equals("Clásica")){
				cat = 1;
			}
		}
		if(e.getSource() == bt1){
			String item=(String) cb2.getSelectedItem();
			if(item.equals("Elija una opción")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Elija la categoría","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
			
			}else{
				guardar();
				this.dispose();
			}
		}
		if(e.getSource() == bt2){
			this.dispose();
		}
	}

	private void guardar() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Peliculas set pel_cat = "+cat+" where pel_id="+ID;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			JOptionPane.showOptionDialog(null,"Cambios guardados","Cambios de películas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}
}
