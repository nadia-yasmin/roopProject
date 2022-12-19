package com.roop.admin.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roop.admin.FileUploadUtil;
import com.roop.common.entity.Category;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import com.roop.admin.repository.CategoryRepository;
//import com.shopme.admin.service.impl.ICategoryService;
//import com.shopme.admin.util.CategoryPageInfo;
import com.roop.common.entity.Category;
import com.roop.common.entity.user;
//import com.roop.common.exception.CategoryNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
@Controller

public class CategoryController {
	@Autowired
	private CategoryService service;
	@GetMapping("/categories")
	public String listAll(Model model) {
		List<Category> listCategories= service.listAll();
		model.addAttribute("listCategories", listCategories);
		
		return "categories/categories";
	}
	
	@GetMapping("/categories/new")
	public String newCategory(Model model) {
		List<Category>	listCategories= service.listCategoriesUsedInForm();
		
		model.addAttribute("category", new Category());
		model.addAttribute("listCategories",listCategories);
		model.addAttribute("pageTitle","Create new category");
		return "categories/category_form";
	}
	@PostMapping("/categories/save")
	public String saveCategory(Category category ,
			@RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
		if(!multipartFile.isEmpty()) {
String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
category.setImage(fileName);
Category  savedCategory = service.save(category);
String uploadDir="src/main/resources/static/images" ;
//FileUploadUtil.cleanDir(uploadDir);   + savedCategory.getId() upore
FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		else {
			service.save(category);
		}
ra.addFlashAttribute("message","The category has been saved successfully");
return "redirect:/categories";

}
	@GetMapping("/categories/edit/{id}")
	public String editCategory(@PathVariable(name="id") Integer id,Model model, RedirectAttributes ra ) {
		try {
			Category category= service.get(id);
			List<Category> listCategories= service.listCategoriesUsedInForm();
			model.addAttribute("category", category);
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("PageTitle", "Edit category(ID:"+id+")");
			return "categories/category_form";
		} catch(CategoryNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/categories";
		}
	}
}
