package simulator;

import java.io.IOException;
import java.util.List;

import calculator.MatrixCalculator;
import model.Complex;
import model.ComplexMatrix;
import model.ComplexVector;
import web.HttpConnection;

/**
 * BasicQuantum
 */
public class BasicQuantum {

    public static Complex variance(ComplexMatrix mat1, ComplexVector ket) {
        if (!MatrixCalculator.isHermitian(mat1)) {
            System.out.println("La matriz no es Hermitian");
            return null;
        } else {
            ComplexMatrix meanComplex = MatrixCalculator.scalarMultiplication(mean(mat1, ket),
                    identityMatrix(mat1.getN(), mat1.getM()));
            ComplexMatrix deltaObs = MatrixCalculator.matrixSum(mat1, MatrixCalculator.negation(meanComplex));
            ComplexMatrix deltaObs2 = MatrixCalculator.matrixMultiplication(deltaObs, deltaObs);
            Complex varianza = MatrixCalculator.innerProduct(ket, MatrixCalculator.actionOverVector(deltaObs2, ket));
            return varianza;

        }

    }

    public static Complex mean(ComplexMatrix mat1, ComplexVector ket) {
        if (!MatrixCalculator.isHermitian(mat1)) {
            System.out.println("La matriz no es Hermitian");
        }
        // System.out.println(ket.toString());
        ComplexVector accion = MatrixCalculator.actionOverVector(mat1, ket);
        Complex media = MatrixCalculator.innerProduct(accion, ket);
        return media;

    }

    private static ComplexMatrix identityMatrix(int n, int m) {
        ComplexMatrix identity = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    identity.addToMatrix(i, j, new Complex(1, 0));
                } else {
                    identity.addToMatrix(i, j, new Complex(0, 0));
                }
            }
        }
        return identity;
    }

    public static ComplexVector dinamica(int times, ComplexVector ket, ComplexMatrix sequence) {
        ComplexVector finalState = ket;
        for (int i = 0; i < times; i++) {
            finalState = MatrixCalculator.actionOverVector(sequence, finalState);
        }
        return finalState;
    }

    public static ComplexVector valProp(ComplexMatrix mat1) throws IOException {
        HttpConnection httpConnection = new HttpConnection();
        List<Double> response = httpConnection.getResponse(stringMatriz(mat1));
        ComplexVector valProps = new ComplexVector(response.size());
        for (int i = 0; i < response.size(); i++) {
            valProps.addToMatrix(i, new Complex(response.get(i), 0));
        }
        return valProps;
    }

    public static String stringMatriz(ComplexMatrix mat1) {
        String matrizString = "{";
        for (int i = 0; i < mat1.getN(); i++) {
            matrizString += "{";
            for (int j = 0; j < mat1.getM(); j++) {
                if (j + 1 == mat1.getM()) {
                    if (mat1.getElement(i, j).getpReal() == 0) {
                        matrizString += mat1.getElement(i, j).getpImg() + "i";
                    } else if (mat1.getElement(i, j).getpImg() == 0) {
                        matrizString += mat1.getElement(i, j).getpReal();
                    } else {
                        matrizString += mat1.getElement(i, j).getpReal() + "" + mat1.getElement(i, j).getpImg() + "i";
                    }
                } else {
                    if (mat1.getElement(i, j).getpReal() == 0) {
                        matrizString += mat1.getElement(i, j).getpImg() + "i,";
                    } else if (mat1.getElement(i, j).getpImg() == 0) {
                        matrizString += mat1.getElement(i, j).getpReal() + ",";
                    } else {
                        matrizString += mat1.getElement(i, j).getpReal() + "" + mat1.getElement(i, j).getpImg() + "i,";
                    }
                }
            }
            if (i + 1 == mat1.getN()) {
                matrizString += "}";
            } else {
                matrizString += "},";
            }
        }
        matrizString += "}";
        return matrizString;
    }
}