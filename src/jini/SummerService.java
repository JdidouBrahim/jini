package jini;

import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceID;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.lease.LeaseRenewalManager;
import net.jini.core.lookup.ServiceMatches;
import net.jini.core.lookup.ServiceItem;
import net.jini.core.entry.Entry;
import net.jini.lookup.JoinManager;
import net.jini.lookup.ServiceIDListener;
import net.jini.lookup.entry.Name;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.StringTokenizer;

public class SummerService extends UnicastRemoteObject 
    implements Summer, ServiceIDListener, Serializable
{
    public SummerService() throws RemoteException {
        super ();
    }

    public long sumString(String s) throws InvalidLongException,
        RemoteException {

        System.out.println("sumString(\"" + s + "\")");
        long sum = 0;
        StringTokenizer st = new StringTokenizer(s);
        String token;
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            try {
                sum += Long.parseLong(token);
            }
            catch (NumberFormatException e) {
                throw new InvalidLongException(
                    "Invalid number: " + token);
            }
        }
      
        return sum;
    }

    public void serviceIDNotify (ServiceID idIn) {
    }

    public static void main (String[] args) {

        try {
            System.setSecurityManager(new RMISecurityManager());

            Entry[] attributes = new Entry[1];
            attributes[0] = new Name("SummerService");

            SummerService summerService = new SummerService();

            JoinManager joinManager = new JoinManager(summerService,
                attributes, summerService, null, new LeaseRenewalManager());
        } 
        catch (Exception e) {
            System.out.println("SummerService Exception:" + e);
        }
    }
}
