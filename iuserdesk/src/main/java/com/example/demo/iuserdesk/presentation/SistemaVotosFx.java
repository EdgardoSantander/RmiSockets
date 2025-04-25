package com.example.demo.iuserdesk.presentation;

import com.example.demo.iuserdesk.IuserdeskApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemaVotosFx extends Application {

    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void init(){
        this.configurableApplicationContext = new SpringApplicationBuilder(IuserdeskApplication.class).run();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(IuserdeskApplication.class.getResource("/fxml/index.fxml"));
        loader.setControllerFactory(configurableApplicationContext::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();

    }
    @Override
    public void stop(){
        configurableApplicationContext.close();
        Platform.exit();
    }
}
