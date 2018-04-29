package com.rasto.accommodationbookingsystem.backend.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T extends Serializable> {

	T createNew();

	List<T> findAll();

	Optional<T> findById(long id);

	T saveOrUpdate(T entity);

	void delete(long id);

	void delete(T entity);
}
