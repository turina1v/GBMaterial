package com.turina1v.gbmaterial.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.turina1v.gbmaterial.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInputActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "com.turina1v.gbmaterial.EXTRA_USERNAME";
    public static final String EXTRA_EMAIL = "com.turina1v.gbmaterial.EXTRA_EMAIL";

    @BindView(R.id.username_edittext)
    EditText usernameEditText;
    @BindView(R.id.email_textinput_layout)
    TextInputLayout emailTextInputLayout;
    @BindView(R.id.email_edittext)
    EditText emailEditText;
    @BindView(R.id.button_confirm)
    Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        ButterKnife.bind(this);
        initTextInputControl();
    }

    private void initTextInputControl() {
        confirmButton.setOnClickListener(v -> {
            confirmButton.requestFocus();
            String usernameInput = usernameEditText.getText().toString();
            String emailInput = emailEditText.getText().toString();
            if (usernameInput.length() > 0 && usernameInput.length() <= 20 && isEmailValid(emailInput)) {
                Intent intent = new Intent(UserInputActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_USERNAME, usernameInput);
                intent.putExtra(EXTRA_EMAIL, emailInput);
                startActivity(intent);
            } else if (emailInput.length() > 0 && !isEmailValid(emailInput)) {
                emailTextInputLayout.setError(getString(R.string.error_invalid_email));
                emailTextInputLayout.setErrorEnabled(true);
            } else if (usernameInput.length() == 0 || emailInput.length() == 0) {
                // SnackBar
            }
        });

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (emailTextInputLayout.isErrorEnabled()) {
                    emailTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
        return (email != null && email.matches(emailPattern));
    }
}
