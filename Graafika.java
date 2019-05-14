import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Graafika extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage peaLava) {
        BorderPane piir = new BorderPane();

        peaLava.setOnShowing(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Stage esileht = new Stage();
                Label label = new Label("Mäng Ur");
                label.setAlignment(Pos.CENTER_LEFT);
                //label.setTranslateX(120);
                //label.setTranslateY(100);
                label.setStyle("-fx-font: 30 ariel;");

                Text tekst = new Text("Mängureeglid:\n" +
                        "Mängu eesmärk on viia enda 6 nuppu esimesena mängu lõppu.\n" +
                        "Iga käigu alguses visatakse 4 täringut, millel kõigil on 50/50 võimalus tagastada 1 või 0 \n" +
                        "Mängija valib nupu, mida soovib liigutada ning liigutab seda vastav arvu ruute(0-4)\n" +
                        "Kui mängija nupule, mis asub mänguväljakul maandub peale vastase nupp, siis läheb nupp algusesse tagasi.\n" +
                        "Boonusruudud on tähistatud #-ga. Sellele maandudes saab uuesti täringuid veeretada. Kui su nupp on boonusruudul\n" +
                        ", siis on see kaitsud vaenlase rünnakute eest.\n" +
                        "Selleks, et mänguväljalt lahkuda, pead veeretama täpse arvu silmasid, kui veeretasid rohkem, siis käia ei saa.");

                Button alustaNupp = new Button("Alusta");

                alustaNupp.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        esileht.hide();
                    }
                });

                FlowPane paneJälle = new FlowPane(10, 10);
                paneJälle.setAlignment(Pos.CENTER);
                paneJälle.getChildren().addAll(alustaNupp);

                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(label, tekst, paneJälle);

                Scene stseen3 = new Scene(vBox);
                esileht.setScene(stseen3);
                esileht.show();
            }
        }
        );

        //mängulaud

        FlowPane mänguPane = new FlowPane();
        Button veeretaNupp = new Button("Veereta");
        veeretaNupp.setLayoutX(400);
        veeretaNupp.setLayoutY(200);
        mänguPane.getChildren().add(veeretaNupp);

        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER_RIGHT);
        vBox1.getChildren().addAll(mänguPane, veeretaNupp);

        peaLava.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                // luuakse teine lava
                Stage kusimus = new Stage();
                // küsimuse ja kahe nupu loomine
                Label label = new Label("Kas tõesti tahad kinni panna?");
                Button okButton = new Button("Jah");
                Button cancelButton = new Button("Ei");

                // sündmuse lisamine nupule Jah
                okButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        kusimus.hide();
                    }
                });

                // sündmuse lisamine nupule Ei
                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        peaLava.show();
                        kusimus.hide();
                    }
                });

                // nuppude grupeerimine
                FlowPane pane = new FlowPane(10, 10);
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(okButton, cancelButton);

                // küsimuse ja nuppude gruppi paigutamine
                VBox vBox = new VBox(10);
                vBox.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(label, pane);

                //stseeni loomine ja näitamine
                Scene stseen2 = new Scene(vBox);
                kusimus.setScene(stseen2);
                kusimus.show();
            }
        });

        Scene stseen1 = new Scene(piir, 300, 150);
        peaLava.setTitle("UR mäng");
        peaLava.setResizable(true);
        peaLava.setScene(stseen1);
        peaLava.show();
    }
}
