package state.ex1;

import java.util.Objects;

public class StateContext {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doAction() {
        Objects.requireNonNull(state);
        state.doAction(this);
    }
}
