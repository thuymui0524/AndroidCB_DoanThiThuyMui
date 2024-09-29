package com.example.gameactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private TextView tvStatus;
    private CardAdapter adapter;
    private List<Card> cardList;

    private Card firstCard = null;
    private Card secondCard = null;

    private int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridView = findViewById(R.id.gridView);
        tvStatus = findViewById(R.id.tvStatus);

        initializeCards();
        adapter = new CardAdapter(this, cardList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card clickedCard = cardList.get(position);
                if (clickedCard.isFlipped() || clickedCard.isMatched()) {
                    // Thẻ đã được lật hoặc đã trùng, không làm gì
                    return;
                }

                clickedCard.setFlipped(true);
                adapter.notifyDataSetChanged();

                if (firstCard == null) {
                    firstCard = clickedCard;
                } else {
                    secondCard = clickedCard;
                    attempts++;
                    tvStatus.setText("Attempts: " + attempts);

                    if (firstCard.getFrontImage() == secondCard.getFrontImage()) {
                        // Cặp thẻ trùng nhau
                        firstCard.setMatched(true);
                        secondCard.setMatched(true);
                        firstCard = null;
                        secondCard = null;

                        // Kiểm tra xem game đã kết thúc chưa
                        if (isGameOver()) {
                            showGameOverDialog();
                        }

                    } else {
                        // Cặp thẻ không trùng nhau, lật lại sau một khoảng thời gian
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                firstCard.setFlipped(false);
                                secondCard.setFlipped(false);
                                adapter.notifyDataSetChanged();
                                firstCard = null;
                                secondCard = null;
                            }
                        }, 1000); // 1 giây
                    }
                }
            }
        });
    }

    // Khởi tạo danh sách các thẻ bài
    private void initializeCards() {
        cardList = new ArrayList<>();

        // Ví dụ: 6 cặp bài (12 thẻ bài)
        int[] images = {
                R.drawable.card1,
                R.drawable.card2,
                R.drawable.card3,
                R.drawable.card4,
                R.drawable.card5,
                R.drawable.card6
        };

        // Thêm mỗi hình ảnh hai lần để tạo cặp
        for (int image : images) {
            cardList.add(new Card(cardList.size(), image));
            cardList.add(new Card(cardList.size(), image));
        }

        // Trộn danh sách thẻ bài
        Collections.shuffle(cardList);
    }

    // Kiểm tra xem tất cả các thẻ bài đã được tìm hết chưa
    private boolean isGameOver() {
        for (Card card : cardList) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    // Hiển thị thông báo khi hoàn thành game
    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chúc mừng!");
        builder.setMessage("Bạn đã hoàn thành trò chơi với " + attempts + " lần nỗ lực.");
        builder.setPositiveButton("Chơi lại", (dialog, which) -> {
            // Reset game
            resetGame();
        });
        builder.setNegativeButton("Thoát", (dialog, which) -> {
            finish();
        });
        builder.setCancelable(false);
        builder.show();
    }

    // Reset lại trò chơi
    private void resetGame() {
        attempts = 0;
        tvStatus.setText("Attempts: 0");
        firstCard = null;
        secondCard = null;
        initializeCards();
        adapter = new CardAdapter(this, cardList);
        gridView.setAdapter(adapter);
    }

}