package ru.sequenceanalyzer.tutorial;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.sequenceanalyzer.R;

public class TutorialAdapter extends FragmentPagerAdapter {

    /**
     * Use enum for simple adding elements
     */
    private enum TutorialPageInfo {
        FIRST(R.drawable.tutorial1, R.string.tutorial1),
        SECOND(R.drawable.tutorial1, R.string.tutorial2),
        THIRD(R.drawable.tutorial1, R.string.tutorial3),
        FOURTH(R.drawable.tutorial1, R.string.tutorial4);

        @DrawableRes int imageId;
        @StringRes   int textId;

        TutorialPageInfo(int imageId, int textId) {
            this.imageId = imageId;
            this.textId = textId;
        }
    }

    public interface OnCompleteTutorialListener {
        void onTutorialCompleted();
    }

    public TutorialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TutorialPageInfo tutorialPageInfo = TutorialPageInfo.values()[position];
        if (position == getCount() - 1) {
            return LastTutorialFragment.newInstance(tutorialPageInfo.imageId, tutorialPageInfo.textId);
        } else {
            return TutorialFragment.newInstance(tutorialPageInfo.imageId, tutorialPageInfo.textId);
        }
    }

    @Override
    public int getCount() {
        return TutorialPageInfo.values().length;
    }
}
