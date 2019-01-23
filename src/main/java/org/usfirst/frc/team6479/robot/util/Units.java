package org.usfirst.frc.team6479.robot.util;


//class for conversion of units and to increase code clarity
public class Units {
	
	//distance values
	//note: this does not include pulses or voltage for certain sensors as this can vary from sensor to sensor
	public enum Distance {
		inches (1.0), 
		feet (12.0 * inches.value), 
		yards (3.0 * feet.value), 
		millimeters (25.4 * inches.value), 
		centimeters (10.0 * millimeters.value), 
		meters (100.0 * centimeters.value);
		
		//constrcutor to hold value
		Distance(double value) {
			this.value = value;
		}
		//the value of the distance in inches
		public double value;
	}
	//convert one value to antoher
	public static double convert(double value, Distance from, Distance to) {
		//first convert value to inches
		double inInches = value * from.value;
		//then convert it to the to value
		double converted = inInches / to.value;
		return converted;
	}
	
	public enum Time {
		milliseconds (1.0), 
		seconds (milliseconds.value / 1000.0), 
		minutes (seconds.value / 60.0);
		
		//constrcutor to hold value
		Time(double value) {
			this.value = value;
		}
		//the value of the time in milliseconds
		public double value;
	}
	//convert one value to antoher
	public static double convert(double value, Time from, Time to) {
		//first convert value to milliseconds
		double inMilli = value * from.value;
		//then convert it to the to value
		double converted = inMilli / to.value;
		return converted;
	}
	
	public enum Rotational {
		degrees (1.0), 
		radians (degrees.value * (Math.PI / 180.0));
		
		//constrcutor to hold value
		Rotational(double value) {
			this.value = value;
		}
		//the value of the time in degrees
		public double value;
	}
	//convert one value to antoher
	public static double convert(double value, Rotational from, Rotational to) {
		//first convert value to degrees
		double inDegrees = value * from.value;
		//then convert it to the to value
		double converted = inDegrees / to.value;
		return converted;
	}
	
}
