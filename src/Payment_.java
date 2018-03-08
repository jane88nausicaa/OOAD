package ooad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Payment_ {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private DBMgr sqlExe;
	//private Integer bookingRef;
	private String tripID;
	private Integer userID;
	private ArrayList<String> passengerInfo;
	private Double price;
	private String seatType;
	private JFrame portalFrame;
	private TicketController ticketController=new TicketController();

	/**
	 * Launch the application.
	 */
	public static void main(final JFrame portalFrame, final Double price,final String tripID,final int userID,final ArrayList<String>passengerInfo,final String seatType,final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_ window = new Payment_(portalFrame, price,tripID,userID,passengerInfo,seatType,sqlExe);
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
	public Payment_(JFrame portalFrame, Double price,String tripID,int userID, ArrayList<String>passengerInfo,String seatType, DBMgr sqlExe) {
		this.portalFrame=portalFrame;
		this.price=price;
		this.tripID=tripID;
		this.userID=userID;
		this.passengerInfo=passengerInfo;
		this.seatType=seatType;
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPayment = new JLabel("PAYMENT");
		GridBagConstraints gbc_lblPayment = new GridBagConstraints();
		gbc_lblPayment.gridwidth = 3;
		gbc_lblPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lblPayment.gridx = 2;
		gbc_lblPayment.gridy = 1;
		frame.getContentPane().add(lblPayment, gbc_lblPayment);
		
		JLabel lblTotalPrice = new JLabel("Total Price");
		GridBagConstraints gbc_lblTotalPrice = new GridBagConstraints();
		gbc_lblTotalPrice.anchor = GridBagConstraints.EAST;
		gbc_lblTotalPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalPrice.gridx = 1;
		gbc_lblTotalPrice.gridy = 2;
		frame.getContentPane().add(lblTotalPrice, gbc_lblTotalPrice);
		
		JLabel lbl_total = new JLabel(Double.toString(price));
		GridBagConstraints gbc_lbl_total = new GridBagConstraints();
		gbc_lbl_total.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_total.gridx = 2;
		gbc_lbl_total.gridy = 2;
		frame.getContentPane().add(lbl_total, gbc_lbl_total);
		
		JLabel lblName = new JLabel("Name on Card");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		frame.getContentPane().add(lblName, gbc_lblName);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 2;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 3;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
		gbc_lblCardNumber.anchor = GridBagConstraints.EAST;
		gbc_lblCardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardNumber.gridx = 1;
		gbc_lblCardNumber.gridy = 4;
		frame.getContentPane().add(lblCardNumber, gbc_lblCardNumber);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		GridBagConstraints gbc_lblCvv = new GridBagConstraints();
		gbc_lblCvv.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvv.anchor = GridBagConstraints.EAST;
		gbc_lblCvv.gridx = 4;
		gbc_lblCvv.gridy = 4;
		frame.getContentPane().add(lblCvv, gbc_lblCvv);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 4;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblExpiry = new JLabel("Exp Date");
		GridBagConstraints gbc_lblExpiry = new GridBagConstraints();
		gbc_lblExpiry.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblExpiry.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpiry.gridx = 1;
		gbc_lblExpiry.gridy = 5;
		frame.getContentPane().add(lblExpiry, gbc_lblExpiry);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblBillingZip = new JLabel("Billing Zip");
		GridBagConstraints gbc_lblBillingZip = new GridBagConstraints();
		gbc_lblBillingZip.anchor = GridBagConstraints.EAST;
		gbc_lblBillingZip.insets = new Insets(0, 0, 5, 5);
		gbc_lblBillingZip.gridx = 1;
		gbc_lblBillingZip.gridy = 6;
		frame.getContentPane().add(lblBillingZip, gbc_lblBillingZip);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 6;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JButton btnPay = new JButton("Pay");
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.anchor = GridBagConstraints.BELOW_BASELINE;
		gbc_btnPay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPay.gridx = 4;
		gbc_btnPay.gridy = 7;
		frame.getContentPane().add(btnPay, gbc_btnPay);
		
		btnPay.addActionListener(ticketController.addPassenger(frame, portalFrame, passengerInfo, tripID, seatType, userID, sqlExe));
		/*
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					for(int i=0;i<passengerInfo.size();i+=6){
					String[] attrs={"tripID","uid","ticketStatus","seatType"};
					String[] values={tripID,Integer.toString(userID),"1","'"+seatType+"'"}; // ticket status=1
					sqlExe.addWithoutMessage("ticket", attrs, values); //add new ticket items
					
					String[] desiredContent={"max(bookingRefID)"};
					ResultSet rs=sqlExe.select(desiredContent, "ticket", "1");
					Integer bookingRef=-1;
					if(rs.next()) bookingRef=rs.getInt(1);
					rs.close();
					String[] attrs1={"bookingRefID","firstName","lastName","passportID","dob","phone","email"};
					String[] values1={Integer.toString(bookingRef),"'"+passengerInfo.get(i)+"'","'"+passengerInfo.get(i+1)+"'","'"+passengerInfo.get(i+2)+"'","'"+passengerInfo.get(i+3)+"'"
							,"'"+passengerInfo.get(i+4)+"'","'"+passengerInfo.get(i+5)+"'"};
					//sqlExe.add("passenger", attrs1, values1);
					int k=sqlExe.addWithoutMessage("passenger", attrs1, values1); ///watch out
					
					String k1= (seatType.equals("e-Seat"))? "availableEseats":"availableBseats";
					String[] attrs2={k1};
					String[] values2={k1+"-1"};
					sqlExe.updateWithoutMessage("trip",attrs2, values2, "tripID="+tripID);  // decrease available tickets
					
					//JOptionPane.showMessageDialog(null, "successful payment");
					
					//frame.dispose();
					//frame_pass.dispose();
					//frame_rsv.dispose();
					}
					JOptionPane.showMessageDialog(null, "successful payment");
					frame.dispose();
					portalFrame.setVisible(true);
					}
					catch(SQLException e1){
						e1.printStackTrace();
					}
				}
		});
		*/
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				portalFrame.setVisible(true);
			}
		});
		GridBagConstraints gbc_btn_cancel = new GridBagConstraints();
		gbc_btn_cancel.insets = new Insets(0, 0, 0, 5);
		gbc_btn_cancel.gridx = 2;
		gbc_btn_cancel.gridy = 7;
		frame.getContentPane().add(btn_cancel, gbc_btn_cancel);
		
		
		
	}

}


