package FunktioLaskin;

/*
 * Controller class
 */
public class Control {
    private static final Control instance = new Control();

    private Laskin laskin;
    private Main main;
    private Laskujarjestys laske;
    private Listat lista;
    private Listat lista1;
    private Listat lista2;
    private Listat lista3;
    private double Tulos;
    //private double vanha;
    private int sulut = 0;
    private double Valitulos;

    /*
     * Constructor
     */
    private Control() {
        laskin = new Laskin();
        main = new Main();
        laske = new Laskujarjestys();
        lista = new Listat();
        lista1 = new Listat();
        lista2 = new Listat();
        lista3 = new Listat();
    }

    public static Control getInstance() {
        return instance;
    }

    /*
     * clears the calculator
     */
    public void nollaa() {
        //vanha = Tulos;
        laskin.nollaa();
        laske.nollaa();
        Tulos = 0;
        lista.nollaa();
        lista1.nollaa();
        lista2.nollaa();
        lista3.nollaa();
        sulut = 0;
        Valitulos = 0.0;
        //edellinen = "tyhja";
        //arvot.clear();
        //merkit.clear();
        //miinus = 0;
        //luku1 = 0;
        //luku = null;
    }

    /*
     * Counts the calculation in the right order
     */
    public void laskelopputulos() {
        /*laske.setArvotList(lista.getArvotList());
		laske.setMerkitList(lista.getMerkitList());*/
        System.out.println("laskee lopputuloksen" + lista.getArvotList() + ", " + lista.getMerkitList());
        Tulos = laske.laske(lista.getArvotList(), lista.getMerkitList());
    }

    public void setMerkki(String value) {
        System.out.println(sulut);
        if (sulut == 0) {
            lista.setMerkki(value);
        } else if (sulut == 1) {
            lista1.setMerkki(value);
        } else if (sulut == 2) {
            lista2.setMerkki(value);
        } else if (sulut == 3) {
            lista3.setMerkki(value);
        }
    }

    public void setLuku(String value) {
        System.out.println(sulut);
        if (sulut == 0) {
            lista.setLuku(value);
        } else if (sulut == 1) {
            lista1.setLuku(value);
        } else if (sulut == 2) {
            lista2.setLuku(value);
        } else if (sulut == 3) {
            lista3.setLuku(value);
        }
    }

    public void setEmerkki(String value) {
        System.out.println(sulut);
        if (sulut == 0) {
            lista.setEmerkki(value);
        } else if (sulut == 1) {
            lista1.setEmerkki(value);
        } else if (sulut == 2) {
            lista2.setEmerkki(value);
        } else if (sulut == 3) {
            lista3.setEmerkki(value);
        }
    }

    /*
     * Check if there are brackets (In progress)
     */
    public void sulut(String sulku) {
        if (sulku == "(") {
            sulut++;
        } else if (sulku == ")") {
            if (sulut == 1) {
                lista1.setMerkki("=");
                laske.setEka(0);
				/*laske.setArvotList(lista1.getArvotList());
				laske.setMerkitList(lista1.getMerkitList());*/
                Valitulos = laske.laske(lista1.getArvotList(), lista1.getMerkitList());
                lista.addArvo(Valitulos);
                System.out.println(Valitulos);
                System.out.println(lista.getArvotList());
                Valitulos = 0;
                lista1.nollaa();
                laske.nollaa();
                laskin.nollaa();
                laske.setEka(0);
                lista.setEdellinenSulut();

            } else if (sulut == 2) {
                lista2.setMerkki("=");
                laske.setEka(0);
                //laske.setArvotList(lista2.getArvotList());
                //laske.setMerkitList(lista2.getMerkitList());
                //lista1.addArvo(laske.laske());
                Valitulos = laske.laske(lista2.getArvotList(), lista2.getMerkitList());
                lista1.addArvo(Valitulos);
                lista2.nollaa();
                laske.nollaa();
                laskin.nollaa();
                laske.setEka(0);
                lista1.setEdellinenSulut();
            } else if (sulut == 3) {
                lista3.setMerkki("=");
                laske.setEka(0);
                //laske.setArvotList(lista3.getArvotList());
                //laske.setMerkitList(lista3.getMerkitList());
                //lista2.setLuku(Double.toString(laske.laske()));
                Valitulos = laske.laske(lista3.getArvotList(), lista3.getMerkitList());
                lista2.addArvo(Valitulos);
                lista3.nollaa();
                laske.nollaa();
                laskin.nollaa();
                laske.setEka(0);
                lista2.setEdellinenSulut();
            } else {
                System.out.println("Shit happens");
            }
            sulut--;
        }
    }


    public void setPii() {
        if (sulut == 0) {
            lista.setPii();
        } else if (sulut == 1) {
            lista1.setPii();
        } else if (sulut == 2) {
            lista2.setPii();
        } else if (sulut == 3) {
            lista3.setPii();
        }
    }

    /*
     * returns the result
     * @return Tulos Tulos is the result of the calculation
     */
    public double getTulos() {
        return Tulos;
    }
}

