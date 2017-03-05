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
     * @param numberAr
     */
    public static void chooseAugro(int flag, int numberAr) {
        //A*
        if (flag == 1) {
            int tmp = 0;
            int[] inputAr = {7, 2, 3, 4, 6, 5, 1, 0, 8};
            if (tmp == 0) {
                Solver solver = new Solver(inputAr);
                solver.solve();
            }
        }
        //BFS
        else if (flag == 2) {

        }
        //DFS
        else if (flag == 3) {

        }
    }

    public static void main(String[] args) {
        Main.chooseAugro(1, 9);
    }
}
