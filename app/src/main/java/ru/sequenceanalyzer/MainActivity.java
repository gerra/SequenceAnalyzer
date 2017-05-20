package ru.sequenceanalyzer;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.sequenceanalyzer.analyzermvp.AnalyzerModel;
import ru.sequenceanalyzer.analyzermvp.AnalyzerPresenterImpl;
import ru.sequenceanalyzer.analyzermvp.AnalyzerView;
import ru.sequenceanalyzer.tutorial.TutorialAdapter;

public class MainActivity extends AppCompatActivity implements
        TutorialAdapter.OnCompleteTutorialListener, AnalyzerView {

    private ViewPager tutorialViewPager;
    private View sequenceView;

    private EditText sequenceEditText;
    private Button   sequenceDetectButton;
    private TextView sequenceTypeView;

    private AnalyzerPresenterImpl analyzerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutorialViewPager = (ViewPager) findViewById(R.id.tutorialViewPager);
        sequenceView = findViewById(R.id.mainContent);

        sequenceEditText = (EditText) findViewById(R.id.sequenceEditText);
        sequenceDetectButton = (Button) findViewById(R.id.sequenceDetect);
        sequenceTypeView = (TextView) findViewById(R.id.sequenceType);

        if (Utils.isTutorialPassed(this)) {
            setNormalMode();
            if (savedInstanceState == null) {
                String lastSequence = Utils.loadEnteredSequence(this);
                sequenceEditText.setText(lastSequence);
            }
        } else {
            setTutorialMode();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveEnteredString();
    }

    private void setTutorialMode() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        tutorialViewPager.setVisibility(View.VISIBLE);
        sequenceView.setVisibility(View.GONE);
        tutorialViewPager.setAdapter(new TutorialAdapter(getSupportFragmentManager()));
    }

    private void setNormalMode() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        analyzerPresenter = new AnalyzerPresenterImpl(this);
        tutorialViewPager.setVisibility(View.GONE);
        sequenceView.setVisibility(View.VISIBLE);
        sequenceDetectButton.setOnClickListener(v -> {
            String integerSequenceAsString = sequenceEditText.getText().toString();
            String[] integersAsStrings = integerSequenceAsString.split("(\\s+|\\s*,\\s*)");
            List<Integer> sequence = new ArrayList<>();
            boolean isIntegerSequence = true;
            for (String integerAsString : integersAsStrings) {
                if (integerAsString.isEmpty()) {
                    continue;
                }
                try {
                    Integer integer = Integer.parseInt(integerAsString);
                    sequence.add(integer);
                } catch (Exception e) {
                    setSequenceTypeToView(R.string.nonsequence);
                    isIntegerSequence = false;
                    break;
                }
            }
            if (sequence.isEmpty() || isIntegerSequence) {
                analyzerPresenter.onSequenceEntered(sequence);
            }
        });
    }

    private void setSequenceTypeToView(@StringRes int stringId) {
        sequenceTypeView.setText(stringId);
    }

    private void saveEnteredString() {
        Utils.saveEnteredSequence(this, sequenceEditText.getText().toString());
    }

    @Override
    public void onTutorialCompleted() {
        Utils.setTutorialPassed(this);
        setNormalMode();
    }

    @Override
    public void onSequenceTypeDetected(AnalyzerModel.SequenceType sequenceType) {
        setSequenceTypeToView(sequenceType.stringId);
        saveEnteredString();
    }
}
