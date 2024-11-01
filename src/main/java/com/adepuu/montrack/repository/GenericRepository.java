package com.adepuu.montrack.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
  T save(T entity);
  T findById(ID id);
  List<T> findAll();
  void delete(T entity);
  void deleteById(ID id);
  T update(T entity);
}
