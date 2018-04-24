package com.rasto.accommodationbookingsystem.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

public interface CrudService<T extends Serializable> {

	JpaRepository<T, Long> getRepository();

	@Transactional
	default T save(T entity) {
		return getRepository().saveAndFlush(entity);
	}

	default void delete(T entity) {
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		getRepository().delete(entity);
	}

	default void delete(long id) {
		delete(load(id));
	}

	default long count() {
		return getRepository().count();
	}

	default T load(long id) {
		T entity = getRepository().findById(id).orElse(null);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	T createNew();
}
