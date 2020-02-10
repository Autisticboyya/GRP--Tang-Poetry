package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;

import java.util.List;
import static com.example.myapplication.PoemList.instances;

public class poemsPage extends AppCompatActivity {
    private PoemDao poemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poems);

        DaoSession daoSession = instances.getDaoSession();
        poemDao = daoSession.getPoemDao();






        /**
         * goto interface
         */
        Button btn_poem = (Button)findViewById(R.id.poem);
        btn_poem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(poemsPage.this,poemPage.class);
                startActivity(intent);
            }
        });
    }

    /**
     * back_to_previous to previous interface
     */
    public void back_to_main(View view) {
        poemsPage.this.finish();
    }

    /**
     * add one element into database
     */
    public void insert(){
        Poem poem = new Poem(null,"静夜思","李白");
        poemDao.insert(poem);
    }

    /**
     * search for elements satisfy the condition
     */
    public void search(){
        List<Poem> list = poemDao.queryBuilder().where(PoemDao.Properties.Id.between(2,15)).limit(5).build().list();
        for(int i = 0; i < list.size();i++){
            Log.d("1111","search: " + list.get(i).toString());
        }

    }

    /**
     * delete elements in the database
     */
    public void delete(){
        List<Poem> poemList = (List<Poem>) poemDao.queryBuilder().where(PoemDao.Properties.Id.le(10)).build().list();
        for (Poem poem : poemList) {
            poemDao.delete(poem);
        }
    }

    /**
     * delete all the elements in the database
     */
    public void deleteAll(){
        poemDao.deleteAll();
    }

    /**
     * show all the ekements in the database
     */
    public void show(){
        poemDao.loadAll();
    }
}
