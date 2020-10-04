package business_logic;

public interface DeleterController<K> {

	boolean deleteById(K id);
}
