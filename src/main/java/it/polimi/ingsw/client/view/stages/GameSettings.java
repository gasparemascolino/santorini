package it.polimi.ingsw.client.view.stages;

import it.polimi.ingsw.client.view.GUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.InputStream;

/**
 * Graphics class to set a 2 or 3 players layout
 * @author Eugenio Facciolo
 */
public class GameSettings {


    public static void gameSettings(Stage stage) {
        // set title for the stage
        stage.setTitle("Game settings");

        AnchorPane pane = new AnchorPane();


        // create a scene
        Scene scene = new Scene(pane, 1200, 800);


        // create a input stream
        InputStream input = GameSettings.class.getClassLoader().getResourceAsStream("lobbybackground.png");


        // background image
        assert input != null;
        Image bg = new Image(input);

        // create a Background Size
        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, false, false, false, false);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);

        // create Background
        Background background = new Background(backgroundimage);


        // set background
        pane.setBackground(background);

        // set the scene
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        input = GameSettings.class.getClassLoader().getResourceAsStream("two-players.png");
        assert input != null;
        ImageView two = new ImageView(new Image(input));

        two.setFitHeight(300);
        two.setFitWidth(300);

        Text text = new Text("How many players?");
        text.setFont(new Font("Arial", 50));
        text.setFill(Color.WHITE);
        text.setStrokeWidth(1);
        text.setStroke(Color.BLACK);

        AnchorPane.setLeftAnchor(text, 385.0);
        AnchorPane.setTopAnchor(text, 150.0);

        Button twoplayers = new Button("", two);
        twoplayers.setMaxHeight(10);
        twoplayers.setMaxWidth(10);
        twoplayers.setMinHeight(10);
        twoplayers.setMinWidth(10);

        AnchorPane.setTopAnchor(twoplayers, 360.0);
        AnchorPane.setLeftAnchor(twoplayers, 350.0);

        input = GameSettings.class.getClassLoader().getResourceAsStream("three-players.png");
        assert input != null;
        ImageView three = new ImageView(new Image(input));

        three.setFitHeight(300);
        three.setFitWidth(300);

        Button threeplayers = new Button("", three);
        threeplayers.setMaxHeight(10);
        threeplayers.setMaxWidth(10);
        threeplayers.setMinHeight(10);
        threeplayers.setMinWidth(10);

        AnchorPane.setTopAnchor(threeplayers, 360.0);
        AnchorPane.setLeftAnchor(threeplayers, 825.0);

        pane.getChildren().add(twoplayers);
        pane.getChildren().add(threeplayers);
        pane.getChildren().add(text);

        twoplayers.setOnAction(e -> {
            try {
                GUI.numofplayers = 2;

                for (int i = 0; i < GUI.numofplayers; i++)
                    GUI.divinity.add(new Text("Divinity " + i));

                GUI.sendMessage("", 2, 0);
                JoinLobby.joinLobby(stage, GUI.numofplayers);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }


        });

        threeplayers.setOnAction(e -> {
            try {
                GUI.numofplayers = 3;

                for (int i = 0; i < GUI.numofplayers; i++)
                    GUI.divinity.add(new Text("Divinity " + i));

                GUI.sendMessage("", 3, 0);
                JoinLobby.joinLobby(stage, GUI.numofplayers);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
    }
}




