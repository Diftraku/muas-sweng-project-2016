package FunktioLaskin;


public class Laskin {

    private double tulos=0;  // Muuttuja tulokselle
    private double edellinen=0;

    /*
     * Clears the result
     */
    public void nollaa() {
        tulos = 0;
        edellinen = 0;
    }
    /*
     * Returns the result
     * @return double Result
     */
    public double annaTulos() {
        return tulos;
    }
    /*
     * Add function
     * @param n Is the number to be added
     */
    public void lisaa(double n) {
    	edellinen = tulos;
        tulos = tulos + n;
    }
    /*
     * Subtraction function
     * @param n Number to be subtracted
     */
    public void vahenna(double n) {
    	edellinen = tulos;
        tulos = tulos - n;
    }
    /*
     * Multiply function
     * @param n Number to be multiplied
     */
    public void kerro(double n) {
    	edellinen = tulos;
        tulos = tulos * n;
    }
    /*
     * Division function
     * @param n Number that divides the last number
     * @throws ArithmeticException
     */
    public void jaa(double n) throws ArithmeticException {
    	edellinen = tulos;
        tulos = tulos / n;
    }
    /*
     * Squared function
     * @param n Number to be squared
     */
    public void nelio(double n) {
    	edellinen = tulos;
        tulos = n * n;
    }

    /*
     * Square root function
     * @param n Number that will be square rooted
     * @throws ArithemticException
     */
    public void neliojuuri(double n) throws ArithmeticException {
    	edellinen = tulos;
    	if (n < 0){
    		tulos = 0;
    	}else{
    		tulos = (double)Math.sqrt(n);
    }
    }
    /*
     * Power of function
     * @param n Latest result or number put to power of n
     */
    public void potenssi(double n){
    	//n potensiin y
    	tulos = Math.pow(tulos,n);
	}
    /*
     * Tangent function
     * @param n Tangent from n
     */
    public void tan(double n) {
        double radiaani = Math.toRadians(n);
        tulos = Math.tan(radiaani);
    }
    /*
     * Sin function
     * @param n Sin from number m
     */
    public void sin(double n) {
        double radiaani = Math.toRadians(n);
        tulos = Math.sin(radiaani);
    }

    /*
     * Cos function
     * @param n Cos from n
     */
    public void cos(double n) {
        double radiaani = Math.toRadians(n);
        tulos = Math.cos(radiaani);
    }
    /*
     * Arctangent function
     * @param n Arctangent from n
     */
    public void arctan(double n) {
        tulos = Math.toDegrees(Math.atan(n));
    }
    /*
     * Arcsin function
     * @param n Arcsin from n
     */
    public void arcsin(double n) {
    	if (n > 0 && n < 1){
    		tulos = Math.toDegrees(Math.asin(n));
    	}
    	else{
    		tulos = -1;
    	}
    }
    /*
     * Arccos function
     * @param n Arccos from n
     */
    public void arccos(double n) {
    	if (n > 0 && n < 1){
        tulos = Math.toDegrees(Math.acos(n));
    	}
    	else{
    	tulos = -1;
    	}
    }
    /*
     * Last result
     */
    public void edellinen(){
    	tulos = edellinen;
    }
    /*
     * Starts the calculator
     */
    public void virtaON() {
        // Tähän voi laittaa alkutoimet
        tulos = 0;
        System.out.println("Laskin: virtaON())");
    }
    /*
     * Closes the calculator
     */
    public void virtaOFF() {
        // Tähän voi laittaa lopputoimet
        System.out.println("Laskin: virtaOFF()");
    }
}
