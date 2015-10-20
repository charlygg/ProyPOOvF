import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public  class verPeliculas extends Pantallas implements ListSelectionListener{
	
	String orden, resultado, genero, categoria;
	
	public verPeliculas(){
		this.setDefaultCloseOperation(0);
		this.setResizable(false);
		this.setSize(350, 400);
		this.paint();
		this.setVisible(true);
	}

	private void paint() {
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Peliculas | Ver todos");
		marco.setBackground(Color.black);
		
		lb2 = new JLabel("Seleccione una opción");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("Arial",1,15));
		lb2.setForeground(Color.white);
		lb2.setBounds(0,60,getWidth(),30);
		marco.add(lb2);
		
		lb1 = new JLabel("INVENTARIO DE PELICULAS");
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
		
        modelolista = new DefaultListModel <Object> ();
		
		lista = new JList <Object> (modelolista);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		sc2 = new JScrollPane(lista);
		sc2.setBounds(10, 110, 320, 190);
		marco.add(sc2);
		
		orden = "pel_id";
		crearTabla();
	}

	private void crearTabla() {	 
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("select * from Peliculas order by "+orden);
			while(res.next()){
				int gen = res.getInt("pel_gen");
				switch (gen){
					case 0:
						genero = "ACCIÓN";
						break;
					case 1: 
						genero = "ANIMACIÓN";
						break;
					case 2: 
						genero = "CIENCIA FICCIÓN";
						break;
					case 3: 
						genero = "COMEDIA";
						break;
					case 4: 
						genero = "DRAMA";
						break;
					case 5: 
						genero = "INFANTIL";
						break;
					case 6: 
						genero = "SUSPENSO";
						break;
					case 7: 
						genero = "TERROR";
						break;	
				}
				/*Por orden alfabetico:
				 * catalogo = 0, clasica = 1, estreno = 2
				 */
				int categ = res.getInt("pel_cat");
				switch (categ){
					case 3:
						categoria = "CATÁLOGO";
						break;
					case 1: 
						categoria = "CLÁSICA";
						break;
					case 2: 
						categoria = "ESTRENO";
						break;	
				}		
		String elemento=res.getString("pel_id")
			+"   |   "+res.getString("pel_nom")
			+"   |   "+genero
			+"   |   "+categoria
			+"   |   "+res.getString("pel_cla")
			+"   |   "+res.getString("pel_rat");
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
			opPeliculas.actualizar(resultado);
			this.dispose();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		String valor, val; int dato;
		String value=(String) lista.getSelectedValue();
		valor=value.trim().substring(0,1);
		dato = Integer.parseInt(valor);
		//jala del 0 al 99
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