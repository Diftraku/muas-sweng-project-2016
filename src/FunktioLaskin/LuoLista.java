package FunktioLaskin;

import java.util.ArrayList;
import java.util.Objects;

public class LuoLista {

	ArrayList<Double> arvot;
	ArrayList<String> merkit;
	private String edellinen = "tyhja";
	private int miinus = 0;
	private String luku;
	private double luku1 = 0;
	private String value;
	private int number = 0;
	private int loppu = 0;
	/*
	 * Sets last number so it can be used in the next calculation
	 */

	public LuoLista(){

		arvot = new ArrayList<Double>();
		merkit = new ArrayList<String>();
	}
	/*
	 * Clears the operation and number lists and result
	 */
	public void nollaa(){
		edellinen = "tyhja";
		arvot.clear();
		merkit.clear();
		miinus = 0;
		luku1 = 0;
		luku = "";
		number = 0;
		value = "";
		loppu = 0;
		System.out.println(arvot +""+ merkit+" nollattu?");
	}

	/*
	 * Sets values to methods witch makes lists
	 */
	public void luolista(String Value){
		nollaa();
		while (Objects.equals(loppu, 0)){
			if (Objects.equals(value, "=") || (Objects.equals(value, ")"))){
				loppu = 1;
			}
			else{
			value = String.valueOf(Value.charAt(number));
			number++;
			System.out.println(value);
			if (Objects.equals(value, "/") || Objects.equals(value, "*") || Objects.equals(value, "+") || Objects.equals(value, "-") || Objects.equals(value, "^")|| Objects.equals(value, "=") || Objects.equals(value, ")") || Objects.equals(value, "(")) {
	            setMerkki(String.valueOf(value));
	            System.out.println("merkki "+value);
	        } else if (Objects.equals(value, "N") || Objects.equals(value, "sin") || Objects.equals(value, "tan") || Objects.equals(value, "cos")) {
	            setEmerkki(String.valueOf(value));
	        } else if (Objects.equals(value, "Pii")) {
	        	setPii();
	        } else if (Objects.equals(value, "0") || Objects.equals(value, "1") || Objects.equals(value, "2") || Objects.equals(value, "3") || Objects.equals(value, "4") || Objects.equals(value, "5") || Objects.equals(value, "6") || Objects.equals(value, "7") || Objects.equals(value, "8") || Objects.equals(value, "9") || Objects.equals(value, ".")) {
	            setLuku(String.valueOf(value));
	            System.out.println("luku "+value);
	        }
			}
		}

	}

	/*
	 *  Saves numbers to string, so that biger numbers and decimals are possible to save
	 */

	public void setLuku(String value){
		if (Objects.equals(edellinen, "tyhja")){
			luku = value;
			edellinen = "numero";
		}
		else if (Objects.equals(edellinen, "merkki")){
			luku = value;
			edellinen = "numero";
		}
		else if (Objects.equals(edellinen, "numero")){
			luku+=value;
			edellinen = "numero";
		}
		else if (Objects.equals(edellinen, "emerkki")){
			luku = value;
			edellinen = "enumero";
		}
		else if (Objects.equals(edellinen, "enumero")){
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
			if (Objects.equals(miinus, 0)){
				arvot.add(luku1);
			}
			else if (Objects.equals(miinus, 1)){
				arvot.add(0-luku1);
			}
		}
	public void addArvo(Double arvo){
		arvot.add(arvo);
	}
	/*
	 * Sets operation in the operation array and sends numbers to method witch adds them to array
	 */
	public void setEmerkki(String merkki){
			merkit.add(merkki);
			edellinen = "enumero";
	}
	/*
	 * Sets operation in the operation array and sends numbers to method witch adds them to array
	 */
	public void setMerkki(String merkki){
		if (Objects.equals(edellinen, "tyhja")){
			edellinen = "numero";

		}
		else if ((Objects.equals(edellinen, "numero")) || (Objects.equals(edellinen, "enumero"))){
			if ((Objects.equals(merkki, "=")) || (Objects.equals(merkki, ")"))){
				setArvo(luku);
				edellinen = "numero";
				miinus = 0;
			}
			else if (Objects.equals(merkki, "(")){
				setArvo(luku);
				merkit.add("*");
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
		else if (Objects.equals(edellinen, "sulut")){
				merkit.add(merkki);
				edellinen = "merkki";
				miinus = 0;
		}
		else if (Objects.equals(edellinen, "merkki")){
			if (Objects.equals(merkki, "(")) {
				System.out.println("nothing");
			}
			}
		else{
			if (Objects.equals(edellinen, "-")){
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
		/*
		 * Sets edellinen to sulut
		 */
		public void setEdellinenSulut(){
			edellinen = "sulut";
		}

	}

