package finalProject.Panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import finalProject.Database.DatabaseHandler;
import finalProject.Obj.BudgetData;
import finalProject.Obj.PayPeriod;
import finalProject.components.AddComponent;
import finalProject.components.AddPayPeriodsComponent;
import finalProject.components.PayPeriodComponent;

@SuppressWarnings("serial")
public class HomePanel extends JPanel{
	private DatabaseHandler db;
	private Color backgroundColor;
	//Access to payments panel to allow switching to, this keeps it so we only have two cards
	private PaymentsPanel paymentsPanel;
	private JPanel container;
	private CardLayout card;
	private AddComponent addBtn;
	
	public HomePanel(CardLayout card, PaymentsPanel panel) throws ClassNotFoundException, SQLException {
		this.db = new DatabaseHandler();
		this.backgroundColor = new Color(242,204,143);
		this.card = card;
		this.paymentsPanel = panel; 
		this.addBtn = new AddPayPeriodsComponent(this.db, this);
		this.container = new JPanel();
		setupPanel();
		setupContainer();
		setPayPeriods("");
	}
	
	public void resetPayPeriods(String input) throws SQLException {
		container.removeAll();
		setPayPeriods(input);
		container.revalidate();
	}
	
	private void setPayPeriods(String input) throws SQLException {
		ArrayList<BudgetData> res = db.getPayPeriodTitle(input);
		container.add(Box.createVerticalStrut(10));
		for(int i = 0; i < res.size(); i++) {
			container.add(new PayPeriodComponent(this, this.card, this.db, this.paymentsPanel, new PayPeriod(res.get(i).getBudgetID(), res.get(i).getTitle())));
			container.add(Box.createVerticalStrut(10));
		}
	}
	
	private void setupContainer() {
		container.setBackground(this.backgroundColor);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
	}
	
	private void setupPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(this.backgroundColor);
		this.setMinimumSize(new Dimension(600,500));
		this.add(Box.createVerticalStrut(10));
		this.add(this.addBtn);
		this.add(container,BorderLayout.CENTER);
	}
}
