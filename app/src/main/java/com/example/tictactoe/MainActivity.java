package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //player representation
//        0 = x
//        1 = o

    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPosition = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };
    //0 for x
    //1 for 0
    //2 for null or blank


    public void constrainTap(View view){
        TextView result = findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);
    }

    public void playerTap(View view) {
        TextView result = findViewById(R.id.result);
        if(result.isShown()){
            result.setVisibility(View.INVISIBLE);
        }
        //checking if all slots are full or not
        int counter = 0;
        for (int i : gameState) {
            if (i == 2) {
                counter++;
            }
        }
        if (counter == 0) {
            gameReset();
        }

        ImageView img = (ImageView) view;
        TextView status = findViewById(R.id.status);
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameActive) {
            if (gameState[tappedImage] == 2) {
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    gameState[tappedImage] = 0;
                    activePlayer = 1;
                    status.setText("o's Turn");
                    checkWinState(0);
                } else if (activePlayer == 1) {
                    img.setImageResource(R.drawable.o);
                    gameState[tappedImage] = 1;
                    activePlayer = 0;
                    status.setText("x's Turn");
                    checkWinState(1);
                }
            }
        } else {
            gameReset();
        }
    }

    public void gameReset() {
        gameActive = true;
        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;

        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("Game Status Here");
    }

    public void checkWinState(int player) {


        for (int[] ints : winPosition) {
            if (player == 0) {
                if (gameState[ints[0]] == 0 && gameState[ints[1]] == 0 && gameState[ints[2]] == 0) {
                    TextView result = findViewById(R.id.result);
                    result.setVisibility(View.VISIBLE);
                    result.setText("X Wins");
                    gameActive = false;
                }
            } else if (player == 1) {
                if (gameState[ints[0]] == 1 && gameState[ints[1]] == 1 && gameState[ints[2]] == 1) {
                    TextView result = findViewById(R.id.result);
                    result.setVisibility(View.VISIBLE);
                    result.setText("O Wins");
                    gameActive = false;
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

