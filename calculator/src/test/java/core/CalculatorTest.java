package core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {
	private SimpleCalculator cal;
	
	@Before
	public void setup() {
		cal = new SimpleCalculator();
		
	}
	
	@Test
	public void add() {
		assertThat(cal.add(6, 3)).as("add have to be 9").isEqualTo(9);
		
		System.out.println(cal.num);
	}
	
	@Test
	public void subtract() {
		assertThat(cal.subtract(6, 3)).as("subtract have to be 3").isEqualTo(3);
		cal.num = 3;
		System.out.println(cal.num);
	}
	
}
