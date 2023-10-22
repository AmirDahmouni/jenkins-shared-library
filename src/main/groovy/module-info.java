module com.example.jenkinssharedlibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.groovy;


    opens com.example.jenkinssharedlibrary to javafx.fxml;
    exports com.example.jenkinssharedlibrary;
}