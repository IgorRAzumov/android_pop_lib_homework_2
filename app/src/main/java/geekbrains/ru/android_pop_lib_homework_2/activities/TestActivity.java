package geekbrains.ru.android_pop_lib_homework_2.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import butterknife.BindView;
import geekbrains.ru.android_pop_lib_homework_2.R;
import geekbrains.ru.android_pop_lib_homework_2.presenters.TestPresenter;
import geekbrains.ru.android_pop_lib_homework_2.views.TestView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TestActivity extends MvpAppCompatActivity
        implements TestView {
    @InjectPresenter
    TestPresenter testPresenter;

    @BindView(R.id.et_main_activity)
    EditText editText;

    @BindView(R.id.tv_main_activity)
    TextView textView;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    private void initUi() {
        disposable = RxTextView.afterTextChangeEvents(editText)
                .skipInitialValue()
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        testPresenter.entryTextChange(textViewAfterTextChangeEvent.editable().toString());
                    }
                });
    }
}
