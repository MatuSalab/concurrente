package Babuinos;

public class Babuino implements Runnable{

    int id; int lado; Cuerda cuerda;
    public Babuino(int i, int l, Cuerda c){
        id=i; lado=l; cuerda=c;
    }

    public void pasarCuerda(){
        try{
            if(lado=='D'){
                System.out.println("El babuino "+id+" esta cruzando la cuerda del lado derecho al izquierdo");
                Thread.sleep(500);
                System.out.println("El babuino "+id+" termino de cruzar del lado derecho al izquierdo");
            }else if(lado=='I'){
                System.out.println("El babuino "+id+" esta cruzando la cuerda del lado izquierdo al derecho");
                Thread.sleep(500);
                System.out.println("El babuino "+id+" termino de cruzar del lado izquierdo al derecho");
            }
        }catch(Exception e){

        }
    }

    public void run(){
        try{
            cuerda.subir(lado);
            pasarCuerda();
        }finally{
            cuerda.bajar(lado);
        }
    }
    
}
