package ooad;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseWheelEvent;
import java.awt.print.Printable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class Portal_ {
    private JFrame frame=new JFrame();
	private String fname="";
	private String lname="";
	private DBMgr sqlExe=null;
	private JTextField txt_search_dPort;
	private JTextField txt_search_aPort;
	private JTextField txt_search_dTime;
	private JTextField txt_search_number;
	private JButton btn_cancel;
	private String[] type={"e-Seat","b-Seat"};
	//private String ticketType;
	private JTable tbl_search;
	private String numOfTicket=null;
	private String userAccount;
	private String tripID;
	private String flightNo;
	private String dTime="";
	private String aTime;
	private String dPort;
	private String aPort;
	private String hours;
	private String price;
	
	private JTable tbl_booking;
	
	private String seatType=null; // 
	private JTable tbl_current;
	private JTable tbl_history;
//	private String ;
	private TicketController ticketController=new TicketController();
	private String bookingRefID;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(final String userAccount,final String fname, final String lname, final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portal_ window=new Portal_(userAccount,fname,lname,sqlExe);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getBookingRefID(){
		return this.bookingRefID;
	}
	
	public void setBookingRefID(String bookingRefID){
		this.bookingRefID=bookingRefID;
	}
	

	/**
	 * Create the frame.
	 */
	public Portal_(final String userAccount, String fname, String lname, final DBMgr sqlExe) {
		this.userAccount=userAccount;
		this.fname=fname;
		this.sqlExe=sqlExe;
		this.lname=lname;
		
	
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 494, 394);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JPanel panel_home = new JPanel();
		tabbedPane.addTab("Home", null, panel_home, null);
		panel_home.setLayout(new MigLayout("", "[][grow][][][][][][][][][][][grow]", "[][][][]"));
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_home.add(btnLogOut, "cell 12 0");
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(UIManager.getFont("MenuBar.font"));
		panel_home.add(lblNewLabel, "cell 4 1");
		
		JLabel lbl_firstname = new JLabel(this.fname);
		panel_home.add(lbl_firstname, "cell 3 3");
		
		JLabel lbl_lastname = new JLabel(this.lname);
		panel_home.add(lbl_lastname, "cell 5 3");
		
		JPanel panel_search = new JPanel();
		tabbedPane.addTab("Browse Trips", null, panel_search, null);
		panel_search.setLayout(new MigLayout("", "[grow][][][][grow]", "[][][][][][][][][grow][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Depart Airport");
		panel_search.add(lblNewLabel_1, "cell 0 1,alignx right");
		
		txt_search_dPort = new JTextField();
		panel_search.add(txt_search_dPort, "cell 1 1,growx");
		txt_search_dPort.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Arrive Airport");
		panel_search.add(lblNewLabel_2, "cell 0 2,alignx right");
		
		txt_search_aPort = new JTextField();
		panel_search.add(txt_search_aPort, "cell 1 2,growx");
		txt_search_aPort.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Depart Time");
		panel_search.add(lblNewLabel_3, "cell 0 3,alignx right");
		
		txt_search_dTime = new JTextField();
		panel_search.add(txt_search_dTime, "cell 1 3,growx");
		txt_search_dTime.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("NO of tickets");
		panel_search.add(lblNewLabel_4, "cell 0 4,alignx trailing");
		
		txt_search_number = new JTextField();
		panel_search.add(txt_search_number, "cell 1 4,growx");
		txt_search_number.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Seat Type");
		panel_search.add(lblNewLabel_5, "cell 0 5,alignx right");
		
		final JComboBox combo_type = new JComboBox(type);
		combo_type.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		       
			}
		});
		panel_search.add(combo_type, "cell 1 5,growx");
		
		JButton btn_browse = new JButton("Browse");
		tbl_search = new JTable();
		btn_browse.addActionListener(ticketController.browseTrip(txt_search_dPort, txt_search_aPort, txt_search_dTime, txt_search_number, combo_type, tbl_search,sqlExe));
//		btn_browse.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String dPort=txt_search_dPort.getText();
//				String aPort=txt_search_aPort.getText();
//				String dTime=txt_search_dTime.getText();
//				numOfTicket=txt_search_number.getText();
//				String type=combo_type.getSelectedItem().toString();
//				System.out.print(type);
//				
//				try {
//					if (dPort.isEmpty()||aPort.isEmpty()|| dTime.isEmpty()|| numOfTicket.isEmpty()||type.isEmpty()) {
//						JOptionPane.showMessageDialog(null, "All the field is required");
//					} else {
//						//String conditions="departTime="+"'"+dTime+"'"+" and departAirport="+"'"+dPort+"'"+" and arriveAirport="+
//							//	"'"+aPort+"'"+" and "+"status=1 and availableEseats>="+numOfTicket;
//						ResultSet rs=null;
//						if(type.equals("e-Seat")){
//							String conditions="departTime="+"'"+dTime+"'"+" and departAirport="+"'"+dPort+"'"+" and arriveAirport="+
//									"'"+aPort+"'"+" and "+"status=1 and availableEseats>="+numOfTicket;
//						String[] desiredContent={"tripID","flightNo","departTime","arriveTime","departAirport","arriveAirport",
//								"flyingHours","Eprice"}; 
//						rs=sqlExe.select(desiredContent,"trip", conditions);
//						}
//						
//						if(type.equals("b-Seat")){
//							String conditions="departTime="+"'"+dTime+"'"+" and departAirport="+"'"+dPort+"'"+" and arriveAirport="+
//									"'"+aPort+"'"+" and "+"status=1 and availableBseats>="+numOfTicket;
//							String[] desiredContent={"tripID","flightNo","departTime","arriveTime","departAirport","arriveAirport",
//									"flyingHours","Bprice"}; 
//							rs=sqlExe.select(desiredContent,"trip", conditions);
//							
//						}
//						if(!rs.next())JOptionPane.showMessageDialog(null, "there is no matched result");
//						else{ rs.previous();
//						tbl_search.setModel(DbUtils.resultSetToTableModel(rs)); }
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
		panel_search.add(btn_browse, "cell 4 6");
		
		JLabel lblNewLabel_6 = new JLabel("show available trips");
		panel_search.add(lblNewLabel_6, "cell 0 7");
		
		JScrollPane scroll_search = new JScrollPane();
		panel_search.add(scroll_search, "cell 0 8 5 1,grow");
		
		
		tbl_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.print("haha");
				int row=tbl_search.getSelectedRow();
	    		 tripID=tbl_search.getModel().getValueAt(row, 0).toString();
	    		 flightNo=tbl_search.getModel().getValueAt(row, 1).toString();
	    		dTime=tbl_search.getModel().getValueAt(row, 2).toString();
	    	    aTime=tbl_search.getModel().getValueAt(row, 3).toString();
	    		dPort=tbl_search.getModel().getValueAt(row, 4).toString();
	    		aPort=tbl_search.getModel().getValueAt(row, 5).toString();
	    		hours=tbl_search.getModel().getValueAt(row, 6).toString();
	    		price=tbl_search.getModel().getValueAt(row, 7).toString();  //condition needed
			}
		});
		scroll_search.setViewportView(tbl_search);
		
		JButton btn_submit = new JButton("Make a Reservation");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(tripID+flightNo+dTime+aTime+dPort+aPort+hours+price+numOfTicket);
				//Reservation_.main(userAccount,tripID,flightNo,dTime,aTime,dPort,aPort,hours,price,txt_search_number.getText(),sqlExe);
				if(dTime.equals("")){JOptionPane.showMessageDialog(null, "Please select one reservation ");}
				else{
					Reservation_.main(userAccount,frame,tripID,flightNo,dTime,aTime,dPort,aPort,hours,price,txt_search_number.getText(),combo_type.getSelectedItem().toString(),sqlExe);
					frame.hide(); 
					}
			}
		});
		panel_search.add(btn_submit, "cell 4 9");
		
		JPanel panel_myBooking = new JPanel();
		tabbedPane.addTab("Manage Booking", null, panel_myBooking, null);
		panel_myBooking.setLayout(new MigLayout("", "[grow][][grow][][][][][][][grow][][][]", "[][][53.00][106.00][138.00][130.00][130.00][][]"));
		
		JLabel lblNewLabel_7 = new JLabel("Current Booking Records");
		panel_myBooking.add(lblNewLabel_7, "cell 1 0,alignx center");
		
		
		//tbl_booking = new JTable();
		JButton btn_booking = new JButton("Display");
		tbl_current = new JTable();
		
	
		// table performance
		
		btn_booking.addActionListener(ticketController.browseBooking(sqlExe, tbl_current, userAccount,"1"));
		
		JLabel lblNewLabel_8 = new JLabel("Booking History");
		panel_myBooking.add(lblNewLabel_8, "cell 9 0,alignx center");
		panel_myBooking.add(btn_booking, "cell 1 1,alignx center");
		
		tbl_history = new JTable();
		
		JButton btn_history = new JButton("Display");
		btn_history.addActionListener(ticketController.browseBooking(sqlExe, tbl_history, userAccount,"0"));
		
		panel_myBooking.add(btn_history, "cell 9 1,alignx center");
		
		JScrollPane scroll_current = new JScrollPane();
		panel_myBooking.add(scroll_current, "cell 0 2 6 3,grow");
		
		scroll_current.setViewportView(tbl_current);
		
		
		tbl_current.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=tbl_current.getSelectedRow();
				
	    		setBookingRefID(tbl_current.getModel().getValueAt(row, 0).toString());
	    		System.out.println(bookingRefID);
	    		 
	    		/*
	    		 flightNo=tbl_search.getModel().getValueAt(row, 1).toString();
	    		dTime=tbl_search.getModel().getValueAt(row, 2).toString();
	    	    aTime=tbl_search.getModel().getValueAt(row, 3).toString();
	    		dPort=tbl_search.getModel().getValueAt(row, 4).toString();
	    		aPort=tbl_search.getModel().getValueAt(row, 5).toString();
	    		hours=tbl_search.getModel().getValueAt(row, 6).toString();
	    		price=tbl_search.getModel().getValueAt(row, 7).toString();  //condition needed
	    		*/
			}
		});
		
		
		
		JScrollPane scroll_history = new JScrollPane();
		panel_myBooking.add(scroll_history, "cell 7 2 6 3,grow");
		
		scroll_history.setViewportView(tbl_history);
		
		btn_cancel = new JButton("Cancel Reservation");
		
		
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bookingRefID==null) JOptionPane.showMessageDialog(null, "Please select one reservation to cancel ");
				else
				{
				String[] desiredContent={"tripID","seatType","ticketStatus"};
				String seatType = null;
				int tripID = -1;
				int status=-1;
				
				try {
				ResultSet rs=sqlExe.select(desiredContent, "ticket", "bookingRefID="+bookingRefID);
				if(rs.next()){
					tripID=rs.getInt(1);
					seatType=rs.getString(2);
					status=rs.getInt(3);   // in case of duplicated cancellation
				}  
				
				if(status==1){
				String[] attrs={"ticketStatus"};
				String[] values={"0"};
				sqlExe.updateWithoutMessage("ticket", attrs, values, "bookingRefID="+bookingRefID); // when cancelling a booking ticket, update the status
				
				
				String k1= (seatType.equals("e-Seat"))? "availableEseats":"availableBseats";
				String[] attrs2={k1};
				String[] values2={k1+"+1"};
				sqlExe.updateWithoutMessage("trip",attrs2, values2, "tripID="+tripID); // increase the ticket cancelled
				
				//////////////////////////////// refresh current table/////////////////////////
				Integer uID=null;
				String[] desiredContent1={"uid"};
				rs=sqlExe.select(desiredContent1, "user", "email="+"'"+userAccount+"'");
				if(rs.next()) uID=rs.getInt(1);
				String[] desiredContent2={"bookingRefID","firstname","lastname","departTime","seatType"};
				String conditions="userid="+uID+" and bookingStatus=1";
				rs=sqlExe.select(desiredContent2, "reservation_results", conditions);
				if(!rs.next()){ tbl_current.setModel(DbUtils.resultSetToTableModel(rs));
								JOptionPane.showMessageDialog(null, "there is no booking result");}
				else{ rs.previous();
				tbl_current.setModel(DbUtils.resultSetToTableModel(rs));}
				
			}
				else{
				JOptionPane.showMessageDialog(null, "You've already cancelled this reservation");	
				 }
				
				}
				
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			}
		});
		
	//	btn_cancel.addActionListener((ticketController.getCancelBooking(sqlExe, tbl_current, userAccount, getBookingRefID())));
		//cancel perf
		
		panel_myBooking.add(btn_cancel, "cell 1 5 1 2");
		
		
//		btn_booking.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try{
//				Integer uID=null;
//				String[] desiredContent={"uid"};
//				ResultSet rs=sqlExe.select(desiredContent, "user", "email="+"'"+userAccount+"'");
//				if(rs.next()) uID=rs.getInt(1);
//				String[] desiredContent1={"bookingRefID","firstname","lastname","departTime"};
//				String conditions="userid="+uID;
//				rs=sqlExe.select(desiredContent1, "reservation_results", conditions);
//				if(!rs.next())JOptionPane.showMessageDialog(null, "there is no booking result");
//				else{ rs.previous();
//				tbl_booking.setModel(DbUtils.resultSetToTableModel(rs));}
//				}
//				catch(SQLException e1){
//					e1.printStackTrace();
//				}
//			}
//		});
		
	}

}
