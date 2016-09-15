package FunktioLaskin;
public class Laskin {

    private double tulos;  // Muuttuja tulokselle
    private double edellinen;

    public void nollaa() {
    	edellinen = tulos;
        tulos = 0;
    }

    public double annaTulos() {
        return tulos;
    }

    public void lisaa(double n) {
    	edellinen = tulos;
        tulos = tulos + n;
    }

    public void vahenna(double n) {
    	edellinen = tulos;
        tulos = tulos - n;
    }

    public void kerro(double n) {
    	edellinen = tulos;
        tulos = tulos * n;
    }

    public void jaa(double n) throws ArithmeticException {
    	edellinen = tulos;
        tulos = tulos / n;
    }

    public void nelio(double n) {
    	edellinen = tulos;
        tulos = n * n;
    }

    public void neliojuuri(double n) throws ArithmeticException {
    	edellinen = tulos;
        //for (;;) ;

        tulos = (double)Math.sqrt(n);

    }
    public void edellinen(){
    	tulos = edellinen;
    }

    public void virtaON() {
        // Tähän voi laittaa alkutoimet
        tulos = 0;
        System.out.println("Laskin: virtaON())");
    }

    public void virtaOFF() {
        // Tähän voi laittaa lopputoimet
        System.out.println("Laskin: virtaOFF()");
    }
}
