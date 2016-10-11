package FunktioLaskin;

import java.util.ArrayList;

public class Control {

	private Laskin laskin;
	private Main main;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private String edellinen = "tyhjä";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;

	public Control(){
		laskin = new Laskin();
		main = new Main();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
	}

	public double getTulos(){
		return laskin.annaTulos();
	}
	public void nollaa(){
		laskin.nollaa();
		edellinen = "tyhjä";
		arvot.clear();
		merkit.clear();
	}
	public void laske(){
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "*"){
				laskin.lisaa((double) arvot.get(i));
				laskin.kerro((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			else if (merkit.get(i) == "/"){
				laskin.lisaa((double) arvot.get(i));
				laskin.jaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "+"){
				laskin.lisaa((double) arvot.get(i));
				laskin.lisaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			else if (merkit.get(i) == "-"){
				laskin.lisaa((double) arvot.get(i));
				laskin.vahenna((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
		}
		//Asetetaan tulos käyttöliittymälle
		//main.setTulos(laskin.annaTulos());
	}

	public void setValue(String value){
		if (edellinen == "tyhjä"){
			luku = value;
		}
		else if (edellinen == "numero"){
			luku.concat(value);
		}
		else if (edellinen == "merkki"){
			luku = value;
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
			if (merkki == "erotus"){
				miinus = 1;
				edellinen = "merkki";
			}
			else{
				//Virheilmoitus
			}
		}
	}
}

