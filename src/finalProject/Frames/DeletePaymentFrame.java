package finalProject.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import finalProject.Database.DatabaseHandler;
import finalProject.Obj.Payment;
import finalProject.Panels.PaymentsPanel;
import finalProject.components.PaymentComponent;

@SuppressWarnings("serial")
public class DeletePaymentFrame extends JFrame {
	
	private DatabaseHandler db;
	private JPanel container;
	private JButton submit;
	private Color backgroundColor;
	private JButton cancel;
	private JLabel label;
	private PaymentsPanel payPanel;
	private PaymentComponent paymentComp;
	private Payment payment;
	private GroupLayout layout;
	
	public DeletePaymentFrame(PaymentsPanel panel, DatabaseHandler handler, PaymentComponent payment) {
		this.db = handler;
		this.payPanel = panel;
		this.backgroundColor = new Color(244, 241, 222);
		this.container = new JPanel();
		this.layout = new GroupLayout(container);
		this.paymentComp = payment;
		this.payment = this.paymentComp.getPayment();
		this.submit = new JButton("Confirm");
		this.cancel = new JButton("Cancel");
		this.label = new JLabel("Would you like to delete payment "+this.payment.getTitle()+"?");
		setupFrame();
	}
	
	private void setupFrame() {
		setupPanel();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 400, 200);
		this.setBackground(this.backgroundColor);
		this.setLayout(new BorderLayout());
		this.add(this.container,BorderLayout.CENTER);
		this.setResizable(false);
		this.setTitle("Delete Payment");
		this.setLocationRelativeTo(null);
	}
	
	private void setupPanel() {
		setComponents();
		setButtonActions();
		container.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(cancel)
						.addComponent(submit))
				);
		container.setBackground(this.backgroundColor);
	}
	
	private void setButtonActions() {
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn = (JButton) e.getSource();
				JPanel panel = (JPanel) btn.getParent();
				JPanel panel2 = (JPanel) panel.getParent();
				JLayeredPane pane = (JLayeredPane) panel2.getParent();
				JRootPane rootPane = (JRootPane) pane.getParent();
				JFrame frame = (JFrame) rootPane.getParent();
				frame.dispose();
			}
			
		});
		this.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn = (JButton) e.getSource();
				JPanel panel = (JPanel) btn.getParent();
				JPanel panel2 = (JPanel) panel.getParent();
				JLayeredPane pane = (JLayeredPane) panel2.getParent();
				JRootPane rootPane = (JRootPane) pane.getParent();
				DeletePaymentFrame frame = (DeletePaymentFrame) rootPane.getParent();
				try {
					db.deletePayment(frame.payment.getPaymentID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				try {
					frame.payPanel.resetPayments("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void setComponents() {
		this.label.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	}

}
