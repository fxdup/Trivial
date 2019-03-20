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

/**
 *
 * @author guill
 */
public class Menu extends StackPane{
    Player me;
    VBox menu = new VBox();
    int resolution=1;
    double sound=100;
    
    public Menu() throws FileNotFoundException {
        
        ImageView back = new ImageView(new Image("/Resources/board.png"));
        back.setFitWidth(1920);
        back.setFitHeight(1080);
        
        ImageView sky = new ImageView(new Image("/Resources/background_image.jpg"));
        sky.setFitWidth(1920);
        sky.setFitHeight(1080);
        
        getChildren().addAll(sky,back,menu);
        menu.setAlignment(Pos.CENTER);
        Back();
    }
    
    public void Back(){
        menu.getChildren().clear();
        Text host = new Text("Host Game");
        host.getStyleClass().addAll("menu","redHover");
        Text join = new Text("Join Game");
        join.getStyleClass().addAll("menu","blueHover");
        Text options = new Text("Options");
        options.getStyleClass().addAll("menu","yellowHover");
        menu.getChildren().addAll(host,join,options);

        
        options.setOnMouseClicked(e->{
            menu.getChildren().clear();
            try {
                Options();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        host.setOnMouseClicked(e->{
            menu.getChildren().clear();
            Host();
        });
        join.setOnMouseClicked(e->{
            menu.getChildren().clear();
            Join();
        });
    }

    private void Options() throws FileNotFoundException {
        String resolution_text="x 1";
        switch(resolution){
            case 1: resolution_text="x 1";break;
            case 2: resolution_text="x 1/2";break;
            case 3: resolution_text="x 1/3";break;
            case 4: resolution_text="x 1/4";break;
        }
        
        try{
            File opt = new File("opt.txt");
            Scanner input = new Scanner(opt);
            sound=input.nextDouble();
            resolution=input.nextInt();
        }
        catch(FileNotFoundException e){
            System.out.println("File not fuodn");
            PrintWriter writer = new PrintWriter("opt.txt");
            writer.println(sound);
            writer.println(resolution);
            writer.close();
        }
        HBox slider = new HBox();
        slider.setAlignment(Pos.CENTER);
        slider.setSpacing(5);
        Slider res = new Slider();
        res.setMin(1);
        res.setMax(100);
        res.setValue(sound);
        res.setMaxSize(600, 60);
        res.setMinSize(600, 60);
        res.setBlockIncrement(1);
        TextField reso = new TextField();
        reso.setText("100");
        reso.setMaxWidth(45);
        res.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number oldv, Number newv) {
                    reso.setText(Double.toString((double)newv));
                    sound = (double)newv;
            }
        });
        
        Text sound = new Text("Sound");
        sound.getStyleClass().add("submenu");
        
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        Text resolution_button = new Text("Size: "+resolution_text);
        resolution_button.getStyleClass().add("submenu");
        
        Text confirm = new Text("Confirm");
        confirm.getStyleClass().add("submenu");
        
        resolution_button.setOnMouseClicked(e->{
            switch(resolution){
                case 1: resolution=2;resolution_button.setText("Size: x 1/2");break;
                case 2: resolution=3;resolution_button.setText("Size: x 1/3");break;
                case 3: resolution=4;resolution_button.setText("Sixe: x 1/4");break;
                case 4: resolution=1;resolution_button.setText("Size: x 1");break;
            }
        });
        
        confirm.setOnMouseClicked(e->{
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
            Back();
        });
        slider.getChildren().addAll(res,reso);
        menu.getChildren().addAll(sound,slider,resolution_button,confirm,back);
    }
    
    public void Host(){
        menu.getChildren().clear();
        Text nm = new Text("Name:");
        nm.getStyleClass().add("submenu");
        TextField name = new TextField();
        name.getStyleClass().add("textField");
        name.setMaxWidth(1000);
        
        Text host = new Text("Host");
        host.getStyleClass().addAll("submenu","redHover");
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        
        host.setOnMouseClicked(e->{
            
            try {
                me = new HostPlayer(name.getText());
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
        TextField name = new TextField();
        name.getStyleClass().add("textField");
        name.setMaxWidth(1000);
        
        Text join = new Text("Join");
        join.getStyleClass().addAll("submenu","blueHover");
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        
        join.setOnMouseClicked(e->{
            try {
                me = new ClientPlayer(name.getText());
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
        menu.setSpacing(10);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text number_of_players = new Text("Players joined : 1/40");
        Text start = new Text("Start");
        TextField ipt = new TextField(((HostPlayer)me).getIp());
        ipt.setEditable(false);
        TextField portt = new TextField(""+((HostPlayer)me).getPort());
        portt.setEditable(false);
        ipt.getStyleClass().add("textField");
        portt.getStyleClass().add("textField");
        start.getStyleClass().addAll("textField","redHover");
        number_of_players.getStyleClass().add("textField");
        ip.getStyleClass().add("textField");
        port.getStyleClass().add("textField");
        ipt.setMaxWidth(1000);
        portt.setMaxWidth(1000);
        menu.getChildren().addAll(ip,ipt,port,portt,number_of_players,start);
        
       
        
        start.setOnMouseClicked(e->{
            ((Game)(getParent())).startGame(true);
        });
    }

    private void Joining() throws UnknownHostException {
        menu.getChildren().clear();
        menu.setSpacing(10);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text join = new Text("Join");
        TextField ipt = new TextField();
        TextField portt = new TextField();
        Text error =new Text("");
        error.setFill(Color.RED);
        ipt.getStyleClass().add("textField");
        portt.getStyleClass().add("textField");
        join.getStyleClass().addAll("textField","blueHover");
        ip.getStyleClass().add("textField");
        port.getStyleClass().add("textField");
        ipt.setMaxWidth(1000);
        portt.setMaxWidth(1000);
        menu.getChildren().addAll(ip,ipt,port,portt,error,join);
        
        join.setOnMouseClicked(e->{
            
            try {
                ((ClientPlayer)me).connect(ipt.getText(), parseInt(portt.getText()));
                waiting();
            }catch(IOException | IllegalArgumentException ex){
                error.setText("Impossible to connect. IP or Port is invalid");
            }
            
            
        });
    }

    private void waiting() throws UnknownHostException {
        menu.getChildren().clear();
        menu.setSpacing(10);
        Text text = new Text("You are connected");
        Text text2 = new Text("Waiting for host to start");
        text.getStyleClass().add("textField");
        text2.getStyleClass().add("textField");
        
        menu.getChildren().addAll(text,text2);
        
        
    }
}
