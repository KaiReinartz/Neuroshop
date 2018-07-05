package Neuroshop.ANN.Validate;

import Neuroshop.ANN.Data.NeuralDataSet;
import Neuroshop.ANN.Learn.DeltaRule;
import Neuroshop.ANN.Neural.HiddenLayer;
import Neuroshop.ANN.Neural.NeuralNet;
import Neuroshop.ANN.Neural.OutputLayer;
import Neuroshop.Models.ANNModel;

import java.util.ArrayList;

public class Simulation extends DeltaRule {

    private ANNModel annModel;

    public ArrayList<ArrayList<Double>> deltaNeuron;

    public ArrayList<ArrayList<ArrayList<Double>>> simWeights;

    public Simulation (NeuralNet _neuralNet, NeuralDataSet _trainDataSet) {
        super(_neuralNet, _trainDataSet);
        initializeDeltaNeuron();
        initializeSimWeights();
    }

    private void initializeDeltaNeuron() {

    }

    private void initializeSimWeights() {
        this.simWeights = new ArrayList<>();
        int numberOfHiddenLayers = this.neuralNet.getNumberOfHiddenLayers();
        for (int l = 0; l <= numberOfHiddenLayers; l++) {
            int numberOfNeuronsInLayer, numberOfInputsInNeuron;
            this.simWeights.add(new ArrayList<ArrayList<Double>>());
            if (l < numberOfHiddenLayers) {
                numberOfNeuronsInLayer = this.neuralNet.getHiddenLayer(l)
                        .getNumberOfNeuronsInLayer();
                for (int j = 0; j < numberOfNeuronsInLayer; j++) {
                    numberOfInputsInNeuron = this.neuralNet.getHiddenLayer(l)
                            .getNeuron(j).getNumberOfInputs();
                    this.simWeights.get(l).add(new ArrayList<Double>());
                    for (int i = 0; i <= numberOfInputsInNeuron; i++) {
                        this.simWeights.get(l).get(j).add(0.0);
                    }
                }
            } else {
                numberOfNeuronsInLayer = this.neuralNet.getOutputLayer()
                        .getNumberOfNeuronsInLayer();
                for (int j = 0; j < numberOfNeuronsInLayer; j++) {
                    numberOfInputsInNeuron = this.neuralNet.getOutputLayer()
                            .getNeuron(j).getNumberOfInputs();
                    this.simWeights.get(l).add(new ArrayList<Double>());
                    for (int i = 0; i <= numberOfInputsInNeuron; i++) {
                        this.simWeights.get(l).get(j).add(0.0);
                    }
                }
            }
        }

    }

    public void applySimWeights(){
        int numberOfHiddenLayers=this.neuralNet.getNumberOfHiddenLayers();
        for(int l=0;l<=numberOfHiddenLayers;l++){
            int numberOfNeuronsInLayer,numberOfInputsInNeuron;
            if(l<numberOfHiddenLayers){
                HiddenLayer hl = this.neuralNet.getHiddenLayer(l);
                numberOfNeuronsInLayer=hl.getNumberOfNeuronsInLayer();
                for(int j=0;j<numberOfNeuronsInLayer;j++){
                    numberOfInputsInNeuron=hl.getNeuron(j).getNumberOfInputs();
                    for(int i=0;i<=numberOfInputsInNeuron;i++){
                        double newWeight=annModel.getNewWeights().get(l).get(j).get(i);
                        hl.getNeuron(j).updateWeight(i, newWeight);
                    }
                }
            }
            else{
                OutputLayer ol = this.neuralNet.getOutputLayer();
                numberOfNeuronsInLayer=ol.getNumberOfNeuronsInLayer();
                for(int j=0;j<numberOfNeuronsInLayer;j++){
                    numberOfInputsInNeuron=ol.getNeuron(j).getNumberOfInputs();

                    for(int i=0;i<=numberOfInputsInNeuron;i++){
                        double newWeight=annModel.getNewWeights().get(l).get(j).get(i);
                        ol.getNeuron(j).updateWeight(i, newWeight);
                    }
                }
            }
        }
    }

    public void initModel(ANNModel annModel) {
        this.annModel = annModel;
    }
}