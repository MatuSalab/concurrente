package SalaMuseo;

public class Persona implements Runnable{
    int id; boolean jubilado; GestorSala gestor;

    public Persona(int i, boolean j, GestorSala g){
        id=i; jubilado=j; gestor=g;
    }

    public boolean esJubilado(){
        return jubilado;
    }

    public void run(){
        if(esJubilado()){
            gestor.entrarSalaJubilado();
        }else{
            gestor.entrarSala();
        }
    }
}
