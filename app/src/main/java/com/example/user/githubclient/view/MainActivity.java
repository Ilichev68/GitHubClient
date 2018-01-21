package com.example.user.githubclient.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.githubclient.R;
import com.example.user.githubclient.presenter.interfaces.IMainActivityPresenter;
import com.example.user.githubclient.presenter.MainActivityPresenter;
import com.example.user.githubclient.view.interfaces.IMainActivityView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivityView, View.OnFocusChangeListener {

    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.sign_in)
    Button button;
    @BindView(R.id.login_layout)
    TextInputLayout usernameLayout;
    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;
    private View view;

    private IMainActivityPresenter mainActivityPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initialize(this);

        mainActivityPresenter.chekUserInSystem(this);

        login.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);

    }

    @OnClick(R.id.sign_in)
    void click(View view) {
        mainActivityPresenter.auth(login.getText().toString(), password.getText().toString(), this);
        this.view = view;
        view.setEnabled(false);
    }


    @Override
    public void showErrorSnackbar() {
        Snackbar.make(view, getResources().getString(R.string.incorrect_login_or_password), Snackbar.LENGTH_LONG).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        });
    }

    @Override
    public void showInternetConnectionError() {
        Snackbar.make(view, getResources().getString(R.string.internet_connection_problem), Snackbar.LENGTH_LONG).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        });

    }

    private void initialize(IMainActivityView iMainActivity) {
        mainActivityPresenter = new MainActivityPresenter(iMainActivity);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view != login && login.getText().toString().isEmpty()) {
            usernameLayout.setErrorEnabled(true);
            usernameLayout.setError(getResources().getString(R.string.login_error));
        } else {
            usernameLayout.setErrorEnabled(false);
        }
    }
}
