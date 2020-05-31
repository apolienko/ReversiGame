import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class Controller {
    final private Logic logicModel = new Logic();

    final private static Canvas[][] arrayOfCells = new Canvas[8][8];

    final GridPane playingField = new GridPane();

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

    }


}
