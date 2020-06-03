import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {
    public static void main(String[] args) { Application.launch(args); }

    @Override
    public void start(Stage stage) {
        final Controller controller = new Controller();

        stage.setHeight(610);
        stage.setWidth(670);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Reversi");

        controller.playingField.setLayoutX(50);
        controller.playingField.setLayoutY(50);
        controller.playingField.setGridLinesVisible(true);

        controller.blackText.setFont(Font.font("Arial", 20));
        controller.blackScoreText.setFont(Font.font("Arial", 20));
        controller.whiteText.setFont(Font.font("Arial", 20));
        controller.whiteScoreText.setFont(Font.font("Arial", 20));
        controller.whoMove.setFont(Font.font("Arial",20));

        final Group root = new Group(
                controller.whiteText, controller.whiteScoreText,
                controller.blackText, controller.blackScoreText,
                controller.playingField, controller.whoMove
        );
        final Scene scene = new Scene(root, Color.LIGHTGREEN);
        stage.setScene(scene);

        controller.fillTheField();
        controller.newGameStart();

        stage.show();

        controller.playingField.setOnMousePressed((e) -> controller.nextMove());

    }
}