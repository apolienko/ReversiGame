package desktopgames.reversi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

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
        controller.playingField.setOnMousePressed((e) -> controller.nextMove());

        controller.blackText.setFont(Font.font("Arial", 20));
        controller.blackScoreText.setFont(Font.font("Arial", 20));
        controller.whiteText.setFont(Font.font("Arial", 20));
        controller.whiteScoreText.setFont(Font.font("Arial", 20));
        controller.whoMove.setFont(Font.font("Arial",20));

        controller.newGameButton.setLayoutX(540);
        controller.newGameButton.setLayoutY(250);
        controller.newGameButton.setOnMouseClicked((e) -> controller.newGameStart());

        controller.whoWinRect.setX(234);
        controller.whoWinRect.setY(234);
        controller.whoWinRect.setWidth(200);
        controller.whoWinRect.setHeight(50);
        controller.whoWinRect.setFill(Color.ORANGERED);
        controller.whoWinText.setFont(Font.font(null, 20));
        controller.whoWinRect.setStroke(Color.BLACK);
        controller.whoWinRect.setStrokeWidth(2.0);

        final Group root = new Group(
                controller.whiteText,
                controller.whiteScoreText, controller.newGameButton,
                controller.blackText, controller.blackScoreText,
                controller.playingField, controller.whoMove,
                controller.whoWinRect, controller.whoWinText
        );

        final Scene scene = new Scene(root, Color.LIGHTGREEN);
        stage.setScene(scene);

        controller.fillTheField();
        controller.newGameStart();

        stage.show();

    }
}