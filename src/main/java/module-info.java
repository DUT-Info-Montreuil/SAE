module com.example.sae {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.sae to javafx.fxml;
    exports com.example.sae;
    opens com.example.sae.controleur to javafx.fxml;
    exports com.example.sae.controleur;

}