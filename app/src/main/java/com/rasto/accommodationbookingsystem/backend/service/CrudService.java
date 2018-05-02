package com.rasto.accommodationbookingsystem.backend.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudService<T extends Serializable, ID> {

	T createNew();

	List<T> findAll();

	Optional<T> findById(ID id);

	T saveOrUpdate(T entity);

	void delete(ID id);

	void delete(T entity);
}
