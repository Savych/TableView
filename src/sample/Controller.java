package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    private boolean editing;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> surname;
    @FXML
    private TextField name_tf;
    @FXML
    private TextField surname_tf;
    @FXML
    private TabPane tabPane;

    private ObservableList<Person> items = FXCollections.observableArrayList(
            new Person("Savely", "Kavykov"),
            new Person("Lev", "Kirilov"));

    @FXML
    private void commit() {
        if(editing) {
            table.getSelectionModel().getSelectedItem().set(name_tf.getText(), surname_tf.getText());
            editing = false;
            table.refresh();
        } else
            items.add(new Person(name_tf.getText(), surname_tf.getText()));
        name_tf.clear();
        surname_tf.clear();
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void initialize() {
        table.itemsProperty().setValue(items);

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.setRowFactory(param -> {
            TableRow<Person> row = new TableRow<>();
            MenuItem remove = new MenuItem("Remove");
            MenuItem edit = new MenuItem("Edit");
            edit.setOnAction(event -> {
                name_tf.setText(table.getSelectionModel().getSelectedItem().getName());
                surname_tf.setText(table.getSelectionModel().getSelectedItem().getSurname());
                editing = true;
            });
            remove.setOnAction(event -> items.remove(table.getSelectionModel().getSelectedItem()));
            ContextMenu menu = new ContextMenu(edit, remove);
            row.contextMenuProperty().setValue(menu);
            return row;
        });

        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        surname.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
    }
}