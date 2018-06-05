package Clock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.image.ImageView;


public class Main extends Application {

    Label label;
    Stage window;
    BorderPane layoutB;
    public static TableView<sample.Alarm> table;
    Button deleteButton;        //przyscisk do usuwania wiersza z tabeli
    //tworze tutaj, aby sortowac po kolumnie hourColumn, w tabeli
    static TableColumn<sample.Alarm, Integer> hourColumn=new TableColumn<>("Godzina");

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        window.setTitle("Kiedys to bedzie zegar");

        label=new Label();

        //hour column
        hourColumn.setMinWidth(50);
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));

        //minute column
        TableColumn<sample.Alarm, Integer> minuteColumn=new TableColumn<>("Minuty");
        minuteColumn.setMinWidth(50);
        minuteColumn.setCellValueFactory(new PropertyValueFactory<>("minute"));

        //kontekst menu usun
        ContextMenu contextMenu=new ContextMenu();
        MenuItem deleteRow=new MenuItem("usun");
        deleteRow.setOnAction(e->deleteButtonClick());
        contextMenu.getItems().add(deleteRow);

        //dodanie kolumn i zawartosci do tageli
        table=new TableView<>();
        table.setItems(getAlarm());
        table.setMaxHeight(175);
        table.getColumns().addAll(hourColumn, minuteColumn);
        table.getSortOrder().add(hourColumn);       //sortowanie
        table.setContextMenu(contextMenu);      //menu usun

        //o autorze
        Label autorInfo=new Label("O autorze");
        autorInfo.setOnMouseClicked(e->displayInfo());
        Menu autotI=new Menu();
        autotI.setGraphic(autorInfo);

        //program menu
        Menu fileMenu=new Menu("Program");

        //program items
        MenuItem fileClose=new MenuItem("Zamknij");
        fileClose.setOnAction(e-> window.close());
        fileMenu.getItems().add(fileClose);

        //budzik menu
        Menu alarmMenu=new Menu("Budzik");

        //budzik items
        MenuItem addAlarm=new MenuItem("Dodaj alarm");
        addAlarm.setOnAction(e-> display());
        alarmMenu.getItems().add(addAlarm);
        alarmMenu.getItems().add(new SeparatorMenuItem());
        MenuItem clearAlarm=new MenuItem("Wyczysc alarmy");
        clearAlarm.setOnAction(e-> wyszyscAlarmy());
        alarmMenu.getItems().add(clearAlarm);

        //main menu bar
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(fileMenu, alarmMenu, autotI);

        //Vbox dla dolnego panelu, tablica i przycisk usun
        VBox bottonVbox=new VBox(10);
        bottonVbox.getChildren().addAll(table);



        //biore zegar z controllera funkcja startClock zwraca canvas
        Controller con=new Controller();
        layoutB=new BorderPane();
        layoutB.setCenter(con.startClock());
        layoutB.setTop(menuBar);
        layoutB.setRight(bottonVbox);
        Scene scene=new Scene(layoutB, 900, 800);
        window.setScene(scene);
        window.show();
    }


    //wszystkie funkcje

    //dodaje alam
    public ObservableList<sample.Alarm> getAlarm(){
        ObservableList<sample.Alarm> alarms= FXCollections.observableArrayList();
        alarms.add(new sample.Alarm(14,21));
        alarms.add(new sample.Alarm(11,38));
        alarms.add(new sample.Alarm(23,57));
        return  alarms;
    }

    //usuwa z tabeli alarm ktry jest zaznaczony
    public void deleteButtonClick(){
        ObservableList<sample.Alarm> alarmSelected, allAlarms;
        allAlarms=table.getItems();
        alarmSelected=table.getSelectionModel().getSelectedItems();
        alarmSelected.forEach(allAlarms::remove);
    }

    //usuwa wszystkie alarmy z tabel
    public void wyszyscAlarmy(){
        table.getItems().clear();
    }

    //nowe okno info o autorze
    public static void displayInfo(){
        Stage window=new Stage();
        Label label=new Label("Dane: ");
        Label label1=new Label("Jakub");
        Label label2=new Label("Lazarski");
        ImageView image=new ImageView("https://cdn0.iconfinder.com/data/icons/toys/256/teddy_bear_toy_6.png");
        window.setTitle("Info");
        window.setWidth(650);
        window.setHeight(550);
        window.initModality(Modality.APPLICATION_MODAL);
        VBox vBox=new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox=new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label,label1,label2, image);
        vBox.getChildren().addAll(hBox);
        Scene scene=new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    //nowe okno, dodawanie alarmow
    public static void display(){
        Stage window=new Stage();
        window.setTitle("Dodaj budzik");
        window.setWidth(350);
        window.setHeight(150);
        window.initModality(Modality.APPLICATION_MODAL);

        HBox hBox=new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        ComboBox<Integer> hourInput, minuteInput;
        hourInput=new ComboBox<>();
        minuteInput=new ComboBox<>();
        hourInput.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
        minuteInput.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,
                24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,
                54,55,56,57,58,59);
        hourInput.setPromptText("godziny");
        minuteInput.setPromptText("minuty");
        hBox.getChildren().addAll(hourInput, minuteInput);

        VBox layout=new VBox(5);
        Label label=new Label("Wybierz");
        Button addButton=new Button("Dodaj");
        addButton.setOnAction(e->addButtonClick(hourInput, minuteInput));
        layout.getChildren().addAll(label,hBox, addButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    //addButtonClick dodaje do tabeli godzine z nowego okna
    public static void addButtonClick(ComboBox hourInput, ComboBox minuteInput) {
        sample.Alarm alarm = new sample.Alarm();
        alarm.setHour((int) hourInput.getValue());
        alarm.setMinute((int) minuteInput.getValue());
        table.getItems().add(alarm);
        table.getSortOrder().add(hourColumn);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
