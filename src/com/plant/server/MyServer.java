package com.plant.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.plant.frame.NodeJPanel;
import com.plant.util.ImageInput;


public class MyServer{
	public ServerSocket server;
//	public Vector<ManageClient> clients = new Vector<ManageClient>();
	public Map<String, ManageClient> clients = new HashMap<String, ManageClient>();
	
	public MyServer() throws Exception{
		server = new ServerSocket(80, 10);
//		new ImageInput();
		System.out.println("PlantFactory is running!");
		while (true) {
			Socket client = server.accept();
			
            ManageClient c = new ManageClient(client);
		}
	}
	
    public void sendtothread(String threadname, String message,byte[] mesg) {
        for (Map.Entry<String, ManageClient> entry : clients.entrySet()) {  
        	 if (entry.getKey().equals(threadname)) {
              // entry.getValue().output.println(message);
//        	System.out.println(mesg[0]+mesg[1]);
               entry.getValue().output.println(Arrays.toString(mesg));
           }
        }  
    }


	public static void main(String[] args) throws Exception{
		new MyServer();
	}
	
	class ManageClient extends Thread {

	        String gotthread = "";
	        BufferedReader input;
	        PrintWriter output;

	        public ManageClient(Socket client) throws Exception {

	            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
	            output = new PrintWriter(client.getOutputStream(), true);

	            gotthread = input.readLine();
	            System.out.println(gotthread);
	            clients.put(gotthread,this);     //Ìí¼Óµ½hashmap
	            start();
	        }

	        @Override
	        public void run() {
	            String line;
	            byte[] bt ;
	            try {
	                while (true) {
	                    line = input.readLine();
	                    bt = input.readLine().getBytes();
//	                    if (line.equals("end")) {
//	                        clients.remove(this);
////	                    users.remove(gotthread);
//	                        break;
//	                    }
	                    sendtothread("00", line,bt); 
	                }
	            } 
	            catch (Exception ex) {
	                System.out.println(ex.getMessage());
	            }
	        } 
	    } 
}
