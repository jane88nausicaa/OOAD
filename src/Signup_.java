package ooad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Signup_ {

	private JFrame frame;
	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField emailField;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JTextField phoneField;
	private JButton btnSubmit;
	private JLabel lblPassportNumber;
	private JTextField passportField;
	private JComboBox yearCombo;
	private JComboBox monthCombo;
	private JComboBox dayCombo;
	private String year=null;
	private String month=null;
	private String day=null;
	private HashMap<String, String> convertedMonth=new HashMap<String,String>();
	private DBMgr sqlExe=null;

	/**
	 * Launch the application.
	 */
	public static void main(final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup_ window = new Signup_(sqlExe);
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
	public Signup_(DBMgr sqlExe) {
		this.sqlExe=sqlExe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][149.00][167.00]", "[][][8.00][][8][][8][][8][][8][][8][][8][]"));
		
		JLabel lblFirstName = new JLabel("First Name");
		frame.getContentPane().add(lblFirstName, "cell 1 1,alignx center");
		
		fnameField = new JTextField();
		frame.getContentPane().add(fnameField, "cell 2 1,growx");
		fnameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		frame.getContentPane().add(lblLastName, "cell 1 3,alignx center");
		
		lnameField = new JTextField();
		frame.getContentPane().add(lnameField, "cell 2 3,growx");
		lnameField.setColumns(10);
		
		JLabel lblYourEmail = new JLabel("Your e-mail");
		frame.getContentPane().add(lblYourEmail, "cell 1 5,alignx center");
		
		emailField = new JTextField();
		frame.getContentPane().add(emailField, "cell 2 5,growx");
		emailField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword, "cell 1 7,alignx center");
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField, "flowx,cell 2 7,growx");
		
		JLabel lblDateOf = new JLabel("Date of Birth");
		frame.getContentPane().add(lblDateOf, "cell 1 9,alignx center");
		////////////////////////////////////////////////added/////////////////////
		DateFormatSymbols symbols=new DateFormatSymbols(Locale.ENGLISH);
		String[] years=new String[118];
		String[] days=new String[31];
		for(int i=2017,j=0;i>=1900;i--){years[j++]=Integer.toString(i);}
		for(int i=0;i<=30;i++){ if(i<9) days[i]="0"+Integer.toString(i+1); else days[i]=Integer.toString(i+1);}
	    String[] months=symbols.getShortMonths();
	    
	    convertedMonth.put("Jan", "01");
	    convertedMonth.put("Feb", "02");
	    convertedMonth.put("Mar", "03");
	    convertedMonth.put("Apr", "04");
	    convertedMonth.put("May", "05");
	    convertedMonth.put("Jun", "06");
	    convertedMonth.put("Jul", "07");
	    convertedMonth.put("Aug", "08");
	    convertedMonth.put("Sep", "09");
	    convertedMonth.put("Oct", "10");
	    convertedMonth.put("Nov", "11");
	    convertedMonth.put("Dec", "12");
	  
		////////////////////////////////////////////////////////////////added/////////////////
	     
	    /////////////////////////////////////////////////////////////////////////////
		
		yearCombo = new JComboBox(years);
		
		yearCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        year = (String)cb.getSelectedItem();
			}
		});
		yearCombo.setBackground(Color.WHITE);
		frame.getContentPane().add(yearCombo, "flowx,cell 2 9,growx");
		
		lblNewLabel = new JLabel("Phone");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, "cell 1 11,alignx center");
		
		phoneField = new JTextField();
		frame.getContentPane().add(phoneField, "cell 2 11,growx");
		phoneField.setColumns(10);
		
		lblPassportNumber = new JLabel("Passport Number ");
		frame.getContentPane().add(lblPassportNumber, "cell 1 13,alignx center");
		
		passportField = new JTextField();
		frame.getContentPane().add(passportField, "cell 2 13,growx");
		passportField.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname=fnameField.getText();
				String lname=lnameField.getText();
				String email=emailField.getText();
				String password=passwordField.getText();
				String phone=phoneField.getText();
				String passport=passportField.getText();
				String birthday=year+"-"+convertedMonth.get(month)+"-"+day;
	            String conditions="email='"+email+"'";
	          if(fname.equals("")||lname.equals("")||email.equals("")||password.equals("")||phone.equals("")||passport.equals("")||birthday.equals(""))
	        	  JOptionPane.showMessageDialog(null, "The registration information is required!");
	          
	          else
	          {
	            try {
					ResultSet rs=sqlExe.selectAll("user", conditions);
					if(rs.next())
						JOptionPane.showMessageDialog(null, "This email address has already been registered!");
					  // whether or not exist
					else{
						String[] attributes={"PassportID", "Firstname","LastName","DOB","Phone","Email",
								"Password"};
						String[] values={"'"+passport+"'", "'"+fname+"'","'"+lname+"'","'"+birthday+"'",phone,"'"+email+"'","'"+password+"'"}; 
						sqlExe.add("user", attributes, values);
						JOptionPane.showMessageDialog(null, "You've registered successfully!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	          }
				
			}
		});
		
		frame.getContentPane().add(btnSubmit, "cell 2 15,alignx left");
		
		monthCombo = new JComboBox(months);
		monthCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        month = (String)cb.getSelectedItem();
			}
		});
		frame.getContentPane().add(monthCombo, "cell 2 9,growx");
		
		dayCombo = new JComboBox(days);
		dayCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        day = (String)cb.getSelectedItem();
			}
		});
		frame.getContentPane().add(dayCombo, "cell 2 9,growx");
	}
}
