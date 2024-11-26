package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;



@Component
public class ProductRepository {
   private List<Product> products;
	@PostConstruct
	public void init() {
		products = new ArrayList<>();
		Random rnd = new Random();
		for (Long i = 1L; i < 101; i++) {
			products.add(new Product(i, "Item" + i, rnd.nextInt(500), 0));
		}
	}

	public List<Product> findAll() {
		return products;
	}
	public Product findByTitle(String title) {
		return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
	}

	public List<Product> findProductsByTitle(String title) {
		return products.stream()
			.filter(product -> product.getTitle() != null &&
				product.getTitle().toLowerCase().contains(title.toLowerCase()))
			.collect(Collectors.toList());
	}
	public Product findById(Long id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
	}
	public void save(Product product) {
		products.add(product);
	}

	public void delete(Long id) {
		products.stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(products::remove);
	}

	public void edit(Long id, String title, Integer price) {
		products.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.ifPresent(product -> {
					product.setTitle(title);
					product.setPrice(price);
				});
	}

	public List<Product> filterProducts(String title, Integer gt, Integer lt)
	{
		if (gt == null && lt == null && (title == null || title.isEmpty())) {
			return products;
		}

		List<Product> result = this.products;
		if (title != null && !title.isEmpty()) {
			result = this.findProductsByTitle(title);
		}

		if (gt != null) {
			result = result.stream()
				.filter(product -> product.getPrice() >= gt)
				.collect(Collectors.toList());
		}
		if (lt != null) {
			result = result.stream()
				.filter(product ->  product.getPrice() <= lt)
				.collect(Collectors.toList());
		}

		return result;
	}
}
