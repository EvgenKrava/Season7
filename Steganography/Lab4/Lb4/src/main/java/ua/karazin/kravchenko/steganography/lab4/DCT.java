package ua.karazin.kravchenko.steganography.lab4;

public class DCT {

    // block size
    protected int N;
    // coefficients
    protected double[] c;
    // zig zag matrix
    protected int[][] zigzag;

    public DCT(int n) {
        this.N = n;
        initCoefficients();
        zigzag = makeZigZagMatrix();
    }

    /* initialize coefficient matrix */
    protected void initCoefficients() {
        c = new double[N];
        c[0] = 1 / Math.sqrt(2);
        for (int i = 1; i < N; i++) {
            c[i] = 1;
        }


    }

    protected double[][] forwardDCT(double[][] input) {
        double[][] output = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double sum = 0d;
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        sum += input[x][y] * Math.cos(((2 * x + 1) / (2.0 * N)) * i * Math.PI) * Math.cos(((2 * y + 1) / (2.0 * N)) * j * Math.PI);
                    }
                }
                sum *= c[i] * c[j] / Math.sqrt(2 * N);
                output[i][j] = sum;
            }
        }
        return output;
    }

    protected double[][] inverseDCT(double[][] input) {
        double[][] output = new double[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                double sum = 0d;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        sum += c[i] * c[j] * input[i][j] * Math.cos(((2 * x + 1) / (2.0 * N)) * i * Math.PI) * Math.cos(((2 * y + 1) / (2.0 * N)) * j * Math.PI);
                    }
                }
                sum /= Math.sqrt(2 * N);
                output[x][y] = sum;
            }
        }
        return output;
    }

    /* write dct coefficient matrix into 1D array in zig zag order */
    public double[] zigZag(double[][] m) {
        double[] zz = new double[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) zz[zigzag[i][j]] = m[i][j];
        }
        return zz;
    }

    /* write zig zag ordered coefficients into matrix */
    public double[][] unZigZag(double[] zz) {
        double[][] m = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                m[i][j] = zz[zigzag[i][j]];
            }
        }
        return m;
    }

    /* generate zig zag matrix */
    private int[][] makeZigZagMatrix() {
        int[][] zz = new int[N][N];
        int zval = 0;
        int zval2 = N * (N - 1) / 2;
        int i, j;
        for (int k = 0; k < N; k++) {
            if (k % 2 == 0) {
                i = 0;
                j = k;
                while (j > -1) {
                    zz[i][j] = zval;
                    zval++;
                    i++;
                    j--;
                }
                i = N - 1;
                j = k;
                while (j < N) {
                    zz[i][j] = zval2;
                    zval2++;
                    i--;
                    j++;
                }
            } else {
                i = k;
                j = 0;
                while (i > -1) {
                    zz[i][j] = zval;
                    zval++;
                    j++;
                    i--;
                }
                i = k;
                j = N - 1;
                while (i < N) {
                    zz[i][j] = zval2;
                    zval2++;
                    i++;
                    j--;
                }
            }
        }
        return zz;
    }

}