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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PacientViewController implements Initializable {

    private Pacient selectedPerson;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label ageLabel;
    @FXML private ImageView photo;


    public void initData(Pacient pacient)
    {

        selectedPerson = pacient;
        firstNameLabel.setText(selectedPerson.getNom());
        lastNameLabel.setText(selectedPerson.getNom());
        birthdayLabel.setText(selectedPerson.getDataNaixament().toString());
        ageLabel.setText(Integer.toString(selectedPerson.getEdat()));
        photo.setImage(selectedPerson.getImage());
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