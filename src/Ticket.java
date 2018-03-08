package ooad;

import org.omg.CORBA.TRANSACTION_MODE;

public class Ticket {
	
	private Integer bookingRefId, tripID, uid, ticketStatus, seatNo;
	private String seatType;
	public Ticket(Integer bookingRefId,  Integer tripID, Integer uid, Integer ticketStatus, Integer seatNo, String seatType){
		this.bookingRefId=bookingRefId; this.uid=uid; this.tripID=tripID; this.ticketStatus=ticketStatus;
		this.seatNo=seatNo; this.seatType=seatType;
		 
	}

}
