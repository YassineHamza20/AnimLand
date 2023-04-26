import servicespi.entities.Rating;
import servicespi.entities.Category;
import servicespi.entities.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import servicespi.controller.RatingController;
import servicespi.controller.ServiceController;

public class AddRating {

    ServiceController es = new ServiceController();
    RatingController er = new RatingController();

    @FXML private ComboBox<Service> eventList;
    @FXML private TextField rating;
    @FXML private TextField user_id;
    @FXML private Button rate;


    @FXML public void initialize(){
        ObservableList<Service> Services = FXCollections.observableArrayList(es.getAllServices());
        eventList.setItems(Services);

        user_id.setText("1");

        eventList.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {
            @Override
            public ListCell<Service> call(ListView<Service> param) {
                return new ListCell<Service>() {
                    @Override
                    protected void updateItem(Service item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.getNom());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    @FXML public void onRate(ActionEvent event) {
        Service s = eventList.getSelectionModel().getSelectedItem();
        int user = Integer.parseInt(user_id.getText());
        float r = Float.parseFloat(rating.getText());

        if (s==null || user_id.getText().trim().isEmpty() || rating.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();
            return;
        }

        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No event selected");
            alert.setContentText("Please select an event.");
            alert.showAndWait();
            return;
        }

        if (r < 0 | r > 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid rating");
            alert.setContentText("Please enter a rating between 0 and 5.");
            alert.showAndWait();
            return;
        }

        er.addRating(new Rating(user, r, s));

        // Close the window
        Stage stage = (Stage) rate.getScene().getWindow();
        stage.close();
    }

}
