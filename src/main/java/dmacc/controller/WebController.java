package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import dmacc.model.Company;
import dmacc.repository.CompanyRepository;

@Controller
public class WebController {

	@Autowired
	CompanyRepository repo;
	
	@GetMapping("/viewAll")
	public String viewAllCompany(Model model) {
		
		if(repo.findAll().isEmpty()) {
			return addNewCompany(model);
		}
		
		model.addAttribute("Company", repo.findAll());
		return "results";
	}
	
	@GetMapping("/inputCompany")
	public String addNewCompany(Model model) {
		Company c = new Company();
		model.addAttribute("newCompany", c);
		return "input";
	}
	
	@PostMapping("inputCompany")
	public String addNewCompany(@ModelAttribute Company c, Model model) {
		repo.save(c);
		return viewAllCompany(model);
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateCompany(@PathVariable("id") long id, Model model) {
		Company c = repo.findById(id).orElse(null);
		model.addAttribute("newCompany", c);
		repo.delete(c);
		return "input";
	}
	
	@PostMapping("/update/{id}")
	public String reviseCompany(Company c, Model model) {
		repo.save(c);
		return viewAllCompany(model);
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCompany(@PathVariable("id") long id, Model model) {
		Company c = repo.findById(id).orElse(null);
		repo.delete(c);
		return viewAllCompany(model);
	}
	
}
