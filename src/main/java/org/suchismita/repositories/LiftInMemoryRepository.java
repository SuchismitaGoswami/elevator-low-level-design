package org.suchismita.repositories;

import org.suchismita.models.Lift;

import java.util.ArrayList;
import java.util.HashMap;

public class LiftInMemoryRepository implements IRepository<Lift> {

    private HashMap<Integer, Lift> lifts;
    public LiftInMemoryRepository(){
        lifts = new HashMap<>();
    }

    @Override
    public void add(Lift lift) {
        lifts.put(lift.getNo(),lift);
    }

    @Override
    public void delete(Lift lift) {
        lifts.remove(lift);
    }

    @Override
    public void update(int id, Lift lift) {
        lifts.put(id, lift);
    }

    @Override
    public ArrayList<Lift> getAll() {
       return new ArrayList<Lift>(lifts.values());
    }

    @Override
    public Lift getById(int id) {
        return this.lifts.get(id);
    }

}
