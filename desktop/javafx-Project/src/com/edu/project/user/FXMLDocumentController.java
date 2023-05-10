/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import com.edu.project.entities.CategorieP;
import com.edu.project.entities.Produit;
import com.edu.project.service.ServiceCategorie;
import com.edu.project.service.ServicesProduit;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.chart.NumberAxis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Sarra Kachouandi
 */
public class FXMLDocumentController implements Initializable {
  int myIndex;
  int id;
    @FXML
    private Button btAjout;

    @FXML
    private TextField ftnom;
 @FXML
    private TableView<Produit> table;
    @FXML
    private Button btnimprim;
@FXML
    private Button filterButton;
 @FXML
    private Hyperlink AfficherStat;
  @FXML
    private Label temperatureLabel;

    @FXML
    private Label descriptionLabel;
  @FXML
    private Label weatherLabel;
  @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private TextField ftphoto;
 @FXML
    private TextField categorie;
    @FXML
    private Label label;
    @FXML
    private Button btnAJOUTER;
@FXML
    private TextField ftprix;
@FXML
    private TableColumn<Produit, Integer> IDcolumn;

    @FXML
    private TableColumn<Produit, String> nomcolumn;

    @FXML
    private TableColumn<Produit, String> Photocolumn;

    @FXML
    private TableColumn<Produit, Integer>  categoriecolumn;

    @FXML
    private TableColumn<Produit, Float> Prixcolumn;
  @FXML
    private Button btnSuppr;
  
  @FXML
    private ComboBox<CategorieP> CatCombo;
 @FXML
    private TextField searchField;
@FXML
    private Button btnCat;
  @FXML
    private Hyperlink UserInterface;

    @FXML
    private Button btnModifier;
     @FXML
    private ImageView ImagePreview;

    @FXML
    private Button onBtnImageClicked;
private String photo;
 @FXML 
    private Label labelImage;
 @FXML
    private Button sortNom;
    //MODIFIER PRODUit
 @FXML
    void ModifierProduit(ActionEvent event) {
   String nom = ftnom.getText();
    String photo = ftphoto.getText();
    float prixFP = Float.parseFloat(ftprix.getText());
    CategorieP selectedCategorie = (CategorieP) CatCombo.getValue();
int categorieId = selectedCategorie.getId();
Produit p = new Produit(nom, photo, prixFP, categorieId);
    ServicesProduit servicesProduit = new ServicesProduit();
    

    int id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
    servicesProduit.modifier(p, id);
    initialize(null, null);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//alert.setTitle("Student Registationn");
 
alert.setHeaderText("Produit modifié avec Succès");
//alert.setContentText("Produit modifiée avec Succé");
 
alert.showAndWait();
    }
    
    
  /// AJOUT PRODUIT 
    @FXML
    void AddProduit(ActionEvent event) {
String photot = photo;
System.out.println("AddProduit method called.");


// Get input values
String nom = ftnom.getText().trim();
String photo = ftphoto.getText().trim();
String prixFP = ftprix.getText().trim();
CategorieP selectedCategorie = (CategorieP) CatCombo.getValue();

// Validate input values
if (nom.isEmpty() || prixFP.isEmpty()) {
    // Show an error message
    showAlert(Alert.AlertType.ERROR, "Remplir tout les champs!!");
    return;
}

if (selectedCategorie == null) {
    // Show an error message
    showAlert(Alert.AlertType.ERROR, "Veuillez sélectionner une catégorie!");
    return;
}

int prix;
try {
    prix = Integer.parseInt(prixFP);
} catch (NumberFormatException e) {
    // Show an error message
    showAlert(Alert.AlertType.ERROR, "Le prix doit être un entier valide!");
    return;
}

// Create new product object
Produit p = new Produit(nom, selectedCategorie.getId(), prix);

// Call the service method to add the product
ServicesProduit newProd = new ServicesProduit();
newProd.ajouter(p, photot);

// Show success message
showAlert(Alert.AlertType.INFORMATION, "Produit ajouté avec succès!");

// Clear the input fields
ftnom.setText("");
ftphoto.setText("");
ftprix.setText("");
CatCombo.getSelectionModel().clearSelection();

// Refresh the product list
initialize(null, null);
}

private void showAlert(Alert.AlertType type, String message) {
Alert alert = new Alert(type);
alert.setTitle("Erreur");
alert.setHeaderText(null);
alert.setContentText(message);
alert.showAndWait();
}
    
// private static final String API_KEY = "1daf025b7a59d2afabd317bfa4ffccc7";
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    @Override
    public void initialize(URL location, ResourceBundle rb) {
   getWeather("Tunisie");

//
//String city = "Tunisia";
//    try {
//        URL url = new URL(String.format(API_URL, city, API_KEY));
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        JSONObject json = new JSONObject(response.toString());
//        double temperature = json.getJSONObject("main").getDouble("temp");
//        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
//        temperatureLabel.setText(String.format("%.2f°C", temperature - 273.15));
//        descriptionLabel.setText(description);
//    } catch (IOException | JSONException e) {
//        System.out.println("An error occurred while retrieving weather data: " + e.getMessage());
//    }


















        try {
            
            try {
                TableColumn<Produit, Integer> idColumn = new TableColumn<>("ID");
                IDcolumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
                TableColumn<Produit, String> nomColumn = new TableColumn<>("Nom");
                nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                
                TableColumn<Produit, String> photoColumn = new TableColumn<>("Photo");
                Photocolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
                
                TableColumn<Produit, Float> prixColumn = new TableColumn<>("Prix");
                Prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
//                
                TableColumn<Produit, Integer> categorieId = new TableColumn<>("categorie_p_id");
                categoriecolumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategoriePId()));
                  
            ServiceCategorie servicesCategorie = new ServiceCategorie();
                  List<CategorieP> categories = servicesCategorie.afficherListeCategorie();
                table.setRowFactory( tv -> {
                    TableRow<Produit> myRow = new TableRow<>();
                    myRow.setOnMouseClicked (event ->
                    {
                        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                        {
                            myIndex =  table.getSelectionModel().getSelectedIndex();
                            id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                            ftnom.setText(table.getItems().get(myIndex).getNom());
                            ftphoto.setText(table.getItems().get(myIndex).getPhoto());
                            ftprix.setText(Float.toString(table.getItems().get(myIndex).getPrix()));
                            CatCombo.setValue(categories.stream().filter(c -> c.getId() == table.getItems().get(myIndex).getCategoriePId()).findFirst().orElse(null));
                            
                            
                        }
                        
                    });
                    return myRow;
                });
            
                
                // Retrieve the data from the database and add it to the table
                ServicesProduit servicesProduit = new ServicesProduit();
                List<Produit> produits = servicesProduit.afficherListe();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("barchart.fxml"));
Parent root = loader.load();
BarchartController controller = loader.getController();

// use the controller instance to set the data and configure the chart
controller.setData(produits);
              

                ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
                table.setItems(observableProduits);
                
                
                 searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        String keyword = newValue.toLowerCase();
        List<Produit> filteredList = produits.stream()
            .filter(produit -> produit.getNom().toLowerCase().contains(keyword)
                    || Float.toString(produit.getPrix()).contains(keyword)
                    || Integer.toString(produit.getCategoriePId()).contains(keyword))
            .collect(Collectors.toList());
        table.setItems(FXCollections.observableArrayList(filteredList));
    });
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ServiceCategorie servicesCategorie = new ServiceCategorie();
            List<CategorieP> categories = servicesCategorie.afficherListeCategorie();
            ObservableList<CategorieP> categoriesList = FXCollections.observableArrayList();
// add elements to categoriesList
List<CategorieP> categoriess = new ArrayList<>(categoriesList);
CatCombo.getItems().addAll(categories);

CatCombo.setConverter(new StringConverter<CategorieP>() {
    @Override
    public String toString(CategorieP categorie) {
        return categorie.getType();
    }

    @Override
    public CategorieP fromString(String string) {
        return null;
    }
});
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        

    }
    public static ObservableList<Produit> observableProduits;

    public TableView<Produit> getTable() {
    return table;
    }
    
   
    @FXML
    void SupprimerProduit(ActionEvent event) {

        ServicesProduit newProd = new ServicesProduit();
Alert alert = new Alert(Alert.AlertType.INFORMATION);


alert.setHeaderText("Produit Supprimer");

 
alert.showAndWait();
     
        newProd.supprimer(id);
        initialize(null, null);
    }
    
      @FXML
    void onBtnImageClicked(ActionEvent event)  {
    
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    File file = fileChooser.showOpenDialog(null); // Show file chooser dialog

    if (file != null) {
        try {
            // Load image from selected file
            FileInputStream fileInputStream = new FileInputStream(file);
            Image photot = new Image(fileInputStream);
            ImagePreview.setImage(photot);

            photo = file.toURI().toString(); // Update the photo variable with the file path
            labelImage.setText(file.getName()); // Update the label with the file name
        } catch (IOException e) {
        }
        
    }
    

}


@FXML
    void afficheCat(ActionEvent event) {
  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Categories.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


     
    @FXML
    void affusrint(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
}
       



    
    @FXML
void handleSortAscButtonAction(ActionEvent event) {
   togglePriceOrder();
}
      
    private boolean isPriceAscending = true;
private void togglePriceOrder() {
    isPriceAscending = !isPriceAscending;
    ObservableList<Produit> currentItems = table.getItems();
    currentItems.sort((p1, p2) -> isPriceAscending ? Float.compare(p1.getPrix(), p2.getPrix()) : Float.compare(p2.getPrix(), p1.getPrix()));
    currentItems.sort((p1, p2) -> isPriceAscending ? p1.getNom().compareTo(p2.getNom()) : p2.getNom().compareTo(p1.getNom()));

    
    table.setItems(currentItems);
}

private boolean isNom = true;
 private void toggleNomOrder() {
    isNom = !isNom;
    ObservableList<Produit> currentItems = table.getItems();

    System.out.println("Sorting by Nom in " + (isNom ? "ascending" : "descending") + " order:");

    for (Produit p : currentItems) {
        System.out.println(p.getNom());
    }

   currentItems.sort((p1, p2) -> isNom ? p1.getNom().compareToIgnoreCase(p2.getNom()) : p2.getNom().compareToIgnoreCase(p1.getNom()));

    for (Produit p : currentItems) {
      
    }

    table.setItems(currentItems);
}

    @FXML
    void SORTNOM(ActionEvent event) {
        toggleNomOrder();
    }
    

  

@FXML
    void AfficherStat(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("barchart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
}
    
    
    
      @FXML
    void ImprimerPdf(ActionEvent event) {
   Produit selectedProduit = table.getSelectionModel().getSelectedItem();
    if (selectedProduit != null) {
        generatePDF(selectedProduit);
    } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No row selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a row to print.");
        alert.showAndWait();
    }

    }
    
    private void generatePDF(Produit p) {
   try {
        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("table.pdf"));
        document.open();

        // Add the table to the document
        PdfPTable pdfTable = new PdfPTable(5);
        pdfTable.setWidthPercentage(100);
        pdfTable.setSpacingBefore(10f);
        pdfTable.setSpacingAfter(10f);

        // Add table headers
        PdfPCell idHeader = new PdfPCell(new Phrase("ID"));
        idHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(idHeader);

        PdfPCell nomHeader = new PdfPCell(new Phrase("Nom"));
        nomHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(nomHeader);

        PdfPCell photoHeader = new PdfPCell(new Phrase("Photo"));
        photoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(photoHeader);

        PdfPCell prixHeader = new PdfPCell(new Phrase("Prix"));
        prixHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(prixHeader);

        PdfPCell categorieHeader = new PdfPCell(new Phrase("Catégorie"));
        categorieHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(categorieHeader);

        // Add selected row data to the table
        pdfTable.addCell(String.valueOf(p.getId()));
        pdfTable.addCell(p.getNom());
        pdfTable.addCell(p.getPhoto());
        pdfTable.addCell(String.valueOf(p.getPrix()));
        pdfTable.addCell(String.valueOf(p.getCategoriePId()));

        document.add(pdfTable);
        document.close();

        // Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF generated");
        alert.setHeaderText(null);
        alert.setContentText("The PDF file has been generated successfully.");
        alert.showAndWait();

    } catch (Exception e) {
        // Show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while generating the PDF file: " + e.getMessage());
        alert.showAndWait();
    }
}
    

    
public class WeatherApi {
    private static final String API_KEY = "1daf025b7a59d2afabd317bfa4ffccc7";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public String getWeatherData(String location) throws IOException {
        URL url = new URL(String.format(API_URL, location, API_KEY));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

public void getWeather(String location) {
    try {
        WeatherApi api = new WeatherApi();
        String weatherData = api.getWeatherData(location);

        // Parse JSON response
        JSONObject json = new JSONObject(weatherData);
        double temperature = json.getJSONObject("main").getDouble("temp");
        String condition = json.getJSONArray("weather").getJSONObject(0).getString("main");

        // Update weather label
        String text = String.format("Bonjour, La température d'aujourd'hui \n en %s: %.1f\u00B0C, %s ", location, temperature, condition);
        
        weatherLabel.setText(text);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

   
