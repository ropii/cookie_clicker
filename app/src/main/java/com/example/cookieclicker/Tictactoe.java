package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Tictactoe extends AppCompatActivity implements View.OnClickListener {

    Button btnNewGame;

    ImageButton[][] ivBtnMat = new ImageButton[3][3];
    int[][] isClicked = new int[3][3];

    boolean stop = false;

    GridLayout gridEt;

    TableLayout gameTable;

    EditText etNameX, etNameO;

    TextView tv1;

    int turn = 0, isGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        ivBtnMat[0][0] = findViewById(R.id.btn00);
        ivBtnMat[0][0].setOnClickListener(this);
        ivBtnMat[0][1] = findViewById(R.id.btn01);
        ivBtnMat[0][1].setOnClickListener(this);

        ivBtnMat[0][2] = findViewById(R.id.btn02);
        ivBtnMat[0][2].setOnClickListener(this);

        ivBtnMat[1][0] = findViewById(R.id.btn10);
        ivBtnMat[1][0].setOnClickListener(this);

        ivBtnMat[1][1] = findViewById(R.id.btn11);
        ivBtnMat[1][1].setOnClickListener(this);

        ivBtnMat[1][2] = findViewById(R.id.btn12);
        ivBtnMat[1][2].setOnClickListener(this);

        ivBtnMat[2][0] = findViewById(R.id.btn20);
        ivBtnMat[2][0].setOnClickListener(this);

        ivBtnMat[2][1] = findViewById(R.id.btn21);
        ivBtnMat[2][1].setOnClickListener(this);

        ivBtnMat[2][2] = findViewById(R.id.btn22);
        ivBtnMat[2][2].setOnClickListener(this);

        gridEt = findViewById(R.id.gridEt);

        btnNewGame = findViewById(R.id.btnNewGame);

        etNameX = findViewById(R.id.etNameX);

        etNameO = findViewById(R.id.etNameO);

        tv1 = findViewById(R.id.tv1);

        gameTable = findViewById(R.id.gameTable);
        gameTable.setVisibility(View.INVISIBLE);
    }

    public void game(View view) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                isClicked[i][j] = 0;
                ivBtnMat[i][j].setBackgroundColor(Color.TRANSPARENT);
            }
        }
        stop = false;
        isGame++;

        if (isGame % 2 != 0) { //נבדוק אם אנחנו במשחק או לא
            if (etNameX.getText().toString().equals("") || etNameO.getText().toString().equals("")) { //במקרה שהמשתמש לא הכניס את כל השדות המתאימים
                Toast.makeText(this, "אנא הכנס הכל", Toast.LENGTH_SHORT).show();
                isGame--;
            } else {
                gridEt.setVisibility(View.INVISIBLE);
                gameTable.setVisibility(View.VISIBLE);
                turn = (int) (Math.random() * 2); //נגריל את התור
                if (turn % 2 == 0) {
                    tv1.setText(" תורו של " + etNameO.getText().toString());
                } else {
                    tv1.setText(" תורו של " + etNameX.getText().toString());
                }
            }

        } else {
            gridEt.setVisibility(View.VISIBLE);
            gameTable.setVisibility(View.INVISIBLE);
            tv1.setText("הכניסו את שמכם:");
        }
    }

    @Override
    public void onClick(View view) {
        if (stop) { //אם המשחק כבר נגמר לא ניתן למשתמש לבחור עוד תא
            Toast.makeText(this, "המשחק נגמר", Toast.LENGTH_SHORT).show();
        } else { //אחרת
            if (turn % 2 == 0) { //נבדוק של מי התור
                tv1.setText(" תורו של " + etNameO.getText().toString());

                for (int i = 0; i < 3; i++) { //נעבור על כל המערך עד שנמצא את הכפתור המצאים ונעצור
                    for (int j = 0; j < 3; j++) {
                        if (view == ivBtnMat[i][j]) {
                            if (isClicked[i][j] != 0) {
                                Toast.makeText(this, "בחר מקום אחר", Toast.LENGTH_SHORT).show();
                            } else {
                                isClicked[i][j] = 2;
                                ivBtnMat[i][j].setBackgroundResource(R.drawable.o1);
                                turn++;
                                tv1.setText(" תורו של " + etNameX.getText().toString());
                            }
                            i = 3;
                            j = 3;
                        }
                    }
                }

                //כעת נבדוק מצבי ניצחון ותיקו:
                if (CheckRows(isClicked, 2)) {
                    tv1.setText(etNameO.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    int row = CheckRowsReturn(isClicked, 2);
                    ivBtnMat[row][0].setBackgroundResource(R.drawable.o2);
                    ivBtnMat[row][1].setBackgroundResource(R.drawable.o2);
                    ivBtnMat[row][2].setBackgroundResource(R.drawable.o2);
                } else if (CheckCols(isClicked, 2)) {
                    tv1.setText(etNameO.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    int col = CheckColsReturn(isClicked, 2);
                    ivBtnMat[0][col].setBackgroundResource(R.drawable.o2);
                    ivBtnMat[1][col].setBackgroundResource(R.drawable.o2);
                    ivBtnMat[2][col].setBackgroundResource(R.drawable.o2);
                } else if (CheckDiagonals(isClicked, 2)) {
                    tv1.setText(etNameO.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    if (CheckDiagonalsReturn(isClicked, 2) == 0) {
                        ivBtnMat[0][0].setBackgroundResource(R.drawable.o2);
                        ivBtnMat[1][1].setBackgroundResource(R.drawable.o2);
                        ivBtnMat[2][2].setBackgroundResource(R.drawable.o2);
                    } else {
                        ivBtnMat[2][0].setBackgroundResource(R.drawable.o2);
                        ivBtnMat[1][1].setBackgroundResource(R.drawable.o2);
                        ivBtnMat[0][2].setBackgroundResource(R.drawable.o2);
                    }
                } else if (fullMatrix(isClicked)) { //המשחק נגמר בתיקו
                    tv1.setText("תיקו!");
                    stop = true;
                }
            } else {
                tv1.setText(" תורו של " + etNameX.getText().toString());

                for (int i = 0; i < 3; i++) { //את אותו הדבר נעשה פה
                    for (int j = 0; j < 3; j++) {
                        if (view == ivBtnMat[i][j]) {
                            if (isClicked[i][j] != 0) {
                                Toast.makeText(this, "בחר מקום אחר", Toast.LENGTH_SHORT).show();
                            } else {
                                isClicked[i][j] = 1;
                                ivBtnMat[i][j].setBackgroundResource(R.drawable.x1);
                                turn++;
                                tv1.setText(" תורו של " + etNameO.getText().toString());
                            }
                            i = 3;
                            j = 3;
                        }
                    }
                }
                //גם פה נבדוק מצבי ניצחון ותיקו:
                if (CheckRows(isClicked, 1)) {
                    tv1.setText(etNameX.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    int row = CheckRowsReturn(isClicked, 1);
                    ivBtnMat[row][0].setBackgroundResource(R.drawable.x2);
                    ivBtnMat[row][1].setBackgroundResource(R.drawable.x2);
                    ivBtnMat[row][2].setBackgroundResource(R.drawable.x2);
                } else if (CheckCols(isClicked, 1)) {
                    tv1.setText(etNameX.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    int col = CheckColsReturn(isClicked, 1);
                    ivBtnMat[0][col].setBackgroundResource(R.drawable.x2);
                    ivBtnMat[1][col].setBackgroundResource(R.drawable.x2);
                    ivBtnMat[2][col].setBackgroundResource(R.drawable.x2);
                } else if (CheckDiagonals(isClicked, 1)) {
                    tv1.setText(etNameX.getText().toString() + " ניצח את המשחק!");
                    stop = true;

                    if (CheckDiagonalsReturn(isClicked, 1) == 0) {
                        ivBtnMat[0][0].setBackgroundResource(R.drawable.x2);
                        ivBtnMat[1][1].setBackgroundResource(R.drawable.x2);
                        ivBtnMat[2][2].setBackgroundResource(R.drawable.x2);
                    } else {
                        ivBtnMat[2][0].setBackgroundResource(R.drawable.x2);
                        ivBtnMat[1][1].setBackgroundResource(R.drawable.x2);
                        ivBtnMat[0][2].setBackgroundResource(R.drawable.x2);
                    }
                } else if (fullMatrix(isClicked)) {
                    tv1.setText("תיקו!");
                    stop = true;
                }
            }
        }
    }

    //הפעולה מהקבלת מטריצה של המספרים שלמים ובודקת אם במערך אין אפס
    public boolean fullMatrix(int[][] isClicked) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isClicked[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //הפעולה מקבלת מטריצה של מספרים שלמים וערך לבדיקה (1 או 2) ובודקת האם קיים מצב ניצחון לפי השורות במטריצה
    public boolean CheckRows(int[][] isClicked, int check) {
        for (int i = 0; i < 3; i++) {
            if (isClicked[i][0] == check && isClicked[i][1] == check && isClicked[i][2] == check) {
                return true;
            }
        }
        return false;
    }

    public int CheckRowsReturn(int[][] isClicked, int check) {
        for (int i = 0; i < 3; i++) {
            if (isClicked[i][0] == check && isClicked[i][1] == check && isClicked[i][2] == check) {
                return i;
            }
        }
        return -1;
    }

    //הפעולה מקבלת מטריצה של מספרים שלמים וערך לבדיקה (1 או 2) ובודקת האם קיים מצב ניצחון לפי העמודות במטריצה
    public boolean CheckCols(int[][] isClicked, int check) {
        for (int i = 0; i < 3; i++) {
            if (isClicked[0][i] == check && isClicked[1][i] == check && isClicked[2][i] == check) {
                return true;
            }
        }
        return false;
    }

    public int CheckColsReturn(int[][] isClicked, int check) {
        for (int i = 0; i < 3; i++) {
            if (isClicked[0][i] == check && isClicked[1][i] == check && isClicked[2][i] == check) {
                return i;
            }
        }
        return -1;
    }

    //הפעולה מקבלת מטריצה של מספרים שלמים וערך לבדיקה (1 או 2) ובודקת האם קיים מצב ניצחון לפי האלכסונים במטריצה
    public boolean CheckDiagonals(int[][] isClicked, int check) {
        if (isClicked[0][0] == check && isClicked[1][1] == check && isClicked[2][2] == check) {
            return true;
        }
        return isClicked[2][0] == check && isClicked[1][1] == check && isClicked[0][2] == check;
    }

    public int CheckDiagonalsReturn(int[][] isClicked, int check) {
        if (isClicked[0][0] == check && isClicked[1][1] == check && isClicked[2][2] == check) {
            return 0;
        }
        return 1;
    }

}