package finalProject.Obj;

public class PaymentsData {
	private int pID;
	private int bID;
	private String title;
	private String inOut;
	private int quantity;
	
	public PaymentsData(int pID, int bID, String title, String in, int quantity) {
		this.pID = pID;
		this.bID = bID;
		this.title = title;
		this.inOut = in;
		this.quantity = quantity;
	}
	
	public int getPID() {
		return this.pID;
	}
	
	public int getBID() {
		return this.bID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getInOut() {
		return this.inOut;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getActualQuantity() {
		if(inOut.equals("incoming")) {
			return quantity;
		}else {
			return -quantity;
		}
	}
}
