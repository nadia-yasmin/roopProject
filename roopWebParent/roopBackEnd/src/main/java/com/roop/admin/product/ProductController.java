package com.roop.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import com.roop.admin.brand.BrandService;
import com.roop.admin.category.CategoryService;
import com.roop.common.entity.Brand;
import com.roop.common.entity.Category;
import com.roop.common.entity.Product;
import com.roop.common.entity.user;

@Controller  
public class ProductController {
@Autowired private ProductService productService;
@Autowired private BrandService brandService;
@Autowired private CategoryService categoryService;
@GetMapping("/products")
public String listAll(Model model) {
	List<Product> listProducts= productService.listAll();
	model.addAttribute("listProducts",listProducts);
	return "products/products";
	//return listByPage(1,model,"name","asc", null, 0);
	//return "categories/normal";
}
/*@GetMapping("/products/page/{pageNum}")
public String listByPage(
		@PathVariable(name="pageNum") int pageNum, Model model,
		@Param("sortField") String sortField, @Param("sortDir") String sortDir,
		@Param("keyword") String keyword,
		@Param("categoryId") Integer categoryId
		) {
	Page<Product> page= productService.listByPage(pageNum, sortField, sortDir, keyword, categoryId);
	List<Product> listProducts= page.getContent();
	List<Category> listCategories= categoryService.listCategoriesUsedInForm();
	long startCount =(pageNum-1)* ProductService.PRODUCTS_PER_PAGE +1;
	long endCount =startCount+ (pageNum-1)* ProductService.PRODUCTS_PER_PAGE -1;
	if(endCount>page.getTotalElements()) {
		endCount= page.getTotalElements();
	}
	String reverseSortDir= sortDir.equals("asc") ? "desc" : "asc" ;
	if(categoryId != null) model.addAttribute("categoryId", categoryId);
	model.addAttribute("currentPage", pageNum);
	
	model.addAttribute("totalPages", page.getTotalPages());
	model.addAttribute("startCount", startCount);
	model.addAttribute("endCount", endCount);
	model.addAttribute("totalItems", page.getTotalElements());
	model.addAttribute("sortField", sortField);
	model.addAttribute("sortDir", sortDir);
	model.addAttribute("reverseSortDir", reverseSortDir);
	model.addAttribute("keyword", keyword);
	model.addAttribute("listProducts", listProducts);
	model.addAttribute("listCategories", listCategories);
	return "product/products";
} */
@GetMapping("/products/new")
public String newProduct(Model model) {
	List<Brand> listBrand =brandService.listAll();
	List<Category> listCategory=categoryService.listAll();
	Product product= new Product();
	product.setEnabled(true);
	product.setInStock(true);
	model.addAttribute("product",product);
	model.addAttribute("listBrands",listBrand);
	model.addAttribute("listCategory",listCategory);
	model.addAttribute("pageTitle","Create New Product");
	return "products/product_overview";
}
/*@PostMapping("/products/save")
public String saveProduct(Product product, RedirectAttributes ra) {
	productService.save(product);
	ra.addFlashAttribute("message","The product has been saved successfully");
	return "redirect:/products";
} 
@PostMapping("/products/save")
public String saveProduct(Category product ,
		@RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
	if(!multipartFile.isEmpty()) {
String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
product.setImage(fileName);
Category  savedProduct = productService.save(product);
String uploadDir="src/main/resources/static/images" ;
//FileUploadUtil.cleanDir(uploadDir);   + savedCategory.getId() upore
FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
	else {
		service.save(category);
	}
ra.addFlashAttribute("message","The category has been saved successfully");
return "redirect:/categories";

} */
@PostMapping("/products/save")
public String saveProduct(Product product,
		@RequestParam("fileImage") MultipartFile multipartFile
		, RedirectAttributes redirectAttributes) throws IOException {
	if(!multipartFile.isEmpty()) {
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		product.setPhotos(fileName);
		Product savedUser= productService.save(product);
		//product.setPhotos(fileName);
		String uploadDir= "src/main/resources/static/images" ;
		//FileUploadUtil.cleanDir(uploadDir);
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} else {
		
		productService.save(product);
	}
	
	
	redirectAttributes.addFlashAttribute("message", "This product has been saved successfully");
	return "redirect:/products";
	
}

}
