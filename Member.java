/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author yanca
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Member extends SocialClub {
    private static final int MAX_USERS = 35; // Limit of total users
    private static final int MAX_VIP = 3; // Limit of VIP users
    private static final int REGULAR_LIMIT = 1000000; // Max funds for Regular
    private static final int VIP_LIMIT = 5000000; // Max funds for VIP
    private static int vipCount = 0; // VIP user counter


    protected HashSet<String> namesOfAssociates;
    private Invoices invoices;
    private static ArrayList<Member> userList = new ArrayList<>(); // Static user list

    public Member() {
        this.namesOfAssociates = new HashSet<>();
        this.availableFunds = 0;
        
    }

    public Member(String name, String id) {
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Initialize the user's invoice
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(int availableFunds) {
        this.availableFunds = availableFunds;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Invoices getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    // Method to enter name and ID
    public void enterNameAndId(Scanner sc) {
        if (userList.size() >= MAX_USERS) {
            System.out.println("The maximum number of users (35) has been reached. Cannot add more users.");
            return;
        }

        System.out.println("Enter the user's name:");
        String name = sc.nextLine();
        System.out.println("Enter the user's ID:");
        String id = sc.nextLine();

        // Validate unique ID
        for (Member member : userList) {
            if (member.getId().equals(id)) {
                System.out.println("The ID already exists, please enter another.");
                return;
            }
        }

        Member newUser = new Member(name, id);
        userList.add(newUser);
        System.out.println("User added: " + name);
    }

    // Show users along with their pending invoices
    @Override
    public void showUsers() {
        System.out.println("List of Users:");
        for (Member member : userList) {
            System.out.println("Name: " + member.getName() + ", ID: " + member.getId() + ", Subscription: " + this.subscription +
            ", Pending Invoices: " + member.getInvoices().getPendingInvoices() +  "remaining value: " + this.getAvailableFunds());
        }
    }

    // Manage funds and subscription
    @Override
    public void availableFunds(Scanner sc) {
        System.out.println("ENTER SUBSCRIPTION AMOUNT: ");
        this.availableFunds = sc.nextInt();
        sc.nextLine(); // Clear the buffer

        // Check if the amount is below the minimum required for any subscription
        if (this.availableFunds < 50000) {
            System.out.println("--NO FUNDS--");
            return;
        }

        // Regular subscription: amount between 50,000 and 1,000,000
        if (this.availableFunds >= 50000 && this.availableFunds <= REGULAR_LIMIT) {
            if (this.availableFunds == REGULAR_LIMIT) {
                System.out.println("You have reached the maximum limit for REGULAR subscription: 1,000,000");
            }
            this.subscription = "REGULAR";
            System.out.println("--REGULAR SUBSCRIPTION--");
        }
        // VIP subscription: amount between 100,000 and 5,000,000
        else if (this.availableFunds >= 100000 && this.availableFunds <= VIP_LIMIT) {
            if (vipCount >= MAX_VIP) {
                System.out.println("Cannot add more VIP members. The limit of 3 VIP members has been reached.");
                return;
            }
            if (this.availableFunds == VIP_LIMIT) {
                System.out.println("You have reached the maximum limit for VIP subscription: 5,000,000");
            }
            this.subscription = "VIP";
            vipCount++; // Increment VIP count
            System.out.println("--VIP SUBSCRIPTION--");
        }
        // Exceeds the limit for any subscription
        else if (this.availableFunds > VIP_LIMIT) {
            System.out.println("Amount exceeds the maximum limit for VIP subscription (5,000,000). Please enter a valid amount.");
            return;
        }

        // Handle additional funds within limits
        handleFunds(sc);
    }

    // Handle adding more funds
    private void handleFunds(Scanner sc) {
        System.out.println("Would you like to add new funds? (yes/no)");
        String response = sc.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new amount to add: ");
            int newFunds = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            // Ensure that added funds don't exceed the limit for Regular users
            if (this.subscription.equals("REGULAR")) {
                if (this.availableFunds + newFunds > REGULAR_LIMIT) {
                    System.out.println("Cannot exceed $1,000,000 for REGULAR members.");
                    return;
                }
            }

            // Ensure that added funds don't exceed the limit for VIP users
            if (this.subscription.equals("VIP")) {
                if (this.availableFunds + newFunds > VIP_LIMIT) {
                    System.out.println("Cannot exceed $5,000,000 for VIP members.");
                    return;
                }
            }

            // Add funds if within limits
            this.availableFunds += newFunds;
            System.out.println("Funds added. Total funds: " + this.availableFunds);
        } else {
            System.out.println("No additional funds added.");
        }
    }

}
