import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public  class verCopiasRenta extends Pantallas implements ListSelectionListener{
	
	String orden, nivel, disp, resultado;
	
	public verCopiasRenta(){
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(350, 400);
		this.paint();
		this.setVisible(true);
	}
	
	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Copias de Renta | Ver todos");
		marco.setBackground(Color.black);
		
		lb2 = new JLabel("Seleccione una opción");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("Arial",1,15));
		lb2.setForeground(Color.white);
		lb2.setBounds(0,60,getWidth(),30);
		marco.add(lb2);
		
		lb1 = new JLabel("INVENTARIO DE RENTAS");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Arial",Font.BOLD,22));
		lb1.setForeground(Color.white);
		lb1.setBounds(0, 10, getWidth(), 60);
		marco.add(lb1);
		
		bt1 = new JButton("Cerrar");
		bt1.setFont(new Font("Arial",1,15));
		bt1.setBounds((getWidth()/2-100)/2, 320, 100, 30);
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton("Aceptar");
		bt2.setFont(new Font("Arial",1,15));
		bt2.setBounds(getWidth()/2+(getWidth()/2-100)/2, 320, 100, 30);
		bt2.addActionListener(this);
		marco.add(bt2);
		
		lb3 = new JLabel();
		lb3.setForeground(Color.white);
		lb3.setText("Id Copia     Id Pel.                   Ubicacion              F.Entrega");
		lb3.setBounds(10, 110, 315, 30);
		marco.add(lb3);
		
		modelolista = new DefaultListModel <Object> ();
			
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(10, 140, 315, 160);
		marco.add(sc2);
		
		orden = "pre_id";
		crearTabla();
		
	}

	private void crearTabla() {
	    trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Pren where pre_eli=0 order by "+orden);
			while(res.next()){
				switch (res.getInt("pre_niv")){
				case 1:
					nivel = "ARRIBA";
					break;
				case 2: 
					nivel = "MEDIO";
					break;
				case 3: 
					nivel = "ABAJO";
					break;
				}
				String elemento = res.getString("pre_id")
				+"              |     "+res.getString("pre_pel")
				+"     |     "+res.getString("pre_sec")
				+"     |     "+res.getString("pre_est")
				+"     |     "+ nivel
				+"     |     "+res.getString("pre_ent");
				modelolista.addElement(elemento);
			}	
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
		if(e.getSource()==bt2){
			opRentas.actualizar(resultado);
			this.dispose();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
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
	}
}