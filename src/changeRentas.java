import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class changeRentas extends Pantallas{

	String ID="";
	int nide,estante,nivel;
	char seccion;
	
	public changeRentas(String ide){
		this.ID=ide;
		setDefaultCloseOperation(0);
		this.setSize(350, 280);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void pant() {
		Container m = getContentPane();
		m.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Cambios Rentas");
		m.setBackground(Color.black);
		
		JLabel lb = new JLabel();
		lb.setBounds(10, 10, 120, 25);
		lb.setFont(new Font("Arial", 1, 14));
		lb.setForeground(Color.white);
		lb.setHorizontalAlignment(SwingConstants.RIGHT);
		lb.setText("ID Pelicula: ");
		m.add(lb);
		
		JTextField txtid = new JTextField(ID);
		txtid.setBounds(130, 10, 120, 25);
		txtid.setEditable(false);
		m.add(txtid);
		
		JLabel lbl = new JLabel();
		lbl.setBounds(10, 60, 120, 25);
		lbl.setFont(new Font("Arial", 1, 14));
		lbl.setForeground(Color.white);
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl.setText("Sección: ");
		m.add(lbl);
		
		JLabel lbl2 = new JLabel();
		lbl2.setBounds(10, 100, 120, 25);
		lbl2.setFont(new Font("Arial", 1, 14));
		lbl2.setForeground(Color.white);
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2.setText("Estante: ");
		m.add(lbl2);
		
		JLabel lbl3 = new JLabel();
		lbl3.setBounds(10, 140, 120, 25);
		lbl3.setFont(new Font("Arial", 1, 14));
		lbl3.setForeground(Color.white);
		lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl3.setText("Nivel: ");
		m.add(lbl3);
		
		String sec [] = {"Elegir opción","A","B"};
		cb1 = new JComboBox<Object>(sec);
		cb1.setBounds(130, 60, 120, 30);
		cb1.setFont(new Font("Arial", 5, 16));
		cb1.addActionListener(this);
		m.add(cb1);
		
		String est [] = {"Elegir opción","1","2","3","4","5"};
		cb2 = new JComboBox<Object>(est);
		cb2.setBounds(130, 100, 120, 30);
		cb2.setFont(new Font("Arial", 5, 16));
		cb2.addActionListener(this);
		m.add(cb2);
		
		String niv [] = {"Elegir opción","Abajo","Medio","Arriba"};
		cb3 = new JComboBox<Object>(niv);
		cb3.setBounds(130, 140, 120, 30);
		cb3.setFont(new Font("Arial", 5, 16));
		cb3.addActionListener(this);
		m.add(cb3);
		
		bt1 = new JButton();
		bt1.setBounds(((getWidth()/2)-120)/2, 200, 120, 30);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Guardar");
		bt1.addActionListener(this);
		m.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds((getWidth()/2)+((getWidth()/2)-120)/2, 200, 120, 30);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Cancelar");
		bt2.addActionListener(this);
		m.add(bt2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s;
		
		if(e.getSource() == bt1){
			String item=(String) cb1.getSelectedItem();
			String item2=(String) cb2.getSelectedItem();
			String item3=(String) cb3.getSelectedItem();
			if(item.equals("Elija una opción")||item2.equals("Elija una opción")||item3.equals("Elija una opción")){
				
				colordialogo();
				JOptionPane.showOptionDialog(null,"Falta seleccionar sección, estante y/o nivel","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
			
			}else{
				guardar();
				this.dispose();
			}
		}
		if(e.getSource() == bt2){
			this.dispose();
		}
		if(e.getSource()==cb1){
			s = (String) cb1.getSelectedItem();
			if(s.equals("A")){
				seccion = 'A';
			} else if(s.equals("B")){
				seccion = 'B';
			}
		}
		if(e.getSource()==cb2){
			s = (String) cb2.getSelectedItem();
			if(s.equals("1")){
				estante = 1;
			} else if(s.equals("2")){
				estante = 2;
			} else if(s.equals("3")){
				estante = 3;
			} else if(s.equals("4")){
				estante = 4;
			} else if(s.equals("5")){
				estante = 5;
			}
		}
		if(e.getSource()==cb3){
			s = (String) cb3.getSelectedItem();
			if(s.equals("Abajo")){
				nivel = 3;
			} else if(s.equals("Medio")){
				nivel = 2;
		    } else if(s.equals("Arriba")){
				nivel = 1;
		    }
		}
	}

	private void guardar() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_sec='"+seccion+"',pre_est="+estante+",pre_niv ='"+nivel+"' where pre_id="+ID+"";//Error
			st.executeUpdate(updt);	
			JOptionPane.showOptionDialog(null,"Cambios guardados","Cambios de rentas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}
	

}

