package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.myapplication.poemPage.EnglishVersion_pp;
import static com.example.myapplication.poemPage.chineseVersion_pp;
import static com.example.myapplication.poemPage.poemName_pp;
import static com.example.myapplication.poemPage.authorName_pp;

public class poemsPage extends AppCompatActivity {
    private PoemDao poemDao;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    public static List<Poem> Poems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poems);

        DaoSession daoSession = PoemList.getDaoSession();
        poemDao = daoSession.getPoemDao();

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

        Poems = poemDao.loadAll();

        Button btn_show = (Button)findViewById(R.id.orderByPoem);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOrderByPoem(Poems);
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
            }
        });

        Button btn_showAll = (Button)findViewById(R.id.orderByAuthor);
        btn_showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poems = poemDao.loadAll();
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
            }
        });


    }

    private void doOrderByPoem(List<Poem> poems) {
        Poems = poemDao.queryBuilder().orderAsc(PoemDao.Properties.AuthorName).list();
    }

    protected void initData() {
        poemDao.deleteAll();
        readFromFile();
    }

    private void readFromFile() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("c.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Poem poem = new Poem(null,strArr[0],strArr[1],strArr[2],strArr[3]);
                    poemDao.insert(poem);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        @NonNull
        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(poemsPage.this).
                    inflate(R.layout.recycler_view_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, final int position) {
            holder.authorName.setText(Poems.get(position).getAuthorName());
            holder.poemName.setText(Poems.get(position).getPoemName());
            holder.btn_go_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(poemsPage.this,poemPage.class);
                    startActivity(intent);
                    poemName_pp = Poems.get(position).getPoemName();
                    authorName_pp = Poems.get(position).getAuthorName();
                    chineseVersion_pp = Poems.get(position).getChineseVersion();
                    EnglishVersion_pp = Poems.get(position).getEnglishVersion();
                }
            });
        }

        @Override
        public int getItemCount() {
            return Poems.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView authorName;
            TextView poemName;
            Button btn_go_in;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                authorName = (TextView)itemView.findViewById(R.id.authorName1);
                poemName = (TextView)itemView.findViewById(R.id.poemName1);
                btn_go_in = (Button)itemView.findViewById(R.id.go_in1);
            }
        }
    }

    public void back_to_main(View view) {
        poemsPage.this.finish();
    }

}

