package mx.iteso.erickgarcia.serviciosweb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by erickgarcia on 04/04/18
 */

public class AgregarContacto extends AppCompatActivity implements onTaskCompleted{
    EditText editTextNombre, editTextApellido;
    Button buttonGuardar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        editTextNombre = findViewById(R.id.txt_nombre);
        editTextApellido = findViewById(R.id.txt_apellido);
        buttonGuardar = findViewById(R.id.btn_guardar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextNombre.getText().equals("")){
                    new NetServices(AgregarContacto.this).execute("post",
                            editTextNombre.getText().toString(),
                            editTextApellido.getText().toString());
                } else
                    Toast.makeText(getBaseContext(), "favor de ingresar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTaskCompleted(String response) {

    }
}
