/**
 * @Title: ICT 373 A1 
 * @Author: Khon Min Thite
 * @Date: 2/10/2024
 * @File: PayingCustomer.java
 * @Purpose: A child class of Customer that represents a paying customer
 * A paying customer has a payment method, payment detail and a list of associate customers
 * A payment method/detail could only be a specified credit card or a bank account
 * @Assumptions: 
 * @Limitations: 
 */
package customer;

import java.util.ArrayList;

import magazine.*;

public class PayingCustomer extends Customer {
    private String paymentMethod;
    private double paymentDetail;
    private ArrayList<AssociateCustomer> associateCustomers;

    /**
     * Constructor for the PayingCustomer class without associate customers
     * 
     * @param name The name of the paying customer
     * @param email The email of the paying customer
     * @param supplements The list of supplements the associate customer is interested in
     * @param paymentMethod The payment method of the paying customer
     * @param paymentDetail The payment detail of the paying customer
     */
    public PayingCustomer(String name, String email,ArrayList<Supplement> supplements, String paymentMethod, double paymentDetail) {
        super(name, email, supplements);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<>();
    }

    /**
     * Constructor for the PayingCustomer class without supplements and associate
     * customers
     * 
     * @param name The name of the paying customer
     * @param email The email of the paying customer
     * @param paymentMethod The payment method of the paying customer 
     * @param paymentDetail The payment detail of the paying customer
     */
    public PayingCustomer(String name, String email, String paymentMethod, double paymentDetail) {
        super(name, email);
        this.paymentMethod = paymentMethod;
        this.paymentDetail = paymentDetail;
        this.associateCustomers = new ArrayList<>();
    }

    /**
     * Getter for the payment method of the paying customer
     * 
     * @return The payment method of the paying customer
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Getter for the payment detail of the paying customer
     * 
     * @return The payment detail of the paying customer
     */
    public double getPaymentDetail() {
        return paymentDetail;
    }

    /**
     * Getter for the list of associate customers for the paying customer
     * 
     * @return The list of associate customers for the paying customer
     */
    public ArrayList<AssociateCustomer> getAssociateCustomers() {
        return associateCustomers;
    }

    /**
     * Setter for the payment method of the paying customer
     * 
     * @param paymentMethod The payment method of the paying customer
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Setter for the payment detail of the paying customer
     * 
     * @param paymentDetail The payment detail of the paying customer
     */
    public void setPaymentDetail(double paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    /**
     * Setter for the list of associate customers for the paying customer
     * 
     * @param associateCustomers The list of associate customers for the paying customer
     */
    public void setAssociateCustomers(ArrayList<AssociateCustomer> associateCustomers) {
        this.associateCustomers = associateCustomers;
    }

    /**
     * Add an associate customer to the list of associate customers for the paying customer
     * 
     * @param associateCustomer The associate customer to be added
     */
    public void addAssociateCustomer(AssociateCustomer associateCustomer) {
        this.associateCustomers.add(associateCustomer);
    }

    /**
     * Remove an associate customer from the list of associate customers for the paying customer
     * 
     * @param associateCustomer The associate customer to be removed
     */
    public void removeAssociateCustomer(AssociateCustomer associateCustomer) {
        associateCustomers.remove(associateCustomer);
    }

    /**
     * Returns the monthly payment due for the paying customer
     * 
     * @param magazine The magazine the customer is subscribed to
     * @return The monthly payment due for the paying customer
     */
    public double calculateMonthlyPayment(Magazine magazine) {
        double monthlyPayment = magazine.getWeeklyCost() * 4;
        for (Supplement supplement : getSupplements()) {
            monthlyPayment += supplement.getWeeklyCost() * 4;
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            monthlyPayment += associateCustomer.calculateMonthlyPayment(magazine);
        }
        return monthlyPayment;
    }

    /**
     * Returns the weekly payment due for the paying customer
     * 
     * @param magazine The magazine the customer is subscribed to
     * @return The weekly payment due for the paying customer
     */
    public double calculateWeeklyPayment(Magazine magazine) {
        double weeklyPayment = magazine.getWeeklyCost();
        for (Supplement supplement : getSupplements()) {
            weeklyPayment += supplement.getWeeklyCost();
        }
        for (AssociateCustomer associateCustomer : associateCustomers) {
            weeklyPayment += associateCustomer.calculateWeeklyPayment(magazine);
        }
        return weeklyPayment;
    }

    /**
     * Returns the weekly email for the customer
     * 
     * @return The weekly email for the customer
     */
    @Override
    public String getWeeklyEmail() {
        StringBuilder email = new StringBuilder();
        email.append("To: ").append(this.getEmail()).append("\n");
        email.append("Dear ").append(this.getName()).append(",\n");
        email.append("Your magazine is ready to look at. You are currently subscribed to the following supplements:\n");
        for (Supplement supplement : this.getSupplements()) {
            email.append(supplement.getName()).append("\n");
        }
        return email.toString();
    }

    /**
     * Returns the email content for the monthly email
     * 
     * @param magazine The magazine the customer is subscribed to
     * @return The email content for the monthly email
     */

    @Override
    public String getMonthlyEmail(Magazine magazine) {
        StringBuilder email = new StringBuilder();
        email.append("To: ").append(this.getEmail()).append("\n");
        email.append("Dear ").append(this.getName()).append(",\n");
        // Total cost for the customer
        email.append("The total amount due for the month is: ").append(this.calculateMonthlyPayment(magazine));
        // Itemized cost for the customer
        email.append("\nThe itemized cost for the month is:\n");
        email.append("Magazine: ").append(magazine.getWeeklyCost() * 4).append("\n");
        for (Supplement supplement : this.getSupplements()) {
            // Calculate monthly cost for the supplement from the weekly cost
            email.append(supplement.getName()).append(": ").append(supplement.getWeeklyCost() * 4).append("\n");
        }
        // Get the list of associate customers from the paying customer's attribute
        ArrayList<AssociateCustomer> associatedCustomers = ((PayingCustomer) this).getAssociateCustomers();
        for (AssociateCustomer associateCustomer : associatedCustomers) {
            // Calculate monthly cost for the supplement from the weekly cost
            email.append("Associate customer ").append(associateCustomer.getName()).append(":\n");
            email.append("Magazine: ").append(magazine.getWeeklyCost() * 4).append("\n");
            for (Supplement supplement : associateCustomer.getSupplements()) {
                email.append(supplement.getName()).append(": ").append(supplement.getWeeklyCost() * 4).append("\n");
            }
        }
        // Charge to the payment method and payment detail
        email.append("The amount will be charged to your ").append(this.getPaymentMethod()).append(" with the details: ")
                .append(this.getPaymentDetail()).append("\n");
        return email.toString();
    }
}