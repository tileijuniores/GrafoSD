/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosd;

/**
 *
 * @author antonio
 */

import grafosd.*;

/*import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.protocol.TBinaryProtocol;*/

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;


public class Servidor {

    public static void main(String[] args)
    {
    
        try
        {
                  
            TServerTransport serverTransport = new TServerSocket(9090);
            Handler handler = new Handler();
            Grafosd.Processor processor = new Grafosd.Processor(handler);
            
	    TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
	    serverArgs.processor(processor);
            serverArgs.transportFactory(new TTransportFactory());
	    serverArgs.protocolFactory(new TBinaryProtocol.Factory(true, true));

            TServer server = new TThreadPoolServer(serverArgs);
            
            System.out.println("Iniciando o servidor...");
            server.serve();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
    }
}

