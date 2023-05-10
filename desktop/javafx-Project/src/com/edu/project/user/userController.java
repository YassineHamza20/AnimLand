/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import DBCnx.MyConnection;
import static DBCnx.MyConnection.MyConnection;
import com.edu.project.entities.user;
import com.edu.project.entities.user;
import com.edu.project.entities.user;
import com.edu.project.service.GeneralServices;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.mindrot.jbcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author islem
 */
public class userController implements Initializable {

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;



    @FXML
    private Button updatebutton;
 
    @FXML
    private TableColumn<user, String> actions;

@FXML
    private Hyperlink interfaceproduit;

    @FXML
    void ONclickProduit(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
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
    private Hyperlink affrec;
  @FXML
    void Onclickaffrec(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailswindow.fxml"));
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
    private TextField emailtf;
    @FXML
    private TextField lasttf;
    @FXML
    private TextField pwdtf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField firsttf;
    @FXML
    private ComboBox roletf;
    @FXML
    private TableView<user> tabuser;
    @FXML
    private TableColumn<user, String> colemail;
    @FXML
    private TableColumn<user, String> colusername;
    @FXML
    private TableColumn<user, String> colfirst;
    @FXML
    private TableColumn<user, String> collast;
    @FXML
    private TableColumn<user, String> colpwd;
    
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<user, String> colrole;
    @FXML
    private Button updatebutton1;
    
    @FXML
    private Hyperlink servicevet;

    @FXML
    private Hyperlink afficheadop;
      @FXML
    void OnclickAdoption(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Adoption.fxml"));
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
    void OnclickServicevet(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocuments.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.add("[\"ROLE_ADMIN\"]");
        list.add("[\"ROLE_USER\"]");
        list1.add("Admin");
        list1.add("User");
        
        
        roletf.setItems(list1);
        showusers();
        //showRec();
        //searchRec();
    }

    public ObservableList<user> getuserList() {
        ObservableList<user> compteList = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            user user;

            while (rs.next()) {
         
                user = new user(rs.getInt("id"),rs.getString("email") , rs.getString("username"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("roles"), rs.getString("password"));
                compteList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return compteList;
    }

    /*/////////////////////////////////////////////////Controle de saisie/////////////////////////////////////////////////////////////////////*/
    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String email = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (lasttf.getText().isEmpty()) {
            showAlert("Error", "Tel field is required.");
            return false;
        }
        if (pwdtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (pwdtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (usernametf.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
            return false;
        }
        if (firsttf.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
            return false;
        }
        return true;
    }


    public void showusers() {
        ObservableList<user> list = getuserList();
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colusername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colfirst.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        collast.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        colpwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        tabuser.setItems(list);
        Callback<TableColumn<user, String>, TableCell<user, String>> cellFoctory = (TableColumn<user, String> param) -> {
            // make cell containing buttons
            final TableCell<user, String> cell = new TableCell<user, String>() {
                @Override
                public void updateItem(String item, boolean empty) {

                    user user = null;
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView blockIcon = new FontAwesomeIconView(FontAwesomeIcon.LOCK);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        blockIcon.setStyle(
                               " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                 + "-fx-fill:#000000;"
                        );
                        FontAwesomeIconView unblockIcon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK);
                       unblockIcon.setStyle(
                         "-fx-cursor: hand;"
                        + "-glyph-size: 28px;"
                      + "-fx-fill: #2196F3;"
                            );
                        
                        blockIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            
                            //lhneeeee metier block w deblock 
    @Override
    public void handle(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment bloquer cet utilisateur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                PreparedStatement ps = null;
                user users = tabuser.getSelectionModel().getSelectedItem();
                String query = "UPDATE `user` SET `blocked` = ? WHERE `id` = ?";
                Connection conn = MyConnection();
                ps = conn.prepareStatement(query);
                ps.setInt(1, 1);
                ps.setInt(2, users.getId());
                ps.executeUpdate();
                showusers();
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (result.get() == ButtonType.CANCEL) {
            showusers();
        }
    }});
                        unblockIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
        user selectedUser = tabuser.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment débloquer cet utilisateur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (selectedUser != null) {
            selectedUser.setBlocked(false);
            try {
                PreparedStatement ps = null;
                String query = "UPDATE `user` SET blocked = 0 WHERE id =" + selectedUser.getId();
                Connection conn = MyConnection();
                ps = conn.prepareStatement(query);
                ps.execute();
                showusers();
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
});
                        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Alert!");
                                alert.setContentText("This is an alert");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    
                                
                                try {
                                    PreparedStatement ps = null;
                                    user users;
                                    users = tabuser.getSelectionModel().getSelectedItem();
                                    String query = "DELETE FROM `user` WHERE id =" + users.getId();
                                    Connection conn = MyConnection();
                                    ps = conn.prepareStatement(query);
                                    ps.execute();
                                    showusers();

                                } catch (SQLException ex) {
                                    Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                } else if (result.get() == ButtonType.CANCEL) {
                                    showusers();
                                }
                            }
                        });

                        editIcon.setOnMouseClicked((new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {

                                user users = tabuser.getSelectionModel().getSelectedItem();
                                
                                emailtf.setText(String.valueOf(users.getEmail()));
                                usernametf.setText(String.valueOf(users.getUserName()));
                                 pwdtf.setText(String.valueOf(users.getPassword()));
                                 firsttf.setText(String.valueOf(users.getFirst_name()));
                                 lasttf.setText(String.valueOf(users.getLast_name()));
                                
                                

                            }

                        }));
                        HBox managebtn = new HBox(editIcon, deleteIcon,blockIcon,unblockIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(blockIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(unblockIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;
        };
        actions.setCellFactory(cellFoctory);
        tabuser.setItems(list);

    }

  
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    

    @FXML
    private void onupdate(ActionEvent event) {
        if (event.getSource() == updatebutton) {
             if (validateFields()) {
        String email = emailtf.getText();
        String username = usernametf.getText();
        String pwd = pwdtf.getText();
        String first = firsttf.getText();
        String last = lasttf.getText();
        String role = list.get(list1.indexOf(roletf.getValue()));
            user user = tabuser.getSelectionModel().getSelectedItem();
            String query = "UPDATE user SET email = '" + email + "' ,  username = '" + username + "' , first_name = '" + first + "' , last_name = '" + last + "', roles = '" + role + "', password = '" + pwd + "' WHERE id='" + user.getId() + "' ";
            executeQuery(query);
            showusers();
        }
        }
    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
     public boolean test_used_pseudo_or_email(user c1)
            {
             int a;
              Statement st;
        ResultSet rs;
             Connection conn = MyConnection();
         String req="select id from user WHERE email = '"+c1.getEmail()+"'"; 
        try {
            st = conn.createStatement();
             rs = st.executeQuery(req);
             rs.next();
             a = rs.getInt("id");
        return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
            }
    public void update_mdp(String new_mdp,String mail)
    {
        Statement st;
        ResultSet rs;
             Connection conn = MyConnection();
        GeneralServices gs = new GeneralServices (); 
          String mdp_enc = BCrypt.hashpw(new_mdp, BCrypt.gensalt()); ;
         String req = "UPDATE user SET password ='"+mdp_enc+"'WHERE email ='"+mail+"'";
        try {
            st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("succes mdp ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void SignOut(MouseEvent event) {
        try {
            // Get the previous stage
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Set the previous stage as the owner of the new stage
            newStage.initOwner(previousStage);

            // Show the new stage and hide the previous stage
            
            previousStage.hide();
            newStage.show();

        } catch (IOException ex) {
            // Handle the exception
        }
    }




}
