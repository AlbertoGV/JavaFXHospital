package sample;
/*    git push -u origin master*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PacientViewController implements Initializable {

    private Pacient selectedPerson;
    @FXML private Label dniLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label ageLabel;
    @FXML private Label pesLabel;
    @FXML private Label alsadaLabel;
    @FXML private Label genLabel;



    public void initData(Pacient pacient)
    {

        selectedPerson = pacient;
        dniLabel.setText(selectedPerson.getDNI());
        firstNameLabel.setText(selectedPerson.getNom());
        lastNameLabel.setText(selectedPerson.getCognoms());
        ageLabel.setText(selectedPerson.getDataNaixament().toString());
        pesLabel.setText(String.valueOf(selectedPerson.getPes()));
        alsadaLabel.setText(String.valueOf(selectedPerson.getAlçada()));
        genLabel.setText(selectedPerson.getGenere().toString());

    }


    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}