import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Room {
	private String roomName;
	private List<Reservation> reservations;
	
	public Room(String roomName) {
		super();
		this.roomName = roomName;
		reservations = new ArrayList<>();
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public boolean isAvailable(LocalTime start,LocalTime end) {
		for(Reservation r:reservations) {
			if(r.getStartTime().isBefore(end) && r.getEndTime().isAfter(start)) {
				return false;
			}
		}
		return true;
	}
	
	public void reserve(Reservation reservation) {
		synchronized(reservations) {
			reservations.add(reservation);
		}
	}
	
	public void cancel(Reservation reservation) {
		synchronized(reservations) {
			reservations.remove(reservation);
		}
	}
}
