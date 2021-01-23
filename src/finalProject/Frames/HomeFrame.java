package finalProject.Frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import finalProject.Panels.HomePanel;
import finalProject.Panels.PaymentsPanel;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame{
	
	private CardLayout card;
	private Container c;
	private PaymentsPanel payPanel;
	private HomePanel homePanel;
	private JScrollPane pane;
	public HomeFrame() throws ClassNotFoundException, SQLException {
		this.setLayout(new BorderLayout());
		this.card = new CardLayout(0,0);
		this.c = new Container();
		this.pane = new JScrollPane(c,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.c.setLayout(card);
		this.payPanel = new PaymentsPanel(card);
		this.homePanel = new HomePanel(card, payPanel);
		this.c.add("home",homePanel);
		this.c.add("payments",payPanel);
		this.add(pane, BorderLayout.CENTER);
		this.setBounds(100, 100, 600, 500);
		this.setTitle("Budget App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.add(pane);
		
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		HomeFrame frame = new HomeFrame();
	}
}
