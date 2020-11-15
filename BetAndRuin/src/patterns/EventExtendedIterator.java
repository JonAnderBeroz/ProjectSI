package patterns;

import java.util.Vector;

import domain.Event;

public class EventExtendedIterator implements ExtendedIterator<Event> {

	private Vector<Event> events;
	private int position = 0;
	public EventExtendedIterator(Vector<Event> e) {
		this.events = e;
	}
	@Override
	public boolean hasNext() {
		return position < events.size();
	}

	@Override
	public Event next() {
		return events.get(position++);
	}

	@Override
	public Event previous() {
		return events.get(position--);
	}

	@Override
	public boolean hasPrevious() {
		return position > -1;
	}

	@Override
	public void goFirst() {
		// TODO Auto-generated method stub
		this.position = 0;
	}

	@Override
	public void goLast() {
		// TODO Auto-generated method stub
		this.position = events.size() -1;
	}

}
