package FunktioLaskin;

import java.util.ArrayList;

/*
 * Controller class
 */
public class Control {

	private Laskin laskin;
	private Main main;
	private Laskujarjestys laske;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private String edellinen = "tyhja";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;
	private double Tulos;
	private double vanha;
/*
 * Constructor
 */
	public Control(){
		laskin = new Laskin();
		main = new Main();
		laske = new Laskujarjestys();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
	}
	/*
	 * clears the calculator
	 */
	public void nollaa(){
		//vanha = Tulos;
		laskin.nollaa();
		edellinen = "tyhja";
		laske.nollaa();
		arvot.clear();
		merkit.clear();
		Tulos = 0;
		miinus = 0;
		luku1 = 0;
		luku = null;
	}
	/*
	 * Counts the calculation in the right order
	 */
	public void laske(){
		laske.setArvotList(arvot);
		laske.setMerkitList(merkit);
		laske.laske();
		Tulos = laske.getValiTulos();
	}

	/*
	 * Sets last number so it can be used in the next calculation
	 */
	public void setLuku(String value){
		if (edellinen == "tyhja"){
			luku = value;
			edellinen = "numero";
		}
		else if (edellinen == "merkki"){
			luku = value;
			edellinen = "numero";
		}
		else if (edellinen == "numero"){
			luku+=value;
			edellinen = "numero";
		}
		else if (edellinen == "emerkki"){
			luku = value;
			edellinen = "enumero";
		}
		else if (edellinen == "enumero"){
			luku+=value;
			edellinen = "enumero";
		}
	}
	/*
	 * Sets a number in the number array
	 */
	public void setArvo(String arvo){
		try {
			luku1 = Double.parseDouble(arvo);
	   }catch (NumberFormatException e){
	       System.out.println(arvo);
	   }
			if (miinus == 0){
				arvot.add(luku1);
			}
			if (miinus == 1){
				arvot.add(0-luku1);
			}
		}

	/*
	 * Sets operation in the operation array
	 */
	public void setEmerkki(String merkki){
			merkit.add(merkki);
			edellinen = "enumero";
	}
	public void setMerkki(String merkki){
		if (edellinen == "tyhja"){
			arvot.add(vanha);
			edellinen = "numero";

		}
		if (edellinen == "numero" || edellinen == "enumero"){
			if (merkki == "="){
				setArvo(luku);
				edellinen = "merkki";
				miinus = 0;
			}
			else{
				setArvo(luku);
				merkit.add(merkki);
				edellinen = "merkki";
				miinus = 0;
			}
		}
		else{
			if (merkki == "-"){
				miinus = 1;
				edellinen = "merkki";
			}
			else{
				//Virheilmoitus
			}
		}
	}
	/*
	 * Check if there are brackets (In progress)
	 */
	public void sulut(String sulku){
		if (sulku == "("){

		}
	}

	/*
	 * Sets Pii to arvot()
	 */
	public void setPii(){
		if (miinus == 0){
			arvot.add(Math.PI);
		}
		if (miinus == 1){
			arvot.add(0-Math.PI);
		}
	}
	/*
	 * returns the result
	 * @return Tulos Tulos is the result of the calculation
	 */
	public double getTulos(){
		return Tulos;
	}
}

