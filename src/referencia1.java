import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public  class referencia1 extends Pantallas{
	
	String precio;
	
	public referencia1(){
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(300, 400);
		this.paint();
		this.setVisible(true);
	}
	private DefaultTableModel tabla;
	
	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Referencias");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel("PELICULAS P/VENDER");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Arial",Font.BOLD,16));
		lb1.setForeground(Color.white);
		lb1.setBounds(0, 10, getWidth(), 40);
		marco.add(lb1);
		
		lb2 = new JLabel("Peliculas en Venta: ");
		lb2.setFont(new Font("Arial",1,15));
		lb2.setForeground(Color.white);
		lb2.setBounds(30,60,150,30);
		marco.add(lb2);
		
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
		
		crearTabla();
	}

	private void crearTabla() {
		String [] encabezado  = {"ID Copia","ID Pelicula","Precio"};
		String [] contenido = new String [3];
	    tabla = new DefaultTableModel(null,encabezado);
	    trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Pvta where pvt_ven = 0 order by pvt_id");
			while(res.next()){
				String a = res.getString("pvt_pre");
				precio = a+".00";
				contenido[0] = res.getString("pvt_id");
				contenido[1] = res.getString("pvt_pel");
				contenido[2] = precio;
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
		if(e.getSource()==bt1){
			this.dispose();
		}
	}
}