///////*
////// * To change this license header, choose License Headers in Project Properties.
////// * To change this template file, choose Tools | Templates
////// * and open the template in the editor.
////// */
//package com.javaonly.buildingessentials.controller;
//
//import com.javaonly.buildingessentials.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// *
// * @author Prathima
// */
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       
//       
//        http
//                .csrf()
//                    .disable()
//               // .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/orders.html")
//                .permitAll()
//                .anyRequest()
//                    .authenticated();
//        
//////                
////                .and()
////                
////      .oauth2Login();
////        
//    }
//// http
////         .authorizeRequests()
////            .antMatchers("/", "/index.html").permitAll()
////            .anyRequest().authenticated()
////            .and()
////         .formLogin()
////            .loginPage("/login")
////            .permitAll()
////            .and()
////            .logout()
////            .permitAll();
////   }
////   @Autowired
////   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////      auth
////         .inMemoryAuthentication()
////         .withUser("user").password("password").roles("USER");
////   }
////
//}
