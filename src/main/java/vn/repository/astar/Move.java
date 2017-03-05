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
    public static State up(State state) {
        if (state.getBlankIndex() > 2)
            return new State(state, state.getBlankIndex() - 3, state.getArray()[state.getBlankIndex() - 3] + " chuyen xuong duoi");
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
        if (state.getBlankIndex() < 6)
            return new State(state, state.getBlankIndex() + 3, state.getArray()[state.getBlankIndex() + 3] + " chuyen len tren");
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
    public static State left(State state) {
        if (state.getBlankIndex() % 3 > 0)
            return new State(state, state.getBlankIndex() - 1, state.getArray()[state.getBlankIndex() - 1] + " chuyen sang phai");
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
    public static State right(State state) {
        if (state.getBlankIndex() % 3 < 2)
            return new State(state, state.getBlankIndex() + 1, state.getArray()[state.getBlankIndex() + 1] + " chuyen sang trai");
        return null;
    }
}
