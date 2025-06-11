package it.polimi.ingsw.client.view.stages;

import it.polimi.ingsw.client.view.GUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
 * Graphics class for the divinities choice by the first player
 * @author Eugenio Facciolo
 */
public class DivinitySelect {

    public static int divinitychoosen = 0;


    public static void divinitySelect(Stage DivinityStage) {
        //set title for the stage
        DivinityStage.setTitle("DivinitySelect");

        //set the top of the scene
        Text title1 = new Text("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
        title1.setFont(new Font("Arial", 30));
        title1.setFill(Color.WHITE);
        title1.setStrokeWidth(1);
        title1.setStroke(Color.BLACK);
        VBox title = new VBox(title1);
        title.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(title1, new Insets(35, 20, 0, 10));

        //Set the center of the scene (divinity description)
        Text description = new Text("Pick a divinity");
        description.setFont(new Font("Arial", 25));
        description.setFill(Color.GRAY);
        description.setStrokeWidth(1);
        description.setStroke(Color.BLACK);

        InputStream divinitypanelimage = DivinitySelect.class.getClassLoader().getResourceAsStream("divinitypanel.png");
        assert divinitypanelimage != null;
        ImageView divinitypanel = new ImageView(new Image(divinitypanelimage));
        divinitypanel.setFitHeight(500.0);
        divinitypanel.setFitWidth(1400.0);


        InputStream selectbuttonimage = DivinitySelect.class.getClassLoader().getResourceAsStream("selectbutton.png");
        assert selectbuttonimage != null;
        ImageView select = new ImageView(new Image(selectbuttonimage));
        select.setFitHeight(80);
        select.setFitWidth(250);
        Button selectbutton = new Button("", select);
        selectbutton.setMaxHeight(0);
        selectbutton.setMaxWidth(0);
        selectbutton.setMinHeight(0);
        selectbutton.setMinWidth(0);
        selectbutton.setVisible(false);


        //Set the center of the scene (divinity)
        InputStream apolloimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Apollo.png");
        assert apolloimage != null;
        ImageView apollo = new ImageView(new Image(apolloimage));
        apollo.setFitHeight(120);
        apollo.setFitWidth(100);
        Button apollobutton = new Button("", apollo);
        apollobutton.setOnAction(e -> {
            System.out.println("Pick Apollo");
            description.setText("Apollo's Power:\nYour worker can move into an enemy worker's cell \n(using normal move rules) and force him to occupy\n the new free cell by swapping positions.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e13 -> {

                System.out.println("Select Apollo");
                apollobutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {
                    GUI.sendMessage("Apollo", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        apollobutton.setMaxHeight(0);
        apollobutton.setMaxWidth(0);
        apollobutton.setMinHeight(0);
        apollobutton.setMinWidth(0);

        InputStream artemisimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Artemis.png");
        assert artemisimage != null;
        ImageView artemis = new ImageView(new Image(artemisimage));
        artemis.setFitHeight(120);
        artemis.setFitWidth(100);
        Button artemisbutton = new Button("", artemis);
        artemisbutton.setOnAction(e -> {
            System.out.println("Pick Artemis");
            description.setText("Artemis's Power:\nYour worker can move once more,\n but cannot return to the cell from which he started.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e14 -> {
                System.out.println("Select Artemis");
                artemisbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose " + divinitychoosen + "/" + GUI.numofplayers + " Divinity");
                try {
                    GUI.sendMessage("Artemis", 0, 0);

                    if (divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        artemisbutton.setMaxHeight(0);
        artemisbutton.setMaxWidth(0);
        artemisbutton.setMinHeight(0);
        artemisbutton.setMinWidth(0);

        InputStream athenaimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Athena.png");
        assert athenaimage != null;
        ImageView athena = new ImageView(new Image(athenaimage));
        athena.setFitHeight(120);
        athena.setFitWidth(100);
        Button athenabutton = new Button("", athena);
        athenabutton.setOnAction(e -> {
            System.out.println("Pick Athena");
            description.setText("Athena's Power:\n If one of your workers leveled on your last turn,\n enemy workers cannot level up this turn.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e15 -> {
                System.out.println("Select Athena");
                athenabutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose " + divinitychoosen + "/" + GUI.numofplayers + " Divinity");
                try {

                    GUI.sendMessage("Athena", 0, 0);

                    if (divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        athenabutton.setMaxHeight(0);
        athenabutton.setMaxWidth(0);
        athenabutton.setMinHeight(0);
        athenabutton.setMinWidth(0);

        InputStream atlasimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Atlas.png");
        assert atlasimage != null;
        ImageView atlas = new ImageView(new Image(atlasimage));
        atlas.setFitHeight(120);
        atlas.setFitWidth(100);
        Button atlasbutton = new Button("", atlas);
        atlasbutton.setOnAction(e -> {
            System.out.println("Pick Atlas");
            description.setText("Atlas's Power:\n Your worker can build a dome on any level, \n including the terrain.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e16 -> {

                System.out.println("Select Atlas");
                atlasbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Atlas", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        atlasbutton.setMaxHeight(0);
        atlasbutton.setMaxWidth(0);
        atlasbutton.setMinHeight(0);
        atlasbutton.setMinWidth(0);

        InputStream demeterimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Demeter.png");
        assert demeterimage != null;
        ImageView demeter = new ImageView(new Image(demeterimage));
        demeter.setFitHeight(120);
        demeter.setFitWidth(100);
        Button demeterbutton = new Button("", demeter);
        demeterbutton.setOnAction(e -> {
            System.out.println("Pick Demeter");
            description.setText("Demeter's Power:\n Your worker can build once more, but not in the same cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e18 -> {
                System.out.println("Select Demeter");
                demeterbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Demeter", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        demeterbutton.setMaxHeight(0);
        demeterbutton.setMaxWidth(0);
        demeterbutton.setMinHeight(0);
        demeterbutton.setMinWidth(0);

        InputStream hephaestusimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Hephaestus.png");
        assert hephaestusimage != null;
        ImageView hephaestus = new ImageView(new Image(hephaestusimage));
        hephaestus.setFitHeight(120);
        hephaestus.setFitWidth(100);
        Button hephaestusbutton = new Button("", hephaestus);
        hephaestusbutton.setOnAction(e -> {
            System.out.println("Pick Hephaestus");
            description.setText("Hephaestus's Power:\n Your worker can build an additional block (not a dome)\n above the first block.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e17 -> {
                System.out.println("Select Hephaestus");
                hephaestusbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Hephaestus", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        hephaestusbutton.setMaxHeight(0);
        hephaestusbutton.setMaxWidth(0);
        hephaestusbutton.setMinHeight(0);
        hephaestusbutton.setMinWidth(0);

        InputStream minotaurimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Minotaur.png");
        assert minotaurimage != null;
        ImageView minotaur = new ImageView(new Image(minotaurimage));
        minotaur.setFitHeight(120);
        minotaur.setFitWidth(100);
        Button minotaurbutton = new Button("", minotaur);
        minotaurbutton.setOnAction(e -> {
            System.out.println("Pick Minotaur");
            description.setText("Minotaur's Power:\n Your worker can move to an opposing worker's cell\n (according to the normal rules of the move)\n if the next cell in the same direction is free.\n The opposing worker is forced to move \nto that box (regardless of level).");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e19 -> {
                System.out.println("Select Minotaur");
                minotaurbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Minotaur", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        minotaurbutton.setMaxHeight(0);
        minotaurbutton.setMaxWidth(0);
        minotaurbutton.setMinHeight(0);
        minotaurbutton.setMinWidth(0);

        InputStream panimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Pan.png");
        assert panimage != null;
        ImageView pan = new ImageView(new Image(panimage));
        pan.setFitHeight(120);
        pan.setFitWidth(100);
        Button panbutton = new Button("", pan);
        panbutton.setOnAction(e -> {
            System.out.println("Pick Pan");
            description.setText("Pan's Power:\n You win even if your worker has fallen two or more levels ");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e110 -> {
                System.out.println("Select Pan");
                panbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose " + divinitychoosen + "/" + GUI.numofplayers + " Divinity");
                try {

                    GUI.sendMessage("Pan", 0, 0);

                    if (divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        panbutton.setMaxHeight(0);
        panbutton.setMaxWidth(0);
        panbutton.setMinHeight(0);
        panbutton.setMinWidth(0);

        InputStream prometheusimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Prometheus.png");
        assert prometheusimage != null;
        ImageView prometheus = new ImageView(new Image(prometheusimage));
        prometheus.setFitHeight(120);
        prometheus.setFitWidth(100);
        Button prometheusbutton = new Button("", prometheus);
        prometheusbutton.setOnAction(e -> {
            System.out.println("Pick Prometheus");
            description.setText("Prometheus's Power:\n If your worker doesn't level up,\n he can builds before and after the movement");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e1 -> {
                System.out.println("Select Prometheus");
                prometheusbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Prometheus", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        prometheusbutton.setMaxHeight(0);
        prometheusbutton.setMaxWidth(0);
        prometheusbutton.setMinHeight(0);
        prometheusbutton.setMinWidth(0);

        InputStream chronusimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Chronus.png");
        assert chronusimage != null;
        ImageView chronus = new ImageView(new Image(chronusimage));
        chronus.setFitHeight(120);
        chronus.setFitWidth(100);
        Button chronusbutton = new Button("", chronus);
        chronusbutton.setOnAction(e -> {
            System.out.println("Pick Chronus");
            description.setText("Chronus's Power:\nYou win even if there is 5 complete towers on the gameboard");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e111 -> {
                System.out.println("Select Chronus");
                chronusbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Chronus", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        chronusbutton.setMaxHeight(0);
        chronusbutton.setMaxWidth(0);
        chronusbutton.setMinHeight(0);
        chronusbutton.setMinWidth(0);

        InputStream hestiaimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Hestia.png");
        assert hestiaimage != null;
        ImageView hestia = new ImageView(new Image(hestiaimage));
        hestia.setFitHeight(120);
        hestia.setFitWidth(100);
        Button hestiabutton = new Button("", hestia);
        hestiabutton.setOnAction(e -> {
            System.out.println("Pick Hestia");
            description.setText("Hestia's Power:\nYour worker can build once more, but not in a perimetral cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e112 -> {
                System.out.println("Select Hestia");
                hestiabutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose " + divinitychoosen + "/" + GUI.numofplayers + " Divinity");
                try {

                    GUI.sendMessage("Hestia", 0, 0);

                    if (divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        hestiabutton.setMaxHeight(0);
        hestiabutton.setMaxWidth(0);
        hestiabutton.setMinHeight(0);
        hestiabutton.setMinWidth(0);

        InputStream poseidonimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Poseidon.png");
        assert poseidonimage != null;
        ImageView poseidon = new ImageView(new Image(poseidonimage));
        poseidon.setFitHeight(120);
        poseidon.setFitWidth(100);
        Button poseidonbutton = new Button("", poseidon);
        poseidonbutton.setOnAction(e -> {
            System.out.println("Pick Poseidon");
            description.setText("Poseidon's Power:\nYour worker can build until three times more if the \nother worker of the player is in a ground level cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e12 -> {
                System.out.println("Select Poseidon");
                poseidonbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Poseidon", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        poseidonbutton.setMaxHeight(0);
        poseidonbutton.setMaxWidth(0);
        poseidonbutton.setMinHeight(0);
        poseidonbutton.setMinWidth(0);

        InputStream tritonimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Triton.png");
        assert tritonimage != null;
        ImageView triton = new ImageView(new Image(tritonimage));
        triton.setFitHeight(120);
        triton.setFitWidth(100);
        Button tritonbutton = new Button("", triton);
        tritonbutton.setOnAction(e -> {
            System.out.println("Pick Triton");
            description.setText("Triton's Power:\nYour worker can move once more if he has moved in a perimetral cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e113 -> {
                System.out.println("Select Triton");
                tritonbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Triton", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        tritonbutton.setMaxHeight(0);
        tritonbutton.setMaxWidth(0);
        tritonbutton.setMinHeight(0);
        tritonbutton.setMinWidth(0);

        InputStream zeusimage = DivinitySelect.class.getClassLoader().getResourceAsStream("Zeus.png");
        assert zeusimage != null;
        ImageView zeus = new ImageView(new Image(zeusimage));
        zeus.setFitHeight(120);
        zeus.setFitWidth(100);
        Button zeusbutton = new Button("", zeus);
        zeusbutton.setOnAction(e -> {
            System.out.println("Pick Zeus");
            description.setText("Zeus's Power:\nYour worker can build a block under himself.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e114 -> {
                System.out.println("Select Zeus");
                zeusbutton.setVisible(false);
                divinitychoosen++;
                title1.setText("Choose "+divinitychoosen+"/"+ GUI.numofplayers+" Divinity");
                try {

                    GUI.sendMessage("Zeus", 0,0);

                    if(divinitychoosen == GUI.numofplayers)
                        GUI.receiveMessage();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                selectbutton.setVisible(false);
            });
        });
        zeusbutton.setMaxHeight(0);
        zeusbutton.setMaxWidth(0);
        zeusbutton.setMinHeight(0);
        zeusbutton.setMinWidth(0);

        //Set center of scene

        AnchorPane divinity1 = new AnchorPane(title, divinitypanel, description, selectbutton, apollobutton, artemisbutton, athenabutton, atlasbutton, demeterbutton, hephaestusbutton, minotaurbutton, panbutton, prometheusbutton, chronusbutton, hestiabutton, poseidonbutton, tritonbutton, zeusbutton);

        AnchorPane.setTopAnchor(divinitypanel, 350.0);
        AnchorPane.setLeftAnchor(divinitypanel, -100.0);

        AnchorPane.setTopAnchor(title, 5.0);
        AnchorPane.setLeftAnchor(title, 455.0);

        AnchorPane.setTopAnchor(description, 575.0);
        AnchorPane.setLeftAnchor(description, 150.0);

        AnchorPane.setTopAnchor(selectbutton, 450.0);
        AnchorPane.setLeftAnchor(selectbutton, 587.0);

        AnchorPane.setTopAnchor(apollobutton, 175.0);
        AnchorPane.setLeftAnchor(apollobutton, 180.0);

        AnchorPane.setTopAnchor(artemisbutton, 175.0);
        AnchorPane.setLeftAnchor(artemisbutton, 320.0);

        AnchorPane.setTopAnchor(athenabutton, 175.0);
        AnchorPane.setLeftAnchor(athenabutton, 460.0);

        AnchorPane.setTopAnchor(atlasbutton, 175.0);
        AnchorPane.setLeftAnchor(atlasbutton, 600.0);

        AnchorPane.setTopAnchor(demeterbutton, 175.0);
        AnchorPane.setLeftAnchor(demeterbutton, 740.0);

        AnchorPane.setTopAnchor(hephaestusbutton, 175.0);
        AnchorPane.setLeftAnchor(hephaestusbutton, 880.0);

        AnchorPane.setTopAnchor(minotaurbutton, 175.0);
        AnchorPane.setLeftAnchor(minotaurbutton, 1020.0);

        AnchorPane.setTopAnchor(panbutton, 310.0);
        AnchorPane.setLeftAnchor(panbutton, 180.0);

        AnchorPane.setTopAnchor(prometheusbutton, 310.0);
        AnchorPane.setLeftAnchor(prometheusbutton, 320.0);

        AnchorPane.setTopAnchor(chronusbutton, 310.0);
        AnchorPane.setLeftAnchor(chronusbutton, 460.0);

        AnchorPane.setTopAnchor(hestiabutton, 310.0);
        AnchorPane.setLeftAnchor(hestiabutton, 600.0);

        AnchorPane.setTopAnchor(poseidonbutton, 310.0);
        AnchorPane.setLeftAnchor(poseidonbutton, 740.0);

        AnchorPane.setTopAnchor(tritonbutton, 310.0);
        AnchorPane.setLeftAnchor(tritonbutton, 880.0);

        AnchorPane.setTopAnchor(zeusbutton, 310.0);
        AnchorPane.setLeftAnchor(zeusbutton, 1020.0);

        //Set the main layout background
        InputStream input = DivinitySelect.class.getClassLoader().getResourceAsStream("lobbybackground.png");
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
        BorderPane layout = new BorderPane(divinity1);
        layout.setBackground(background);

        // create a scene
        Scene scene = new Scene(layout, 1200, 800);

        // set the scene
        DivinityStage.setScene(scene);
        DivinityStage.setResizable(false);
        DivinityStage.show();

        title1.textProperty().addListener((observable, oldValue, newValue) -> {
            if(title1.getText().equals("Choose "+ GUI.numofplayers+"/"+ GUI.numofplayers+" Divinity")) {

                try {
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });


    }
}
