package com.example.Forensic.Calculator; /*it is there so that it groups this 
class to other in the same directory. it sets the home folder for this file so that
it tells java where exactly this files lives in your project structure
*/
import org.springframework.boot.SpringApplication;/*
does many things: auto configuration(gives what it needs based on the program)
it launches a web serves. open the portal to port 8080. we dont nned an external
source or an api. builds space where all your application pieces can talk
 It gives this file permission to use the SpringApplication command later
  on line 10 to turn the app on.
*/
import org.springframework.boot.autoconfigure.SpringBootApplication;/*
it imports the thing on line 6
 */

@SpringBootApplication/*turns on the automatic features of spring boot
like setting up a web server and configuring everything
*/
public class ForensicCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForensicCalculatorApplication.class, args);/*
		exact command that starts the engine. tells it to load the web server and
		 open up the network port so the application can listen for the data

		 */
	}

}
