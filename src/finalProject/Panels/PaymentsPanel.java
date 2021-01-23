package finalProject.Panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import finalProject.Database.DatabaseHandler;
import finalProject.Obj.Payment;
import finalProject.Obj.PaymentsData;
import finalProject.components.AddPaymentsComponent;
import finalProject.components.PaymentComponent;

@SuppressWarnings("serial")
public class PaymentsPanel extends JPanel{
	private DatabaseHandler db;
	private int bID;
	private JPanel container;
	private AddPaymentsComponent addBtn;
	private Color backgroundColor;
	private JLabel finalAmt;
	private JLabel quantity;
	private JPanel quantityPanel;
	private CardLayout card;
	
	public PaymentsPanel(CardLayout card) throws ClassNotFoundException, SQLException {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setMinimumSize(new Dimension(600,500));
		this.db = new DatabaseHandler();
		this.backgroundColor = new Color(242,204,143);
		this.addBtn = new AddPaymentsComponent(this.bID, this.db,this);
		this.card = card;
		this.finalAmt = new JLabel("Budget: ");
		this.quantity = new JLabel("");
		this.quantityPanel = new JPanel();
		this.container = new JPanel();
		this.container.setBackground(this.backgroundColor);
		this.container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		this.setBackground(this.backgroundColor);
		this.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setQuantityPanel();
		add(addBtn);
		add(container);
		add(quantityPanel);
		bID = 0;
		setPayments("");
	}
	
	public void setbID(int bID) {
		this.bID = bID;
	}
	
	public int getbID() {
		return this.bID;
	}
	
	private void setPayments(String input) throws SQLException {
		ArrayList<PaymentsData> res = db.getPayPeriodPayments(bID,input);
		int finalCount = 0;
		container.add(Box.createVerticalStrut(10));
		for(int i = 0; i < res.size(); i++){
			Payment payment = new Payment( res.get(i).getBID(), res.get(i).getPID() , res.get(i).getTitle(), res.get(i).getInOut(), res.get(i).getQuantity() );
			container.add(new PaymentComponent(this, this.db ,payment));
			container.add(Box.createVerticalStrut(10));
			finalCount += res.get(i).getActualQuantity();
		}
		this.quantity.setText(Integer.toString(finalCount));
	}
	
	public void resetPayments(String input) throws SQLException {
		container.removeAll();
		setPayments(input);
		container.revalidate();
	}
	
	private void setQuantityPanel() {
		this.quantityPanel.setBackground(this.backgroundColor);
		this.quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
		this.quantityPanel.add(this.finalAmt);
		this.quantityPanel.add(this.quantity);
	}
	public void back() {
		Container parent = this.getParent();
		card.previous(parent);
	}
	
	
	public DatabaseHandler getDB() {
		return this.db;
	}
}
