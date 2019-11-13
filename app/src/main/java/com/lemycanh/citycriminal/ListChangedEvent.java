package com.lemycanh.citycriminal;

/**
 * Created by lemycanh on 6/11/2019.
 */

public class ListChangedEvent {
    private Problem problem;

    public ListChangedEvent(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
