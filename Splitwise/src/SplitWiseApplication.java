import Blogic.Split.EqualSplit;
import Blogic.Split.ExactSplit;
import Blogic.Split.PercentageSplit;
import Blogic.Split.Split;
import Manager.ExpenseManager;
import Model.Expense.ExpenseType;
import Model.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitWiseApplication {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addUser(new User("u1", "Madhuri Sharma", "madhu@gmail.com", "9334233085"));
        expenseManager.addUser(new User("u2", "Ashok Sinha", "ashok@gmail.com", "7004041877"));
        expenseManager.addUser(new User("u3", "Akshata Ohm", "omi@gmail.com", "8660923088"));
        expenseManager.addUser(new User("u4", "Akshansh Ohm", "ishu@gmail.com", "8076006113"));


        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1 for add expense 2 for view balance");
            System.out.println();
            String input = sc.nextLine();
            switch (input) {

                case "1": {
                    createExpense(expenseManager, sc);
                }

                case "2": {
                    showBalance(expenseManager, sc);
                    break;

                }
                default: {
                    System.out.println("Invalid Entry");
                }

            }
            System.out.println();
        }


    }

    private static void showBalance(ExpenseManager em, Scanner sc) {
        System.out.println();
        System.out.println("Enter UserId of a user or All for all users ");
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("All")) {
            em.showAllExpenses();

        } else {
            em.showExpense(input);
        }
    }

    private static void createExpense(ExpenseManager em, Scanner sc) {
        System.out.println();
        System.out.println("Enter Expense Statement");
        String input[] = sc.nextLine().split(" ");


        String paidBy = input[0];
        System.out.println(paidBy);
        double amount = Double.parseDouble(input[1]);
        System.out.println(amount);
        int noOfusers = Integer.parseInt(input[2]);
        System.out.println(noOfusers);
        String type = input[3 + noOfusers];
        System.out.println(type);
        List<Split> splits = new ArrayList<Split>();

        switch (type) {
            case "EQUAL": {
                for (int i = 0; i < noOfusers; i++) {
                    splits.add(new EqualSplit(em.getUserMap().get(input[3 + i]), amount));
                }
                em.addExpense(ExpenseType.EQUAL, splits, amount, "", paidBy);
                break;
            }
            case "EXACT": {

                for (int i = 0; i < noOfusers; i++) {
                    splits.add(new ExactSplit(em.getUserMap().get(input[3 + i]), amount));
                }
                em.addExpense(ExpenseType.EXACT, splits, amount, "", paidBy);
                break;
            }
            case "PERCENT": {
                for (int i = 0; i < noOfusers; i++) {
                    splits.add(new PercentageSplit(em.getUserMap().get(input[3 + i]), amount, Double.parseDouble(input[4 + noOfusers + i])));
                }
                em.addExpense(ExpenseType.PERCENT, splits, amount, "", paidBy);
                break;
            }
            default: {
                System.out.println("Invalid Input Type");
                System.out.println();
                break;
            }

        }
    }

    //u1 100 2 u2 u3 EXACT
    //u1 100 2 u2 u3 EQUAL
    //u1 1200 4 u1 u2 u3 u4 PERCENT 25 35 30 10
}
