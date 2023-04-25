package View;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.OfferEntities;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import services.OfferServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;

public class OfferController implements Initializable {

    @FXML
    private TableView<OfferEntities> tv1;
    
    @FXML
    private TableColumn<OfferEntities, Integer> idd;
    
    @FXML
    private TableColumn<OfferEntities, String> namee;
    @FXML
    private TableColumn<OfferEntities, String> descriptionn;
    
    @FXML
    private TableColumn<OfferEntities, Integer> pricee;
    
    @FXML
    private TableColumn<OfferEntities, Integer> durationn;
    
    @FXML
    private TableColumn<OfferEntities, String> payment_linkk;
    
    @FXML
    private TableColumn<OfferEntities, String> offer_keyy;
    
    // new fields for offer creation
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField duration;
    @FXML
    private TextField payment_link;
    @FXML
    private TextField offer_key;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;
    @FXML
    private Label lbtype;
    @FXML
    private Button btnadd2;
    @FXML
    private TextField searchi;

    @FXML
    void deleteOffer(ActionEvent event) throws SQLException, MessagingException {
    // Get the selected offer
    OfferEntities selectedOffer = tv1.getSelectionModel().getSelectedItem();

    if (selectedOffer != null) {
        // Call the deleteOffer method of OfferServices and get the status
        int status = offerManager.deleteOffer(selectedOffer.getId());

        if (status > 0) {
            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Information");
            alert.setContentText("Offer Deleted Successfully");
            msg.sendSMS("+21650381852");
            alert.showAndWait();

            // Refresh the table
            table();
        } else {
            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Information");
            alert.setContentText("Offer Not Deleted");
            alert.showAndWait();
        }
    } else {
        // Show warning alert if no offer is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Information");
        alert.setContentText("Please select an offer to delete");
        alert.showAndWait();
    }
}

    @FXML
    void updateOffer(ActionEvent event) throws SQLException {

            // Get the selected offer
    OfferEntities selectedOffer = tv1.getSelectionModel().getSelectedItem();

    if (selectedOffer != null) {
        // Set the updated fields
        selectedOffer.setName(name.getText());
        selectedOffer.setDescription(description.getText());
        selectedOffer.setPrice(Integer.parseInt(price.getText()));
        selectedOffer.setDuration(Integer.parseInt(duration.getText()));
        selectedOffer.setPaymentLink(payment_link.getText());
        selectedOffer.setOffer_key(offer_key.getText());



    } else {
        // Show warning alert if no offer is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Information");
        alert.setContentText("Please select an offer to update");
        alert.showAndWait();
    }
        
    }

    // offer manager instance
    private OfferServices offerManager = new OfferServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        table();
        }catch (SQLException ex){
            ex.getMessage();
        }
    }    



@FXML
private void addNewOffer(ActionEvent event) {
    Alert alert;
    try {
        if (name.getText().isEmpty() || description.getText().isEmpty() || price.getText().isEmpty() ||
                duration.getText().isEmpty() || payment_link.getText().isEmpty() || offer_key.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } else if (!price.getText().matches("\\d+") || !duration.getText().matches("\\d+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Price and duration should be numeric");
            alert.showAndWait();
        } else if (!Character.isUpperCase(name.getText().charAt(0))) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Name should start with an uppercase letter");
            alert.showAndWait();
        }else {
            OfferEntities Oe = new OfferEntities();
            Oe.setName(name.getText());
            Oe.setDescription(description.getText());
            Oe.setPrice(Integer.parseInt(price.getText()));
            Oe.setDuration(Integer.parseInt(duration.getText()));
            Oe.setPaymentLink(payment_link.getText());
            Oe.setOffer_key(offer_key.getText());
            OfferServices od = new OfferServices();      
            od.createOffer(Oe);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Invalid price or duration value");
        alert.showAndWait();
    }
}

  public void table() throws SQLException {
          ObservableList<OfferEntities> offers = new OfferServices().showOffers();
        int myIndex = tv1.getSelectionModel().getSelectedIndex();
          
      tv1.setItems(offers);    
      idd.setCellValueFactory(new PropertyValueFactory<>("id"));  
      namee.setCellValueFactory(new PropertyValueFactory<>("name"));
      descriptionn.setCellValueFactory(new PropertyValueFactory<>("description"));  
      pricee.setCellValueFactory(new PropertyValueFactory<>("price"));  
      durationn.setCellValueFactory(new PropertyValueFactory<>("duration"));  
      payment_linkk.setCellValueFactory(new PropertyValueFactory<>("payment_link"));  
      offer_keyy.setCellValueFactory(new PropertyValueFactory<>("offer_key"));  
      
      System.out.println(offers);
      
tv1.setRowFactory(tv -> {
    TableRow<OfferEntities> myRow = new TableRow<>();
    myRow.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
            int index = myRow.getIndex();
            OfferEntities selectedOffer = tv1.getItems().get(index);
            name.setText(selectedOffer.getName());
            description.setText(selectedOffer.getDescription());
            price.setText(Integer.toString(selectedOffer.getPrice()));
            duration.setText(Integer.toString(selectedOffer.getDuration()));
            payment_link.setText(selectedOffer.getPaymentLink());
            offer_key.setText(selectedOffer.getOffer_key());
        }
    });
    
    
    return myRow;
});
// Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<OfferEntities> filteredData = new FilteredList<>(offers, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
		searchi.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(offer -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (offer.getName().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(offer.getDuration()).contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(offer.getPrice()).contains(lowerCaseFilter))
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
                
                // 3. Wrap the FilteredList in a SortedList. 
		SortedList<OfferEntities> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tv1.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tv1.setItems(sortedData);
               
		

        
       

    }

    @FXML
    private void searchyabro(ActionEvent event) {
    }
       
    
}