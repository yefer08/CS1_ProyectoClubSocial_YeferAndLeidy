/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubsocial;

import java.util.Scanner;

public class SocialClub {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Member member = new Member();
        int option;

        do {
            System.out.println("\n==== MENU OPTIONS ====");
            System.out.println("1. Enter Name and ID"); 
            System.out.println("2. Enter Funds");
            System.out.println("3. Enter Associate Names");
            System.out.println("4. View Pending Invoices");
            System.out.println("5. Show Member Information");
            System.out.println("6. Show Users");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); // Clear the input buffer

            switch (option) {
                case 1:
                    member.enterNameAndId(sc);
                    break;
                case 2:
                    member.availableFunds(sc);
                    break;
                case 3:
                    member.listOfPeople(sc);
                    break;
                case 4:
                    member.pendingInvoices();
                    break;
                case 5:
                    member.showInfoMember();
                    break;
                case 6:
                    member.showUsers(); 
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("INVALID OPTION, PLEASE TRY AGAIN.");
                    break;
            }
        } while (option != 0);
        sc.close(); 
    }
}
