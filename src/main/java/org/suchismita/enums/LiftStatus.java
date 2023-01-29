package org.suchismita.enums;

public enum LiftStatus {
    MOVING(1), NOT_WORKING(-1), IDLE(0);
    private int code;
    private LiftStatus(final int code){
        this.code = code;
    }
}
