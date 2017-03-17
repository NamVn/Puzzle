package vn.repository.astar;

/**
 * @author Van Nam
 */
class Move {

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
    public static State up(State state, int sizeImage) {
        int blankPosition = state.getBlankIndex();
        if (sizeImage == 9 && blankPosition > 2)
            return new State(state, blankPosition - 3, state.getArray()[blankPosition - 3] + " chuyen xuong duoi");
        else if (sizeImage == 16 && state.getBlankIndex() > 3)
            return new State(state, blankPosition - 4, state.getArray()[blankPosition - 4] + " chuyen xuong duoi");
        else if (sizeImage == 25 && blankPosition > 4)
            return new State(state, blankPosition - 5, state.getArray()[blankPosition - 5] + " chuyen xuong duoi");
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
    public static State down(State state, int sizeImage) {
        int blankPosition = state.getBlankIndex();
        if (sizeImage == 9 && blankPosition < 6)
            return new State(state, blankPosition + 3, state.getArray()[blankPosition + 3] + " chuyen len tren");
        else if (sizeImage == 16 && blankPosition < 12)
            return new State(state, blankPosition + 4, state.getArray()[blankPosition + 4] + " chuyen len tren");
        else if (sizeImage == 25 && blankPosition < 20)
            return new State(state, blankPosition + 5, state.getArray()[blankPosition + 5] + " chuyen len tren");
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
    public static State left(State state, int sizeImage) {
        int blankPosition = state.getBlankIndex();
        if (sizeImage == 9 && blankPosition % 3 > 0)
            return new State(state, blankPosition - 1, state.getArray()[blankPosition - 1] + " chuyen sang phai");
        else if (sizeImage == 16 && blankPosition % 4 > 0)
            return new State(state, blankPosition - 1, state.getArray()[blankPosition - 1] + " chuyen sang phai");
        else if (sizeImage == 25 && blankPosition % 5 > 0)
            return new State(state, blankPosition - 1, state.getArray()[blankPosition - 1] + " chuyen sang phai");
        return null;
    }

    /**
     * 7 2 3
     * 0 6 5
     * 1 4 8
     * todo index=7, only right number 6(index 4)
     *
     * @param state
     * @return
     */
    public static State right(State state, int sizeImage) {
        int blankPosition = state.getBlankIndex();
        if (sizeImage == 9 && blankPosition != 2 && blankPosition != 5 && blankPosition != 8)
            return new State(state, blankPosition + 1, state.getArray()[blankPosition + 1] + " chuyen sang trai");
        else if (sizeImage == 16 && blankPosition != 3 && blankPosition != 7 && blankPosition != 11 && blankPosition != 15)
            return new State(state, blankPosition + 1, state.getArray()[blankPosition + 1] + " chuyen sang trai");
        else if (sizeImage == 25 && blankPosition != 4 && blankPosition != 9 && blankPosition != 14 && blankPosition != 19 && blankPosition != 24)
            return new State(state, blankPosition + 1, state.getArray()[blankPosition + 1] + " chuyen sang trai");
        return null;
    }
}
