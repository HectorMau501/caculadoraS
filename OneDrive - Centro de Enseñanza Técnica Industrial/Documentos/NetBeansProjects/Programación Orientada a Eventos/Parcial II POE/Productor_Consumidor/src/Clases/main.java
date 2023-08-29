/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author HECTOR MAURICIO
 */
public class main {
    public static void main(String[] args){
        int numeroHilos = 9; //1 Productor y 8 Consumidores
        
        Thread[] hilos = new Thread[numeroHilos];
        
        for(int i = 0; i < hilos.length ; i++){
            Runnable runnable = null;
            
            if(i != 0){
                runnable = new Consumidor(true, i); //En el constructor voy a crear un objeto, en donde verifica si es consumidor o no
            }
            else{
                runnable = new Consumidor(false, i);  //En el constructor voy a crear un objeto, en donde verifica si es consumidor o no
            }
            hilos[i] = new Thread(runnable); //Se envian los hilos
            hilos[i].start();
        }
        for(int i = 0; i<hilos.length; i++){ //Corrutina
            try{
                hilos[i].join();
            }catch(Exception ex){}
        }
        
    }
}
