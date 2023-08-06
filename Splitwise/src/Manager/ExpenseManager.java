package Manager;

import Blogic.Expense.Expense;
import Blogic.Factories.ExpenseFactory;
import Blogic.Split.Split;
import Model.Expense.ExpenseType;
import Model.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private List<Expense> expenses;
    private HashMap<String, User> userMap;
    private HashMap<User, HashMap<User, Double>> balanceSheet;


    public ExpenseManager() {
        this.expenses = new ArrayList<Expense>();
        this.userMap = new HashMap<String, User>();
        this.balanceSheet = new HashMap<User, HashMap<User, Double>>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user, new HashMap<User, Double>());
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void addExpense(ExpenseType expenseType, List<Split> splits, double amount, String label, String paidBy) {

        User paiduser = userMap.get(paidBy);
        if (paiduser == null) {
            System.out.println("No such User which paid ");
        }
        Expense newExpense = ExpenseFactory.createExpense(expenseType, splits, amount, label, paiduser);

        if (newExpense == null) {
            System.out.println("Error occured in creating Blogic.Expense.Expense ");
            return;

        }

        expenses.add(newExpense);

        for (Split sp : splits) {
            User paidTo = sp.getUser();

            if (paidTo == null) {
                System.out.println("No such user Who Owes");
            }
            HashMap<User, Double> sheetOfUserpaiduser = balanceSheet.get(paiduser);

            if (!sheetOfUserpaiduser.containsKey(paidTo)) {
                sheetOfUserpaiduser.put(paidTo, 0.0);
            }
            sheetOfUserpaiduser.put(paidTo, sheetOfUserpaiduser.get(paidTo) + sp.getAmount());


            HashMap<User, Double> sheetOfUserpaidTo = balanceSheet.get(paidTo);

            if (!sheetOfUserpaidTo.containsKey(paiduser)) {
                sheetOfUserpaidTo.put(paiduser, 0.0);
            }

            double val = sheetOfUserpaidTo.get(paiduser) - sp.getAmount();
            sheetOfUserpaidTo.put(paiduser, val);


        }

    }

    public void showExpense(String userName) {
        User user = userMap.get(userName);
        if (user == null) {
            System.out.println("User does not Exists ");
            return;
        }
        System.out.println("**** Balance sheet of user " + user.getName());


        HashMap<User, Double> balance = balanceSheet.get(user);

        if (balance.size() == 0) {
            System.out.println("No balance for a user " + user.getName());
        }


        for (Map.Entry<User, Double> entry : balance.entrySet()) {

            if ((User) entry.getKey() != user && entry.getValue() > 0) {

                String val = String.format("%.2f", entry.getValue());

                System.out.println(user.getName() + " paid " + entry.getKey().getName() + " an Amount of Rs " + val);

            } else if ((User) entry.getKey() != user && entry.getValue() < 0) {

                String val = String.format("%.2f", Math.abs((double) entry.getValue()));

                System.out.println(user.getName() + " owes " + entry.getKey().getName() + " an Amount of Rs " + val);
            }
        }


    }

    public void showAllExpenses() {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            //System.out.print((String)entry.getKey());

            showExpense((String) entry.getKey());

            System.out.println();
            System.out.println();

        }

    }

}
