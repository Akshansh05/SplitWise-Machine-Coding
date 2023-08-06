package Blogic.Expense;

import Blogic.Split.PercentageSplit;
import Blogic.Split.Split;
import Model.User.User;

import java.util.Date;
import java.util.List;

public class PercentageExpense extends Expense {

    public PercentageExpense(String label, double amount, User paidBy, Date createdAt, List<Split> splits) {
        super(label, amount, paidBy, createdAt, splits);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub

        for (Split splits : super.getSplits()) {
            if (!(splits instanceof PercentageSplit)) {
                return false;
            }
        }
        return true;
    }

}
