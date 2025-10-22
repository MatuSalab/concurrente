import java.util.concurrent.Semaphore;

class Cadena {
  private final Semaphore[] S;
  private final int M;

  public Cadena(int M) {
    this.M = M;
    this.S = new Semaphore[M + 1];          // 1..M
    for (int k = 1; k <= M; k++) S[k] = new Semaphore(0, true);
    S[1].release();                          // arranca la Oración 1
  }

  public void imprimirPaso(int idDivision, int k) {
    try {
        S[k].acquire();          // espera su turno exacto
        System.out.println("Div: "+idDivision+" Oración: "+ k);
        if (k < M) S[k + 1].release();          // habilita la siguiente
    } catch (Exception e) {
        // TODO: handle exception
    }
  }
}

