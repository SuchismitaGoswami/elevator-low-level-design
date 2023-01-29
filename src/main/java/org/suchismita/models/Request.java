package org.suchismita.models;
import org.suchismita.enums.RequestType;
import org.suchismita.enums.Direction;
import java.util.Date;

public class Request {
    private RequestType requestType;
    private Date creationTime;
    private Direction direction;
    private Location currentFloor;
    private Location destinationFloor;
    private Lift lift;

    private Request(RequestType requestType, Date creationTime, Direction direction, Location currentFloor){
        this.requestType = requestType;
        this.creationTime = creationTime;
        this.direction = direction;
        this.currentFloor = currentFloor;
    }
    private Request(RequestType requestType,Lift lift, Date creationTime, Location destinationFloor){
        this.requestType = requestType;
        this.creationTime = creationTime;
        this.destinationFloor = destinationFloor;
        this.lift = lift;
    }

    public Location getDestinationFloor() {
        return destinationFloor;
    }

    public Lift getLift() {
        return lift;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Location getCurrentFloor() {
        return currentFloor;
    }

    public static class Builder{
        private RequestType requestType;
        private Date creationTime;
        private Direction direction;
        private Location currentFloor;
        private Location destinationFloor;
        private  Lift lift;

        public Builder setCreationTime(Date creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public Builder setCurrentFloor(Location currentFloor) {
            this.currentFloor = currentFloor;
            return this;
        }

        public Builder setDestinationFloor(Location destinationFloor) {
            this.destinationFloor = destinationFloor;
            return this;
        }

        public Builder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder setRequestType(RequestType requestType) {
            this.requestType = requestType;
            return this;
        }

        public Builder setLift(Lift lift) {
            this.lift = lift;
            return this;
        }

        public Request build(){
            if(this.requestType == null)
                throw new IllegalArgumentException("Request Type Not set!");
            else{
                if(this.requestType == RequestType.InsideLift){
                    return new Request(this.requestType, this.lift, this.creationTime,this.destinationFloor);
                }else{
                    return new Request(this.requestType,creationTime,this.direction,this.currentFloor);
                }
            }
        }
    }
}
