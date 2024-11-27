package com.vyatsu.task14.repositories.specification;

import java.util.function.Predicate;



@FunctionalInterface
public interface ListSpecification<T> {
	boolean isSatisfiedBy(T item);
	static <T> ListSpecification<T> where(ListSpecification<T> spec) {
		return spec;
	}
	static <T> ListSpecification<T> all() {
		return item -> true;
	}
	default ListSpecification<T> and(ListSpecification<T> other) {
		return item -> this.isSatisfiedBy(item) && other.isSatisfiedBy(item);
	}
	default ListSpecification<T> or(ListSpecification<T> other) {
		return item -> this.isSatisfiedBy(item) || other.isSatisfiedBy(item);
	}
	default ListSpecification<T> not() {
		return item -> !this.isSatisfiedBy(item);
	}
}