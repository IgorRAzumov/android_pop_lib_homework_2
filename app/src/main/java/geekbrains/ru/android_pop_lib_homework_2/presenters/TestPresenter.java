package geekbrains.ru.android_pop_lib_homework_2.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import geekbrains.ru.android_pop_lib_homework_2.activities.TestActivity;
import geekbrains.ru.android_pop_lib_homework_2.views.TestView;

@InjectViewState
public class TestPresenter extends MvpPresenter<TestView> {

    public void entryTextChange(CharSequence text) {
        getViewState().setText(text.toString());
    }
}
