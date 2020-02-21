package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        // cast al Layout SwipeRefresh con el que rodeamos la vista en
        // el xml y le colocamos un listener
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);
        // construimos el Listener que lanza un Toast y desactiva a
        // continuación del Swipe la animación

    }
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            /*Toast toast0 = Toast.makeText(RefreshActivity.this, "going swipeREFRESH", Toast.LENGTH_LONG);
            toast0.show();
            swipeLayout.setRefreshing(false);*/

            final RelativeLayout mrelativeLayout = (RelativeLayout) findViewById(R.id.activity_main_context);

            Snackbar snackbar = Snackbar
                    .make(mrelativeLayout, "fancy a Snack while you refresh?", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mrelativeLayout, "Action is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();
            swipeLayout.setRefreshing(false);
        }
    };
}
