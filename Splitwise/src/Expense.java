import java.util.Date;
import java.util.List;

public abstract class Expense {
	
private String label;
private double amount;
private User paidBy;
private Date createdAt;
private List<Split>splits;


public Expense(String label, double amount, User paidBy, Date createdAt, List<Split> splits) {
	this.label = label;
	this.amount = amount;
	this.paidBy = paidBy;
	this.createdAt = createdAt;
	this.splits = splits;
}


public String getLabel() {
	return label;
}


public void setLabel(String label) {
	this.label = label;
}


public double getAmount() {
	return amount;
}


public void setAmount(double amount) {
	this.amount = amount;
}


public User getPaidBy() {
	return paidBy;
}


public void setPaidBy(User paidBy) {
	this.paidBy = paidBy;
}


public Date getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}


public List<Split> getSplits() {
	return splits;
}


public void setSplits(List<Split> splits) {
	this.splits = splits;
}

public abstract boolean validate();



}
