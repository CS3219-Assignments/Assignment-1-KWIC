package input;

import java.util.List;

public interface IRepository<T> {

	void add(T entity);
	void remove(T entity);
	int find(T entity);
	T readNext();
	List<T> getAll();
}
