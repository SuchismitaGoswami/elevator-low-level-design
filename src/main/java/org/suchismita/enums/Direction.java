package org.suchismita.enums;

import java.util.HashMap;

public enum Direction {
    DOWN(0), UP(1);
    private final int value;
    private static HashMap<Integer, Direction > map =  new HashMap<>();
    static {
        for(Direction d: Direction.values()){
            map.put(d.value, d);
        }
    }

    private Direction(int value){
        this.value = value;
    }


    public static Direction valueOf(Integer val) throws IllegalArgumentException{
        return map.get(Integer.valueOf(val));
    }
}
