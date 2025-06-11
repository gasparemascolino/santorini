package it.polimi.ingsw.client.view.stages;

import it.polimi.ingsw.client.view.GUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import static it.polimi.ingsw.client.view.stages.BoardLayout.*;

/**
 * Graphics class to sum up game settings (player, divinity chosen, name)
 * @author Eugenio Facciolo
 */
public class LobbyReady {
    static int countdown = 3;

    public static void lobbyReady(Stage LobbyReadyStage) throws IOException, ClassNotFoundException {

        Label count = new Label("START!");

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> {

                    count.setVisible(true);
                    countdown--;

                    if(countdown == 1) {
                        board(LobbyReadyStage);
                    }

                }));

        //set title for the stage
        LobbyReadyStage.setTitle("LobbyReady");


        //set the 3 divinity chosen in background
        InputStream Apolloinput = LobbyReady.class.getClassLoader().getResourceAsStream("ApolloFullArt.png");
        InputStream Artemisinput = LobbyReady.class.getClassLoader().getResourceAsStream("ArtemisFullArt.png");
        InputStream Athenainput = LobbyReady.class.getClassLoader().getResourceAsStream("AthenaFullArt.png");
        InputStream Atlasinput = LobbyReady.class.getClassLoader().getResourceAsStream("AtlasFullArt.png");
        InputStream Demeterinput = LobbyReady.class.getClassLoader().getResourceAsStream("DemeterFullArt.png");
        InputStream Hephaestusinput = LobbyReady.class.getClassLoader().getResourceAsStream("HephaestusFullArt.png");
        InputStream Minotaurinput = LobbyReady.class.getClassLoader().getResourceAsStream("MinotaurFullArt.png");
        InputStream Paninput = LobbyReady.class.getClassLoader().getResourceAsStream("PanFullArt.png");
        InputStream Prometheusinput = LobbyReady.class.getClassLoader().getResourceAsStream("PrometheusFullArt.png");
        InputStream Chronusinput = LobbyReady.class.getClassLoader().getResourceAsStream("ChronusFullArt.png");
        InputStream Hestiainput = LobbyReady.class.getClassLoader().getResourceAsStream("HestiaFullArt.png");
        InputStream Poseidoninput = LobbyReady.class.getClassLoader().getResourceAsStream("PoseidonFullArt.png");
        InputStream Tritoninput = LobbyReady.class.getClassLoader().getResourceAsStream("TritonFullArt.png");
        InputStream Zeusinput = LobbyReady.class.getClassLoader().getResourceAsStream("ZeusFullArt.png");

       ArrayList<ImageView> divinityimage = new ArrayList<>();

        for(int i = 0; i < GUI.numofplayers; i++) {

            divinityimage.add(new ImageView());
            divinityimage.get(i).setFitWidth(300);
            divinityimage.get(i).setFitHeight(500);
        }


        AnchorPane divinityimages;
        InputStream mainlayoutinput;
        if(GUI.numofplayers == 2) {
            AnchorPane.setTopAnchor(divinityimage.get(0), 100.0);
            AnchorPane.setTopAnchor(divinityimage.get(1), 100.0);
            AnchorPane.setLeftAnchor(divinityimage.get(0), 275.0);
            AnchorPane.setLeftAnchor(divinityimage.get(1), 625.0);

            divinityimages = new AnchorPane(divinityimage.get(0), divinityimage.get(1));
            mainlayoutinput = LobbyReady.class.getClassLoader().getResourceAsStream("lobbyready2player.png");
        }
        else {
            AnchorPane.setTopAnchor(divinityimage.get(0), 100.0);
            AnchorPane.setTopAnchor(divinityimage.get(1), 100.0);
            AnchorPane.setTopAnchor(divinityimage.get(2), 100.0);
            AnchorPane.setLeftAnchor(divinityimage.get(0), 100.0);
            AnchorPane.setLeftAnchor(divinityimage.get(1), 450.0);
            AnchorPane.setLeftAnchor(divinityimage.get(2), 800.0);


            divinityimages = new AnchorPane(divinityimage.get(0), divinityimage.get(1), divinityimage.get(2));
            mainlayoutinput = LobbyReady.class.getClassLoader().getResourceAsStream("lobbyready3player.png");
        }
        //set mainlayoutimage in front of divinity images
        assert mainlayoutinput != null;
        ImageView  mainlayoutimage = new ImageView(new Image(mainlayoutinput));
        mainlayoutimage.setFitWidth(1200);
        mainlayoutimage.setFitHeight(800);

    InputStream input = LobbyReady.class.getClassLoader().getResourceAsStream("lobbybackground.png");
        assert input != null;
        Image image = new Image(input);

    BackgroundSize backgroundSize = new BackgroundSize(1200, 800, false, false, false, false);
    BackgroundImage backgroundimage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            backgroundSize);
    Background background = new Background(backgroundimage);


        // create a scene
        AnchorPane mainlayout = new AnchorPane(divinityimages,mainlayoutimage);
        Scene scene = new Scene(mainlayout, 1200, 800);
        mainlayout.setBackground(background);

        // set the scene
        LobbyReadyStage.setScene(scene);
        LobbyReadyStage.setResizable(false);
        LobbyReadyStage.show();

        Text wait = new Text("Waiting the others players");
        wait.setFont(new Font("Arial", 70));
        wait.setFill(Color.WHITE);
        wait.setStrokeWidth(0.3);
        wait.setStroke(Color.BLACK);


        AnchorPane.setTopAnchor(wait, 300.0);
        AnchorPane.setLeftAnchor(wait, 210.0);



        if(GUI.numofplayers == 2) {
            AnchorPane.setTopAnchor(GUI.name.get(0), 483.0);
            AnchorPane.setLeftAnchor(GUI.name.get(0), 412.0);
            AnchorPane.setTopAnchor(GUI.name.get(1), 494.0);
            AnchorPane.setLeftAnchor(GUI.name.get(1), 770.0);
            mainlayout.getChildren().addAll(wait, GUI.name.get(0), GUI.name.get(1));
        }
        else {
            AnchorPane.setTopAnchor(GUI.name.get(0), 485.0);
            AnchorPane.setLeftAnchor(GUI.name.get(0), 220.0);
            AnchorPane.setTopAnchor(GUI.name.get(1), 494.0);
            AnchorPane.setLeftAnchor(GUI.name.get(1), 590.0);
            AnchorPane.setTopAnchor(GUI.name.get(2), 494.0);
            AnchorPane.setLeftAnchor(GUI.name.get(2), 930.0);
            mainlayout.getChildren().addAll(wait, GUI.name.get(0), GUI.name.get(1), GUI.name.get(2));
        }

        AnchorPane.setTopAnchor(count, 200.0);
        AnchorPane.setLeftAnchor(count, 300.0);

        count.setTextFill(Color.WHITE);
        count.setStyle("-fx-font-size: 14em;");
        count.setVisible(false);

        mainlayout.getChildren().add(count);

        GUI.divinity.clear();

        for(int i = 0; i < GUI.numofplayers; i++)
            GUI.divinity.add(new Text("Divinity "+i));

        //set the divinity chosen by the player
        if(GUI.userindex!= 1) {
            GUI.divinity.get(GUI.userindex-1).setText(GUI.receiveMessage());

            System.out.println("La divinità scelta è: "+ GUI.divinity.get(GUI.userindex-1).getText());

            switch (GUI.divinity.get(GUI.userindex-1).getText()) {

                case "Apollo":
                    assert Apolloinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Apolloinput));
                    break;

                case "Artemis":
                    assert Artemisinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Artemisinput));
                    break;

                case "Athena":
                    assert Athenainput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Athenainput));
                    break;

                case "Atlas":
                    assert Atlasinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Atlasinput));
                    break;

                case "Demeter":
                    assert Demeterinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Demeterinput));
                    break;

                case "Hephaestus":
                    assert Hephaestusinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Hephaestusinput));
                    break;

                case "Minotaur":
                    assert Minotaurinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Minotaurinput));
                    break;

                case "Pan":
                    assert Paninput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Paninput));
                    break;

                case "Prometheus":
                    assert Prometheusinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Prometheusinput));
                    break;

                case "Chronus":
                    assert Chronusinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Chronusinput));
                    break;

                case "Hestia":
                    assert Hestiainput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Hestiainput));
                    break;

                case "Poseidon":
                    assert Poseidoninput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Poseidoninput));
                    break;

                case "Triton":
                    assert Tritoninput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Tritoninput));
                    break;

                case "Zeus":
                    assert Zeusinput != null;
                    divinityimage.get(GUI.userindex-1).setImage(new Image(Zeusinput));
                    break;
            }

            if(GUI.userindex == GUI.numofplayers) {


                wait.setVisible(false);
                for(int i = 0; i < GUI.numofplayers - 1; i++) {

                    switch (Objects.requireNonNull(GUI.receiveMessage())) {

                        case "Apollo":
                            assert Apolloinput != null;
                            divinityimage.get(i).setImage(new Image(Apolloinput));
                            break;

                        case "Artemis":
                            assert Artemisinput != null;
                            divinityimage.get(i).setImage(new Image(Artemisinput));
                            break;

                        case "Athena":
                            assert Athenainput != null;
                            divinityimage.get(i).setImage(new Image(Athenainput));
                            break;

                        case "Atlas":
                            assert Atlasinput != null;
                            divinityimage.get(i).setImage(new Image(Atlasinput));
                            break;

                        case "Demeter":
                            assert Demeterinput != null;
                            divinityimage.get(i).setImage(new Image(Demeterinput));
                            break;

                        case "Hephaestus":
                            assert Hephaestusinput != null;
                            divinityimage.get(i).setImage(new Image(Hephaestusinput));
                            break;

                        case "Minotaur":
                            assert Minotaurinput != null;
                            divinityimage.get(i).setImage(new Image(Minotaurinput));
                            break;

                        case "Pan":
                            assert Paninput != null;
                            divinityimage.get(i).setImage(new Image(Paninput));
                            break;

                        case "Prometheus":
                            assert Prometheusinput != null;
                            divinityimage.get(i).setImage(new Image(Prometheusinput));
                            break;

                        case "Chronus":
                            assert Chronusinput != null;
                            divinityimage.get(i).setImage(new Image(Chronusinput));
                            break;

                        case "Hestia":
                            assert Hestiainput != null;
                            divinityimage.get(i).setImage(new Image(Hestiainput));
                            break;

                        case "Poseidon":
                            assert Poseidoninput != null;
                            divinityimage.get(i).setImage(new Image(Poseidoninput));
                            break;

                        case "Triton":
                            assert Tritoninput != null;
                            divinityimage.get(i).setImage(new Image(Tritoninput));
                            break;

                        case "Zeus":
                            assert Zeusinput != null;
                            divinityimage.get(i).setImage(new Image(Zeusinput));
                            break;
                    }


                    timeline.setCycleCount(countdown);
                    timeline.play();
                }
            }

            // wait the others players
            GUI.receiveMessage();


        }






        GUI.divinity.get(0).textProperty().addListener((observable, oldValue, newValue) -> {
            wait.setVisible(false);
            System.out.println("Divinità aggiornate : "+ GUI.divinity);

            GUI.divinity.get(GUI.numofplayers - 1).getText();

            switch (GUI.divinity.get(0).getText()) {

                    case "Apollo":
                        assert Apolloinput != null;
                        divinityimage.get(0).setImage(new Image(Apolloinput));
                        break;

                    case "Artemis":
                        assert Artemisinput != null;
                        divinityimage.get(0).setImage(new Image(Artemisinput));
                        break;

                    case "Athena":
                        assert Athenainput != null;
                        divinityimage.get(0).setImage(new Image(Athenainput));
                        break;

                    case "Atlas":
                        assert Atlasinput != null;
                        divinityimage.get(0).setImage(new Image(Atlasinput));
                        break;

                    case "Demeter":
                        assert Demeterinput != null;
                        divinityimage.get(0).setImage(new Image(Demeterinput));
                        break;

                    case "Hephaestus":
                        assert Hephaestusinput != null;
                        divinityimage.get(0).setImage(new Image(Hephaestusinput));
                        break;

                    case "Minotaur":
                        assert Minotaurinput != null;
                        divinityimage.get(0).setImage(new Image(Minotaurinput));
                        break;

                    case "Pan":
                        assert Paninput != null;
                        divinityimage.get(0).setImage(new Image(Paninput));
                        break;

                    case "Prometheus":
                        assert Prometheusinput != null;
                        divinityimage.get(0).setImage(new Image(Prometheusinput));
                        break;

                    case "Chronus":
                        assert Chronusinput != null;
                        divinityimage.get(0).setImage(new Image(Chronusinput));
                        break;

                    case "Hestia":
                        assert Hestiainput != null;
                        divinityimage.get(0).setImage(new Image(Hestiainput));
                        break;

                    case "Poseidon":
                        assert Poseidoninput != null;
                        divinityimage.get(0).setImage(new Image(Poseidoninput));
                        break;

                    case "Triton":
                        assert Tritoninput != null;
                        divinityimage.get(0).setImage(new Image(Tritoninput));
                        break;

                    case "Zeus":
                        assert Zeusinput != null;
                        divinityimage.get(0).setImage(new Image(Zeusinput));
                        break;
                }

            timeline.setCycleCount(countdown);
            timeline.play();


        });

        GUI.divinity.get(1).textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Divinità aggiornate : " + GUI.divinity);


            switch (GUI.divinity.get(1).getText()) {

                case "Apollo":
                    assert Apolloinput != null;
                    divinityimage.get(1).setImage(new Image(Apolloinput));
                    break;

                case "Artemis":
                    assert Artemisinput != null;
                    divinityimage.get(1).setImage(new Image(Artemisinput));
                    break;

                case "Athena":
                    assert Athenainput != null;
                    divinityimage.get(1).setImage(new Image(Athenainput));
                    break;

                case "Atlas":
                    assert Atlasinput != null;
                    divinityimage.get(1).setImage(new Image(Atlasinput));
                    break;

                case "Demeter":
                    assert Demeterinput != null;
                    divinityimage.get(1).setImage(new Image(Demeterinput));
                    break;

                case "Hephaestus":
                    assert Hephaestusinput != null;
                    divinityimage.get(1).setImage(new Image(Hephaestusinput));
                    break;

                case "Minotaur":
                    assert Minotaurinput != null;
                    divinityimage.get(1).setImage(new Image(Minotaurinput));
                    break;

                case "Pan":
                    assert Paninput != null;
                    divinityimage.get(1).setImage(new Image(Paninput));
                    break;

                case "Prometheus":
                    assert Prometheusinput != null;
                    divinityimage.get(1).setImage(new Image(Prometheusinput));
                    break;

                case "Chronus":
                    assert Chronusinput != null;
                    divinityimage.get(1).setImage(new Image(Chronusinput));
                    break;

                case "Hestia":
                    assert Hestiainput != null;
                    divinityimage.get(1).setImage(new Image(Hestiainput));
                    break;

                case "Poseidon":
                    assert Poseidoninput != null;
                    divinityimage.get(1).setImage(new Image(Poseidoninput));
                    break;

                case "Triton":
                    assert Tritoninput != null;
                    divinityimage.get(1).setImage(new Image(Tritoninput));
                    break;

                case "Zeus":
                    assert Zeusinput != null;
                    divinityimage.get(1).setImage(new Image(Zeusinput));
                    break;
            }


        });

        if(GUI.numofplayers == 3)
            GUI.divinity.get(2).textProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Divinità aggiornate : " + GUI.divinity);


                switch (GUI.divinity.get(2).getText()) {

                    case "Apollo":
                        assert Apolloinput != null;
                        divinityimage.get(2).setImage(new Image(Apolloinput));
                        break;

                    case "Artemis":
                        assert Artemisinput != null;
                        divinityimage.get(2).setImage(new Image(Artemisinput));
                        break;

                    case "Athena":
                        assert Athenainput != null;
                        divinityimage.get(2).setImage(new Image(Athenainput));
                        break;

                    case "Atlas":
                        assert Atlasinput != null;
                        divinityimage.get(2).setImage(new Image(Atlasinput));
                        break;

                    case "Demeter":
                        assert Demeterinput != null;
                        divinityimage.get(2).setImage(new Image(Demeterinput));
                        break;

                    case "Hephaestus":
                        assert Hephaestusinput != null;
                        divinityimage.get(2).setImage(new Image(Hephaestusinput));
                        break;

                    case "Minotaur":
                        assert Minotaurinput != null;
                        divinityimage.get(2).setImage(new Image(Minotaurinput));
                        break;

                    case "Pan":
                        assert Paninput != null;
                        divinityimage.get(2).setImage(new Image(Paninput));
                        break;

                    case "Prometheus":
                        assert Prometheusinput != null;
                        divinityimage.get(2).setImage(new Image(Prometheusinput));
                        break;

                    case "Chronus":
                        assert Chronusinput != null;
                        divinityimage.get(2).setImage(new Image(Chronusinput));
                        break;

                    case "Hestia":
                        assert Hestiainput != null;
                        divinityimage.get(2).setImage(new Image(Hestiainput));
                        break;

                    case "Poseidon":
                        assert Poseidoninput != null;
                        divinityimage.get(2).setImage(new Image(Poseidoninput));
                        break;

                    case "Triton":
                        assert Tritoninput != null;
                        divinityimage.get(2).setImage(new Image(Tritoninput));
                        break;

                    case "Zeus":
                        assert Zeusinput != null;
                        divinityimage.get(2).setImage(new Image(Zeusinput));
                        break;
                }


            });

    }






    }


