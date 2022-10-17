/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials;
//import com.javaonly.buildingessentials.controller.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;




/**
 *
 * @author Prathima
 */

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

//@SpringBootApplication
public class BuildingEssentialsApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(BuildingEssentialsApplication.class, args);
	} 
}
