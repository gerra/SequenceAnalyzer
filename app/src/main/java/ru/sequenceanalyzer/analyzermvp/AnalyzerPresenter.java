package ru.sequenceanalyzer.analyzermvp;

import java.util.List;

/**
 * Created by german on 20.05.17.
 */

public interface AnalyzerPresenter {
    void onSequenceEntered(List<Integer> sequence);
}
