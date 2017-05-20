package ru.sequenceanalyzer.analyzermvp;

import android.support.annotation.NonNull;

import java.util.List;

public class AnalyzerModelImpl implements AnalyzerModel {

    private interface TwoNeighborValidator {
        boolean validate(Integer element, Integer next);
    }

    private boolean checkIsConstantSequence(List<Integer> sequence) {
        return checkIsValidSequence(sequence, Integer::equals);
    }

    private boolean checkIsArithmeticSequence(List<Integer> sequence) {
        int step = sequence.get(1) - sequence.get(0);
        return checkIsValidSequence(sequence, ((element, next) -> next - element == step));
    }

    private boolean checkIsGeometricSequence(List<Integer> sequence) {
        if (sequence.get(0) == 0) {
            return false;
        }
        /*
        Division is not accurate, so we can just multiply using this rule:
        a / b = c / d if and only if a * d = b * c (b, d != 0)
         */
        Integer first = sequence.get(0);
        Integer second = sequence.get(1);
        return checkIsValidSequence(sequence, ((element, next) -> {
            if (element == 0) {
                return false;
            }
            /*
            next / element = second / first
            next * first = second * element
             */
            return (long) next * first == (long) second * element;
        }));
    }

    private boolean checkIsValidSequence(List<Integer> sequence, TwoNeighborValidator validator) {
        Integer prev = null;
        for (Integer x : sequence) {
            if (prev != null) {
                if (!validator.validate(prev, x)) {
                    return false;
                }
            }
            prev = x;
        }
        return true;
    }

    @Override
    public SequenceType getSequenceType(@NonNull List<Integer> sequence) {
        if (sequence.size() == 1) {
            return SequenceType.CONSTANT;
        }
        if (checkIsConstantSequence(sequence)) {
            return SequenceType.CONSTANT;
        } else if (checkIsArithmeticSequence(sequence)) {
            return SequenceType.ARITHMETIC;
        } else if (checkIsGeometricSequence(sequence)) {
            return SequenceType.GEOMETRIC;
        }
        return SequenceType.RANDOM;
    }
}
