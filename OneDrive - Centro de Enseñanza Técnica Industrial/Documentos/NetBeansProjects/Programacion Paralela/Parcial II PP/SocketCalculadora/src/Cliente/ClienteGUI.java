package Cliente;

import Calculadora.Implementaciones;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClienteGUI extends JFrame {

    private boolean punto = true;
    private JTextField resultado;
    private String numero1 = "";
    private String numero2 = "";
    String signo = "";
    String contenido = "";
    Implementaciones operaciones = new Implementaciones();
    private JTextArea operacionesTextArea;
    private JButton enviarButton;

    public ClienteGUI() {
        setTitle("Calculadora");
        setSize(500, 500);
        setLayout(null);

        resultado = new JTextField();
        resultado.setBounds(50, 50, 350, 40);
        add(resultado);
        


        operacionesTextArea = new JTextArea();
        operacionesTextArea.setBounds(80, 400, 350, 40);
        add(operacionesTextArea);

        enviarButton = new JButton("Enviar");
        enviarButton.setBounds(40, 350, 100, 30);
        add(enviarButton);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la operación del JTextArea y enviarla al servidor
                String operacion = resultado.getText();
                operacionesTextArea.append(operacion + "\n");  // Agregar la operación al TextArea
                resultado.setText(enviarOperacionAlServidor(operacion));
            }
        });

        JButton uno = new JButton("1");
        uno.setBounds(50, 150, 50, 30);
        add(uno);
        uno.addActionListener(e -> resultado.setText(resultado.getText() + "1"));

        JButton dos = new JButton("2");
        dos.setBounds(150, 150, 50, 30);
        add(dos);
        dos.addActionListener(e -> resultado.setText(resultado.getText() + "2"));
        
        JButton tres;
        tres = new  JButton("3");
        tres.setBounds(250,150,50,30);
        add(tres);
        tres.addActionListener(e -> resultado.setText(resultado.getText() + "3"));


        JButton multiplicacion = new JButton("*");
        multiplicacion.setBounds(350, 200, 50, 30);
        add(multiplicacion);
        multiplicacion.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "*";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        
        JButton cuatro;
        cuatro = new  JButton("4");
        cuatro.setBounds(50,200,50,30);
        add(cuatro);
        cuatro.addActionListener(e -> resultado.setText(resultado.getText() + "4"));
        
        JButton cinco;
        cinco = new JButton("5");
        cinco.setBounds(150,200,50,30);
        add(cinco);
        cinco.addActionListener(e -> resultado.setText(resultado.getText() + "5"));

        
        JButton seis;
        seis = new JButton("6");
        seis.setBounds(250,200,50,30);
        add(seis);
        seis.addActionListener(e -> resultado.setText(resultado.getText() + "6"));

        
        JButton division;
        division = new JButton("/");
        division.setBounds(350,150,50,30);
        add(division);
        division.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "/";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        JButton siete;
        siete = new JButton("7");
        siete.setBounds(50,250,50,30);
        add(siete);
        siete.addActionListener(e -> resultado.setText(resultado.getText() + "7"));
        
        JButton ocho;
        ocho = new JButton("8");
        ocho.setBounds(150,250,50,30);
        add(ocho);
        ocho.addActionListener(e -> resultado.setText(resultado.getText() + "8"));
        
        JButton nueve;
        nueve = new JButton("9");
        nueve.setBounds(250,250,50,30);
        add(nueve);
        nueve.addActionListener(e -> resultado.setText(resultado.getText() + "9"));

        
        JButton suma;
        suma = new JButton("+");
        suma.setBounds(50,300,50,30);
        add(suma);
        suma.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "+";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        JButton resta;
        resta = new JButton("-");
        resta.setBounds(150,300,50,30);
        add(resta);
        resta.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "-";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        JButton punto;
        punto = new JButton(".");
        punto.setBounds(150,350,50,30);
        add(punto);
        punto.addActionListener(e -> {
            contenido = resultado.getText();
            if(contenido.length() <= 0) 
            {
                resultado.setText("0.");
            }else
            {
                if(resultado.getText().contains("."))
                {
                }
                else
                {
                    resultado.setText(resultado.getText()+".");
                   // punto = false;
                }
        }
        });
        punto.setBackground(Color.GRAY);
        punto.setForeground(Color.WHITE);

        
        JButton cero;
        cero = new JButton("0");
        cero.setBounds(250,250,50,30);
        add(cero);
        cero.addActionListener(e -> resultado.setText(resultado.getText() + "0"));
        
        JButton raiz;
        raiz = new JButton("√");
        raiz.setBounds(350,250,50,30);
        add(raiz);
        raiz.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "√";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        JButton modulo;
        modulo = new JButton("%");
        modulo.setBounds(350,300,50,30);
        add(modulo);
        modulo.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "%";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
                
        JButton pow;
        pow = new JButton("^");
        pow.setBounds(250,300,50,30);
        add(pow);
        pow.addActionListener(e -> {
            if (!resultado.getText().equals("")) {
                signo = "^";
                numero1 = resultado.getText();
                resultado.setText("");
            }
        });
        
        JButton borrar = new JButton("<-");
        borrar.setBounds(250, 350, 50, 30);
        add(borrar);
        borrar.addActionListener(e -> {
            resultado.setText("");
        });
        borrar.setBackground(Color.RED);
        borrar.setForeground(Color.WHITE);

        
        JButton calcular = new JButton("=");
        calcular.setBounds(350, 350, 50, 30);
        add(calcular);
        calcular.setBackground(Color.GREEN);
        calcular.setForeground(Color.WHITE);

        
        
         calcular.addActionListener(e -> {
            if (!resultado.getText().equals("") && !numero1.equals("") && !signo.equals("")) {
                numero2 = resultado.getText();
                double num1 = Double.parseDouble(numero1);
                double num2 = Double.parseDouble(numero2);
               
                double result = 0;

                switch (signo) {
                    case "+":
                        result = operaciones.suma(num1, num2);
                        break;
                    case "-":
                        result = operaciones.resta(num1, num2);
                        break;
                    case "*":
                        result = operaciones.multiplicacion(num1, num2);
                        break;
                    case "/":
                        result = operaciones.div(num1, num2);
                        break;
                    case "%":
                        result = operaciones.modulo(num1, num2);
                        break;
                    case "√":
                        result = operaciones.raiz(num1, num2);
                        break;  
                    case "^":
                        result = operaciones.pow(num1, num2);
                        break;     
                }

                resultado.setText(String.valueOf(result));
                numero1 = "";
                numero2 = "";
                signo = "";
            }
        });
        

        
        //Boton para calular
        getContentPane().setBackground(Color.BLUE);
        setVisible(true);
    }
     private String enviarOperacionAlServidor(String operacion) {
        try {
            // Conectarse al servidor de calculadora (ajusta la dirección IP y puerto)
            Socket socket = new Socket("192.168.1.64", 8082);

            // Obtener flujos de entrada y salida para la comunicación
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar la operación al servidor
            out.println(operacion);

            // Recibir la respuesta del servidor
            String respuesta = in.readLine();

            // Cerrar la conexión
            socket.close();

            return respuesta;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al comunicarse con el servidor";
        }
    }

}


