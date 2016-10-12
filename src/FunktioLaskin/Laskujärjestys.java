package FunktioLaskin;

import java.util.ArrayList;

public class Laskujärjestys {
	private Laskin laskin;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	ArrayList<String> emerkit;
	private int eka = 0;
	private double tulos = 0;

	public Laskujärjestys(){
		laskin = new Laskin();
		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
		emerkit = new ArrayList<String>();

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
		//Asetetaan tulos kontrollerille
		tulos = laskin.annaTulos();
	}
	public void setMerkitList(ArrayList<String> Merkit){
		this.merkit = Merkit;
	}
	public void setArvottList(ArrayList<Double> Arvot){
		this.arvot = Arvot;
	}

	public double getVäliTulos(){
		return tulos;
	}
	public void nollaa(){
		eka = 0;
		tulos = 0;
		arvot.clear();
		merkit.clear();
	}
}
