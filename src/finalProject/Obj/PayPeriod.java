package finalProject.Obj;

import javax.swing.JPanel;

public class PayPeriod {
	private int budgetID;
	private String title;
	private JPanel paymentPanel;
	
	
	public PayPeriod(int bID, String title) {
		this.budgetID = bID;
		this.title = title;
		this.paymentPanel = new JPanel();
	}
	public String getTitle() {
		return this.title;
	}
	public int getBudgetID() {
		return budgetID;
	}
	
	public JPanel getPaymentPanel() {
		return paymentPanel;
	}
	
}
