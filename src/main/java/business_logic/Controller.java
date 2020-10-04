package business_logic;

public interface Controller<T, K> extends SaverController<T>,UpdaterController<T>,DeleterController<K>,Reader<T> {	
}