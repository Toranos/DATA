/**
 * 
 */
package DATA.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DATA.exceptions.BadInformationException;
import DATA.model.User;
import DATA.services.DataService;
import DATA.services.UserService;

/**
 * JUnit Tests for UserService class.
 */
public class UserServiceTest {
	/**
	 * UserService reference to be tested.
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
	 * Test for createUser function.
	 */
	@Test
	public void createUserTest() {
		User user = null;
		try {
			assertNull(service.createUser(user));
			fail("Expected BadInformationException");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// Null login test
		user = new User("", "test", "test", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null login not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// Null password test
		user = new User("test", "", "test", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null password not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// Null firstname test
		user = new User("test", "test", "", "test", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null firstName not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		//Null lastname test
		user = new User("test", "test", "test", "", "test", "test");
		try {
			assertNull(service.createUser(user));
			fail("Null lastName not recognized");
		} catch (BadInformationException e){
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
	
	/**
	 * Test for checkProfile function.
	 */
	@Test
	public void checkProfileTest() {
		try {
			service.updateProfile(new User("test", "test", "test", "test", "test", "test"));
		} catch (IOException | BadInformationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Null login test
		User user = new User(null, "test", "test", "test", "test", "test");
		try {
			assertNull(service.checkProfile(user.getLogin(), user.getPassword()));
			fail("Expected BadInformationException");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// Null password test
		user = new User("test", null, "test", "test", "test", "test");
		try {
			assertNull(service.checkProfile(user.getLogin(), user.getPassword()));
			fail("Null password not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// empty login test
		user = new User("", "test", "test", "test", "test", "test");
		try {
			assertNull(service.checkProfile(user.getLogin(), user.getPassword()));
			fail("Null login not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		// empty password test
		user = new User("test", "", "test", "test", "test", "test");
		try {
			assertNull(service.checkProfile(user.getLogin(), user.getPassword()));
			fail("Null password not recognized");
		} catch (BadInformationException e){
			// TEST OK
		}
		
		//Everything ok
		user = new User("test", "test", "test", "test", "test", "test");
		try {
			user = service.checkProfile(user.getLogin(), user.getPassword());
		} catch (BadInformationException e) {
			fail("EveryThing is normally OK");
		}
		assertEquals("Same Uid, same user", user.getUid(), service.getCurrentUser().getUid());
	}

}
