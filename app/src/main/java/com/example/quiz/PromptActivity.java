package com.example.quiz;

import static com.example.quiz.MainActivity.REQUEST_CODE_PROMPT;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_ANSWER_SHOWN="com.example.quiz.answerShown";
    private boolean correctAnswer;
    public Button showCorrectAnswerButton;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        showCorrectAnswerButton=findViewById(R.id.zobacz_odp_button);
        answerTextView=findViewById(R.id.answer_text_view);


        correctAnswer=getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);


        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener()
           {
             public void onClick(View v)
             {
                 int answer = correctAnswer ? R.string.button_true :R.string.button_false;
                 answerTextView.setText(answer);
                 setAnswerShownResult(true);
             }
           }
        );
    }
    private void setAnswerShownResult(boolean answerWasShown)
    {
        Intent resultIntent=new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }
}