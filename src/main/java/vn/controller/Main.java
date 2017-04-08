package vn.controller;

import vn.repository.astar.Solver;

/**
 * Created by nam on 3/3/2017.
 */
public class Main {
    /**
     * todo minh se lam 3 ban choi voi kich thuoc 3*3  4*4  5*5 nhe de khi giai thich ham uoc luong cho nhanh, nho code theo
     * todo coding convention cua t gui cho nhe khi co loi hay ghep code thi toi moi de lam hay sua duoc nha
     * - thin: se tao ra 3 cai radio button nhe, o day ong phai danh so tung radiobutton de lay ra gia tri cho t de check vao if nhe.
     * +1:goi y theo thuat toan A*
     * +2: goi y theo BFS
     * +3: goi y theo DFS
     * + nho code giao dien trong view day nhe
     * - nhat anh: code cac thu lien quan cua ong trong package repository.bfs
     * - tri: code cac thu lien quan cua ong trong package repository.dfs
     *
     * @param flag
     * @param inputArr
     */
    public static void chooseAugro(int flag, int inputArr[]) {
        //A*
        if (flag == 1) {
            Solver solver = new Solver(inputArr);
            solver.solve(inputArr.length);
        }
        //BFS
        else if (flag == 2) {
            int tmp = 0;

            vn.repository.greedy.Solver solver = new vn.repository.greedy.Solver(inputArr);
            solver.solve();
        }
        //greedy
        else if (flag == 3) {

        }
    }

    public static void main(String[] args) {
        int[] inputArr8 = {
                7,
                2,
                3,
                4,
                6,
                5,
                1,
                0,
                8
        };
        int[] inputArr16 = {
                1,
                2,
                3,
                4,
                9,
                5,
                10,
                7,
                15,
                14,
                6,
                8,
                13,
                12,
                0,
                11
        };
//        int[] inputArr25 =
//                {
//                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 22, 0, 24
//                };
       // Main.chooseAugro(1, inputArr16);
        int[] inputAr = {0,8,7,6,5,4,3,2,1};
        Main.chooseAugro(2, inputAr);
    }
}
