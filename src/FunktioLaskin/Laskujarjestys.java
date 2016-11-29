package FunktioLaskin;

import java.util.ArrayList;
import java.util.Objects;

public class Laskujarjestys {
	private Laskin laskin;
	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private int eka = 0;
	//private double tulos = 0;
	//private String valitulos;
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
	public double laske(ArrayList<Double> arvotList, ArrayList<String> merkitList){
		laskin.nollaa();
		System.out.println(laskin.annaTulos()+ "laske");
		setArvotList(arvotList);
		setMerkitList(merkitList);
		/*
		 * Checks witch operation is next to calculate and calculates it.
		 * Uses arrays to calculate right numbers and operations
		 */
		for (int i = 0; i < merkit.size(); i++){
			if (Objects.equals(merkit.get(i), "N")){
				laskin.neliojuuri((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (Objects.equals(merkit.get(i), "cos")){
				laskin.cos((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (Objects.equals(merkit.get(i), "sin")){
				laskin.sin((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
			else if (Objects.equals(merkit.get(i), "tan")){
				laskin.tan((double) arvot.get(i));
				arvot.set(i, laskin.annaTulos());
				eka = 1;
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (Objects.equals(merkit.get(i), "^")){
				if (Objects.equals(eka, 0)){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.potenssi((double) arvot.get(i));
			}
			else if (Objects.equals(merkit.get(i), "*")){
				if (Objects.equals(eka, 0)){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.kerro((double) arvot.get(i));
			}
			else if (Objects.equals(merkit.get(i), "/")){
				if (Objects.equals(eka, 0)){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.jaa((double) arvot.get(i));
			}
		}
		for (int i = 0; i < merkit.size(); i++){
			if (Objects.equals(merkit.get(i), "+")){
				if (Objects.equals(eka, 0)){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					System.out.println(arvot);
					eka = 1;
				}
				laskin.lisaa((double) arvot.get(i));

			}
			else if (Objects.equals(merkit.get(i), "-")){
				if (Objects.equals(eka, 0)){
					laskin.lisaa((double) arvot.get(i));
					arvot.remove(i);
					eka = 1;
				}
				laskin.vahenna((double) arvot.get(i));
			}
		}
		//Asetetaan tulos kontrollerille
		System.out.println(laskin.annaTulos()+ "k?"+arvot+", "+merkit);
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
	/*public String getValiTulos(){
		valitulos = Double.toString(tulos);
		tulos = 0;
		return valitulos;
	}*/
	/*
	 * Clears the operation and number lists and result
	 */
	public void nollaa(){
		eka = 0;
		arvot.clear();
		merkit.clear();
		System.out.println(arvot +""+ merkit+" nollattu?");

	}
	public void setEka(int i){
		eka = i;
	}

}
