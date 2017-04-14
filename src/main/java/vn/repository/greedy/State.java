package vn.repository.greedy;

import java.util.Arrays;


public class State {

    private String move;
    private int[] array;

    private int blankIndex;
    private int positionIndex;
    private int numberIndex;
    private int heuristicValue;
    private State previous;
    private int size;


    public State(int[] input) {
        this.size = (int) Math.sqrt(input.length*1.0);
        array = input;
        move = "";

        blankIndex = getIndex(0);
        positionIndex = getPositionIndex();
        numberIndex =  getNumberIndex(positionIndex);

        previous = null;
        heuristicValue = this.getFirstHeuristic();
    }

    /**
     * This constructor is used to create a new state based on
     * the previous state and a new blank index.
     *
     * @param previous   The previous state.
     * @param blankIndex The new blank index.
     */
    public State(State previous, int blankIndex, String move) {
        //Trạng thái này lấy trạng thái ở phía trước

        this.size = previous.getSize();
        this.move = move;
        array = Arrays.copyOf(previous.array, previous.array.length);
        array[previous.blankIndex] = array[blankIndex];
        array[blankIndex] = 0;
        this.blankIndex = blankIndex;
        positionIndex = getPositionIndex();
        numberIndex =  getNumberIndex(positionIndex);

        if(positionIndex<this.array.length-this.size/2){
            heuristicValue = this.getFirstHeuristic();
            //Hàm này sẽ đưa từng ô một tới đúng vị trí của nó
        }
        else{
            heuristicValue = this.getSecondHeuristic();
            //Hàm này lấy tổng Mahatan bắt đầu từ hai hàng cuối cùng đến hết
        }

        this.previous = previous;
    }


    // Các hàm Heurictic
    public int getFirstHeuristic() {
        int heuristic = 0;

        //Số ô đang xét dánh trọng số 5

        //Khoảng cách giữa ô cần xét và đích đến trọng số 8
        //Khoảng cách giữa ô trắng và ô cần xét trọng số 3
        //Khoảng cách giữa ô trắng và đích đến trọng số 2

        heuristic -= ((this.positionIndex+1)*10);
        heuristic += (getManhattanDistance(this.positionIndex,this.numberIndex)*8);
        heuristic += (getManhattanDistance(this.blankIndex,this.numberIndex)*3);
        heuristic += (getManhattanDistance(this.blankIndex,this.positionIndex)*2);
        heuristic -= this.getColumnIndex(this.blankIndex);
        heuristic -= this.getRowIndex(this.blankIndex);
        return heuristic;
    }

    public int getSecondHeuristic() {
        int heuristic = -100;

        //Đặt hàm heuristic là -100 để đảm bảo luôn luôn có độ ưu tiên cao hơn các TH ở Hàm Heuristic thứ nhất.

        for (int i = this.array.length-2*this.size; i < this.array.length-1; i++) {
            heuristic+=getManhattanDistance(i, this.getIndex(i+1));
        }
        return heuristic;
    }

    /**
     * @param
     * @param
     * @return
     */


    // Hàm lấy chỉ số cơ bản
    private int getIndex(int value) {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == value)
                return i;
        return -1;
    }
    public int getColumnIndex(int realindex){
        return realindex%this.size;
    }
    public int getRowIndex(int realindex){
        return realindex/this.size;
    }

    public boolean isConner(){
        boolean isconner = true;
        if(this.getColumnIndex(this.positionIndex)!=(this.size-1)||this.getRowIndex(this.positionIndex)>=(this.size-2))
            isconner = false;
        if(this.getColumnIndex(this.numberIndex)!=this.getColumnIndex(this.positionIndex)||this.getRowIndex(this.numberIndex)!=(this.getRowIndex(this.positionIndex)+1))
            isconner = false;
        if(this.getColumnIndex(this.blankIndex)!=(this.getColumnIndex(this.numberIndex)-1)||this.getRowIndex(this.blankIndex)!=(this.getRowIndex(this.numberIndex)))
            isconner = false;
        return isconner;
    }

    // Hàm lấy chỉ số cao cấp
    public int getPositionIndex() {
        int wrongindex = 0;
        while(this.array[wrongindex]==(wrongindex+1)){
            wrongindex++;
        }
        return wrongindex;
    }

    public int getNumberIndex(int wrongindex) {
        return this.getIndex(wrongindex+1);
    }

    public int getManhattanDistance(int startindex, int endindex) {
        return Math.abs(this.getColumnIndex(startindex) - this.getColumnIndex(endindex)) + Math.abs(this.getRowIndex(startindex) - this.getRowIndex(endindex));
        // Nội dung, trị tuyệt đối tọa độ hàng của đích trừ đi hàng của đầu cộng với trị tuyệt đối tọa độ cột của đích trừ đi cột của đầu
    }


    public boolean isSolved() {
        int[] p = array;
        for (int i = 1; i < p.length - 1; i++)
            if (p[i - 1] > p[i]) return false;

        return (p[0] == 1);
    }

    /**
     * todo: to read easier
     *
     * @return The puzzle as a string.
     */
    public String toString() {
        int[] state = this.array;
        String s = "\n\n";
        for (int i = 0; i < state.length; i++) {
            if (i % this.size == 0 && i != 0) s += "\n";
            s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";

        }
        return s;
    }

    /**
     * This method returns a string representation of all
     * steps taken to solve the puzzle.
     *
     * @return String - The puzzle steps as a string.
     */
    private String getAllSteps() {
        StringBuilder sb = new StringBuilder();
        if (this.previous != null)
            sb.append(previous.getAllSteps() + "\n" + previous.move);
        return sb.toString();
    }

    /**
     * This method creates a solution message for when the
     * puzzle has been solved using a StringBuilder.
     *
     * @return String - The solution message.
     */
    public String showSolutionMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAllSteps());
        return sb.toString();
    }

    public int getHeuristicValue() {
        return this.heuristicValue;
    }

    public int getBlankIndex() {
        return blankIndex;
    }

    public int getSize() {
        return size;
    }

    public int[] getArray() {
        return array;
    }

    public void setheuricticValue(int heurictic){
        this.heuristicValue = heurictic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;
        return Arrays.equals(array, state.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    public static void main(String[] args) {
        int[] inputAr = {1, 6, 0, 8, 2, 7, 3, 4, 5};
        State state1 = new State(inputAr);

        int[] inputAr2 = {1, 6, 7, 8, 0, 2, 3, 4, 5};
        State state2 = new State(inputAr2);

        System.out.println("Chuẩn: "+state1.toString());
        System.out.println("Không chuẩn: "+state2.toString());

        System.out.println("Chuẩn:"+state1.getHeuristicValue());
        System.out.println("Không chuẩn:"+state2.getHeuristicValue());
        System.out.println("Không chuẩn thấp hơn là sai");
    }

}
