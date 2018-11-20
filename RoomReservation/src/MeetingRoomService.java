import java.time.LocalTime;
import java.util.List;


public interface MeetingRoomService {

	/**
	 * Returns all the rooms 
	 */
	List<Room> getRooms();
	
	/**
	 * Checks if any room is available for the given time range. 
	 */
	Room isAvailable(LocalTime start, LocalTime end);
	
	/**
	 * Reserves any available room for the given time range. 
	 * It also handles "no availability" appropriately 
	 */
	boolean reserve(LocalTime start, LocalTime end, String owner);
	
	/**
	 * Fetches all reservations that are owned by the given owner 
	 */
	List<Reservation> getReservations(String owner);
	
	/**
	 * Cancels all reservations owned by the owner for the given time range
	 */
	boolean cancel(String owner, LocalTime start, LocalTime end);
	
}

