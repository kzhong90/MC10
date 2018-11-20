package com.kzhong.mc10.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kzhong.mc10.R;
import com.kzhong.mc10.intefaces.LogInInView;
import com.kzhong.mc10.model.UserAccount;

public class LogInActivity extends AppCompatActivity implements LogInInView {

    private LogInPresenter presenter;

    private EditText usernameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        presenter = new LogInPresenter(this);
        usernameText = findViewById(R.id.usernameEditText);
        passwordText = findViewById(R.id.passwordEditText);
    }

    @Override
    public void onResponseSuccess(UserAccount account) {
        Intent intent = new Intent(this, StudiesListActivity.class);

        intent.putExtra("id", account.getUser().getId());
        intent.putExtra("accountId", account.getUser().getAccountId());
        intent.putExtra("accessToken", account.getAccessToken());

        startActivity(intent);
    }

    @Override
    public void onResponseFailure(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("LogIn Error");
        builder.setMessage(error);
        builder.setPositiveButton("Ok", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onLogInClicked(View v) {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        presenter.onButtonClicked(username, password);
    }
}
