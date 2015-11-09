import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.designpatterns.app.model.MySQLDAOFactory;
import com.designpatterns.app.model.DAOFactory;
import com.designpatterns.app.model.Database;
import com.designpatterns.app.model.Person;
import com.designpatterns.app.model.PersonDAO;


public class PersonDAOTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		System.out.println("set up before class");
		
		Database.getInstance().connect();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		System.out.println("tear down after class");
		
		Database.getInstance().disconnect();
	}
	
	@Before
	public void setUp() throws Exception{
		System.out.println("set Up");
		
		MySQLDAOFactory factory = new MySQLDAOFactory();
		PersonDAO personDAO = factory.getPersonDAO();
		personDAO.deleteAll();
	}
	
	@After
	public void tearDown() throws Exception{
		System.out.println("tear down");
	}
	
	@Test
	public void testDemo() {
		int value = 7;
		value +=2;
		
		System.out.println("Running Demo tests");
		
		assertEquals("Check that arithmetic value:", 9, value);
	}
	
	@Test
	public void testCreate() throws SQLException{
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		PersonDAO personDAO = factory.getPersonDAO();
		
		Person person1 = new Person("Bob", "letmein");
		Person person2 = new Person("Sue", "hello");
		
		personDAO.addPerson(person1);
		personDAO.addPerson(person2);
		
		List<Person> people = personDAO.getPeople();
		
		assertEquals("Should be 2 people in the database", 2, people.size()); 
		
		assertEquals("This two people should be the same", person1, people.get(0));
		assertEquals("This two people should be the same", person2, people.get(1));
	} 
	
}
