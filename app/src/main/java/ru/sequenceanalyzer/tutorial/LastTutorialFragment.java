package ru.sequenceanalyzer.tutorial;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.sequenceanalyzer.R;

//@SuppressWarnings("ConstantConditions")
public class LastTutorialFragment extends TutorialFragment {

    public static LastTutorialFragment newInstance(@DrawableRes int imageId, @StringRes int textId) {
        LastTutorialFragment lastTutorialFragment = new LastTutorialFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_ID_KEY, imageId);
        args.putInt(TEXT_ID_KEY, textId);
        lastTutorialFragment.setArguments(args);
        return lastTutorialFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Button tutorialButton = (Button) view.findViewById(R.id.tutorialButton);
        tutorialButton.setVisibility(View.VISIBLE);
        tutorialButton.setOnClickListener(v -> {
            if (getActivity() instanceof TutorialAdapter.OnCompleteTutorialListener) {
                ((TutorialAdapter.OnCompleteTutorialListener) getActivity()).onTutorialCompleted();
            }
        });

        return view;
    }
}
