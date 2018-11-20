import java.time.LocalTime;
import java.util.UUID;

public class Reservation {
	
	private String id;
	private LocalTime startTime;
	private LocalTime endTime;
	private String roomName;
	private String owner;
	
	
	public Reservation(LocalTime startTime, LocalTime endTime, String roomName, String owner) {
		super();
		id = UUID.randomUUID().toString();
		this.startTime = startTime;
		this.endTime = endTime;
		this.roomName = roomName;
		this.owner = owner;
	}


	public LocalTime getStartTime() {
		return startTime;
	}


	public LocalTime getEndTime() {
		return endTime;
	}


	public String getRoomName() {
		return roomName;
	}


	public String getOwner() {
		return owner;
	}
		

}
