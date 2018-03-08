package ooad;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Reservation_ {

	private JFrame frame;
	private DBMgr sqlExe=null;
	private String tripID2;
	private String flightNo2 = "";
	private String  departTime2= "";
	private String  arriveTime2= "";
	private String  departAirport2= "";
	private String  arriveAirport2= "";
	private String  flyingHours2;
	private String  pricePerTicket2;
	private String  NumOfTickets2;
	private String seatType;
	private double totalPrice2 = 0;
	private String userAccount;
	private JFrame fatherFrame;

	/**
	 * Launch the application.
	 */
	public static void main(final String userAccount,final JFrame fatherFrame, final String tripID2, final String flightNo2, final String departTime2, 
			final String arriveTime2, final String departAirport2, final String arriveAirport2,
			final String flyingHours2, final String pricePerTicket2, final String NumOfTickets2, final String seatType,
			final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation_ window = new Reservation_(userAccount, fatherFrame,
							tripID2, flightNo2, departTime2, arriveTime2, departAirport2, arriveAirport2,
							flyingHours2, pricePerTicket2, NumOfTickets2, seatType, sqlExe);
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
	public Reservation_(String userAccount,JFrame fatherFrame, String tripID2, String flightNo2, String departTime2, 
		 String arriveTime2, String departAirport2, String arriveAirport2,
		 String flyingHours2, String pricePerTicket2, String NumOfTickets2, String seatType,
		 final DBMgr sqlExe) {
		this.userAccount=userAccount;
		this.fatherFrame=fatherFrame;
		this.tripID2 = tripID2;
		this.flightNo2 = flightNo2;
		this.departTime2 = departTime2;
		this.arriveTime2 = arriveTime2;
		this.departAirport2 = departAirport2;
		this.arriveAirport2 = arriveAirport2;
		this.flyingHours2 = flyingHours2;
		this.pricePerTicket2 = pricePerTicket2;
		
		this.NumOfTickets2 = NumOfTickets2;
		this.seatType=seatType;
		this.sqlExe=sqlExe;
		System.out.println(userAccount);
		initialize();
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
//		this.tripID2 = tripID2;
//		this.flightNo2 = flightNo2;
//		this.departTime2 = departTime2;
//		this.arriveTime2 = arriveTime2;
//		this.departAirport2 = departAirport2;
//		this.arriveAirport2 = arriveAirport2;
//		this.flyingHours2 = flyingHours2;
//		this.pricePerTicket2 = pricePerTicket2;
//		this.NumOfTickets2 = NumOfTickets2;
		
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][][][]", "[][][][][][][][][][][][][][]"));
		
		JLabel lblMakeReservation = new JLabel("Make Reservation");
		frame.getContentPane().add(lblMakeReservation, "cell 3 0");
		
		JLabel tripID = new JLabel("Trip ID");
		frame.getContentPane().add(tripID, "cell 2 2");
		
		JLabel tripID1 = new JLabel(tripID2);
		frame.getContentPane().add(tripID1, "cell 3 2");
		
		JLabel flightNo = new JLabel("Flight Number");
		frame.getContentPane().add(flightNo, "cell 2 3");
		
		JLabel flightNo1 = new JLabel(flightNo2);
		frame.getContentPane().add(flightNo1, "cell 3 3");
		
		JLabel departDate = new JLabel("Depart Date");
		frame.getContentPane().add(departDate, "cell 2 4");
		
		JLabel departDate1 = new JLabel(departTime2);
		frame.getContentPane().add(departDate1, "cell 3 4");
		
		JLabel arriveDate = new JLabel("Arrive Date");
		frame.getContentPane().add(arriveDate, "cell 2 5");
		
		JLabel arriveDate1 = new JLabel(arriveTime2);
		frame.getContentPane().add(arriveDate1, "cell 3 5");
		
		JLabel departAirport = new JLabel("Depart Airport");
		frame.getContentPane().add(departAirport, "cell 2 6");
		
		JLabel departAirport1 = new JLabel(departAirport2);
		frame.getContentPane().add(departAirport1, "cell 3 6");
		
		JLabel arriveAirport = new JLabel("Arrive Airport");
		frame.getContentPane().add(arriveAirport, "cell 2 7");
		
		JLabel arriveAirport1 = new JLabel(arriveAirport2);
		frame.getContentPane().add(arriveAirport1, "cell 3 7");
		
		JLabel flyingHours = new JLabel("Flying Hours");
		frame.getContentPane().add(flyingHours, "cell 2 8");
		
		JLabel flyingHours1 = new JLabel(flyingHours2);
		frame.getContentPane().add(flyingHours1, "cell 3 8");
		
		JLabel pricePerTicket = new JLabel("Price/ Ticket");
		frame.getContentPane().add(pricePerTicket, "cell 2 9");
		
		JLabel pricePerTicket1 = new JLabel(pricePerTicket2);
		frame.getContentPane().add(pricePerTicket1, "cell 3 9");
		
		JLabel ticketNo = new JLabel("Ticket Number");
		frame.getContentPane().add(ticketNo, "cell 2 10,alignx center,aligny top");
		
		JLabel ticketNo1 = new JLabel(NumOfTickets2);
		frame.getContentPane().add(ticketNo1, "cell 3 10");
		
		JLabel totalPrice = new JLabel("Total Price");
		frame.getContentPane().add(totalPrice, "cell 2 11,aligny top");
		
		//System.out.println("xxxxx"+pricePerTicket2);
		totalPrice2 = Double.parseDouble(NumOfTickets2) * Double.parseDouble(pricePerTicket2);
		JLabel totalPrice1 = new JLabel(Double.toString(totalPrice2));/////
		frame.getContentPane().add(totalPrice1, "cell 3 11");
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tripID=tripID2;
				String[] desiredContent={"uid"};
				String conditions="email='"+userAccount+"'";
				int userID=-1;
				//int bookingRef=-1;
					try {
						ResultSet rs=sqlExe.select(desiredContent, "user", conditions);
						if(rs.next())userID=rs.getInt(1);
						//String[] value = new String[] {tripID, Integer.toString(userID)};
						//String[] attrs = new String[] {"tripID", "uID"};
						//sqlExe.add("ticket", attrs, value);
						//String[] desiredContent1={"max(bookingRefID)"};  ////the newest bookingID
						//rs=sqlExe.select(desiredContent1, "ticket", "userID="+userID);
						
						//if(rs.next()){
					//		bookingRef=rs.getInt(1);
					//	}
						 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				
				Passengers_.main(fatherFrame, totalPrice2,tripID, userID,Integer.parseInt(NumOfTickets2), seatType, sqlExe);
				frame.dispose();
				
			}
		});
		
		JButton btn_back = new JButton("Cancel");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				fatherFrame.setVisible(true);
			}
		});
		frame.getContentPane().add(btn_back, "cell 2 13");
		frame.getContentPane().add(btnContinue, "cell 4 13");
	}

}
