

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
 
public class ChatServidor  extends UnicastRemoteObject implements IChatServidor{
	
	private Vector listaCliente = new Vector();	
	public ChatServidor() throws RemoteException{}
		
	public boolean registrarCliente(IChatCliente cliente) throws RemoteException{	
		System.out.println(cliente.getNome() + "  se conectou");	
		cliente.publicar("Você está conectado ao servidor de chat");
		enviarMensagens(cliente.getNome()+ " entrou no chat");
		listaCliente.add(cliente);
		System.out.println(listaCliente.size());
		return true;		
	}
	
	public void enviarMensagens(String msg) throws RemoteException{
	    System.out.println(msg);
		for(int i=0;i<listaCliente.size();i++){
		    try{
		    	IChatCliente tmp=(IChatCliente)listaCliente.get(i);
			tmp.publicar(msg);
		    }catch(Exception e){
		    	
		    }
		}
	}
 
	public Vector getConnected() throws RemoteException{
		return listaCliente;
	}

	@Override
	public boolean removerCliente(IChatCliente cliente) throws RemoteException {
		listaCliente.remove(cliente);
		System.out.println(cliente.getNome()+" DESCONECTADO");
		cliente.publicar("Você está desconectado do servidor de chat");
		enviarMensagens(cliente.getNome()+" desconectado");
		return true;
	}
}