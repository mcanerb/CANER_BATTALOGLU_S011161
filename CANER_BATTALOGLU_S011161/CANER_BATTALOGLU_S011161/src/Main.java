import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read the input specification from the input file
        File inputFile = new File("C:/Users/caner/Desktop/CANER_BATTALOGLU_S011161/CANER_BATTALOGLU_S011161/src/inputs/input.txt");
        Scanner scanner = new Scanner(inputFile);
            int numInputVariables = scanner.nextInt();
            String[] inputAlphabet = new String[numInputVariables];
            for (int i = 0; i < numInputVariables; i++) {
              inputAlphabet[i] = scanner.next();
            }
            int numTapeVariables = scanner.nextInt();
            String[] tapeAlphabet = new String[numTapeVariables];
            for (int i = 0; i < numTapeVariables; i++) {
              tapeAlphabet[i] = scanner.next();
            }
            String blankSymbol = scanner.next();
            int numStates = scanner.nextInt();
            String[] states = new String[numStates];
            for (int i = 0; i < numStates; i++) {
              states[i] = scanner.next();
            }
            String startState = scanner.next();
            String acceptState = scanner.next();
            String rejectState = scanner.next();
            String[][] transitionRules = new String[numStates][5];
            for (int i = 0; i < numStates; i++) {
              transitionRules[i][0] = scanner.next();
              transitionRules[i][1] = scanner.next();
              transitionRules[i][2] = scanner.next();
              transitionRules[i][3] = scanner.next();
              transitionRules[i][4] = scanner.next();
            }
            String input = scanner.next();
            
     
            // Create a Turing machine from the input specification and simulate it on the given input string
            TuringMachine tm = new TuringMachine(numInputVariables, inputAlphabet, numTapeVariables, tapeAlphabet, blankSymbol, numStates, states, startState, acceptState, rejectState, transitionRules);
           
            tm.simulate(input);
            scanner.close();
            
          
        }
        
    }
      








