import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DisplayMarquee extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Characteristics
        final double width  = Screen.getPrimary().getBounds().getMaxX();
        final double height = Screen.getPrimary().getBounds().getMaxY() * .08;
        final double size   = height - (height * .15);
        final String family = "Thaoma";

        TextFlow textFlow = new TextFlow();
        Font font = new Font(family, size);

        Text text1 = new Text("원숭이도 나무에서 떨어진다 (Even monkeys fall from trees.)     " +
                "The devil hides himself in details.     "  +
                "Hey! Que pasa :)");

        text1.setFill(Color.DARKBLUE);
        text1.setFont(font);
        textFlow.getChildren().addAll(text1);
        textFlow.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        Group group = new Group(textFlow);
        Scene scene = new Scene(group, width, height, Color.LIGHTGRAY);
        scene.setCursor(Cursor.NONE);

        // Set stage as a windows without borders nor buttons
        stage.initStyle(StageStyle.TRANSPARENT);

        //stage.alwaysOnTopProperty();
        stage.setScene(scene);
        stage.show();

        // Animation
        double sceneWidth = scene.getWidth();
        double textWidth = textFlow.getLayoutBounds().getWidth();

        // Create initial and final keys
        KeyValue initkv = new KeyValue(textFlow.translateXProperty(), sceneWidth);
        KeyFrame initkf = new KeyFrame(Duration.ZERO, initkv);

        KeyValue endkv = new KeyValue(textFlow.translateXProperty(), -1.0 * textWidth);
        KeyFrame endkf = new KeyFrame(Duration.seconds(textWidth / 180), endkv);

        Timeline timeline = new Timeline(initkf, endkf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}