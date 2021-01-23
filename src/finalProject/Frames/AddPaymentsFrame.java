package finalProject.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import finalProject.Database.DatabaseHandler;
import finalProject.Obj.PaymentsData;
import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class AddPaymentsFrame extends JFrame implements ActionListener{
	
	private DatabaseHandler db;
	private PaymentsPanel payPanel;
	private int bID;
	private JPanel panel;
	private Color backgroundColor;
	private JButton submit;
	private JTextField title;
	private JLabel titleLabel;
	private JTextField quantity;
	private JLabel quantityLabel;
	private JRadioButton incoming;
	private JRadioButton outgoing;
	private GroupLayout groupLayout;
	private ButtonGroup btnGroup;
	
	public AddPaymentsFrame(DatabaseHandler handler, PaymentsPanel payPanel){
		// TODO Auto-generated constructor stub
		this.backgroundColor = new Color(244, 241, 222);
		this.db = handler;
		this.payPanel = payPanel;
		this.bID = 0;
		this.panel = new JPanel();
		this.groupLayout = new GroupLayout(this.panel);
		this.submit = new JButton("Submit");
		this.title = new JTextField();
		this.titleLabel = new JLabel("Title:");
		this.quantity = new JTextField();
		this.quantityLabel = new JLabel("Quantity:");
		this.incoming = new JRadioButton("Incoming");
		this.outgoing = new JRadioButton("Outgoing");
		this.btnGroup = new ButtonGroup();
		setupComponents();
		setupPanel();
		setupFrame();
	}
	public void setBID(int bID) {
		// TODO Auto-generated method stub
		this.bID = bID;
	}
	
	private void setupPanel() {
		btnGroup.add(incoming);
		btnGroup.add(outgoing);
		submit.addActionListener(this);
		panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 0));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleLabel)
								.addComponent(title)
								.addComponent(quantityLabel)
								.addComponent(quantity))
						.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(incoming)
								.addComponent(outgoing)
								.addComponent(submit))
				);
		panel.setBackground(this.backgroundColor);
	}
	
	private void setupFrame() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 400, 200);
		this.setBackground(this.backgroundColor);
		this.setLayout(new BorderLayout());
		this.add(this.panel,BorderLayout.CENTER);
		this.setResizable(false);
		this.setTitle("Add Payment");
		this.setLocationRelativeTo(null);
	}
	
	private void setupComponents() {
		title.setPreferredSize(new Dimension(100,25));
		quantity.setPreferredSize(new Dimension(100,25));
		incoming.setBackground(this.backgroundColor);
		outgoing.setBackground(this.backgroundColor);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String inOut = "";
		if(!title.equals("") && !quantity.equals("") && xorInOut()){
			if(incoming.isSelected()) {
				inOut = "incoming";
			}else {
				inOut = "outgoing";
			}
			try {
				try {
					db.createPayment(getNextPID(), payPanel.getbID(), title.getText(), inOut, Integer.parseInt(quantity.getText()));
					this.dispose();
					this.title.setText("");
					this.quantity.setText("");
					this.btnGroup.clearSelection();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					payPanel.resetPayments("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int getNextPID() throws SQLException {
		ArrayList<PaymentsData> data = db.getCurrentPID();
		return data.get(0).getPID()+1;
	}
	
	private Boolean xorInOut() {
		return ((incoming.isSelected() || outgoing.isSelected()) && !(incoming.isSelected() && outgoing.isSelected()));
		
	}
}
