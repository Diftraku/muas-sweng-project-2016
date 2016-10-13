package FunktioLaskin;

import java.util.ArrayList;

public class Control {

	private Laskin laskin;
	private Main main;
	private Laskujarjestys laske;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	ArrayList<String> emerkit;
	private String edellinen = "tyhja";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;
	private double Tulos;

	public Control(){
		laskin = new Laskin();
		main = new Main();
		laske = new Laskujarjestys();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
		emerkit = new ArrayList<String>();
	}

	public void nollaa(){
		laskin.nollaa();
		edellinen = "tyhja";
		laske.nollaa();
		arvot.clear();
		merkit.clear();
		Tulos = 0;
	}

	public void laske(){
		laske.setArvottList(arvot);
		laske.setMerkitList(merkit);
		laske.laske();
		Tulos = laske.getValiTulos();
	}
	public void laske2(){
		for (int i = 0; i < emerkit.size(); i++){
			if (emerkit.get(i) == "N"){
				laskin.neliojuuri(arvot.get(i));
			}
		}
	}

	public void setLuku(String value){
		if (edellinen == "tyhja"){
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

	public double getTulos(){
		return Tulos;
	}
}

