package ru.sequenceanalyzer.analyzermvp;

import java.util.List;

interface AnalyzerPresenter {
    void onSequenceEntered(List<Integer> sequence);
}
