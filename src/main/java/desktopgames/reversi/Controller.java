package desktopgames.reversi;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;


public class Controller {
    final private Logic logicModel = new Logic();

    final private static Canvas[][] arrayOfCells = new Canvas[8][8];

    final Text blackText = new Text(540, 200, "BLACK: ");
    final Text whiteText = new Text(540, 355, "WHITE: ");
    final Text blackScoreText = new Text(610, 200, "");
    final Text whiteScoreText = new Text(610, 355, "");
    final Text whoMove = new Text(250, 30, "");

    final Rectangle whoWinRect = new Rectangle();
    final Text whoWinText = new Text(282, 265, "");

    final GridPane playingField = new GridPane();

    final Button newGameButton = new Button("New Game");


    public void fillTheField() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                arrayOfCells[i][j] = new Canvas(60, 60);
                playingField.add(arrayOfCells[i][j], j, i);
            }
    }

    public void newGameStart() {
        logicModel.initialize();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                repaintCell(i, j, logicModel.getValueFromArray(i, j));

        logicModel.findPlaceablePositions();
        for (Pair<Integer, Integer> p : logicModel.getPlaceablePositions())
            repaintCell(p.getKey(), p.getValue(), 3);

        whiteScoreText.setText(String.valueOf(logicModel.getWhiteScore()));
        blackScoreText.setText(String.valueOf(logicModel.getBlackScore()));

        whoWinRect.setVisible(false);
        whoWinText.setVisible(false);

        whoMove.setText("Move: Black");
    }


    private static void repaintCell(int i, int j, int style) {
        GraphicsContext cells = arrayOfCells[i][j].getGraphicsContext2D();

        cells.clearRect(0, 0, 60, 60);

        cells.setStroke(Color.BLACK);
        cells.setLineWidth(1.0);
        cells.strokeRect(0, 0, 60, 60);

        if (style == 1) {
            cells.setFill(Color.BLACK);
            cells.fillOval(5, 5, 50,50);

        }

        if (style == 2) {
            cells.setFill(Color.WHITE);
            cells.fillOval(5, 5, 50,50);
            cells.setStroke(Color.BLACK);
            cells.strokeOval(5, 5, 51,51);
        }

        if (style == 3) {
            cells.setStroke(Color.GREEN);
            cells.setLineWidth(2.0);
            cells.strokeOval(6, 6, 48,48);
        }

    }

    public void nextMove() {
        playingField.getChildren()
                .forEach(grid ->
                        grid.setOnMouseReleased((e) -> {
                            if (e.getClickCount() == 1) {
                                Node source = (javafx.scene.Node) e.getSource();

                                int i = GridPane.getRowIndex(source);
                                int j = GridPane.getColumnIndex(source);

                                boolean curFlag = false;
                                for (Pair<Integer, Integer> p : logicModel.getPlaceablePositions())
                                    if (p.getKey() == i && p.getValue() == j) {
                                        curFlag = true;
                                        break;
                                    }

                                if (curFlag) {

                                    for (Pair<Integer, Integer> p : logicModel.getPlaceablePositions())
                                        repaintCell(p.getKey(), p.getValue(), 0);


                                    logicModel.findCorrectLine(i, j, true);

                                    for (Pair<Integer, Integer> p : logicModel.getRepaintCell())
                                        repaintCell(p.getKey(), p.getValue(),
                                                logicModel.getValueFromArray(p.getKey(), p.getValue()));

                                    logicModel.findPlaceablePositions();
                                    if (logicModel.getPlaceablePositions().isEmpty())
                                        finishGame();
                                    else
                                        giveControlAnotherPlayer();
                                }
                            }
                        }));
    }

    private void giveControlAnotherPlayer() {
        for (Pair<Integer, Integer> p : logicModel.getPlaceablePositions())
            repaintCell(p.getKey(), p.getValue(), 3);
        if (logicModel.getMoveFlag())
            whoMove.setText("Move: Black");
        else
            whoMove.setText("Move: White");

        whiteScoreText.setText(String.valueOf(logicModel.getWhiteScore()));
        blackScoreText.setText(String.valueOf(logicModel.getBlackScore()));

    }

    private void finishGame() {
        whoWinRect.setVisible(true);
        whoWinText.setVisible(true);

        byte black = logicModel.getBlackScore();
        byte white = logicModel.getWhiteScore();

        whiteScoreText.setText(String.valueOf(white));
        blackScoreText.setText(String.valueOf(black));

        if (black > white)
            whoWinText.setText("Black Wins!");
        else if (white > black)
            whoWinText.setText("White Wins!");
        else
            whoWinText.setText("Drawn Game!");
    }

}



