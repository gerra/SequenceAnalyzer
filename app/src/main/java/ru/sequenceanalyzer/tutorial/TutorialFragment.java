package ru.sequenceanalyzer.tutorial;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.sequenceanalyzer.R;

public class TutorialFragment extends Fragment {

    protected static final String IMAGE_ID_KEY = "IMAGE_ID";
    protected static final String TEXT_ID_KEY  = "TEXT_ID";

    public static TutorialFragment newInstance(@DrawableRes int imageId, @StringRes int textId) {
        TutorialFragment tutorialFragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_ID_KEY, imageId);
        args.putInt(TEXT_ID_KEY, textId);
        tutorialFragment.setArguments(args);
        return tutorialFragment;
    }

    protected @DrawableRes int imageId;
    protected @StringRes   int textId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(IMAGE_ID_KEY)) {
                imageId = args.getInt(IMAGE_ID_KEY);
            }
            if (args.containsKey(TEXT_ID_KEY)) {
                textId = args.getInt(TEXT_ID_KEY);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        ImageView tutorialImage = (ImageView) view.findViewById(R.id.tutorialImage);
        if (imageId != 0) {
            //noinspection deprecation
            tutorialImage.setImageDrawable(getResources().getDrawable(imageId));
        }

        TextView tutorialText = (TextView) view.findViewById(R.id.tutorialText);
        if (textId != 0) {
            tutorialText.setText(textId);
        }

        return view;
    }
}
