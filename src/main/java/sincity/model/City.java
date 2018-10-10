package sincity.model;

public class City {
    private RoadPuzzle[][] puzzleBoard;
    private int padding;
    private double tileSize;
    public City(int verticalPuzzles, int horizontalPuzzles, int padding, double tileSize) {
        puzzleBoard = new RoadPuzzle[horizontalPuzzles + padding * 2][verticalPuzzles + padding * 2];
        this.padding = padding;
        this.tileSize = tileSize;
        initializeBoard();
    }

    RoadPuzzle[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public RoadType getRoadType(int x, int y) {
        return puzzleBoard[x][y].getRoadType();
    }

    private void initializeBoard() {
        for (int x = 0; x < puzzleBoard.length; x++) {
            for (int y = 0; y < puzzleBoard[x].length; y++) {
                // test intersection
                if (y == 3 && x == 4) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENW);
                } else if ((y == 3 && x == 6)) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENSW);
                } else if (y == 3) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.EW);
                } else if ((x == 4 && y <= 3) || x == 6) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.NS);
                } else {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.BCG);
                }
            }
        }
    }
}
