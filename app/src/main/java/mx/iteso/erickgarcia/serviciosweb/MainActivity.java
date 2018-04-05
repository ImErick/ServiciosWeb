package mx.iteso.erickgarcia.serviciosweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonAgregarContacto, buttonMostrarLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAgregarContacto = findViewById(R.id.btn_agregar_contacto);
        buttonMostrarLista = findViewById(R.id.btn_mostrar_lista);

        buttonAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
