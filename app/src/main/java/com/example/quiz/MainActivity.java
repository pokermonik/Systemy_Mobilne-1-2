package com.example.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private Question[] questions= new Question[]
            {
                    new Question(R.string.q_anime,true),
                    new Question(R.string.q_drake,false),
                    new Question(R.string.q_film,true),
                    new Question(R.string.q_rzeka,true),
                    new Question(R.string.q_polska,false)
            };
    private int currentQuestion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
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

        nextButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                currentQuestion=(currentQuestion+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer=questions[currentQuestion].isTrueAnswer();
        int resultMessageId=0;
        if(userAnswer==correctAnswer)
        {
            resultMessageId=R.string.correct_answer;
        }
        else
        {
            resultMessageId=R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion()
    {
        questionTextView.setText(questions[currentQuestion].getQuestionId());
    }


}