package FunktioLaskin;

import java.util.ArrayList;

/*
 * Controller class
 */
public class Control {

	private Laskin laskin;
	private Main main;
	private Laskujarjestys laske;
	private Listat lista;
	private double Tulos;
	private double vanha;
/*
 * Constructor
 */
	public Control(){
		laskin = new Laskin();
		main = new Main();
		laske = new Laskujarjestys();
		lista = new Listat();
	}
	/*
	 * clears the calculator
	 */
	public void nollaa(){
		//vanha = Tulos;
		laskin.nollaa();
		laske.nollaa();
		Tulos = 0;
		lista.nollaa();
		//edellinen = "tyhja";
		//arvot.clear();
		//merkit.clear();
		//miinus = 0;
		//luku1 = 0;
		//luku = null;
	}
	/*
	 * Counts the calculation in the right order
	 */
	public void laske(){
		laske.setArvotList(lista.getArvotList());
		laske.setMerkitList(lista.getMerkitList());
		Tulos = laske.laske();
	}
	public void setMerkki(String value){
		lista.setMerkki(value);
	}
	public void setLuku(String value){
		lista.setLuku(value);
	}
	public void setEmerkki(String value){
		lista.setEmerkki(value);
	}
	/*
	 * Check if there are brackets (In progress)
	 */
	public void sulut(String sulku){
		if (sulku == "("){

		}
	}

	public void setPii(){
		lista.setPii();
	}
	/*
	 * returns the result
	 * @return Tulos Tulos is the result of the calculation
	 */
	public double getTulos(){
		return Tulos;
	}
}

