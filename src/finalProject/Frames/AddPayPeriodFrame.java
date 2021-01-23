package finalProject.Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import finalProject.Database.DatabaseHandler;
import finalProject.Panels.HomePanel;

@SuppressWarnings("serial")
public class AddPayPeriodFrame extends JFrame implements ActionListener{
	
		private JButton submit;
		private JLabel title;
		private JTextField input;
		private Color backgroundColor;
		private int bID;
		private JPanel panel;
		private DatabaseHandler db;
		private HomePanel home;
		
		public AddPayPeriodFrame(DatabaseHandler handler, HomePanel home) {
			this.backgroundColor = new Color(244, 241, 222);
			this.submit = new JButton("Submit");
			this.title = new JLabel("Title:");
			this.input = new JTextField();
			this.panel = new JPanel();
			this.db = handler;
			this.home = home;
			this.bID = 0;
			setupPanel();
			setupFrame();
			
		}
		
		private void setupPanel() {
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
			panel.setBackground(this.backgroundColor);
			panel.setMaximumSize(new Dimension(100,50));
			panel.add(this.title);
			panel.add(Box.createHorizontalStrut(10));
			panel.add(this.input);
			panel.add(Box.createHorizontalStrut(10));
			this.submit.addActionListener(this);
			panel.add(this.submit);
		}
		
		private void setupFrame() {
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setBounds(100, 100, 300, 80);
			this.setBackground(this.backgroundColor);
			this.setLayout(new BorderLayout());
			this.setTitle("Add Pay Period");
			this.add(this.panel,BorderLayout.CENTER);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!input.getText().equals("")) {
				try {
					db.createPayPeriod(this.bID, input.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//Set the next BID
				this.input.setText("");
				this.dispose();
				try {
					this.home.resetPayPeriods("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input.setText("");
			}
		}
		
		public void setBID(int num) {
			this.bID = num;
		}
		
		
		
}
