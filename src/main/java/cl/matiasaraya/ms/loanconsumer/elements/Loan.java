package cl.matiasaraya.ms.loanconsumer.elements;

public class Loan {

	private int id;
	private String type;
	private int debtAmount;
	
	public Loan() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDebtAmount() {
		return debtAmount;
	}

	public void setDebtAmount(int debtAmount) {
		this.debtAmount = debtAmount;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true; 
		return (this.getId() == ((Loan) obj).getId()
				&& this.getType().equals(((Loan) obj).getType())
				&& this.getDebtAmount() == ((Loan) obj).getDebtAmount()); 
	}
	

}
