import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Megan Porto and Tom Levine
 *
 */
public class TestCommissionCalculator {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * AMP
	 * This method will determine the proper name and experience attributes
	 * are given to an employee of type probationary (pEmp) and 
	 * type experienced (eEmp)
	 */
	
	@Test
	public void testEmployeeAttributes() {
		CommissionCalculator pEmp = new CommissionCalculator("Megan", 0);
		assertEquals(pEmp.getName(), "Megan");
		assertEquals(pEmp.getEmployeeExperience(), 0);
		
		CommissionCalculator eEmp=new CommissionCalculator("Tom", 1);
		assertEquals(eEmp.getName(), "Tom");
		assertEquals(eEmp.getEmployeeExperience(), 1);
	}
	
	/**
	 * AMP
	 * This test case will test the commission for a probationary employee
	 * before he reaches a minimum sales total of $2000.00 (no commission) 
	 */
	
	@Test
	public void testProbationaryBeforeMinimum (){
		CommissionCalculator pEmp = new CommissionCalculator("Megan", 0);
		
		 //Add a basic sale at $1.00 (no commission)
		 pEmp.addSale(0, 1.00);
		assertEquals(pEmp.calculateCommission(), 0, 0);
		
		 //Add a sale of $1999.00 to test commission at exactly the minimum of $2000.00
		 //(no commission, must be above $2000.00)
		pEmp.addSale(0, 1999.00);
		assertEquals(pEmp.calculateCommission(), 0, 0);
			
	}
	
	/**
	 * AMP
	 * This test will calculate the commission for a probationary employee after reaching
	 * minimum sales. The method will test commission percentages of a basic sales item,
	 * maintenance contract, replacement parts, and consulting services
	 */
	
	@Test
	public void testProbationaryAfterMinimum (){
		CommissionCalculator pEmp = new CommissionCalculator("Megan", 0);
		pEmp.addSale(0, 2000.00);
		
		//First ensure commission at $2001.00
		//This also test correct percentage rates for Basic Sales Item
		pEmp.addSale(0, 1.00);
		assertEquals(pEmp.calculateCommission(), 0.02,0);
		
		//Test correct percentage for maintenance contract
		pEmp.addSale(1, 100.00);
		assertEquals(pEmp.calculateCommission(), 3.02, 0);
		
		//Test correct percentage for replacement parts
		pEmp.addSale(2, 100.00);
		assertEquals(pEmp.calculateCommission(), 4.02, 0);
		
		//Test correct percentage for consulting services
		pEmp.addSale(3, 100.00);
		assertEquals(pEmp.calculateCommission(), 7.02, 0);
		
		//We should now ensure proper commission rates up until the bonus commission sales level
		//is reached, when the commission rate increases
		
		pEmp.addSale(0, 49699);
		assertEquals(pEmp.calculateCommission(),1001.00 , 0);
		
	}
	
	/**
	 * AMP
	 * This test will calculate the commission for a probationary employee after
	 * meeting the bonus requirement of $50,000.
	 */
	@Test
	public void testProbationaryWithBonus(){
		
		CommissionCalculator pEmp=new CommissionCalculator("Megan", 0);
		pEmp.addSale(0, 50000.00);
		//First ensure there is a standard rate right at $50000
		assertEquals(pEmp.calculateCommission(), 960.00, 0);
		
		//Now ensure the bonus commission at $50,002.00
		//(This gives us a solid one cent, compared to $1.00
		pEmp.addSale(0,2.00 );
		assertEquals(pEmp.calculateBonusCommission(),0.01,0 );
		
		//Try again with a higher value
		pEmp.addSale(0, 10000);
		assertEquals(pEmp.calculateBonusCommission(), 50.01, 0);
		
	}
}
