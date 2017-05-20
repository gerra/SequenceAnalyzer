package ru.sequenceanalyzer.analyzermvp;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import ru.sequenceanalyzer.R;

public interface AnalyzerModel {
    enum SequenceType {
        ARITHMETIC(R.string.arithmetic),
        GEOMETRIC(R.string.geometric),
        CONSTANT(R.string.constant),
        RANDOM(R.string.random);

        @StringRes public int stringId;

        SequenceType(int stringId) {
            this.stringId = stringId;
        }
    }

    SequenceType getSequenceType(@NonNull List<Integer> sequence);
}
