package ru.sequenceanalyzer.analyzermvp;

import java.util.List;

public class AnalyzerPresenterImpl implements AnalyzerPresenter {

    private AnalyzerView analyzerView;
    private AnalyzerModel analyzerModel;

    public AnalyzerPresenterImpl(AnalyzerView analyzerView) {
        this.analyzerView = analyzerView;
        this.analyzerModel = new AnalyzerModelImpl();
    }

    @Override
    public void onSequenceEntered(List<Integer> sequence) {
        AnalyzerModel.SequenceType sequenceType = analyzerModel.getSequenceType(sequence);
        analyzerView.onSequenceTypeDetected(sequenceType);
    }
}
