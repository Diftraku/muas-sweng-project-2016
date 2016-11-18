package FunktioLaskin;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Controller class
 */
public class Control {
    private static final Control instance = new Control();

    private Laskin laskin;
    private Main main;
    private Laskujarjestys laske;
    ArrayList<String> listat;
    private String lasku = "";
    private int place = 0;
    private LuoLista luolista;
    private char aChar;
    private String Valitulos;
    private CharSequence charplace = "x";
    private String newString = "";

    private double Tulos;


    /*
     * Constructor
     */
    private Control() {
        laskin = new Laskin();
        main = new Main();
        laske = new Laskujarjestys();
        listat = new ArrayList<String>();
        luolista = new LuoLista();

    }

    public static Control getInstance() {
        return instance;
    }

    /*
     * clears the calculator
     */
    public void nollaa() {
        //vanha = Tulos;
        laskin.nollaa();
        laske.nollaa();
        Tulos = 0;
        listat.clear();
        Valitulos = "";
        lasku = "";
        place = 0;
        luolista.nollaa();
        //edellinen = "tyhja";
        //arvot.clear();
        //merkit.clear();
        //miinus = 0;
        //luku1 = 0;
        //luku = null;
        System.out.println(listat+" nollattu?");
    }
    /*
	 * clears only chosen classes between calculations
	 */
    public void nollaaValilasku(){
    	laske.nollaa();
    	laskin.nollaa();
    	newString = "";
    }

    /*
     * Counts the calculation in the right order
     *
     */
    public void laskelopputulos() {
    	System.out.println("control" +listat);
    	for (int i = (listat.size()-1); i >= 0; i--){
    		/*
    		 * checks if there are multiple strings in array.
    		 * if so calculates last one first and so on
    		 * saves result each time
    		 */
    		if (i!= 0){
    			luolista.luolista(listat.get(i));
    			System.out.println("laskee tuloksen" + luolista.getArvotList() + ", " + luolista.getMerkitList());
    			Tulos = laske.laske(luolista.getArvotList(), luolista.getMerkitList());
    			nollaaValilasku();
    			Valitulos = Double.toString(Tulos);
    			lasku = listat.get(i-1);
    		/*
    		 * replace x in previous string with result of calculations inside prackets
    		 */
    		for (int k=0; k < lasku.length(); k++){
    			aChar = ((CharSequence) lasku).charAt(k);
    			if (Objects.equals(aChar, 'x')){
    				newString += Valitulos;

    			}
    			else {
    				newString += Character.toString(aChar);
    			}
    		}
    		listat.set((i-1), newString);
    		}
    		/*
    		 * Calculates final string and saves result
    		 */
    		else if (Objects.equals(i, 0)){
    			luolista.luolista(listat.get(i));
        		System.out.println(listat.get(i)+ " 0 laskee tuloksen" + luolista.getArvotList() + ", " + luolista.getMerkitList());
                Tulos = laske.laske(luolista.getArvotList(), luolista.getMerkitList());
                nollaaValilasku();
    		}
    	}
    	}
    /*
	 * sets value to string "lasku#
	 */
    public void setValue(String value) {
        lasku += value;
    }

    /*
	 * if prackets where used makes new string to array
	 */
    public void sulut(String sulku) {
        if (Objects.equals(sulku, "(")) {
        	listat.add(place, lasku);
        	System.out.println(lasku);
        	place++;
        	lasku = "";
        }
        /*
    	 * adds new string to array and goes back to previous string in array
    	 */
        else if (Objects.equals(sulku, ")")) {
        	lasku += sulku;
        	listat.add(place, lasku);
        	System.out.println(lasku);
        	place--;
        	lasku = listat.get(place);
        	listat.remove(place);
        	lasku += "x";
        }
        /*
    	 * adds new string to array
    	 */
        else if (Objects.equals(sulku, "=")) {
        	lasku += sulku;
        	listat.add(place, lasku);
        	System.out.println(lasku);
        }

    }






    /*
     * returns the result
     * @return Tulos Tulos is the result of the calculation
     */
    public double getTulos() {
        return Tulos;
    }
}

