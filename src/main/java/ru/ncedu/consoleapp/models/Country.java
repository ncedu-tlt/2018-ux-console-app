package ru.ncedu.consoleapp.models;

public class Country implements Cloneable{

    private long id;
    private String name;
    private String phoneExtension;
    private String flag;

    public Country(){

    }

    public Country(long id){
        this.id = id;
    }

    public Country(long id, String name, String phoneExtension, String flag){

        this.id = id;
        this.name = name;
        this.phoneExtension = phoneExtension;
        this.flag = flag;
    }

    public Country(Country country){
        this(country.getId(), country.getName(), country.getPhoneExtension(), country.getFlag());
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(long id){ this.id = id;}

    public void setPhoneExtension(String phoneExtension){
        this.phoneExtension = phoneExtension;
    }

    public void setFlag(String flag){
        this.flag = flag;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhoneExtension(){
        return phoneExtension;
    }

    public String getFlag(){
        return flag;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Country) {
            Country country = (Country) obj;
            return country.getId() == id;
        }
        return false;
    }

}