package finalProject.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import finalProject.Database.DatabaseHandler;
import finalProject.Obj.BudgetData;

@SuppressWarnings("serial")
public abstract class AddComponent extends JPanel implements ActionListener{
	
	private JButton addBtn;
	protected DatabaseHandler db;
	protected int bID;
	
	public AddComponent(DatabaseHandler handler){
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(400,50));
		this.bID = 0;
		this.db = handler;
		this.addBtn = new JButton("+");
		this.setBackground(Color.gray);
		addBtn.addActionListener(this);
		add(addBtn, BorderLayout.EAST);
	}
	
	public abstract void showInsertWindow() throws SQLException;
	
	 protected void setNextBID() throws SQLException {
		ArrayList<BudgetData> res = db.getCurrentBID();
		this.bID = res.get(0).getBudgetID()+1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			showInsertWindow();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
