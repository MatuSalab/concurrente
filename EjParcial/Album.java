package EjParcial;

public class Album {
    int contador=0, duracionMax; String nombre;
    public Album(int max, String n){duracionMax=max; nombre=n;}

    public boolean sumarDuracion(int tiempo){
        boolean hayEspacio=true;
        if(!(contador+tiempo>duracionMax)){
            contador+=tiempo;
        }else{
            hayEspacio=false;
        }
        return hayEspacio;
    }
}
