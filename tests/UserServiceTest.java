/**
 * 
 */
package DATA.tests;

import static org.junit.Assert.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import DATA.exceptions.BadInformationException;
import DATA.model.User;
import DATA.services.UserService;

/**
 * JUnit Tests for UserService class
 * @author le-goc
 *
 */
public class UserServiceTest {
	/**
	 * UserService reference to be tested
	 */
	private UserService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.service = new UserService();
	}

	/**
	 * Test for createUser function
	 */
	@Test
	public void createUserTest() {
		User user = null;
		try {
			assertNull(service.createUser(user));
			fail("Expected BadInformationException");
		} catch(BadInformationException e){
			// TEST OK
		}
		
		// Null login test
		user = new User("", "test", "test", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null login not recognized");
		} catch(BadInformationException e){
			// TEST OK
		}
		
		// Null password test
		user = new User("test", "", "test", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null password not recognized");
		} catch(BadInformationException e){
			// TEST OK
		}
		
		// Null firstname test
		user = new User("test", "test", "", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null firstName not recognized");
		} catch(BadInformationException e){
			// TEST OK
		}
		
		//Null lastname test
		user = new User("test", "test", "test", "", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null lastName not recognized");
		} catch(BadInformationException e){
			// TEST OK
		}
		
		//Everything ok
		user = new User("test", "test", "test", "test", "test", "test");
		List<String> adress = new ArrayList<String>();
		adress.add("127.0.0.1");

		user.setListIP(adress);
		try {
			user = service.createUser(user);
		} catch (BadInformationException e) {
			fail("EveryThing is normally OK");
		}
		assertEquals("Firstname correpsond", user.getFirstname(), "test");
	}

}
