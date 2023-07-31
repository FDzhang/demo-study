package cn.fd.javafx1;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

public class PersonBuilderFactory implements BuilderFactory {
    private final JavaFXBuilderFactory jff = new JavaFXBuilderFactory();
    @Override
    public Builder<?> getBuilder(Class<?> type) {
        if (type == Person.class) {
//            return new PersonBuilder();
            return new PersonBuilderMap();
        } else {
            return jff.getBuilder(type);
        }
    }
}
