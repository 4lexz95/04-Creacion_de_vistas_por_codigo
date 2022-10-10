package alex.example.a04_creaciondevistasporcodigo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import alex.example.a04_creaciondevistasporcodigo.databinding.ActivityAddAlumnoBinding;
import alex.example.a04_creaciondevistasporcodigo.modeos.AlumnoModel;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();

            }
        });

        binding.btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlumnoModel alumnoModel = crearAlumno();

                if (alumnoModel != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO",alumnoModel);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();

                }else {
                    Toast.makeText(AddAlumnoActivity.this, "Faltan da  ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    private AlumnoModel crearAlumno() {
        if (binding.txtNombreAddAlumno.getText().toString().isEmpty() || binding.txtApellidosAlumno.getText().toString().isEmpty())
            return null;
        if (binding.spCiclosAddAlumno.getSelectedItemPosition()== 0)
            return null;

        if(!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked())
            return null;

        String ciclo = (String) binding.spCiclosAddAlumno.getSelectedItem();

         // radio grop puede decir que radiobutton se ha selecionado

       RadioButton rb = findViewById(binding.RGgrupoAddAlumno.getCheckedRadioButtonId());
       char grupo = rb.getText().charAt(0);
        return new AlumnoModel(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidosAlumno.getText().toString(),ciclo,grupo);

    }



}