package org.suchismita.controllers;

import org.suchismita.enums.Direction;
import org.suchismita.enums.RequestType;
import org.suchismita.interfaces.IDispatcher;
import org.suchismita.models.Lift;
import org.suchismita.models.Request;
import org.suchismita.models.Location;
import org.suchismita.services.LiftService;

import java.util.Date;

public class RequestController {

    private IDispatcher requestDispatcherService;
    private LiftService liftService;

    public RequestController(IDispatcher requestDispatcherService, LiftService liftService){
        this.requestDispatcherService = requestDispatcherService;
        this.liftService = liftService;
    }
    public void requestForElevator(int currentFloor, int direction){
        Request request = new Request.Builder()
                .setRequestType(RequestType.OutsideLift)
                .setCreationTime(new Date())
                .setCurrentFloor(new Location(currentFloor))
                .setDirection(Direction.valueOf(direction))
                .build();
        requestDispatcherService.dispatch(request);
//        System.out.println("A request for lift at floor " + currentFloor+ " raised succesfuly!");
    }

    public void requestForDestination(int currentFloor, int destination, int liftNo){

        Lift lift = liftService.getLift(liftNo);
        Request request = new Request.Builder()
                .setRequestType(RequestType.InsideLift)
                .setCreationTime(new Date())
                .setCurrentFloor(new Location(currentFloor))
                .setDestinationFloor(new Location(destination))
                .setLift(lift)
                .build();
        requestDispatcherService.dispatch(request);
    }
}
