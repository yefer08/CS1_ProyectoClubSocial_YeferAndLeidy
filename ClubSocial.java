/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubsocial;

import java.util.Scanner;

public class ClubSocial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Socio socio = new Socio();
        int option;

        do {
            System.out.println("\n==== MENU OPTIONS ====");
            System.out.println("1. Enter Name and ID"); 
            System.out.println("2. Enter Funds");
            System.out.println("3. Enter Names of Associates");
            System.out.println("4. Check Pending Invoices");
            System.out.println("5. Show Member Information");
            System.out.println("6. Show Users");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    socio.EnterNameAndId();
                    break;
                case 2:
                    socio.AvailableFunds(sc);
                    break;
                case 3:
                    socio.ListOfPeople(sc);
                    break;
                case 4:
                    socio.PendingInvoices(sc);
                    break;
                case 5:
                    socio.ShowInfoPartner();
                    break;
                case 6:
                    socio.showUsers(); 
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("INVALID OPTION, PLEASE TRY AGAIN.");
                    break;
            }
        } while (option != 0);
    }
}

