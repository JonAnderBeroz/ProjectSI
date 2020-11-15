package patterns;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Bet;
import domain.BetContainer;
import domain.BetType;
import domain.Event;
import domain.Prediction;
import domain.PredictionContainer;
import domain.Question;
import domain.Sport;
import domain.User;

public class tableTest extends JFrame {
	private BetsAdapterJTable tableModel;
	private JTable table;
	private PredictionContainer pc;
	private BetContainer bc;
	public tableTest(String title) {
		super(title);
		setBounds(10,10,400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		Date date;
		try {
			//settings parameters to go with the table
			date = df.parse("12:00 27/5/2020");
			Event e = new Event(1, "test1-test2", date, date, Sport.FOOTBALL);
			Question q = new Question("test", 1,e);
			Prediction p = new Prediction(q, "yes", 1);
			pc = new PredictionContainer(p);
			List<Prediction> lp = new ArrayList<Prediction>();
			lp.add(p);
			Bet b = new Bet(new User(), (float)2, BetType.DOUBLE, lp);
			bc = new BetContainer(b);
			//creating the table
			tableModel = new BetsAdapterJTable(pc, bc);
			table = new JTable(tableModel);
			table.setAutoCreateRowSorter(true);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(380,280));
			JPanel panel = new JPanel();
			panel.add(scrollPane);
			add(panel,BorderLayout.CENTER);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	
	}
	public static void main(String[] args) {
		tableTest ts = new tableTest("Testing table adapter");
		ts.setVisible(true);
	}

}
