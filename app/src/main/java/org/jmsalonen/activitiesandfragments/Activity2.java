package org.jmsalonen.activitiesandfragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity implements
        FragmentA.FragmentAListener,
        FragmentB.FragmentBListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        setTitle("Activity 2");
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        Intent intent = getIntent();
        String mainActivityString = intent.getStringExtra("mainActivityText");

        Bundle bundle = new Bundle();
        bundle.putString("editText", mainActivityString);
        fragmentA.setArguments(bundle);
        fragmentB.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();


        Button buttonBack = findViewById(R.id.button_back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result",  result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }

    @Override
    public void onInputASent(String input) {
        result = input;
        fragmentB.updateEditText(input);
    }

    @Override
    public void onInputBSent(String input) {
        result = input;
        fragmentA.updateEditText(input);
    }

}
