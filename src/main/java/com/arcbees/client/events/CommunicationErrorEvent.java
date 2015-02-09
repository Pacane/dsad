package com.arcbees.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import com.google.gwt.event.shared.HasHandlers;

public class CommunicationErrorEvent extends GwtEvent<CommunicationErrorEvent.CommunicationErrorEventHandler> {
    public interface CommunicationErrorEventHandler extends EventHandler, HasHandlers {
        void onError(CommunicationErrorEvent event);
    }

    public static final Type<CommunicationErrorEventHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source) {
        source.fireEvent(new CommunicationErrorEvent());
    }

    @Override
    public Type<CommunicationErrorEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CommunicationErrorEventHandler handler) {
        handler.onError(this);
    }
}
