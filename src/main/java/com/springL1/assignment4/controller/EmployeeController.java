package com.springL1.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.springL1.assignment4.entity.Employee;
import com.springL1.assignment4.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "index";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/displayAll";
	}

	@GetMapping("/displayAll")
	public String displayAllEmployees(Model model) {
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "displayAll";
	}

	@GetMapping("/display/{id}")
	public String getMethodName(@PathVariable("id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id).orElse(null);
		model.addAttribute("employee", employee);
		return "display";
	}

}
