package tech.wenisch.ipfix.generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import tech.wenisch.ipfix.generator.service.IPFIXGeneratorService;
import tech.wenisch.ipfix.generator.util.ResponseManager;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {
	@Autowired
	private IPFIXGeneratorService inputService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request, Model model) {

		model.addAttribute("title", "About");
		model.addAttribute("description",
				"This IPFIX  (IP Flow Information Export) Generator is a tool designed to create and send IPFIX traffic for testing, demonstration, and analysis purposes. It simulates network flow data by generating IPFIX packets, which can be used to test network monitoring systems, analyze network performance, and ensure the accuracy of flow data collection.");
		return "index";
	}



	@PostMapping("/process")
	public String processInput(@RequestParam("inputValue") String inputValue, Model model) {
		String result = inputService.processInput(inputValue);
		model.addAttribute("result", result);
		return "index";
	}
}
