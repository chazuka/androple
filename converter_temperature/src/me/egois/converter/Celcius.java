package me.egois.converter;

public class Celcius {
	
	public static double fromFahrenheit(double n) {
		 return (n - 32) * 5/9;
	}
	
	public static double fromKelvin(double n) {
		 return n - 273.15;
	}
	
	public static double fromNewton(double n) {
		return n * 100/33;
	}
	
	public static double fromRankine(double n) {
		return (n - 491.67) * 5/9;
	}
	
	public static double fromReaumur(double n) {
		return n * 5/4;
	}
	
	public static double fromRomer(double n) {
		return (n-7.5) * 40/21;
	}
	
	public static double toFahrenheit(double n) {
		return (n * 9/5) + 32;
	}
	
	public static double toKelvin(double n) {
		return n + 273.15;
	}
	
	public static double toNewton(double n) {
		return n * 33/100;
	}
	
	public static double toRankine(double n) {
		return (n + 273.15) * 9/5;
	}
	
	public static double toReaumur(double n) {
		return n * 4/5;
	}
	
	public static double toRomer(double n) {
		return (n * 21/40) + 7.5;
	}
}
