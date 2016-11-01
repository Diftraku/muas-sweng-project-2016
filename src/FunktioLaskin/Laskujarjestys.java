package FunktioLaskin;

import java.util.ArrayList;

public class Laskujarjestys {
	private Laskin laskin;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private int eka = 0;
	private double tulos = 0;
	/*
	 * Constructor
	 */
	public Laskujarjestys(){
		laskin = new Laskin();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();

	}
	/*
	 *Calculates the counting order
	 */
	public double laske(){
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "N"){
				laskin.neliojuuri((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (merkit.get(i) == "cos"){
				laskin.cos((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (merkit.get(i) == "sin"){
				laskin.sin((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (merkit.get(i) == "sin"){
				laskin.tan((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "^"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.potenssi((double) arvot.get(i));
			}
			else if (merkit.get(i) == "*"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.kerro((double) arvot.get(i));
			}
			else if (merkit.get(i) == "/"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.jaa((double) arvot.get(i));
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "+"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.lisaa((double) arvot.get(i));
			}
			else if (merkit.get(i) == "-"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.vahenna((double) arvot.get(i));
			}
		}
		//Asetetaan tulos kontrollerille
		nollaa();
		return laskin.annaTulos();
	}
	/*
	 * Sets the operations list for this class
	 */

	public void setMerkitList(ArrayList<String> Merkit){
		this.merkit = Merkit;
	}
	/*
	 * Set the numbers list for this class
	 */
	public void setArvotList(ArrayList<Double> Arvot){
		this.arvot = Arvot;
	}
	/*
	 * Gets the intermediate result
	 * @return double intermediate result
	 */
	public double getValiTulos(){
		return tulos;
	}
	/*
	 * Clears the operation and number lists and result
	 */
	public void nollaa(){
		eka = 0;
		tulos = 0;
		arvot.clear();
		merkit.clear();
	}
}
