package SalaMuseo;

public class CheckTemperatura implements Runnable{
    int umbral; GestorSala gestor;
    public CheckTemperatura(int u, GestorSala g){
        umbral=u; gestor=g;
    }

    public void run(){
        gestor.notificarTemperatura(this.umbral);
    }
}
