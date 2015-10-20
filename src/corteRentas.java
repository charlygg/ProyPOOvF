import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

@SuppressWarnings("serial")
public  class corteRentas extends Pantallas implements ActionListener{
	
	String resultado, pasar, total, tot_rnt, adm, aa;
	int nide;
	static String id = "";
	static JTextField txt;
	
	SimpleDateFormat formateador = new SimpleDateFormat(
			"dd'/'MM'/'yyyy", new Locale("ES"));
	Date fechaDate = new Date();
	String fecha = formateador.format(fechaDate);
	
	public corteRentas(String a){
		adm = a;
		this.setResizable(false);
		this.setSize(315, 250);
		this.paint();
		this.setVisible(true);
	}
	
	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Corte Rentas");
		this.setLocationRelativeTo(null);
		marco.setBackground(Color.black);
		
		lb1 = new JLabel("Corte de Rentas");
		lb1.setFont(new Font("Arial",Font.BOLD,16));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(0, 10, getWidth(), 40);
		marco.add(lb1);
		
		txt = new JTextField("");
		actualizar(null);
		txt.setBounds(120, 50, 70, 30);
		txt.setEditable(false);
		txt.addActionListener(this);
		marco.add(txt);
		
		bt1 = new JButton("Buscar");
		bt1.setFont(new Font("Arial",1,15));
		bt1.setBounds(10, 50, 100, 30);
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt3 = new JButton("Mostrar");
		bt3.setFont(new Font("Arial",1,15));
		bt3.setBounds(200, 50, 100, 30);
		bt3.addActionListener(this);
		marco.add(bt3);
		
		bt2 = new JButton("Guardar archivo");
		bt2.setFont(new Font("Arial",1,15));
		bt2.setBounds(getWidth()/2-75, 170, 150, 40);
		bt2.addActionListener(this);
		marco.add(bt2);
		
		aa="# Corte   Fecha         Empleado   Total          Rentas";
		lb2 = new JLabel(aa);
		lb2.setForeground(Color.white);
		lb2.setBounds(10, 100, getWidth(), 20);
		marco.add(lb2);
		
		ta1 = new JTextArea ();
        ta1.setEditable(false);
		ta1.setBounds(10, 130, 290, 30);
		marco.add(ta1);
		autoid();
		crearConsulta();
	}

	static void actualizar(String a) {
		id = a;
		txt.setText(id);
	}
	
	private void crearConsulta() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select count(drt_id) as rentas from Detren where drt_fec='"+fecha+"'");
			while(res.next()){
				tot_rnt = res.getString(1);
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} total();
	}
	
	private void total() {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select sum(drt_ren) as suma from Detren where drt_fec='"+fecha+"'");
			while(res.next()){
				this.total = res.getString(1);
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} info();
	} 

	private void info() {
	    String elemento=nide+"          "+fecha+"   "+adm+"                   "+total+"          "+tot_rnt;
		ta1.append(elemento+"\n");
	}
	
	private void guardar() {
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into CorteRen (cor_id, cor_fec, cor_adm, cor_tot, cor_ren) values (" 
					+nide+", '"
					+fecha+"', "
					+adm+", "
					+total+", "
					+tot_rnt+")");	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void autoid() {
		int ide;
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(cor_id) as max from CorteRen");
			if(res.next()){
				String lide = (res.getString(1));
				if(lide==null){
					nide=1;
				} else{
				ide=Integer.parseInt(lide);
				nide = 1 + ide;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearArchivo(){
		try{
			archivosalida = new PrintWriter(new FileWriter(direccion+"corteRentas.doc"), true);
			archivosalida.print("                                 VideoFox\n                             Corte de Rentas\n\n\n");
			archivosalida.print("# corte      Fecha        Empleado      Total             Ventas\n");
			archivosalida.print(ta1.getText()+"\n");
		    colordialogo();
			JOptionPane.showOptionDialog(null,"El corte se encuentra en:\n"+direccion+"","Corte de rentas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			archivosalida.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error: El archivo["+direccion+"corteVentaHOY.doc"+"] se encuentra abierto!");
		}	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt2){
			//validar que debe haber 1 renta en el dia para hacer el corte
			crearArchivo();
			guardar();
			this.dispose();
		}
		if(e.getSource() == bt1){
			new verCorterenta();
		}
		if(e.getSource() == bt3){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Mostrar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
			} else{
				mostrar();
			}
		}
	}

	//validar por si no existe
	private void mostrar() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select * from CorteRen where cor_id="+txt.getText());
			if(res.next()){
				ta1.setText("");
				String elemento=res.getString("cor_id")+"            "+res.getString("cor_fec")+"   "+res.getString("cor_adm")+"               "+res.getString("cor_tot")+"          "+res.getString("cor_ren");
				ta1.append(elemento+"\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}