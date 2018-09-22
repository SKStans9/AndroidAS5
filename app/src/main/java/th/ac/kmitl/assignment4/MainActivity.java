package th.ac.kmitl.assignment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt0,btDot,btEq,btPlus,btDe,btMul,btClear,btDel,btDi;
    TextView tv1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    int cou=2;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idOfItem = item.getItemId();
        switch (idOfItem){
            case R.id.menuItem1:
            {
                if(cou % 2 == 0){
                    setContentView(R.layout.activity_main2);
                    init();
                    tv1.setText(ShowText);
                }else{
                    setContentView(R.layout.activity_main);
                    init();
                    tv1.setText(ShowText);
                }
                cou++;
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }




    /////////////////////////////////////////////////////////////////////////////////////////////
private String ShowText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);;
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        bt0 = findViewById(R.id.bt0);
        btDot= findViewById(R.id.btDot);
        btEq= findViewById(R.id.btEq);
        btPlus= findViewById(R.id.btPlus);
        btDe= findViewById(R.id.btDe);
        btMul= findViewById(R.id.btMul);
        btClear= findViewById(R.id.btClear);
        btDel= findViewById(R.id.btDel);
        btDi= findViewById(R.id.btDi);
        tv1 =findViewById(R.id.tv1);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt0.setOnClickListener(this);
        btDot.setOnClickListener(this);
        btEq.setOnClickListener(this);
        btPlus.setOnClickListener(this);
        btDe.setOnClickListener(this);
        btMul.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btDel.setOnClickListener(this);
        btDi.setOnClickListener(this);
        tv1.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        double sum;
        if(v.getId()==R.id.bt1){
            tv1.setText(tv1.getText()+"1");
        }else if(v.getId()==R.id.bt2){
            tv1.setText(tv1.getText()+"2");
        }else if(v.getId()==R.id.bt3){
            tv1.setText(tv1.getText()+"3");
        }else if(v.getId()==R.id.bt4){
            tv1.setText(tv1.getText()+"4");
        }else if(v.getId()==R.id.bt5){
            tv1.setText(tv1.getText()+"5");
        }else if(v.getId()==R.id.bt6){
            tv1.setText(tv1.getText()+"6");
        }else if(v.getId()==R.id.bt7){
            tv1.setText(tv1.getText()+"7");
        }else if(v.getId()==R.id.bt8){
            tv1.setText(tv1.getText()+"8");
        }else if(v.getId()==R.id.bt9){
            tv1.setText(tv1.getText()+"9");
        }else if(v.getId()==R.id.bt0){
            tv1.setText(tv1.getText()+"0");
        }else if(v.getId()==R.id.btPlus){
            tv1.setText(tv1.getText()+"+");
        }else if(v.getId()==R.id.btDe){
            tv1.setText(tv1.getText()+"-");
        }else if(v.getId()==R.id.btMul){
            tv1.setText(tv1.getText()+"*");
        }else if(v.getId()==R.id.btDi){
            tv1.setText(tv1.getText()+"/");
        }else if(v.getId()==R.id.btEq){
            try {
                sum = (eval(tv1.getText().toString()));
                tv1.setText(String.valueOf(sum));
            }catch(Exception e){
                tv1.setText("Invalid Expression.");
            }
        }else if(v.getId()==R.id.btClear){
            tv1.setText(null);
        }
        else if(v.getId()==R.id.btDel){
            int num =  (tv1.getText()).length();
            if(num != 0) {
                tv1.setText(tv1.getText().subSequence(0, num - 1));
            }
            if(tv1.getText().toString().equals("Invalid Expression")){
                tv1.setText(null);
            }

        }else if(v.getId()==R.id.btDot){
            tv1.setText(tv1.getText()+".");
        }
        ShowText = (String)tv1.getText();

    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            // | number | functionName factor | factor `^` factor
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                return x;
            }
        }.parse();
    }
}
