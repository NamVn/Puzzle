package vn.repository.astar;


import java.util.Arrays;


class State {
    private String move;
    private int[] array = new int[9];
    private int blankIndex;
    private int g;
    private int h;
    private State previous;

    public State(int[] input) {
        array = input;
        move = null;
        blankIndex = getIndex(input, 0);
        previous = null;
        g = 0;
        h = Solver.getHeuristic(array);
    }

    /**
     * This constructor is used to create a new state based on
     * the previous state and a new blank index.
     *
     * @param previous   The previous state.
     * @param blankIndex The new blank index.
     */
    public State(State previous, int blankIndex, String move) {
        this.move = move;
        array = Arrays.copyOf(previous.array, previous.array.length);
        array[previous.blankIndex] = array[blankIndex];
        array[blankIndex] = 0;
        this.blankIndex = blankIndex;
        this.g = previous.g + 1;
        h = Solver.getHeuristic(array);
        this.previous = previous;
    }

    private static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == value) return i;
        return -1;
    }

    /**
     * This method checks to see if the current state is the solved state.
     *
     * @return True if it is in the solved state, false if it is not.
     */
    public boolean isSolved() {
        int[] p = array;
        for (int i = 1; i < p.length - 1; i++)
            if (p[i - 1] > p[i]) return false;

        return (p[0] == 1);
    }

    /**
     * todo: to read easier
     *
     * @return The puzzle as a string.
     */
    public String toString() {
        int[] state = this.array;
        String s = "\n\n";
        if (state.length == 9)
            for (int i = 0; i < state.length; i++) {
                if (i % 3 == 0 && i != 0)
                    s += "\n";
                s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";

            }
        else if (state.length == 16)
            for (int i = 0; i < state.length; i++) {
                if (i % 4 == 0 && i != 0)
                    s += "\n";
                s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
            }
        else if (state.length == 25)
            for (int i = 0; i < state.length; i++) {
                if (i % 5 == 0 && i != 0)
                    s += "\n";
                s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
            }
        return s;
    }

    /**
     * This method returns a string representation of all
     * steps taken to solve the puzzle.
     *
     * @return String - The puzzle steps as a string.
     */
    private String getAllSteps() {
        StringBuilder sb = new StringBuilder();
        if (this.previous != null) {
            sb.append(previous.getAllSteps());
           if(previous.move!=null)
               sb.append("\n>> "+previous.move+"\n**************************************");
        }
        sb.append(this.toString());
        return sb.toString();
    }
//    private String getAllSteps() {
//        StringBuilder sb = new StringBuilder();
//        if (this.previous != null) {
//            sb.append(previous.getAllSteps());
//            if (previous.move != null)
//                sb.append(previous.move + "\n");
//
//        }
//        return sb.toString();
//    }

    /**
     * This method creates a solution message for when the
     * puzzle has been solved using a StringBuilder.
     *
     * @return String - The solution message.
     */
//    public String showSolutionMessage() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getAllSteps());
//        return sb.toString();
//    }
    public String showSolutionMessage() {
              StringBuilder sb = new StringBuilder();
               sb.append(getAllSteps() + "===> con 1 nuoc khong goi y nua nhe");
              return sb.toString();
          }
    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int f() {
        return g() + h();
    }

    public int getBlankIndex() {
        return blankIndex;
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;
        return Arrays.equals(array, state.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
