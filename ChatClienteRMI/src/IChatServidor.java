

import java.rmi.*;
import java.util.*;
 
public interface IChatServidor extends Remote{	
	public boolean registrarCliente (IChatCliente cliente)throws RemoteException ;
	public boolean removerCliente(IChatCliente cliente)throws RemoteException;
	public void enviarMensagens (String msg)throws RemoteException ;
	public Vector getConnected() throws RemoteException ;
}


