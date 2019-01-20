package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Offering;
import java.util.ArrayList;
import java.util.List;

public class OfferingRepository implements Repository<Offering> {

    private static OfferingRepository instance;

    private final List<Offering> offerings;

    public OfferingRepository(){
        this.offerings = new ArrayList<>();
    }

    public static OfferingRepository getInstance(){
        if (instance == null){
            instance = new OfferingRepository();
        }
        return instance;
    }

    public List<Offering> get(){
        return offerings;
    }

    @Override
    public Offering get(long id){
        return offerings.stream().filter(offering -> offering.getId() == id).findFirst().orElse(null);
    }
    @Override
    public Offering add(Offering object){
        Offering offering = new Offering(object);
        offering.setId(generateId());
        offerings.add(offering);

        return offering;
    }
    @Override
    public Offering update(Offering object){
        Offering offering = new Offering(object);

        offerings.remove(offering);
        offerings.add(offering);

        return offering;
    }
    public boolean remove(Offering offering) {
        return offerings.remove(offering);
    }
    @Override
    public boolean remove(long id) {
        return remove(new Offering(id));
    }

    private long generateId(){
        long id = 0;
        for (Offering offering : offerings){
            id = Math.max(id, offering.getId());
        }
        return ++id;
    }
}
