/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;
//import static changemakers.utils.SMS.ACCOUNT_SID;
//import static changemakers.utils.SMS.AUTH_TOKEN;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.twilio.Twilio;
//import com.twilio.type.PhoneNumber;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import com.edu.project.entities.Adoption;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.edu.project.service.AdoptionService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



import com.sun.rowset.internal.Row;
import java.io.File;
import javafx.stage.FileChooser;
import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetailsWindowController_1 implements Initializable {
    @FXML
private Button buttonSortByRace;
    @FXML
private Button verify;
     @FXML
    private Button switchButton1;
     @FXML
    private Button buttonSOrtBySexe;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tproprietaire;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tage;
    @FXML
    private TextField tsexe;
    @FXML
    private TextField trace;
    @FXML
    private TextField tcategorie;
    @FXML
    private TableView<Adoption> tableview;
    @FXML
    private TableColumn<Adoption, Integer> idco;
    @FXML
    private TableColumn<Adoption,String> nomco;
    @FXML
    private TableColumn<Adoption, String> propco;
    @FXML
    private TableColumn<Adoption, String> descrico;
    @FXML
    private TableColumn<Adoption, String> ageco;
    @FXML
    private TableColumn<Adoption, String> raceco;
    @FXML
    private TableColumn<Adoption, String> sexeco;
    @FXML
    private TableColumn<Adoption, Integer> catco;
    @FXML
private TextField sortTextField;
    
    ObservableList<Adoption> adop = FXCollections.observableArrayList();
    @FXML
    private Button supidbtn;

    @FXML
    private TextField supid;
    int idAdoption;
    int myIndex;
    @FXML
    public void Select() {
        Adoption r = tableview.getSelectionModel().getSelectedItem();
        idAdoption=r.getId();

        if (r != null) {
            tnom.setText(r.getNom());
            supid.setText(Integer.toString(idAdoption));
            trace.setText(r.getRace());
            tsexe.setText(r.getRace());
            tproprietaire.setText(r.getProprietaire());
            tdescription.setText(r.getDescription());
            tage.setText(r.getAge());
            

        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdoptionService rc = new AdoptionService();
        List<Adoption> listR = null;
        listR = rc.ChercherAdoption();
    
        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                adop.add(listR.get(i));
            }
        }
        idco.setCellValueFactory(new PropertyValueFactory<Adoption, Integer>("id"));
        nomco.setCellValueFactory(new PropertyValueFactory<Adoption, String>("nom"));
        ageco.setCellValueFactory(new PropertyValueFactory<Adoption, String>("age"));
        descrico.setCellValueFactory(new PropertyValueFactory<Adoption, String>("description"));
        propco.setCellValueFactory(new PropertyValueFactory<Adoption, String>("proprietaire"));
        raceco.setCellValueFactory(new PropertyValueFactory<Adoption, String>("race"));
        sexeco.setCellValueFactory(new PropertyValueFactory<Adoption, String>("sexe"));
        catco.setCellValueFactory(new PropertyValueFactory<Adoption, Integer>("categorie_id"));
        tableview.setItems(adop);
        
        tableview.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        Select();
    }
    });
        
        sortTextField.setOnKeyPressed(event -> {
    if (event.getCode() == KeyCode.ENTER) {
        sortTable(sortTextField.getText());
    }
});
        
        sortTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    handleSort(newValue);
});
        buttonSOrtBySexe.setOnAction(event -> {
    handleSortBySexe();
});
       buttonSortByRace.setOnAction(event -> sortTableByRace());



    } 
    
    
    public void refresh(TableView t) {
        ObservableList<Adoption> dataR = FXCollections.observableArrayList();
        AdoptionService ps = new AdoptionService();
        List<Adoption> listR = null;
        listR = ps.ChercherAdoption();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                dataR.add(listR.get(i));
            }
        }

        t.getItems().setAll(dataR);
        t.refresh();
    }
    
    
    /**
     * Initializes the controller class.
     */
    
    
    public void setTextnom(String message){
        this.tnom.setText(message);
    }
    public void setTextproprietaire(String message){
        this.tproprietaire.setText(message);
    }
    public void setTextdescription(String message){
        this.tdescription.setText(message);
    }
    public void setTextage(String message){
        this.tage.setText(message);
    }
    public void setTextsexe(String message){
        this.tsexe.setText(message);
    }
    public void setTextrace(String message){
        this.trace.setText(message);
    }
    public void setTextcategorie(String message){
        this.tcategorie.setText(message);
    }

    @FXML
    private void SupprimerParID(ActionEvent event) {
        /*int idsup = Integer.parseInt(supid.getText());*/
        AdoptionService rc = new AdoptionService();
        rc.supprimer(idAdoption);   
        // Get a reference to the current window
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("DetailsWindow_1.fxml");
    
    // Create a new FXMLLoader
    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
    
    try {
        // Load the FXML file and create a new scene graph
        Parent root = fxmlLoader.load();
        
        // Create a new scene with the root node
        Scene scene = new Scene(root);
        
        // Set the scene of the stage
        stage.setScene(scene);
        
        // Show the stage
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
    
    public void UpdateItems(ActionEvent event) throws SQLException {

        String nom = tnom.getText();
        String proprietaire = tproprietaire.getText();
        String description = tdescription.getText();
        String sexe = tsexe.getText();
        String race = trace.getText();
        String age = tage.getText();

        //    public Reclamation(String titre_reclamation, String type_reclamation, String etat_reclamation, String description_reclamation, Date date) {
        if (nom.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ nom");
            alert.showAndWait();
        } else if (proprietaire.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veulliez remplir le champ proprietaire");
            alert.showAndWait();
        } else if ((race.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ race");
            alert.showAndWait();
        } else if ((description.isEmpty()) || (description.length() <= 3)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "La description ne peut être vide et doit plus de trois lettres et des chiffres.");
            alert.showAndWait();
         } else if ((sexe.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ sexe");
            alert.showAndWait();
        }else if ((age.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ age");
            alert.showAndWait();
        } else {
            Adoption a = new Adoption(nom, proprietaire, age, description, sexe, race);
            AdoptionService ps = new AdoptionService();
             int id = Integer.parseInt(String.valueOf(tableview.getItems().get(myIndex).getId()));
             System.out.print(id);
            ps.modifier(a, id);
            System.out.print(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "adoption modifiée.");
            alert.showAndWait();
            refresh(tableview);
            alert.showAndWait();
     initialize(null, null);
     
      
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("DetailsWindow_1.fxml");
    
    // Create a new FXMLLoader
    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
    try {
        // Load the FXML file and create a new scene graph
        Parent root = fxmlLoader.load();
        // Create a new scene with the root node
        Scene scene = new Scene(root);
        
        // Set the scene of the stage
        stage.setScene(scene);
        
        // Show the stage
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        }

    }
    
    
    public void sortTable(String attribute) {
    adop.sort(new Comparator<Adoption>() {
        @Override
        public int compare(Adoption o1, Adoption o2) {
            switch (attribute) {
                case "id":
                    return o1.getId() - o2.getId();
                case "nom":
                    return o1.getNom().compareTo(o2.getNom());
                case "age":
                    return o1.getAge().compareTo(o2.getAge());
                case "description":
                    return o1.getDescription().compareTo(o2.getDescription());
                case "proprietaire":
                    return o1.getProprietaire().compareTo(o2.getProprietaire());
                case "race":
                    return o1.getRace().compareTo(o2.getRace());
                case "sexe":
                    return o1.getSexe().compareTo(o2.getSexe());
                default:
                    return 0;
            }
        }
    });
    tableview.setItems(adop);
}
    
    private void handleSort(String input) {
    ObservableList<Adoption> filteredList = FXCollections.observableArrayList();
    for (Adoption adoption : adop) {
        if (adoption.getNom().toLowerCase().contains(input.toLowerCase())
            || adoption.getAge().toLowerCase().contains(input.toLowerCase())
            || adoption.getDescription().toLowerCase().contains(input.toLowerCase())
            || adoption.getProprietaire().toLowerCase().contains(input.toLowerCase())
            || adoption.getRace().toLowerCase().contains(input.toLowerCase())
            || adoption.getSexe().toLowerCase().contains(input.toLowerCase())) {
            filteredList.add(adoption);
        }else {
    tableview.setItems(adop);
}
    }
    tableview.setItems(filteredList);
}
    

@FXML
public void handleSwitch1() throws IOException {
    System.err.println("dddddddddddddd");
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Adoption.fxml"));
        Parent root = loader.load();

        AdoptionController controller = loader.getController(); // retrieve the controller instance
        // call any methods on the controller instance if needed

        Scene scene = new Scene(root);
        Stage stage = (Stage) switchButton1.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException ex) {
        System.out.println("error:"+ex.getMessage());
    }
}
/*
@FXML
    public void Select() {
        r = tableview.getSelectionModel().getSelectedItem();
        //int  num = tableview.getSelectionModel().getSelectedIndex();

        if (r != null) {
            tftitre.setText(r.getTitre_reclamation());
            tadescription.setText(r.getDescription_reclamation());
            id = r.getId_reclamation();
            titreRecToDisplay=r.getTitre_reclamation();
            // date à convertir en LocalDate
            /* Instant instant = r.getDate().toInstant(); // conversion de la date en Instant
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault()); // conversion de l'instant en ZonedDateTime
        LocalDate localDate = zonedDateTime.toLocalDate();
        cdate.setValue(localDate);
        }

    }*/
@FXML
private void handleSortBySexe() {
    ObservableList<Adoption> sortedList = tableview.getItems().sorted(Comparator.comparing(Adoption::getSexe));
    tableview.setItems(sortedList);
}
@FXML
public void sortTableByRace() {
    ObservableList<Adoption> sortedList = adop.sorted(Comparator.comparing(Adoption::getRace));
    tableview.setItems(sortedList);
}
/*@FXML
    private void EnvoyerSMS(ActionEvent event) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message;
        message = com.twilio.rest.api.v2010.account.Message
                .creator(
                        new PhoneNumber("+21693149809"),
                        new PhoneNumber("+16318886632"),
                        "Votre adoption à été ajouté"
                )
                .create();

        System.out.println(message.getSid());
    }*/
/*---------------------------PDF STAT ----------------------*/
    @FXML
    private void printPDF() throws FileNotFoundException, DocumentException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File selectedFile = fileChooser.showSaveDialog(null);
    if (selectedFile != null) {
        // create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream(selectedFile));

        document.open();

        PdfPTable table = new PdfPTable(5);

        PdfPCell nomCell = new PdfPCell(new Phrase("nom"));
        PdfPCell descriptionPCell = new PdfPCell(new Phrase("description"));
        PdfPCell proprietaireCell = new PdfPCell(new Phrase("proprietaire"));
        PdfPCell raceCell = new PdfPCell(new Phrase("race"));
        PdfPCell sexeCell = new PdfPCell(new Phrase("sexe"));
        

        table.addCell(nomCell);
        table.addCell(descriptionPCell);
        table.addCell(proprietaireCell);
        table.addCell(raceCell);
        table.addCell(sexeCell);

        for (Adoption adoption : tableview.getItems()) {
            table.addCell(adoption.getNom());
            table.addCell(adoption.getDescription());
            table.addCell(adoption.getProprietaire());
            table.addCell(String.valueOf(adoption.getRace()));
            table.addCell(String.valueOf(adoption.getSexe()));
        }

        document.add(table);
        document.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF Export");
        alert.setHeaderText("Export successful");
        alert.setContentText("The Table has been exported to PDF successfully.");
        alert.showAndWait();
    }
}


    
}
