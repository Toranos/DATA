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
		this.service = DataService.getInstance();
	}
	
	/**
	 * Test for export function
	 */
	@Test
	public void exportimportTest() {
		User user = new User("value1", "value2", "valeur2", "valeur3", "valeur4", "valeur5");
		service.setPathProfile("src/DATA/tests/profile/profile.data");
		try {
			service.setUser(user);
			service.exports();
			try {
				DataService.imports();
				assertEquals("User correpsond", user, DataService.getInstance().getUser());
			} catch (Exception e) {
				fail("Not Expected Import Exception");
			}
			
		} catch(Exception e){
			fail("Not Expected Export Exception");
		}
	}
}
