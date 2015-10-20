import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public  class referencias extends Pantallas implements ListSelectionListener{
	
	String orden, resultado, pasar;
	int op;
	boolean bloquear=false;
	
	public referencias(int opc){
		op = opc;
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(250, 400);
		this.paint();
		this.setVisible(true);
	}
	
	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Referencias");
		marco.setBackground(Color.black);
		
		lb1 = new JLabel("PELICULAS");
		lb1.setFont(new Font("Arial",Font.BOLD,16));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(0, 10, getWidth(), 40);
		marco.add(lb1);

		bt2 = new JButton("Cerrar vista");
		bt2.setFont(new Font("Arial",1,15));
		bt2.setBounds(getWidth()/2-70, 320, 140, 40);
		bt2.addActionListener(this);
		marco.add(bt2);
		
        modelolista = new DefaultListModel <Object> ();
		
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(10, 50, 225, 250);
		marco.add(sc2);
		crearTabla();
	}

	private void crearTabla() {
		trycatch();
		try {
			orden="pel_id";
			st = cn.createStatement();
			res = st.executeQuery("select * from Peliculas order by "+orden);
			while(res.next()){
				String elemento=res.getString("pel_id")+"      "+res.getString("pel_nom");
				modelolista.addElement(elemento);
				bloquear=true;
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt2){
			this.dispose();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		String valor, val; int dato;
		String value=(String) lista.getSelectedValue();
		valor=value.trim().substring(0,1);
		dato = Integer.parseInt(valor);
		
		if(dato == 1){
			valor=value.trim().substring(0,2);
			if(valor.equals("1 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			} else {
				dato = Integer.parseInt(valor);
				System.out.println("debe ser mas de 10: "+dato);
				if(dato > 9){
					resultado = valor;
				}
			}
		}
		
		if(dato == 2){
			valor=value.trim().substring(0,2);
			if(valor.equals("2 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			} else {
				dato = Integer.parseInt(valor);
				if(dato > 19){
					resultado = valor;
				}
			}
		}
		if(dato == 3){
			valor=value.trim().substring(0,2);
			if(valor.equals("3 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			} else {
				dato = Integer.parseInt(valor);
				if(dato > 29){
					resultado = valor;
				}
			}
		}
		if(dato == 4){
			valor=value.trim().substring(0,2);
			if(valor.equals("4 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			} else {
				dato = Integer.parseInt(valor);
				if(dato > 39){
					resultado = valor;
				}
			}
		}
		if(dato == 5){
			valor=value.trim().substring(0,2);
			if(valor.equals("5 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			} else {
				dato = Integer.parseInt(valor);
				if(dato > 49){
					resultado = valor;
				}
			}
		}
		if(dato == 6){
			valor=value.trim().substring(0,2);
			if(valor.equals("6 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			}else {
				dato = Integer.parseInt(valor);
				if(dato > 59){
					resultado = valor;
				}
			}
		}
		if(dato == 7){
			valor=value.trim().substring(0,2);
			if(valor.equals("7 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			}else {
				dato = Integer.parseInt(valor);
				if(dato > 69){
					resultado = valor;
				}
			}
		}
		if(dato == 8){
			valor=value.trim().substring(0,2);
			if(valor.equals("8 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			}else {
				dato = Integer.parseInt(valor);
				if(dato > 79){
					resultado = valor;
				}
			}
		}
		if(dato == 9){
			valor=value.trim().substring(0,2);
			if(valor.equals("9 ")){
				val=value.trim().substring(0,1);
				resultado = val;
			}else {
				dato = Integer.parseInt(valor);
				if(dato > 89){
					resultado = valor;
				}
			}
		}
		if(op == 1){
			addRenta.actualizar(resultado);
		}
		if(op == 2){
			addVenta.actualizar(resultado);
		}
	}
}