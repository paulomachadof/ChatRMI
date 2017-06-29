

import javax.swing.*;
import javax.swing.border.*;
 
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
 
public class ChatGUI{
  private ChatCliente cliente;
  private IChatServidor servidor;
  public void conectar(){
	    if (conectar.getText().equals("Conectar")){
	    	if (nome.getText().length()<2){JOptionPane.showMessageDialog(frame, "Insira um nome"); return;}
	    	if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "Informe o IP do servidor"); return;}	    	
	    	try{
				cliente=new ChatCliente(nome.getText());
	    		cliente.setGUI(this);
	    		
				servidor=(IChatServidor)Naming.lookup("rmi://"+ip.getText()+"/chat");
				servidor.registrarCliente(cliente);
				
				conectar.setText("Desconectar");	
				conectar.addActionListener(new ActionListener(){
				      public void actionPerformed(ActionEvent e){ desconectar();   }  });
				
	    	}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(frame, "Erro, não foi possível conectar....");}		  
	      }else{
	    	  conectar.setText("Conectar");
	    	  	
		}
	  }  
  
  public void desconectar(){
	  if(conectar.getText().equals("Desconectar")){
		  try {
				servidor.removerCliente(cliente);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	 
  }
  
  public void enviarMsg(){
    if (conectar.getText().equals("Conectar")){
    	JOptionPane.showMessageDialog(frame, "Você precisa se conectar primeiro"); return;	
    }
      String msg = tfMsg.getText();
      msg = "["+nome.getText()+"] " + msg;
      tfMsg.setText("");
      try{
    	  	servidor.enviarMensagens(msg);
  	  	}catch(Exception e){e.printStackTrace();}
  }
 
  public void adicionarMsg(String msg){  listaMsg.setText(listaMsg.getText()+"\n"+msg);  }
 
  
  
  public static void main(String [] args){
	System.out.println("Cliente Iniciado");
	ChatGUI c=new ChatGUI();
  }  
  
  //User Interface code.
  public ChatGUI(){
	    frame=new JFrame("Chat RMI");
	    JPanel main =new JPanel();
	    JPanel top =new JPanel();
	    JPanel cn =new JPanel();
	    JPanel bottom =new JPanel();
	    
	    ip=new JTextField();
	    tfMsg=new JTextField();
	    nome=new JTextField();
	    listaMsg=new JTextArea();
	    conectar=new JButton("Conectar");
	    JButton bt=new JButton("Enviar");
	    
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    
	    top.add(new JLabel("Nome: "));top.add(nome);    
	    top.add(new JLabel("IP do servidor: "));top.add(ip);
	    top.add(conectar);
	    
	    cn.add(new JScrollPane(listaMsg), BorderLayout.CENTER);  
	    
	    bottom.add(tfMsg, BorderLayout.CENTER);    
	    bottom.add(bt, BorderLayout.EAST);
	    
	    main.add(top, BorderLayout.NORTH);
	    main.add(cn, BorderLayout.CENTER);
	    main.add(bottom, BorderLayout.SOUTH);
	    main.setBorder(new EmptyBorder(10, 10, 10, 10) );

	    conectar.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ conectar();   }  });
	    bt.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ enviarMsg();   }  });
	    tfMsg.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ enviarMsg();   }  });
	    
	    frame.setContentPane(main);
	    frame.setSize(600,600);
	    frame.setVisible(true);  
	  }
	  JTextArea listaMsg;
	  JTextField tfMsg,ip, nome;
	  JButton conectar;
	  JFrame frame;
}
