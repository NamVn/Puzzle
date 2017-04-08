/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.repository.greedy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Tri Ngo
 */
public class Solver {

    private State initialState;
    private State state;
    private static final int CAPACITY = 100;
    //Sức chúa của queue này là 100
    /**
     * A Hash set containing the states that have been visited.
     */
    private final HashSet<State> closed = new HashSet<State>();
    /**
     * The A * Search priority queue used to solve the puzzle, order by asc
     */
    private final PriorityQueue<State> fringe = new PriorityQueue<State>(CAPACITY, new Comparator<State>() {
        @Override
        public int compare(State o1, State o2) {
            return o1.getHeuristicValue() - o2.getHeuristicValue(); //Thuật toán tham lam
        }
    });

    public Solver(int[] puzzleInput) {
        this.initialState = new State(puzzleInput);
        this.state = this.initialState;
    }

    /**
     * todo get h(n)
     *
     * @param
     * @return
     */
    
    
    private void addToQueue(State nextStates[]) {
        for (int i = 0; i < nextStates.length; i++) {
            fringe.add(nextStates[i]);
        }   
    }

    private void addToQueue(State nextState) {
        if (nextState != null && !closed.contains(nextState))
            fringe.add(nextState);
        
    }
  
    /**
     * This method handles the solving of the puzzle.
     */
    public void solve() {
        // Clear the queue and add the initial state.
        fringe.clear();
        fringe.add(this.initialState);
        while (!fringe.isEmpty()) {
            // Get the best next state.
            state = fringe.poll();
            
            //Đặt một điều kiện if, chèn vào đây nhé!
            
            // Check if the state is a solution.
            if (state.isSolved()) {
                System.out.println(state.showSolutionMessage());
                return;
            }
            // Add this state to the closed HashSet so we don't revisit it.
            closed.add(state);
            if(state.isConner()){
                    State steps[] = Move.insertConner(state);
                    addToQueue(steps);
                    System.out.println("Có add");
            }
            else{
                addToQueue(Move.up(state));
                addToQueue(Move.down(state));
                addToQueue(Move.left(state));
                addToQueue(Move.right(state));
            }
            // Add valid moves to the queue.
        }
    }
}
