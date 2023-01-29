package org.suchismita.strategies;

import org.suchismita.enums.Direction;
import org.suchismita.enums.LiftStatus;
import org.suchismita.exceptions.NoLiftAvailableException;
import org.suchismita.models.Lift;
import org.suchismita.models.Location;

import java.util.List;
import java.util.ArrayList;

import java.util.stream.Collectors;

public class DirectionBasedLiftAllocationStrategy implements ILiftAllocationStrategy{

//    TODO: Add mechanism to allow multiple lift to come to the desired location
    @Override
    public Lift allocateLift(ArrayList<Lift> lifts, Direction destinationDirection, Location desiredLocation) throws NoLiftAvailableException{

        Lift selectedLift = null;
        int miniMumDistance = Integer.MAX_VALUE;

        List<Lift> candidateLifts = lifts.stream().filter(lift->{
            if(lift.getLiftStatus().equals(LiftStatus.IDLE))
                return true;
            if(destinationDirection.equals(Direction.UP))
                return (lift.getLiftStatus().equals(LiftStatus.MOVING) && lift.getCurrent_direction().equals(destinationDirection) && lift.getCurrent_location().getFloorNo() < desiredLocation.getFloorNo()) ;
            else if(destinationDirection.equals(Direction.DOWN))
                return lift.getLiftStatus().equals(LiftStatus.MOVING) && lift.getCurrent_direction().equals(destinationDirection) && lift.getCurrent_location().getFloorNo() > desiredLocation.getFloorNo() ;
            else
                return false;
        }).collect(Collectors.toList());;


        for(Lift lift : candidateLifts) {
            int distance = Math.abs(lift.getCurrent_location().getFloorNo() - desiredLocation.getFloorNo());
            if (distance < miniMumDistance) {
                miniMumDistance = distance;
                selectedLift = lift;
            }
        }
        if(selectedLift == null)
            throw new NoLiftAvailableException("No Lift is available to handle request!");
        return  selectedLift;
    }
}
