package com.pluralsight.CarDealershipAPI.models;

public class LeaseContract extends Contract {
    private int leaseContractID;
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double leaseContractExpectedFinalValue;
    private double leaseContractLeaseFee;
    private double totalPrice;
    private double monthlyPayment;

    // Constructor to create a LeaseContract object
    public LeaseContract(int leaseContractID, String contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.leaseContractID = leaseContractID;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.leaseContractExpectedFinalValue = leaseContractExpectedFinalValue;
        this.leaseContractLeaseFee = leaseContractLeaseFee;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    // Getters and Setters
    public int getLeaseContractID() {
        return leaseContractID;
    }

    public void setLeaseContractID(int leaseContractID) {
        this.leaseContractID = leaseContractID;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public double getLeaseContractExpectedFinalValue() {
        return leaseContractExpectedFinalValue;
    }

    public void setLeaseContractExpectedFinalValue(double leaseContractExpectedFinalValue) {
        this.leaseContractExpectedFinalValue = leaseContractExpectedFinalValue;
    }

    public double getLeaseContractLeaseFee() {
        return leaseContractLeaseFee;
    }

    public void setLeaseContractLeaseFee(double leaseContractLeaseFee) {
        this.leaseContractLeaseFee = leaseContractLeaseFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String encode() {
        return "LEASE|" +
                this.getDateOfContract() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getLeaseContractExpectedFinalValue() + "|" +
                this.getLeaseContractLeaseFee() + "|" +
                this.getTotalPrice() + "|" +
                this.getMonthlyPayment();
    }


}
