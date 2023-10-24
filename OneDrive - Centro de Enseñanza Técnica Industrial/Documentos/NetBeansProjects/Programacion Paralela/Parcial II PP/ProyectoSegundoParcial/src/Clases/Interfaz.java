
package Clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interfaz extends JFrame{
    //Intancias para mostrar
    private JTextArea originalText;
    private JTextArea sumaText;
    private JTextField numero; //Tamaño del arreglo
    private JButton executor;
    private JLabel resultLabel;
    private ExecutorService executorService;
    private Callable<Float> sumaCallable;

    
    public Interfaz(){
        setTitle("Suma de elementos de un Arreglo");
        setSize(800, 500);
        setLayout(null);
        this.setLocationRelativeTo(null);
      
        JLabel titulo;
        titulo = new JLabel("Proyecto Segundo Parcial");
        Color colorTexto = Color.decode("#FFFFFF");
        titulo.setForeground(colorTexto);
        Font font = titulo.getFont();
        Font fontNegrita = new Font(font.getName(), Font.BOLD, 16); 
        titulo.setFont(fontNegrita);
        titulo.setBounds(325,15,200,50);
        add(titulo);
        
        //Text area donde se van a poner todos los numetos
        originalText = new JTextArea("Generar Numeros");
        originalText.setBounds(80, 80, 350, 50);
        add(originalText);
        JScrollPane ScrollPane = new JScrollPane(originalText);
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane.setBounds(80, 80, 350, 50);
        add(ScrollPane);
        
        //Text Area de la Suma de elementos
        sumaText = new JTextArea("Suma de Elementos");
        sumaText.setBounds(80, 180, 350, 50);
        add(sumaText);
        JScrollPane ScrollPane2 = new JScrollPane(sumaText);
        ScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane2.setBounds(80, 180, 350, 50);
        add(ScrollPane2);
        
        //Boton para generar numeros Aleatorios    
        JButton acomodo = new JButton("Generar Numeros");
        acomodo.setBounds(500, 80, 150, 30);
        acomodo.setBackground(Color.WHITE); 
        acomodo.setForeground(Color.BLACK);  
        add(acomodo); 
        acomodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tamaño = Integer.parseInt(numero.getText());
                generarNumerosAleatorios(tamaño);
            }
        });
        
        //Textfiel para el tamaño del arreglo
        numero = new JTextField();
        numero.setText("");
        numero.setColumns(4); 
        numero.setBounds(500, 130, 150, 30);
        add(numero);
        
        //Archivo
        JButton archivo = new JButton("Archivo");
        archivo.setBounds(675, 95, 80, 50);
        archivo.setBackground(Color.WHITE); 
        archivo.setForeground(Color.BLACK);  
        add(archivo); 
        archivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));

                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();

                    try {
                        cargarNumerosDesdeArchivo(archivoSeleccionado);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
               
        
        //          Merge
                
        JLabel tiempoMerge;
        tiempoMerge = new JLabel("Tiempo:");
        Color colorTexto2 = Color.decode("#FFD700");
        tiempoMerge.setForeground(colorTexto2);
        tiempoMerge.setBounds(80,280,200,50);
        add(tiempoMerge);
        
        JButton merge = new JButton("MergeSort");
        merge.setBounds(80, 320, 120, 30);
        merge.setBackground(Color.WHITE);
        merge.setForeground(Color.BLACK); 
        add(merge);
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeros != null && numeros.length > 0) {
                    Merge merge = new Merge(); //Objeto de la clase merge                    
                    long tiempoInicio = System.nanoTime(); // Registra el tiempo de inicio
                    float sumaTotal = merge.mergeSort(numeros, 0, numeros.length - 1); // vairbale suma total de tipoFloat para hacer el calculo
                    long tiempoFin = System.nanoTime();
                    long tiempoTotal = tiempoFin - tiempoInicio;                   
                    mostrarSumaNumeros(); //Mostrar la suma

                    //Añadir una coma cada tres dijistos
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tiempoDecimal = df.format(tiempoTotal);
                    tiempoMerge.setText("Tiempo: " + tiempoDecimal + " ns");
                }
            }
        });

        //          ForkJoin
   
        JLabel tiempoJoin;
        tiempoJoin = new JLabel("Tiempo:");
        tiempoJoin.setForeground(colorTexto2);
        tiempoJoin.setBounds(270, 280, 150, 50);
        add(tiempoJoin);
        JButton fork = new JButton("Fork Join");
        fork.setBounds(270, 320, 130, 30);
        fork.setBackground(Color.WHITE);
        fork.setForeground(Color.BLACK); 
        add(fork);
        fork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeros != null && numeros.length > 0) {
                   //// Llamamos al método estático sumarElementosConForkJoin
                   ResultadoSuma resultado = ForkJoin.sumarElementosConForkJoin(numeros);
                   float suma = resultado.getSuma();// Obtenemos la suma total 
                   long tiempoTotal = resultado.getTiempoTotal();//// Obtenemos el tiempo total
                   mostrarSumaNumeros();
                   // Formatea el tiempo en nanosegundos como deseas
                   DecimalFormat df = new DecimalFormat("#,###");
                   String tiempoDecimal = df.format(tiempoTotal);
                   tiempoJoin.setText("Tiempo: " + tiempoDecimal + " ns");
                }
            }
        });
        
        
        //      Executor
        
        JLabel tiempoExecutor;
        tiempoExecutor = new JLabel("Tiempo:");
        tiempoExecutor.setForeground(colorTexto2);
        tiempoExecutor.setBounds(480,280,200,50);
        add(tiempoExecutor);
        
        
        
        // Inicializa el ExecutorService con un ThreadPool de 4 hilos
        executorService = Executors.newFixedThreadPool(4);

        // Inicializa el Callable para calcular la suma
        sumaCallable = new Callable<Float>() {
            @Override
            public Float call() {
                // Realiza el cálculo de la suma en paralelo aquí
                float suma = 0;
                for (float numero : numeros) {
                    suma += numero;
                }
                return suma;
            }
        };
                
        //Boton y metodo Executer
        JButton executer = new JButton("Executer");
        executer.setBounds(480, 320, 130, 30);
        executer.setBackground(Color.WHITE);
        executer.setForeground(Color.BLACK); 
        add(executer);
        executer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeros != null && numeros.length > 0) {
                    
                    long tiempoInicio = System.nanoTime(); 

                    ExecutorTask sumCalculator = new ExecutorTask(numeros, executorService);
                    Future<Float> resultado = sumCalculator.calcularSumaEnParalelo();

                    try {
                        float sumaTotal = resultado.get(); // Obtiene el resultado de la suma
                        long tiempoFin = System.nanoTime(); // Registra el tiempo de finalización
                        long tiempoTotal = tiempoFin - tiempoInicio; // Calcula el tiempo de ejecución

                        mostrarSumaNumeros();

                        // Formatea el tiempo en nanosegundos como desees
                        DecimalFormat df = new DecimalFormat("#,###");
                        String tiempoFormateado = df.format(tiempoTotal);

                        tiempoExecutor.setText("Tiempo: " + tiempoFormateado + " ns");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

                
         //Metodo Limpiar
        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(600, 400, 100, 30);
        limpiar.setBackground(Color.decode("#FFD700"));
        limpiar.setForeground(Color.BLACK); 
        add(limpiar);
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumaText.setText("");
                numero.setText("");
                originalText.setText("");
                tiempoMerge.setText("Tiempo: ");
                tiempoJoin.setText("Tiempo: ");
                tiempoExecutor.setText("Tiempo: ");
            }
        });
               
        getContentPane().setBackground(Color.decode("#000080"));
        setVisible(true);
        
    }//Interfaz
    
    
    
    
        //Metodos Para Generar y Mostrar
    
    private float[] numeros;  // Arreglo para almacenar números aleatorios
    
    // Genera tamanio numeros aleatorios y los guarda en el arreglo
    private void generarNumerosAleatorios(int tamaño) {
        numeros = new float[tamaño];
        Random random = new Random();

        for (int i = 0; i < tamaño; i++) {
            numeros[i] = (random.nextInt(10001)/100.0f);
        }
        mostrarNumerosOriginales(); // Llama a la función para mostrar los números originales
    }//GenerarNumerosAleatorios

    // Construye una cadena de texto con los números del 
    //arreglo numeros y los muestra en originalText
    private void mostrarNumerosOriginales() {
        StringBuilder sb = new StringBuilder();
        for (float numero : numeros) {
            sb.append(numero).append(" ");
        }
        originalText.setText(sb.toString());
    }//mostrarNumerosOriginales

    // Construye una cadena de texto con los números del 
    //arreglo numeros y los muestra en acomodoText
        private void mostrarSumaNumeros() {
            float suma = 0;
            for (float numero : numeros) {
                suma += numero;
            }

            DecimalFormat df = new DecimalFormat("#,###.###");
            String sumaFormateada = df.format(suma);

            sumaText.setText("Suma de números: " + sumaFormateada);
        }  

    private void cargarNumerosDesdeArchivo(File archivo) throws IOException {
        List<Float> numerosList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                for (String parte : partes) {
                    try {
                        // Establece el Locale para asegurarte de que los números se interpreten correctamente
                        Locale usLocale = new Locale("en", "US");
                        NumberFormat format = NumberFormat.getInstance(usLocale);
                        Number number = format.parse(parte);
                        float numero = number.floatValue();
                        numerosList.add(numero);
                    } catch (ParseException e) {
                        // Ignora partes que no sean números flotantes
                    }
                }
            }
        }
    
    if (!numerosList.isEmpty()) {
        numeros = new float[numerosList.size()];
        for (int i = 0; i < numerosList.size(); i++) {
            numeros[i] = numerosList.get(i);
        }
        
        mostrarNumerosOriginales();
    } else {
        originalText.setText("No se encontraron números en el archivo.");
    }
}
   
}//class
