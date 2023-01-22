import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Represents a single configuration in the skyscraper puzzle.
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class SkyscraperConfig implements Configuration {
    /** empty cell value */
    public final static int EMPTY = 0;

    /** empty cell value display */
    public final static char EMPTY_CELL = '.';

    private static int DIM;
    private static int[] north;
    private static int[] east;
    private static int[] south;
    private static int[] west;

    private int[][] grid;

    private int curserRow;
    private int curserCol;

    private int nextNum;

    /**
     * Constructor
     *
     * @param filename the filename
     *  <p>
     *  Read the board file.  It is organized as follows:
     *  DIM     # square DIMension of board (1-9)
     *  lookNS   # DIM values (1-DIM) left to right
     *  lookEW   # DIM values (1-DIM) top to bottom
     *  lookSN   # DIM values (1-DIM) left to right
     *  lookWE   # DIM values (1-DIM) top to bottom
     *  row 1 values    # 0 for empty, (1-DIM) otherwise
     *  row 2 values    # 0 for empty, (1-DIM) otherwise
     *  ...
     *
     *  @throws FileNotFoundException if file not found
     */
    SkyscraperConfig(String filename) throws FileNotFoundException {
        Scanner f = new Scanner(new File(filename));

        // Gets the dimension values in the file
        DIM = f.nextInt();

        // Gets the looking values for each coordinate direction using the integers in the file
        north = new int[DIM];
        for (int val=0; val<DIM; val++) {
            north[val] = f.nextInt();
        }
        east = new int[DIM];
        for (int val=0; val<DIM; val++) {
            east[val] = f.nextInt();
        }
        south = new int[DIM];
        for (int val=0; val<DIM; val++) {
            south[val] = f.nextInt();
        }
        west = new int[DIM];
        for (int val=0; val<DIM; val++) {
            west[val] = f.nextInt();
        }

        // Configures the grid using the dimension
        this.grid = new int[DIM][DIM];
        for (int r=0; r<DIM; r++) {
            for (int c=0; c<DIM; c++) {
                this.grid[r][c] = f.nextInt();
            }
        }

        // close the input file
        f.close();

        // If found a 1 on the perimeter fill in to the max value (DIM)
        // If found a max value (DIM) on the perimeter fill in to 1
        for (int i=0; i<DIM; i++) {

            // Found on north perimeter
            // Found 1
            if (north[i] == 1) {
                this.grid[0][i] = DIM;
            }
            // Found max value
            if (north[i] == DIM) {
                for (int n=0; n<DIM; n++) {
                    this.grid[n][i] = n+1;
                }
            }

            // Found on south perimeter
            // Found 1
            if (south[i] == 1) {
                this.grid[DIM-1][i] = DIM;
            }
            // Found max value
            if (south[i] == DIM) {
                for (int n=0; n<DIM; n++) {
                    this.grid[n][i] = DIM-n;
                }
            }

            // Found on west perimeter
            // Found 1
            if (west[i] == 1) {
                this.grid[i][0] = DIM;
            }
            // Found max value
            if (west[i] == DIM) {
                for (int n=0; n<DIM; n++) {
                    this.grid[i][n] = n+1;
                }
            }

            // Found on east perimeter
            // Found 1
            if (east[i] == 1) {
                this.grid[i][DIM-1] = DIM;
            }
            if (east[i] == DIM) {
                for (int n=0; n<DIM; n++) {
                    this.grid[i][n] = DIM-n;
                }
            }
        }

        // Start at 0,-1 on the grid
        this.curserRow = 0;
        this.curserCol = -1;
        this.nextNum = 0;
    }

    /**
     * Copy constructor
     *
     * @param parent SkyscraperConfig instance
     */
    public SkyscraperConfig(SkyscraperConfig parent, int nextNum) {

        // Gets the grid size and current nextNum
        this.grid = new int[DIM][DIM];
        this.nextNum = nextNum;

        // Sets all possible cell values
        for (int row=0; row<DIM; row++) {
            System.arraycopy(parent.grid[row], 0, this.grid[row], 0, DIM);
        }

        // Moving the curser
        this.curserRow = parent.curserRow;
        this.curserCol = parent.curserCol+1;
        // Finds if the curser reached the edge of the grid
        if (this.curserCol == DIM) {
            this.curserCol = 0;
            this.curserRow++;
        }

        // Sets the cell
        this.grid[curserRow][curserCol] = nextNum;

    }

    @Override
    public boolean isGoal() {

        // TODO

        return this.curserRow == DIM-1 && this.curserCol == DIM-1;
    }

    /**
     * getSuccessors
     *
     * @returns Collection of Configurations
     */
    @Override
    public Collection<Configuration> getSuccessors() {

        // Creates successor list
        List<Configuration> successors = new LinkedList<>();

        // Checks the next cell if it is
        int nextRow = this.curserRow;
        int nextCol = this.curserCol+1;
        if (nextCol == DIM) {
            nextCol = 0;
            nextRow++;
        }
        if (this.grid[nextRow][nextCol] != EMPTY) {

            // The next cell is filled already, so make a successor with the value already in the cell
            successors.add(new SkyscraperConfig(this, this.grid[nextRow][nextCol]));

        } else {

            // The next cell is empty, so try every combo
            // Creates the successors with each possible cell number
            for (int nextNum=1; nextNum<=DIM; nextNum++) {
                successors.add(new SkyscraperConfig(this, nextNum));
            }
        }

        return successors;
    }

    /**
     * isValid() - checks if current config is valid
     *
     * @returns true if config is valid, false otherwise
     */
    @Override
    public boolean isValid() {

        // TODO

        // Checks to see if the nextNum is duplicated in the current row and column
        for (int row = this.curserRow-1; row >= 0; row--) {
            if (this.grid[row][this.curserCol] == this.nextNum) {
                return false;
            }
        }
        for (int col = this.curserCol-1; col >= 0; col--) {
            if (this.grid[this.curserRow][col] == this.nextNum) {
                return false;
            }
        }

        /**
         * This is the logic for the following directional checks
         * 1. It looks to see if the respective column or row is populated
         * 2. Starting from the north/south/east/west it has a counter that compares largestBuildingHeight with currentBuildingHeight
         * 3. If found a building that is larger than the largestBuildingHeight stored, that current building will become the largest building
         *    and a sight-line counter will increase by 1
         * 4. The sight-line counter will be compared with the expected sight-line from each direction
         * 5. If the counter = expected direction sight-line, it will consider the successor valid
         */
        boolean colFull = true;
        for (int row=0; row<DIM; row++) {
            if (this.grid[row][this.curserCol] == 0) {
                colFull = false;
            }
        }
        if (colFull) {

            // Checks north
            int counter = 0;
            int largestBuildingHeight = 0;
            for (int row=0; row<DIM; row++) {
                int currentBuildingHeight = this.grid[row][this.curserCol];
                //System.out.println("currentBuildingHeight " + currentBuildingHeight);
                if (largestBuildingHeight < currentBuildingHeight) {
                    counter++;
                    largestBuildingHeight = currentBuildingHeight;
                }
                //System.out.println("counter " + counter);
            }
            if (north[this.curserCol] != counter) {
                return false;
            }

            // Checks south
            counter = 0;
            largestBuildingHeight = 0;
            for (int row = DIM - 1; row >= 0; row--) {
                int currentBuildingHeight = this.grid[row][this.curserCol];
                //System.out.println("currentBuildingHeight " + currentBuildingHeight);
                if (largestBuildingHeight < currentBuildingHeight) {
                    counter++;
                    largestBuildingHeight = currentBuildingHeight;
                }
                //System.out.println("counter " + counter);
            }
            if (south[this.curserCol] != counter) {
                return false;
            }
        }


        boolean rowFull = true;
        for (int col=0; col<DIM; col++) {
            if (this.grid[this.curserRow][col] == 0) {
                rowFull = false;
            }
        }
        if (rowFull) {

            // Checks west
            int counter = 0;
            int largestBuildingHeight = 0;
            for (int col = 0; col < DIM; col++) {
                int currentBuildingHeight = this.grid[this.curserRow][col];
                //System.out.println("currentBuildingHeight " + currentBuildingHeight);
                if (largestBuildingHeight < currentBuildingHeight) {
                    counter++;
                    largestBuildingHeight = currentBuildingHeight;
                }
                //System.out.println("counter " + counter);
            }
            if (west[this.curserRow] != counter) {
                return false;
            }

            // Checks east
            counter = 0;
            largestBuildingHeight = 0;
            for (int col = DIM - 1; col >= 0; col--) {
                int currentBuildingHeight = this.grid[this.curserRow][col];
                //System.out.println("currentBuildingHeight " + currentBuildingHeight);
                if (largestBuildingHeight < currentBuildingHeight) {
                    counter++;
                    largestBuildingHeight = currentBuildingHeight;
                }
                //System.out.println("counter " + counter);
            }
            if (east[this.curserRow] != counter) {
                return false;
            }
        }

        return true;
    }

    /**
     * toString() method
     *
     * @return String representing configuration board & grid w/ look values.
     * The format of the output for the problem solving initial config is:
     *
     *   1 2 4 2
     *   --------
     * 1|. . . .|3
     * 2|. . . .|3
     * 3|. . . .|1
     * 3|. . . .|2
     *   --------
     *   4 2 1 2
     */
    @Override
    public String toString() {

        // Gets the top looking row
        String print = "  ";
        for (int i : north) {
            print += i + " ";
        }
        // Adds dashes
        print += "\n  ";
        for (int dash=0; dash<DIM*2; dash++){
            print += "-";
        }
        // Gets the values from the grid mixed with the west and east looking values
        for (int row=0; row<DIM; row++) {
            print += "\n" + west[row] + "|";
            for (int col=0; col<DIM; col++) {
                if (this.grid[row][col] == EMPTY) {
                    print += EMPTY_CELL;
                } else {
                    print += this.grid[row][col];
                }

                if (col<DIM-1) {
                    print += " ";
                }
            }
            print += "|" + east[row];
        }
        // Adds dashes
        print += "\n  ";
        for (int dash=0; dash<DIM*2; dash++){
            print += "-";
        }
        // Gets the bottom looking row
        print += "\n  ";
        for (int i : south) {
            print += i + " ";
        }

        return print;
    }
}
