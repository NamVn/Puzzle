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
public class EightPuzzleState implements State
{
	// khia bao kich thuoc phan tu
	private final int PUZZLE_SIZE = 9;


	
// khai bao mang dich
	private final int[] GOAL = new int[]
	{ 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	private int[] curBoard;

	
	public EightPuzzleState(int[] board)
	{
		curBoard = board;
		
	}

	// mat bao nhieu buoc de den trang thai dang xet
	public double findCost()
	{
		return 1;
	}

	
	

	
	private int getHole()
	{
		
		int holeIndex = -1;

		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			if (curBoard[i] == 0)
				holeIndex = i;
		}
		return holeIndex;
	}


	

	// tao ra 1 ban sao cua mamng va truyen cho kich thuc
	private int[] copyBoard(int[] state)
	{
		int[] ret = new int[PUZZLE_SIZE];
		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			ret[i] = state[i];
		}
		return ret;
	}

	// di chuyen
	
	public ArrayList<State> genSuccessors()
	{
		ArrayList<State> successors = new ArrayList<State>();
		int hole = getHole();

		
		if (hole != 0 && hole != 3 && hole != 6)
		{
			// doi vi tri va luu
			swapAndStore(hole - 1, hole, successors);
                       
		}

		
		if (hole != 6 && hole != 7 && hole != 8)
		{
			swapAndStore(hole + 3, hole, successors);
		}

	
		if (hole != 0 && hole != 1 && hole != 2)
		{
			swapAndStore(hole - 3, hole, successors);
		}
		
		if (hole != 2 && hole != 5 && hole != 8)
		{
			swapAndStore(hole + 1, hole, successors);
		}

		return successors;
	}

	// thuat toan tao ra mang moi d1 d2 va luu
	private void swapAndStore(int d1, int d2, ArrayList<State> s)
	{
		int[] cpy = copyBoard(curBoard);
		int temp = cpy[d1];
		cpy[d1] = curBoard[d2];
		cpy[d2] = temp;
		s.add((new EightPuzzleState(cpy)));
	}

	// kiem tra mang hien tai co phai la dich khong
	public boolean isGoal()
	{
		if (Arrays.equals(curBoard, GOAL))
		{
			return true;
		}
		return false;
	}

	//in ra
	public void printState()
	{
		System.out.println(curBoard[0] + " | " + curBoard[1] + " | "
				+ curBoard[2]);
		System.out.println("---------");
		System.out.println(curBoard[3] + " | " + curBoard[4] + " | "
				+ curBoard[5]);
		System.out.println("---------");
		System.out.println(curBoard[6] + " | " + curBoard[7] + " | "
				+ curBoard[8]);

	}

	
	public boolean equals(State s)
	{
		if (Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard()))
		{
			return true;
		}
		else
			return false;

	}

	// lay gia tri dang xet
	public int[] getCurBoard()
	{
		return curBoard;
	}

}