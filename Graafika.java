package OOP_UrGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.w3c.dom.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



//TODO: Erinditöötluse algul nime küsimesele
//TODO: Failist lugemine



public class Graafika extends Application {

    //Kirjutb käigu logi faili
    private static void fileWriter(String rida, String failinimi) throws Exception {
        try (FileWriter fileWriter = new FileWriter(failinimi, true);
             BufferedWriter puhverVälja = new BufferedWriter(fileWriter)) {
            puhverVälja.write(rida);
            puhverVälja.newLine();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage peaLava) throws Exception {
        //Objektid
        final Täring täring = new Täring();
        final Mängulaud mängulaud = new Mängulaud();
        final Arvuti arvuti = new Arvuti();

        //Loob logi faili:
        final String failiNimi = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm")) + ".txt";
        File logiFail = new File(failiNimi);
        logiFail.createNewFile();


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
                "Boonusruudud on punased. Sellele maandudes mitte midagi ei juhtu. Kui su nupp on boonusruudul\n" +
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

        TextField nimi = new TextField();
        nimi.setPromptText("Sisestage oma eesnimi: ");

        try {
            if (!nimi.getText().isEmpty()) {
                nimi.getText();
            }
        }

        catch (Exception e) {
            System.out.println("Viga! Sa ei sisestanud oma eesnime!");
        }

        FlowPane paneJälle = new FlowPane(10, 10);
        paneJälle.setAlignment(Pos.CENTER);
        paneJälle.getChildren().addAll(alustaNupp, nimi);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, tekst, paneJälle);

        Scene stseen1 = new Scene(vBox);
        esileht.setScene(stseen1);
        esileht.show();



        //Mängulaua graafika
        GridPane lauaPane = new GridPane();
        lauaPane.setMinSize(800,300);
        lauaPane.setPadding(new Insets(10, 10, 10, 10));
        lauaPane.setVgap(5);
        lauaPane.setHgap(5);
        lauaPane.setGridLinesVisible(true);
        //lauaPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                Rectangle rectangle = new Rectangle(100,100);
                // Algused
                if (i<6 && (j < 2 || j > 4)) {
                    rectangle.setFill(Color.LIGHTGRAY);
                    lauaPane.add(rectangle,i,j);
                }
                // Boonus
                else if ((i == 0 && (j == 2 || j == 4)) || (i == 6 && (j == 2 || j == 4)) || (i == 3 && j == 3)) {
                    rectangle.setFill(Color.RED);
                    lauaPane.add(rectangle,i,j);
                }
                // Transparent
                else if ((i > 5 && (j < 2 || j > 4)) || (j != 3 && (i == 4 || i == 5))) {
                    rectangle.setFill(Color.TRANSPARENT);
                    lauaPane.add(rectangle,i,j);
                }
                //  Sinine
                else {
                    rectangle.setFill(Color.BLUE);
                    lauaPane.add(rectangle, i, j);
                }
            }
        }

        // Nuppude väljastamine
        for (Mängunupp mängunupp : mängulaud.getArvutiNuppud()) {
            lauaPane.add(mängunupp,mängunupp.getI(),mängunupp.getJ());
        }
        for (Mängunupp mängunupp : mängulaud.getMängijaNuppud()) {
            lauaPane.add(mängunupp,mängunupp.getI(),mängunupp.getJ());
        }

        //Pealava akna osad:
        BorderPane borderPane = new BorderPane();
        FlowPane pane1 = new FlowPane();
        pane1.getChildren().addAll(lauaPane);
        borderPane.setCenter(pane1);
        pane1.setAlignment(Pos.CENTER);

        Group juur = new Group();
        FlowPane pane2 = new FlowPane();
        Button veeretaNupp = new Button("Veereta");
        veeretaNupp.setOnMouseEntered(event -> veeretaNupp.setEffect(shadow));
        veeretaNupp.setOnMouseExited(event -> veeretaNupp.setEffect(null));
        pane2.setAlignment(Pos.BOTTOM_CENTER);
        borderPane.setBottom(pane2);

        pane1.getChildren().addAll(veeretaNupp);
        juur.getChildren().addAll(borderPane);

        Scene stseen2 = new Scene(juur, 1080, 900);
        peaLava.setTitle("UR mäng");
        peaLava.setResizable(false);
        peaLava.setScene(stseen2);

        //Äkki saab niimoodi erinevat teksti väljastada?
        TextField tekstField = new TextField();
        tekstField.setAlignment(Pos.CENTER_RIGHT);
        pane1.getChildren().addAll(tekstField);
        tekstField.setText("Sinu käik");




        //Game loop
        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                //Objektide loomine.
                List<Mängunupp> lubatud = new ArrayList<>();
                int silmadeArv;
                int liigutatudNuppuAsukoht;


                if (mängulaud.võiduKontroll() != 0)
                    this.stop();

                // Veereta nuppu vajutus paneb tööle kasutaja ja arvuti loopid.
                veeretaNupp.setOnMouseClicked(event -> {
                    veeretaNupp.setDisable(true);


                    int silmad = täring.veereta();
                    System.out.println(silmad);
                    tekstField.setText("Täring veeretas " + silmad);


                    //Kontroll
                    List<Mängunupp> lubatudList  = mängulaud.kontroll(silmad, mängulaud.getMängijaAlgus(), mängulaud.getMängijaTee(), mängulaud.getArvutiTee());

                    if (silmad != 0 && lubatudList.size() != 0) {
                        for (Mängunupp mängunupp : mängulaud.getMängijaNuppud()) {
                            mängunupp.setOnMouseClicked(e -> {
                                if (lubatudList.contains(mängunupp)) {
                                    mängulaud.liiguta(mängunupp, silmad);
                                    mängulaud.väljastaLaud();
                                    lauaPane.getChildren().removeAll(mängulaud.getMängijaNuppud());
                                    lauaPane.getChildren().removeAll(mängulaud.getArvutiNuppud());
                                    for (Mängunupp nupp : mängulaud.getArvutiNuppud()) {
                                        lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                                    }
                                    for (Mängunupp nupp : mängulaud.getMängijaNuppud()) {
                                        lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                                    }

                                    //Logi
                                    try {
                                        Graafika.fileWriter(mängulaud.tagastaLaud(), failiNimi);
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }

                                    for (Mängunupp mängunupp1 : mängulaud.getMängijaNuppud()) {
                                        mängunupp1.setOnMouseClicked(null);
                                    }

                                    int silmadArvuti = täring.veereta();
                                    tekstField.setText("Arvuti! Täring =" + silmadArvuti);

                                    List<Mängunupp> lubatudListArvuti  = mängulaud.kontroll(silmadArvuti, mängulaud.getArvutiAlgus(), mängulaud.getArvutiTee(), mängulaud.getMängijaTee());
                                    Mängunupp arvutiKäik = arvuti.suvalineKäik(lubatudListArvuti);

                                    mängulaud.liiguta(arvutiKäik, silmadArvuti);
                                    mängulaud.väljastaLaud();
                                    lauaPane.getChildren().removeAll(mängulaud.getMängijaNuppud());
                                    lauaPane.getChildren().removeAll(mängulaud.getArvutiNuppud());
                                    for (Mängunupp nupp : mängulaud.getArvutiNuppud()) {
                                        lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                                    }
                                    for (Mängunupp nupp : mängulaud.getMängijaNuppud()) {
                                        lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                                    }

                                    //Logi
                                    try {
                                        Graafika.fileWriter(mängulaud.tagastaLaud(), failiNimi);
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                            });
                        }
                    }
                    else {
                        tekstField.setText("Jääd vahele!");
                        //Jääd vahele
                        int silmadArvuti = täring.veereta();
                        tekstField.setText("Arvuti! Täring =" + silmadArvuti);

                        List<Mängunupp> lubatudListArvuti  = mängulaud.kontroll(silmadArvuti, mängulaud.getArvutiAlgus(), mängulaud.getArvutiTee(), mängulaud.getMängijaTee());
                        Mängunupp arvutiKäik = arvuti.suvalineKäik(lubatudListArvuti);

                        mängulaud.liiguta(arvutiKäik, silmadArvuti);
                        mängulaud.väljastaLaud();
                        lauaPane.getChildren().removeAll(mängulaud.getMängijaNuppud());
                        lauaPane.getChildren().removeAll(mängulaud.getArvutiNuppud());
                        for (Mängunupp nupp : mängulaud.getArvutiNuppud()) {
                            lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                        }
                        for (Mängunupp nupp : mängulaud.getMängijaNuppud()) {
                            lauaPane.add(nupp, nupp.getI(), nupp.getJ());
                        }

                        //Logi
                        try {
                            Graafika.fileWriter(mängulaud.tagastaLaud(), failiNimi);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }


                    veeretaNupp.setDisable(false);
                });
            }
        }.start();



        //Võiduteksti väljastamine eraldi aknana:
        if (mängulaud.võiduKontroll() != 0) {
            Stage võitjaLõpp = new Stage();
            FlowPane paneLõpp = new FlowPane(10, 10);
            Label labelLõpp = new Label();
            paneLõpp.setAlignment(Pos.CENTER);

            VBox vBoxLõpp = new VBox(10);
            vBoxLõpp.setAlignment(Pos.CENTER);

            //Arvuti võit
            if (mängulaud.võiduKontroll() == 1) {
                labelLõpp.setText("Arvuti võitis.");
            }
            //Kasutaja võit
            else if (mängulaud.võiduKontroll() == 2) {
                labelLõpp.setText("Sina võitsid!.");
            }
            vBoxLõpp.getChildren().addAll(labelLõpp, paneLõpp);

            Scene stseenLõpp = new Scene(vBoxLõpp);
            võitjaLõpp.setScene(stseenLõpp);
            võitjaLõpp.setTitle("Mäng läbi!");
            võitjaLõpp.show();

            võitjaLõpp.setOnCloseRequest(event1 -> {
                peaLava.hide();
                võitjaLõpp.hide();
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
                kusimus.setOnCloseRequest(event1 -> {
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

