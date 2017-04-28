package org.tryhard.gl.egghunt.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.tryhard.gl.egghunt.Child;

public class ResultsTableModel extends AbstractTableModel {

	private ArrayList<Child> children;
	private String[] headers;

	private static final long serialVersionUID = 138651387872134687L;

	public ResultsTableModel(ArrayList<Child> children) {
		super();
		this.children = children;
		this.headers = new String[] { "Prénom", "Mètres parcourus", "Œufs ramassés", "Score" };
	}

	@Override
	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return children.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Child c = children.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return c.getName();
		case 1:
			return c.getDistance();
		case 2:
			return c.getBasket().size();
		case 3:
			return c.getDistance() + c.getBasket().size() * 10; // Un oeuf rapporte 10 points
		default:
			throw new IllegalArgumentException("Le numero de colonne indiqué n'est pas valide.");

		}
	}

	public void setChildren(ArrayList<Child> nChildren) {
		children = nChildren;
	}
}
