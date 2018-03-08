package ooad;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.print.Printable;
import java.util.ArrayList;

import javax.crypto.AEADBadTagException;
import javax.swing.JButton;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Passengers_ {

	private JFrame frame;
	private Integer numOfPassenger;
	private DBMgr sqlExe;
	//private Integer bookingRef;
	private Integer userID;
	private String tripID;
	private Double totalPrice;
	private String[] labelList={"First Name:","Last Name:","PassportID:","DOB:","Phone","Email:"};
	private ArrayList<JTextField> txtFieldList=new ArrayList<JTextField>();
	private String seatType;
	private JFrame portalFrame;
	/**
	 * Launch the application.
	 */
	public static void main(final JFrame portalFrame, final Double totalPrice,final String tripID,final int userID, final int numOfTicket, final String seatType,final DBMgr sqlExe) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passengers_ window = new Passengers_(portalFrame, totalPrice,tripID,userID,numOfTicket,seatType,sqlExe);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	public static void main(String[] args){
//		try {
//			Integer numOfTicket=4;
//			String tripID="1";
//			int userID=0;
//			Passengers window = new Passengers(0.0,tripID,userID,numOfTicket,null);
//			
//			window.frame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the application.
	 */
	public Passengers_(JFrame portalFrame,Double totalPrice,String tripID, int userID, int numOfTicket, String seatType, DBMgr sqlExe) {
		numOfPassenger=numOfTicket;
		this.portalFrame=portalFrame;
		this.totalPrice=totalPrice;
		this.userID=userID;
		this.tripID=tripID;
		this.seatType=seatType;
		this.sqlExe=sqlExe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 516, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][][][][][][][]", "[][][][][][][][][]"));
		JButton btn_checkout=new JButton("Check Out");
		btn_checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> passengerInfo=new ArrayList<>();
				for(int i=0;i<txtFieldList.size();i++){
					passengerInfo.add(txtFieldList.get(i).getText());
				}
				
				if(passengerInfo.contains("")) JOptionPane.showMessageDialog(null, "all fields are required");
				else{
					Payment_.main(portalFrame, totalPrice,tripID,userID,passengerInfo,seatType,sqlExe);
					frame.dispose();
					
				}
			}
		});
		frame.getContentPane().add(btn_checkout,"cell 3 "+(numOfPassenger*3)+",growy");
///////////////////////////////////////////////
		for(int j=0,gap=0;j<numOfPassenger;j++,gap+=2){
			System.out.println(j);
			frame.getContentPane().add(new JLabel("Passenger "+(j+1)+":"),"cell 0 "+(j+gap));
			for(int i=0;i<labelList.length;i++){
					frame.getContentPane().add(new JLabel(labelList[i]),"cell "+i+" "+(j+gap+1));
					JTextField text=new JTextField(10);
					txtFieldList.add(text);
					frame.getContentPane().add(text,"cell "+(i+1)+" "+(j+gap+1)+",growx");
			}	
		}
		
			
	}


}
