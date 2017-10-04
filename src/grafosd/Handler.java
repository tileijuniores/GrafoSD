/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosd;

/**
 *
 * @author lorena
 */
import java.util.concurrent.Semaphore;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.lang.String;
import java.io.ObjectOutputStream;
import java.util.List;


public class Handler implements Grafosd.Iface {

    Object grafoInput = null;
    Grafo grafo = new Grafo();
   
    static Semaphore semaphore = new Semaphore(1);

    
    ///cria um vertice e adiciona o mesmo no grafo, usando semaphore para controlar o acesso ao recurso
    @Override
    public void addVertice(int nome, int cor, String descricao, double peso) {
        
    try {
        semaphore.acquire();
    }
    catch (Exception e) {
         e.printStackTrace();
    }
    
        int i, aux = 0;
        if(grafo.vertices != null)// verifica se já existe um vertice de mesmo nome
        {
            for(i=0; i< grafo.vertices.size(); i++)
            {
               if(nome == grafo.vertices.get(i).getNome())
               {
                   System.out.println("O vertice não pode ser adicionado pois ja existe outro com o mesmo nome\n ");
                   aux = 1; 
                   break;

               }
            }
        }
        Vertice vertice = new Vertice(nome, cor, descricao, peso);

       
        if(aux != 1) //caso nao exista outro vertice com o mesmo nome
        {

            vertice.setCor(cor);
            vertice.setDescricao(descricao);
            vertice.setNome(nome);
            vertice.setPeso(peso);

            grafo.addToVertices(vertice);
            System.out.println("Vertice adicionado com sucesso!\n");
        }
      
        
        semaphore.release();
	

    }

    ///cria uma aresta e adiciona o mesmo no grafo, usando semaphore para controlar o acesso ao recurso
    @Override
    public void addAresta(int id, int origem, int destino, double peso, int flag, String descricao) {
        try {
            semaphore.acquire();
        }
    catch (Exception e) {
         e.printStackTrace();
    }
        int i, j, aux = 0;
        if(grafo.arestas != null){ 
            for(i=0; i<grafo.arestas.size();i++) //verifica se já existe aresta com mesma origem e destino
            {
                if(origem == grafo.arestas.get(i).getOrigem() && destino == grafo.arestas.get(i).getDestino())
                {
                    System.out.println("Operação inválida! Aresta com mesma origem e destino já existente \n");
                    aux = 1;
                    break;
                }
                
            }
        }
            
            if(grafo.vertices == null)
                System.out.println("Operação inválida!Vertices de origem e/ou destino não existem \n");
            else 
            {
                for(i=0;i<grafo.vertices.size();i++)
                {
                    if(origem == grafo.vertices.get(i).getNome())
                    {
                        for(j=0;j<grafo.vertices.size();j++)
                        {
                            if(destino == grafo.vertices.get(j).getNome())
                            {
                                if(aux != 1){
                                Aresta aresta = new Aresta();
                                aresta.setDestino(destino);
                                aresta.setOrigem(origem);
                                aresta.setFlag(flag);
                                aresta.setPeso(peso);
                                aresta.setDescricao(descricao);
                                aresta.setId(id);

                                grafo.addToArestas(aresta);
                                aux = 2;
                                System.out.println("Aresta adicionado com sucesso!\n");
                                break;
                                }
                            }   
                        }
                    
                    }
                }
            }

        if(aux != 2)
            System.out.println("Operação inválida!Vertices de origem e/ou destino não existem \n");
       
        semaphore.release();

    }
            

    ///Função que modifica os dados de um vertice ja existente (compara pelo nome).
    @Override
    public void modificaVertice(int nome, int cor, String descricao, double peso) {
        try {
            semaphore.acquire();
    }
    catch (Exception e) {
         e.printStackTrace();
    }
        int i;

        for (i = 0; i < grafo.vertices.size(); i++) {
            if (grafo.vertices.get(i).nome == nome) {
                grafo.vertices.get(i).setDescricao(descricao);
                grafo.vertices.get(i).setPeso(peso);
                grafo.vertices.get(i).setNome(nome);
                System.out.println("Vertice modificado com sucesso!\n");
                break;
            } else 
                System.out.println("Esse vertice nao existe e o nome do vertice nao pode ser modificado!\n");
        }
        
        semaphore.release();
    }

    ///Função que modifica os dados de uma aresta ja existente (compara por origem e destino)
    @Override
    public void modificaAresta(int id, double peso, String descricao, int origem, int destino, int flag) {
        try {
            semaphore.acquire();
        }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;
        Aresta aresta = new Aresta();
        
        for (i = 0; i < grafo.arestas.size(); i++) {
            if (grafo.arestas.get(i).origem == origem && grafo.arestas.get(i).destino == destino) {
                grafo.arestas.get(i).setPeso(peso);
                grafo.arestas.get(i).setFlag(flag);
                grafo.arestas.get(i).setDescricao(descricao);
                System.out.println("Aresta modificada com sucesso!\n");
                break;
            } else {
                System.out.println("Os vertices de uma aresta nao podem ser modificados!\n");
            }
        }
      
        semaphore.release();

    }
    /// compara por meio do ID e printa todas as informações da aresta
    @Override
    public void lerVertice(int nome) {
        try {
            semaphore.acquire();
        
    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;

        for (i = 0; i < grafo.vertices.size(); i++) {
            if (grafo.vertices.get(i).nome == nome) {
                String des = grafo.vertices.get(i).getDescricao();
                int corVer = grafo.vertices.get(i).getCor();
                double pesoV = grafo.vertices.get(i).getPeso();
                System.out.println("Vértice de nome: " + nome);
                System.out.println("Descrição: " + des);
                System.out.println("Peso: " + pesoV);
                System.out.println("Cor: " + corVer);

                break;
            }
        }
        System.out.println("\n");
        semaphore.release();

    }
    /// compara pelo nome do vertice e printa todas as informações referentes ao vertice
    @Override
    public void lerAresta(int ID) {
        try {
            semaphore.acquire();
    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;
        Aresta aresta = new Aresta();
        
        for (i = 0; i < grafo.arestas.size(); i++) {
            if (grafo.arestas.get(i).id == ID )
            {
                int id = grafo.arestas.get(i).getId();
                int origem = grafo.arestas.get(i).getOrigem();
                int destino = grafo.arestas.get(i).getDestino();
                double peso = grafo.arestas.get(i).getPeso();
                int flag = grafo.arestas.get(i).getFlag();
                String descricao = grafo.arestas.get(i).getDescricao();
                
               
                System.out.println("Aresta de ID: " + id);
                System.out.println("Descrição: " + descricao);
                System.out.println("Peso: " + peso);
                System.out.println("Vertice de Destino: " + destino);
                System.out.println("Vertice de Origem: " + origem);
                System.out.println("Flag: " + flag);

                break;
            }
        }
        
        System.out.println("\n");
        semaphore.release();

    } 

    ///Seleciona um vertice por meio do nome e exclui o mesmo.
    @Override
    public void removeVertice(int nome) {
        try {
            semaphore.acquire();

    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;

        for (i = 0; i < grafo.vertices.size(); i++) {
            if (grafo.vertices.get(i).nome == nome) {
                grafo.vertices.remove(i);
                System.out.println("Vertice removido com sucesso!\n");
                break;
            } else
                System.out.println("Nao existe vertice com esse nome para ser removido!\n");
            
            
        }
        
        semaphore.release();
    }
    
    ///Seleciona uma aresta por meio do destino e origem e exclui a mesma
    @Override
    public void removeAresta(int origem, int destino) {
    try {
            semaphore.acquire();
    }
    catch (Exception e) {
         e.printStackTrace();
    }
        int i;

        for (i = 0; i < grafo.arestas.size(); i++) {
            if (grafo.arestas.get(i).origem == origem && grafo.arestas.get(i).destino == destino) {
                grafo.arestas.remove(i);
                System.out.println("Aresta removida com sucesso!\n");
                break;
            } else 
                System.out.println("Nao exite aresta com essa origem e destino!\n");
        }
        
        semaphore.release();
 
}

    @Override //lista todos os vertices do grafo, ficou faltando a função de listar todas as arestas.
    public void listaVertices() {
        try {
            semaphore.acquire();
    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;
        if(grafo.vertices == null)
        {
            System.out.println("Nao existem vertices no grafo!");
        } else {
        for (i = 0; i < grafo.vertices.size(); i++) {
            System.out.println("##Vertice: "+(i+1)+"##");
                int nom = grafo.vertices.get(i).getNome();
                String des = grafo.vertices.get(i).getDescricao();
                int corVer = grafo.vertices.get(i).getCor();
                double pesoV = grafo.vertices.get(i).getPeso();
                System.out.println("Vértice de nome: " + nom);
                System.out.println("Descrição: " + des);
                System.out.println("Peso: " + pesoV);
                System.out.println("Cor: " + corVer);
                System.out.println("\n");

                //break;
            
        }
        }
        semaphore.release();

    }

    ///lista todas as arestas de um dado vertice.
    @Override
    public void listaArestas() {
        try {
            semaphore.acquire();
    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;
        if(grafo.arestas == null)
        {
            System.out.println("Nao existem arestas no grafo!");
        } else {
        for (i = 0; i < grafo.arestas.size(); i++) {
            System.out.println("##Aresta: "+(i+1)+"##");
                int id = grafo.arestas.get(i).getId();
                int dest = grafo.arestas.get(i).getDestino();
                int orig = grafo.arestas.get(i).getOrigem();
                double peso = grafo.arestas.get(i).getPeso();
                int flag = grafo.arestas.get(i).getFlag();
                String desc = grafo.arestas.get(i).getDescricao();
                System.out.println("Aresta de id: " + id);
                System.out.println("Descriçao: " + desc);
                System.out.println("Vertice de Origem: " + orig);
                System.out.println("Vertice de Destino: " + dest);
                System.out.println("Peso: " + peso);
                System.out.println("Flag: " + flag);
                System.out.println("\n");

                //break;
            
        }

        }
        semaphore.release();
    }
    
    @Override
    public void listaArestasVertice(int nome) {
        try {
            semaphore.acquire();
    
    }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;
        
        System.out.println("Vertice: " + nome);
        for (i = 0; i < grafo.arestas.size(); i++) {
            if (grafo.arestas.get(i).origem == nome || grafo.arestas.get(i).destino == nome) {
                System.out.println("Aresta:"+grafo.arestas.get(i).id);
                System.out.println("Descriçao: "+grafo.arestas.get(i).descricao);
                System.out.println("Origem: " + grafo.arestas.get(i).origem);
                System.out.println("Destino: " + grafo.arestas.get(i).destino);
                System.out.println("Peso: " + grafo.arestas.get(i).peso);
                System.out.println("Flag: " + grafo.arestas.get(i).flag);
                System.out.println("\n");
            }
        }

        semaphore.release();

    }

    ///listar os vizinhos de um vertice
    @Override
    public void listaVizinhosVertice(int nome) {
        try {
            semaphore.acquire();
        }
    catch (Exception e) {

         e.printStackTrace();

    }
        int i;

        System.out.println("##Vizinhos do vértice " + nome+"##");
        for (i = 0; i < grafo.arestas.size(); i++) {
            if(grafo.arestas.get(i).getOrigem() == nome)
                System.out.println("Vertice "+grafo.arestas.get(i).getDestino());
            if(grafo.arestas.get(i).getDestino() == nome) {  
                System.out.println("Vertice "+grafo.arestas.get(i).getOrigem());
            }
            
        }
        
        
        semaphore.release();

    }

}

