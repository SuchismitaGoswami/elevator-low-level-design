package org.suchismita.exceptions;

public class QueueUnderFlowException extends RuntimeException{
    public  QueueUnderFlowException(String message){
        super(message);
    }
}
