package core;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringCalculator{
	Logger logger = LoggerFactory.getLogger(StringCalculator.class);
	String customDelimiterStart = "//";
	String customDelimiterEnd = "\n";

	public int calculate(String input) {
		
		int result = 0;
		String delimiter = null, valueInput = null;
		Calculator calculator = null;
		
		Calculator simpleCal = () -> {
			CalInput calInput = new CalInput();
			
			calInput.valueInput = input;
			calInput.delimiter = ",|:";
			
			return calInput;
		};
		
		Calculator customCal = () -> {
			CalInput calInput = new CalInput();
			
//			calInput.delimiter = getCustomDelimiter(input);
//			int lastIndex = input.indexOf(customDelimiterEnd);
//			calInput.valueInput = input.substring(lastIndex + customDelimiterEnd.length());
			
			Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(input);
			if(matcher.find()) {
				calInput.delimiter = matcher.group(1);
				calInput.valueInput = matcher.group(2);
			}
			
			return calInput;
		};
		
		if(input == null || input.isEmpty()) {
			return 0;
		}
		
		isNegativeValue(input);
		
		if(input.startsWith("//")) {
			calculator = customCal;
		}
		
		Character startChar = input.charAt(0); 
		if(Character.isDigit(startChar)) {
			calculator = simpleCal;
		}
		
		CalInput calInput = calculator.initValue();
		valueInput = calInput.valueInput;
		delimiter = calInput.delimiter;
		result = sum(valueInput.split(delimiter));
		
		return result;
	}
	private int sum(String[] inputArray) {
		return Arrays.stream(inputArray).mapToInt( Integer::parseInt ).sum();
	}

	private String getCustomDelimiter(String input) {
		int firstIndex = input.indexOf(customDelimiterStart) + customDelimiterStart.length();
		int lastIndex = input.indexOf(customDelimiterEnd);
		String customDelimiter = input.substring(firstIndex, lastIndex);
		
		return customDelimiter;
	}
	public boolean isNegativeValue(String input) throws RuntimeException{
		
		if(input.contains("-")) {
			logger.debug("Input can't be nagative value");
			throw new RuntimeException();
		}
		return false;
	}
	
	
}
