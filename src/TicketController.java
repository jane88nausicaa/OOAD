package ooad;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Printable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class TicketController {
	
	public BrowseTripListener browseTrip(JTextField dPort,JTextField aPort,JTextField dTime, JTextField number,JComboBox combo,
			JTable tbl_search,DBMgr sqlExe){
		return new BrowseTripListener(dPort, aPort, dTime,  number, combo, tbl_search,sqlExe);
	}
	
	public BrowseBookingListener browseBooking(DBMgr sqlExe, JTable tbl_booking,String userAccount,String isCurrent){
		return new BrowseBookingListener(sqlExe, tbl_booking, userAccount, isCurrent);
	}
	
	public CancelBookingListener cancelBooking(DBMgr sqlExe, JTable tbl_current,String userAccount,String bookingRefID){
		
		
		return new CancelBookingListener(sqlExe, tbl_current, userAccount, bookingRefID);
	}
	
	public addPassengerListener addPassenger(JFrame frame,JFrame portalFrame,ArrayList<String> passengerInfo,String tripID,String seatType,Integer userID,DBMgr sqlExe){
		return new addPassengerListener(frame, portalFrame, passengerInfo, tripID, seatType, userID, sqlExe);
	}
	
	
	class BrowseTripListener implements ActionListener{
		private JTextField dPort,aPort,dTime,number;
		private DBMgr sqlExe;
		private JComboBox combo_type;
		private JTable tbl_search;
		public BrowseTripListener(JTextField dPort,JTextField aPort,JTextField dTime, JTextField number,JComboBox combo,
				JTable tbl_search, DBMgr sqlExe) {
			this.aPort=aPort;this.dPort=dPort;this.dTime=dTime;this.number=number; combo_type=combo; this.tbl_search=tbl_search;
			this.sqlExe=sqlExe;
		}
			
		@Override
		public void actionPerformed(ActionEvent e) {
			String dPort1=dPort.getText();
			String aPort1=aPort.getText();
			String dTime1=dTime.getText();
			//numOfTicket=number.getText();
			String type=combo_type.getSelectedItem().toString();
			System.out.print(type);
			
			try {
				if (dPort1.isEmpty()||aPort1.isEmpty()|| dTime1.isEmpty()|| number.getText().isEmpty()||type.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All the field is required");
				} else {
					//String conditions="departTime="+"'"+dTime+"'"+" and departAirport="+"'"+dPort+"'"+" and arriveAirport="+
						//	"'"+aPort+"'"+" and "+"status=1 and availableEseats>="+numOfTicket;
					ResultSet rs=null;
					if(type.equals("e-Seat")){
						String conditions="departTime="+"'"+dTime1+"'"+" and departAirport="+"'"+dPort1+"'"+" and arriveAirport="+
								"'"+aPort1+"'"+" and "+"status=1 and availableEseats>="+number.getText();
					String[] desiredContent={"tripID","flightNo","departTime","arriveTime","departAirport","arriveAirport",
							"flyingHours","Eprice"}; 
					rs=sqlExe.select(desiredContent,"trip", conditions);
					}
					
					if(type.equals("b-Seat")){
						String conditions="departTime="+"'"+dTime1+"'"+" and departAirport="+"'"+dPort1+"'"+" and arriveAirport="+
								"'"+aPort1+"'"+" and "+"status=1 and availableBseats>="+number.getText();
						String[] desiredContent={"tripID","flightNo","departTime","arriveTime","departAirport","arriveAirport",
								"flyingHours","Bprice"}; 
						rs=sqlExe.select(desiredContent,"trip", conditions);
						
					}
					if(!rs.next()){ JOptionPane.showMessageDialog(null, "there is no matched result"); tbl_search.setModel(DbUtils.resultSetToTableModel(rs));}
					else{ rs.previous();
					tbl_search.setModel(DbUtils.resultSetToTableModel(rs)); }
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			
		}
	
	class BrowseBookingListener implements ActionListener{
		private DBMgr sqlExe;
		private JTable tbl_booking;
		private String userAccount;
		private String isCurrent;
		public BrowseBookingListener(DBMgr sqlExe, JTable tbl_booking,String userAccount, String isCurrent) {
			this.sqlExe=sqlExe; this.tbl_booking=tbl_booking; this.userAccount=userAccount;
			this.isCurrent=isCurrent;
		}
		
		public void actionPerformed(ActionEvent e) {
			try{
				Integer uID=null;
				String[] desiredContent={"uid"};
				ResultSet rs=sqlExe.select(desiredContent, "user", "email="+"'"+userAccount+"'");
				if(rs.next()) uID=rs.getInt(1);
				String[] desiredContent1={"bookingRefID","firstname","lastname","departTime","seatType"};
				String conditions="userID="+uID+" and bookingStatus="+isCurrent;
				rs=sqlExe.select(desiredContent1, "reservation_results", conditions);
				if(!rs.next())JOptionPane.showMessageDialog(null, "there is no booking result");
				else{ rs.previous(); rs.previous();
				tbl_booking.setModel(DbUtils.resultSetToTableModel(rs));}
				
				/*
				Integer uID=null;
				String[] desiredContent={"uid"};
				ResultSet rs=sqlExe.select(desiredContent, "user", "email="+"'"+userAccount+"'");
				if(rs.next()) uID=rs.getInt(1);
				String[] desiredContent1={"bookingRefID","firstname","lastname","departTime","seatType"};
				String conditions="userid="+uID+" and bookingStatus=1";
				rs=sqlExe.select(desiredContent1, "reservation_results", conditions);
				tbl_current.setModel(DbUtils.resultSetToTableModel(rs)); */
				}
				
				catch(SQLException e1){
					e1.printStackTrace();
				}
			
		}
		
	}
	
	class CancelBookingListener implements ActionListener{
		private DBMgr sqlExe; private JTable tbl_current; private String userAccount; private String bookingRefID;
		public CancelBookingListener(DBMgr sqlExe,JTable tbl_current,String userAccount, String bookingRefID){
			
			this.sqlExe=sqlExe; this.tbl_current=tbl_current; this.userAccount=userAccount; this.bookingRefID=bookingRefID;
			
			
		}
		
		public void actionPerformed(ActionEvent e) {
			 
			 
			if(bookingRefID==null) JOptionPane.showMessageDialog(null, "Please select one reservation to cancel ");
			else{
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
			if(!rs.next())JOptionPane.showMessageDialog(null, "there is no booking result");
			else{ rs.previous();
			tbl_current.setModel(DbUtils.resultSetToTableModel(rs));
			JOptionPane.showMessageDialog(null, "Cancelling the reservation successfully");
			}
			
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
	
		
	}
	
	class addPassengerListener implements ActionListener{
		private ArrayList<String> passengerInfo; private String tripID; private String seatType; private Integer userID; private DBMgr sqlExe;
		private JFrame portalFrame; private JFrame frame;
		public addPassengerListener(JFrame frame,JFrame portalFrame,ArrayList<String> passengerInfo,String tripID,String seatType,Integer userID,DBMgr sqlExe) {
			this.passengerInfo=passengerInfo; this.tripID=tripID; this.seatType=seatType; this.userID=userID; this.sqlExe=sqlExe; this.frame=frame;
			this.portalFrame=portalFrame;
		}
		@Override
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
		
	}
		
	}


