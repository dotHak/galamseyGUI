//Package Name
package monitoring.gui.controller;

//Dependencies

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import monitoring.gui.model.Galamsey;
import monitoring.gui.model.Observatory;
import monitoring.gui.model.ObservatoryData;

/**
 * @author Hubert, Bobie, Lisa, Jones
 * @version 2.0.0
 * Controller class for deleting galamsey classes
 */

public class galamDeleteController {
    //instant variables
    @FXML
    private Button galamConfirm, galamCancel;

    @FXML
    Text deleteGalamseyId;

    /**
     * Initialize method to start the controller class
     */
    public void initialize(){
        deleteGalamseyId.setText(Integer.toString(MainController.galamseyID));
        galamConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) galamConfirm.getScene().getWindow();
                processDeletion();
                stage.close();
            }
        });

        galamCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) galamCancel.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     *  Method for deleting and updating the database and hash table
     */
    public void processDeletion(){
        /**
         * Thread class for running update in background;
         */
        Runnable task = new Runnable() {
            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Observatory observatory = ObservatoryData.getInstance().getObservatoryHashMap().get(MainController.observatoryID);
                        Galamsey galamsey =  ObservatoryData.getInstance().getGalamseyHashMap().get(MainController.galamseyID);
                        ObservatoryData.getInstance().getObservatoryHashMap().remove(MainController.galamseyID);
                        ObservatoryData.getInstance().getDatabase().deleteEntryFromGalamseyDB(MainController.galamseyID);
                        observatory.getGalamseyList().remove(galamsey);
                        ObservatoryData.getInstance().updateDatabase();
                    }
                });

            }
        };
        new Thread(task).start();

    }
}
