package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.view.stages.BoardLayout;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStream;

import static it.polimi.ingsw.client.view.GUI.*;
import static it.polimi.ingsw.client.view.stages.BoardLayout.*;

/**
 * Graphics class for game board cells
 * @author Eugenio Facciolo
 */
public class CellGUI extends StackPane {

    public int row,column;
    private ImageView workerimage;
    private int workerindex=-1;
    public int workeruserindex=-1;
    private int level;

    public CellGUI(int x, int y) {
        row = x;
        column = y;
        level = 0;


        InputStream startbuttoninput = CellGUI.class.getClassLoader().getResourceAsStream("level-0.png");
        assert startbuttoninput != null;
        ImageView level0 = new ImageView(new Image(startbuttoninput));

        Button cellbutton = new Button("", level0);
        cellbutton.setOpacity(0.3);

        cellbutton.relocate(y * BoardLayout.TILE_SIZE, x * BoardLayout.TILE_SIZE);

        this.getChildren().add(cellbutton);
        this.relocate(y * BoardLayout.TILE_SIZE, x * BoardLayout.TILE_SIZE);

        cellbutton.setOnMousePressed(event -> {
            System.out.println("mouse click detected! " + event.getSource());
            System.out.println(row + "," + column);

            if (userindex == currentplayer) {
                try {

                    if (!sendMessage("", row, column)) {
                        ChangeMessage2("Unavailable, retry!");
                    } else {
                        // UPDATE
                        receiveMessage();
                        // NEW MESSAGE
                        receiveMessage();
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void setWorker(int userindex, int workerindex){


        if (userindex==1) {
            if (workerindex == 1) {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("blue-male-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            } else {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("blue-female-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            }
        }

        if(userindex==2) {
            if (workerindex == 1) {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("red-male-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            } else {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("red-female-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            }
        }

        if(userindex==3) {
            if (workerindex == 1) {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("green-male-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            } else {
                InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("green-female-builder.png");
                assert input != null;
                workerimage = new ImageView(new Image(input));
                this.getChildren().add(workerimage);
            }
        }

        this.workerindex=workerindex;
        this.workeruserindex=userindex;

        workerimage.setDisable(true);

    }

    public void removeWorker(){
        this.workerindex=-1;
        this.workeruserindex=-1;
        this.getChildren().remove(workerimage);
    }

    public int getWorkeruserindex(){
        return this.workeruserindex;
    }

    public int getWorkerindex(){
        return this.workerindex;
    }

    public void setLevel(int level){

        InputStream input = CellGUI.class.getClassLoader().getResourceAsStream("level-0.png");
        assert input != null;
        Image image= new Image(input);
        ImageView imageview = null;

        int oldlevel = this.level;

        this.level = level;



        switch (level) {

            case 1:
                input = CellGUI.class.getClassLoader().getResourceAsStream("level-1.png");
                assert input != null;
                image = new Image(input);
                break;

            case 2:
                input = CellGUI.class.getClassLoader().getResourceAsStream("level-2.png");
                assert input != null;
                image = new Image(input);
                break;

            case 3:
                input = CellGUI.class.getClassLoader().getResourceAsStream("level-3.png");
                assert input != null;
                image = new Image(input);
                break;
            case 4:
                input = CellGUI.class.getClassLoader().getResourceAsStream("level-4.png");
                assert input != null;
                image = new Image(input);
                break;
            case 5:
                input = CellGUI.class.getClassLoader().getResourceAsStream("dome.png");
                assert input != null;
                imageview = new ImageView(new Image(input));
                break;

        }

        if (level != 5) {


            BackgroundSize backgroundSize = new BackgroundSize(110, 110, false, false, false, false);
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    backgroundSize);
            Background background = new Background(backgroundimage);

            this.setBackground(background);
        }
        else {
            this.setLevel(oldlevel-1);
            this.getChildren().add(imageview);
        }
    }
    
    public int getLevel() {
        return this.level;
    }

}

