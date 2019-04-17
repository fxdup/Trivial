/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author guill
 */
public class Menu extends StackPane{
    Player me;
    VBox menu = new VBox();
    int resolution;
    double resfactor;
    double sound;
    private boolean waiting=false;
    
    public Menu(double sound, int resolution,double resfactor) throws FileNotFoundException {
        this.sound=sound;
        this.resolution=resolution;
        this.resfactor=resfactor;
        ImageView back = new ImageView(new Image("/Resources/board.png"));
        back.setFitWidth(1920*resfactor);
        back.setFitHeight(1080*resfactor);
        
        ImageView sky = new ImageView(new Image("/Resources/background_image.jpg"));
        sky.setFitWidth(1920*resfactor);
        sky.setFitHeight(1080*resfactor);
        
        getChildren().addAll(sky,back,menu);
        menu.setAlignment(Pos.CENTER);
        Back();
    }
    
    public void Back(){
        menu.getChildren().clear();
        Text host = new Text("Host Game");
        host.getStyleClass().addAll("menu","redHover");
        host.setStyle("-fx-font: "+120*resfactor+"px EraserDust;");
        Text join = new Text("Join Game");
        join.getStyleClass().addAll("menu","blueHover");
        join.setStyle("-fx-font: "+120*resfactor+"px EraserDust;");
        Text options = new Text("Options");
        options.getStyleClass().addAll("menu","yellowHover");
        options.setStyle("-fx-font: "+120*resfactor+"px EraserDust;");
        menu.getChildren().addAll(host,join,options);

        
        options.setOnMouseClicked(e->{
            try {
                Options();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        host.setOnMouseClicked(e->{
            Host();
        });
        join.setOnMouseClicked(e->{
            Join();
        });
    }

    private void Options() throws FileNotFoundException {
        menu.getChildren().clear();
        String resolution_text="x 1";
        switch(resolution){
            case 1: resolution_text="x 1";break;
            case 2: resolution_text="x 3/4";break;
            case 3: resolution_text="x 2/3";break;
            case 4: resolution_text="x 1/2";break;
        }
        
        HBox slider = new HBox();
        slider.setAlignment(Pos.CENTER);
        slider.setSpacing(5*resfactor);
        Slider res = new Slider();
        res.setMin(1);
        res.setMax(100);
        res.setValue(sound);
        res.setMaxSize(600*resfactor, 60*resfactor);
        res.setMinSize(600*resfactor, 60*resfactor);
        res.setBlockIncrement(1);
        TextField reso = new TextField();
        reso.setText(""+sound);
        reso.setMaxWidth(45*resfactor);
        res.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number oldv, Number newv) {
                    reso.setText(Double.toString((double)newv));
                    sound = (double)newv;
            }
        });
        
        Text sound = new Text("Sound");
        sound.getStyleClass().addAll("submenu");
        sound.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        back.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        Text resolution_button = new Text("Size: "+resolution_text);
        resolution_button.getStyleClass().addAll("submenu","redHover");
        resolution_button.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        Text confirm = new Text("Confirm");
        confirm.getStyleClass().addAll("submenu","blueHover");
        confirm.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        resolution_button.setOnMouseClicked(e->{
            switch(resolution){
                case 1: resolution=2;resolution_button.setText("Size: x 3/4");break;
                case 2: resolution=3;resolution_button.setText("Size: x 2/3");break;
                case 3: resolution=4;resolution_button.setText("Sixe: x 1/2");break;
                case 4: resolution=1;resolution_button.setText("Size: x 1");break;
            }
        });
        
        confirm.setOnMouseClicked(e->{
            Confirmation();
        });
        slider.getChildren().addAll(res,reso);
        menu.getChildren().addAll(sound,slider,resolution_button,confirm,back);
    }
    
    public void Host(){
        menu.getChildren().clear();
        Text nm = new Text("Name:");
        nm.getStyleClass().add("submenu");
        nm.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        TextField name = new TextField();
        name.getStyleClass().add("textField");
        name.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        name.setMaxWidth(1000*resfactor);
        
        Text host = new Text("Host");
        host.getStyleClass().addAll("submenu","redHover");
        host.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        back.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        host.setOnMouseClicked(e->{
            
            try {
                me = new HostPlayer(name.getText(),((Game)(getParent())));
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Hosting();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        menu.getChildren().addAll(nm,name,host,back);
        
    }
    
    private void Join() {
        menu.getChildren().clear();
        Text nm = new Text("Name:");
        nm.getStyleClass().add("submenu");
        nm.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        TextField name = new TextField();
        name.getStyleClass().add("textField");
        name.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        name.setMaxWidth(1000*resfactor);
        
        Text join = new Text("Join");
        join.getStyleClass().addAll("submenu","blueHover");
        join.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        back.setStyle("-fx-font: "+90*resfactor+"px EraserDust;");
        
        join.setOnMouseClicked(e->{
            try {
                me = new ClientPlayer(name.getText(),((Game)(getParent())));
                Joining();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        menu.getChildren().addAll(nm,name,join,back);
    }
    
    private void Hosting() throws UnknownHostException {
        menu.getChildren().clear();
        menu.setSpacing(10*resfactor);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text number_of_players = new Text("Players joined : 1/40");
        Text start = new Text("Start");
        TextField ipt = new TextField(((HostPlayer)me).getIp());
        ipt.setEditable(false);
        TextField portt = new TextField(""+((HostPlayer)me).getPort());
        portt.setEditable(false);
        ipt.getStyleClass().add("textField");
        ipt.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        portt.getStyleClass().add("textField");
        portt.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        start.getStyleClass().addAll("textField","redHover");
        start.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        number_of_players.getStyleClass().add("textField");
        number_of_players.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        ip.getStyleClass().add("textField");
        ip.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        port.getStyleClass().add("textField");
        port.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        ipt.setMaxWidth(1000*resfactor);
        portt.setMaxWidth(1000*resfactor);
        Text back = new Text("Back");
        HBox startback = new HBox();
        startback.getChildren().addAll(start,back);
        startback.setAlignment(Pos.CENTER);
        startback.setSpacing(40*resfactor);
        menu.getChildren().addAll(ip,ipt,port,portt,number_of_players,startback);
        waiting=true;
        Timeline playerCount=new Timeline(new KeyFrame(Duration.seconds(1),e->{
        number_of_players.setText("Players joined : "+(((HostPlayer)me).getPlayerSize()+1)+"/40");
        }));
        playerCount.setCycleCount(Animation.INDEFINITE);
        playerCount.play();
        start.setOnMouseClicked(e->{
            playerCount.stop();
            ((HostPlayer)me).stopConnecting();
            ((HostPlayer)me).sendStart();
            start(true);
        });
        
        back.getStyleClass().addAll("submenu","yellowHover");
        back.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        back.setOnMouseClicked(e->{
            Host();
        });
    }

    private void Joining() throws UnknownHostException {
        menu.getChildren().clear();
        menu.setSpacing(10*resfactor);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text join = new Text("Join");
        TextField ipt = new TextField();
        TextField portt = new TextField();
        Text error =new Text("");
        error.setFill(Color.RED);
        ipt.getStyleClass().add("textField");
        ipt.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        portt.getStyleClass().add("textField");
        portt.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        join.getStyleClass().addAll("textField","blueHover");
        join.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        ip.getStyleClass().add("textField");
        ip.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        port.getStyleClass().add("textField");
        port.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        ipt.setMaxWidth(1000*resfactor);
        portt.setMaxWidth(1000*resfactor);
        Text back = new Text("Back");
        HBox joinback = new HBox();
        joinback.getChildren().addAll(join,back);
        joinback.setAlignment(Pos.CENTER);
        joinback.setSpacing(40*resfactor);
        menu.getChildren().addAll(ip,ipt,port,portt,error,joinback);
        
        join.setOnMouseClicked(e->{
            
            try {
                ((ClientPlayer)me).connect(ipt.getText(), parseInt(portt.getText()));
                waiting();
            }catch(IOException | IllegalArgumentException ex){
                error.setText("Impossible to connect. IP or Port is invalid");
            } catch (InterruptedException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });
        
        
        back.getStyleClass().addAll("submenu","yellowHover");
        back.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        back.setOnMouseClicked(e->{
            Join();
        });
    }

    private void waiting() throws UnknownHostException, InterruptedException, FileNotFoundException {
        menu.getChildren().clear();
        menu.setSpacing(10*resfactor);
        Text text = new Text("You are connected");
        Text text2 = new Text("Waiting for host to start");
        text.getStyleClass().add("textField");
        text.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        text2.getStyleClass().add("textField");
        text2.setStyle("-fx-font: "+60*resfactor+"px EraserDust;");
        waiting=true;
        menu.getChildren().addAll(text,text2);
    }
    
    public void start(boolean host){
        if(waiting){
            ((Game)(getParent())).startGame(host,me);
        }
    }
    private void Confirmation() {
        menu.getChildren().clear();
        Text message = new Text("To confirm the settings you will have to launch the application again. Confirm ?");
        message.getStyleClass().add("submenu");
        message.setStyle("-fx-font: "+35*resfactor+"px EraserDust;");
        Text yes = new Text("Yes");
        yes.getStyleClass().add("submenu");
        yes.setStyle("-fx-font: "+35*resfactor+"px EraserDust;");
        Text no = new Text("No");
        no.getStyleClass().add("submenu");
        no.setStyle("-fx-font: "+35*resfactor+"px EraserDust;");
        menu.getChildren().add(message);
        HBox buttons = new HBox();
        buttons.setSpacing(40*resfactor);
        buttons.getChildren().addAll(yes,no);
        buttons.setAlignment(Pos.CENTER);
        menu.getChildren().add(buttons);
        
        yes.setOnMouseClicked(e->{
            File file = new File("opt.txt");
            file.delete();
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("opt.txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.println(this.sound);
            writer.println(resolution);
            writer.close();
            try {
                Trivial.restart();
            } catch (FileNotFoundException ex) {
            }
        });
        no.setOnMouseClicked(e->{
            try {
                Options();
            } catch (FileNotFoundException ex) {
            }
        });
    }
}
