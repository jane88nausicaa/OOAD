package ooad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;


public class TripController {
	
	public addTripListener addTrip(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
			JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe){
		return new addTripListener(planeNo,eSeat,bSeat,status,tripID,flyingHour,dTime,aTime,dPort,aPort,sqlExe);
	}
	
	public retrieveTripListener retrieveTrip(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
			JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe){
		return new retrieveTripListener(planeNo,eSeat,bSeat,status,tripID,flyingHour,dTime,aTime,dPort,aPort,sqlExe);
	}
	public updateTripListener updateTrip(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
			JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe){
		return new updateTripListener(planeNo,eSeat,bSeat,status,tripID,flyingHour,dTime,aTime,dPort,aPort,sqlExe);
	}
	
	public displayTripListener displayTrip(DBMgr sqlExe,JTable tbl_trip){
		return new displayTripListener(sqlExe, tbl_trip);
	}

	class addTripListener implements ActionListener{
		private JTextField tripID,planeNo,eSeat,bSeat,status1,flyingHour,dTime,aTime,dPort,aPort;
		private DBMgr sqlExe;
		public addTripListener(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
				JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe){
			this.planeNo=planeNo; this.eSeat=eSeat;this.bSeat=bSeat;this.status1=status; this.sqlExe=sqlExe;
			this.tripID=tripID;this.aPort=aPort;this.dPort=dPort;this.aTime=aTime;this.dTime=dTime; this.flyingHour=flyingHour;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			//System.out.println(tripID1);  

			String dTime1=dTime.getText();
			String aTime1=aTime.getText();
			String planeID1=planeNo.getText();
			String dPort1=dPort.getText();
			String aPort1=aPort.getText();
			String flyHour1=flyingHour.getText();
			String status2=status1.getText();
			String eSeats=eSeat.getText();
			String bSeats=bSeat.getText();
			String tripID1=tripID.getText();
			String[] value = new String[] {"'"+tripID1+"'", "'"+planeID1+"'", "'"+dTime1+"'", "'"+aTime1+"'", "'"+dPort1+"'","'"+aPort1+"'",flyHour1,status2
					,eSeats,bSeats};
			String[] attribute = new String[] {"tripID", "flightno","departTime", "arriveTime", "departAirport", "arriveAirport", "flyingHours", "status",
					"availableEseats","availableBseats"};
			
			sqlExe.add("trip", attribute, value);
			
		}
		
	}
	
	class retrieveTripListener implements ActionListener{
		private JTextField planeNo, status,eSeat,bSeat,tripID1,flyingHour,dTime,aTime,dPort,aPort; private DBMgr sqlExe;
		public  retrieveTripListener(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
				JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe) {
			this.planeNo=planeNo; this.status=status; this.sqlExe=sqlExe; this.eSeat=eSeat;this.bSeat=bSeat;
			this.dPort=dPort;this.aPort=aPort; this.aTime=aTime;this.dTime=dTime; this.flyingHour=flyingHour; tripID1=tripID;
		}
		public void actionPerformed(ActionEvent e) {
			String tripID=tripID1.getText();
			  //String flight=flightID.getText();
			  if(!tripID.equals("")){
				  String conditions="tripID="+tripID;
				  try {
					ResultSet rSet=sqlExe.selectAll("trip", conditions);
					String _flightNO="";
					String _aTime="";
					String _dTime="";
					String _dPort="";
					String _aPort="";
				    double _hour=-1.0;
				    int _status=-1;
				    int _eSeat=-1;
				    int _bSeat=-1;
					if(rSet.next()){
						_flightNO=rSet.getString(2);
						_dTime=rSet.getString(3);
						_aTime=rSet.getString(4);
						_dPort=rSet.getString(5);
						_aPort=rSet.getString(6);
						_hour=rSet.getDouble(7);
						_status=rSet.getInt(8);
						_eSeat=rSet.getInt(9);
						_bSeat=rSet.getInt(10);
					}
					if(_hour==-1.0) {
						JOptionPane.showMessageDialog(null, "No matched results!");
						planeNo.setText("");
						dTime.setText("");
						aTime.setText("");
						dPort.setText("");
						aPort.setText("");
						flyingHour.setText("");
						status.setText("");
						eSeat.setText("");
						bSeat.setText("");
						
					}
					if(_hour!=-1.0){
						planeNo.setText(_flightNO);
						dTime.setText(_dTime);
						aTime.setText(_aTime);
						dPort.setText(_dPort);
						aPort.setText(_aPort);
						flyingHour.setText(Double.toString(_hour));
						status.setText(Integer.toString(_status));
						eSeat.setText(Integer.toString(_eSeat));
						bSeat.setText(Integer.toString(_bSeat));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No matched results!");
					e1.printStackTrace();
				}
			  }
			  else JOptionPane.showMessageDialog(null, "Please enter trip ID!");
			
		}
		
	}
	
	class updateTripListener implements ActionListener{
		private JTextField planeNo, status,eSeat,bSeat,tripID1,flyingHour,dTime,aTime,dPort,aPort; private DBMgr sqlExe;
		public updateTripListener(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,
				JTextField tripID,JTextField flyingHour,JTextField dTime,JTextField aTime,JTextField dPort,JTextField aPort,DBMgr sqlExe){
			this.planeNo=planeNo; this.status=status; this.sqlExe=sqlExe; this.eSeat=eSeat;this.bSeat=bSeat;
			this.dPort=dPort;this.aPort=aPort; this.aTime=aTime;this.dTime=dTime; this.flyingHour=flyingHour; tripID1=tripID;
		}
		public void actionPerformed(ActionEvent e) {
			String[] attrs = {"flightno","departTime", "arriveTime", "departAirport", "arriveAirport", "flyingHours", "Status","availableEseats",
			"availableBseats"};

	String[] values = {"'"+planeNo.getText()+"'", "'"+dTime.getText()+"'","'"+aTime.getText()+"'", 
			"'"+dPort.getText()+"'", "'"+aPort.getText()+"'",  flyingHour.getText(), status.getText()
			,eSeat.getText(),bSeat.getText()};
	String con = "tripid="+tripID1.getText();
	sqlExe.update("trip", attrs, values, con); 
	if(status.getText().toString().equals("0")){
		String[] desiredContent={"count(*)"};
		try {
			
			ResultSet rs=sqlExe.select(desiredContent, "ticket", "tripID="+tripID1.getText()+" and ticketStatus=1 group by seatType");
			if(rs.next()){
				
				int numB=rs.getInt(1);
				rs.next();
				int numE=rs.getInt(1);
				String[] attrs_trip={"availableEseats","availableBseats"};
				String[] values_trip={"availableEseats+"+Integer.toString(numE),"availableBseats+"+Integer.toString(numB)};
				String condi_trip="tripID="+tripID1.getText();
				sqlExe.updateWithoutMessage("trip", attrs_trip, values_trip, condi_trip); // decrease ticket num
				
				String[] attrs_ticket={"ticketStatus"};
				String[] values_ticket={"0"};
				String condi_ticket="tripID="+tripID1.getText();
				sqlExe.updateWithoutMessage("ticket", attrs_ticket, values_ticket, condi_ticket);
				
			}
				
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}   /////
	
		}		
	}
	
	class displayTripListener implements ActionListener{
		private DBMgr sqlExe; 
		private JTable tbl_trip;
		public displayTripListener(DBMgr sqlExe,JTable tbl_trip) {
			this.sqlExe=sqlExe; this.tbl_trip=tbl_trip;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ResultSet rs=sqlExe.selectAll("trip","1");
				if(!rs.next())JOptionPane.showMessageDialog(null, "there is no trip");
				else{ rs.previous();
				tbl_trip.setModel(DbUtils.resultSetToTableModel(rs)); }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
