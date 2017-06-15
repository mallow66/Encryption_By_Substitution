package EncryptionUtils.Utils;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by brahim on 4/4/17.
 */
public class LayoutUtils {



    public   void changeView(BorderPane pane, String newView, int tabNumber)throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(newView));
        TabPane tabPane = (TabPane)pane.getParent().getParent();
        ObservableList<Tab> list = tabPane.getTabs();
        Tab mTab = list.get(tabNumber);
        mTab.setContent(root);


    }
}
