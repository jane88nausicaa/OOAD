package ooad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class PlaneController {
	
	public addPlaneListener addPlane(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,DBMgr sqlExe){
		return new addPlaneListener(planeNo, eSeat, bSeat, status, sqlExe);
	}
	public retrievePlaneListener retrievePlane(JTextField planeNo, JTextField status, DBMgr sqlExe){
		return new retrievePlaneListener(planeNo, status, sqlExe);
	}
	public updatePlaneListener updatePlane(JTextField planeNo, JTextField status, DBMgr sqlExe){
		return new updatePlaneListener(planeNo, status, sqlExe);
	}
	public displayPlaneListener displayPlane(DBMgr sqlExe,JTable tbl_plane){
		return new displayPlaneListener(sqlExe, tbl_plane);
	}
	
	
	
	class addPlaneListener implements ActionListener{
		private JTextField planeNo,eSeat,bSeat,status1;
		private DBMgr sqlExe;
		public addPlaneListener(JTextField planeNo,JTextField eSeat,JTextField bSeat,JTextField status,DBMgr sqlExe){
			this.planeNo=planeNo; this.eSeat=eSeat;this.bSeat=bSeat;this.status1=status; this.sqlExe=sqlExe;
		}
		
		public void actionPerformed(ActionEvent e) {
			String plane_no = planeNo.getText();
			String eSeats = eSeat.getText();
			String bSeats = bSeat.getText();
			String status = status1.getText();

			String[] value = new String[] {"'"+plane_no+"'", eSeats, bSeats, status};
			String[] attribute = new String[] {"flightNo", "eSeats", "bSeats", "status" };
			
			sqlExe.add("flight", attribute, value);
			
		}
		
	}
	
	class retrievePlaneListener implements ActionListener{
		private JTextField planeNo, status; private DBMgr sqlExe;
		public  retrievePlaneListener(JTextField planeNo, JTextField status, DBMgr sqlExe) {
			this.planeNo=planeNo; this.status=status; this.sqlExe=sqlExe;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String plane_no = planeNo.getText();
			  if(!plane_no.equals("")){
				  String conditions="flightNo=" + "'"+plane_no+"'";
				  try {
					ResultSet rSet=sqlExe.selectAll("flight", conditions);
					int _status= -1;
					if(rSet.next()){
						_status=rSet.getInt(4);
					}
					if(_status == -1) {
						JOptionPane.showMessageDialog(null, "No matched results!");
						status.setText("");
					}
					if(_status != -1){
						status.setText(Integer.toString(_status));
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  else JOptionPane.showMessageDialog(null, "Please enter plane no!");
			
		}
		
	}
	
	class updatePlaneListener implements ActionListener{
		private JTextField planeNo, status;
		private DBMgr sqlExe;
		public updatePlaneListener(JTextField planeNo, JTextField status, DBMgr sqlExe){
			this.planeNo=planeNo; this.status=status; this.sqlExe=sqlExe;
		}
		public void actionPerformed(ActionEvent e) {
			String[] attrs = {"flightNo", "status"};
			String[] values = {"'"+planeNo.getText()+"'", status.getText()};
			String con = "flightNo ="+ "'"+planeNo.getText()+"'";
			sqlExe.update("flight", attrs, values, con);	
		}
		
	}
	
	class displayPlaneListener implements ActionListener{
		private DBMgr sqlExe; 
		private JTable tbl_plane;
		public displayPlaneListener(DBMgr sqlExe,JTable tbl_plane) {
			this.sqlExe=sqlExe; this.tbl_plane=tbl_plane;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ResultSet rs=sqlExe.selectAll("flight", "1");
				if(!rs.next()) JOptionPane.showMessageDialog(null, "ther is no planes info");
				else{ rs.previous();
				tbl_plane.setModel(DbUtils.resultSetToTableModel(rs)); }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	
}
