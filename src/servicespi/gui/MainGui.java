/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.gui;

import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import servicespi.ServicesPI;

import servicespi.controller.CategoryController;
import servicespi.controller.ServiceController;

import servicespi.entities.Category;
import servicespi.entities.Service;

/**
 *
 * @author Mynet
 */
public class MainGui {
    
    CategoryController categpryController = new CategoryController();
    ServiceController serviceController = new ServiceController();
    
    @FXML private TableView<Category> categoryTableView;
    @FXML private TableColumn<Category, Integer> idColumn;
    @FXML private TableColumn<Category, String> typeColumn;
    @FXML private Button deleteButton;
    @FXML private Button showButton;
    @FXML private Button editCategory;
    @FXML private TextField editTextField;
    @FXML private Button addButton;
    
    
    @FXML private TableView<Service> servicesTableView;
    @FXML private TableColumn<Service, Integer> ids;
    @FXML private TableColumn<Service, String> nom;
    @FXML private TableColumn<Service, String> prix;
    @FXML private TableColumn<Service, String> date;
    @FXML private TableColumn<Service, String> desc;
    @FXML private TableColumn<Service, Integer> category;
    @FXML private TableColumn<Service, Integer> views;
    @FXML private Button deleteService;
    @FXML private Button show;
    @FXML private TextField nomEdit;
    @FXML private TextField prixEdit;
    @FXML private TextField dateEdit;
    @FXML private TextField descEdit;
    @FXML private TextField categoryEdit;
    @FXML private Button updateList;
    @FXML private Button addService;
    
    
    
    @FXML public void initialize() {
        System.out.println("initializing categories");
        initCategories();
        System.out.println("initializing services");
        initServices();
    }
    
    @FXML public void refetch() {
        initCategories();
        categoryTableView.refresh();
        initServices();
        servicesTableView.refresh();
    }
    
    /** categories */
    private void initCategories() {
        List<Category> categories = fetchCategories();
        
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Category, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getId()).asObject();
            }
        });
        
        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new SimpleStringProperty(param.getValue().getType());
            }
        });
        
        categoryTableView.setItems(FXCollections.observableList(categories));
        
    }
    private List<Category> fetchCategories() {
        return categpryController.getAllCategories();
    }
    @FXML private void deleteCategoryFromTable(ActionEvent event) {
        Category idCaptured = categoryTableView.getSelectionModel().getSelectedItem();
        categoryTableView.getItems().remove(idCaptured);
        categpryController.deleteCategory(idCaptured.getId());
        System.out.println("delete =>" + idCaptured.toString());
        categoryTableView.refresh();
    }
    @FXML private void showCategoryWhenSelected(ActionEvent event) {
        Category idCaptured = categoryTableView.getSelectionModel().getSelectedItem();
        editTextField.setText(idCaptured.getType());
    }
    @FXML private void updateCategoryThatIsChanged(ActionEvent event) {
        Integer idCaptured = categoryTableView.getSelectionModel().getSelectedItem().getId();
        categpryController.updateCategory(new Category(idCaptured, editTextField.getText()));
        initCategories();
        categoryTableView.refresh();
    }
    @FXML private void openAddCategory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ServicesPI.class.getResource("FXMLADDCategory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    /** services */
    private void initServices() {
    List<Service> services = fetchServices();

    ids.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Integer>, ObservableValue<Integer>>() {
        @Override
        public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Service, Integer> param) {
            return new SimpleIntegerProperty(param.getValue().getId()).asObject();
        }
    });

    nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> param) {
            return new SimpleStringProperty(param.getValue().getNom());
        }
    });

    prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> param) {
            return new SimpleStringProperty(String.valueOf(param.getValue().getPrix()));
        }
    });

    date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> param) {
            return new SimpleStringProperty(param.getValue().getDate());
        }
    });

    desc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Service, String> param) {
            return new SimpleStringProperty(param.getValue().getDescription());
        }
    });

    category.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Integer>, ObservableValue<Integer>>() {
        @Override
        public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Service, Integer> param) {
            return new SimpleIntegerProperty(param.getValue().getCategory()).asObject();
        }
    });

    views.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Service, Integer>, ObservableValue<Integer>>() {
        @Override
        public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Service, Integer> param) {
            return new SimpleIntegerProperty(param.getValue().getViewCount()).asObject();
        }
    });

    servicesTableView.setItems(FXCollections.observableList(services));
}
    private List<Service> fetchServices() {
        return serviceController.getAllServices();
    }
    @FXML private void deleteServiceFromTable(ActionEvent event) {
        Service idCaptured = servicesTableView.getSelectionModel().getSelectedItem();
        servicesTableView.getItems().remove(idCaptured);
        serviceController.deleteService(idCaptured.getId());
        System.out.println("deleted => " + idCaptured.toString());
        servicesTableView.refresh();
    }
    @FXML private void showServiceWhenSelected(ActionEvent event) {
        Service idCaptured = servicesTableView.getSelectionModel().getSelectedItem();
        nomEdit.setText(idCaptured.getNom());
        prixEdit.setText(String.valueOf(idCaptured.getPrix()));
        dateEdit.setText(idCaptured.getDate());
        descEdit.setText(idCaptured.getDescription());
        categoryEdit.setText(String.valueOf(idCaptured.getCategory()));
    }
    @FXML private void updateServiceThatIsChanged(ActionEvent event) {
        Service idCaptured = servicesTableView.getSelectionModel().getSelectedItem();
        serviceController.updateService(new Service(
                idCaptured.getId(),
                Double.parseDouble(prixEdit.getText()),
                nomEdit.getText(),
                descEdit.getText(),
                dateEdit.getText(),
                idCaptured.getCategory(),
                idCaptured.getViewCount()
        ));
        initServices();
        servicesTableView.refresh();
    }
    @FXML private void openServiceWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ServicesPI.class.getResource("FXMLADDService.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
    
}

/** bhokem scene builder mahouch integre fel netbeans besh nkamlou lkol w mba3ed nzidou el edit! */