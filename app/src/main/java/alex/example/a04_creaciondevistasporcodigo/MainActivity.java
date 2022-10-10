package alex.example.a04_creaciondevistasporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import alex.example.a04_creaciondevistasporcodigo.databinding.ActivityMainBinding;
import alex.example.a04_creaciondevistasporcodigo.modeos.AlumnoModel;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // Necesito 3 cosas para mostrar un conjunto de datos en una actividad:
    //1. Un elemento para mostrar informacion
    //2. El conjunto de datos (Arraylist<AlumnoModel>)
    //3. Un contenedor donde meter cada uno de los elemento (LinearLayout(Vertical)-> ScrollView).
    //4. Logica para poner los elementos

    //1.
    private ArrayList<AlumnoModel> alumnoModelArrayList;
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addAlumnoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    // Asociacion de una clase en una actividad en el xml

        alumnoModelArrayList = new ArrayList<>();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializarlaucher();
        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlumnoLauncher.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));
            }
        });
    }

    private void inicializarlaucher() {
        addAlumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                AlumnoModel alumnoModel = (AlumnoModel) result.getData().getExtras().getSerializable("ALUMNO");
                                if (alumnoModel != null) {
                                    alumnoModelArrayList.add(alumnoModel);
                                    mostrarAlumnos();
                                }

                            }
                        }
                    }
                }

        );
    }

    private void mostrarAlumnos() {
        binding.contentMain.contendorMain.removeAllViews();

        for (AlumnoModel alumno: alumnoModelArrayList
             ) {
            TextView txtAlumno = new TextView(MainActivity.this);
            txtAlumno.setText(alumno.toString());
            binding.contentMain.contendorMain.addView(txtAlumno);

        }


    }
}