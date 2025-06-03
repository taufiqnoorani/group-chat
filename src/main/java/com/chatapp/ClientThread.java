package com.chatapp;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                for (ClientThread client : Server.clients) {
                    if (client != this) {
                        client.sendMessage("Client: " + msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}