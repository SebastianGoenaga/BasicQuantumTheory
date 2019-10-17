package simulator;

import calculator.MatrixCalculator;
import calculator.Functions;
import model.Complex;
import model.ComplexMatrix;
import model.ComplexVector;

public class QuantumStatesSystem {
	
	ComplexVector vector;

	public QuantumStatesSystem(ComplexVector vector) {
		this.vector = vector;
	}

    public double likeliHood(int i) {
		return Math.sqrt(Functions.Modulus(vector.getElement(i)) / MatrixCalculator.norm(vector));
	}

	public Complex transitionAmplitudes(ComplexVector vector) {

		Complex complex = MatrixCalculator.innerProduct((ComplexMatrix) vector,(ComplexMatrix) this.vector);
		return complex;
	}

}