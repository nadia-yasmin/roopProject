package com.roop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.roop.category.CategoryService;
import com.roop.common.entity.Category;
import com.roop.common.entity.Product;

@Controller
public class ProductController{
	@Autowired private ProductService productService;
	@Autowired private CategoryService categoryService;
	@GetMapping("/c/{category_alias}")
	public String viewCategoryFirstPage(@PathVariable("category_alias") String alias,
			Model model) {
		return viewCategoryByPage(alias,1,model);
	}
	@GetMapping("/c/{category_alias}/page/{pageNum}")
	public String viewCategoryByPage(@PathVariable("category_alias") String alias,
			@PathVariable("pageNum") int pageNum,
			Model model) {
	Category category=	categoryService.getCategory(alias);
	if(category==null) {
		return "error/404";
	}
	List<Category> listCategoryParents= categoryService.getCategoryParents(category);
	Page<Product> pageProducts= productService.listByCategory(pageNum, category.getId());
	List<Product> listProducts= pageProducts.getContent();
	// EKHAN THEK COPY KORLAM LONG START TO GET TOTOAL ELEMENT
	long startCount =(pageNum-1)* ProductService.PRODUCTS_PER_PAGE +1;
	long endCount =startCount+ ProductService.PRODUCTS_PER_PAGE -1;
	if(endCount>pageProducts.getTotalElements()) {
		endCount= pageProducts.getTotalElements();
	}
	
	
	model.addAttribute("currentPage", pageNum);
	
	model.addAttribute("totalPages", pageProducts.getTotalPages());
	model.addAttribute("startCount", startCount);
	model.addAttribute("endCount", endCount);
	model.addAttribute("totalItems", pageProducts.getTotalElements());
	
	
	//EKHAN PORJONTO
	model.addAttribute("pageTitle",category.getName());
	model.addAttribute("listCategoryParents",listCategoryParents);
	model.addAttribute("listProducts",listProducts);
		return "products_by_category";
	}
	@GetMapping("/product")
	public String allProduct(Model model) {
		model.addAttribute("listProducts",productService.getAllProduct());
		return "products_by_category";
	}
	@GetMapping("/order/save")
	public String confirmOrder() {
		
		return "saveOrder";
	}
	@GetMapping("/p/products/addtocart")
	public String orderProduct() {
		
		return "cart_form";
	}
	@GetMapping("/p/{product_photos}")
	public String viewProductDetail(@PathVariable("product_photos") String photos, Model model) {
		try {
			Product product= productService.getProduct(photos);
			List<Category> listCategoryParents= categoryService.getCategoryParents(product.getCategory());
			model.addAttribute("listCategoryParents",listCategoryParents);
			model.addAttribute("product",product);
			model.addAttribute("pageTitle",product.getName());
			return "product_detail";
		} catch(ProductNotFoundException e) {
			return "error/404";
		}
	}
}


