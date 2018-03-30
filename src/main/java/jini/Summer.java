package jini;

import java.rmi.*;

public interface Summer extends Remote {

    long sumString(String s)
        throws InvalidLongException,
        RemoteException;
}

