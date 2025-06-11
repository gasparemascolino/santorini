package it.polimi.ingsw.client.view.stages;

import it.polimi.ingsw.client.view.CellGUI;
import it.polimi.ingsw.client.view.GUI;
import javafx.scene.Group;
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
 * Game board graphics class
 * @author Eugenio Facciolo
 */
public class BoardLayout extends StackPane {

    public static final int TILE_SIZE = 110;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;
    public static CellGUI[][] board = new CellGUI[5][5];
    public static Text message= new Text();
    public static Text message2= new Text("");
    public static Group Tilegroup = new Group();
    public static AnchorPane gameboard;
    public static Button divinitypower;
    public static Button yes;
    public static Button no;
    public static ImageView divinitypowerimage;

    public static void board(Stage primaryStage){

        InputStream input = BoardLayout.class.getClassLoader().getResourceAsStream("heropower_inactive.png");
        assert input != null;
        divinitypowerimage = new ImageView(new Image(input));

        input = BoardLayout.class.getClassLoader().getResourceAsStream("heropower_active.png");
        assert input != null;
        ImageView divinitypoweractiveimage = new ImageView(new Image(input));

        input = BoardLayout.class.getClassLoader().getResourceAsStream("yes.png");
        assert input != null;
        ImageView yesimage = new ImageView(new Image(input));

        input = BoardLayout.class.getClassLoader().getResourceAsStream("no.png");
        assert input != null;
        ImageView noimage = new ImageView(new Image(input));


        yes = new Button("", yesimage);
        no = new Button("", noimage);
        divinitypower = new Button("", divinitypowerimage);

        divinitypower.setMaxHeight(0);
        divinitypower.setMaxWidth(0);
        divinitypower.setMinHeight(0);
        divinitypower.setMinWidth(0);

        yes.setMaxHeight(0);
        yes.setMaxWidth(0);
        yes.setMinHeight(0);
        yes.setMinWidth(0);

        no.setMaxHeight(0);
        no.setMaxWidth(0);
        no.setMinHeight(0);
        no.setMinWidth(0);

        divinitypower.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);

        //show request from server
        message.setFont(new Font("Arial", 40));
        message.setFill(Color.WHITE);
        message.setStrokeWidth(1.5);
        message.setStroke(Color.BLACK);


        //set cells on the gameboard
        gameboard = new AnchorPane();
        gameboard.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        gameboard.getChildren().addAll(Tilegroup);

        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {

                board[row][column] = new CellGUI(row, column);

                Tilegroup.getChildren().add(board[row][column]);
                board[row][column].relocate(column * BoardLayout.TILE_SIZE, row * BoardLayout.TILE_SIZE);

            }
        }


        // set background of game board
        input = BoardLayout.class.getClassLoader().getResourceAsStream("santoriniboard.png");
        assert input != null;
        Image bg = new Image(input);

        BackgroundSize backgroundSize = new BackgroundSize(1200, 800, false, false, false, false);
        BackgroundImage backgroundimage = new BackgroundImage(bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize);

        // set player details on screen
        InputStream Apolloinput = BoardLayout.class.getClassLoader().getResourceAsStream("Apollo.png");
        InputStream Artemisinput = BoardLayout.class.getClassLoader().getResourceAsStream("Artemis.png");
        InputStream Athenainput = BoardLayout.class.getClassLoader().getResourceAsStream("Athena.png");
        InputStream Atlasinput = BoardLayout.class.getClassLoader().getResourceAsStream("Atlas.png");
        InputStream Demeterinput = BoardLayout.class.getClassLoader().getResourceAsStream("Demeter.png");
        InputStream Hephaestusinput = BoardLayout.class.getClassLoader().getResourceAsStream("Hephaestus.png");
        InputStream Minotaurinput = BoardLayout.class.getClassLoader().getResourceAsStream("Minotaur.png");
        InputStream Paninput = BoardLayout.class.getClassLoader().getResourceAsStream("Pan.png");
        InputStream Prometheusinput = BoardLayout.class.getClassLoader().getResourceAsStream("Prometheus.png");
        InputStream Chronusinput = BoardLayout.class.getClassLoader().getResourceAsStream("Chronus.png");
        InputStream Hestiainput = BoardLayout.class.getClassLoader().getResourceAsStream("Hestia.png");
        InputStream Poseidoninput = BoardLayout.class.getClassLoader().getResourceAsStream("Poseidon.png");
        InputStream Tritoninput = BoardLayout.class.getClassLoader().getResourceAsStream("Triton.png");
        InputStream Zeusinput = BoardLayout.class.getClassLoader().getResourceAsStream("Zeus.png");

        ImageView divinitybox = new ImageView(new Image(input));

        switch (GUI.divinity.get(GUI.userindex-1).getText()) {

            case "Apollo":
                assert Apolloinput != null;
                divinitybox.setImage(new Image(Apolloinput));
                break;

            case "Artemis":
                assert Artemisinput != null;
                divinitybox.setImage(new Image(Artemisinput));
                break;

            case "Athena":
                assert Athenainput != null;
                divinitybox.setImage(new Image(Athenainput));
                break;

            case "Atlas":
                assert Atlasinput != null;
                divinitybox.setImage(new Image(Atlasinput));
                break;

            case "Demeter":
                assert Demeterinput != null;
                divinitybox.setImage(new Image(Demeterinput));
                break;

            case "Hephaestus":
                assert Hephaestusinput != null;
                divinitybox.setImage(new Image(Hephaestusinput));
                break;

            case "Minotaur":
                assert Minotaurinput != null;
                divinitybox.setImage(new Image(Minotaurinput));
                break;

            case "Pan":
                assert Paninput != null;
                divinitybox.setImage(new Image(Paninput));
                break;

            case "Prometheus":
                assert Prometheusinput != null;
                divinitybox.setImage(new Image(Prometheusinput));
                break;

            case "Chronus":
                assert Chronusinput != null;
                divinitybox.setImage(new Image(Chronusinput));
                break;

            case "Hestia":
                assert Hestiainput != null;
                divinitybox.setImage(new Image(Hestiainput));
                break;

            case "Poseidon":
                assert Poseidoninput != null;
                divinitybox.setImage(new Image(Poseidoninput));
                break;

            case "Triton":
                assert Tritoninput != null;
                divinitybox.setImage(new Image(Tritoninput));
                break;

            case "Zeus":
                assert Zeusinput != null;
                divinitybox.setImage(new Image(Zeusinput));
                break;
        }

        divinitybox.setFitHeight(300);
        divinitybox.setFitWidth(220);

        Text playerbox = new Text("Player "+ GUI.userindex);
        playerbox.setFont(new Font("Arial", 30));
        if(GUI.userindex==1) playerbox.setFill(Color.BLUE);
        else if(GUI.userindex==2) playerbox.setFill(Color.RED);
        else if(GUI.userindex==3) playerbox.setFill(Color.GREEN);
        playerbox.setStrokeWidth(0.3);
        playerbox.setStroke(Color.BLACK);

        message2= new Text("");
        message2.setFont(new Font("Arial", 40));
        message2.setFill(Color.WHITE);
        message2.setStrokeWidth(1.5);
        message2.setStroke(Color.BLACK);

        //setting main layout
        AnchorPane window = new AnchorPane(gameboard, message, message2, divinitypower, yes, no, divinitybox, playerbox);
        Background background = new Background(backgroundimage);
        window.setBackground(background);
        AnchorPane.setTopAnchor(gameboard, 120.0);
        AnchorPane.setLeftAnchor(gameboard, 315.0);
        AnchorPane.setTopAnchor(message, 50.0);
        AnchorPane.setLeftAnchor(message, 315.0);
        AnchorPane.setTopAnchor(divinitypower, 700.0);
        AnchorPane.setLeftAnchor(divinitypower, 1090.0);
        AnchorPane.setTopAnchor(yes, 600.0);
        AnchorPane.setLeftAnchor(yes, 1050.0);
        AnchorPane.setTopAnchor(no, 600.0);
        AnchorPane.setLeftAnchor(no, 1130.0);

        AnchorPane.setTopAnchor(divinitybox, 500.0);
        AnchorPane.setLeftAnchor(divinitybox, 10.0);
        AnchorPane.setTopAnchor(playerbox, 10.0);
        AnchorPane.setLeftAnchor(playerbox, 10.0);
        AnchorPane.setTopAnchor(message2, 700.0);
        AnchorPane.setLeftAnchor(message2, 315.0);

        //set scene
        Scene scene = new Scene(window, 1200, 800);
        primaryStage.setTitle("GAMEBOARD");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        yes.setOnAction(e -> {

            divinitypower.setGraphic(divinitypoweractiveimage);
            yes.setVisible(false);
            no.setVisible(false);

            try {
                GUI.sendMessage("", 1, 0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Tilegroup.setDisable(false);

        });

        no.setOnAction(e -> {

            divinitypower.setVisible(false);
            yes.setVisible(false);
            no.setVisible(false);

            try {
                GUI.sendMessage("", 0, 0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Tilegroup.setDisable(false);

        });

    }

    /**
     * Add notifications on the game screen
     * @param messagechanged a message from server
     */
    public static void ChangeMessage(String messagechanged) {
        message.setText(messagechanged);
    }
    public static void ChangeMessage2(String messagechanged) {
        message2.setText(messagechanged);
    }
}

