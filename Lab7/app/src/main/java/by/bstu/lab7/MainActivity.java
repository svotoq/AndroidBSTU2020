package by.bstu.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void init() {
        userList = (ListView) findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        db = databaseHelper.getReadableDatabase();

        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);

        String[] headers = new String[]{DatabaseHelper.COLUMN_MAIL};

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                userCursor, headers, new int[]{android.R.id.text1}, 0);

        userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {

                if (constraint == null || constraint.length() == 0) {

                    return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                } else {
                    return db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                            DatabaseHelper.COLUMN_MAIL + " like ?", new String[]{"%" + constraint.toString() + "%"});
                }
            }
        });

        userList.setAdapter(userAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    public void add(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        this.searchView = (SearchView) menu.findItem(R.id.menuItem_search).getActionView();

        this.searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // Need click "search" icon to expand SearchView.
        this.searchView.setIconifiedByDefault(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // Typing search text.
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                userAdapter.getFilter().filter(newText.toString());
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.favorite_users:
                new GetFavorite().execute();
                return true;
            case R.id.all_users:
                onResume();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class GetFavorite extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            userList.setAdapter(null);
            db = databaseHelper.getReadableDatabase();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_FAVORITE + "=?", new String[]{String.valueOf(1)});

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String[] headers = new String[]{DatabaseHelper.COLUMN_MAIL, DatabaseHelper.COLUMN_PHONE};

            userAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,
                    userCursor, headers, new int[]{android.R.id.text1}, 0);

            userList.setAdapter(userAdapter);
        }

    }

}