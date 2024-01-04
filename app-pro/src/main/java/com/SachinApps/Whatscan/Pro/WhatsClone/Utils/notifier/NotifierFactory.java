package com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier;

import java.util.Vector;

public class NotifierFactory {

    private static Vector< EventNotifier > _eventNotifiers = null;
    
    private static NotifierFactory _notifierFactoryInstance = null;

    public static final int EVENT_NOTIFIER_AD_STATUS = 4;

    private NotifierFactory( ) {
        _eventNotifiers = new Vector<>();
    }

    public static NotifierFactory getInstance( ) {
        if ( _notifierFactoryInstance == null ) {
            _notifierFactoryInstance = new NotifierFactory( );
        }
        return _notifierFactoryInstance;
    }

    public EventNotifier getNotifier( int eventCategory ) {

        EventNotifier eventNotifier = findNotifier( eventCategory );
        if ( eventNotifier != null ) {
            
            return eventNotifier;
        }

        EventNotifier objEventNotifier = new EventNotifier( eventCategory );
        _eventNotifiers.addElement( objEventNotifier );
        
        return objEventNotifier;
    }

    private static EventNotifier findNotifier( int eventCategory ) {
        
        EventNotifier eventNotifierObject;
        
        int length = _eventNotifiers.size( );
        
        for ( int index = 0; index < length; index++ ) {
            
            eventNotifierObject = (EventNotifier) _eventNotifiers.elementAt( index );
            int category = eventNotifierObject.getEventCategory( );
            
            if ( eventCategory == category ) {
                
                return eventNotifierObject;
            }
        }
        return null;
    }
}