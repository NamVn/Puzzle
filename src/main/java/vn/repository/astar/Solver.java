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
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                heuristic += getManhattanDistance(i, array[i]);
        }
        return heuristic;
    }

    /**
     * @param index
     * @param number
     * @return
     */
    public static int getManhattanDistance(int index, int number) {
        return Math.abs((index / 3) - ((number - 1) / 3)) + Math.abs((index % 3) - ((number - 1) % 3));
    }

    private void addToQueue(State nextState) {
        if (nextState != null && !visitedSet.contains(nextState))
            queue.add(nextState);

    }

    /**
     * This method handles the solving of the puzzle.
     */
    public void solve() {
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
            addToQueue(Move.up(state));
            addToQueue(Move.down(state));
            addToQueue(Move.left(state));
            addToQueue(Move.right(state));
        }
    }
}
