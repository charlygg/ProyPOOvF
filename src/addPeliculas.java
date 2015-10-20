import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class addPeliculas extends Pantallas implements ChangeListener{
	
	private JProgressBar pb1;
	private JSlider ds1;
	private JLabel lb8;
	String rat, cla;
	int nide, gen, cat;

	public addPeliculas(){
		setDefaultCloseOperation(0);
		this.setSize(580, 360);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);		
	}
	
	private void pant() {                 
		int y = 60, l = 110, a = 25, x = l + 20, iy = 30;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Peliculas | Agregar");
		marco.setBackground(Color.black);
		
		lb5 = new JLabel();
		lb5.setBounds(0, 20, getWidth(), 30);
		lb5.setFont(new Font("Arial", 1, 16));
		lb5.setForeground(Color.white);
		lb5.setHorizontalAlignment(SwingConstants.CENTER);
		lb5.setText(" AGREGAR PELICULAS ");
		marco.add(lb5);
		
		lb1 = new JLabel();
		lb1.setBounds(10, y, l, a);
		lb1.setFont(new Font("Arial", 1, 16));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Película: ");
		marco.add(lb1);
		
		lb2 = new JLabel();
		lb2.setBounds(10, y + iy, l, a);
		lb2.setFont(new Font("Arial", 1, 16));
		lb2.setForeground(Color.white);
		lb2.setHorizontalAlignment(SwingConstants.RIGHT);
		lb2.setText("Nombre: ");
		marco.add(lb2);
		
		lb3 = new JLabel();
		lb3.setBounds(10, y + iy*2, l, a);
		lb3.setFont(new Font("Arial", 1, 16));
		lb3.setForeground(Color.white);
		lb3.setHorizontalAlignment(SwingConstants.RIGHT);
		lb3.setText("Género: ");
		marco.add(lb3);
		
		lb4 = new JLabel();
		lb4.setBounds(10, y + iy*3, l, a);
		lb4.setFont(new Font("Arial", 1, 16));
		lb4.setForeground(Color.white);
		lb4.setHorizontalAlignment(SwingConstants.RIGHT);
		lb4.setText("Categoría: ");
		marco.add(lb4);
		
		lb6 = new JLabel();
		lb6.setBounds(10,  y + iy*4, l, a);
		lb6.setFont(new Font("Arial", 1, 16));
		lb6.setForeground(Color.white);
		lb6.setHorizontalAlignment(SwingConstants.RIGHT);
		lb6.setText("Clasificación: ");
		marco.add(lb6);
		
		lb7 = new JLabel();
		lb7.setBounds(10, y + iy*5, l, a);
		lb7.setFont(new Font("Arial", 1, 16));
		lb7.setForeground(Color.white);
		lb7.setHorizontalAlignment(SwingConstants.RIGHT);
		lb7.setText("Rating: "+0);
		marco.add(lb7);
		
		tf1 = new JTextField();
		tf1.setBounds(x, y, (int) (l*1.5), a);
		tf1.setFont(new Font("Arial", 4, 14));
		autoid();
		tf1.setText(""+nide);
		tf1.setEditable(false);
		marco.add(tf1);
		
		tf2 = new JTextField("");
		tf2.setBounds(x, y + iy, (int) (l*1.5), a);
		tf2.setFont(new Font("Arial", 4, 14));;
		marco.add(tf2);
		
		/* Por orden alfabetico:
		 * accion = 0, animacion = 1,c. ficcion = 2, comedia = 3
		 * drama = 4, infantil = 5, suspenso = 6, terror = 7
		 */
		String gen [] = {"Elija una opción","Accion","Animacion",
				"Ciencia Ficcion","Comedia","Drama","Infantil",
				"Suspenso","Terror"};
		cb1 = new JComboBox <Object> (gen);
		cb1.setBounds(x, y + iy*2, (int) (l*1.5), a);
		cb1.setFont(new Font("Arial", 5, 16));
		cb1.addActionListener(this);
		marco.add(cb1);
		
		/*Por orden alfabetico:
		 * catalogo = 0, clasica = 1, estreno = 2
		 */
		String cat [] = {"Elija una opción","Catalogo","Clasica","Estreno"};
		cb2 = new JComboBox <Object> (cat);
		cb2.setBounds(x, y + iy*3, (int) (l*1.5), a);
		cb2.setFont(new Font("Arial", 5, 16));
		cb2.addActionListener(this);
		marco.add(cb2);
		
		String cla [] = {"Elija una opción","A", "B","B15","C"};
		cb3 = new JComboBox<Object>(cla);
		cb3.setBounds(x, y + iy*4, (int) (l*1.5), a);
		cb3.setFont(new Font("Arial", 5, 16));
		cb3.addActionListener(this);
		marco.add(cb3);
		
		pb1 =new JProgressBar(0,10);
		pb1.setBounds(x, y + iy*5, (int) (l*1.5), a);
		pb1.setForeground(Color.orange);
		pb1.setValue(0);
		marco.add(pb1);
				
		ds1 = new JSlider(JSlider.HORIZONTAL,0,10,0);
		ds1.setBounds(x, y + iy*6, (int) (l*1.5), a);
		ds1.setBackground(Color.black);
		ds1.setMajorTickSpacing(1);
		ds1.setPaintTicks(true);
		ds1.addChangeListener(this);
		marco.add(ds1);
		
		lb8 = new JLabel();
		lb8.setBounds((int) (2.5*x), y, (int) (l*1.5), a);
		lb8.setFont(new Font("Arial", 1, 16));
		lb8.setForeground(Color.white);
		lb8.setText("Sinopsis: ");
		marco.add(lb8);
		
		ta1 = new JTextArea();
		ta1.setLineWrap(true);
		ta1.setFont(new Font("Arial", 4, 15));
		sc1 = new JScrollPane(ta1);
		sc1.setBounds((int) (2.5*x), y + iy, 2*l, (int) (1.5*l));
		marco.add(sc1);
		
		int xb = getWidth()/2, yb = (int) (y + iy*7.5);
		
		bt1 = new JButton();
		bt1.setBounds((xb-l)/2, yb, l, a+5);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Regresar");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds(xb+((xb-l)/2), yb, l, a+5);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Guardar");
		bt2.addActionListener(this);
		marco.add(bt2);
	}

	private void autoid() {
		trycatch();
		try { 
			st = cn.createStatement();
			res = st.executeQuery("select max(pel_id) as max from Peliculas");
			res.next();
			int ide = Integer.parseInt(res.getString(1));
			if(ide == 0){
				nide = 10001;
			} else {
				nide = 1 + ide;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String algo;
		
		if(e.getSource() == bt1){
			String item=(String) cb1.getSelectedItem();
			String item2=(String) cb2.getSelectedItem();
			String item3=(String) cb3.getSelectedItem();
			if(!tf2.getText().equals("")||!item.equals("Elija una opción")||!item2.equals("Elija una opción")||!item3.equals("Elija una opción")){
				colordialogo();
				int op = JOptionPane.showOptionDialog(
					    this,
					    "¿Seguro que desea cancelar?", 
					    "Agregar película",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,null, 0);
				if(op==0){
					this.dispose();
				}		
			}else{
				this.dispose();
			}
		}
		if(e.getSource() == bt2){
			String item=(String) cb1.getSelectedItem();
			String item2=(String) cb2.getSelectedItem();
			String item3=(String) cb3.getSelectedItem();
			if(tf2.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese nombre de la película","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else if(ta1.getText().equals("")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Ingrese sinopsis de la película","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);	
				}else if(item.equals("Elija una opción")||item2.equals("Elija una opción")||item3.equals("Elija una opción")){
					colordialogo();
					JOptionPane.showOptionDialog(null,"Falta seleccionar género, clasificación y/o categoría","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				}else{
						guardar();
						this.dispose();
					}
		}
		/*accion = 0, animacion = 1,c. ficcion = 2, comedia = 3
		* drama = 4, infantil = 5, suspenso = 6, terror = 7
		*/
		if(e.getSource()==cb1){
			algo = (String) cb1.getSelectedItem();
			if(algo.equals("Accion")){
				gen = 0;
			} else if(algo.equals("Animacion")){
				gen = 1;
			} else if(algo.equals("Ciencia Ficcion")){
				gen = 2;
			} else if(algo.equals("Comedia")){
				gen = 3;
			} else if(algo.equals("Drama")){
				gen = 4;
			} else if(algo.equals("Infantil")){
				gen = 5;
			} else if(algo.equals("Suspenso")){
				gen = 6;
			} else if(algo.equals("Terror")){
				gen = 7;
			}
		}
		/* Por orden alfabetico:
		 * catalogo = 0, clasica = 1, estreno = 2
		 */
		if(e.getSource()==cb2){
			algo = (String) cb2.getSelectedItem();
			if(algo.equals("Catalogo")){
				cat = 3;
			} else if(algo.equals("Clasica")){
				cat = 1;
			} else if(algo.equals("Estreno")){
				cat = 2;
			}
		}
		if(e.getSource()==cb3){
			algo = (String) cb3.getSelectedItem();
			if(algo.equals("A")){
				cla = "A";
			} else if(algo.equals("B")){
				cla = "B";
			} else if(algo.equals("B15")){
				cla = "B15";
			} else if(algo.equals("C")){
				cla = "C";
			}
		}
	}

	private void guardar() {
		System.out.println("aaa");
		trycatch();
		try {
			st = cn.createStatement();
			@SuppressWarnings("unused")
			int r = st.executeUpdate("Insert into Peliculas(pel_id, pel_nom, pel_gen, pel_cat, pel_cla, pel_rat, pel_sin) values"+" ("
					+Integer.parseInt(tf1.getText())+", '"
					+tf2.getText().toUpperCase()+"', "
					+gen+", "
					+cat+", '"
					+cla+"', "
					+rat+", '"
					+ta1.getText()+"')");	
			cn.close();
			JOptionPane.showOptionDialog(null,"La Pelicula se guardó correctamente","Agregar Peliculas",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		String rating = String.valueOf(pb1.getValue());
		pb1.setValue(ds1.getValue());
		lb7.setText("Rating: "+rating);
		this.rat = rating;
	}
}
