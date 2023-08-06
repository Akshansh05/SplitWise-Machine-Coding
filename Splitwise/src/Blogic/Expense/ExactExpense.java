package Blogic.Expense;

import Blogic.Split.ExactSplit;
import Blogic.Split.Split;
import Model.User.User;

import java.util.Date;
import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(String label, double amount, User paidBy, Date createdAt, List<Split> splits) {
        super(label, amount, paidBy, createdAt, splits);
    }

    public boolean validate() {

        for (Split split : super.getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
        return true;
    }

}
