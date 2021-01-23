package finalProject.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTextField;

import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class SearchPaymentsComponent extends JTextField{
	
	public SearchPaymentsComponent(PaymentsPanel p) {
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField search = (JTextField) e.getSource();
				String text = search.getText();
				try {
					p.resetPayments(text);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
