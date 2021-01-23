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
import finalProject.Obj.PayPeriod;
import finalProject.Panels.HomePanel;
import finalProject.components.PayPeriodComponent;

@SuppressWarnings("serial")
public class DeletePayPeriodFrame extends JFrame{
	private DatabaseHandler db;
	private JPanel container;
	private JButton submit;
	private JButton cancel;
	private Color backgroundColor;
	private JLabel label;
	private HomePanel home;
	private PayPeriodComponent payComp;
	private PayPeriod pay;
	private GroupLayout layout;
	
	public DeletePayPeriodFrame(HomePanel home, DatabaseHandler db, PayPeriodComponent payPeriod) {
		this.db = db;
		this.home = home;
		this.backgroundColor = new Color(244, 241, 222);
		this.payComp = payPeriod;
		this.pay = this.payComp.getPayPeriod();
		this.container = new JPanel();
		this.layout = new GroupLayout(container);
		this.submit = new JButton("Confirm");
		this.cancel = new JButton("Cancel");
		this.label = new JLabel("Would you like to delete "+this.pay.getTitle()+"?");
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
		this.setTitle("Delete Pay Period");
		this.setLocationRelativeTo(null);
	}
	
	private void setupPanel() {
		setButtonActions();
		setComponents();
		container.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 0));
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
				DeletePayPeriodFrame frame = (DeletePayPeriodFrame) rootPane.getParent();
				try {
					PayPeriod pay = frame.getPayPeriod();
					db.deletePayPeriods(pay.getBudgetID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				try {
					frame.home.resetPayPeriods("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void setComponents() {
		this.label.setBorder(BorderFactory.createEmptyBorder(0, 75, 10, 75));
	}
	
	private PayPeriod getPayPeriod() {
		return this.pay;
	}
}
