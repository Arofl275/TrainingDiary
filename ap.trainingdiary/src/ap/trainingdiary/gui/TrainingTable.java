package ap.trainingdiary.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import java.awt.HeadlessException;
import java.awt.Window;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.TableModelListener;

import ap.trainingdiary.type.TrainingRow;
import static ap.swing.JMenuItemBuilder.addAction;

public class TrainingTable{
	private final Window parent;
	private final JScrollPane scrollPane;
	private final JTable table;
	private final TrainingTableModel trainingTableModel;
	private final JMenuItem miAddRow;
	private final JMenuItem miDeselect;
	private final JMenuItem miEditRow;
	private final JMenuItem miRemove;
	private final JMenuItem miMoveUP;
	private final JMenuItem miMoveDOWN;
	
	public static TrainingTable create(final Window parent){
		return new TrainingTable(parent, TrainingTableModel.create());
	}
	public static TrainingTable create(final Window parent, final Collection<TrainingRow> collection){
		return new TrainingTable(parent, TrainingTableModel.create(collection));
	}
	private TrainingTable(final Window parent, final TrainingTableModel trainingTableModel){
		this.parent = parent;
		this.trainingTableModel = trainingTableModel;
		table = new JTable(trainingTableModel);
		scrollPane = new JScrollPane(table);
		
		miAddRow = addAction(
			table,
			JMenuItemBuilder.addRow(),
			KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					addRow();
				}
			},
			"addRow"
		);
		miEditRow = addAction(
			table,
			JMenuItemBuilder.editRow(),
			KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					editRow();
				}
			},
			"aeditRow"
		);
		miDeselect = addAction(
			table,
			JMenuItemBuilder.deselect(),
			KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					table.clearSelection();
				}
			},
			"deselect"
		);
		miRemove = addAction(
			table,
			JMenuItemBuilder.remove(),
			KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					removeRows();
				}
			},
			"remove"
		);
		miMoveUP = addAction(
			table,
			JMenuItemBuilder.moveUP(),
			KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					moveUP();
				}
			},
			"moveUP"
		);
		miMoveDOWN = addAction(
			table,
			JMenuItemBuilder.moveDOWN(),
			KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					moveDOWN();
				}
			},
			"moveDOWN"
		);

		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.addPopupMenuListener(
			new PopupMenuListener(){
				public void popupMenuCanceled(PopupMenuEvent e){}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e){}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e){
					popupMenu.removeAll();
					if(table.getSelectedRowCount() == 0){
						popupMenu.add(miAddRow);
					}
					else if(table.getSelectedRowCount() == 1){
						popupMenu.add(miEditRow);
						popupMenu.add(miDeselect);
						popupMenu.add(miRemove);
						if(isContiguous()){
							if(!isOnTop()){
								popupMenu.add(miMoveUP);
							}
							if(!isOnBottom()){
								popupMenu.add(miMoveDOWN);
							}
						}
					}
					else{
						popupMenu.add(miDeselect);
						popupMenu.add(miRemove);
						if(isContiguous()){
							if(!isOnTop()){
								popupMenu.add(miMoveUP);
							}
							if(!isOnBottom()){
								popupMenu.add(miMoveDOWN);
							}
						}
					}
				}
			}
		);
		table.setComponentPopupMenu(popupMenu);
		scrollPane.setComponentPopupMenu(popupMenu);
		table.requestFocus();
		DefaultTableCellRenderer tableCellRenderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
		tableCellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	public List<TrainingRow> getTrainingData(){
		return trainingTableModel.getTrainingData();
	}
	public void setTrainingData(final List<TrainingRow> list){
		trainingTableModel.setTrainingData(list);
	}
	private boolean isContiguous(){
		final int[] selectedRows = table.getSelectedRows();
		if(selectedRows.length > 0){
			for(int i = 1; i < selectedRows.length; i++){
				if(selectedRows[i] != selectedRows[i - 1] + 1){
					return false;
				}
			}
		}
		return true;
	}
	private boolean isOnTop(){
		final int[] selectedRows = table.getSelectedRows();
		if(selectedRows.length > 0){
			if(selectedRows[0] != 0){
				return false;
			}
		}
		return true;
	}
	private boolean isOnBottom(){
		final int[] selectedRows = table.getSelectedRows();
		if(selectedRows.length > 0){
			final int last = trainingTableModel.getRowCount() - 1;
			if(selectedRows[selectedRows.length - 1] != last){
				return false;
			}
		}
		return true;
	}
	private void deselect(){
		if(table.getSelectedRowCount() > 0){
			table.clearSelection();
		}
	}
	private void removeRows(){
		if(table.getSelectedRowCount() > 0){
			final int[] selectedRows = table.getSelectedRows();
			TreeSet<Integer> set = new TreeSet<Integer>();
			for(int i = 0; i < selectedRows.length; i++){
				set.add(Integer.valueOf(selectedRows[i]));
			}
			List<TrainingRow> trainingData = trainingTableModel.getTrainingData();
			List<TrainingRow> newTrainingsData = new LinkedList<TrainingRow>();
			for(int i = 0; i < trainingData.size(); i++){
				if(!set.contains(Integer.valueOf(i))){
					newTrainingsData.add(trainingData.get(i));
				}
			}
			trainingTableModel.setTrainingData(newTrainingsData);
		}
	}
	private void moveUP(){
		if(table.getSelectedRowCount() > 0 && isContiguous() && !isOnTop()){
			final int[] selectedRows = table.getSelectedRows();
			final int firstSelectedRow = selectedRows[0];
			final int lastSelectedRow = selectedRows[selectedRows.length - 1];
			final int movableRow = firstSelectedRow - 1;
			ArrayList<TrainingRow> trainingData = new ArrayList<TrainingRow>(trainingTableModel.getTrainingData());
			TrainingRow movableTrainingRow = trainingData.get(movableRow);
			for(int i = firstSelectedRow; i <= lastSelectedRow; i++){
				trainingData.set(i - 1, trainingData.get(i));
			}
			trainingData.set(lastSelectedRow, movableTrainingRow);
			trainingTableModel.setTrainingData(trainingData);
			table.addRowSelectionInterval(firstSelectedRow - 1, lastSelectedRow - 1);
		}
	}
	private void moveDOWN(){
		if(table.getSelectedRowCount() > 0 && isContiguous() && !isOnBottom()){
			final int[] selectedRows = table.getSelectedRows();
			final int firstSelectedRow = selectedRows[0];
			final int lastSelectedRow = selectedRows[selectedRows.length - 1];
			final int movableRow = lastSelectedRow + 1;
			ArrayList<TrainingRow> trainingData = new ArrayList<TrainingRow>(trainingTableModel.getTrainingData());
			TrainingRow movableTrainingRow = trainingData.get(movableRow);
			for(int i = lastSelectedRow; i >= firstSelectedRow; i--){
				trainingData.set(i + 1, trainingData.get(i));
			}
			trainingData.set(firstSelectedRow, movableTrainingRow);
			trainingTableModel.setTrainingData(trainingData);
			table.addRowSelectionInterval(firstSelectedRow + 1, lastSelectedRow + 1);
		}
	}
	private void addRow(){
		if(table.getSelectedRowCount() == 0){
			AddTrainingRowDialog addTrainingRowDialog = new AddTrainingRowDialog(parent);
			try{
				int result = addTrainingRowDialog.show();
				if(result == addTrainingRowDialog.OK_OPTION){
					trainingTableModel.add(
						new TrainingRow(
							addTrainingRowDialog.getWeight(),
							addTrainingRowDialog.getRepeats()
						)
					);
				}
			}
			catch(HeadlessException e){
				e.printStackTrace();
			}
		}
	}
	private void editRow(){
		if(table.getSelectedRowCount() == 1){
			final int[] selectedRows = table.getSelectedRows();
			if(selectedRows.length == 1){
				final int selectedRow = selectedRows[0];
				final TrainingRow selectedTrainingRow = trainingTableModel.get(selectedRow);
				EditTrainingRowDialog editTrainingRowDialog = new EditTrainingRowDialog(parent);
				editTrainingRowDialog.setWeight(selectedTrainingRow.getWeightKG());
				editTrainingRowDialog.setRepeats(selectedTrainingRow.getRepeats());
				try{
					int result = editTrainingRowDialog.show();
					if(result == editTrainingRowDialog.OK_OPTION){
						trainingTableModel.set(
							selectedRow,
							new TrainingRow(
								editTrainingRowDialog.getWeight(),
								editTrainingRowDialog.getRepeats()
							)
						);
					}
				}
				catch(HeadlessException e){
					e.printStackTrace();
				}
			}
		}
	}
	public void	addTableModelListener(final TableModelListener listener){
		trainingTableModel.addTableModelListener(listener);
	}
}
