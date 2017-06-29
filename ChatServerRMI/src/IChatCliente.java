

import java.rmi.*;

public interface IChatCliente extends Remote{	
	public void publicar (String nome)throws RemoteException ;
	public String getNome()throws RemoteException ;
}