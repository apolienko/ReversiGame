import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;


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


}
