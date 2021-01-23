package finalProject.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTextField;

import finalProject.Panels.HomePanel;

@SuppressWarnings("serial")
public class SearchPayPeriodsComponent extends JTextField {

	public SearchPayPeriodsComponent(HomePanel h) {
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField search = (JTextField) e.getSource();
				String text = search.getText();
				try {
					h.resetPayPeriods(text);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
