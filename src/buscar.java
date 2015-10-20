import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class buscar extends Pantallas {

	String disp, idrenta, seccion, estante, nivel, entrega, idpelicula;
	String genero, categoria, pelicula, venta, precio;
	static boolean cli;
	
	public buscar(String sql, int opc, JTextArea ta){
			trycatch();
			try {
				st = cn.createStatement();
				res = st.executeQuery(sql);
				if(res.next()==true){
					if(opc == 0){ //clientes
						ta.setText("ID: "+ res.getString("cli_id") + 
								"\nNombre: "+ res.getString("cli_nom") +
								"\nApellidos: "+ res.getString("cli_ape") +
								"\nDireccion: "+ res.getString("cli_dir") +
								"\nTelefono: "+ res.getString("cli_tel"));
						cli = true;
					}
					if(opc == 1){ //peliculas
						switch (res.getInt("pel_gen")){
							case 0:
								genero = "ACCION";
								break;
							case 1: 
								genero = "ANIMACION";
								break;
							case 2: 
								genero = "CIENCIA FICCION";
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
						switch (res.getInt("pel_cat")){
							case 3:
								categoria = "CATALOGO";
								break;
							case 1: 
								categoria = "CLASICA";
								break;
							case 2: 
								categoria = "ESTRENO";
								break;
						}
						ta.setText("ID Pelicula: "+ res.getString("pel_id")+ 
								"\nNombre : "+ res.getString("pel_nom") +
								"\nGenero: "+ genero +
								"\nCategoria: "+ categoria +
								"\nClasificacion: "+ res.getString("pel_cla") +
								"\nRating: "+ res.getString("pel_rat"));
					}
					if(opc == 2){ // copias de renta
						if(res.getString("pre_dis").equals("1")){
							disp = "Disponible";
						} else {
							disp = "Rentada";
						}
						idrenta = res.getString("pre_id");
						seccion =  res.getString("pre_sec");
						estante = res.getString("pre_est");
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
						entrega = res.getString("pre_ent");
						idpelicula = res.getString("pre_pel");
					}
					if(opc == 3){ //empleados
						ta.setText("ID: "+ res.getString("per_id") + 
								"\nNombre: "+ res.getString("per_nom") +
								"\nApellidos: "+ res.getString("per_ape") +
								"\nTipo: "+ res.getString("per_tip") +
								"\nUsuario: "+ res.getString("per_use"));
					}
					if(opc == 4){ //copias de venta
						venta = res.getString("pvt_id");
						idpelicula = res.getString("pvt_pel");
						precio = res.getString("pvt_pre");
					}
					
				} else {
					ta.setText("Error:\nEl ID a buscar no fue \nencontrado");
					cli = false;
				}
				cn.close();
				if(opc == 2){
					nomPel(ta);
				}
				if(opc == 4){
					nomPeel(ta);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	private void nomPeel(JTextArea ta) {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Peliculas WHERE pel_id="+idpelicula);
			while(res.next()){
				pelicula = res.getString("pel_nom");
				ta.setText("ID Copia: "+ venta + 
						"\nPelicula : "+ pelicula +
						"\nPrecio: $ "+ precio + ".00");
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	private void nomPel(JTextArea ta) {
		trycatch();
		try {
			st = cn.createStatement();
			res = st.executeQuery("SELECT * FROM Peliculas WHERE pel_id="+idpelicula);
			while(res.next()){
				pelicula = res.getString("pel_nom");
				ta.setText("ID Copia: "+ idrenta + 
						"\nPelicula : "+ pelicula +
						"\nEstado: "+ disp +
						"\nSeccion: "+ seccion +
						"\nEstante: "+ estante +
						"\nNivel: "+ nivel +
						"\nF. Entrega: "+ entrega);
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}