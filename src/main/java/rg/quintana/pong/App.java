package rg.quintana.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    
    int ballCenterX = 0;
    int ballCurrentSpeedX = 5;
    int ballDirectionX = 1;
    int ballCenterY = 0;
    int ballCurrentSpeedY = 5;
    int ballDirectionY = 1;
    @Override
    public void start(Stage stage) {
        
       final short SCENE_HEIGHT = 480;
       final short SCENE_WIDTH = 640;
        
        
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT );
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle();
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        
        root.getChildren().add(circleBall);
        
        short rectHeight = 50;
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(rectHeight);
        rectStick.setX(SCENE_WIDTH - 40);
        rectStick.setY((SCENE_HEIGHT - rectHeight)/2);
        root.getChildren().add(rectStick);
        rectStick.setFill(Color.WHITE);
        
        scene.setOnKeyPressed(new EventHandler <KeyEvent> (){
            public void handle (final KeyEvent keyEvent){
                switch (keyEvent.getCode())
                    case UP;
                    break;
                    
               
                    
                    
                    
                if (keyEvent.getCode() == KeyCode.UP){
                    System.out.println("arriba");
                }
            }
            
        });

        
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    circleBall.setCenterX(ballCenterX);
                    circleBall.setCenterY(ballCenterY);
                    ballCenterX +=ballCurrentSpeedX * ballDirectionX;
                    ballCenterY +=ballCurrentSpeedY * ballDirectionY;
                    
                    if(ballCenterX>= 640){
                        ballDirectionX=-1;
                    } else if (ballCenterX<=0){
                        ballDirectionX= +1;
                    }
                        if(ballCenterY>= 480){
                        ballDirectionY=-1;
                    } else if (ballCenterY<=0){
                        ballDirectionY= +1;
                    }
                    
                    
                }
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();        
        
    }

    public static void main(String[] args) {
        launch();
    }

}
