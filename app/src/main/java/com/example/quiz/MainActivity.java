package com.example.quiz;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz.correctAnswer";
    public static final int REQUEST_CODE_PROMPT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button hintButton;

    boolean answerWasShown=false;
    private TextView questionTextView;
    private Question[] questions= new Question[]
            {
                    new Question(R.string.q_anime,true),
                    new Question(R.string.q_drake,false),
                    new Question(R.string.q_film,true),
                    new Question(R.string.q_rzeka,true),
                    new Question(R.string.q_polska,false)
            };
    private int currentIndex =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DDDDDDDDD","wywolane zostalo oncreate");
        if(savedInstanceState!=null)
        {
            currentIndex= savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        hintButton=findViewById(R.id.hint_button);
        questionTextView=findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                checkAnswerCorrectness(false);
            }
        });

        hintButton.setOnClickListener((View v)->
        {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer =questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
            startActivityForResult(intent,REQUEST_CODE_PROMPT);
        });

        nextButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                currentIndex =(currentIndex +1)%questions.length;
                answerWasShown=false;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer=questions[currentIndex].isTrueAnswer();
        int resultMessageId=0;
        if(answerWasShown)
        {
            resultMessageId=R.string.answer_was_shown;
        }
        else
        {
            if(userAnswer==correctAnswer)
            {
                resultMessageId=R.string.correct_answer;
            }
            else
            {
                resultMessageId=R.string.incorrect_answer;
            }

        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion()
    {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK)
        {
            return;
        }
        if(requestCode==REQUEST_CODE_PROMPT)
        {
            if(data==null)
            {
                return;

            }
            answerWasShown=data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DDDDDDDDD","Wywolal sie Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DDDDDDDDD","Wywolal sie Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DDDDDDDDD","Wywolal sie Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DDDDDDDDD","Wywolal sie Destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DDDDDDDDD","Wywolal sie Stop");
    }

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d("DDDDDDDDD","Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);

    }
}