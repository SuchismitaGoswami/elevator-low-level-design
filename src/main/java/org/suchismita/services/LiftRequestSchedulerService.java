package org.suchismita.services;

import org.suchismita.enums.LiftStatus;
import org.suchismita.enums.RequestType;
import org.suchismita.interfaces.ISubscriber;
import org.suchismita.repositories.IRepository;
import org.suchismita.strategies.ILiftAllocationStrategy;
import org.suchismita.models.Lift;
import org.suchismita.models.Request;

public class LiftRequestSchedulerService implements ISubscriber<Request> {

    private IRepository<Lift> liftRepository;
    private final ILiftAllocationStrategy allocationStrategy;

    public LiftRequestSchedulerService(IRepository<Lift> liftRepository, ILiftAllocationStrategy allocationStrategy){
        this.liftRepository = liftRepository;
        this.allocationStrategy = allocationStrategy;
    }

    @Override
    public void consume(Request request) {
        Lift lift = allocationStrategy.allocateLift(liftRepository.getAll(), request.getDirection(), request.getCurrentFloor());
        System.out.println("Lift " + lift.getNo() + " at floor " + lift.getCurrent_location().getFloorNo() + " is assigned to move to floor " + request.getCurrentFloor().getFloorNo());

        lift.addStopLocation(request.getCurrentFloor());
        if(lift.getLiftStatus() == LiftStatus.IDLE){
            lift.setCurrent_direction(request.getDirection());
        }

    }
}
