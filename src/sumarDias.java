import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.*;

@SuppressWarnings("serial")
public class sumarDias extends Pantallas{
	
	int LimD, ID, A, D, M;
	
	public sumarDias(int dias, int id){
		ID = id;
		Calendar hoy = Calendar.getInstance();
		D = hoy.get(Calendar.DAY_OF_MONTH);
	    M = hoy.get(Calendar.MONTH);
	    A = hoy.get(Calendar.YEAR);
	    M = M + 1;
	    LimiteDias();
	    sumar(dias);
	}

	private void LimiteDias() {
		if(M == 1||M == 3||M == 5||M == 7||M == 8||M == 10||M == 12){
	    	 LimD = 31;
	     }
	     if(M == 4||M == 6||M == 9||M == 11){
	    	 LimD = 30;
	     }
	     if(M == 2){
	    	 if(A%4 == 0){
	    		 LimD = 29;
	    	 } else {
	    		 LimD = 28;
	    	 }
	     }
	}

	private void sumar(int dias) {
		D = D + dias;
		contar();
		modificar();
	}
	
	private void modificar() {
		trycatch();
		try {
			st = cn.createStatement();
			String updt = "Update Pren set pre_ent = '"+D+"/"+M+"/"+A+"' where pre_id="+ID;
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
			String updt = "Update Pren set pre_dis = 0 where pre_id="+ID;
			@SuppressWarnings("unused")
			int r = st.executeUpdate(updt);	
			cn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "ERROR: "+e1);
		}
	}

	private void contar() {
		int dif;
		if(D > LimD){
			dif = LimD - D;
			D = -dif;
			M = M + 1;
			if(M > 12){
				A = A + 1;
				M = 1;
			}
			LimiteDias();
			if(LimD < D){
				contar();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}