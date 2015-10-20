import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public  class verEntregas extends Pantallas implements ListSelectionListener{
	
	String orden, resultado, idcopia, idpel, entrega, pelicula;
	
	public verEntregas(){
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(400, 420);
		this.paint();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	String [] seleccion = {"Copia","F. Entrega"};

	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Empleados | Menu | Entregas");
		marco.setBackground(Color.black);
				
		lb1 = new JLabel("ENTREGA DE PELICULAS");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Arial",Font.BOLD,25));
		lb1.setForeground(Color.white);
		lb1.setBounds(0, 30, getWidth(), 40);
		marco.add(lb1);
		
		bt1 = new JButton("Cerrar");
		bt1.setFont(new Font("Arial",1,15));
		bt1.setBounds((getWidth()/2-130)/2, 340, 130, 40);
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton("Entrega");
		bt2.setFont(new Font("Arial",1,15));
		bt2.setBounds(getWidth()/2+(getWidth()/2-130)/2, 340, 130, 40);
		bt2.addActionListener(this);
		marco.add(bt2);
		
		lb2 = new JLabel();
		lb2.setForeground(Color.white);
		lb2.setText("ID de la copia        ID de la película              Fecha de entrega");
		lb2.setBounds(22, 90, getWidth(), 30);
		marco.add(lb2);
		
		modelolista = new DefaultListModel <Object> ();
			
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(20, 120, getWidth()-40, 200);
		marco.add(sc2);
		
		crearTabla();
	}

	private void crearTabla() {
	    trycatch();
	    try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Pren where pre_dis=0 order by pre_ent");
			while(res.next()){
				idcopia = res.getString("pre_id");
				idpel = res.getString("pre_pel");
				entrega = res.getString("pre_ent");
				String elemento= "               "+idcopia
				+"               |               "+idpel
				+"               |               "+entrega;
				modelolista.addElement(elemento);
			}			
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1){
			this.dispose();
		}
		if(e.getSource() == bt2){
			modificar();
			this.dispose();
		}
	}

	private void modificar() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_ent = 'N/A' where pre_id="+resultado;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		} disp();
	}

	private void disp() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_dis = 1 where pre_id="+resultado;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
			colordialogo();
			JOptionPane.showOptionDialog(rootPane,"Pelicula No. "+resultado+" entregada","Entregas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
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
	}
}