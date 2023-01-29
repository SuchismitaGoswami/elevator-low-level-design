package org.suchismita.services;

import org.suchismita.enums.RequestType;
import org.suchismita.interfaces.IDispatcher;
import org.suchismita.interfaces.IDispatcher;
import org.suchismita.models.Request;
import org.suchismita.models.RequestQueue;

public class DefaultRequestDispatcherService implements IDispatcher<Request> {

    private RequestQueue insideLiftRequestQueue;
    private RequestQueue outsideLiftRequestQueue;


    public DefaultRequestDispatcherService(RequestQueue insideLiftRequestQueue, RequestQueue outsideLiftRequestQueue){
        this.outsideLiftRequestQueue = outsideLiftRequestQueue;
        this.insideLiftRequestQueue = insideLiftRequestQueue;
    }
    public void dispatch(Request request) {
        if(request.getRequestType().equals(RequestType.InsideLift))
            this.insideLiftRequestQueue.publish(request);
        if(request.getRequestType().equals(RequestType.OutsideLift))
            this.outsideLiftRequestQueue.publish(request);
    }
}
