package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {//Serves main index.html
 
	@RequestMapping({ "/dashboard", "/signup", "/advertisements/new", "/deposit"})
	public String index() {
	    return "forward:/index.html";
	}
       
         
}