package it.polimi.ingsw.client.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static it.polimi.ingsw.client.view.stages.BoardLayout.*;
import static it.polimi.ingsw.client.view.stages.StartMenu.startMenu;

public class GUI extends Application{

    public static String worker;
    private static String response;
    protected static Socket server;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    public static String message;
    public static int userindex;
    public static int numofplayers;
    public static int currentplayer = 1;
    public static int currentworker;
    public static ArrayList<Text> name = new ArrayList<>();
    public static ArrayList<Text> divinity = new ArrayList<>();

    private static NotifyGUI notifytab;

    public static void main(String[] args) {
        Application.launch(GUI.class);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

            startMenu(primaryStage);

            primaryStage.setOnCloseRequest(event -> {
                System.out.println("Stage is closing");
                try {
                    server.close();
                    System.exit(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

    public static boolean sendMessage(String message, int number1, int number2) throws IOException {

        try {

            System.out.println("Sending message...");

            //print output from server
            response = (String) input.readObject();
            System.out.println(response);
            switch (response) {

                case "How many players?":
                    output.writeObject(number1);
                    break;

                case "Insert the name of the player":
                    output.writeObject(message);
                    response = (String) input.readObject();
                    System.out.println(response);
                    return !response.equals("Not available!");

                case "Not available!":

                    return false;

                case "Set worker position":
                    output.writeObject(number1);
                    output.writeObject(number2);
                    response = (String) input.readObject();
                    System.out.println(response);
                    return !response.equals("Cell is unavailable, retry");

                case "Worker chosen":
                    if(userindex==board[number1][number2].getWorkeruserindex()){
                        output.writeObject(board[number1][number2].getWorkerindex());
                        return true;
                    }
                    else{
                        output.writeObject(-1);
                        return false;
                    }

                case "Cell chosen":
                    output.writeObject(number1);
                    output.writeObject(number2);

                    String notify  = (String) input.readObject();

                    return !notify.equals("Cell is unavailable, retry");

                case "Power Chosen":
                    output.writeObject(number1);
                    receiveMessage();
                    break;

                case "Power Chosen Extra":
                    output.writeObject(number1);
                    receiveMessage();

                    if(number1 == 1)
                        receiveMessage();

                    break;

                case "Choose divinities":
                    output.writeObject(message);
                    break;

                case "Disconnected":
                    ChangeMessage2("Disconnected from server");
                    System.out.println("Disconnected from server");
                    server.close();
                    break;

                default:
                    break;


            }

        } catch (ClassNotFoundException | SocketException e) {
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
        }

        return true;
    }
    public static String receiveMessage() throws IOException, ClassNotFoundException{
        System.out.println("Receiving message...");

        //print output from server
        try{
            response = (String) input.readObject();
        }catch (SocketException | EOFException e){
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
        }

        System.out.println(response);
        switch (response) {

            case "Player index":
                userindex = (int) input.readObject();
                break;

            case "Number of players":
                numofplayers = (int) input.readObject();
                break;

            case "Update Players":

            case "Update Divinity":
                return (String) input.readObject();

            case "Update current client":
                currentplayer = (int) input.readObject();
                receiveMessage();
                break;

            case "Wait the others players for join...":
                ChangeMessage2("");
                notifytab.work();
                break;
            case "Set worker position":
                currentworker = (int) input.readObject();

                int row = (int) input.readObject();
                int column = (int) input.readObject();

                board[row][column].setWorker(currentplayer, currentworker);

                break;

            case "Move Worker":

                currentworker = (int) input.readObject();

                int oldrow=(int) input.readObject();
                int oldcolumn=(int) input.readObject();

                int oldworkeruserindex = board[oldrow][oldcolumn].getWorkeruserindex();
                int oldworkerindex = board[oldrow][oldcolumn].getWorkerindex();

                board[oldrow][oldcolumn].removeWorker();

                int newrow = (int) input.readObject();
                int newcolumn = (int) input.readObject();

                // divinities graphic consequences
                if(board[newrow][newcolumn].getWorkeruserindex()!=-1) {


                    int workerpusheduserindex = board[newrow][newcolumn].getWorkeruserindex();
                    int workerpushedindex = board[newrow][newcolumn].getWorkerindex();

                    //Divinity name
                    String divinity = (String) input.readObject();

                    if(divinity.equals("Apollo")) {

                        board[newrow][newcolumn].removeWorker();
                        board[oldrow][oldcolumn].setWorker(workerpusheduserindex, workerpushedindex);

                    }

                    if(divinity.equals("Minotaur")) {



                        String message = (String) input.readObject();
                        System.out.println(message);

                        if(message.equals("Push done")) {

                            int pushedrow = (int) input.readObject();
                            int pushedcolumn = (int) input.readObject();

                            board[newrow][newcolumn].removeWorker();
                            board[pushedrow][pushedcolumn].setWorker(workerpusheduserindex, workerpushedindex);

                        }
                        else {
                            board[oldrow][oldcolumn].setWorker(oldworkeruserindex, oldworkerindex);
                            receiveMessage();
                            ChangeMessage2("Cell is unavailable, retry");
                            break;
                        }


                    }

                }

                board[newrow][newcolumn].setWorker(currentplayer, currentworker);

                break;

            case  "Build a block":

                int buildrow = (int) input.readObject();
                int buildcolumn = (int) input.readObject();
                int buildlevel= (int) input.readObject();

                board[buildrow][buildcolumn].setLevel(buildlevel);

                break;

            case "Do you want to use the divinity power?":
                ChangeMessage(response);
                Tilegroup.setDisable(true);
                divinitypower.setVisible(true);
                yes.setVisible(true);
                no.setVisible(true);
                break;

            case "Power Off":
                divinitypower.setVisible(false);
                divinitypower.setGraphic(divinitypowerimage);
                receiveMessage();
                break;

            case "Notify setDome": //Atlas
                int domerow = (int) input.readObject();
                int domecolumn = (int) input.readObject();
                board[domerow][domecolumn].setLevel(5);
                receiveMessage();
                break;

            case "Can't level up":
                ChangeMessage(response);
                int preresetrow = (int) input.readObject();
                int preresetcolumn = (int) input.readObject();
                int resetrow = (int) input.readObject();
                int resetcolumn = (int) input.readObject();

                int currentworker =  board[preresetrow][preresetcolumn].getWorkerindex();
                int currentuser =  board[preresetrow][preresetcolumn].getWorkeruserindex();

                board[preresetrow][preresetcolumn].removeWorker();
                board[resetrow][resetcolumn].setWorker(currentuser, currentworker);
                receiveMessage();
                break;

            case "EndGame":
                ChangeMessage((String) input.readObject());
                server.close();
                break;

            case "Exit":
                ChangeMessage((String) input.readObject());
                break;

            case "Disconnected":
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

                ChangeMessage2("Disconnected from server");
                System.out.println("Disconnected from server");
                server.close();
                break;

            case "Lost":
                Stage error2 = new Stage();
                error2.setTitle("You lost!");
                Label errormessage2 = new Label("You lost!");
                HBox errorbox2 = new HBox();
                errorbox2.setAlignment(Pos.CENTER);
                errorbox2.getChildren().add(errormessage2);
                Scene errorview2 = new Scene(errorbox2, 300, 100);
                error2.setScene(errorview2);
                error2.setResizable(false);
                error2.show();
                if(gameboard!=null) gameboard.setDisable(true);

                ChangeMessage(" ");
                ChangeMessage2("You Lost");
                int row1 = (int) input.readObject();
                int column1 = (int) input.readObject();
                int row2 = (int) input.readObject();
                int column2 = (int) input.readObject();

                board[row1][column1].removeWorker();
                board[row2][column2].removeWorker();

                break;

            case "Chronus power is activated":
                ChangeMessage2("Chronus power is activated");
                server.close();
                break;

            default:
                ChangeMessage(response);
                if(!message2.getText().equals("Athena Power is activated")) ChangeMessage2("");
                break;
        }
        return null;
    }


    public void setServerConnection(Socket connection) throws IOException {

        server = connection;

        output = new ObjectOutputStream(server.getOutputStream());
        input = new ObjectInputStream(server.getInputStream());

        notifytab = new NotifyGUI(input);
        Thread notifytabthread = new Thread(notifytab);
        notifytabthread.start();

    }


}