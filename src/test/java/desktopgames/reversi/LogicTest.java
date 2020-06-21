package desktopgames.reversi;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

public class LogicTest {

    private Logic logicModel = new Logic();

    @Test
    public void getters()  {

        logicModel = new Logic();
        logicModel.findCorrectLine(3, 2, true);

        List<Pair<Integer, Integer>> trueValue = new ArrayList<>();
        trueValue.add(new Pair(3, 2));
        trueValue.add(new Pair(3, 3));

        assertFalse(logicModel.getMoveFlag());
        assertEquals(trueValue, logicModel.getRepaintCell());
        assertEquals(4, logicModel.getBlackScore());
        assertEquals(1, logicModel.getWhiteScore());
    }

    @Test
    public void findPlaceablePositions() {

        logicModel = new Logic();
        logicModel.findPlaceablePositions();

        Set<Pair<Integer, Integer>> trueValue = new HashSet<>();
        trueValue.add(new Pair(3, 2));
        trueValue.add(new Pair(2, 3));
        trueValue.add(new Pair(5, 4));
        trueValue.add(new Pair(4, 5));

        assertEquals(trueValue, logicModel.getPlaceablePositions());

    }

    @Test
    public void findCorrectLine() {

        logicModel = new Logic();
        assertTrue(logicModel.findCorrectLine(3, 2, false));

        logicModel = new Logic();
        assertFalse(logicModel.findCorrectLine(0, 0, false));

    }

}