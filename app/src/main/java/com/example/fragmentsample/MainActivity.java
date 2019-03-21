package com.example.fragmentsample;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int contentFrameId = R.id.contentFrame;
    private int position = 0;
    private Button prevButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getFragmentInstance(position);
        setFragment(fragment);

        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrev();
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();
            }
        });

        View view = findViewById(R.id.contentFrame);
        new OnFlickListener(view) {
            @Override
            public void onFlick(int type) {
                switch (type) {
                    case OnFlickListener.FLICK_LEFT:
                        goNext();
                        break;
                    case OnFlickListener.FLICK_RIGHT:
                        goPrev();
                        break;
                }
            }
        };
    }

    private void goPrev() {
        Fragment fragment = getFragmentInstance(position - 1);
        if (fragment == null) {
            Toast.makeText(this, "Can't go Prev", Toast.LENGTH_SHORT).show();
            return;
        }
        position--;
        setFragment(fragment);
    }

    private void goNext() {
        Fragment fragment = getFragmentInstance(position + 1);
        if (fragment == null) {
            Toast.makeText(this, "Can't go Next", Toast.LENGTH_SHORT).show();
            return;
        }
        position++;
        setFragment(fragment);
    }

    private Fragment getFragmentInstance(int nextPosition) {
        switch (nextPosition) {
            case 0:
                return Fragment0.newInstance();
            case 1:
                return Fragment1.newInstance();
            case 2:
                return Fragment2.newInstance();
            case 3:
                return Fragment3.newInstance();
            default:
                return null;
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (isContentFrameNull(manager)) {
            transaction.add(contentFrameId, fragment);
        } else {
            transaction.replace(contentFrameId, fragment);
        }

        transaction.commit();
    }

    private boolean isContentFrameNull(@NonNull FragmentManager manager) {
        Fragment fragment = manager.findFragmentById(contentFrameId);
        return (fragment == null);
    }
}
