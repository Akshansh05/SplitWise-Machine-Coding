package Blogic.Expense;

import Blogic.Split.EqualSplit;
import Blogic.Split.Split;
import Model.User.User;

import java.util.Date;
import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(String label, double amount, User paidBy, Date createdAt, List<Split> splits) {
        super(label, amount, paidBy, createdAt, splits);
    }

    public boolean validate() {

        for (Split split : super.getSplits()) {
            if (!(split instanceof EqualSplit))
                return false;
        }

        return true;

    }

}
