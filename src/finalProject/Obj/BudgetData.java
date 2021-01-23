package finalProject.Obj;

public class BudgetData {
	private int budgetID;
	private String title;
	
	public BudgetData(int id, String title) {
		this.budgetID = id;
		this.title = title;
	}
	
	public int getBudgetID() {
		return this.budgetID;
	}
	
	public String getTitle() {
		return this.title;
	}
}
