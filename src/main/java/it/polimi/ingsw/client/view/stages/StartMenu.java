package it.polimi.ingsw.client.view.stages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;

import static it.polimi.ingsw.client.Client.NewConnection;
import static it.polimi.ingsw.client.view.GUI.*;
import static it.polimi.ingsw.client.view.stages.GameSettings.gameSettings;
import static it.polimi.ingsw.client.view.stages.JoinLobby.joinLobby;

/**
 * Graphics class to start GUI and insert server IP
 * @author Eugenio Facciolo
 */
public class StartMenu  {



    public static void startMenu(Stage startView) {

        // set title for the stage
        startView.setTitle("Santorini");



        InputStream input = StartMenu.class.getClassLoader().getResourceAsStream("title.png");

        assert input != null;
        ImageView title = new ImageView(new Image(input));

        AnchorPane.setTopAnchor(title, 600.0);
        AnchorPane.setLeftAnchor(title, 370.0);

        AnchorPane pane = new AnchorPane();

        pane.getChildren().add(title);

        // create a scene
        Scene scene = new Scene(pane, 1200, 800);


        // create a input stream
        input = StartMenu.class.getClassLoader().getResourceAsStream("background.png");


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
        startView.setScene(scene);
        startView.setResizable(false);
        startView.show();

        input = StartMenu.class.getClassLoader().getResourceAsStream("play-button.png");
        assert input != null;
        ImageView playbutton = new ImageView(new Image(input));

        playbutton.setFitHeight(150);
        playbutton.setFitWidth(150);

        Button play = new Button("", playbutton);
        play.setMaxHeight(10);
        play.setMaxWidth(10);
        play.setMinHeight(10);
        play.setMinWidth(10);

        AnchorPane.setTopAnchor(play, 650.0);
        AnchorPane.setLeftAnchor(play, 120.0);

        pane.getChildren().add(play);

        play.setOnAction(e -> {
            System.out.println("Play");

            InputStream input1 = StartMenu.class.getClassLoader().getResourceAsStream("play-button-press.png");
            assert input1 != null;
            ImageView playbuttonpress = new ImageView(new Image(input1));
            playbuttonpress.setFitHeight(150);
            playbuttonpress.setFitWidth(150);

            play.setGraphic(playbuttonpress);

            Stage dialog = new Stage();
            dialog.setTitle("Server connection");
            dialog.initStyle(StageStyle.UTILITY);

            Label label1 = new Label("Insert the IP of the server");
            TextField textField = new TextField();
            Button connect = new Button("Connect");


            HBox hb = new HBox();

            label1.setAlignment(Pos.CENTER_LEFT);
            textField.setAlignment(Pos.CENTER);
            connect.setAlignment(Pos.CENTER_RIGHT);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(10);

            hb.getChildren().addAll(label1, textField, connect);
            Scene scene1 = new Scene(hb, 500, 100);

            dialog.setScene(scene1);
            dialog.setResizable(false);
            dialog.show();

            connect.setOnAction(actionEvent -> {
                boolean condition;

                try {
                    condition = NewConnection(textField.getText());


                    if (!condition) {
                        Stage error = new Stage();
                        error.setTitle("Error!");
                        Label errormessage = new Label("Server unreachable");
                        HBox errorbox = new HBox();
                        errorbox.setAlignment(Pos.CENTER);
                        errorbox.getChildren().add(errormessage);

                        Scene errorview = new Scene(errorbox, 300, 100);

                        error.setScene(errorview);
                        error.setResizable(false);
                        error.show();


                    } else {
                        dialog.close();
                        try {

                            //userindex update
                            receiveMessage();

                            if (userindex == 1)
                                gameSettings(startView);
                            else {
                                //numofplayers update
                                receiveMessage();
                                System.out.println("Il numero di giocatori è: " + numofplayers);

                                for (int i = 0; i < numofplayers; i++)
                                    divinity.add(new Text("Divinity " + i));

                                joinLobby(startView, numofplayers);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });


        });


    }


}



