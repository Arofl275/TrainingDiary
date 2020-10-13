package ap.trainingdiary.gui;

import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.Properties;

public class LabeledTextFieldBuilder{
	public static LabeledTextField create(final int columns, final ColumnType columnType){
		LabeledTextField labeledTextField = new LabeledTextField();
		labeledTextField.getLabel().setText(Properties.getInstance().getValue(columnType));
		labeledTextField.getTextField().setColumns(columns);
		return labeledTextField;
	}
	public static LabeledTextField repeats(final int columns){
		return create(columns, ColumnType.REPEATS);
	}
	public static LabeledTextField weight(final int columns){
		return create(columns, ColumnType.WEIGHTKG);
	}
	public static LabeledTextField nameOfComment(){
		return create(
			Integer.parseInt(Properties.getInstance().getValue(ColumnType.NAMEOFCOMMENTCOLUMNS)),
			ColumnType.NAMEOFCOMMENT
		);
	}
	public static LabeledTextField nameOfTemplate(){
		return create(
			Integer.parseInt(Properties.getInstance().getValue(ColumnType.NAMEOFTEMPLATECOLUMNS)),
			ColumnType.NAMEOFTEMPLATE
		);
	}
}
