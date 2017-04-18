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
public class BFSearch
{
	
	public static void search(int[] board, boolean d)
	{
		SearchNode root = new SearchNode(new EightPuzzleState(board));
		Queue<SearchNode> queue = new LinkedList<SearchNode>();

		queue.add(root);

		performSearch(queue, d);
	}

	
	public static void search(boolean d)
	{
		SearchNode root = new SearchNode(new FWGC_State());
		Queue<SearchNode> queue = new LinkedList<SearchNode>();

		queue.add(root);

		performSearch(queue, d);
	}

	/*
	 * kiem tra xem 1 dinh da duoc danh gia hay chua
	 * Returns true neu da dc danh gia, false neu chua.
	 */
	private static boolean checkRepeats(SearchNode n)
	{
		boolean retValue = false;
		SearchNode checkNode = n;

		// kiem tra xem nut co rong khong va co bang nut dang xet khong
		while (n.getParent() != null && !retValue)
		{
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				retValue = true;
			}
			n = n.getParent();
		}

		return retValue;
	}

	
	public static void performSearch(Queue<SearchNode> q, boolean d)
	{
		int searchCount = 1; // tim gia tri dau tien

		while (!q.isEmpty()) // Duyet den khi hang doi rong
		{
			SearchNode tempNode = (SearchNode) q.poll();

			if (!tempNode.getCurState().isGoal()) // new tempNode khong phai dich
													
			{
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors(); // generate tempNode's immediate
											// successors

				//vong lap kiem tra xem cac nut da duoc duyet chua
                                //neu chua thi add vao hang doi
				for (int i = 0; i < tempSuccessors.size(); i++)
				{
					// them chi phi vao tong chi phi hien tai
                                    // new node la tham so thu 2
					SearchNode newNode = new SearchNode(tempNode,
							tempSuccessors.get(i), tempNode.getCost()
									+ tempSuccessors.get(i).findCost(), 0);

					if (!checkRepeats(newNode))
					{
						q.add(newNode);
					}
				}
				searchCount++;
			}
			else
			// neu dinh la dich thi dung tim kiem va tra ve ket qua
			{
				// duong di tu luc bat dau den khi ket thuc
				Stack<SearchNode> solutionPath = new Stack<SearchNode>();
				solutionPath.push(tempNode);
				tempNode = tempNode.getParent();

				while (tempNode.getParent() != null)
				{
					solutionPath.push(tempNode);
					tempNode = tempNode.getParent();
				}
				solutionPath.push(tempNode);

				// kich thuoc cua stack
				int loopSize = solutionPath.size();

				for (int i = 0; i < loopSize; i++)
				{
					tempNode = solutionPath.pop();
					tempNode.getCurState().printState();
					System.out.println();
					System.out.println();
				}
				System.out.println("Chi phi la: " + tempNode.getCost());
				if (d)
				{
					System.out.println("So nut da duoc kiem tra: "
							+ searchCount);
				}

				System.exit(0);
			}
		}

		// neu khong tim duoc dich.
		System.out.println("khong tim duoc loi giai!");
	}
}