package business_logic.local;

import java.util.HashSet;
import java.util.Set;

import business_logic.Observable;
import business_logic.Observer;

public class ObservableImpl implements Observable {

	private Set<Observer> observers;
	
	public ObservableImpl() {
		observers = new HashSet<>();
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);		
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer ob : observers) {
			ob.update();
		}
	}
}
