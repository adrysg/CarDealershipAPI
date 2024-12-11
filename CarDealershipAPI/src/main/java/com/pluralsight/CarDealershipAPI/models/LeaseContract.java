package com.pluralsight.CarDealershipAPI.models;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private final double expectedEndingValuePctOfPrice = 0.50;
    private double LeaseFee;
    private final double leaseFeePercentage = 0.07;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * expectedEndingValuePctOfPrice;
        this.LeaseFee = vehicleSold.getPrice() * leaseFeePercentage;
    }

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, double expectedEndingValue, double leaseFee) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        LeaseFee = leaseFee;
    }

    public static Contract buildFromEncodedData(String encodedData){
        //super("asdf","asdf","asdf", null);
        String[] cols = encodedData.split("\\|");

        String contractDate = cols[1];
        String contractName = cols[2];
        String contractEmail = cols[3];
        int vehicleVin = Integer.parseInt(cols[4]);
        int vehicleYear = Integer.parseInt(cols[5]);
        String vehicleMake = cols[6];
        String vehicleModel = cols[7];
        String vehicleType = cols[8];
        String vehicleColor = cols[9];
        int vehicleMiles = Integer.parseInt(cols[10]);
        double vehiclePrice = Double.parseDouble(cols[11]);
        double leaseContractExpectedEndingValue = Double.parseDouble(cols[12]);
        double leaseContractLeaseFee = Double.parseDouble(cols[13]);

        Vehicle v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);

        return new LeaseContract(contractDate,contractName, contractEmail, v, leaseContractExpectedEndingValue, leaseContractLeaseFee );

    }


    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return LeaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.LeaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return (this.expectedEndingValue + this.getLeaseFee());
    }

    @Override
    public double getMonthlyPayment() {
        double financeRate = 0.04;
        double financeTerm = 36;

        return BankingCalculations.calculateLoanPayment(this.getTotalPrice(), financeRate, financeTerm);
    }

    @Override
    public String encode() {
        return "LEASE|" +
                this.getDateOfContract() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getExpectedEndingValue() + "|" +
                this.getLeaseFee() + "|" +
                this.getTotalPrice() + "|" +
                this.getMonthlyPayment();
    }






}
