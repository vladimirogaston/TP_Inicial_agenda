package repositories.jdbc;

public interface Mapper<T> {
	
	T map(Object [] obj);
}