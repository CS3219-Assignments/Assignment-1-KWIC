package input;

public interface IRepository<T> {

	void add(T entity);
	void remove(T entity);
	int find(T entity);
	T readNext();
}
