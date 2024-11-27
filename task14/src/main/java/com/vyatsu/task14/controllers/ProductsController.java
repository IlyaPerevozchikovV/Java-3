package com.vyatsu.task14.controllers;

import java.util.List;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
	private ProductRepository productsRepository;
		@Autowired
		public void setProductsRepository(ProductRepository productsRepository) {
		this.productsRepository = productsRepository;}

		@GetMapping
		public String showProductsList(@RequestParam(value = "title", required = false) String title,
				       @RequestParam(value = "gt", required = false) Integer gt,
				       @RequestParam(value = "lt", required = false) Integer lt,
				       Model model) {
		List<Product> products = productsRepository.filterProducts(title, gt, lt);
			model.addAttribute("products", products);
			model.addAttribute("product", new Product());
			return "products";
		}

		@PostMapping("/add")
		public String addProduct(@ModelAttribute(value = "product")Product product) {
			productsRepository.save(product);
			return "redirect:/products";
		}

		@GetMapping("/show/{id}")
		public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
			Product product = productsRepository.findById(id);
			model.addAttribute("product", product);
			return "product-page";
		}

		@PostMapping("/delete/{id}")
		public String deleteProduct(Model model, @PathVariable(value = "id") Long id) {
			productsRepository.delete(id);
			return "redirect:/products";
		}

		@GetMapping("/edit/{id}")
		public String editFormProduct(Model model, @PathVariable(value = "id") Long id) {
			Product product = productsRepository.findById(id);
			model.addAttribute("product", product);
			return "edit-product";
		}
		@PostMapping("/edit")
		public String editProduct(@RequestParam(value = "id", required = false) Long id,
					  @RequestParam(value = "title", required = false) String title,
					  @RequestParam(value = "price", required = false) Integer price,
					  Model model) {
			productsRepository.edit(id, title, price);
			Product product = productsRepository.findById(id);
			model.addAttribute("product", product);
			return "edit-product";
		}
}


