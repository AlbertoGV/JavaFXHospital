package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author jwright
 */
public class TableViewController implements Initializable {
    private String csvFile = null;
    //configure the table
    @FXML private TableView<Pacient> tableView;
    private ObservableList<Pacient> data;
    private List<Pacient> personList = new ArrayList<>();

    //These instance variables are used to create new Person objects
    @FXML private TextField dniTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker ageTextField;
    @FXML private TextField genTextField;
    @FXML private TextField telTextField;
    @FXML private TextField pesTextField;
    @FXML private TextField alsadaTextField;
    @FXML
    PieChart idPieChart;
    @FXML
    Button btnLoadFile;
    @FXML
    TextField txtDNI, txtNom, txtCognoms, txtAlçada;

    @FXML private Button detailedPersonViewButton;



    public void changeFirstNameCellEvent(CellEditEvent edittedCell)
    {
        Pacient personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setNom(edittedCell.getNewValue().toString());
    }



    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }

    public void btnChart(ActionEvent event) {
        setChart();
    }


    public void loadChart(Event event) {
        setChart();
    }

    private void setChart() {
        idPieChart.getData().clear();
        long dones = personList.stream()
                .filter(pacient -> pacient.getGenere()== Pacient.Genere.DONA)
                .count();
        long homes = personList.stream()
                .filter(pacient -> pacient.getGenere()== Pacient.Genere.HOME)
                .count();
        idPieChart.setTitle("Gènere");
        idPieChart.getData().add(new PieChart.Data(Pacient.Genere.DONA.toString(),dones));
        idPieChart.getData().add(new PieChart.Data(Pacient.Genere.HOME.toString(),homes));

    }


        public void changeLastNameCellEvent(CellEditEvent edittedCell)
    {
        Pacient personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setCognoms(edittedCell.getNewValue().toString());
    }




    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PacientView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        PacientViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        data = FXCollections.observableArrayList();
        if (data == null) {
            setTableView();
        }
          //load dummy data

        tableView.setEditable(true);

        //This will allow the table to select multiple rows at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Disable the detailed person view button until a row is selected
        this.detailedPersonViewButton.setDisable(true);
    }
    private void setTableView() {
        TableColumn DNI = new TableColumn("DNI");
        TableColumn Nom = new TableColumn("Nom");
        TableColumn Cognoms = new TableColumn("Cognoms");
        TableColumn DataNaix = new TableColumn("Data de Naixament");
        TableColumn Genre = new TableColumn("Gènere");
        TableColumn Telefon = new TableColumn("Telèfon");
        TableColumn pes = new TableColumn("Pes");
        TableColumn Alçada = new TableColumn("Alçada");

        // COMPTE!!!! les propietats han de tenir getters i setters
        DNI.setCellValueFactory(new PropertyValueFactory<Pacient, String>("DNI"));
        Nom.setCellValueFactory(new PropertyValueFactory<Pacient, String>("Nom"));
        Cognoms.setCellValueFactory(new PropertyValueFactory<Pacient, String>("Cognoms"));
        DataNaix.setCellValueFactory(new PropertyValueFactory<Pacient, String>("DataNaixament"));
        Genre.setCellValueFactory(new PropertyValueFactory<Pacient, String>("genere"));
        Telefon.setCellValueFactory(new PropertyValueFactory<Pacient, String>("Telefon"));
        pes.setCellValueFactory(new PropertyValueFactory<Pacient, Float>("Pes"));
        Alçada.setCellValueFactory(new PropertyValueFactory<Pacient, Integer>("Alçada"));

        tableView.getColumns().addAll(DNI, Nom, Cognoms, DataNaix, Genre, Telefon, pes, Alçada);
        /*data.add(new Pacient("111", "n", "co", LocalDate.of(2000, 12, 12), Persona.Genere.HOME, "55555", 5.4f, 100));*/
        loadData();
        data.addAll(personList);
        tableView.setItems(data);

    }
    public void btnCerca(ActionEvent event) {
        List<Pacient> pacients = personList.stream()
                .filter(pacient -> pacient.getDNI().equals(txtDNI.getText()))
                .collect(Collectors.toList());
        if(txtDNI.getText().equals("")) {
            updateTable(personList);
        }else updateTable(pacients);
    }
    private void loadData() {
        Hospital hospital = new Hospital();
        personList.addAll(hospital.loadPacients(csvFile));
    }






    public void deleteButtonPushed()
    {
        ObservableList<Pacient> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Person objects from the table
        for (Pacient pacient: selectedRows)
        {
            allPeople.remove(pacient);
        }
    }
    public void changeText(KeyEvent keyEvent) {
        data.clear();
        List<Pacient> pacients = personList.stream()
                .filter(pacient -> pacient.getNom().contains(txtNom.getText()))
                .filter((pacient -> pacient.getCognoms().contains(txtCognoms.getText())))
                .collect(Collectors.toList());
        data.addAll(pacients);
        tableView.setItems(data);
    }


    private void updateTable(List<Pacient> pacients) {
        data.clear();
        data.addAll(pacients);
        tableView.setItems(data);
    }





    public void newPersonButtonPushed()
    {
        Pacient newPacient = new Pacient( dniTextField.getText(),firstNameTextField.getText(), lastNameTextField.getText(),
                 ageTextField.getValue(), Pacient.Genere.valueOf(genTextField.getText()),telTextField.getText(),Integer.parseInt(pesTextField.getText()),Integer.parseInt(alsadaTextField.getText()));

        //Get all the items from the table as a list, then add the new person to
        //the list
        tableView.getItems().add(newPacient);

    }
    public void clickLoadFile(ActionEvent event) {
        if (csvFile == null) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Select csv file");
            File file = fc.showOpenDialog(null);
            csvFile = file.getAbsolutePath();
            setTableView();
            btnLoadFile.setText("Loaded");
        } else {
            btnLoadFile.setText("File is loaded");
        }
    }




    public ObservableList<Pacient>  getPeople()
    {
        ObservableList<Pacient> people = FXCollections.observableArrayList();
        people.add(new Pacient( "53332819L","Alberto","Garcia", LocalDate.of(1915, Month.DECEMBER, 12), Pacient.Genere.HOME,"658073800", 84.0f,175));


        return people;
    }
}