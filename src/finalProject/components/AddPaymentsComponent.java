package finalProject.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import finalProject.Database.DatabaseHandler;
import finalProject.Frames.AddPaymentsFrame;
import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class AddPaymentsComponent extends AddComponent{
	private int bID;
	private AddPaymentsFrame frame;
	private PaymentsPanel payPanel;
	private SearchPaymentsComponent search;
	private JButton back;
	
	public AddPaymentsComponent(int bID,DatabaseHandler handler, PaymentsPanel paymentsPanel) {
		super(handler);
		this.bID = bID;
		this.payPanel = paymentsPanel;
		this.frame = new AddPaymentsFrame(handler, paymentsPanel);
		this.search  = new SearchPaymentsComponent(payPanel);
		this.back = new JButton("Back");
		this.back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paymentsPanel.back();
			}
			
		});
		add(search, BorderLayout.CENTER);
		add(back, BorderLayout.WEST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showInsertWindow() throws SQLException {
		// TODO Auto-generated method stub
		this.setNextBID();
		frame.setBID(this.bID);
		frame.setVisible(true);
	}

}
