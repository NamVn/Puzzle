/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.repository.bfs;
import java.io.*;
import java.util.*;
/**
 *
 * @author Nhatanh
 */
public interface State
{
	// determine if current state is goal
	boolean isGoal();

	// generate successors to the current state
	ArrayList<State> genSuccessors();

	// determine cost from initial state to THIS state
	double findCost();

	// print the current state
	public void printState();

	// compare the actual state data
	public boolean equals(State s);
}