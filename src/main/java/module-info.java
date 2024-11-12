module com.umss.buscaminas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;

    opens com.umss.buscaminas.Controlador to javafx.fxml;
    exports com.umss.buscaminas;
    opens com.umss.buscaminas.Modelo to javafx.fxml;

    exports com.umss.buscaminas.Modelo;
}