package ooad;

public class Trip {
	
	private String flightNo, departTime, arriveTime, departAirport, arriveAirport; 
	private Double flyingHours, ePrice, bPrice; 
	private Integer status, availableESeats, availableBSeats; 
	public Trip(String flightNo, String departTime, String arriveTime, String departAirport, String arriveAirport,
			Double flyingHours, Double ePrice, Double bPrice, Integer status,Integer  availableESeats, Integer availableBSeats){
		this.flightNo=flightNo; this.departAirport=departAirport; this.arriveAirport=arriveAirport; this.departTime=departTime;
		this.arriveTime=arriveTime; this.flyingHours=flyingHours; this.ePrice=ePrice; this.bPrice=bPrice; this.status=status;
		this.availableBSeats=availableBSeats; this.availableESeats=availableESeats;
	}

}
