
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TestMeetingRoomService {
	
	MeetingRoomService service = new Booking();
	
	/**
	 * Test get rooms
	 */
	@Test
	void testGetRooms() {
		Assert.assertEquals(4, service.getRooms().size());
	}
	
	
	/**
	 * Test reserve
	 * five persons reserve for the same time range, the last person cannot reserve(get False)
	 */
	@Test
	void testReserve() {
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Lynn"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Micheal"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Shipra"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"May"));
		
		Assert.assertEquals(false, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Bob"));
	}
	
	
	/**
	 * Test isAvailable 
	 */
	@Test
	void testIsAvailable() {
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Lynn"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Micheal"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Shipra"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"May"));
		
		Assert.assertEquals(null, service.isAvailable(LocalTime.of(16, 00), LocalTime.of(18, 00)));
	}
	
	
	/**
	 * Test get reservations
	 */
	@Test
	void testGetReservations() {
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Lynn"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(17, 00), LocalTime.of(18, 00),"Lynn"));
		Assert.assertEquals(2, service.getReservations("Lynn").size());
	}
	
	
	/**
	 * Test cancel
	 */
	@Test
	void testCancel() {
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Lynn"));
		Assert.assertEquals(true, service.reserve(LocalTime.of(17, 00), LocalTime.of(18, 00),"Lynn"));
		Assert.assertEquals(2, service.getReservations("Lynn").size());
		Assert.assertEquals(true, service.cancel("Lynn", LocalTime.of(15, 00), LocalTime.of(19, 00)));
		Assert.assertEquals(0, service.getReservations("Lynn").size());
	}
	
	
	/**
	 * Test cancel a non-exist reservation
	 */
	@Test
	void testCancelNonExist() {
		Assert.assertEquals(true, service.reserve(LocalTime.of(15, 30), LocalTime.of(17, 00),"Lynn"));
		Assert.assertEquals(1, service.getReservations("Lynn").size());
		Assert.assertEquals(false, service.cancel("Lynn", LocalTime.of(00, 00), LocalTime.of(01, 00)));
		Assert.assertEquals(1, service.getReservations("Lynn").size());
		
	}

}
