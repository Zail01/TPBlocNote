package com.zail.tpblocnote;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Button for bold police
    private Button boldButton = null;
    //Button for italic police
    private Button italicButton = null;
    //Button for highlight police
    private Button underButton = null;

    private SmileyGetter getter = null;
    //Smiley smile
    private ImageButton smile = null;
    //Smiley laught
    private ImageButton laught = null;
    //Smiley wink
    private ImageButton wink = null;
    //Buttons for police color
    private RadioGroup colors = null;
    //Edit text
    private EditText editNote = null;
    //Final text
    private TextView note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getter = new SmileyGetter(this);

        editNote = (EditText)findViewById(R.id.editText);
        note = (TextView)findViewById(R.id.previewText);

        boldButton = (Button)findViewById(R.id.boldButton);
        boldButton.setOnClickListener(this);

        italicButton = (Button)findViewById(R.id.italicButton);
        italicButton.setOnClickListener(this);

        underButton = (Button)findViewById(R.id.underButton);
        underButton.setOnClickListener(this);

        smile = (ImageButton)findViewById(R.id.smileButton);
        smile.setOnClickListener(this);

        laught = (ImageButton)findViewById(R.id.laughtButton);
        laught.setOnClickListener(this);

        wink = (ImageButton)findViewById(R.id.winkButton);
        wink.setOnClickListener(this);

        colors = (RadioGroup)findViewById(R.id.colorPolice);
        //Listener sur les changements de couleurs
        colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                int startSelection = editNote.getSelectionStart();
                int endSelection = editNote.getSelectionEnd();

                Editable editable = editNote.getText();

                switch(checkedId){
                    case R.id.blackPolice:
                        //Si il n'y a rien de selectionné,
                        //On ajoute les balises en fin de chaine
                        if(startSelection==endSelection){
                            editable.insert(startSelection, "<font color=\"#000000\"></font>");
                        } else {
                            editable.insert(endSelection, "</font>");
                            editable.insert(startSelection, "<font color=\"#000000\">");
                        }
                        break;
                    case R.id.bluePolice:
                        //Si il n'y a rien de selectionné,
                        //On ajoute les balises en fin de chaine
                        if(startSelection==endSelection){
                            editable.insert(startSelection, "<font color=\"#0000FF\"></font>");
                        } else {
                            editable.insert(endSelection, "</font>");
                            editable.insert(startSelection, "<font color=\"#0000FF\">");
                        }
                        break;
                    case R.id.redPolice:
                        //Si il n'y a rien de selectionné,
                        //On ajoute les balises en fin de chaine
                        if(startSelection==endSelection){
                            editable.insert(startSelection, "<font color=\"#FF0000\"></font>");
                        } else {
                            editable.insert(endSelection, "</font>");
                            editable.insert(startSelection, "<font color=\"#FF0000\">");
                        }
                        break;
                }
            }
        });

        //TODO : doesn't work... WHY ??
        editNote.setOnKeyListener(new EditText.OnKeyListener(){
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event){
                int cursorIndex = editNote.getSelectionStart();

                //S'il s'agit d'un appui sur une touche et que c'est le retour à la ligne
                //editNote.getText().insert(cursorIndex, "."+keyCode+".");
                if(event.getAction() == 0 && keyCode == 66){
                    editNote.getText().insert(cursorIndex, "<br />");
                }
                return true;
            }
        });
        editNote.addTextChangedListener(textWatcher);
    }

    public void onClick(View v){
        int startSelection = editNote.getSelectionStart();
        int endSelection = editNote.getSelectionEnd();

        Editable editable = editNote.getText();

        switch (v.getId()){
            case R.id.boldButton:
                //Si il n'y a rien de selectionné,
                //On ajoute les balises en fin de chaine
                if(startSelection==endSelection){
                    editable.insert(startSelection, "<b></b>");
                } else {
                    editable.insert(endSelection, "</b>");
                    editable.insert(startSelection, "<b>");
                }
                break;
            case R.id.italicButton:
                //Si il n'y a rien de selectionné,
                //On ajoute les balises en fin de chaine
                if(startSelection==endSelection){
                    editable.insert(startSelection, "<i></i>");
                } else {
                    editable.insert(endSelection, "</i>");
                    editable.insert(startSelection, "<i>");
                }
                break;
            case R.id.underButton:
                //Si il n'y a rien de selectionné,
                //On ajoute les balises en fin de chaine
                if(startSelection==endSelection){
                    editable.insert(startSelection, "<u></u>");
                } else {
                    editable.insert(endSelection, "</u>");
                    editable.insert(startSelection, "<u>");
                }
                break;
            case R.id.smileButton:
                editable.insert(startSelection, "<img src=\"smile\" >");
                break;
            case R.id.laughtButton:
                editable.insert(startSelection, "<img src=\"laught\" >");
                break;
            case R.id.winkButton:
                editable.insert(startSelection, "<img src=\"wink\" >");
                break;
        }
    }

    private TextWatcher textWatcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            note.setText(Html.fromHtml(editNote.getText().toString(), getter, null));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
