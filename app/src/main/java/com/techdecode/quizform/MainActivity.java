package com.techdecode.quizform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv_Question;
    Button b_answer1,b_answer2,b_answer3,b_answer4;
    List<QuestionItem> questionItems;
    int currentQuestion=0;
    int correct=0;
    int wrong=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Question=findViewById(R.id.Question);
        b_answer1=findViewById(R.id.answer1);
        b_answer2=findViewById(R.id.answer2);
        b_answer3=findViewById(R.id.answer3);
        b_answer4=findViewById(R.id.answer4);
        //get all question
        loadallquestions();
        //shuffle all question if you want
        Collections.shuffle(questionItems);
        //load first question
        set_QuestionScreen(currentQuestion);


        b_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionItems.get(currentQuestion).getAnswer1().equals
                        (questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"correct!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"wrong! correct answer" +
                                    questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion <questionItems.size()-1){
                    currentQuestion++;
                    set_QuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("Wrong",wrong);
                    startActivity(intent);
                    finish();

                }

            }
        });
        b_answer2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the answer is correct
                if(questionItems.get(currentQuestion).getAnswer2().equals
                        (questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"correct!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"wrong! correct answer" +
                            questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion <questionItems.size()-1){
                    currentQuestion++;
                    set_QuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("Wrong",wrong);
                    startActivity(intent);
                    finish();

                }


            }
        });
        b_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionItems.get(currentQuestion).getAnswer3().equals
                        (questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"correct!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"wrong! correct answer" +
                            questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion <questionItems.size()-1){
                    currentQuestion++;
                    set_QuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("Wrong",wrong);
                    startActivity(intent);
                    finish();

                }


            }
        });
        b_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionItems.get(currentQuestion).getAnswer4().equals
                        (questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"correct!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"wrong! correct answer" +
                            questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion <questionItems.size()-1){
                    currentQuestion++;
                    set_QuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("Wrong",wrong);
                    startActivity(intent);
                    finish();


                }


            }
        });
    }
    //set question to screen
    private void set_QuestionScreen(int number){
        tv_Question.setText(questionItems.get(number).getQuestion());
        b_answer1.setText(questionItems.get(number).getAnswer1());
        b_answer2.setText(questionItems.get(number).getAnswer2());
        b_answer3.setText(questionItems.get(number).getAnswer3());
        b_answer4.setText(questionItems.get(number).getAnswer4());



    }

    //make list with all question
    private void loadallquestions(){
        questionItems=new ArrayList<>();
        //load all question into json string
        String jsonstr=loadjsonFormAsset("question quiz app.json");
        //load all data into the list
        try{
            JSONObject jsonobj=new JSONObject(jsonstr);
            JSONArray questions=jsonobj.getJSONArray("Questions");
            for(int i=0;i<questions.length();i++){
                JSONObject question=questions.getJSONObject(i);
                String questionString=question.getString("Question");
                String answer1String=question.getString("Answer1");
                String answer2String=question.getString("Answer2");
                String answer3String=question.getString("Answer3");
                String answer4String=question.getString("Answer4");
                String correctString=question.getString("correct");
                questionItems.add(new QuestionItem(questionString,answer1String,answer2String,answer3String,answer4String,correctString));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    //load the jason file from asets
    private String loadjsonFormAsset(String file) {
        String jason = "";
        try {
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            jason=new String(buffer,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }
        return jason;


    }

}