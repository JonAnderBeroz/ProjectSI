package patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.BetContainer;
import domain.Event;
import domain.PredictionContainer;
import domain.Question;

public class BetsAdapterJTable extends AbstractTableModel {
	private final List<Event> event = new ArrayList<Event>();
	private final List<Question> question = new ArrayList<Question>();
	private PredictionContainer pc;
	private String[] colNames = {"Event","Question","Event Date", "Stake"};
	private BetContainer bc ;
	
	public BetsAdapterJTable(PredictionContainer  p, BetContainer bc) {
		this.pc = p;
		this.event.add(p.getEvent());
		this.question.add(p.getQuestion());
		this.bc = bc;
	}
	@Override
	public int getColumnCount() {
		return  colNames.length	;
	}

	@Override
	public int getRowCount() {
		return event.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colunmIndex) {
		switch(colunmIndex) {
		case 0: return  event.get(rowIndex).getDescription();
		case 1: return  question.get(rowIndex).getQuestion(); 
		case 2: return  event.get(rowIndex).getEventDate();
		case 3:
			for(PredictionContainer pc : bc.getPredictions()) {
				if(pc.getPrediction().equals(this.pc.getPrediction())) {
					return bc.getBet().getStake();
				}
			}
			default: break;
		}
		return null;
	}
	
	 public String getColumnName(int col) {
	      return colNames[col];
	   }
}
