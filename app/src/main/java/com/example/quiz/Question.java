package com.example.quiz;

public class Question
{
    private int questionId;
    private boolean trueAnswer;

    public Question(int questionID,boolean trueAnswer)
    {
        this.questionId=questionID;
        this.trueAnswer=trueAnswer;
    }

    public boolean isTrueAnswer()
    {
        if(trueAnswer==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getQuestionId()
    {
        return(questionId);
    }
}
