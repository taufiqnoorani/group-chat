package com.chatapp;

// import com.sun.glass.ui.Application; // Removed to avoid conflict with JavaFX Application
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    String address;
    int port;

    @Override
     public void start(Stage primaryStage) throws Exception {
          var args = getParameters().getRaw();
          if (args.size() < 2) {
            System.err.println("Usage: mvn javafx:run -DmainArgs=\"localhost 1234\"");
            Platform.exit();
            return;
          }
          address = getParameters().getRaw().get(0);
          port = Integer.parseInt(getParameters().getRaw().get(1));

        this.window = primaryStage;
        this.window.setTitle("Client");

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(80);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        grid.getColumnConstraints().addAll(column0, column1);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(75);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(20);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(5);
        grid.getRowConstraints().addAll(row0, row1, row2);

        new Client(address, port, grid);

        Scene scene = new Scene(grid, 600, 520);
        this.window.setScene(scene);
        this.window.show();

        try {
          if (System.getProperty("os.name").toLowerCase().contains("mac")) {
          new ProcessBuilder(
            "osascript",
            "-e",
            "tell app \"System Events\" to set frontmost of the first process whose unix id is " + ProcessHandle.current().pid() + " to true"
          ).start();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}