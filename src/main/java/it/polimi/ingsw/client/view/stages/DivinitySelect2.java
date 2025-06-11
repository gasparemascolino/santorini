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
import java.util.ArrayList;

/**
 * Graphics class for the divinities choice by the second/third player
 * @author Eugenio Facciolo
 */
public class DivinitySelect2 {



    public static void divinitySelect2(Stage DivinityStage){
        //set title for the stage
        DivinityStage.setTitle("DivinitySelect");

        //set the top of the scene
        Text title1 = new Text("Choose a Divinity");
        title1.setFont(new Font("Arial", 38));
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

        InputStream divinitypanelimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("divinitypanel.png");
        assert divinitypanelimage != null;
        Image divinitypanel = new Image(divinitypanelimage);
        BackgroundSize backgrounddivinitypanelSize = new BackgroundSize(1200,1000, false, false, false, false);
        BackgroundImage backgrounddivinitypanelimage = new BackgroundImage(divinitypanel,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgrounddivinitypanelSize);
        Background backgrounddivinitypanel = new Background(backgrounddivinitypanelimage);

        InputStream selectbuttonimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("selectbutton.png");
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
        InputStream apolloimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Apollo.png");
        assert apolloimage != null;
        ImageView apollo = new ImageView(new Image(apolloimage));
        apollo.setFitHeight(300);
        apollo.setFitWidth(220);
        Button apollobutton = new Button("", apollo);
        apollobutton.setOnAction(e -> {
            System.out.println("Pick Apollo");
            description.setText("Apollo's Power:\nYour worker can move into an enemy worker's cell \n(using normal move rules) and force him to occupy\n the new free cell by swapping positions.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e1 -> {

                System.out.println("Select Apollo");
                try {
                    GUI.sendMessage("Apollo", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        apollobutton.setMaxHeight(0);
        apollobutton.setMaxWidth(0);
        apollobutton.setMinHeight(0);
        apollobutton.setMinWidth(0);

        InputStream artemisimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Artemis.png");
        assert artemisimage != null;
        ImageView artemis = new ImageView(new Image(artemisimage));
        artemis.setFitHeight(300);
        artemis.setFitWidth(220);
        Button artemisbutton = new Button("", artemis);
        artemisbutton.setOnAction(e -> {
            System.out.println("Pick Artemis");
            description.setText("Artemis's Power:\nYour worker can move once more,\n but cannot return to the cell from which he started.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e15 -> {

                System.out.println("Select Artemis");
                try {
                    GUI.sendMessage("Artemis", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        artemisbutton.setMaxHeight(0);
        artemisbutton.setMaxWidth(0);
        artemisbutton.setMinHeight(0);
        artemisbutton.setMinWidth(0);

        InputStream athenaimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Athena.png");
        assert athenaimage != null;
        ImageView athena = new ImageView(new Image(athenaimage));
        athena.setFitHeight(300);
        athena.setFitWidth(220);
        Button athenabutton = new Button("", athena);
        athenabutton.setOnAction(e -> {
            System.out.println("Pick Athena");
            description.setText("Athena's Power:\n If one of your workers leveled on your last turn,\n enemy workers cannot level up this turn.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e14 -> {
                System.out.println("Select Athena");
                try {
                    GUI.sendMessage("Athena", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        athenabutton.setMaxHeight(0);
        athenabutton.setMaxWidth(0);
        athenabutton.setMinHeight(0);
        athenabutton.setMinWidth(0);

        InputStream atlasimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Atlas.png");
        assert atlasimage != null;
        ImageView atlas = new ImageView(new Image(atlasimage));
        atlas.setFitHeight(300);
        atlas.setFitWidth(220);
        Button atlasbutton = new Button("", atlas);
        atlasbutton.setOnAction(e -> {
            System.out.println("Pick Atlas");
            description.setText("Atlas's Power:\n Your worker can build a dome on any level, \nincluding the terrain.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e16 -> {
                System.out.println("Select Atlas");
                try {
                    GUI.sendMessage("Atlas", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        atlasbutton.setMaxHeight(0);
        atlasbutton.setMaxWidth(0);
        atlasbutton.setMinHeight(0);
        atlasbutton.setMinWidth(0);

        InputStream demeterimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Demeter.png");
        assert demeterimage != null;
        ImageView demeter = new ImageView(new Image(demeterimage));
        demeter.setFitHeight(300);
        demeter.setFitWidth(220);
        Button demeterbutton = new Button("", demeter);
        demeterbutton.setOnAction(e -> {
            System.out.println("Pick Demeter");
            description.setText("Demeter's Power:\n Your worker can build once more, but not in the same cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e17 -> {
                System.out.println("Select Demeter");
                try {
                    GUI.sendMessage("Demeter", 0, 0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        demeterbutton.setMaxHeight(0);
        demeterbutton.setMaxWidth(0);
        demeterbutton.setMinHeight(0);
        demeterbutton.setMinWidth(0);

        InputStream hephaestusimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Hephaestus.png");
        assert hephaestusimage != null;
        ImageView hephaestus = new ImageView(new Image(hephaestusimage));
        hephaestus.setFitHeight(300);
        hephaestus.setFitWidth(220);
        Button hephaestusbutton = new Button("", hephaestus);
        hephaestusbutton.setOnAction(e -> {
            System.out.println("Pick Hephaestus");
            description.setText("Hephaestus's Power:\n Your worker can build an additional block (not a dome)\n above the first block.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e18 -> {
                System.out.println("Select Hephaestus");
                try {
                    GUI.sendMessage("Hephaestus", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        hephaestusbutton.setMaxHeight(0);
        hephaestusbutton.setMaxWidth(0);
        hephaestusbutton.setMinHeight(0);
        hephaestusbutton.setMinWidth(0);

        InputStream minotaurimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Minotaur.png");
        assert minotaurimage != null;
        ImageView minotaur = new ImageView(new Image(minotaurimage));
        minotaur.setFitHeight(300);
        minotaur.setFitWidth(220);
        Button minotaurbutton = new Button("", minotaur);
        minotaurbutton.setOnAction(e -> {
            System.out.println("Pick Minotaur");
            description.setText("Minotaur's Power:\n Your worker can move to an opposing worker's cell\n (according to the normal rules of the move)\n if the next cell in the same direction is free.\n The opposing worker is forced to move \nto that box (regardless of level).");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e12 -> {
                System.out.println("Select Minotaur");
                try {
                    GUI.sendMessage("Minotaur", 0, 0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        minotaurbutton.setMaxHeight(0);
        minotaurbutton.setMaxWidth(0);
        minotaurbutton.setMinHeight(0);
        minotaurbutton.setMinWidth(0);

        InputStream panimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Pan.png");
        assert panimage != null;
        ImageView pan = new ImageView(new Image(panimage));
        pan.setFitHeight(300);
        pan.setFitWidth(220);
        Button panbutton = new Button("", pan);
        panbutton.setOnAction(e -> {
            System.out.println("Pick Pan");
            description.setText("Pan's Power:\n You win even if your worker has fallen two or more levels ");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e19 -> {
                System.out.println("Select Pan");
                try {
                    GUI.sendMessage("Pan", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        panbutton.setMaxHeight(0);
        panbutton.setMaxWidth(0);
        panbutton.setMinHeight(0);
        panbutton.setMinWidth(0);

        InputStream prometheusimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Prometheus.png");
        assert prometheusimage != null;
        ImageView prometheus = new ImageView(new Image(prometheusimage));
        prometheus.setFitHeight(300);
        prometheus.setFitWidth(220);
        Button prometheusbutton = new Button("", prometheus);
        prometheusbutton.setOnAction(e -> {
            System.out.println("Pick Prometheus");
            description.setText("Prometheus's Power:\n If your worker doesn't level up,\n he can builds before and after the movement");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e112 -> {
                System.out.println("Select Prometheus");
                try {
                    GUI.sendMessage("Prometheus", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        prometheusbutton.setMaxHeight(0);
        prometheusbutton.setMaxWidth(0);
        prometheusbutton.setMinHeight(0);
        prometheusbutton.setMinWidth(0);

        InputStream chronusimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Chronus.png");
        assert chronusimage != null;
        ImageView chronus = new ImageView(new Image(chronusimage));
        chronus.setFitHeight(300);
        chronus.setFitWidth(220);
        Button chronusbutton = new Button("", chronus);
        chronusbutton.setOnAction(e -> {
            System.out.println("Pick Chronus");
            description.setText("Chronus's Power:\nYou win even if there is 5 complete towers on the gameboard");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e110 -> {
                System.out.println("Select Chronus");
                try {
                    GUI.sendMessage("Chronus", 0, 0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        chronusbutton.setMaxHeight(0);
        chronusbutton.setMaxWidth(0);
        chronusbutton.setMinHeight(0);
        chronusbutton.setMinWidth(0);

        InputStream hestiaimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Hestia.png");
        assert hestiaimage != null;
        ImageView hestia = new ImageView(new Image(hestiaimage));
        hestia.setFitHeight(300);
        hestia.setFitWidth(220);
        Button hestiabutton = new Button("", hestia);
        hestiabutton.setOnAction(e -> {
            System.out.println("Pick Hestia");
            description.setText("Hestia's Power:\nYour worker can build once more, but not in a perimetral cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e13 -> {
                System.out.println("Select Hestia");
                try {
                    GUI.sendMessage("Hestia", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        hestiabutton.setMaxHeight(0);
        hestiabutton.setMaxWidth(0);
        hestiabutton.setMinHeight(0);
        hestiabutton.setMinWidth(0);

        InputStream poseidonimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Poseidon.png");
        assert poseidonimage != null;
        ImageView poseidon = new ImageView(new Image(poseidonimage));
        poseidon.setFitHeight(300);
        poseidon.setFitWidth(220);
        Button poseidonbutton = new Button("", poseidon);
        poseidonbutton.setOnAction(e -> {
            System.out.println("Pick Poseidon");
            description.setText("Poseidon's Power:\nYour worker can build until three times more if the \nother worker of the player is in a ground level cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e113 -> {
                System.out.println("Select Poseidon");
                try {
                    GUI.sendMessage("Poseidon", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        poseidonbutton.setMaxHeight(0);
        poseidonbutton.setMaxWidth(0);
        poseidonbutton.setMinHeight(0);
        poseidonbutton.setMinWidth(0);

        InputStream tritonimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Triton.png");
        assert tritonimage != null;
        ImageView triton = new ImageView(new Image(tritonimage));
        triton.setFitHeight(300);
        triton.setFitWidth(220);
        Button tritonbutton = new Button("",triton);
        tritonbutton.setOnAction(e -> {
            System.out.println("Pick Triton");
            description.setText("Triton's Power:\nYour worker can move once more if \nhe has moved in a perimetral cell.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e111 -> {
                System.out.println("Select Triton");
                try {
                    GUI.sendMessage("Triton", 0,0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        tritonbutton.setMaxHeight(0);
        tritonbutton.setMaxWidth(0);
        tritonbutton.setMinHeight(0);
        tritonbutton.setMinWidth(0);

        InputStream zeusimage = DivinitySelect2.class.getClassLoader().getResourceAsStream("Zeus.png");
        assert zeusimage != null;
        ImageView zeus = new ImageView(new Image(zeusimage));
        zeus.setFitHeight(300);
        zeus.setFitWidth(220);
        Button zeusbutton = new Button("",zeus);
        zeusbutton.setOnAction(e -> {
            System.out.println("Pick Zeus");
            description.setText("Zeus's Power:\nYour worker can build a block under himself.");
            selectbutton.setVisible(true);
            selectbutton.setOnAction(e114 -> {
                System.out.println("Select Zeus");
                try {
                    GUI.sendMessage("Zeus", 0, 0);
                    LobbyReady.lobbyReady(DivinityStage);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
        zeusbutton.setMaxHeight(0);
        zeusbutton.setMaxWidth(0);
        zeusbutton.setMinHeight(0);
        zeusbutton.setMinWidth(0);

        ArrayList<Button> buttons = new ArrayList<>();



        for(int i = 0; i < GUI.numofplayers - GUI.userindex + 2; i++) {
            switch (GUI.divinity.get(i).getText()) {

                case "Apollo":
                    buttons.add(apollobutton);
                    break;
                case "Artemis":
                    buttons.add(artemisbutton);
                    break;
                case "Athena":
                    buttons.add(athenabutton);
                    break;
                case "Atlas":
                    buttons.add(atlasbutton);
                    break;
                case "Demeter":
                    buttons.add(demeterbutton);
                    break;
                case "Hephaestus":
                    buttons.add(hephaestusbutton);
                    break;
                case "Minotaur":
                    buttons.add(minotaurbutton);
                    break;
                case "Pan":
                    buttons.add(panbutton);
                    break;
                case "Prometheus":
                    buttons.add(prometheusbutton);
                    break;
                case "Chronus":
                    buttons.add(chronusbutton);
                    break;
                case "Hestia":
                    buttons.add(hestiabutton);
                    break;
                case "Poseidon":
                    buttons.add(poseidonbutton);
                    break;
                case "Triton":
                    buttons.add(tritonbutton);
                    break;
                case "Zeus":
                    buttons.add(zeusbutton);
                    break;

            }

        }



        //Set center of scene
        TilePane descriptionpanel = new TilePane(description,selectbutton);
        descriptionpanel.setBackground(backgrounddivinitypanel);
        descriptionpanel.setPrefTileHeight(190);
        descriptionpanel.setPrefTileWidth(100);
        descriptionpanel.setAlignment(Pos.CENTER);
        TilePane divinity;

        if(buttons.size() == 2)
            divinity = new TilePane(buttons.get(0), buttons.get(1)); // insert divinity chosen by divinityselect stage
        else
            divinity = new TilePane(buttons.get(0), buttons.get(1), buttons.get(2)); // insert divinity chosen by divinityselect stage

        divinity.setAlignment(Pos.CENTER);
        VBox container= new VBox(divinity,descriptionpanel);
        TilePane.setMargin(apollobutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(athenabutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(atlasbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(artemisbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(demeterbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(hephaestusbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(minotaurbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(panbutton, new Insets(250, 200, 150, 150));
        TilePane.setMargin(prometheusbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(chronusbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(hestiabutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(poseidonbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(tritonbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(zeusbutton, new Insets(250, 150, 200, 150));
        TilePane.setMargin(selectbutton, new Insets(250,10,10,600));

        //Set the main layout background
        InputStream input = DivinitySelect2.class.getClassLoader().getResourceAsStream("lobbybackground.png");
        assert input != null;
        Image image = new Image(input);

        BackgroundSize backgroundSize = new BackgroundSize(1200,800, false, false, false, false);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);
        Background background = new Background(backgroundimage);

        //Set a main layout of scene
        BorderPane layout = new BorderPane();
        layout.setTop(title);
        layout.setCenter(container);
        layout.setBackground(background);

        // create a scene
        Scene scene = new Scene(layout, 1200, 800);

        // set the scene
        DivinityStage.setScene(scene);
        DivinityStage.setResizable(false);
        DivinityStage.show();

    }
}