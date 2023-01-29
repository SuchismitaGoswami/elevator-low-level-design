package org.suchismita.controllers;

import org.suchismita.enums.LiftStatus;
import org.suchismita.models.Lift;
import org.suchismita.services.LiftService;

public class LiftController {

    private LiftService liftService;

    public LiftController(LiftService liftService){
        this.liftService = liftService;
    }

    public void addLift(int liftNo, int capacity){
        Lift lift = new Lift(liftNo, capacity, LiftStatus.IDLE);
        liftService.addLift(lift);
    }

    public void markLiftUnderMaintainance(int liftNo){
        liftService.markLiftUnderMaintainance(liftService.getLift(liftNo));
    }

}
