package ru.ncedu.consoleapp.models;


public class Offering {
    private long id;
    private long productId;
    private long officeId;
    private int offeringPrice;
    private String description;

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
        this.description = offering.description;
    }

    public Offering(long productId, long officeId, int offeringPrice, String description){
        this.productId = productId;
        this.officeId = officeId;
        this.offeringPrice = offeringPrice;
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public long getOfficeId() {
        return officeId;
    }

    public int getOfferingPrice() {
        return offeringPrice;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public void setOfferingPrice(int offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Offering){
            Offering offering = (Offering) obj;
            return offering.getOfficeId()==officeId&&offering.getProductId()==productId;
        }
        return false;
    }
}
