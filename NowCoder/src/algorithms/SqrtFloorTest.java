package algorithms;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqrtFloorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSqrtFloor() {
		for (int i = 0; i < 1000; i++) {
			int a = (int) Math.floor(Math.sqrt(i));
			int b = SqrtFloor.sqrtFloor(i);
			assertEquals(a,b);
		}
		int a = (int) Math.floor(Math.sqrt(-1));
		
		System.out.println(a);
	}


}
