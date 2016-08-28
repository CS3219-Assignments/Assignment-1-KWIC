package io;

import java.util.List;

import pipeAndFilter.IFilter;

public interface IRepository<T> extends IFilter<T,T>{

	void add(T entity);
	void remove(T entity);
	int find(T entity);
	T readNext();
	List<T> getAll();
}
