package OOP_UrGame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



//TODO: Suhtlemine kasutajaga peab olema realiseeritud graafilise kasutajaliidese abil.
// Programm peab töötlema nii hiire kui ka klaviatuuriga tekitatud sündmusi.

//TODO: Programmi akna suurust muutes peab kuvatu mõistlikult muutuma.
//TODO: Erinditöötluse abil tagada, et toimuks mõistlik reageerimine kasutaja ekslikele tegevustele .

//TODO: Programm peab mingid andmed kirjutama faili ja neid failist ka lugema.
//TODO: Programm käivitamisel annab vajaliku üldtutvustava lühiinfo.


public class Graafika extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage peaLava) {

        //Algus ekraan:
        Stage esileht = new Stage();
        Label label = new Label("Mäng Ur");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font: 30 ariel;");
        DropShadow shadow = new DropShadow();

        Text tekst = new Text("Mängureeglid:\n" +
                        "Mängu eesmärk on viia enda 6 nuppu esimesena mängu lõppu.\n" +
                        "Iga käigu alguses visatakse 4 täringut, millel kõigil on 50/50 võimalus tagastada 1 või 0 \n" +
                        "Mängija valib nupu, mida soovib liigutada ning liigutab seda vastav arvu ruute(0-4)\n" +
                        "Kui mängija nupule, mis asub mänguväljakul maandub peale vastase nupp, siis läheb nupp algusesse tagasi.\n" +
                        "Boonusruudud on tähistatud #-ga. Sellele maandudes saab uuesti täringuid veeretada. Kui su nupp on boonusruudul\n" +
                        ", siis on see kaitsud vaenlase rünnakute eest.\n" +
                        "Selleks, et mänguväljalt lahkuda, pead veeretama täpse arvu silmasid, kui veeretasid rohkem, siis käia ei saa.");
        tekst.setStyle("-fx-font: 13 ariel;");

        //Alusta nupp
        Button alustaNupp = new Button("Alusta");
        alustaNupp.setOnMouseEntered(event -> alustaNupp.setEffect(shadow));
        alustaNupp.setOnMouseExited(event -> alustaNupp.setEffect(null));
        alustaNupp.setOnAction(event -> {
                esileht.hide();
                peaLava.show();
        });


        FlowPane paneJälle = new FlowPane(10, 10);
        paneJälle.setAlignment(Pos.CENTER);
        paneJälle.getChildren().addAll(alustaNupp);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, tekst, paneJälle);

        Scene stseen1 = new Scene(vBox);
        esileht.setScene(stseen1);
        esileht.show();


        //Mängu ekraan:
        //TODO: Graafika mängulaud
        Group juur = new Group();
        FlowPane pane1 = new FlowPane();
        FlowPane pane2 = new FlowPane();
        Button veeretaNupp = new Button("Veereta");
        veeretaNupp.setOnMouseEntered(event -> veeretaNupp.setEffect(shadow));
        veeretaNupp.setOnMouseExited(event -> veeretaNupp.setEffect(null));
        pane1.setAlignment(Pos.BOTTOM_CENTER);

        //TODO: Parem pildi avamine.
        String imagePath = "https://lh3.googleusercontent.com/a5zVjDqzg-Hqw1QEU0djgCUErcF0u5CLLUSD4PfOB6GrnEZthxVmd5zghRjjcM85OCW-Ult07ZOqbbPl9J8y5Yk7dNF52iuYsZAHZ9Ie4Qm6vk6aWXDTXlF4QYYYPDFZzQo_eeDLJxEbaWS6_a6GfO3bmo0ZvcdgyXjLeuMuzA_4SwuV1TNKxiAJA_VLRHusY_JSdyeOCME_Nn5DK12hJOmC2eX8fJa1CDNcx7iBBoGhghJ5Aoix_geblFq4VCl8yIg-t9uUC7fqyLYCWEN0WwyPASzRpH7YtZ2oNGnUnCvtADHSsbMPaexyVCpIuqDw5bBxi4kDHmJLTUJMfvNOZVVO6oyB3PbxbZPE6bQkMXqgkzWkAiGcXBCx4W6QWJojL2SdWIK7twDfYcaIrqD-JfRm5yxgwpPM-oG1FcE2cYSyNxWi6fk1z5zBDKevonS4hnr5cKshWYK2gY1Py8ddaWSeAbwoekyaRNnCBivWuqn-txxGCrphVCEE9z_kLW4j29pn3qJZpRnalMRDNCiQ-9D7sIAm0D5Oq34il_ygSQP2lGqVB-w9yVE5uA46C_lfIZ6TzQO1qU5tqOUi_jDmj3ajQjZxsbwI-ckFuXVhOvjV4hdc=w1227-h476-no";
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        pane2.setAlignment(Pos.CENTER);

        pane1.getChildren().addAll(veeretaNupp);
        pane2.getChildren().addAll(imageView);
        juur.getChildren().addAll(pane1,pane2);

        Scene stseen2 = new Scene(juur, 720, 480);
        peaLava.setTitle("UR mäng");
        peaLava.setResizable(true);
        peaLava.setScene(stseen2);


        //Võiduteksti väljastamine eraldi aknana:
        //TODO: kuidas seda korrektselt teha?
        if (mängulaud.võiduKontroll() == 1) {
            peaLava.setOnShowing(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    Stage võitja1 = new Stage();
                    Label label1 = new Label("Arvuti võitis!");

                    FlowPane pane = new FlowPane(10, 10);
                    pane.setAlignment(Pos.CENTER);

                    VBox vBox = new VBox(10);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.getChildren().addAll(label1, pane);

                    Scene stseen1 = new Scene(vBox);
                    võitja1.setScene(stseen1);
                    võitja1.show();
                }
            });
        }
        if (mängulaud.võiduKontroll() == 2) {
            peaLava.setOnShowing(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    Stage võitja2 = new Stage();
                    Label label2 = new Label("Sina võitsid!");

                    FlowPane pane = new FlowPane(10, 10);
                    pane.setAlignment(Pos.CENTER);

                    VBox vBox2 = new VBox(10);
                    vBox2.setAlignment(Pos.CENTER);
                    vBox2.getChildren().addAll(label2, pane);

                    Scene stseen2 = new Scene(vBox2);
                    võitja2.setScene(stseen2);
                    võitja2.show();
                }
            });
        }

        //Sulgemise kontroll:
        peaLava.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Stage kusimus = new Stage();
                Label label = new Label("Kas tõesti tahad kinni panna?");

                Button okButton = new Button("Jah");
                okButton.setOnMouseEntered(e -> okButton.setEffect(shadow));
                okButton.setOnMouseExited(e -> okButton.setEffect(null));
                okButton.setOnAction(e -> kusimus.hide());

                Button cancelButton = new Button("Ei");
                cancelButton.setOnMouseEntered(e -> cancelButton.setEffect(shadow));
                cancelButton.setOnMouseExited(e -> cancelButton.setEffect(null));
                cancelButton.setOnAction(e -> {
                    peaLava.show();
                    kusimus.hide();
                });

                // Kui küsimus akna kinni panna, siis ei lähe programm kinni.
                kusimus.setOnCloseRequest(event1 ->  {
                    peaLava.show();
                    kusimus.hide();
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

    }
}
