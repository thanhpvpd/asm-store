package poly.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import poly.store.entity.Product;
import poly.store.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/list/{idCategory}/{page}")
	public String list(Model model, @PathVariable("idCategory") String idCategory, @PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 8);
		Page<Product> list;
		String id = idCategory;
		if (id.contains("all")) {
			list = productService.findAll(pageable);
			model.addAttribute("page", list);
			model.addAttribute("idca", "all");
		} else {
			list = productService.findByCategory(id, pageable);
			model.addAttribute("page", list);
			model.addAttribute("idca", id);
		}
		model.addAttribute("pageFalse", true);
		return "/product/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		Pageable pageable = PageRequest.of(0, 4);
		Page<Product> list = productService.findByCategory(product.getCategory().getId(), pageable);
		model.addAttribute("page", list);
		return "/product/detail";
	}

	@GetMapping("/index")
	public String home(Model model) {
		Pageable pageable = PageRequest.of(0, 4);
		Page<Product> list = productService.findAll(pageable);
		model.addAttribute("page", list);
		return "/layout/index";
	}
	
	@GetMapping("/find")
	public String find(Model model, @RequestParam("findProduct") String find) {
		Pageable pageable = PageRequest.of(0, 8);
		Page<Product> list = productService.findName(find, pageable);
		model.addAttribute("page", list);
		model.addAttribute("find", find);
		model.addAttribute("pageFalse", false);
		return "/product/list";
	}
	
}
