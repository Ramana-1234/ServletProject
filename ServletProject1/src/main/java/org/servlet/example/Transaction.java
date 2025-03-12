package org.servlet.example;

public class Transaction {
	 private int transactionId;
	    private String transactionDate;
	    private double amount;
	    private int customerId;
	    private String paymentMethod;

	    public Transaction(int transactionId, String transactionDate, double amount, int customerId, String paymentMethod) {
	        this.transactionId = transactionId;
	        this.transactionDate = transactionDate;
	        this.amount = amount;
	        this.customerId = customerId;
	        this.paymentMethod = paymentMethod;
	    }

	    public int getTransactionId() {
	        return transactionId;
	    }

	    public String getTransactionDate() {
	        return transactionDate;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public String getPaymentMethod() {
	        return paymentMethod;
	    }
	}


