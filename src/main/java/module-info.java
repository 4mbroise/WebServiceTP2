module com.app.webservicetp2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jersey.client;
    requires jersey.core;
    requires json.simple;
    requires java.xml;
    requires java.desktop;

    opens com.app.webservicetp2 to javafx.fxml;
    exports com.app.webservicetp2;
}