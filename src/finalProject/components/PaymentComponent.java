package finalProject.components;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import finalProject.Database.DatabaseHandler;
import finalProject.Frames.DeletePaymentFrame;
import finalProject.Obj.Payment;
import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class PaymentComponent extends JPanel{
	
	private Payment payObj;
	private JLabel title;
	private JLabel quantity;
	private DatabaseHandler db;
	private PaymentsPanel payPanel;
	private Color backgroundColor;
	public PaymentComponent(PaymentsPanel panel, DatabaseHandler handler, Payment payObj) {
		this.payObj = payObj;
		this.payPanel = panel;
		this.backgroundColor = new Color(244, 241, 222);
		this.db = handler;
		this.setMaximumSize(new Dimension(400,50));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setBackground(this.backgroundColor);
		this.setLayout(new BorderLayout());
		setAdditionalComponents();
		setMouseListener();
	}
	
	private void setAdditionalComponents() {
		this.title = new JLabel(payObj.getTitle());
		this.quantity = new JLabel(payObj.getQuantity());
		add(title, BorderLayout.WEST);
		add(quantity, BorderLayout.EAST);
	}
	//This will implement a delete functionality
	private void setMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				PaymentComponent source = (PaymentComponent) e.getSource();
				new DeletePaymentFrame(source.getPayPanel(), source.getDB(), source).setVisible(true);
			}
		});
	}
	
	public Payment getPayment() {
		return this.payObj;
	}
	
	public DatabaseHandler getDB() {
		return this.db;
	}
	
	public PaymentsPanel getPayPanel() {
		return this.payPanel;
	}

	
}
