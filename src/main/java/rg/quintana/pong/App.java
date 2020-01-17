package rg.quintana.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
    
     final short SCENE_HEIGHT = 480;
     final short SCENE_WIDTH = 640;
    
    int ballCenterX = 0;
    int ballCurrentSpeedX = 5;
    int ballDirectionX = 1;
    int ballCenterY = 0;
    int ballCurrentSpeedY = 5;
    int ballDirectionY = 1;
    
    short stickHeight = 50;
    short stickPosY = (short) ((SCENE_HEIGHT - stickHeight)/2);
    byte stickCurrentSpeed = 2;
    byte stickDirection = 0;
    @Override
    public void start(Stage stage) {
        
      
        
        
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT );
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        HBox paneScores = new HBox ();
        paneScores.setTranslateY (20);
        paneScores.setMinWidth (SCENE_WIDTH );
        paneScores.setAlignment (SCENE_WIDTH/2, i, SCENE_WIDTH/2, i + 10 );
        
        
        
        
        
        
        
        
        for (int i = 0; i<SCENE_HEIGHT; i += 30) {
        Line line = new Line (SCENE_WIDTH/2, i, SCENE_WIDTH/2, i + 10);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth (4);
        root.getChildren().add(line);
        }
    
        
        Circle circleBall = new Circle();
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        
        root.getChildren().add(circleBall);
        
        
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(stickHeight);
        rectStick.setX(SCENE_WIDTH - 40);
        rectStick.setY(stickPosY);
        root.getChildren().add(rectStick);
        rectStick.setFill(Color.WHITE);
        
        
        scene.setOnKeyPressed(new EventHandler <KeyEvent> (){
            public void handle (final KeyEvent keyEvent){
                switch (keyEvent.getCode()) {
                    case UP:
                        stickDirection = -1;
                    break;
                    case DOWN:
                        stickDirection = 1;
                    break;  
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
                    rectStick.setY(stickPosY);
                    stickPosY += stickCurrentSpeed * stickDirection;
                    if (stickPosY <= 0 ||stickPosY >= SCENE_HEIGHT-stickHeight) {
                        stickDirection = 0;    
                    }
                    if (stickPosY <= 0) {
                        stickDirection = 0;    
                        stickPosY = 0;
                
                    }else if (stickPosY >= SCENE_HEIGHT-stickHeight){
                        stickDirection = 0;    
                        stickPosY = (short)(SCENE_HEIGHT- stickHeight) ;
                    }
                    Shape shapeCollision = Shape.intersect(circleBall, rectStick);
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false) {
                        System.out.println("Ha colisionado");
                        ballDirectionX = -1;
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
