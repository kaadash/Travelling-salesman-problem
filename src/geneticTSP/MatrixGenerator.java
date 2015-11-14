package geneticTSP;

/**
 * Created by kadash on 17.10.15.
 */
public class MatrixGenerator {
    private int size;
    private int[][] adjacentMatrix;

    public MatrixGenerator(int size) {
        this.size = size;
        this.adjacentMatrix = new int[size][size];
    }

    public int[][] generate() {
        int size = this.size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    this.adjacentMatrix[i][i] = 0;
                } else if (j > i) {
                    int random = (int )(Math.random() * 50 + 1);
                    this.adjacentMatrix[i][j] = random;
                } else {
                    this.adjacentMatrix[i][j] = this.adjacentMatrix[j][i];
                }
            }
        }
        return this.adjacentMatrix;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }

    public void setAdjacentMatrix(int[][] adjacentMatrix) {
        this.adjacentMatrix = adjacentMatrix;
    }
}
