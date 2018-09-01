package com.example.chirag.rgbdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener,AdapterView.OnItemSelectedListener,View.OnClickListener{
    RelativeLayout rl=null;
    SeekBar r=null,g=null,b=null;
    Integer red=null,green=null,blue=null;
    TextView lblR=null,lblG=null,lblB=null;
    Button btnBack=null;
    Spinner spin=null;
    String bg[]={"---Select BackColor---","Red","Green","Blue","Cyan","Megenta","Yellow","White","Black"};
    TextView lblshow=null;  //for context menu

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl=(RelativeLayout)findViewById(R.id.activity_main);

        r=(SeekBar)findViewById(R.id.sbrR);
        r.setOnSeekBarChangeListener(this);

        g=(SeekBar)findViewById(R.id.sbrG);
        g.setOnSeekBarChangeListener(this);

        b=(SeekBar)findViewById(R.id.sbrB);
        b.setOnSeekBarChangeListener(this);

        lblR=(TextView)findViewById(R.id.lblR);
        lblG=(TextView)findViewById(R.id.lblG);
        lblB=(TextView)findViewById(R.id.lblB);

        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        spin=(Spinner) findViewById(R.id.spinBg);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_item,bg);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        lblshow=(TextView)findViewById(R.id.showmenulbl);
        registerForContextMenu(lblshow);

        Toast t=Toast.makeText(this,"Mix your color",Toast.LENGTH_LONG);
        t.setGravity(Gravity.BOTTOM,0,0);
        t.show();
    }

    //to create menu in title bar
    public boolean onCreateOptionsMenu(Menu m){
        //this menu is in title bar
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.color_menu,m);
        return true;
    }

    //to handle click of menu in title bar
    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.menured:
                rl.setBackgroundColor(Color.rgb(255,0,0));
                setPro("255","0","0",255,0,0);
                break;
            case R.id.menugreen:
                rl.setBackgroundColor(Color.rgb(0,255,0));
                setPro("0","255","0",0,255,0);
                break;
            case R.id.menublue:
                rl.setBackgroundColor(Color.rgb(0,0,255));
                setPro("0","0","255",0,0,255);
                break;
            default:
                return super.onOptionsItemSelected(mi);
        }
        return true;
    }

    //to create floating menu on pressand hold on label
    @Override
    public void onCreateContextMenu(ContextMenu cm, View v, ContextMenu.ContextMenuInfo cmi ){
        super.onCreateContextMenu(cm,v,cmi);
        cm.setHeaderTitle("Select Color");
        cm.add(0,v.getId(),0,"Red");//groupId, itemId, order, title
        cm.add(0,v.getId(),0,"Green");
        cm.add(0,v.getId(),0,"Blue");
    }

    //to handle events of floating menu on pressand hold on label
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Red") {
            rl.setBackgroundColor(Color.rgb(255, 0, 0));
        } else if (item.getTitle() == "Green") {
            rl.setBackgroundColor(Color.rgb(0, 255, 0));
        } else if (item.getTitle() == "Blue") {
            rl.setBackgroundColor(Color.rgb(0, 0,255));
        }
        else{}
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean bool) {
        //String rc=seekBar.seek
        red = r.getProgress();
        green = g.getProgress();
        blue = b.getProgress();

        rl.setBackgroundColor(Color.rgb(red, green, blue));

        Integer colr=Color.rgb(red, green, blue);
        lblR.setText(red.toString());
        lblG.setText(green.toString());
        lblB.setText(blue.toString());

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onClick(View view) {

        //on click of button create popup menu
        PopupMenu pop = new PopupMenu(MainActivity.this, btnBack); //Creating the instance of PopupMenu
        pop.getMenuInflater().inflate(R.menu.color_menu, pop.getMenu());    //Inflating the Popup using xml file


        //to handle event of popup menu of on click of button
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menured){
                    rl.setBackgroundColor(Color.RED);
                }
                else if(menuItem.getItemId()==R.id.menugreen){
                    rl.setBackgroundColor(Color.GREEN);
                }
                else if(menuItem.getItemId()==R.id.menublue){
                    rl.setBackgroundColor(Color.BLUE);
                }
                else{}
                return true;
            }
        });
        pop.show();
    }

    //to handle dropdown
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String str=spin.getSelectedItem().toString();
        switch (str){

            case "---Select BackColor---":
                rl.setBackgroundColor(Color.rgb(255,255,255));
                break;
            case "Red":
                rl.setBackgroundColor(Color.rgb(255,0,0));
                setPro("255","0","0",255,0,0);
                break;
            case "Green":
                rl.setBackgroundColor(Color.rgb(0,255,0));
                setPro("0","255","0",0,255,0);
                break;
            case "Blue":
                rl.setBackgroundColor(Color.rgb(0,0,255));
                setPro("0","0","255",0,0,255);
                break;
            case "Cyan":
                rl.setBackgroundColor(Color.rgb(0,255,255));
                setPro("0","255","255",0,255,255);
                break;
            case "Megenta":
                rl.setBackgroundColor(Color.rgb(255,0,255));
                setPro("255","0","255",255,0,255);
                break;
            case "Yellow":
                rl.setBackgroundColor(Color.rgb(255,255,0));
                setPro("255","255","0",255,255,0);
                break;
            case "White":
                rl.setBackgroundColor(Color.rgb(255,255,255));
                setPro("255","255","255",255,255,255);
                break;
            case "Black":
                rl.setBackgroundColor(Color.rgb(0,0,0));
                setPro("0","0","0",0,0,0);
                break;
            default:
                rl.setBackgroundColor(Color.rgb(255,255,255));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Kai select karo bhai!!!!!", Toast.LENGTH_SHORT).show();
    }
    void setPro(String r1,String g1,String b1,int r2,int g2,int b2){
        lblR.setText(r1);r.setProgress(r2);
        lblG.setText(g1);g.setProgress(g2);
        lblB.setText(b1);b.setProgress(b2);
    }
}
