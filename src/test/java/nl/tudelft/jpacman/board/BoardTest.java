package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    /**
     * Test to create a 1x1 board and check if it is set up correctly.
     */
    @Test
    void createBoard() {
        // Create a 1x1 grid with a BasicSquare.
        Square s = new BasicSquare();
        Square[][] grid = {{s}};
        Board board = new Board(grid);

        // Assert that (0, 0) is within the board's boundaries.
        assertThat(board.withinBorders(0, 0)).isTrue();

        // Assert that the square at (0, 0) is the one we created.
        assertThat(board.squareAt(0, 0)).isEqualTo(s);

        // Assert that out-of-bounds coordinates are handled correctly.
        assertThat(board.withinBorders(-1, 0)).isFalse();  // Out of bounds: negative index
        assertThat(board.withinBorders(0, -1)).isFalse();  // Out of bounds: negative index
        assertThat(board.withinBorders(1, 0)).isFalse();   // Out of bounds: greater than grid size
        assertThat(board.withinBorders(0, 1)).isFalse();   // Out of bounds: greater than grid size
    }
    @Test
    public void createNullBoardTest() {
        BasicSquare[][] bs = new BasicSquare[1][1];
        bs[0][0] = null;
        Board b = new Board(bs);
        assertThat(b.invariant()).isFalse();
        assertThat(b.squareAt(0,0).invariant()).isTrue();

    }

}
