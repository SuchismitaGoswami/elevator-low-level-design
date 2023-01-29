package org.suchismita.strategies;
import org.suchismita.enums.Direction;
import org.suchismita.exceptions.NoLiftAvailableException;
import org.suchismita.models.Lift;
import org.suchismita.models.Location;

import  java.util.ArrayList;
public interface ILiftAllocationStrategy {
    Lift allocateLift(ArrayList<Lift> lifts, Direction destinationDirection, Location currentFloor) throws NoLiftAvailableException;
}
