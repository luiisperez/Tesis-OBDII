/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entities;
import java.util.Arrays;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author cristian
 */
public class DiagnosticMultilayerPerceptron {
    private int entradas;
    private int salidas;
    private MultiLayerPerceptron myMlPerceptron;

    
    public static void bildRNA() {

        // create training set (logical Diagnostic function)
        DataSet trainingSet = new DataSet(16, 10);
        
        
        // ENTRENAMIENTO
        // aquí entrarían 16 valores y saldrían 10 valores
//        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
//        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
//        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
//        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

        // create multi layer perceptron: in:16, shadow: 12, out: 10
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 16, 12, 10);
        // learn the training set
        myMlPerceptron.learn(trainingSet);

        // prueba perceptron
        System.out.println("Testing trained neural network");
        testNeuralNetwork(myMlPerceptron, trainingSet);

        // save trained neural network
        myMlPerceptron.save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

        // Probar la red
        System.out.println("Testing loaded neural network");
        testNeuralNetwork(loadedMlPerceptron, trainingSet);

    }
    
    // función para probar salidas según entradas.
    public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

        for(DataSetRow dataRow : testSet.getRows()) {
            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[ ] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
        }

    }
}
