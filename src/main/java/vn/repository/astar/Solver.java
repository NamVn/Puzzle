package vn.repository.astar;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author Van Nam
 */
public class Solver {
    private State initialState;
    private State state;
    private static final int CAPACITY = 100;
    /**
     * A Hash set containing the states that have been visited.
     */
    private final HashSet<State> visitedSet = new HashSet<State>();
    /**
     * The A * Search priority queue used to solve the puzzle, order by asc
     */
    private final PriorityQueue<State> queue = new PriorityQueue<State>(CAPACITY, new Comparator<State>() {
        @Override
        public int compare(State o1, State o2) {
            return o1.f() - o2.f();

        }
    });

    public Solver(int[] puzzleInput) {
        this.initialState = new State(puzzleInput);
        this.state = this.initialState;
    }

    /**
     * todo get h(n)
     *
     * @param array
     * @return
     */
    public static int getHeuristic(int[] array) {
        int heuristic = 0;
        if (array.length == 9)
            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0)
                    heuristic += getManhattanDistance9(i, array[i]);
            }
        else if (array.length == 16)
            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0)
                    heuristic += getManhattanDistance16(i, array[i]);
            }
        else if (array.length == 25)
            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0)
                    heuristic += getManhattanDistance25(i, array[i]);
            }
        return heuristic;
    }

    /**
     * 7 2 3
     * 4 6 5
     * 1 0 8
     * <p>
     * 1 2 3
     * 4 5 6
     * 7 8 0
     *
     * @param index
     * @param number
     * @return
     */
    private static int getManhattanDistance9(int index, int number) {
        return Math.abs((index / 3) - ((number - 1) / 3)) + Math.abs((index % 3) - ((number - 1) % 3));
    }

    private static int getManhattanDistance16(int index, int number) {
        return Math.abs((index / 4) - ((number - 1) / 4)) + Math.abs((index % 4) - ((number - 1) % 4));
    }

    private static int getManhattanDistance25(int index, int number) {
        return Math.abs((index / 5) - ((number - 1) / 5)) + Math.abs((index % 5) - ((number - 1) % 5));
    }


    private void addToQueue(State state) {
        if (state != null && !visitedSet.contains(state))
            queue.add(state);

    }

    /**
     * This method handles the solving of the puzzle.
     */
    public void solve(int sizeImage) {
        // Clear the queue and add the initial state.
        queue.clear();
        queue.add(this.initialState);
        while (!queue.isEmpty()) {
            // Get the best next state.
            state = queue.poll();

            // Check if the state is a solution.
            if (state.isSolved()) {
                System.out.println(state.showSolutionMessage());
                return;

            }
            // Add this state to the visited HashSet so we don't revisit it.
            visitedSet.add(state);

            // Add valid moves to the queue.
            addToQueue(Move.up(state, sizeImage));
            addToQueue(Move.down(state, sizeImage));
            addToQueue(Move.left(state, sizeImage));
            addToQueue(Move.right(state, sizeImage));
        }
    }
}
