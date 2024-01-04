package com.SachinApps.Whatscan.Pro.WhatsClone.Utils.notifier;

import java.util.Vector;

public class EventNotifier {
    
    private Vector< ListenerObject > _registeredListener = null;
    private final int _eventCategory;

    public EventNotifier(int category ) {
        
        _registeredListener = new Vector< ListenerObject >( );
        _eventCategory = category;
    }

    public int getEventCategory( ) {
        return _eventCategory;
    }

    public void registerListener( IEventListener eventListener, int listenerPriority ) {
        
        try {
            if ( _checkAlreadyRegistered( eventListener, listenerPriority ) ) {
                return;
            }
            
            ListenerObject listenerObj = new ListenerObject( eventListener, listenerPriority );
            int length = _registeredListener.size( );

            if ( length == 0 ) {
                _registeredListener.addElement( listenerObj );
                return;
            }

            for ( int index = 0; index < length; index++ ) {
                
                ListenerObject tempObj1 = (ListenerObject) _registeredListener.elementAt( index );
                if ( listenerPriority <= tempObj1.getPriority( ) ) {
                    _registeredListener.insertElementAt( listenerObj, index );
                    return;
                }
            }
            
            _registeredListener.addElement( listenerObj );
            return;
        } catch ( Exception e ) {

        }
    }

    public void unRegisterListener( IEventListener eventListener ) {
        try {
            int length = _registeredListener.size( );
            for ( int index = 0; index < length; index++ ) {
                ListenerObject listenerObj = (ListenerObject) _registeredListener.elementAt( index );
                IEventListener iEventListener = listenerObj.getIEventListener( );

                if ( iEventListener.equals( eventListener ) ) {
                    _registeredListener.removeElementAt( index );
                    return;
                }
            }
        } catch ( Exception ignored) {
            
        }
    }

    public void eventNotify(int eventType, Object eventObject )
    {
        int eventState = EventState.EVENT_PROCESSED;
        try {
            int length = _registeredListener.size( );
            if ( length == 0 ) {
                return;
            }

            for ( int index = _registeredListener.size( ) - 1; index >= 0; index-- ) {
                IEventListener listenerObj =
                        (IEventListener) ( (ListenerObject) _registeredListener.elementAt( index ) )
                                .getIEventListener( );
                if ( listenerObj == null )
                    continue;

                eventState = listenerObj.eventNotify( eventType, eventObject );
                if ( eventState == EventState.EVENT_CONSUMED ) {
                    return;
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
    }

    private boolean _checkAlreadyRegistered( IEventListener eventListener, int priority ) {
        try {
            int length = _registeredListener.size( );
            
            for ( int index = 0; index < length; index++ ) {
                IEventListener listener =
                        (IEventListener) ( (ListenerObject) _registeredListener.elementAt( index ) )
                                .getIEventListener( );
                
                if ( eventListener.equals( listener ) ) {
                    return true;
                }
            }
        } catch ( Exception e ) {
            
        }
        return false;
    }

    public class ListenerObject {
        private IEventListener _eventListener;
        private int _priority;

        public ListenerObject( IEventListener eventListener, int priority ) {
            _eventListener = eventListener;
            _priority = priority;
        }

        public IEventListener getIEventListener( ) {
            return _eventListener;
        }

        public int getPriority( ) {
            return _priority;
        }
    }
    
}