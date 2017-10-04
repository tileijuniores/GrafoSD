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
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.lang.String;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {


        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            Grafosd.Client cliente = new Grafosd.Client(protocol);

        int opcao = 0;             
	Scanner s = new Scanner(System.in);
	while(opcao != -1)
  {	
	System.out.println("Escolha uma operação a ser realizada");
    
        System.out.println("1 - Adicionar Aresta\n2 - Adicionar Vertice\n3 - Remover Aresta\n4 - Remover Vertice\n5 - Modificar Aresta\n6 - Modificar Vertice\n7 - Ler Aresta\n8 - Ler Vertice\n9 - Listar Arestas\n10 - Listar Vertices\n11 - Listar Arestas de um Vertice\n12 - 	Listar Vertices Vizinhos de um Vertice\n");
        
        opcao = s.nextInt();
        switch(opcao)
        {
	    
            case 1:
                System.out.println("Digite os dados da aresta.\nID da aresta:");
                int id = s.nextInt();
                System.out.println("Verice de origem: ");
                int origem = s.nextInt();
                System.out.println("Verice de destino: ");
                int destino = s.nextInt();
                System.out.println("Descricao: ");
                s.nextLine();
		String descricao = s.nextLine();
                System.out.println("Peso: ");
                double peso = s.nextDouble();
		System.out.println("Flag: ");
                int flag = s.nextInt();
                cliente.addAresta(id, origem, destino, peso, flag, descricao);
		break;
	    case 2:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome = s.nextInt();
		System.out.println("Cor: ");
                int cor = s.nextInt();
                System.out.println("Descricao: ");
                s.nextLine();
		String descricaov = s.nextLine();
                System.out.println("Peso: ");
                double pesov = s.nextDouble();
		cliente.addVertice(nome, cor, descricaov, pesov);
		break;
	    case 3: 
                System.out.println("Digite os dados da aresta.\nVerice de origem: ");
                int origem1 = s.nextInt();
                System.out.println("Vertice de destino: ");
                int destino1 = s.nextInt();
		cliente.removeAresta(origem1, destino1);
		break;
            case 4:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome1 = s.nextInt();
                cliente.removeVertice(nome1);
                break;
            case 5:
                System.out.println("Digite os dados da aresta.\nID da aresta:");
                int id2 = s.nextInt();
                System.out.println("Verice de origem: ");
                int origem2 = s.nextInt();
                System.out.println("Verice de destino: ");
                int destino2 = s.nextInt();
                System.out.println("Descricao: ");
                s.nextLine();
		String descricao2 = s.nextLine();
                System.out.println("Peso: ");
                double peso2 = s.nextDouble();
		System.out.println("Flag: ");
                int flag2 = s.nextInt();
                cliente.modificaAresta(id2,  peso2, descricao2, origem2, destino2, flag2);
		break;
            case 6:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome3 = s.nextInt();
		System.out.println("Cor: ");
                int cor3 = s.nextInt();
                System.out.println("Descricao: ");
                s.nextLine();
		String descricao3 = s.nextLine();
                System.out.println("Peso: ");
                double peso3 = s.nextDouble();
		cliente.modificaVertice(nome3, cor3, descricao3, peso3);
		break;
            case 7:
                System.out.println("Digite os dados da aresta.\nID da aresta:");
                int id4 = s.nextInt();
                cliente.lerAresta(id4);
                break;
            case 8:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome5 = s.nextInt();
                cliente.lerVertice(nome5);
                break;
	    case 9:
                cliente.listaArestas();
		break;
            case 10:
                cliente.listaVertices();
                break;
            case 11:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome6 = s.nextInt();
                cliente.listaArestasVertice(nome6);
                break;
            case 12:
                System.out.println("Digite os dados do vertice.\nNome do vertice:");
                int nome7 = s.nextInt();
                cliente.listaVizinhosVertice(nome7);
                break;
            default:
                System.out.println("Esta opçao nao e valida");
                

        } 
        System.out.println("\n\n");
  }
	transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
  
}

