package it.polimi.ingsw.client.view.stages;

import it.polimi.ingsw.client.view.GUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;

import static it.polimi.ingsw.client.view.stages.DivinitySelect.divinitySelect;
import static it.polimi.ingsw.client.view.stages.DivinitySelect2.divinitySelect2;


/**
 * Graphics class for waiting players
 * @author Eugenio Facciolo
 */
public class JoinLobby {

    public static void joinLobby(Stage LobbyStage, int playernum) throws IOException, ClassNotFoundException {

        //set title for the stage
        LobbyStage.setTitle("Lobby");

        //set the top of the scene
        Text title1 = new Text("Welcome to Lobby");
        title1.setFont(new Font("Arial", 40));
        title1.setFill(Color.WHITE);
        title1.setStrokeWidth(1);
        title1.setStroke(Color.BLACK);
        Text title2 = new Text("Waiting other players... ("+ GUI.userindex+"/"+playernum+")");
        title2.setFont(new Font("Arial", 20));
        title2.setFill(Color.DARKGRAY);
        title2.setStrokeWidth(0.3);
        title2.setStroke(Color.BLACK);
        InputStream paneltitleimage = JoinLobby.class.getClassLoader().getResourceAsStream("paneltitle.png");
        assert paneltitleimage != null;
        Image paneltitle = new Image(paneltitleimage);

        //Set the top of the scene with background
        VBox title = new VBox(title1, title2);
        title.setAlignment(Pos.CENTER);
        VBox.setMargin(title, new Insets(20, 20, 20, 20));
        BackgroundSize paneltitlesize = new BackgroundSize(500, 80, false, false, false, false);
        BackgroundImage backgroundpaneltitle = new BackgroundImage(paneltitle,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                paneltitlesize);
        Background backgroundpanel = new Background(backgroundpaneltitle);
        title.setBackground(backgroundpanel);

        //Set the center of the scene
        GUI.name.add(new Text("Player 1"));
        GUI.name.get(0).setFont(new Font("Arial", 30));
        GUI.name.get(0).setFill(Color.WHITE);
        GUI.name.get(0).setStrokeWidth(1);
        GUI.name.get(0).setStroke(Color.BLACK);
        InputStream crownimage = JoinLobby.class.getClassLoader().getResourceAsStream("crown.png");
        assert crownimage != null;
        ImageView crown = new ImageView(new Image(crownimage));
        crown.setFitWidth(40);
        crown.setFitHeight(40);
        HBox owner = new HBox();
        owner.getChildren().addAll(GUI.name.get(0), crown);
        owner.setAlignment(Pos.CENTER);

        GUI.name.add(new Text("Player 2"));
        GUI.name.get(1).setFont(new Font("Arial", 30));
        GUI.name.get(1).setFill(Color.WHITE);
        GUI.name.get(1).setStrokeWidth(1);
        GUI.name.get(1).setStroke(Color.BLACK);

        Image nameflags;

        if(GUI.numofplayers == 3) {
            GUI.name.add(new Text("Player 3"));
            GUI.name.get(2).setFont(new Font("Arial", 30));
            GUI.name.get(2).setFill(Color.WHITE);
            GUI.name.get(2).setStrokeWidth(1);
            GUI.name.get(2).setStroke(Color.BLACK);

            //insert nameflags image as background of center
            InputStream nameflagsimage = JoinLobby.class.getClassLoader().getResourceAsStream("threenameflags.png");
            assert nameflagsimage != null;
            nameflags = new Image(nameflagsimage);
        }
        else {
            InputStream nameflagsimage = JoinLobby.class.getClassLoader().getResourceAsStream("twonameflags.png");
            assert nameflagsimage != null;
            nameflags = new Image(nameflagsimage);
        }

        BackgroundSize flagssize = new BackgroundSize(350, 350, false, false, false, false);
        BackgroundImage backgroundflags = new BackgroundImage(nameflags,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                flagssize);
        Background backgroundflagsname = new Background(backgroundflags);

        //Set center of scene
        InputStream versusimage1 = JoinLobby.class.getClassLoader().getResourceAsStream("versus.png");
        assert versusimage1 != null;
        ImageView versus1 = new ImageView(new Image(versusimage1));
        InputStream versusimage2 = JoinLobby.class.getClassLoader().getResourceAsStream("versus.png");
        assert versusimage2 != null;
        ImageView versus2 = new ImageView(new Image(versusimage2));
        versus1.setFitWidth(35);
        versus1.setFitHeight(35);
        versus2.setFitWidth(35);
        versus2.setFitHeight(35);

        VBox names;

        if(GUI.numofplayers == 2) {
            names = new VBox(owner, versus1, GUI.name.get(1));
            names.setAlignment(Pos.CENTER);
            VBox.setMargin(owner, new Insets(-40, 0, 10, 0));
            VBox.setMargin(versus1, new Insets(35,0,0,0));
            VBox.setMargin(GUI.name.get(1), new Insets(55, 0, 20, 0));

        }
        else {
            names = new VBox(owner, versus1, GUI.name.get(1), versus2, GUI.name.get(2));
            names.setAlignment(Pos.CENTER);
            VBox.setMargin(owner, new Insets(15, 0, 0, 20));
            VBox.setMargin(versus1, new Insets(20,0,0,0));
            VBox.setMargin(GUI.name.get(1), new Insets(15, 0, 15, 0));
            VBox.setMargin(versus2, new Insets(10,0,0,0));
            VBox.setMargin(GUI.name.get(2), new Insets(15, 0, 35, 0));
        }

        names.setBackground(backgroundflagsname);

        //Set the main layout background
        InputStream input = JoinLobby.class.getClassLoader().getResourceAsStream("lobbybackground.png");
        assert input != null;
        Image image = new Image(input);

        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, false, false, false, false);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);
        Background background = new Background(backgroundimage);

        //Set a main layout of scene
        BorderPane layout = new BorderPane();
        layout.setTop(title);
        layout.setCenter(names);
        layout.setBackground(background);

        // create a scene
        Scene scene = new Scene(layout, 1200, 800);

        // set the scene
        LobbyStage.setScene(scene);
        LobbyStage.setResizable(false);
        LobbyStage.show();

        Stage dialog = new Stage();
        dialog.setTitle("Name");
        dialog.initStyle(StageStyle.UTILITY);

        Label label1 = new Label("Insert the nickname of the player");
        TextField textField = new TextField();
        Button ok = new Button("ok");

        HBox hb = new HBox();

        label1.setAlignment(Pos.CENTER_LEFT);
        textField.setAlignment(Pos.CENTER);
        ok.setAlignment(Pos.CENTER_RIGHT);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);

        hb.getChildren().addAll(label1, textField, ok);
        Scene window = new Scene(hb, 500, 100);

        for(int i = 0; i < GUI.userindex-1; i++) {
            GUI.name.get(i).setText(GUI.receiveMessage());
        }

        dialog.setScene(window);
        dialog.setResizable(false);
        dialog.show();

        ok.setOnAction(actionEvent -> {
            boolean condition = false;


            try {
                while (!condition) {

                    // name of the player
                    condition = GUI.sendMessage(textField.getText(), 0, 0);

                    if (!condition) {
                        Stage error = new Stage();
                        error.setTitle("Error!");
                        Label errormessage = new Label("Not available!");
                        HBox errorbox = new HBox();
                        errorbox.setAlignment(Pos.CENTER);
                        errorbox.getChildren().add(errormessage);

                        Scene errorview = new Scene(errorbox, 300, 100);

                        error.setScene(errorview);
                        error.setResizable(false);
                        error.show();

                        return;
                    }

                }

                dialog.close();

                GUI.name.get(GUI.userindex - 1).setText(textField.getText());


                //wait other players
                GUI.receiveMessage();


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        });


        InputStream startbuttoninput = JoinLobby.class.getClassLoader().getResourceAsStream("start-button.png");
        assert startbuttoninput != null;
        ImageView startbuttonimage = new ImageView(new Image(startbuttoninput));
        startbuttonimage.setFitHeight(200);
        startbuttonimage.setFitWidth(500);
        Button startbutton = new Button("", startbuttonimage);
        startbutton.setMaxHeight(0);
        startbutton.setMaxWidth(0);
        startbutton.setMinHeight(0);
        startbutton.setMinWidth(0);

        startbutton.setOnAction(e -> {
            System.out.println("Press Start");
            if (GUI.userindex == 1)
                divinitySelect(LobbyStage);
            else {
                divinitySelect2(LobbyStage);
            }
        });

        startbutton.setVisible(false);

        GUI.name.get(GUI.numofplayers-1).textProperty().addListener((observable, oldValue, newValue) -> {

            if (GUI.userindex == 1) startbutton.setVisible(true);
        });

        startbuttonimage.setFitHeight(150);
        startbuttonimage.setFitWidth(350);
        startbutton.setPadding(new Insets(-1200, 10, 10 ,1225));


        layout.setBottom(startbutton);

        if(GUI.userindex != 1) {

            GUI.name.get(GUI.numofplayers-1).textProperty().addListener((observable, oldValue, newValue) -> {
                title1.setText("Wait " + GUI.name.get(0).getText() + "'s choice");
                title2.setText(GUI.name.get(0).getText() + " is choosing " + playernum + " divinity");
            });

            GUI.divinity.get(0).textProperty().addListener((observable, oldValue, newValue) -> {
                if (GUI.userindex == 2) title1.setText(GUI.name.get(0).getText() + " makes his choice");
                if (GUI.userindex == 3) title1.setText(GUI.name.get(1).getText() + " makes his choice");
                title2.setText("Press Start and choose a divinity");
                startbutton.setVisible(true);
            });

        }


    }
}

