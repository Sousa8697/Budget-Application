package finalProject.Obj;

public class Payment {
	private int budgetID;
	private int paymentID;
	private String title;
	private String inOut;
	private int quantity;
	
	public Payment(int bID, int pID, String title, String inOut, int quantity) {
		this.budgetID  = bID;
		this.paymentID = pID;
		this.title = title;
		this.inOut = inOut;
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public String getInOut() {
		return inOut;
	}

	public String getQuantity() {
		if(inOut.equals("incoming")) {
			return Integer.toString(quantity);
		}else {
			return Integer.toString(-quantity);
		}
	}
	
	public int getPaymentID() {
		return this.paymentID;
	}
}
