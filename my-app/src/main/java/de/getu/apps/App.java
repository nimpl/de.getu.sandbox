package de.getu.apps;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception{
		System.out.println("Hello World!");

		Properties p = new Properties();
				p.load(new FileInputStream("META-INF/application.properties"));
		System.out.println("Application: "+  p.toString());
	}
}
