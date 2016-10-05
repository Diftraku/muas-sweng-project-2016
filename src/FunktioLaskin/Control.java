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
	private int useDes = 0;

	public void Control(){
		laskin = new Laskin();
		main = new Main();
		arvot = new ArrayList();
		merkit = new ArrayList();
	}

	public double getTulos(){
		return laskin.annaTulos();
	}
	public void laske(){
		for (int i = 0; i <= merkit.size(); i++){
			if (merkit.get(i) == "*"){
				laskin.setTulos((double) arvot.get(i));
				laskin.kerro((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			if (merkit.get(i) == "/"){
				laskin.setTulos((double) arvot.get(i));
				laskin.jaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
		}
		for (int i = 0; i <= merkit.size(); i++){
			if (merkit.get(i) == "+"){
				laskin.setTulos((double) arvot.get(i));
				laskin.lisaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			if (merkit.get(i) == "-"){
				laskin.setTulos((double) arvot.get(i));
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
		if (edellinen == "numero"){
			luku.concat(value);
		}
		if (edellinen == "merkki"){
			luku = value;
		}
		edellinen = "numero";
	}
	public void setArvo(String arvo){
		luku1 = Double.parseDouble(arvo);
			if (miinus == 0){
				arvot.add(luku1);
			}
			if (miinus == 1){
				arvot.add(0-luku1);
			}
		}
	public void setMerkki(String merkki){
		if (edellinen == "numero"){
				setArvo(luku);
				merkit.add(merkki);
				edellinen = merkki;
				miinus = 0;
		}

		else{
			if (merkki == "erotus"){
				miinus = 1;
				edellinen = merkki;
			}
			else{
				//Virheilmoitus
			}
		}
	}
}

