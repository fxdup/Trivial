/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.UnknownHostException;
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
    public Menu(){
        
        ImageView back = new ImageView(new Image("file:board.png"));
        back.setFitWidth(1920);
        back.setFitHeight(1080);
        
        ImageView sky = new ImageView(new Image("file:sky.jpg"));
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
            Options();
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

    private void Options() {
       
        HBox slider = new HBox();
        slider.setAlignment(Pos.CENTER);
        slider.setSpacing(5);
        Slider res = new Slider();
        res.setMin(1);
        res.setMax(100);
        res.setValue(100);
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
            }
        });
        
        Text sound = new Text("Sound");
        sound.getStyleClass().add("submenu");
        
        Text back = new Text("Back");
        back.getStyleClass().addAll("submenu","yellowHover");
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        slider.getChildren().addAll(res,reso);
        menu.getChildren().addAll(sound,slider,back);
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
        
        class MultithreadingDemo extends Thread 
{ 
    public void run() 
    { 
        try
        { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
}
        
        start.setOnMouseClicked(e->{
            ((Game)(getParent())).startGame();
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
            }catch(NumberFormatException | IOException ex){
                error.setText("Impossible to connect. Ip or Port might be invalid");
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
