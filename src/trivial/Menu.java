/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

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
        host.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text join = new Text("Join Game");
        join.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text options = new Text("Options");
        options.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");
        menu.getChildren().addAll(host,join,options);
        
        host.setOnMouseEntered(e->{host.setStyle("-fx-font: 120px EraserDust;-fx-stroke: red;-fx-fill: red;");});
        host.setOnMouseExited(e->{host.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        join.setOnMouseEntered(e->{join.setStyle("-fx-font: 120px EraserDust;-fx-stroke: blue;-fx-fill: blue;");});
        join.setOnMouseExited(e->{join.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        options.setOnMouseEntered(e->{options.setStyle("-fx-font: 120px EraserDust;-fx-stroke: yellow;-fx-fill: yellow;");});
        options.setOnMouseExited(e->{options.setStyle("-fx-font: 120px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        
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
        sound.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        
        Text back = new Text("Back");
        back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        back.setOnMouseEntered(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: yellow;-fx-fill: yellow;");});
        back.setOnMouseExited(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        back.setOnMouseClicked(e->{
            Back();
        });
        
        slider.getChildren().addAll(res,reso);
        menu.getChildren().addAll(sound,slider,back);
    }
    
    public void Host(){
        menu.getChildren().clear();
        Text nm = new Text("Name:");
        nm.setStyle(("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;"));
        TextField name = new TextField();
        name.setStyle("-fx-background-color: transparent;-fx-font: 90px EraserDust;-fx-fill: white;-fx-border-color: white;");
        name.setMaxWidth(1000);
        
        Text host = new Text("Host");
        host.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text back = new Text("Back");
        back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        
        host.setOnMouseClicked(e->{
            Player me = new Player(name.getText());
            Hosting();
        });
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        host.setOnMouseEntered(e->{host.setStyle("-fx-font: 90px EraserDust;-fx-stroke: blue;-fx-fill: blue;");});
        host.setOnMouseExited(e->{host.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        back.setOnMouseEntered(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: yellow;-fx-fill: yellow;");});
        back.setOnMouseExited(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        
        
        menu.getChildren().addAll(nm,name,host,back);
        
    }
    
    private void Join() {
        menu.getChildren().clear();
        Text nm = new Text("Name:");
        nm.setStyle(("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;"));
        TextField name = new TextField();
        name.setStyle("-fx-background-color: transparent;-fx-font: 90px EraserDust;-fx-fill: white;-fx-border-color: white;");
        name.setMaxWidth(1000);
        
        Text join = new Text("Join");
        join.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text back = new Text("Back");
        back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");
        
        join.setOnMouseClicked(e->{
            Player me = new Player(name.getText());
            Joining();
        });
        
        back.setOnMouseClicked(e->{
            Back();
        });
        
        join.setOnMouseEntered(e->{join.setStyle("-fx-font: 90px EraserDust;-fx-stroke: blue;-fx-fill: blue;");});
        join.setOnMouseExited(e->{join.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        back.setOnMouseEntered(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: yellow;-fx-fill: yellow;");});
        back.setOnMouseExited(e->{back.setStyle("-fx-font: 90px EraserDust;-fx-stroke: white;-fx-fill: white;");});
        
        
        menu.getChildren().addAll(nm,name,join,back);
    }
    
    private void Hosting() {
        menu.getChildren().clear();
        menu.setSpacing(10);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text number_of_players = new Text("Players joined : 1/40");
        Text start = new Text("Start");
        TextField ipt = new TextField();
        TextField portt = new TextField();
        ipt.setStyle("-fx-background-color: transparent;-fx-font: 60px EraserDust;-fx-fill: white;-fx-border-color: white;");
        portt.setStyle("-fx-background-color: transparent;-fx-font: 60px EraserDust;-fx-fill: white;-fx-border-color: white;");
        start.setStyle("-fx-background-color: transparent;-fx-font: 80px EraserDust;-fx-fill: white;-fx-border-color: white;");
        number_of_players.setStyle("-fx-background-color: transparent;-fx-font: 80px EraserDust;-fx-fill: white;-fx-border-color: white;");
        ip.setStyle("-fx-font: 60px EraserDust;-fx-stroke: white;-fx-fill: white;");
        port.setStyle("-fx-font: 60px EraserDust;-fx-stroke: white;-fx-fill: white;");
        ipt.setMaxWidth(1000);
        portt.setMaxWidth(1000);
        menu.getChildren().addAll(ip,ipt,port,portt,number_of_players,start);
        
        start.setOnMouseClicked(e->{
            ((Game)(getParent())).startGame();
            GameInterface game = new GameInterface();
            
        });
    }

    private void Joining() {
        menu.getChildren().clear();
        menu.setSpacing(10);
        Text ip = new Text("IP");
        Text port = new Text("Port");
        Text join = new Text("Join");
        TextField ipt = new TextField();
        TextField portt = new TextField();
        ipt.setStyle("-fx-background-color: transparent;-fx-font: 60px EraserDust;-fx-fill: white;-fx-border-color: white;");
        portt.setStyle("-fx-background-color: transparent;-fx-font: 60px EraserDust;-fx-fill: white;-fx-border-color: white;");
        join.setStyle("-fx-background-color: transparent;-fx-font: 80px EraserDust;-fx-fill: white;-fx-border-color: white;");
        ip.setStyle("-fx-font: 60px EraserDust;-fx-stroke: white;-fx-fill: white;");
        port.setStyle("-fx-font: 60px EraserDust;-fx-stroke: white;-fx-fill: white;");
        ipt.setMaxWidth(1000);
        portt.setMaxWidth(1000);
        menu.getChildren().addAll(ip,ipt,port,portt,join);
    }

    
}
