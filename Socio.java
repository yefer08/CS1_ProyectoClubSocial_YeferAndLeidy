/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsocial;

/**
 *
 * @author yanca
 */
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;

public class Socio {
    Scanner sc = new Scanner(System.in);
    private String name;
    private String id;
    private int availableFunds; 
    private String subscription; 
    private int pendingInvoices;
    private int listOfPeople;
    private HashSet<String> namesofassociates;
    private ArrayList<Socio> userList;
    

    public Socio() {
        this.namesofassociates = new HashSet<>();
        this.userList = new ArrayList<>();
        this.pendingInvoices = 0;
        this.availableFunds = 0;
    }

    public Socio(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Socio(String name, String id, int availableFunds, String subscription, int pendingInvoices) {
        this.name = name;
        this.id = id;
        this.availableFunds = availableFunds;
        this.subscription = subscription;
        this.pendingInvoices = pendingInvoices;
    }

    
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
 
    public int getPendingInvoices() {
        return pendingInvoices; 
    }
 
    public void setPendingInvoices(int pendingInvoices) { 
        this.pendingInvoices = pendingInvoices; 
    }
 
    public int getListOfPeople() {
        return listOfPeople; 
    }
 
    public void setListOfPeople(int listOfPeople) { 
        this.listOfPeople = listOfPeople; 
    }

    

    public void EnterNameAndId() {
        System.out.println("Enter the user's name:");
        String name = sc.nextLine();
        System.out.println("Enter the user's ID:");
        String id = sc.nextLine();
    
        for (Socio socio : userList) {
            if (socio.getId().equals(id)) {
                System.out.println("El id usuario ya existe, ingrese por favor otro.");
                return;
            }
        }
        Socio newUser = new Socio(name, id);
        userList.add(newUser);
        System.out.println("User added: " + name);
    }

        
    

    public void showUsers() {
        System.out.println("User List:");
        for (Socio socio : userList) {
            System.out.println("Name: " + socio.getName() + ", ID: " + socio.getId());
        }
    }
    
    
    int Tpregu = 1000000;
    int Tpvip = 5000000;   
    public void AvailableFunds(Scanner sc) {
        System.out.println("ENTER SUBSCRIPTION VALUE: ");
        this.availableFunds = sc.nextInt();
    
        if (this.availableFunds < 50000) {
            System.out.println("--NO FUNDS--");
        } else if (this.availableFunds >= 50000 && this.availableFunds < 100000) {
            System.out.println("--REGULAR--");
    
            
            System.out.println("Do you want to add new funds? (yes/no)");
            String respuesta = sc.next(); 
    
            if (respuesta.equalsIgnoreCase("yes")) {
                System.out.println("Enter your new funds (up to 1,000,000):");
                int newFunds = sc.nextInt();
                if (newFunds > Tpregu ) {
                    System.out.println("Your funds exceed the allowed limit.");
                    }else if(newFunds<Tpregu){
                        if(this.availableFunds < Tpregu){
                            this.availableFunds += newFunds;
                             System.out.println("Funds added successfully. Total funds: " + this.availableFunds);
                    }else{
                        System.out.println("You cannot add funds because the available funds already exceed the limit.");
                    }
                } else {
                    System.out.println("Your funds exceed the allowed limit.");
                }
            } else {
                System.out.println("OK, thanks....");
            }
        } else if (this.availableFunds >= 100000 && this.availableFunds <5000000) {
            System.out.println("--VIP--");
    
            
            System.out.println("Do you want to add new funds? (yes/no)");
            String respuesta = sc.next(); 
    
            if (respuesta.equalsIgnoreCase("yes")) {
                System.out.println("Enter your new funds (up to 5,000,000):");
                int newFunds = sc.nextInt();
                if (newFunds > Tpvip) {
                    System.out.println("Your funds exceed the allowed limit.");
                }else if (newFunds < Tpvip){
                    if(this.availableFunds < Tpvip){
                        this.availableFunds += newFunds; 
                        System.out.println("Funds added successfully. Total funds: " + this.availableFunds);
                    }else{
                    System.out.println("You cannot add funds because the available funds already exceed the limit.");
                    }
                }else {
                    System.out.println("Your funds exceed the allowed limit.");
                }
                }else {
                System.out.println("OK, thanks....");
            }
            }else {
            System.out.println("--UNRECOGNIZED AMOUNT--");
        }
    }
    public void PendingInvoices(Scanner sc){
        int acom= 0;
        if (pendingInvoices == acom){
            System.out.println("--YOUR ACCOUNT IS-- "+ acom);
        }
    }
    public void ListOfPeople(Scanner sc){
        System.out.println("Enter the names of associates (max 10):");
        while (namesofassociates.size() < 10) {
            System.out.println("Enter a name (or type 'exit' to finish):");
            String nickname = sc.nextLine();
            if (nickname.equalsIgnoreCase("exit")) {
                break; 
            }
            if (namesofassociates.contains(nickname)) {
                System.out.println("The name is already registered. Please enter another.");
            } else {
                namesofassociates.add(nickname);  
            }
        }
 
        System.out.println("registered users: ");
        System.out.println(namesofassociates);
    }
 
    

 
    
    public void ShowInfoPartner() {
        System.out.println("===== Info Socio =====");
        if (userList.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("User List:");
            for (Socio socio : userList) { 
                System.out.println("Name: " + socio.getName() + ", ID: " + socio.getId());
                System.out.println("Funds: " + this.availableFunds);
                System.out.println("Registered Users: " + (namesofassociates.isEmpty() ? "No users registered" : namesofassociates));
                System.out.println("Subscription: " + this.subscription);
            }
        }
    }
}
      // Method to display the list of users
      

    /*public void Ingresodeusuario(){
        System.out.println("ENTER YOUR NAME AND LAST NAME: ");
        String name1 = sc.nextLine();
        if (nombres.contains(name1)) {
            System.out.println("El usuario ya está registrado.");
            return;
            System.out.println("Ingresa los nombres de asociados (máximo 10):");
            while (nombres.size() < 10) {
                System.out.println("Ingresa un nombre (o escribe 'salir' para terminar):");
                String nombre = sc.nextLine();
                if (nombre.equalsIgnoreCase("salir")) {
                    break; 
                }
                if (nombres.contains(nombre)) {
                    System.out.println("El nombre ya está registrado. Por favor, ingresa otro.");
                } else {
                    nombres.add(nombre); 
                }
        } 
    }
}*/

