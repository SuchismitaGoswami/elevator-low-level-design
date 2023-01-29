package org.suchismita;

import org.suchismita.controllers.LiftController;
import org.suchismita.controllers.RequestController;
import org.suchismita.models.Request;
import org.suchismita.models.RequestQueue;
import org.suchismita.repositories.LiftInMemoryRepository;
import org.suchismita.services.*;
import org.suchismita.strategies.DirectionBasedLiftAllocationStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        LiftInMemoryRepository repository = new LiftInMemoryRepository();
        LiftService liftService = new LiftService(repository);
        LiftController liftController = new LiftController(liftService);

        //ADMIN PERSONA
            liftController.addLift(1,10);
            liftController.addLift(2,12);

        DirectionBasedLiftAllocationStrategy liftAllocationStrategy = new DirectionBasedLiftAllocationStrategy();
        LiftRequestSchedulerService outsideLiftRequestHandlerService = new LiftRequestSchedulerService(repository, liftAllocationStrategy);
        RequestQueue<Request> outsideLiftQueue = new RequestQueue();
        outsideLiftQueue.subscribe(outsideLiftRequestHandlerService);


        InsideLiftRequestHandlerService insideLiftRequestHandlerService = new InsideLiftRequestHandlerService(repository);
        RequestQueue<Request> insideLiftQueue = new RequestQueue();
        insideLiftQueue.subscribe(insideLiftRequestHandlerService);

        DefaultRequestDispatcherService requestDispatcherService = new DefaultRequestDispatcherService(insideLiftQueue, outsideLiftQueue);
        RequestController requestController = new RequestController(requestDispatcherService, liftService);

        // User Persona

        requestController.requestForElevator(1,1);
        requestController.requestForElevator(4,0);
        requestController.requestForElevator(2,1);


        requestController.requestForDestination(1,5,1);
        requestController.requestForDestination(2,6,1);

        Thread.sleep(10000);

        requestController.requestForElevator(2,0);
        requestController.requestForElevator(4,0);
        requestController.requestForElevator(4,1);


    }
}