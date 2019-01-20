package ru.ncedu.consoleapp.models;

public class Office {
    private long id;
    private String name;
    private String phoneNumber;
    private long cityId;


    public Office() {
    }

    public Office(long id) {
        this.id = id;
    }

    public Office(long id, String name, String phoneNumber, long cityId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cityId = cityId;
    }

    public Office(Office source) {
        this(source.getId(), source.getName(), source.getPhoneNumber(), source.cityId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Office) {
            Office office = (Office) object;
            return office.getId() == id;
        }
        return false;
    }
}

