package com.roop;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.roop.category.CategoryService;
import com.roop.common.entity.Category;

@Controller
public class MainController {
	@Autowired private CategoryService categoryService;
	@GetMapping("")
	public String viewHomePage(Model model) {
		
		List<Category> listCategories =categoryService.listNoChildrenCategories();
		model.addAttribute("listCategories", listCategories);
		return "index";
	}
	
}
