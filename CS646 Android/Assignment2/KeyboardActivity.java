package edu.sdsu.cs.cs646.assignment2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class KeyboardActivity extends AppCompatActivity {

    private EditText mTopEditText;
    private EditText mMiddleEditText;
    private EditText mBottomEditText;
    private Button mHideButton;
    private Button mBackButton;
    private String passedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        mTopEditText = (EditText)findViewById(R.id.editTextKey1);
        mMiddleEditText = (EditText)findViewById(R.id.editTextKey2);
        mBottomEditText = (EditText)findViewById(R.id.editTextKey3);
        mHideButton = (Button)findViewById((R.id.hide_button));
        mBackButton = (Button)findViewById(R.id.back_button);

        mHideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Bundle mainActivityData = getIntent().getExtras();
        passedEditText = mainActivityData.getString("topTextField");
        mTopEditText.setText(passedEditText);

    }
    public void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
