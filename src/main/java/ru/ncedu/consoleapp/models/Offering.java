package ru.ncedu.consoleapp.models;


public class Offering {
    private long id;
    private long productId;
    private long officeId;
    private double offeringPrice;

    public Offering(){
    }

    public Offering(long id){
        this.id = id;
    }

    public Offering(Offering offering){
        this.id = offering.id;
        this.productId = offering.productId;
        this.officeId = offering.officeId;
        this.offeringPrice = offering.offeringPrice;
    }

    public Offering(long productId, long officeId, double offeringPrice){
        this.productId = productId;
        this.officeId = officeId;
        this.offeringPrice = offeringPrice;
    }

    public long getProductId() {
        return productId;
    }

    public long getOfficeId() {
        return officeId;
    }

    public double getOfferingPrice() {
        return offeringPrice;
    }

    public long getId() {
        return id;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public void setOfferingPrice(double offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Offering){
            Offering offering = (Offering) obj;
            return offering.getId()==id;
        }
        return false;
    }
}
