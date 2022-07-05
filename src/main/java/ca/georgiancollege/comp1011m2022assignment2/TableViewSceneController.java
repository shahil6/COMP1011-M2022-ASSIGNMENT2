package ca.georgiancollege.comp1011m2022assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableViewSceneController implements Initializable {

        @FXML
        private TableColumn<WPopulation, String> Code;

        @FXML
        private TableColumn<WPopulation, String> Continent;

        @FXML
        private TableColumn<WPopulation, String> Name;

        @FXML
        private TableColumn<WPopulation, Long> Population;

        @FXML
        private TableColumn<WPopulation, String> Region;
        @FXML
        private TableView<WPopulation> TableView;

    @FXML
    void OnGraphViewClicked(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("graph-view-scene.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
        Continent.setCellValueFactory(new PropertyValueFactory<>("Continent"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Population.setCellValueFactory(new PropertyValueFactory<>("Population"));
        Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        TableView.getItems().addAll(DBManager.Instance().readWorldTable());

    }


}
