import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class opPeliculas extends Pantallas{
	
	String nombre,genero,categoria,clasif,rating;
	int cat;
	static String resultado = "";
	static JTextField txt;
	
	public opPeliculas(){
		setDefaultCloseOperation(0);
		this.setSize(380, 400);
		this.setLocationRelativeTo(this);
		this.pant();
		this.setResizable(false);
		this.setVisible(true);		
	}

	private void pant() {
		int x = getWidth()/2, y = 90, l = 100, a = 30, i = 50;
		
		Container marco = getContentPane();
		marco.setLayout(null);
		this.setTitle("Administrador | Menu | Inventario | Peliculas");
		marco.setBackground(Color.black);

		lb1 = new JLabel();
		lb1.setBounds(10, 25, 90, 30);
		lb1.setFont(new Font("Arial", 1, 14));
		lb1.setForeground(Color.white);
		lb1.setHorizontalAlignment(SwingConstants.RIGHT);
		lb1.setText("ID Pelicula: ");
		marco.add(lb1);
		
		txt = new JTextField("");
		actualizar(null);
		txt.setBounds(((x-(x-50))/2)+90, 25, 140, 30);
		txt.setFont(new Font("Arial", 4, 14));
		txt.setEditable(false);
		valID(txt);
		marco.add(txt);
		
		bt6 = new JButton();
		bt6.setBounds(((x-(x-50))/2)+80+160, 25, 100, 30);
		bt6.setFont(new Font("Arial", 1, 14));
		bt6.setText("Buscar");
		bt6.addActionListener(this);
		marco.add(bt6);		
		
		bt1 = new JButton();
		bt1.setBounds(x + ((x-l)/2), y, l, a);
		bt1.setFont(new Font("Arial", 1, 14));
		bt1.setText("Nuevo");
		bt1.addActionListener(this);
		marco.add(bt1);
		
		bt2 = new JButton();
		bt2.setBounds(x + ((x-l)/2), y + i, l, a);
		bt2.setFont(new Font("Arial", 1, 14));
		bt2.setText("Cambiar");
		bt2.addActionListener(this);
		marco.add(bt2);		
		
		bt4 = new JButton();
		bt4.setBounds(x + ((x-l)/2), y + i*2, l, a);
		bt4.setFont(new Font("Arial", 1, 14));
		bt4.setText("Ver Todo");
		bt4.addActionListener(this);
		marco.add(bt4);
		
		bt5 = new JButton();
		bt5.setBounds((x-(x-50))/2, 320, l, a);
		bt5.setFont(new Font("Arial", 1, 14));
		bt5.setText("Regresar");
		bt5.addActionListener(this);
		marco.add(bt5);
		
		ta1 = new JTextArea();
		ta1.setFont(new Font("Arial", 4, 18));
		ta1.setEditable(false);
		sc1 = new JScrollPane(ta1);
		sc1.setBounds((x-(x-50))/2, y, x, 220);
		marco.add(sc1);
		new verPeliculas();
	}
	
	static void actualizar(String a) {
		resultado = a;
		txt.setText(resultado);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == bt1){
			new addPeliculas();
		}
		if(a.getSource() == bt2){
			if(txt.getText().equals("")||ta1.getText().equals("")){
				 colordialogo();
				JOptionPane.showOptionDialog(null,"El error se debe a que:\n-No se ha ingresado el ID de la película.\n-No presionó el botón buscar.\n-La película que buscó no existe.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);			
			}else{
				new changePeliculas(txt.getText());
			}
		}
		if(a.getSource() == bt4){
			new verPeliculas();
		}
		if(a.getSource() == bt5){
			this.dispose();
		}
		if(a.getSource() == bt6){
			if(txt.getText().equals("")){
				colordialogo();
				JOptionPane.showOptionDialog(null,"Ingrese el ID a Buscar","¡Atención!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,null,null);
				ta1.setText("");
			} else{
				String busca = "select * from Peliculas where pel_id="+txt.getText();
				new buscar(busca, 1, ta1);
			}
		}
	}
}