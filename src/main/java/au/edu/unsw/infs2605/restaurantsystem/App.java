package au.edu.unsw.infs2605.restaurantsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {
 
    private static Scene scene;
    
    /*
     * This will start the application. Upon launch the login screen will be
     * shown and the database will be called to configure. 
     */
    
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        /* Set scene as a the login screen. */
        stage.setTitle("Restaurant System");
        Scene scene = new Scene(loadFXML("LoginScreen"), 600, 405);
        //Scene scene = new Scene(loadFXML("MainScreen"));
        stage.setScene(scene);
        stage.show();
        System.out.println("LoginScreen Active");
        
        /* Database gets called here. */
        Database database = new Database();
        database.setupDatabase();
    }
        /* Standard app.java configuration to set, load and run. */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}