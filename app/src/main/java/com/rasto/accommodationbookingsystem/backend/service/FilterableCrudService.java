package com.rasto.accommodationbookingsystem.backend.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface FilterableCrudService<T extends Serializable> extends CrudService<T> {

	List<T> findAnyMatching(Optional<String> filter);

}
