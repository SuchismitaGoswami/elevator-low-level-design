package org.suchismita.models;

public class Location implements  Comparable<Location>{

    private int floorNo;
    public Location(int floorNo){
        this.floorNo = floorNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    @Override
    public int compareTo(Location o) {
        return Integer.valueOf(this.floorNo).compareTo(Integer.valueOf(o.floorNo));
    }
}
