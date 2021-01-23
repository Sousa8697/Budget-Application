package finalProject.components;

import java.awt.BorderLayout;
import java.sql.SQLException;

import finalProject.Database.DatabaseHandler;
import finalProject.Frames.AddPayPeriodFrame;
import finalProject.Panels.HomePanel;

@SuppressWarnings("serial")
public class AddPayPeriodsComponent extends AddComponent{
	
	private AddPayPeriodFrame frame;
	private HomePanel home;
	private SearchPayPeriodsComponent search;
	public AddPayPeriodsComponent(DatabaseHandler handler, HomePanel home) {
		super(handler);
		this.home = home;
		this.frame = new AddPayPeriodFrame(this.db, this.home);
		this.search  = new SearchPayPeriodsComponent(this.home);
		add(search, BorderLayout.CENTER);
	}

	@Override
	public void showInsertWindow() throws SQLException {
		// TODO Auto-generated method stub
		this.setNextBID();
		frame.setBID(this.bID);
		frame.setVisible(true);
		
	}

}
