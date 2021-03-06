package Main.UserInterface;


import Main.Domain.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterfaceImpl implements UserInterface {
    Integer command = 0;
    Map<String, Object> request = new HashMap<>();

   public UserInterfaceImpl () {
        System.out.println("Hi! This is Expenses Resolver.");
        System.out.println("Here you can add purchases and resolve transactions!");
        System.out.println("----------------------------------------------------");
    }

    @Override
    public void openMenu() {
        /**
         * Prints out command (action)options
         * Depending on command, updates request object (Map, basicaly <dataName,data>)
         * */
        System.out.println("----------------------------------------------------");
        System.out.println("To add purchase press 1.");
        System.out.println("To resolve transactions press 2.");
        System.out.println("To reset press 3.");
        System.out.println("To exit press 4.");
        System.out.println("Please enter command.");

        Scanner scanner = new Scanner(System.in);
        command = scanner.nextInt();

        switch (command) {
            case 1:
                System.out.println("----------------------------------------------------");
                request.put("command", "ADD_PURCHASE");
                System.out.println("Please enter friends name.");
                String frinedName = scanner.next();
                System.out.println("Enter what he paid for.");
                String description = scanner.next();
                System.out.println("Enter purchase cost.");
                String costString = scanner.next();
                Double cost = Double.parseDouble(costString);
                Map<String, Object> purchaseData = new HashMap<>();
                purchaseData.put("friendName", frinedName);
                purchaseData.put("purchaseDescription", description);
                purchaseData.put("cost", cost);
                request.put("purchaseData", purchaseData);
                break;

            case 2:
                request.put("command", "RESOLVE");
                break;
            case 3:
                request.put("command", "RESET");
                break;
        }
    }

    @Override
    public Map<String, Object> getRequest() {

        return this.request;
    }

    @Override
    public void processResponse(Map<String, Object> response) {
        /**
         * Gets response object as parameter which is the same type as request (Map<dataName,data>)
         * Depending on command (which comes in respos) represents received data in console.
         * */

        String command = (String) response.get("command");
        switch (command) {
            case "ADD_PURCHASE":
                System.out.println("----------------------------------------------------");
                System.out.println(response.get("status"));
                System.out.println("Total amount = " + response.get("total"));
                System.out.println("Amount each friend has to pay = " + response.get("average"));
                break;
            case "RESOLVE":

                System.out.println("----------------------------------------------------");
                System.out.println("Expenses:");
                Map <String,Double> expenses = (Map<String, Double>) response.get("expenses");
                for (Map.Entry<String,Double> entry: expenses.entrySet()){
                    System.out.println(entry.getKey()+" paid:" + entry.getValue());
                }
                System.out.println("----------------------------------------------------");
                System.out.println("Transactions to be made:");
                List <Transaction> transactions = (List<Transaction>) response.get("transactions");
                for (Transaction transaction: transactions){
                    System.out.println(transaction);
                }
                break;
            case "RESET":
                System.out.println("----------------------------------------------------");
                System.out.println(response.get("status"));
        }

    }
    @Override
    public Integer getCommand(){
        /**Method to pass command to Aplication class, to be able to run aplication until command is 4 (exit) */
        return command;
    }
}
