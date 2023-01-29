package org.suchismita.services;

import org.suchismita.models.Lift;
import org.suchismita.repositories.IRepository;

public class LiftService {
    IRepository<Lift> liftIRepository;

    public LiftService(IRepository<Lift> liftIRepository){
        this.liftIRepository = liftIRepository;
    }

    public Lift getLift(int liftNo){
        return this.liftIRepository.getById(liftNo);
    }
    public void addLift(Lift lift){
        this.liftIRepository.add(lift);
    }

    public void markLiftUnderMaintainance(Lift lift){
        lift.markLiftUnderMaintainance();
        this.liftIRepository.update(lift.getNo(), lift);
    }

    public void deleteLift(Lift lift){
        this.liftIRepository.delete(lift);
    }

}
