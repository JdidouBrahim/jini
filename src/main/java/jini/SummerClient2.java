package jini;

import net.jini.entry.*;
import net.jini.core.lookup.*; 
import net.jini.lookup.entry.*; 
import net.jini.core.discovery.*;    
import java.rmi.*;
import java.io.*;

class SummerClient2
{
    public static void main (String[] args) {

        try {
            System.setSecurityManager (new RMISecurityManager ());

            // Perform unicast lookup on localhost
            LookupLocator lookup = new LookupLocator("jini://localhost");

            // Get the service registrar object for the lookup service
            ServiceRegistrar registrar = lookup.getRegistrar();

            // Search the lookup server to find a service that implements
            // the Summer interface.
            Class[] types = new Class[1];
            types[0] = Summer.class;
            ServiceTemplate template = new ServiceTemplate(null, types, null);

            // lookup() returns the service object for a service that matches
            // the search criteria passed in as template
            // Here, because I searched by type, I'm certain that
            // the object that comes back implements the Summer interface.
            Summer summer = (Summer) registrar.lookup(template);

            LineNumberReader stdinReader = new LineNumberReader(
                new BufferedReader(new InputStreamReader(System.in)));

            for (;;) {

                String userLine = stdinReader.readLine();

                if (userLine == null || userLine.length() == 0) {
                    break;
                }

                String outString;
                try {
                    long sum = summer.sumString(userLine);
                    outString = Long.toString(sum);
                }
                catch(InvalidLongException e) {
                    outString = e.getMessage();
                }
                System.out.println(outString);
            }
        }
        catch (Exception e) 
        {
          System.out.println("client: MyClient.main() exception: " + e);
          e.printStackTrace();
        }
    }
}
