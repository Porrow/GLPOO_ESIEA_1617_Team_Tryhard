package org.tryhard.gl.egghunt.gui;

import static java.awt.BorderLayout.CENTER;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.*;

public class ResultsDialog extends JDialog {

	private static final long serialVersionUID = 4398087483922446754L;

	private static final Logger LOGGER = Logger.getLogger(ResultsDialog.class);
	private JTable table;
	private ResultsTableModel model;

	public ResultsDialog() {
		super(EggHunt.getInstance().getWin(), "Scores", true);
		setSize(400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		model = new ResultsTableModel(getChildren());
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		getContentPane().add(new JScrollPane(table), CENTER);
		pack();
		Window.dialog = this;
	}

	public ArrayList<Child> getChildren() {
		ArrayList<Child> children = new ArrayList<>();
		Game g = (Game) EggHunt.getInstance().getViews().get(Game.ID);
		for (GraphicObject o : g.getGarden().getDescendants())
			if (o instanceof Child)
				children.add((Child) o);
		return children;
	}

	public void update() {
		model.setChildren(getChildren());
		repaint();
	}
}
