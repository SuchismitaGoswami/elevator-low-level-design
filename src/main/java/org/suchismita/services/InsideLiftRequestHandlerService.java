package org.suchismita.services;

import org.suchismita.interfaces.ISubscriber;
import org.suchismita.models.Lift;
import org.suchismita.models.Request;
import org.suchismita.repositories.IRepository;
import org.suchismita.strategies.ILiftAllocationStrategy;

public class InsideLiftRequestHandlerService implements ISubscriber<Request> {

    private IRepository<Lift> liftRepository;

    public InsideLiftRequestHandlerService(IRepository<Lift> liftRepository) {
        this.liftRepository = liftRepository;
    }

    @Override
    public void consume(Request request) {
        Lift lift = liftRepository.getById(request.getLift().getNo());
        System.out.println("Lift " + lift.getNo() + " will stop at floor "+ request.getDestinationFloor().getFloorNo());
        lift.addStopLocation(request.getDestinationFloor());
    }
}
