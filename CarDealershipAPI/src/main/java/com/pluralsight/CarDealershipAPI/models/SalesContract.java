package com.pluralsight.CarDealershipAPI.models;

import com.pluralsight.CarDealershipAPI.ITextEncodable;
import com.pluralsight.CarDealershipAPI.dao.impl.SalesContractDao;

import java.sql.Connection;
import java.sql.SQLException;

public class SalesContract extends Contract implements ITextEncodable {
    private double salesTaxAmount;
    private final double salesTaxPercentage = 0.05;
    private double recordingFee;
    private double processingFee;
    private boolean wantToFinance;


    public SalesContract(int salesContractID, String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean wantToFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);

        this.salesTaxAmount = vehicleSold.getPrice() * salesTaxPercentage;
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295 : 495;
        this.wantToFinance = wantToFinance;
    }

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, double salesTaxAmount, double recordingFee, double processingFee, boolean wantToFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.wantToFinance = wantToFinance;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee1) {
        this.processingFee = processingFee1;
    }

    public boolean isWantToFinance() {
        return wantToFinance;
    }

    public void setWantToFinance(boolean wantToFinance) {
        this.wantToFinance = wantToFinance;
    }

    @Override
    public double getTotalPrice() {
        return (super.getVehicleSold().getPrice() + this.salesTaxAmount + this.processingFee + this.recordingFee);
    }

    @Override
    public double getMonthlyPayment() {
        if (this.wantToFinance) {
            double financeRate = (super.getVehicleSold().getPrice() < 10000) ? 0.0525 : 0.0425;
            double financeTerm = (super.getVehicleSold().getPrice() < 10000) ? 24 : 48;
            return calculateLoanPayment(this.getTotalPrice(), financeRate, financeTerm);
        } else {
            return 0; //no financing means there's no monthly payment.
        }
    }

    private double calculateLoanPayment(double borrowedAmount, double loanRate, double months) {
        return borrowedAmount * (loanRate / 12 * Math.pow(1 + loanRate / 12, months)) / (Math.pow(1 + loanRate / 12, months));
    }

    @Override
    public String encode() {
        return "SALE|" +
                this.getDateOfContract() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getSalesTaxAmount() + "|" +
                this.getRecordingFee() + "|" +
                this.getProcessingFee() + "|" +
                this.getTotalPrice() + "|" +
                (this.isWantToFinance() ? "YES" : "NO") + "|" +
                this.getMonthlyPayment();

    }

    public void saveToDatabase(Connection connection) throws SQLException {
        SalesContractDao dao = new SalesContractDao(connection);
        dao.save(this);

    }
}