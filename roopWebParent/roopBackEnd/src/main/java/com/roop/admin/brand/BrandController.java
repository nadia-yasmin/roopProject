package com.roop.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.roop.admin.category.CategoryNotFoundException;
import com.roop.admin.category.CategoryService;
import com.roop.common.entity.Brand;
import com.roop.common.entity.Category;

@Controller

public class BrandController {
@Autowired
private BrandService brandService;
@Autowired
private CategoryService categoryService;
@GetMapping("/brands")
public String listAll(Model model) {
	List<Brand> listBrands= brandService.listAll();
	model.addAttribute("listBrands", listBrands);
	return "brands/brands";
}
@GetMapping("/brands/new")
public String newBrand(Model model) {
	List<Category> listCategories=  categoryService.listCategoriesUsedInForm();
	model.addAttribute("listCategories", listCategories);
	model.addAttribute("brand", new Brand());
	model.addAttribute("pageTitle", "Create new Brand");
	return "brands/brand_form";
	//return "categories/normal";
}
@PostMapping("/brands/save")
		public String saveBrand(Brand brand ,
				@RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
			if(!multipartFile.isEmpty()) {
	String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
	brand.setLogo(fileName);
	Brand savedBrand = brandService.save(brand);
	String uploadDir="src/main/resources/static/images" ;
	//FileUploadUtil.cleanDir(uploadDir);   + savedCategory.getId() upore
	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}
			else {
				brandService.save(brand);
			}
	ra.addFlashAttribute("message","The category has been saved successfully");
	return "redirect:/brands";

	}
@GetMapping("/brands/edit/{id}")
public String editBrand(@PathVariable(name="id") Integer id,Model model, RedirectAttributes ra ) throws BrandNotFoundException {
	try {
		Brand brand = brandService.get(id);
		List<Category> listCategories= categoryService.listCategoriesUsedInForm();
		model.addAttribute("brand", brand);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("PageTitle", "Edit brand(ID:"+id+")");
		return "brands/brand_form";
	} catch(BrandNotFoundException ex) {
		ra.addFlashAttribute("message", ex.getMessage());
		return "redirect:/brands";
	}
}
}
