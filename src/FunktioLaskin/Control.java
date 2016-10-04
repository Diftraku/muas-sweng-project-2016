package FunktioLaskin;

import java.util.ArrayList;

public class Control {

	private Laskin laskin;
	private Main main;
	ArrayList arvot;
	ArrayList merkit;
	private String edellinen = "tyhjä";
	private int miinus = 0;

	public void Control(){
		laskin = new Laskin();
		main = new Main();
		arvot = new ArrayList();
		merkit = new ArrayList();
	}

	public void laske(){
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "kerto"){
				laskin.setTulos((double) arvot.get(i));
				laskin.kerro((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			if (merkit.get(i) == "jako"){
				laskin.setTulos((double) arvot.get(i));
				laskin.jaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (merkit.get(i) == "summa"){
				laskin.setTulos((double) arvot.get(i));
				laskin.lisaa((double) arvot.get(i+1));
				merkit.remove(i);
				arvot.set(i, laskin.annaTulos());
				arvot.remove(i+1);
			}
			if (merkit.get(i) == "erotus"){
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

	public void setArvo(Double arvo){
		if (miinus == 0){
			arvot.add(arvo);
			edellinen = "numero";
		}
		if (miinus == 1){
			arvot.add(0-arvo);
			edellinen = "numero";
		}
	}
	public void setMerkki(String merkki){
		if (edellinen == "numero"){
			merkit.add(merkki);
			edellinen = merkki;
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
