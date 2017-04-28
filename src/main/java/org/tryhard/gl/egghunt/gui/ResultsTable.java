package org.tryhard.gl.egghunt.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import org.tryhard.gl.egghunt.Child;

public class ResultsTable extends AbstractTableModel {

	private ArrayList<Child> children;
	private String[] headers;

	private static final long serialVersionUID = 138651387872134687L;

	public ResultsTable(ArrayList<Child> children) {
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
			return 0;// c.getDistance();
		case 2:
			return c.getBasket().size();
		case 3:
			return /*c.getDistance()*/0 + c.getBasket().size() * 10;
		default:
			throw new IllegalArgumentException("Le numero de colonne indiqué n'est pas valide.");

		}
	}

}
