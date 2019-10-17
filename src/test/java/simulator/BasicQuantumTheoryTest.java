package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.Complex;
import model.ComplexMatrix;
import model.ComplexVector;

public class BasicQuantumTheoryTest {

	@Test
	void likeliHoodTest() {

		ComplexVector vector = new ComplexVector(4);

		// mat1
		Complex c1 = new Complex(-3, -1);
		Complex c2 = new Complex(0, -2);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(2, 0);

		// add

		vector.addToMatrix(0, c1);
		vector.addToMatrix(1, c2);
		vector.addToMatrix(2, c3);
		vector.addToMatrix(3, c4);

		QuantumStatesSystem system = new QuantumStatesSystem(vector);

		double answer = system.likeliHood(2);

		double answerExpected = 0.47897362544357464;

		assertEquals(answerExpected, answer);

	}

	@Test
	void transitionAmplitudesTest() {

		ComplexVector vector = new ComplexVector(2);
		ComplexVector vector2 = new ComplexVector(2);

		// mat1
		Complex c1 = new Complex(1, 0);
		Complex c2 = new Complex(0, -1);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(1, 0);

		// add

		vector.addToMatrix(0, c1);
		vector.addToMatrix(1, c2);
		vector2.addToMatrix(0, c3);
		vector2.addToMatrix(1, c4);

		QuantumStatesSystem system = new QuantumStatesSystem(vector);

		Complex answer = system.transitionAmplitudes(vector2);

		Complex answerExpected = new Complex(0, -2);

		assertEquals(answerExpected, answer);

	}

	@Test
	public void obsTest() {
		ComplexMatrix mat1 = new ComplexMatrix(2, 2);
		ComplexVector ket2 = new ComplexVector(2);

		Complex c1 = new Complex(1, 0);
		Complex c2 = new Complex(0, -1);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(2, 0);
		Complex c5 = new Complex(Math.sqrt(2) / 2.0, 0);
		Complex c6 = new Complex(0, Math.sqrt(2) / 2.0);

		mat1.addToMatrix(0, 0, c1);
		mat1.addToMatrix(0, 1, c2);
		mat1.addToMatrix(1, 0, c3);
		mat1.addToMatrix(1, 1, c4);

		ket2.addToMatrix(0, c5);
		ket2.addToMatrix(1, c6);

		Complex result = BasicQuantum.mean(mat1, ket2);
		Complex result1 = new Complex(Math.round(result.getpReal() * 100.0) / 100.0,
				Math.round(result.getpImg() * 100.0) / 100.0);
		Complex resultToCompare1 = new Complex(2.5, 0);
		assertEquals(result1, resultToCompare1);
	}

	@Test
	public void varianzaTest() {

		ComplexMatrix mat1 = new ComplexMatrix(2, 2);
		ComplexVector ket2 = new ComplexVector(2);

		Complex c1 = new Complex(1, 0);
		Complex c2 = new Complex(0, -1);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(2, 0);
		Complex c5 = new Complex(Math.sqrt(2) / 2.0, 0);
		Complex c6 = new Complex(0, Math.sqrt(2) / 2.0);

		mat1.addToMatrix(0, 0, c1);
		mat1.addToMatrix(0, 1, c2);
		mat1.addToMatrix(1, 0, c3);
		mat1.addToMatrix(1, 1, c4);

		ket2.addToMatrix(0, c5);
		ket2.addToMatrix(1, c6);

		Complex result = BasicQuantum.variance(mat1, ket2);
		Complex result1 = new Complex(Math.round(result.getpReal() * 100.0) / 100.0,
				Math.round(result.getpImg() * 100.0) / 100.0);
		Complex resultToCompare1 = new Complex(0.25, 0);
		assertEquals(resultToCompare1, result1);
	}

	@Test
	public void valPropTest() throws IOException {
		ComplexMatrix mat1 = new ComplexMatrix(2, 2);
		Complex c1 = new Complex(-1, 0);
		Complex c2 = new Complex(0, -1);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(1, 0);

		mat1.addToMatrix(0, 0, c1);
		mat1.addToMatrix(0, 1, c2);
		mat1.addToMatrix(1, 0, c3);
		mat1.addToMatrix(1, 1, c4);

		ComplexVector result = BasicQuantum.valProp(mat1);

		ComplexVector ket2 = new ComplexVector(2);

		Complex c5 = new Complex(-Math.sqrt(2), 0);
		Complex c6 = new Complex(Math.sqrt(2), 0);

		ket2.addToMatrix(0, c5);
		ket2.addToMatrix(1, c6);

		assertEquals(result, ket2);

	}

	@Test
	public void dinamicTest() {
		ComplexMatrix mat1 = new ComplexMatrix(2, 2);
		Complex c1 = new Complex(-1, 0);
		Complex c2 = new Complex(0, -1);
		Complex c3 = new Complex(0, 1);
		Complex c4 = new Complex(1, 0);

		mat1.addToMatrix(0, 0, c1);
		mat1.addToMatrix(0, 1, c2);
		mat1.addToMatrix(1, 0, c3);
		mat1.addToMatrix(1, 1, c4);

		ComplexVector ket2 = new ComplexVector(2);

		Complex c5 = new Complex(-Math.sqrt(2), 0);
		Complex c6 = new Complex(Math.sqrt(2), 0);

		ket2.addToMatrix(0, c5);
		ket2.addToMatrix(1, c6);
		ComplexVector result = BasicQuantum.dinamica(2, ket2, mat1);
		ComplexVector ket3 = new ComplexVector(2);

		Complex c7 = new Complex(-2.8284271247461903, 0);
		Complex c8 = new Complex(2.8284271247461903, 0);
		ket3.addToMatrix(0, c7);
		ket3.addToMatrix(1, c8);
		assertEquals(result, ket3);
	}

}