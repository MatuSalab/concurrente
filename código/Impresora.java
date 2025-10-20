public class Impresora {
    String id;
    boolean estado;

    public Impresora(String id, boolean e){
        this.id=id; this.estado=e;
    }

    public void imprimir(String usuario){
        try {
            this.estado=true;
            System.out.println(usuario + " está utilizando la impresora "+this.id);
            Thread.sleep(1000);
            System.out.println(usuario + " libera la impresora");
            this.estado=false;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
