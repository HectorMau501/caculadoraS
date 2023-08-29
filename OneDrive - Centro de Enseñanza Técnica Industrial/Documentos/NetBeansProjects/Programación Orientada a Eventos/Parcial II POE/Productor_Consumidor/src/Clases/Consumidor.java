/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HECTOR MAURICIO
 */
public class Consumidor extends Thread {
    
     Random random1 = new Random(); //Instanciamos un objeto del metodo Random, para crear numeros aleatorios
    
    private boolean consumidor;
    private int hilo;
    
    private static int enchilada = 0;
    private static Object lock = new Object();
    
    public Consumidor (boolean consumidor, int hilo){
        this.consumidor = consumidor;
        this.hilo = hilo;
    }
    
    
    @Override
    public void run(){
        while(true){
            if(consumidor ){
                consumiendo(hilo);
            }else{
                produciendo();
            }
        }
    }

    private void produciendo() {
        synchronized(lock){ //Solo un hilo puede escribir en la variable enchilada
            if(enchilada == 0){
                enchilada = random1.nextInt(21) + 10;
                System.out.println("Soy el Productor  hice "+enchilada+" enchiladas" + "\n\n");
                lock.notifyAll(); //Nofiticar a todos
            }
            try{
                lock.wait();
            }catch(Exception ex){}
        }
    }

    private void consumiendo(int hilo) {
        synchronized(lock){
            if(enchilada  > 0){
                enchilada --;
                System.out.println("Hola soy el hilo " + hilo + " Quedan "+enchilada+" enchiladas restantes");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {               
                    ex.printStackTrace();
                }
            }else{
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
