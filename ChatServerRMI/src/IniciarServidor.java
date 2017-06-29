

import java.rmi.*;
import java.rmi.server.*;
 
public class IniciarServidor {
	public static void main(String[] args) {
		try {
				//System.setSecurityManager(new RMISecurityManager());
			 	java.rmi.registry.LocateRegistry.createRegistry(1099);
			 	System.out.println("Registro Criado");
				IChatServidor servidor = new ChatServidor();	
				Naming.rebind("rmi://127.0.0.1/chat", servidor);
				System.out.println("Servidor de chat iniciado");
			}catch (Exception e) {
					System.out.println("Erro ao iniciar servidor de chat " + e);
			}
	}
}