package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {

    @Mock
    private BoardFactory boardFactory;

    @Mock
    private LevelFactory levelFactory;

    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (valid map).
     */
    @Test
    public void testParseMapGood() {
        // Ensure mocked objects are not null
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);

        // Mock behavior for creating a ghost
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);

        // Initialize the MapParser
        MapParser mapParser = new MapParser(levelFactory, boardFactory);

        // Create a valid map
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        // Parse the map
        mapParser.parseMap(map);

        // Verify expected interactions
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
        Mockito.verify(boardFactory, Mockito.times(12)).createGround(); // Count for ground tiles
        Mockito.verify(boardFactory, Mockito.times(24)).createWall();   // Count for wall tiles
    }

    /**
     * Test for the parseMap method (invalid map).
     */
    @Test
    public void testParseMapWrong1() {
        // Expect an IllegalArgumentException to be thrown
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Ensure mocked objects are not null
            assertNotNull(boardFactory);
            assertNotNull(levelFactory);

            // Initialize the MapParser
            MapParser mapParser = new MapParser(levelFactory, boardFactory);

            // Create an invalid map
            ArrayList<String> map = new ArrayList<>();
            map.add("##########");  // Valid row
            map.add("#P  G   $");   // Invalid row (contains `$`)
            map.add("#####");       // Invalid row (inconsistent size)

            // Attempt to parse the invalid map
            mapParser.parseMap(map);
        });

        // Verify the exception message matches the expected result
        Assertions.assertEquals("Invalid map input", thrown.getMessage());
    }
}
