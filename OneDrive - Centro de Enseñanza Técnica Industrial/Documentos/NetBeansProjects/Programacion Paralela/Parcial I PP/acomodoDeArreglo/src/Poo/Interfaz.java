
package Poo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Interfaz extends JFrame{
    //Instancias
    private JTextArea originalText;
    private JTextArea acomodoText;
    private JTextField numero;
    
    //Constructor
    public Interfaz(){
        
        setTitle("Acomodo de Arreglos");
        setSize(800, 500);
        setLayout(null);
        
        //Text Area
        originalText = new JTextArea("Original");
        originalText.setBounds(100, 50, 350, 50);
        add(originalText);
        JScrollPane ScrollPane = new JScrollPane(originalText);
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane.setBounds(100, 50, 350, 50);
        add(ScrollPane);
        
        acomodoText = new JTextArea("Acomodado");
        acomodoText.setBounds(100, 150, 350, 50);
        add(acomodoText);
        JScrollPane ScrollPane2 = new JScrollPane(acomodoText);
        ScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane2.setBounds(100, 150, 350, 50);
        add(ScrollPane2);
        
        //Textfiel
        numero = new JTextField();
        numero.setText("");
        numero.setColumns(4); 
        numero.setBounds(600, 100, 100, 30);
        add(numero);
        
        //botones y jlabel      
        JButton acomodo = new JButton("Original");
        acomodo.setBounds(600, 50, 100, 30);
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
        
        JLabel tiempoMerge;
        tiempoMerge = new JLabel("Tiempo:");
        Color colorTexto = Color.decode("#FF5733");
        tiempoMerge.setForeground(colorTexto);
        Font font = tiempoMerge.getFont();
        Font fontNegrita = new Font(font.getName(), Font.BOLD, 14); 
        tiempoMerge.setFont(fontNegrita);
        tiempoMerge.setBounds(100,250,200,50);
        add(tiempoMerge);
        
        JButton merge = new JButton("MergeSort");
        merge.setBounds(100, 300, 120, 30);
        merge.setBackground(Color.WHITE);
        merge.setForeground(Color.BLACK); 
        add(merge); 
       
        //Tiempo
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numeros != null && numeros.length > 0){
                    long tiempo = Merge.mergeSort(numeros, 0, numeros.length - 1);  // Ejecuta Merge Sort y mide el tiempo
                    mostrarNumerosAcomodados(); // Muestra los números acomodados en el JTextArea
                    tiempoMerge.setText("Tiempo: " +tiempo+" ms"); //Tiempo
                }
            }           
        });
        
        //Algoritmo
        merge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeros != null && numeros.length > 0) {
                    Merge.mergeSort(numeros, 0, numeros.length - 1);
                    mostrarNumerosAcomodados(); // Muestra los números acomodados en el JTextArea
                }
            }
        });
        
        JLabel tiempoJoin;
        tiempoJoin = new JLabel("Tiempo:");
        Color colorTexto2 = Color.decode("#FF5733");
        tiempoJoin.setForeground(colorTexto2);
//        tiempoJoin.setFont(fontNegrita);
        tiempoJoin.setBounds(300, 250, 150, 50);
        add(tiempoJoin);

        JButton fork = new JButton("Fork Join");
        fork.setBounds(300, 300, 130, 30);
        fork.setBackground(Color.WHITE);
        fork.setForeground(Color.BLACK); 
        add(fork);
        
                // Acción para el botón "Fork Join"
        fork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeros != null && numeros.length > 0) {
                    long tiempoInicio = System.currentTimeMillis(); // Registro del tiempo de inicio

                    // Llama al método que utiliza Fork Join para ordenar los números
                    ForkJoin.ordenarConForkJoin(numeros);

                    long tiempoFin = System.currentTimeMillis(); // Registro del tiempo de finalización
                    long tiempoTotal = tiempoFin - tiempoInicio; // Calcula el tiempo total

                    mostrarNumerosAcomodados(); // Muestra los números acomodados en el JTextArea
                    tiempoJoin.setText("Tiempo: " + tiempoTotal + " ms"); // Muestra el tiempo en el JLabel
                }
            }
        });
        
        JLabel tiempoExecutor;
        tiempoExecutor = new JLabel("Tiempo:");
        Color colorTexto3 = Color.decode("#FF5733");
        tiempoExecutor.setForeground(colorTexto3);
//        tiempoExecutor.setFont(fontNegrita);
        tiempoExecutor.setBounds(500,250,200,50);
        add(tiempoExecutor);
        
        JButton executer = new JButton("Executer");
        executer.setBounds(500, 300, 100, 30);
        executer.setBackground(Color.WHITE);
        executer.setForeground(Color.BLACK); 
        add(executer);
        
        
        //Metodo Limpiar
        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(600, 400, 100, 30);
        limpiar.setBackground(Color.GREEN);
        limpiar.setForeground(Color.BLACK); 
        add(limpiar);
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acomodoText.setText("");
                numero.setText("");
                
            }
        });
        
        
        getContentPane().setBackground(Color.decode("#333333"));
        setVisible(true);
    }//interfaz constructor
    
    //Metodos Para Generar y Mostrar
    
    private int[] numeros;  // Arreglo para almacenar números aleatorios

    // Genera tamanioo numeros aleatorios y los guarda en el arreglo
    private void generarNumerosAleatorios(int tamaño) {
        numeros = new int[tamaño];
        Random random = new Random();

        for (int i = 0; i < tamaño; i++) {
            numeros[i] = random.nextInt(100); 
        }
        mostrarNumerosOriginales(); // Llama a la función para mostrar los números originales
    }//GenerarNumerosAleatorios

    // Construye una cadena de texto con los números del 
    //arreglo numeros y los muestra en originalText
    private void mostrarNumerosOriginales() {
        StringBuilder sb = new StringBuilder();
        for (int numero : numeros) {
            sb.append(numero).append(" ");
        }
        originalText.setText(sb.toString());
    }//mostrarNumerosOriginales

    // Construye una cadena de texto con los números del 
    //arreglo numeros y los muestra en acomodoText
    private void mostrarNumerosAcomodados() {
        StringBuilder sb = new StringBuilder();
        for (int numero : numeros) {
            sb.append(numero).append(" ");
        }
        acomodoText.setText(sb.toString());
    }//mostrarNumerosAcomodados

}//class
