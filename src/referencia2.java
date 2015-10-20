import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public  class referencia2 extends Pantallas{
	
	String orden, nivel, disp;
	
	public referencia2(){
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(300, 400);
		this.paint();
		this.setVisible(true);
	}
	private DefaultTableModel tabla;
	String [] seleccion = {"Seleccione una opcion","Disponibles","No Disponibles"};

	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Referencias");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel("PELICULAS P/RENTAR");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Arial",Font.BOLD,16));
		lb1.setForeground(Color.white);
		lb1.setBounds(0, 10, getWidth(), 40);
		marco.add(lb1);
		
		lb2 = new JLabel("Ver Peliculas: ");
		lb2.setFont(new Font("Arial",1,15));
		lb2.setForeground(Color.white);
		lb2.setBounds(30,60,120,30);
		marco.add(lb2);
		
		cb1 = new JComboBox<Object>(seleccion);
		cb1.setBounds(130, 60, 150, 30);
		cb1.addActionListener(this);
		marco.add(cb1);
		
		bt1 = new JButton("Cerrar vista");
		bt1.setFont(new Font("Arial",1,15));
		bt1.setBounds((getWidth()-120)/2, 310, 120, 40);
		bt1.addActionListener(this);
		marco.add(bt1);
		
		tb1 = new JTable();
		tb1.setDragEnabled(true);
		tb1.setCellSelectionEnabled(true);
		sc1 = new JScrollPane(tb1);
		sc1.setBounds(25, 110, 250, 180);
		marco.add(sc1);
	}

	private void crearTabla() {
		String [] encabezado  = {"ID Copia","ID Pelicula","F. Entrega"};
		String [] contenido = new String [3];
	     tabla = new DefaultTableModel(null,encabezado);
	     trycatch();
				try {
					st = cn.createStatement();
					res = st.executeQuery("select * from Pren "+orden);
					while(res.next()){
						contenido[0] = res.getString("pre_id");
						contenido[1] = res.getString("pre_pel");
						contenido[2] = res.getString("pre_ent");
						tabla.addRow(contenido);
					}
					tb1.setModel(tabla);
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String algo;
		if(e.getSource()==bt1){
			this.dispose();
		}
		if(e.getSource()==cb1){
			algo = (String) cb1.getSelectedItem();
			if(algo.equals("Disponibles")){
				orden = "where pre_dis = 1 order by pre_id";
				crearTabla();
			} else if(algo.equals("No Disponibles")){
				orden = "where pre_dis = 0 and pre_eli=0 order by pre_ent";
				crearTabla();
			} 
		}
	}
}