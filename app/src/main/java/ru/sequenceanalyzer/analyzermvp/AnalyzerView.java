package ru.sequenceanalyzer.analyzermvp;

/**
 * Created by german on 20.05.17.
 */

public interface AnalyzerView {
    void onSequenceTypeDetected(AnalyzerModel.SequenceType sequenceType);
}
