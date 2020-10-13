package ap.trainingdiary.gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.ColumnType;

import ap.trainingdiary.type.TrainingRow;

public class TrainingTableModel extends AbstractTableModel{

	private String[] columnName = new String[]{
		Properties.getInstance().getValue(ColumnType.WEIGHTKG),
		Properties.getInstance().getValue(ColumnType.WEIGHTLB),
		Properties.getInstance().getValue(ColumnType.REPEATS)
	};
	public static TrainingTableModel create(){
		return new TrainingTableModel(new ArrayList<TrainingRow>());
	}
	public static TrainingTableModel create(final Collection<TrainingRow> collection){
		return new TrainingTableModel(new ArrayList<TrainingRow>(collection));
	}
	private List<TrainingRow> data;
	private TrainingTableModel(List<TrainingRow> data){
		this.data = data;
	}
	public int getRowCount(){
		return data.size();
	}
	public int getColumnCount(){
		return columnName.length;
	}
	public Class getColumnClass(int column){
		return String.class;
	}
	public String getColumnName(int column){
		return columnName[column];
	}
	public Object getValueAt(int row, int column){
		String result = "";
		TrainingRow trainingRow = data.get(row);
		switch(column){
			case 0:
				result = trainingRow.getWeightKGToString();
				break;
			case 1:
				result = trainingRow.getWeightLBToString();
				break;
			case 2:
				result = trainingRow.getRepeatsToString();
				break;
		}
		return result;
	}
	public void add(final int index, final TrainingRow row){
		data.add(index, row);
		fireTableDataChanged();
	}
	public void add(final TrainingRow row){
		data.add(row);
		fireTableDataChanged();
	}
	public void remove(final int index){
		data.remove(index);
		fireTableDataChanged();
	}
	public void set(final int index, final TrainingRow row){
		data.set(index, row);
		fireTableDataChanged();
	}
	public TrainingRow get(final int index){
		return data.get(index);
	}
	public List<TrainingRow> getTrainingData(){
		return new ArrayList<TrainingRow>(data);
	}
	public void setTrainingData(final List<TrainingRow> data){
		this.data = new ArrayList<TrainingRow>(data);
		fireTableDataChanged();
	}
}
