import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TuringMachine {
  // Number of variables in the input alphabet
  private int numInputVariables;
  // The input alphabet
  private String[] inputAlphabet;
  // Number of variables in the tape alphabet
  private int numTapeVariables;
  // The tape alphabet
  private String[] tapeAlphabet;
  // The blank symbol
  private String blankSymbol;
  // Number of states
  private int numStates;
  // The states
  private String[] states;
  // The start state
  private String startState;
  // The accept state
  private String acceptState;
  // The reject state
  private String rejectState;
  // The transition rules
  private String[][] transitionRules;

  // Construct a Turing machine from the input specification
  public TuringMachine(int numInputVariables, String[] inputAlphabet, int numTapeVariables, String[] tapeAlphabet,
      String blankSymbol, int numStates, String[] states, String startState, String acceptState, String rejectState,
      String[][] transitionRules) {
    this.numInputVariables = numInputVariables;
    this.inputAlphabet = inputAlphabet;
    this.numTapeVariables = numTapeVariables;
    this.tapeAlphabet = tapeAlphabet;
    this.blankSymbol = blankSymbol;
    this.numStates = numStates;
    this.states = states;
    this.startState = startState;
    this.acceptState = acceptState;
    this.rejectState = rejectState;
    this.transitionRules = transitionRules;
  }

  // Simulate the Turing machine on the given input string
  public void simulate(String input) {
    // Initialize the tape with the input string and enough blank symbols to the
    // right
    String tape = input + blankSymbol.repeat(10);
    // Set the current position to the first cell of the tape
    int currentPosition = 0;
    // Set the current state to the start state
    String currentState = startState;

    // Create a list to store the route taken by the machine
    List<String> route = new ArrayList<>();

    // Set the step counter to 0
    int steps = 0;

    // Run the machine until it halts or the number of steps exceeds the threshold
    while (true) {
      // Get the current symbol on the tape
      String currentSymbol = tape.charAt(currentPosition) + "";
      // Search for a transition rule that matches the current state and symbol
      String[] transitionRule = null;
      for (String[] rule : transitionRules) {
        if (rule[0].equals(currentState) && rule[1].equals(currentSymbol)) {
          transitionRule = rule;
          break;
        }
      }

      // If no transition rule is found, the machine rejects the input
      if (transitionRule == null) {
        printRoute(route);
        System.out.println("Rejected");
        
        return;
      }

      // Update the tape with the new symbol specified in the transition rule
      tape = tape.substring(0, currentPosition) + transitionRule[2] + tape.substring(currentPosition + 1);
      // Move the tape head according to the direction specified in the transition
      // rule
      currentPosition += transitionRule[3].equals("L") ? -1 : 1;
      // Update the current state with the new state
      currentState = transitionRule[4];

      // Add the current state and symbol to the route
      route.add(currentState + " " + currentSymbol);

      // If the machine reaches the accept state, it accepts the input
      if (currentState.equals(acceptState)) {
        printRoute(route);
        System.out.println("Accepted");
        
        return;
      }

      // If the machine reaches the reject state, it rejects the input
      if (currentState.equals(rejectState)) {
        printRoute(route);
        System.out.println("Rejected");
        
        return;
      }

      // Increment the step counter
      steps++;

      // If the number of steps exceeds the threshold, the machine is in an infinite
      // loop
      if (steps > 1000) {
        printRoute(route);
        System.out.println("Looped");
        
        return;
      }
    }
  }
  // Print the route taken by the Turing machine
private void printRoute(List<String> route) {
  for (String step : route) {
    System.out.println("Route Taken:" + step);
  }
}

  
}

