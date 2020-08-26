package com.example.apiconversion;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.apiconversion.DatabaseHelper.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteActivity.this, AddNoteActivity.class));
            }
        });
        helper = new DatabaseHelper(this);
        listView = (ListView)findViewById(R.id.list_notes);
        listView.setOnItemClickListener(this);
    }


    public void setListView(){
        Cursor cursor = helper.allData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 1);
        listView.setAdapter(customCursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(R.id.listID);
        final long id = Long.parseLong(getId.getText().toString());
        Cursor cur = helper.oneData(id);
        cur.moveToFirst();

        Intent idnotes = new Intent(NoteActivity.this, EditNoteActivity.class);
        idnotes.putExtra(DatabaseHelper.row_id, id);
        startActivity(idnotes);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setListView();
    }
}