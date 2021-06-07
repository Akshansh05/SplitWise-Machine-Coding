import java.util.*;
public class ExpenseService {
	
	public static Expense createExpense(ExpenseType type,List<Split> splits,double amount,String label,User paidBy){
		
		switch(type){
			case EQUAL: {
				
				int totalSplitSize = splits.size();
				
				double splitamount = amount /totalSplitSize;

				for(Split s :splits) {
					s.setAmount(splitamount);
				}
				splits.get(0).setAmount(splitamount+ (amount - (totalSplitSize*splitamount)));
				return new EqualExpense(label,amount,paidBy,new Date(),splits);
				
			}
			case EXACT:{
				return new ExactExpense(label,amount,paidBy,new Date(),splits);	
			}
			case PERCENT:{
				int totalSplitSize = splits.size();
				for(Split s :splits) {
					PercentageSplit ob = (PercentageSplit)s;
					double splitamount =(double)(ob.getPercent()/100)* amount;
					ob.setAmount(splitamount);
				}
				return new PercentageExpense(label,amount,paidBy,new Date(),splits);	

				
			}
			default:{
				return null;
			}

	}
	
	}
}
