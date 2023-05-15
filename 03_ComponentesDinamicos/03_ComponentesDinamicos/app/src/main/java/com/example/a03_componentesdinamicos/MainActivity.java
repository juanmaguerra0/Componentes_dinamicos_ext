package com.example.a03_componentesdinamicos;// Importaciones necesarias para la clase
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Importamos AppCompatActivity, que es la clase base para las actividades que usan la biblioteca de soporte ActionBar
import androidx.appcompat.app.AppCompatActivity;

// Definimos nuestra clase MainActivity que hereda de AppCompatActivity
public class MainActivity extends AppCompatActivity {
    Button b1;  // Declaramos un botón
    private ProgressDialog progress;  // Declaramos un cuadro de diálogo de progreso

    // Este método se llama cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // Llama al método onCreate de la superclase
        setContentView(R.layout.activity_main);  // Establece la vista de esta actividad
        b1 = (Button) findViewById(R.id.button2);  // Asocia la variable b1 con el botón en la vista
    }

    // Este método se llama cuando se hace clic en el botón de descarga
    public void download(View view){
        progress=new ProgressDialog(this);  // Crea un nuevo cuadro de diálogo de progreso
        progress.setMessage("Downloading Music");  // Establece el mensaje del cuadro de diálogo
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  // Establece el estilo del cuadro de diálogo como horizontal
        progress.setIndeterminate(true);  // Establece que el progreso es indeterminado
        progress.setProgress(0);  // Inicializa el progreso a 0
        progress.show();  // Muestra el cuadro de diálogo

        // Definimos el tiempo total de progreso
        final int totalProgressTime = 100;

        // Creamos un nuevo hilo
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;  // Variable para llevar la cuenta del progreso

                // Mientras el tiempo transcurrido es menor que el tiempo total de progreso
                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(200);  // Duerme el hilo por 200 milisegundos
                        jumpTime += 5;  // Aumenta el tiempo transcurrido
                        progress.setProgress(jumpTime);  // Establece el progreso en el cuadro de diálogo
                    } catch (InterruptedException e) {
                        // Si el hilo es interrumpido, imprime la traza de la pila de la excepción
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();  // Inicia el hilo
    }
}
