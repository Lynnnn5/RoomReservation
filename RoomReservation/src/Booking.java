import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking implements MeetingRoomService{

	Map<String,Room> rooms;
	Map<String,List<Reservation>> reservations;
	
	
	public Booking() {
		rooms = new HashMap<>();
		reservations = new HashMap<>();
		rooms.put("R1",new Room("R1"));
		rooms.put("R2",new Room("R2"));
		rooms.put("R3",new Room("R3"));
		rooms.put("R4",new Room("R4"));
	}


	/**
	 * Returns all the rooms 
	 */
	@Override
	public List<Room> getRooms() {
		return new ArrayList<Room>(rooms.values());
	}
	
	
	/**
	 * Checks if any room is available for the given time range. 
	 */
	@Override
	public Room isAvailable(LocalTime start, LocalTime end) {
		for(String num:rooms.keySet()) {
			if(rooms.get(num).isAvailable(start, end)) {
				return rooms.get(num);
			}
		}
		return null;
	}
	
	
	/**
	 * Reserves any available room for the given time range. 
	 * It also handles "no availability" appropriately 
	 */
	@Override
	public boolean reserve(LocalTime start, LocalTime end, String owner){
		Room room = isAvailable(start,end);
		if(room!=null) {
			Reservation r = new Reservation(start,end,room.getRoomName(),owner);
			room.reserve(r);
			List<Reservation> res = reservations.get(owner);
			if(res==null) res = new ArrayList<>();
			synchronized(res) {
				res.add(r);
				reservations.put(owner, res);
			}
			return true;	
		}else {
			return false;
		}
	}
	
	
	/**
	 * Fetches all reservations that are owned by the given owner 
	 */
	@Override
	public List<Reservation> getReservations(String owner){
		return reservations.get(owner);
	}
	
	
	/**
	 * Cancels all reservations owned by the owner for the given time range
	 */
	@Override
	public boolean cancel(String owner, LocalTime start, LocalTime end) {
		List<Reservation> res = getReservations(owner);
		List<Reservation> needCancel = new ArrayList<>();
		boolean canceled = false;
		synchronized(res) {
			for(Reservation r:res) {
				if((r.getStartTime().isAfter(start)||r.getStartTime().equals(start))
						&&r.getEndTime().isBefore(end)||r.getEndTime().equals(end)){
					Room room = rooms.get(r.getRoomName());
					room.cancel(r);
					needCancel.add(r);
					canceled = true;
				}
			}
			res.removeAll(needCancel);
		}
		return canceled;
	}


}
