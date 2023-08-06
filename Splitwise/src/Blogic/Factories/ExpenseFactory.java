package Blogic.Factories;

import Blogic.Expense.EqualExpense;
import Blogic.Expense.ExactExpense;
import Blogic.Expense.Expense;
import Blogic.Expense.PercentageExpense;
import Blogic.Split.PercentageSplit;
import Blogic.Split.Split;
import Model.Expense.ExpenseType;
import Model.User.User;

import java.util.Date;
import java.util.List;

public class ExpenseFactory {

    public static Expense createExpense(ExpenseType type, List<Split> splits, double amount, String label, User paidBy) {

        switch (type) {
            case EQUAL: {

                int totalSplitSize = splits.size();

                double splitamount = amount / totalSplitSize;

                for (Split s : splits) {
                    s.setAmount(splitamount);
                }
                splits.get(0).setAmount(splitamount + (amount - (totalSplitSize * splitamount)));
                return new EqualExpense(label, amount, paidBy, new Date(), splits);

            }
            case EXACT: {
                return new ExactExpense(label, amount, paidBy, new Date(), splits);
            }
            case PERCENT: {
                int totalSplitSize = splits.size();
                for (Split s : splits) {
                    PercentageSplit ob = (PercentageSplit) s;
                    double splitamount = (double) (ob.getPercent() / 100) * amount;
                    ob.setAmount(splitamount);
                }
                return new PercentageExpense(label, amount, paidBy, new Date(), splits);


            }
            default: {
                return null;
            }

        }

    }
}
