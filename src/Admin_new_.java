package ooad;

import java.awt.EventQueue;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Font;

public class Admin_new_ {

	private JFrame frame;
	private PlaneController planeController=new PlaneController();
	private TripController tripController=new TripController();
	private DBMgr sqlExe=null;
	private JTextField txt_plane_add_planeNO;
	private JTextField txt_plane_add_eSeat;
	private JTextField txt_plane_add_bSeat;
	private JTextField txt_plane_add_status;
	private JTextField txt_plane_update_planeNO;
	private JTextField txt_plane_update_status;
	private JTable tbl_plane;
	private JTextField txt_trip_add_tripID;
	private JTextField txt_trip_add_dTime;
	private JTextField txt_trip_add_aTime;
	private JTextField txt_trip_add_dAirport;
	private JTextField txt_trip_add_aAriport;
	private JTextField txt_trip_add_flyingHour;
	private JTextField txt_trip_add_status;
	private JTextField txt_trip_add_eSeats;
	private JTextField txt_trip_add_bSeat;
	private JTextField txt_trip_update_tripID;
	private JTextField txt_trip_update_dTime;
	private JTextField txt_trip_update_aTime;
	private JTextField txt_trip_update_dAirport;
	private JTextField txt_trip_update__aAirport;
	private JTextField txt_trip_update_flyingHours;
	private JTextField txt_trip_update_status;
	private JTextField txt_trip_update_eSeat;
	private JTextField txt_trip_update_bSeat;
	private JTable tbl_trip;
	private JTable tbl_users;
	private JTextField txt_trip_update_planeID;
	private JTextField txt_trip_add_planeID;

	/**
	 * Launch the application.
	 */
	public static void main(final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_new_ window = new Admin_new_(sqlExe);
					//window.
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
	public Admin_new_(DBMgr sqlExe) {
		this.sqlExe=sqlExe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 360);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{492, 0};
		gridBagLayout.rowHeights = new int[]{357, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel_home = new JPanel();
		tabbedPane.addTab("Home", null, panel_home, null);
		panel_home.setLayout(new MigLayout("", "[][][][][][][][145.00,grow][183.00]", "[][][][grow][grow]"));
		
		JButton btn_logout = new JButton("Log out");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel_home.add(btn_logout, "cell 8 1,alignx left");
		
		JLabel lblNewLabel_32 = new JLabel("Welcome");
		lblNewLabel_32.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		panel_home.add(lblNewLabel_32, "cell 7 3,alignx center");
		
		JPanel panel_plane = new JPanel();
		tabbedPane.addTab("Manage Plane", null, panel_plane, null);
		panel_plane.setLayout(new MigLayout("", "[][][][][][][][grow]", "[][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Add Plane");
		panel_plane.add(lblNewLabel, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("Plane_NO");
		panel_plane.add(lblNewLabel_1, "cell 0 1");
		
		txt_plane_add_planeNO = new JTextField();
		panel_plane.add(txt_plane_add_planeNO, "cell 1 1,growx");
		txt_plane_add_planeNO.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("eSeats");
		panel_plane.add(lblNewLabel_2, "cell 2 1,alignx trailing");
		
		txt_plane_add_eSeat = new JTextField();
		panel_plane.add(txt_plane_add_eSeat, "cell 3 1,growx");
		txt_plane_add_eSeat.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("bSeats");
		panel_plane.add(lblNewLabel_3, "cell 4 1,alignx trailing");
		
		txt_plane_add_bSeat = new JTextField();
		panel_plane.add(txt_plane_add_bSeat, "cell 5 1,growx");
		txt_plane_add_bSeat.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Status");
		panel_plane.add(lblNewLabel_4, "cell 6 1,alignx trailing");
		
		txt_plane_add_status = new JTextField();
		panel_plane.add(txt_plane_add_status, "cell 7 1,growx");
		txt_plane_add_status.setColumns(10);
		
		JButton btn_plane_add = new JButton("Add");
		btn_plane_add.addActionListener(planeController.addPlane(txt_plane_add_planeNO, txt_plane_add_eSeat, txt_plane_add_bSeat, txt_plane_add_status, sqlExe));
//		btn_plane_add.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String plane_no = txt_plane_add_planeNO.getText();
//				String eSeats = txt_plane_add_eSeat.getText();
//				String bSeats = txt_plane_add_bSeat.getText();
//				String status = txt_plane_add_status.getText();
//
//				String[] value = new String[] {"'"+plane_no+"'", eSeats, bSeats, status};
//				String[] attribute = new String[] {"flightNo", "eSeats", "bSeats", "status" };
//				
//				sqlExe.add("flight", attribute, value);
//				
//			}
//		});
		panel_plane.add(btn_plane_add, "cell 6 2");
		
		JLabel lblNewLabel_5 = new JLabel("Update Plane ");
		panel_plane.add(lblNewLabel_5, "cell 1 3");
		
		JLabel lblNewLabel_6 = new JLabel("Plane_NO");
		panel_plane.add(lblNewLabel_6, "cell 0 4,alignx trailing");
		
		txt_plane_update_planeNO = new JTextField();
		panel_plane.add(txt_plane_update_planeNO, "cell 1 4,growx");
		txt_plane_update_planeNO.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Status");
		panel_plane.add(lblNewLabel_7, "cell 2 4,alignx trailing");
		
		txt_plane_update_status = new JTextField();
		panel_plane.add(txt_plane_update_status, "cell 3 4 2 1,growx");
		txt_plane_update_status.setColumns(10);
		
		JButton btn_plane_retrieve = new JButton("Retrieve");
		btn_plane_retrieve.addActionListener(planeController.retrievePlane(txt_plane_update_planeNO, txt_plane_update_status, sqlExe));
//		btn_plane_retrieve.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String plane_no = txt_plane_update_planeNO.getText();
//				  if(!plane_no.equals("")){
//					  String conditions="flightNo=" + "'"+plane_no+"'";
//					  try {
//						ResultSet rSet=sqlExe.selectAll("flight", conditions);
//						int _status= -1;
//						if(rSet.next()){
//							_status=rSet.getInt(4);
//						}
//						if(_status == -1) {
//							JOptionPane.showMessageDialog(null, "No matched results!");
//							txt_plane_update_status.setText("");
//						}
//						if(_status != -1){
//							txt_plane_update_status.setText(Integer.toString(_status));
//						}
//						
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				  }
//				  else JOptionPane.showMessageDialog(null, "Please enter plane no!");
//			}
//		});
		panel_plane.add(btn_plane_retrieve, "cell 1 5");
		
		JButton btn_plane_update = new JButton("Update");
		btn_plane_update.addActionListener(planeController.updatePlane(txt_plane_update_planeNO, txt_plane_update_status, sqlExe));
//		btn_plane_update.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String[] attrs = {"flightNo", "status"};
//				String[] values = {"'"+txt_plane_update_planeNO.getText()+"'", txt_plane_update_status.getText()};
//				String con = "flightNo ="+ "'"+txt_plane_update_planeNO.getText()+"'";
//				sqlExe.update("flight", attrs, values, con);
//			}
//		});
		panel_plane.add(btn_plane_update, "cell 6 5");
		
		JLabel lblNewLabel_9 = new JLabel("View Plane Info");
		panel_plane.add(lblNewLabel_9, "cell 1 7");
		
		JButton btn_plane_display = new JButton("Display");
		JScrollPane scroll_plane = new JScrollPane();
		panel_plane.add(scroll_plane, "cell 0 8 8 1,grow");
		
		tbl_plane = new JTable();
		scroll_plane.setViewportView(tbl_plane);
		btn_plane_display.addActionListener(planeController.displayPlane(sqlExe, tbl_plane));
//		btn_plane_display.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					ResultSet rs=sqlExe.selectAll("flight", "1");
//					if(!rs.next()) JOptionPane.showMessageDialog(null, "ther is no planes info");
//					else{ rs.previous();
//					tbl_plane.setModel(DbUtils.resultSetToTableModel(rs)); }
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
		panel_plane.add(btn_plane_display, "cell 6 7");
		
	
		
		JPanel panel_trip = new JPanel();
		tabbedPane.addTab("Manage Trip", null, panel_trip, null);
		panel_trip.setLayout(new MigLayout("", "[grow][grow][][][grow][grow][][grow][grow][grow][][68.00,grow][grow][][grow]", "[][][][][][][][][][grow][grow]"));
		
		JLabel lblNewLabel_8 = new JLabel("Add Trip");
		panel_trip.add(lblNewLabel_8, "cell 1 1");
		
		JLabel lblNewLabel_10 = new JLabel("trip id");
		panel_trip.add(lblNewLabel_10, "cell 0 2,alignx trailing");
		
		txt_trip_add_tripID = new JTextField();
		panel_trip.add(txt_trip_add_tripID, "cell 1 2,growx");
		txt_trip_add_tripID.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("departTime");
		panel_trip.add(lblNewLabel_11, "cell 3 2,alignx trailing");
		
		txt_trip_add_dTime = new JTextField();
		panel_trip.add(txt_trip_add_dTime, "cell 4 2,growx");
		txt_trip_add_dTime.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("ArriveTime");
		panel_trip.add(lblNewLabel_12, "cell 6 2,alignx trailing");
		
		txt_trip_add_aTime = new JTextField();
		panel_trip.add(txt_trip_add_aTime, "cell 8 2 2 1,growx");
		txt_trip_add_aTime.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("departAirport");
		panel_trip.add(lblNewLabel_13, "cell 10 2,alignx trailing");
		
		txt_trip_add_dAirport = new JTextField();
		panel_trip.add(txt_trip_add_dAirport, "cell 11 2,growx");
		txt_trip_add_dAirport.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("arriveAirport");
		panel_trip.add(lblNewLabel_14, "cell 13 2,alignx trailing");
		
		txt_trip_add_aAriport = new JTextField();
		panel_trip.add(txt_trip_add_aAriport, "cell 14 2,growx");
		txt_trip_add_aAriport.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("flyingHours");
		panel_trip.add(lblNewLabel_15, "cell 0 3,alignx trailing");
		
		txt_trip_add_flyingHour = new JTextField();
		panel_trip.add(txt_trip_add_flyingHour, "cell 1 3,growx");
		txt_trip_add_flyingHour.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Status");
		panel_trip.add(lblNewLabel_16, "cell 3 3,alignx trailing");
		
		txt_trip_add_status = new JTextField();
		panel_trip.add(txt_trip_add_status, "cell 4 3,growx");
		txt_trip_add_status.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("available eSeats");
		panel_trip.add(lblNewLabel_17, "cell 6 3,alignx trailing");
		
		txt_trip_add_eSeats = new JTextField();
		panel_trip.add(txt_trip_add_eSeats, "cell 8 3 2 1,growx");
		txt_trip_add_eSeats.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("available bSeats");
		panel_trip.add(lblNewLabel_18, "cell 10 3,alignx trailing");
		
		txt_trip_add_bSeat = new JTextField();
		panel_trip.add(txt_trip_add_bSeat, "cell 11 3,growx");
		txt_trip_add_bSeat.setColumns(10);
		
		JLabel lblNewLabel_30 = new JLabel("planeID");
		panel_trip.add(lblNewLabel_30, "cell 13 3");
		
		txt_trip_add_planeID = new JTextField();
		txt_trip_add_planeID.setColumns(10);
		panel_trip.add(txt_trip_add_planeID, "cell 14 3,growx");
		
		
		JButton btn_trip_add = new JButton("Add");
		//System.out.println(txt_trip_update_planeID.getText());
		btn_trip_add.addActionListener(tripController.addTrip(txt_trip_add_planeID, txt_trip_add_eSeats, 
		txt_trip_add_bSeat, txt_trip_add_status, txt_trip_add_tripID, txt_trip_add_flyingHour,
		txt_trip_add_dTime, txt_trip_add_aTime, txt_trip_add_dAirport, txt_trip_add_aAriport, sqlExe));
//		btn_trip_add.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String tripID=txt_trip_add_tripID.getText();
//				String planeID=txt_trip_add_planeID.getText();
//				System.out.println(planeID);
//				String dTime=txt_trip_add_dTime.getText();
//				String aTime=txt_trip_add_aTime.getText();
//				String dPort=txt_trip_add_dAirport.getText();
//				String aPort=txt_trip_add_aAriport.getText();
//				String flyHour=txt_trip_add_flyingHour.getText();
//				String status=txt_trip_add_status.getText();
//				String eSeats=txt_trip_add_eSeats.getText();
//				String bSeats=txt_trip_add_bSeat.getText();
//				String[] value = new String[] {"'"+tripID+"'", "'"+planeID+"'", "'"+dTime+"'", "'"+aTime+"'", "'"+dPort+"'","'"+aPort+"'",flyHour,status
//						,eSeats,bSeats};
//				String[] attribute = new String[] {"tripID", "flightno","departTime", "arriveTime", "departAirport", "arriveAirport", "flyingHours", "status",
//						"availableEseats","availableBseats"};
//				
//				sqlExe.add("trip", attribute, value);
//				
//			}
//		});
//		
		panel_trip.add(btn_trip_add, "cell 13 4");
		
		JLabel lblNewLabel_19 = new JLabel("Update Trip");
		panel_trip.add(lblNewLabel_19, "cell 1 5");
		
		JLabel lblNewLabel_20 = new JLabel("trip id");
		panel_trip.add(lblNewLabel_20, "cell 0 6,alignx trailing");
		
		txt_trip_update_tripID = new JTextField();
		panel_trip.add(txt_trip_update_tripID, "cell 1 6,growx");
		txt_trip_update_tripID.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("departTime");
		panel_trip.add(lblNewLabel_21, "cell 3 6,alignx trailing");
		
		txt_trip_update_dTime = new JTextField();
		panel_trip.add(txt_trip_update_dTime, "cell 4 6 2 1,growx");
		txt_trip_update_dTime.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("Arrive Time");
		panel_trip.add(lblNewLabel_22, "cell 6 6");
		
		txt_trip_update_aTime = new JTextField();
		panel_trip.add(txt_trip_update_aTime, "cell 8 6 2 1,growx");
		txt_trip_update_aTime.setColumns(10);
		
		JLabel lblNewLabel_23 = new JLabel("departAirport");
		panel_trip.add(lblNewLabel_23, "cell 10 6,alignx trailing");
		
		txt_trip_update_dAirport = new JTextField();
		panel_trip.add(txt_trip_update_dAirport, "cell 11 6,growx");
		txt_trip_update_dAirport.setColumns(10);
		
		JLabel lblNewLabel_24 = new JLabel("arriveAirport");
		panel_trip.add(lblNewLabel_24, "cell 13 6,alignx trailing");
		
		txt_trip_update__aAirport = new JTextField();
		panel_trip.add(txt_trip_update__aAirport, "cell 14 6,growx");
		txt_trip_update__aAirport.setColumns(10);
		
		JLabel lblNewLabel_25 = new JLabel("flyingHours");
		panel_trip.add(lblNewLabel_25, "cell 0 7,alignx trailing");
		
		txt_trip_update_flyingHours = new JTextField();
		panel_trip.add(txt_trip_update_flyingHours, "cell 1 7,growx");
		txt_trip_update_flyingHours.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("Status");
		panel_trip.add(lblNewLabel_26, "cell 3 7");
		
		txt_trip_update_status = new JTextField();
		panel_trip.add(txt_trip_update_status, "cell 4 7 2 1,growx");
		txt_trip_update_status.setColumns(10);
		
		JLabel lblNewLabel_27 = new JLabel("availabe eSeats");
		panel_trip.add(lblNewLabel_27, "cell 6 7,alignx trailing");
		
		txt_trip_update_eSeat = new JTextField();
		panel_trip.add(txt_trip_update_eSeat, "cell 7 7,growx");
		txt_trip_update_eSeat.setColumns(10);
		
		JLabel lblNewLabel_28 = new JLabel("available bSeats");
		panel_trip.add(lblNewLabel_28, "cell 10 7");
		
		txt_trip_update_bSeat = new JTextField();
		panel_trip.add(txt_trip_update_bSeat, "cell 11 7 2 1,growx");
		txt_trip_update_bSeat.setColumns(10);
		
		JLabel lblNewLabel_31 = new JLabel("planeID");
		panel_trip.add(lblNewLabel_31, "cell 13 7");
		
		txt_trip_update_planeID = new JTextField();
		panel_trip.add(txt_trip_update_planeID, "cell 14 7,growx");
		txt_trip_update_planeID.setColumns(10);
		
		JButton btn_trip_update = new JButton("Update");
		btn_trip_update.addActionListener(tripController.updateTrip(txt_trip_update_planeID, txt_trip_update_eSeat, txt_trip_update_bSeat, txt_trip_update_status, txt_trip_update_tripID, txt_trip_update_flyingHours, txt_trip_update_dTime, txt_trip_update_aTime, txt_trip_update_dAirport, txt_trip_update__aAirport, sqlExe));
//		btn_trip_update.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String[] attrs = {"flightno","departTime", "arriveTime", "departAirport", "arriveAirport", "flyingHours", "Status","availableEseats",
//						"availableBseats"};
//			
//				String[] values = {"'"+txt_trip_update_planeID.getText()+"'", "'"+txt_trip_update_dTime.getText()+"'","'"+txt_trip_update_aTime.getText()+"'", 
//						"'"+txt_trip_update_dAirport.getText()+"'", "'"+txt_trip_update__aAirport.getText()+"'",  txt_trip_update_flyingHours.getText(), txt_trip_update_status.getText()
//						,txt_trip_update_eSeat.getText(),txt_trip_update_bSeat.getText()};
//				String con = "tripid="+txt_trip_update_tripID.getText();
//				sqlExe.update("trip", attrs, values, con); 
//				
//			}
//		});
		
		JButton btn_trip_update_retrieve = new JButton("Retrieve");
		btn_trip_update_retrieve.addActionListener(tripController.retrieveTrip(txt_trip_update_planeID, txt_trip_update_eSeat, 
			txt_trip_update_bSeat, txt_trip_update_status, txt_trip_update_tripID, txt_trip_update_flyingHours, txt_trip_update_dTime, txt_trip_update_aTime, txt_trip_update_dAirport, txt_trip_update__aAirport, sqlExe));
//		btn_trip_update_retrieve.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String tripID=txt_trip_update_tripID.getText();
//				  //String flight=flightID.getText();
//				  if(!tripID.equals("")){
//					  String conditions="tripID="+tripID;
//					  try {
//						ResultSet rSet=sqlExe.selectAll("trip", conditions);
//						String _flightNO="";
//						String _aTime="";
//						String _dTime="";
//						String _dPort="";
//						String _aPort="";
//					    double _hour=-1.0;
//					    int _status=-1;
//					    int _eSeat=-1;
//					    int _bSeat=-1;
//						if(rSet.next()){
//							_flightNO=rSet.getString(2);
//							_dTime=rSet.getString(3);
//							_aTime=rSet.getString(4);
//							_dPort=rSet.getString(5);
//							_aPort=rSet.getString(6);
//							_hour=rSet.getDouble(7);
//							_status=rSet.getInt(8);
//							_eSeat=rSet.getInt(9);
//							_bSeat=rSet.getInt(10);
//						}
//						if(_hour==-1.0) {
//							JOptionPane.showMessageDialog(null, "No matched results!");
//							txt_trip_update_planeID.setText("");
//							txt_trip_update_dTime.setText("");
//							txt_trip_update_aTime.setText("");
//							txt_trip_update_dAirport.setText("");
//							txt_trip_update__aAirport.setText("");
//							txt_trip_update_flyingHours.setText("");
//							txt_trip_update_status.setText("");
//							txt_trip_update_eSeat.setText("");
//							txt_trip_update_bSeat.setText("");
//							
//						}
//						if(_hour!=-1.0){
//							txt_trip_update_planeID.setText(_flightNO);
//							txt_trip_update_dTime.setText(_dTime);
//							txt_trip_update_aTime.setText(_aTime);
//							txt_trip_update_dAirport.setText(_dPort);
//							txt_trip_update__aAirport.setText(_aPort);
//							txt_trip_update_flyingHours.setText(Double.toString(_hour));
//							txt_trip_update_status.setText(Integer.toString(_status));
//							txt_trip_update_eSeat.setText(Integer.toString(_eSeat));
//							txt_trip_update_bSeat.setText(Integer.toString(_bSeat));
//						}
//						
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				  }
//				  else JOptionPane.showMessageDialog(null, "Please trip ID!");
//				}
//						
//		});
		panel_trip.add(btn_trip_update_retrieve, "cell 1 8,aligny bottom");
		panel_trip.add(btn_trip_update, "cell 13 8");
		
		JLabel lblNewLabel_29 = new JLabel("View Trips");
		panel_trip.add(lblNewLabel_29, "cell 0 9");
		
		JScrollPane scroll_trip = new JScrollPane();
		panel_trip.add(scroll_trip, "cell 0 10 14 1,grow");
		
		tbl_trip = new JTable();
		scroll_trip.setViewportView(tbl_trip);
		JButton btn_trip_display = new JButton("Display");
		btn_trip_display.addActionListener(tripController.displayTrip(sqlExe, tbl_trip));
//		btn_trip_display.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					ResultSet rs=sqlExe.selectAll("trip","1");
//					if(!rs.next())JOptionPane.showMessageDialog(null, "there is no trip");
//					else{ rs.previous();
//					tbl_trip.setModel(DbUtils.resultSetToTableModel(rs)); }
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
		panel_trip.add(btn_trip_display, "cell 13 9");
		
		
		
		JPanel panel_users = new JPanel();
		tabbedPane.addTab("Show Users", null, panel_users, null);
		panel_users.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][]", "[][][grow]"));
		
		JButton btn_users_display = new JButton("Display");
		btn_users_display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs=sqlExe.selectAll("user", "1");
					if(!rs.next())JOptionPane.showMessageDialog(null, "there is no registered user");
					else{ rs.previous();
					tbl_users.setModel(DbUtils.resultSetToTableModel(rs)); }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_users.add(btn_users_display, "cell 12 1");
		
		JScrollPane scroll_users = new JScrollPane();
		panel_users.add(scroll_users, "cell 0 2 13 1,grow");
		
		tbl_users = new JTable();
		scroll_users.setViewportView(tbl_users);
	}

}

