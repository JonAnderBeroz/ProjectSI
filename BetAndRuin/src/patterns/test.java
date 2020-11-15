package patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacadeImplementation;
import domain.Event;

public class test {

	public static void main(String[] args) {
		BLFacadeImplementation bf = new BLFacadeImplementation();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		Date date;
		try {
			date = df.parse("12:00 27/5/2020");
			ExtendedIterator<Event> i = bf.getEvents(date);
			System.out.println("_____________________");
			System.out.println("AURRETIK ATZERAKA");
			while (i.hasNext()) {
				System.out.println("Event:" + i.next().getDescription());
			}
			System.out.println();
			System.out.println("_____________________");
			System.out.println("ATZETIK AURRERAKA");
			i.goLast();
			while (i.hasPrevious()) {
				System.out.println("Event:" + i.previous().getDescription());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
