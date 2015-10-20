import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class eliminar extends Pantallas{

	public eliminar(String sql){
		trycatch();
		try {
			st = cn.createStatement();
			colordialogo();
			int op = JOptionPane.showOptionDialog(
				    this,
				    "¿Seguro que desea eliminar este registro?", 
				    "Eliminar",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,null, 0);
			if(op==0){
				@SuppressWarnings("unused")
				int r = st.executeUpdate(sql);
				cn.close();
				colordialogo();
				JOptionPane.showOptionDialog(null,"Registro eliminado correctamente","Eliminar",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}