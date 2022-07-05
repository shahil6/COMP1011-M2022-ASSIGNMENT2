package ca.georgiancollege.comp1011m2022assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphViewSceneController implements Initializable {

    @FXML
    private RadioButton Continent;

    @FXML
    private RadioButton Country;

    @FXML
    private Button GraphView;

    @FXML
    private RadioButton Region;

    @FXML
    private BarChart<String, Long> barChart;

    @FXML
    void countryButton() {
        barChart.getData().clear();
        barChart.getData().addAll(DBManager.Instance().getCountry());
        barChart.setLegendVisible(false);
    }

    @FXML
    void regionButton() {
        barChart.getData().clear();
        barChart.getData().addAll(DBManager.Instance().getRegion());
        barChart.setLegendVisible(false);
    }

    @FXML
    void continentButton() {
        barChart.getData().clear();
        barChart.getData().addAll(DBManager.Instance().getContinent());
        barChart.setLegendVisible(false);
    }

    @FXML
    void OnTableViewClicked(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("table-view-scene.fxml"));
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

        barChart.getData().addAll(DBManager.Instance().getTable());
        barChart.setLegendVisible(false);
    }


    public RadioButton getContinent() { return Continent; }

    public RadioButton getCountry() { return Country; }

    public Button getGraphView() { return GraphView; }

    public RadioButton getRegion() { return Region; }
}
