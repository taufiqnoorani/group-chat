package com.chatapp;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    @SuppressWarnings("unused")
    public Client(String address, int port, GridPane grid) {
        try {
            socket = new Socket(address, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            TextArea messages = new TextArea();
            messages.setEditable(false);
            GridPane.setConstraints(messages, 0, 0, 2, 1);

            TextField input = new TextField();
            GridPane.setConstraints(input, 0, 1);

            Button sendBtn = new Button("Send");
            GridPane.setConstraints(sendBtn, 1, 1);

            sendBtn.setOnAction(e -> {
                String text = input.getText();
                out.println(text);
                input.clear();
            });

            grid.getChildren().addAll(messages, input, sendBtn);

            // Listen to server
            new Thread(() -> {
                String line;
                try {
                    while ((line = in.readLine()) != null) {
                        String msg = line;
                        Platform.runLater(() -> messages.appendText(msg + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}