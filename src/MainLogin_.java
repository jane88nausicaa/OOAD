package ooad;

import java.awt.EventQueue;

import javax.swing.JFrame;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class MainLogin_ {

	private JFrame frame; 
	private JTextField usernameField;
	private JTextField FromField;
	private JTextField FromDateField;
	private JTextField ToField;
	private JTextField ToDateField;
	private JTable table;
	private JPasswordField passwordField;
	private DBMgr sqlExe=new DBMgr();
	private String userAccount;
	private UserController userController=new UserController();
	private JTextField ticketNumField;
	private String[] seatOption={"e-Seat","b-Seat"};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin_ window = new MainLogin_();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLogin_() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	
		frame.setBounds(100, 100, 670, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][grow][grow][grow][][76.00,grow][grow][grow]", "[][][][][][][34.00,center][123.00,center][128.00][170.00,center][175.00,grow][169.00,grow]"));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblLogin, "cell 1 1,alignx center");
		
		JLabel lblSeachFlights = new JLabel("Browse Trips");
		lblSeachFlights.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblSeachFlights, "cell 6 1 2 1,alignx center");
		
		JLabel lblUser = new JLabel("username");
		frame.getContentPane().add(lblUser, "cell 0 3,alignx right");
		
		usernameField = new JTextField();
		frame.getContentPane().add(usernameField, "cell 1 3,growx");
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("From");
		frame.getContentPane().add(lblNewLabel_4, "cell 5 3,alignx center");
		
		FromField = new JTextField();
		frame.getContentPane().add(FromField, "cell 6 3 2 1,growx");
		FromField.setColumns(10);
		
		JLabel lblPswd = new JLabel("password");
		frame.getContentPane().add(lblPswd, "cell 0 4,alignx trailing");
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField, "cell 1 4,growx");
		
		JLabel lblNewLabel_5 = new JLabel("To");
		frame.getContentPane().add(lblNewLabel_5, "cell 5 4,alignx center");
		
		ToField = new JTextField();
		frame.getContentPane().add(ToField, "cell 6 4 2 1,growx");
		ToField.setColumns(10);
		
		JButton btnSubmit = new JButton("log in");
		btnSubmit.addActionListener(userController.login(usernameField, passwordField, sqlExe, userAccount));
//		btnSubmit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			 String user=usernameField.getText();
//			 String password=passwordField.getText();
//			 if(user.equals("admin")&&password.equals("admin")){
//				 Admin_new.main(sqlExe);
//			 }
//			 else{
//					String[] desiredContent={"Firstname","Lastname"};
//					String table="user";
//					String conditions="email='"+user+"' and password='"+password+"'";
//					userAccount = user;
//					try {
//						ResultSet rs=sqlExe.select(desiredContent,table, conditions);
//						if(!rs.next())
//							JOptionPane.showMessageDialog(null, "Invalid username or password!");
//						else {
//							String fname=rs.getString(1);
//							String lname=rs.getString(2);
//							Portal.main(userAccount, fname, lname, sqlExe);
//							usernameField.setText("");
//							passwordField.setText("");
//							
//						}
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//			 }
//			}
//		});
		frame.getContentPane().add(btnSubmit, "cell 1 5,alignx center");
		
		JLabel lblNewLabel_6 = new JLabel("From Date");
		frame.getContentPane().add(lblNewLabel_6, "cell 5 5,alignx center");
		
		FromDateField = new JTextField();
		frame.getContentPane().add(FromDateField, "cell 6 5 2 1,growx");
		FromDateField.setColumns(10);
		
		JButton btnRegister = new JButton("sign up");
		btnRegister.addActionListener(userController.addUser(sqlExe));
		frame.getContentPane().add(btnRegister, "cell 1 5");

		
		
		
		table = new JTable();
		
		JLabel lblNumOfTickets = new JLabel("Num of Tickets");
		frame.getContentPane().add(lblNumOfTickets, "cell 5 6,alignx center");
		
		ticketNumField = new JTextField();
		frame.getContentPane().add(ticketNumField, "cell 6 6 2 1,growx");
		ticketNumField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Seat Type");
		frame.getContentPane().add(lblNewLabel, "cell 5 7,alignx center");
		
		JComboBox comboBox = new JComboBox(seatOption);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
			}
		});
		frame.getContentPane().add(comboBox, "cell 6 7 2 1,growx");
		
		JButton btnSearch = new JButton("Browse");
		frame.getContentPane().add(btnSearch, "cell 6 8,alignx right");
		btnSearch.addActionListener(userController.browseUser(FromField, ToField, FromDateField,comboBox,ticketNumField, sqlExe, table));
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 0 9 8 3,grow");
		scrollPane.setViewportView(table);
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String from = FromField.getText();
//				String to = ToField.getText();
//				String fromDate = FromDateField.getText();
//				String condition1;
//				String condition3;
//				String condition4; 
//				String conditions;
//				
//				if (fromDate.isEmpty()) {
//					condition1 = "1";
//				} else {
//					condition1 = "departTime = " + "'" + fromDate + "'";
//				}
//				
//				if (from.isEmpty()) {
//					condition3 = "1";
//				} else {
//					condition3 = "departAirport = " + "'" + from + "'";
//				}
//				
//				if (to.isEmpty()) {
//					condition4 = "1";
//				} else {
//					condition4 = "arriveAirport = " + "'" + to + "'"; 
//				}
//				
//				conditions = condition1 + " and " + condition3 + " and " + condition4;
//				
//				try {
//					if (from.isEmpty()&& to.isEmpty()&& fromDate.isEmpty()) {
//						String[] desiredContent = {"tripID", "flightNo", "departTime", "arriveTime",
//								"departAirport", "arriveAirport", "flyingHours", "availableESeats", "availableBSeats"};
//						String tableName = "trip"; 
//						ResultSet rs=sqlExe.select(desiredContent,tableName, "1");
//						table.setModel(DbUtils.resultSetToTableModel(rs)); 
//					} else {
//						String[] desiredContent = {"tripID", "flightNo", "departTime", "arriveTime",
//								"departAirport", "arriveAirport", "flyingHours", "availableESeats", "availableBSeats"};
//						String tableName = "trip"; 
//						ResultSet rs=sqlExe.select(desiredContent,tableName, conditions);
//						if(!rs.next()) JOptionPane.showMessageDialog(null, "no matched results");
//						else{ rs.previous();
//						table.setModel(DbUtils.resultSetToTableModel(rs));}
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
		
	}

}
