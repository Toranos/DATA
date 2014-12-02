package DATA.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DATA.exceptions.BadInformationException;
import DATA.model.User;
import DATA.services.DataService;

/**
 * JUnit Tests for DataService class
 * @author Yoann
 *
 */
public class DataServiceTest {
	/**
	 * DataService reference to be tested
	 */
	private DataService service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		this.service = DataService.getInstance();
	}
	
	/**
	 * Test for export function
	 */
	@Test
	public void exportimportTest() {
//		User user = new User("value1", "value2", "valeur3", "valeur4", "valeur5", "valeur6");
//		service.setPathProfile("src/DATA/tests/profile/profile.data");
//		try {
//			service.setUser(user);
//			service.exports();
//			try {
//				DataService.imports();
//				User test = DataService.getInstance().getUser();
//				assertEquals("User lastname", user.getLastname(), test.getLastname());
//				assertEquals("User firstname", user.getFirstname(), test.getFirstname());
//				assertEquals("User login", user.getLogin(), test.getLogin());
//				assertEquals("User password", user.getPassword(), test.getPassword());
//				assertEquals("User avatar", user.getAvatar(), test.getAvatar());
//				assertEquals("User birthday", user.getBirthDate(), test.getBirthDate());
//			} catch (Exception e) {
//				fail("Not Expected Import Exception");
//			}
//			
//		} catch(Exception e){
//			fail("Not Expected Export Exception");
//		}
	}
}
