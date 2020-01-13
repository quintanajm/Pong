package rg.quintana.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    
    int ballCenterX = 10;
    int ballCurrentSpeedX = 5;
    int ballDirectionX = 1;
   
    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        var scene = new Scene(root, 640, 480);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle();
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        root.getChildren().add(circleBall);
        circleBall.setFill(Color.WHITE);
        
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX +=ballCurrentSpeedX * ballDirectionX;
                    if(ballCenterX>= 640){
                        ballDirectionX=-1;
                    } else if (ballCenterX<=0){
                        ballDirectionX= +1;
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
