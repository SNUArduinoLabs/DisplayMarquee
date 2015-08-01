import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
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

import java.util.List;

public class DisplayMarquee extends Application {

    public static void main(String[] args){
        launch(args);
    }

    /***
     * Entry point for JavaFX application
     *
     * @param stage primary stage on which the scene will be set
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Rectangle2D stageBounds = Screen.getPrimary().getVisualBounds();
        // Characteristics
        final double marqueeWidth = stageBounds.getMaxX();
        final double marqueeHeight = stageBounds.getMaxY() * .08;
        final double ocapacity = 0.65;
        final double fontSize = marqueeHeight - (marqueeHeight * .15);
        final String fontFamily = "Thaoma";

        // Set up TextFlow with given font family and size
        TextFlow textFlow = new TextFlow();
        Font font = new Font(fontFamily, fontSize);

        // Set up marquee text
        // TODO: Somehow make dynamically update it with other text
        List list_parameters = getParameters().getUnnamed();
        StringBuilder strbld = new StringBuilder();
        for (Object list_parameter : list_parameters) {
            strbld.append(list_parameter);
            strbld.append(new String(new char[20]).replace('\0', ' '));
        }

        String parameters = strbld.toString();
        Text marquee_text = new Text(parameters);

        marquee_text.setFill(Color.YELLOW);
        marquee_text.setFont(font);
        textFlow.getChildren().addAll(marquee_text);
        //textFlow.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        //textFlow.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        Group group = new Group(textFlow);

        Scene scene = new Scene(group, marqueeWidth, marqueeHeight, Color.GREY);
        scene.setCursor(Cursor.NONE);

        // Set stage as a windows without borders nor buttons;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setOpacity(ocapacity);
        stage.setFullScreen(false);

        stage.setX(stageBounds.getMinX() + stageBounds.getWidth() - marqueeWidth);
        stage.setY(stageBounds.getMinY() + stageBounds.getHeight() - marqueeHeight);

        stage.setScene(scene);
        stage.show();

        // Animation
        double sceneWidth = scene.getWidth();
        double textWidth = textFlow.getLayoutBounds().getWidth();

        // Create initial and final keys
        KeyValue initkv = new KeyValue(textFlow.translateXProperty(), sceneWidth);
        KeyFrame initkf = new KeyFrame(Duration.ZERO, initkv);

        KeyValue endkv = new KeyValue(textFlow.translateXProperty(), -1.0 * textWidth);
        KeyFrame endkf = new KeyFrame(Duration.seconds(parameters.length() * 0.18), endkv);

        Timeline timeline = new Timeline(initkf, endkf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}