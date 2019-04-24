package core;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class StringCalculatorTest {
	private StringCalculator stringCalculator;
	private static Logger logger;
	
	
	@BeforeClass
	public static void loggerInit() {
		logger = LoggerFactory.getLogger(StringCalculatorTest.class);
	}
	
	@Before
	public void setup() {
		stringCalculator = new StringCalculator();
		
	}

	@Test
	public void calculate() {
		
		//custom
		String customInput1 = "//;\n1;2;3";
		assertThat(stringCalculator.calculate(customInput1)).as("calculate custom input1").isEqualTo(6);

		String customInput2 = "//a\n1a1a1";
		assertThat(stringCalculator.calculate(customInput2)).as("calculate custom input2").isEqualTo(3);
		
		
	}
	
	@Test
	public void add_����() {
		String negativeInput1 = "1:2:-3";
		assertThatThrownBy( () -> { stringCalculator.calculate(negativeInput1); } )
		.as("add_����")
		.isInstanceOf(RuntimeException.class)
		;
	}
	
	@Test
	public void add_null_�Ǵ�_����() {
		assertThat(stringCalculator.calculate(null)).as("add_null").isEqualTo(0);
		assertThat(stringCalculator.calculate("")).as("add_����").isEqualTo(0);
	}
	
	@Test
	public void add_�����ϳ�() {
		String input = "1";
		assertThat(stringCalculator.calculate(input)).as("add_�����ϳ�").isEqualTo(1);
	}
	
	@Test
	public void add_��ǥ_�Ǵ�_�ݷ�_������() {
		String input = "1,2:3";
		assertThat(stringCalculator.calculate(input)).as("add_��ǥ_�Ǵ�_�ݷ�_������").isEqualTo(6);
	}
	
}
