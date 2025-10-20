public class ClienteImpresora implements Runnable{
    int id; GestorImpresoras gestor;

    public ClienteImpresora(int i, GestorImpresoras g){
        this.id=i; this.gestor=g;
    }

    public void run(){
        int pos=-1;
        try {
            pos=gestor.adquirir();
            System.out.println("Cliente "+this.id+" imprime en impresora "+pos);
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            if (pos>=0) gestor.liberar(pos);
        }
    }
}

