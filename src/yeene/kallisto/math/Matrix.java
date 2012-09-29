package yeene.kallisto.math;

import yeene.kallisto.Constants;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

/**
 * primitive matrix calculation functions.
 *
 * @author yeene
 */
public class Matrix {

  final BigDecimal data[][];

  public Matrix() {
    data = new BigDecimal[][] {{ ONE, ZERO, ZERO}, { ZERO, ONE, ZERO}, { ZERO, ZERO, ONE }};
  }

  public Matrix(BigDecimal[][] data) {
    this.data = data;
    for(int i=0;i<3;i++) {
      for(int j=0;j<3;j++) {
        data[i][j] = data[i][j].setScale(Constants.PRECISION, BigDecimal.ROUND_HALF_UP);
      }
    }
  }

  /**
   * multiplies the matrix with a vector returning a vector.
   * @param v the vector to multiply the matrix with
   * @return another vector
   */
  public Vector multiply(final Vector v) {
    return new Vector(
       data[0][0].multiply(v.getX()).add(data[0][1].multiply(v.getY())).add(data[0][2].multiply(v.getZ())),
       data[1][0].multiply(v.getX()).add(data[1][1].multiply(v.getY())).add(data[1][2].multiply(v.getZ())),
       data[2][0].multiply(v.getX()).add(data[2][1].multiply(v.getY())).add(data[2][2].multiply(v.getZ()))
    );
  }

  /**
   * multiplies the matrix with another matrix.
   * @param m the other matrix.
   * @return matrix with this * m
   */
  public Matrix multiply(final Matrix m) {
    final BigDecimal[][] result = new BigDecimal[][] { {ZERO, ZERO, ZERO}, {ZERO, ZERO, ZERO}, {ZERO, ZERO, ZERO} };

    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        BigDecimal xyResult = BigDecimal.ZERO;

        for(int k=0;k<3;k++) {
          xyResult = xyResult.add(data[i][k].multiply(m.data[k][j]));
        }

        result[i][j] = xyResult;
      }
    }

    return new Matrix(result);
  }
}
