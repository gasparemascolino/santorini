package it.polimi.ingsw.client.view;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

import static it.polimi.ingsw.client.view.GUI.*;
import static it.polimi.ingsw.client.view.stages.BoardLayout.*;
import static it.polimi.ingsw.client.view.stages.BoardLayout.ChangeMessage;
import static it.polimi.ingsw.client.view.stages.BoardLayout.ChangeMessage2;

/**
 * When the CLI is deactivated, NotifyCLi receives the notifications to update it
 * @author Eugenio Facciolo
 */

public class NotifyGUI implements Runnable{

    private ObjectInputStream input;
    private boolean connection=true;

    public NotifyGUI(ObjectInputStream input)
    {
        this.input=input;
    }

    @Override
    public void run() {

        try {
            handleServerConnection();
        } catch (SocketException | InterruptedException | ClassNotFoundException e) {
            try {
                server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Platform.runLater(() -> {
                Stage error = new Stage();
                error.setTitle("Error!");
                Label errormessage = new Label("Connection drop by server");
                HBox errorbox = new HBox();
                errorbox.setAlignment(Pos.CENTER);
                errorbox.getChildren().add(errormessage);
                Scene errorview = new Scene(errorbox, 300, 100);
                error.setScene(errorview);
                error.setResizable(false);
                error.show();
                if(gameboard!=null) gameboard.setDisable(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handle the messages received by the server
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     * @throws InterruptedException for deadlock
     */
    private synchronized void handleServerConnection() throws IOException, ClassNotFoundException, InterruptedException {

        wait();

        while (connection) {



            System.out.println("Listening to the server....");
            //print output from server

            try {
                String responsesend = (String) input.readObject();

            System.out.println(responsesend);

            switch (responsesend) {

                case "Update player name":
                    // Update the other players
                    name.get((int) input.readObject()).setText((String) input.readObject());
                    break;

                case "Update available divinities":
                    for (int i = 0; i < numofplayers - userindex + 2; i++) {
                        divinity.get(i).setText((String) input.readObject());
                        System.out.println(divinity.get(i).getText());
                    }
                    break;

                case "Update divinity name":
                    divinity.get((int) input.readObject()).setText((String) input.readObject());
                    break;

                case "Update current client":
                    currentplayer = (int) input.readObject();
                    break;

                case "Set worker position":

                    currentworker = (int) input.readObject();
                    int row=(int) input.readObject();
                    int column=(int) input.readObject();


                    Platform.runLater(() -> board[row][column].setWorker(currentplayer, currentworker)
                    );

                    break;

                case "Move Worker":

                    currentworker = (int) input.readObject();

                    int oldrow=(int) input.readObject();
                    int oldcolumn=(int) input.readObject();

                    int oldworkeruserindex = board[oldrow][oldcolumn].getWorkeruserindex();
                    int oldworkerindex = board[oldrow][oldcolumn].getWorkerindex();

                    Platform.runLater(() -> board[oldrow][oldcolumn].removeWorker()
                    );

                    int newrow = (int) input.readObject();
                    int newcolumn = (int) input.readObject();

                    //Apollo and Minotaur Power
                    if(board[newrow][newcolumn].getWorkeruserindex()!=-1) {



                        int workerpusheduserindex = board[newrow][newcolumn].getWorkeruserindex();
                        int workerpushedindex = board[newrow][newcolumn].getWorkerindex();


                        //Divinity name
                        String divinity = (String) input.readObject();


                        if (divinity.equals("Apollo")) {

                            Platform.runLater(() -> {
                                        board[newrow][newcolumn].removeWorker();
                                        board[oldrow][oldcolumn].setWorker(workerpusheduserindex, workerpushedindex);

                                    }
                            );
                        }

                        if(divinity.equals("Minotaur")) {


                            String message = (String) input.readObject();
                            System.out.println(message);

                            if(message.equals("Push done")) {

                                int pushedrow = (int) input.readObject();
                                int pushedcolumn = (int) input.readObject();

                                Platform.runLater(() -> {

                                            board[newrow][newcolumn].removeWorker();
                                            board[pushedrow][pushedcolumn].setWorker(workerpusheduserindex, workerpushedindex);
                                        }
                                );

                            }
                            else {
                                Platform.runLater(() -> board[oldrow][oldcolumn].setWorker(oldworkeruserindex, oldworkerindex)
                                );

                                break;
                            }
                        }


                    }

                    Platform.runLater(() -> board[newrow][newcolumn].setWorker(currentplayer, currentworker)
                    );

                    break;

                case "Build a block":

                    int buildrow = (int) input.readObject();
                    int buildcolumn = (int) input.readObject();
                    int buildlevel= (int) input.readObject();

                    Platform.runLater(() -> board[buildrow][buildcolumn].setLevel(buildlevel)
                    );

                    break;

                case "Notify setDome":
                    int domerow = (int) input.readObject();
                    int domecolumn = (int) input.readObject();
                    Platform.runLater(() -> board[domerow][domecolumn].setLevel(5));

                    break;

                case "Notify useDivinityPower":
                    ChangeMessage2((String) input.readObject());
                    break;

                case "Can't level up":

                    int preresetrow = (int) input.readObject();
                    int preresetcolumn = (int) input.readObject();
                    int resetrow = (int) input.readObject();
                    int resetcolumn = (int) input.readObject();

                    int currentworker =  board[preresetrow][preresetcolumn].getWorkerindex();
                    int currentuser =  board[preresetrow][preresetcolumn].getWorkeruserindex();

                    Platform.runLater(() -> {

                        board[preresetrow][preresetcolumn].removeWorker();
                        board[resetrow][resetcolumn].setWorker(currentuser, currentworker);

                    });

                    break;

                case "StartTurn":
                    System.out.println("NotifyGUI stop listening to the server...");
                    if(!message2.getText().equals("Athena Power is activated")) ChangeMessage2("");
                    wait();
                    break;

                case "EndGame":
                    ChangeMessage((String) input.readObject());
                    connection=false;
                    server.close();
                    break;

                case "Chronus power is activated":
                    ChangeMessage2("Chronus power is activated");
                    break;

                case "Disconnected":
                    Platform.runLater(() -> {
                        Stage error = new Stage();
                        error.setTitle("Error!");
                        Label errormessage = new Label("Disconnected from server");
                        HBox errorbox = new HBox();
                        errorbox.setAlignment(Pos.CENTER);
                        errorbox.getChildren().add(errormessage);
                        Scene errorview = new Scene(errorbox, 300, 100);
                        error.setScene(errorview);
                        error.setResizable(false);
                        error.show();
                        if(gameboard!=null) gameboard.setDisable(true);
                    });

                    ChangeMessage2("Disconnected from server");
                    System.out.println("Disconnected from server");
                    connection=false;
                    server.close();
                    break;

                case "Exit":
                    ChangeMessage2((String) input.readObject());
                    int row1 = (int) input.readObject();
                    int column1 = (int) input.readObject();
                    int row2 = (int) input.readObject();
                    int column2 = (int) input.readObject();

                    Platform.runLater(() -> {
                                board[row1][column1].removeWorker();
                                board[row2][column2].removeWorker();
                            }
                    );
                    break;

                default:
                    ChangeMessage(responsesend);
                    if(responsesend.equals("You Win! Enemy can't move")){
                        connection=false;
                        server.close();
                    }
                    break;
            }
            }
            catch (EOFException ex) {
                try {
                    connection = false;
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    Stage error = new Stage();
                    error.setTitle("Error!");
                    Label errormessage = new Label("Connection drop by server");
                    HBox errorbox = new HBox();
                    errorbox.setAlignment(Pos.CENTER);
                    errorbox.getChildren().add(errormessage);
                    Scene errorview = new Scene(errorbox, 300, 100);
                    error.setScene(errorview);
                    error.setResizable(false);
                    error.show();
                    if(gameboard!=null) gameboard.setDisable(true);
                });
            }

            }

        }


    /**
     * Awake the NotifyGUI when the GUI stop to works
     */
    public synchronized void work() {
        notifyAll();
    }
}
