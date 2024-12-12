module org.benz_forza.projectbenzforza {
    requires javafx.controls;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens org.benz_forza.projectbenzforza.entities to org.hibernate.orm.core;
    exports org.benz_forza.projectbenzforza.views to javafx.graphics;
}