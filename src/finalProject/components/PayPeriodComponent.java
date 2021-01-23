package finalProject.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import finalProject.Database.DatabaseHandler;
import finalProject.Frames.DeletePayPeriodFrame;
import finalProject.Obj.PayPeriod;
import finalProject.Panels.HomePanel;
import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class PayPeriodComponent extends JPanel implements ActionListener {
	private HomePanel home;
	private DatabaseHandler db;
	private JButton button;
	private PayPeriod budget;
	private Color backgroundColor;
	private JLabel title;
	private PaymentsPanel paymentsPanel;
	private CardLayout card;
	
	public PayPeriodComponent(HomePanel home, CardLayout card, DatabaseHandler handler, PaymentsPanel panel ,PayPeriod payPeriod) {
		this.budget = payPeriod;
		this.db = handler;
		this.home = home;
		this.backgroundColor = new Color(244, 241, 222);
		this.paymentsPanel = panel;
		this.card = card;
		this.setMaximumSize(new Dimension(400,50));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
		this.setBackground(this.backgroundColor);
		setAdditionalComponents();
		setMouseListener();
		
	}
	
	private void setAdditionalComponents() {
		this.button = new JButton("View");
		this.button.addActionListener(this);
		this.title = new JLabel(this.budget.getTitle());
		add(title, BorderLayout.WEST);
		add(button, BorderLayout.EAST);
	}
	
	public JButton getButton() {
		return this.button;
	}
	
	public JLabel getLabel() {
		return this.title;
	}
	
	private void getNextPanel() {
		//Definitely not the most effecient way to go about it but wanted to restrict to two cards
		Container parent = this.getParent();
		Container hp = parent.getParent();
		Container c = hp.getParent();
		card.next(c);
		
	}
	
	//This method gets all of the payments that belong to a specific pay period
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		paymentsPanel.setbID(budget.getBudgetID());
		try {
			paymentsPanel.resetPayments("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getNextPanel();
		
	}
	
	private void setMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				PayPeriodComponent source = (PayPeriodComponent) e.getSource();
				new DeletePayPeriodFrame(source.getHomePanel(), source.getDB(), source).setVisible(true);
			}
		});
	}
	
	public PayPeriod getPayPeriod() {
		return this.budget;
	}
	
	public DatabaseHandler getDB() {
		return this.db;
	}
	
	public HomePanel getHomePanel() {
		return this.home;
	}
	
}
