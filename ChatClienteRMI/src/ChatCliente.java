

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
 
public class ChatCliente  extends UnicastRemoteObject implements IChatCliente{
	
	private String nome;
	private ChatGUI ui;	
	public ChatCliente (String n) throws RemoteException {
		nome=n;
		}
	
	public void publicar(String msg) throws RemoteException{
		System.out.println(msg);
		ui.adicionarMsg(msg);
	}
	public String getNome() throws RemoteException{
		return nome;
	}
	
	public void setGUI(ChatGUI t){ 
		ui=t ; 
	} 	
}
