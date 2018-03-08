package ooad;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivilegedActionException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.omg.CORBA.PRIVATE_MEMBER;

import net.proteanit.sql.DbUtils;

public class UserController {
	
	 public LoginListener login(JTextField usernameField,JPasswordField passwordField,DBMgr sqlExe,String userAccount){
		 return new LoginListener(usernameField, passwordField, sqlExe, userAccount);
	 }
	 
	 public SignUpListener addUser(DBMgr sqlExe){
		 return new SignUpListener(sqlExe);
	 }
	 public BrowseListener browseUser(JTextField from,JTextField to, JTextField fromDate,JComboBox combo, JTextField numOfTickets,DBMgr sqlExe,JTable table){
		 return new BrowseListener(from, to, fromDate, combo,numOfTickets,sqlExe, table);
	 }
	 
	class LoginListener implements ActionListener{
		 private JTextField usernameField;
		 private JPasswordField passwordField;
		 private DBMgr sqlExe;
		 private String userAccount;
		public LoginListener(JTextField usernameField,JPasswordField passwordField,DBMgr sqlExe,String userAccount) {
			this.passwordField=passwordField;
			this.sqlExe=sqlExe;
			this.userAccount=userAccount;
			this.usernameField=usernameField;
		}
		public void actionPerformed(ActionEvent e) {
			 String user=usernameField.getText();
			 String password=passwordField.getText();
			 if(user.equals("admin")&&password.equals("admin")){
				 Admin_new_.main(sqlExe);
			 }
			 else{
					String[] desiredContent={"Firstname","Lastname"};
					String table="user";
					String conditions="email='"+user+"' and password='"+password+"'";
					userAccount = user;
					try {
						ResultSet rs=sqlExe.select(desiredContent,table, conditions);
						if(!rs.next())
							JOptionPane.showMessageDialog(null, "Invalid username or password!");
						else {
							String fname=rs.getString(1);
							String lname=rs.getString(2);
							Portal_.main(userAccount, fname, lname, sqlExe);
							usernameField.setText("");
							passwordField.setText("");
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 }
			
		}
		
		
		
	}
	
	class SignUpListener implements ActionListener{
		private DBMgr sqlExe;
		public SignUpListener(DBMgr sqlExe) {
			this.sqlExe=sqlExe;
		}
	
		public void actionPerformed(ActionEvent e) {
			Signup_.main(sqlExe);
		}
		
	}
	
	class BrowseListener implements ActionListener{
		private JTextField FromField;
		private JTextField ToField;
		private JTextField FromDateField;
		private JTextField numOfTickets;
		private JComboBox comboBox;
		private DBMgr sqlExe;
		private JTable table;
		
		public BrowseListener(JTextField from,JTextField to, JTextField fromDate,JComboBox comboBox, JTextField numOfTickets, DBMgr sqlExe,JTable table){
			FromField=from; ToField=to; FromDateField=fromDate; this.sqlExe=sqlExe; this.table=table; this.comboBox=comboBox; this.numOfTickets=numOfTickets;
		}
		
		public void actionPerformed(ActionEvent e) {
			String from = FromField.getText();
			String to = ToField.getText();
			String fromDate = FromDateField.getText();
			String num=numOfTickets.getText();
			String seatType=comboBox.getSelectedItem().toString();
			String condition1;
			String condition3;
			String condition4;
			String condition5;
			String condition6="status=1";
			String conditions;
			
			if (fromDate.isEmpty()) {
				condition1 = "1";
			} else {
				condition1 = "departTime = " + "'" + fromDate + "'";
			}
			
			if (from.isEmpty()) {
				condition3 = "1";
			} else {
				condition3 = "departAirport = " + "'" + from + "'";
			}
			
			if (to.isEmpty()) {
				condition4 = "1";
			} else {
				condition4 = "arriveAirport = " + "'" + to + "'"; 
			}
			if (num.isEmpty()) {
				condition5 = "1";
			} else {
				if(seatType.equals("e-Seat")){condition5 = " availableESeats>= " + num;}
				else if (seatType.equals("b-Seat")){condition5 = " availableBSeats>= " + num;}
				else{condition5 = " availableESeats>= " + num+" and availableBSeats>="+num;}
				
			}
			
			conditions = condition1 + " and " + condition3 + " and " + condition4+" and "+condition5+ " and "+condition6;
			
			try {
				/*
				if (from.isEmpty()&& to.isEmpty()&& fromDate.isEmpty()) {
					String[] desiredContent = {"tripID", "flightNo", "departTime", "arriveTime",
							"departAirport", "arriveAirport", "flyingHours", "availableESeats", "availableBSeats"};
					String tableName = "trip"; 
					ResultSet rs=sqlExe.select(desiredContent,tableName, "status=1");
					table.setModel(DbUtils.resultSetToTableModel(rs)); 
				} 
				*/
				//else {
					String[] desiredContent = {"tripID", "flightNo", "departTime", "arriveTime",
							"departAirport", "arriveAirport", "flyingHours", "availableESeats", "availableBSeats"};
					String tableName = "trip"; 
					ResultSet rs=sqlExe.select(desiredContent,tableName, conditions);
					if(!rs.next()) { rs.previous(); JOptionPane.showMessageDialog(null, "no matched results"); table.setModel(DbUtils.resultSetToTableModel(rs));}
					else{ rs.previous();
					table.setModel(DbUtils.resultSetToTableModel(rs));}
				//}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	

}
