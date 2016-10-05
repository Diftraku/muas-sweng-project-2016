package FunktioLaskin;

import java.util.ArrayList;

public class Control {

	private Laskin laskin;
	private Main main;
	ArrayList arvot;
	ArrayList merkit;
	private String edellinen = "tyhjä";
	private int miinus = 0;
	private String luku;
	private double luku1;
	private int useDes = 0;

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

	public void setValue(String value){
		if (edellinen == "numero"){
			if (luku == "0"){
				luku = value;
			}
			else{
			luku.concat(value);
			}
		}
		if (edellinen == "merkki"){
			luku = value;
		}
		edellinen = "numero";
	}
	public void setArvo(Double arvo){
			if (miinus == 0){
				arvot.add(arvo);
			}
			if (miinus == 1){
				arvot.add(0-arvo);
			}
		}
	public void setMerkki(String merkki){
		if (edellinen == "numero"){
				luku1 = Double.parseDouble(luku);
				setArvo(luku1);
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

