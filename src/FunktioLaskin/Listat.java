package FunktioLaskin;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Listat {

	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private String edellinen = "tyhja";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;
	/*
	 * Sets last number so it can be used in the next calculation
	 */

	public Listat(){

		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
	}
	public void nollaa(){
		edellinen = "tyhja";
		arvot.clear();
		merkit.clear();
		miinus = 0;
		luku1 = 0;
		luku = null;
	}
	public void setLuku(String value){
		if (edellinen == "tyhja"){
			luku = value;
			edellinen = "numero";
		}
		else if (edellinen == "merkki"){
			luku = value;
			edellinen = "numero";
		}
		else if (edellinen == "numero"){
			luku+=value;
			edellinen = "numero";
		}
		else if (edellinen == "emerkki"){
			luku = value;
			edellinen = "enumero";
		}
		else if (edellinen == "enumero"){
			luku+=value;
			edellinen = "enumero";
		}
	}
	/*
	 * Sets a number in the number array
	 */
	public void setArvo(String arvo){
		try {
			luku1 = Double.parseDouble(arvo);
	   }catch (NumberFormatException e){
	       System.out.println(arvo);
	   }
			if (miinus == 0){
				arvot.add(luku1);
			}
			else if (miinus == 1){
				arvot.add(0-luku1);
			}
		}
	public void addArvo(Double arvo){
		arvot.add(arvo);
	}
	/*
	 * Sets operation in the operation array
	 */
	public void setEmerkki(String merkki){
			merkit.add(merkki);
			edellinen = "enumero";
	}
	public void setMerkki(String merkki){
		if (edellinen == "tyhja"){
			edellinen = "numero";

		}
		else if (edellinen == "numero" || edellinen == "enumero"){
			if (merkki == "="){
				setArvo(luku);
				edellinen = "numero";
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
		 * Gets the operations list from this class
		 */

		public ArrayList<String> getMerkitList(){
			return merkit;
		}
		/*
		 * Gets the operations list from this class
		 */
		public ArrayList<Double> getArvotList(){
			return arvot;
		}
		/*
		 * Sets Pii to arvot()
		 */
		public void setPii(){
			if (miinus == 0){
				arvot.add(Math.PI);
			}
			if (miinus == 1){
				arvot.add(0-Math.PI);
			}
		}

	}

