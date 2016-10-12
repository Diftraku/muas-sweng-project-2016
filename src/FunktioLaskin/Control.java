package FunktioLaskin;

import java.util.ArrayList;

public class Control {

	private Laskin laskin;
	private Main main;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	ArrayList<String> emerkit;
	private String edellinen = "tyhjä";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;
	private int eka = 0;

	public Control(){
		laskin = new Laskin();
		main = new Main();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
		emerkit = new ArrayList<String>();
	}

	public double getTulos(){
		return laskin.annaTulos();
	}
	public void nollaa(){
		laskin.nollaa();
		edellinen = "tyhjä";
		eka = 0;
		arvot.clear();
		merkit.clear();
	}
	public void laske(){

		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "^"){
				if (eka == 0){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.potenssi((double) arvot.get(i));
			}
			if (merkit.get(i) == "*"){
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
		//Asetetaan tulos käyttöliittymälle
		//main.setTulos(laskin.annaTulos());
	}
	public void laske2(){
		for (int i = 0; i < emerkit.size(); i++){
			if (emerkit.get(i) == "N"){
				laskin.neliojuuri(arvot.get(i));
			}
		}
	}

	public void setLuku(String value){
		if (edellinen == "tyhjä"){
			luku = value;
		}
		else if (edellinen == "merkki"){
			luku = value;
		}
		else{
			luku+=value;
		}
		edellinen = "numero";
	}
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
	public void setMerkki(String merkki){
		if (edellinen == "numero"){
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
	public void sulut(String sulku){
		if (sulku == "("){

		}
	}
	public void setEmerkki(String string){
		emerkit.add(string);
	}
}

