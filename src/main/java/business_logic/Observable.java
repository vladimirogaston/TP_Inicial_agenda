package business_logic;

public interface Observable {
	
	void registerObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObservers();
}