/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsocial;


/**
 *
 * @author yanca
 */
public class Affiliates extends Socio {
    String lastName;

    public Affiliates(String lastName, String name, String id, int Availablefunds, String Subscription, int PendingInvoices, int ListOfPeople) {
        super(name, id, Availablefunds, Subscription, PendingInvoices, ListOfPeople);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void mostrarInfoAsociado() {
        System.out.println("===== Info Asociado =====");
        System.out.println("Nombre: " + this.getName());
        System.out.println("ID: " + this.getId());
        System.out.println("Suscripcion: " + this.getSubscription());
    }
}

         
         
    
        
    
