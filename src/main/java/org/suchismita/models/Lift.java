package org.suchismita.models;

import org.suchismita.enums.Direction;
import org.suchismita.enums.LiftStatus;


import java.util.SortedSet;
import java.util.TreeSet;

public class Lift implements Runnable{
    private int no;
    private  int capacity;
    private  Direction current_direction;
    private Location current_location;

    private  LiftStatus liftStatus;

    private  SortedSet<Location> stopLocations;
    private Thread thread;

    public Lift(final int no, final int capacity, final LiftStatus liftStatus){
        this.no = no;
        this.capacity = capacity;
        this.liftStatus = liftStatus;
        this.current_direction = Direction.UP;
        this.current_location = new Location(0);
        this.stopLocations = new TreeSet<>();
        thread = new Thread(this);
        thread.start();
    }
    public void addStopLocation(final Location floor) {
        this.stopLocations.add(floor);
        synchronized (stopLocations){
            this.stopLocations.notify();
        }

    }

    public int getNo() {
        return no;
    }

    public Direction getCurrent_direction() {
        return current_direction;
    }

    public LiftStatus getLiftStatus() {
        return liftStatus;
    }

    public Location getCurrent_location() {
        return current_location;
    }

    public void markLiftUnderMaintainance(){
        this.setLiftStatus(LiftStatus.NOT_WORKING);
    }

    public void setLiftStatus(LiftStatus liftStatus) {
        this.liftStatus = liftStatus;
    }


    public void setCurrent_direction(Direction current_direction) {
        this.current_direction = current_direction;
    }

    @Override
    public void run() {
        do{
            synchronized (this.stopLocations){
                try {
                    if(this.stopLocations.isEmpty()) {
                        this.setLiftStatus(LiftStatus.IDLE);
                        this.stopLocations.wait();
                    }
                    else{
                        this.setLiftStatus(LiftStatus.MOVING);
                        this.move(this.stopLocations.first());
                        Thread.sleep(2000);
                        this.stopLocations.remove(this.stopLocations.first());
                    }
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }while (true);

    }

    public void setCurrent_location(Location current_location) {
        this.current_location = current_location;
    }

    private void move(Location location) {
        this.setCurrent_location(location);
        System.out.println("Lift " + no + " moved and stopped at "+ location.getFloorNo());
    }
}
