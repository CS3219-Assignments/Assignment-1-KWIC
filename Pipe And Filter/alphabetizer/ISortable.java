package alphabetizer;

public interface ISortable<T> {
	Iterable<T> sortAscending(Iterable<T> collection);
	Iterable<T> sortDescending(Iterable<T> collection);
}
