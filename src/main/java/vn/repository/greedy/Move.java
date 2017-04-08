/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.repository.greedy;

/**
 *
 * @author Tri Ngo
 */
public class Move {

    private Move() {
    }

    /**
     * 7 2 3
     * 4 6 5
     * 1 0 8
     * todo 0 index=7-3=4, only up to number 6(index 4)
     *
     * @param state
     * @return
     */
    
    
    
    
    public static State up(State state) {
        if (state.getBlankIndex() > state.getSize()-1)
            return new State(state, state.getBlankIndex() - state.getSize(), state.getArray()[state.getBlankIndex() - state.getSize()] + " chuyen xuong duoi");
        return null;
    }

    /**
     * 7 2 3
     * 4 0 5
     * 1 6 8
     * todo index=4, only down number 6(index 7)
     *
     * @param state
     * @return
     */
    public static State down(State state) {
        if (state.getBlankIndex() < state.getSize()*(state.getSize()-1))
            return new State(state, state.getBlankIndex() + state.getSize(), state.getArray()[state.getBlankIndex() + state.getSize()] + " chuyen len tren");
        return null;
    }

    /**
     * 7 2 3
     * 4 6 5
     * 1 0 8
     * todo index = 7, only left number 1(index 6)
     *
     * @param state
     * @return
     */
    //modun 3 là theo hàng.
        //VD: 
    /* 1 2 3
     * 4 5 6
     * 7 8 9
    */
    /*Phép modun là phép lấy tọa độ từng cột
     * 1%3 = 1; 2%3 = 2; 3%3 = 0
     * 4%3 = 1; 5%3 = 2; 6%3 = 0
     * 7%3 = 1; 8%3 = 2; 9%3 = 0
    */
    /*Phép chia làm tròn là phép lấy tọa độ từng hàng
     * 1/3 = 0; 2%3 = 0; 3%3 = 1
     * 4/3 = 1; 5%3 = 1; 6%3 = 2
     * 7/3 = 2; 8%3 = 2; 9%3 = 3
    */
    
    
    public static State left(State state) {
        if (state.getBlankIndex() % state.getSize() > 0)
            return new State(state, state.getBlankIndex() - 1, state.getArray()[state.getBlankIndex() - 1] + " chuyen sang phai");
        return null;
        
        
    }

    /**
     * @param state
     * @return
     */
    public static State right(State state) {
        if (state.getBlankIndex() % state.getSize() < state.getSize()-1)
            return new State(state, state.getBlankIndex() + 1, state.getArray()[state.getBlankIndex() + 1] + " chuyen sang trai");
        return null;
    }
    
    public static State[] insertConner(State state) {
        
        //Đây là các trường hợp được ưu tiên nhất! Chính vì thế, nó phải được đánh hàm Heurictic bé nhất để đảm bảo nó luôn được chạy đầu tiên
        //Đây là các trường hợp khi các ô chữ chạy vào góc trên cùng bên phải. Các nước này là bắt buộc, tuân theo lịch trình đã định
        
        State steps[] = new State[9];
        steps[0] = Move.left(state);
        steps[0].setheuricticValue(-200);
        
        steps[1] = Move.up(steps[0]);
        steps[1].setheuricticValue(-199);

        
        steps[2] = Move.right(steps[1]);
        steps[2].setheuricticValue(-198);

        
        steps[3] = Move.right(steps[2]);
        steps[3].setheuricticValue(-197);

        
        steps[4] = Move.down(steps[3]);
        steps[4].setheuricticValue(-196);

        
        steps[5] = Move.left(steps[4]);
        steps[5].setheuricticValue(-195);

        
        steps[6] = Move.up(steps[5]);
        steps[6].setheuricticValue(-194);

         
        steps[7] = Move.left(steps[6]);
        steps[7].setheuricticValue(-193);

         
        steps[8] = Move.down(steps[7]);
        steps[8].setheuricticValue(-192);

        return steps;
    }
    
}
