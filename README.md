# muas-sweng-project-2016
Metropolia University of Applied Sciences - Software Engineering Project 2016

[![Build Status](https://travis-ci.org/Diftraku/muas-sweng-project-2016.svg?branch=master)](https://travis-ci.org/Diftraku/muas-sweng-project-2016)
[![Coverity Status](https://scan.coverity.com/projects/10815/badge.svg)](https://scan.coverity.com/projects/diftraku-muas-sweng-project-2016)

Kyseessä on monifunktiolaskin, jolla on tarkoitus pystyä suoraan käyttämään hankaliakin kaavoja suoraan keskellä funktioita. Tämä tarkoittaa sitä että kaavoja voi poistaa ja lisätä mielivaltaisesti käyttäjän valitsemalle paikalle. Kaavojen muuttujiin pääsy tehdään helpoksi käyttöliittymään. Muuttujat näytetään erillisessä listassa laskutoimituksen vierellä, jossa niihin voi antaa arvoja. Myös kosketusnäytön ominaisuudet otetaan huomioon.

Tällä hetkellä ohjelmassa toimii laskennalliset perustoiminnot, kuten yhteen-ja vähennyslaskut, jako-ja kertolaskut ja potenssit. Käyttöliittymästä on olemassa alpha-versio. Tietokannan valmistuttua sinne lisätään laskukaavat, josta ne haetaan itse ohjelmaan. Käyttäjä voi lisätä tietokantaan myös omia funktioita.

	

Ohjelma toimii MVC-mallin mukaan, siten että käyttöliittymä lähettää napinpainallukset kontrollerille. Kontrolleri luo toistaiseksi kaksi array-listaa, johon se tallentaa numerot ja toiseen merkit. Ohjelman kehittyessä muutamme sen luomaan pino- tai puutoteutus, johon merkit saadaan tallennettua. Kontrolleri paloittelee koodin sulkumerkkien ja erikoislaskutoimitusten mukaan osiin, jotka se lähettää yksitellen laskujärjestys luokalle. Laskujärjestysluokka laskee laskin luokan avulla siihen lähetetyn osan laskua ja palauttaa vastauksen haluttuun kohtaan laskutoimitusta. 

Esimerkiksi laskutoimitus x-z*(a+s) paloitellaan siten että sulkujen sisällä oleva osa viedään ensin laskujärjestysluokkaan joka palauttaa (a+s) vastauksen d. Vastaus sijoitetaan alkuperäiseen laskuun omalle paikalleen, jolloin lasku näyttää tältä x-z*d. Lasku lähetetään kokonaan Laskujärjestys luokalle joka palauttaa lopullisen vastauksen.
