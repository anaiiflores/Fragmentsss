package com.example.fragments2425;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private boolean estagirado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estagirado = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        textView1 = findViewById(R.id.text_view_1);
        textView2 = findViewById(R.id.text_view_2);

        if (estagirado) {
            Log.i("estirado", "nofuncionaaa");
            loadFragmentsSideBySide();
        } else {
            // Configurar listeners para los TextView en modo vertical
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFragment(new opcion1());
                }
            });

            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFragment(new opcion2());
                }
            });
        }
    }

    private void loadFragmentsSideBySide() {
        // Ocultar los TextViews de selección
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);

        // Cargar opcion1 en fragment_container_1
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.fragment_container_1, new opcion1());
        transaction1.commit();

        // Cargar opcion2 en fragment_container_2
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.fragment_container_2, new opcion2());
        transaction2.commit();
    }

    private void openFragment(Fragment fragment) {
        // Configuración para modo vertical: usar fragment_container y ocultar fragment_container_1 y fragment_container_2
        findViewById(R.id.fragment_container_1).setVisibility(View.GONE);
        findViewById(R.id.fragment_container_2).setVisibility(View.GONE);
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);

        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);

        // Cargar el fragmento en fragment_container
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
