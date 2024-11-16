package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MapParserTest {

    @Mock
    private BoardFactory boardFactory;

    @Mock
    private LevelFactory levelFactory;

    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);

        MapParser mapParser = new MapParser(levelFactory, boardFactory);

        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        mapParser.parseMap(map);

        // Verify createGhost() is called exactly once
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
        Mockito.verify(boardFactory, Mockito.times(12)).createGround(); // ground tiles
        Mockito.verify(boardFactory, Mockito.times(24)).createWall(); // walls

    }

    @Test
    public void testParseMapWrong1() {
        // Expect an IllegalArgumentException to be thrown
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MockitoAnnotations.openMocks(this); // Initialize mocks
            assertNotNull(boardFactory);
            assertNotNull(levelFactory);
    
            MapParser mapParser = new MapParser(levelFactory, boardFactory);
    
            ArrayList<String> map = new ArrayList<>();
            /*
             Create a map with inconsistent size between each row or
             containing invalid characters.
            */
            map.add("##########");
            map.add("#P  G   $"); // Invalid character `$`
            map.add("#####");      // Inconsistent size
    
            // Attempt to parse the bad map
            mapParser.parseMap(map);
        });
    
        // Assert that the exception message matches the expected result
        Assertions.assertEquals("Invalid map input", thrown.getMessage());
    }
}
