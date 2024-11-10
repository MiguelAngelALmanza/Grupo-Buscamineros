module com.umss.buscaminas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;

    opens com.umss.buscaminas.presentacion to javafx.fxml;
    exports com.umss.buscaminas;
    opens com.umss.buscaminas.application to javafx.fxml;

}