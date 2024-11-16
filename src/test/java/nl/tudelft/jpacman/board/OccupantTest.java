package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        // Remove the following placeholder:
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        // Remove the following placeholder:
        Square Z_square = new BasicSquare();

        // Occupy the square
        unit.occupy(Z_square);

        // Assert that the unit is occupying the correct square
        assertThat(unit.getSquare()).isEqualTo(Z_square);
        assertThat(Z_square.getOccupants()).contains(unit);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        // Remove the following placeholder:
        Square XSquare = new BasicSquare();
        Square YSquare = new BasicSquare();

        // Occupy the first square
        unit.occupy(XSquare);
        assertThat(unit.getSquare()).isEqualTo(XSquare);

        // Reoccupy with a different square
        unit.occupy(YSquare);

        // Assert that the unit now occupies the second square
        assertThat(unit.getSquare()).isEqualTo(YSquare);
        assertThat(YSquare.getOccupants()).contains(unit);
        assertThat(XSquare.getOccupants()).doesNotContain(unit);
    }
}
