package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.domain.Specification;

import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import com.vyatsu.task14.repositories.specification.ListSpecification;
import com.vyatsu.task14.repositories.specification.ProductSpecification;



@Component
public class ProductRepository {
   private List<Product> products;
	@PostConstruct
	public void init() {
		products = new ArrayList<>();
		Random rnd = new Random();
		for (Long i = 1L; i < 21; i++) {
			products.add(new Product(i, "Milk" + i, rnd.nextInt(500), 0));
		}
		for (Long i = 21L; i < 41; i++) {
			products.add(new Product(i, "Cheese" + i, rnd.nextInt(500), 0));
		}
		for (Long i = 41L; i < 61; i++) {
			products.add(new Product(i, "Bread" + i, rnd.nextInt(500), 0));
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

		ListSpecification<Product> spec = ListSpecification.all();
		if (title != null && !title.isEmpty()) {
			spec = spec.and(ProductSpecification.hasTitle(title));
		}

		if (gt != null) {
			spec = spec.and(ProductSpecification.hasPriceGreaterThan(gt));
		}
		if (lt != null) {
			spec = spec.and(ProductSpecification.hasPriceLessThan(lt));
		}

		return products.stream()
			.filter(spec::isSatisfiedBy)
			.collect(Collectors.toList());
	}
}
