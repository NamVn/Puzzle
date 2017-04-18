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
public class SearchNode
{

	private State curState;
	private SearchNode parent;
	private double cost; // chi phi 
	

	public SearchNode(State s)
	{
		curState = s;
		parent = null;
		cost = 0;
		
	}

	
	public SearchNode(SearchNode prev, State s, double c, double h)
	{
		parent = prev;
		curState = s;
		cost = c;
		
	}

	
	public State getCurState()
	{
		return curState;
	}

	
	public SearchNode getParent()
	{
		return parent;
	}

	
	public double getCost()
	{
		return cost;
	}

	
	
}