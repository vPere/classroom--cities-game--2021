---
writer: vPere
coder: vPere
---
# Funcionalitat 1

Escriu aquí el post de la funcionalitat descrita a l'enunciat.
No canviïs el nom del fitxer ni el títol del post.

Carregar un vaixell de patates

## Carregar un vaixell de patates
### Cal un jugador jugant a la ciutat

* Go to the next player "leonard"
* Go to the "leonard" "city" "Capital".

## Calen que les patates existeixi:
### Afegir a ResourcedTyped

* The resource “potato” round increment should be 0.

## Cal que hi hagi un edifici de plantació de patates
### Posar un cost, ex: 5 bananes

* Given that the "leonard" "city" "Capital" has resource “banana” count 5.

### Afegir buildings.factory de PotatoPlantationFactory (ex HouseFactory) i que sigui de tipus building

* Go to the contained "building" "Builder”.
* Build the “Potato plantation".

### Veure que funciona

* There should contain the “building” “Potato plantation”.
* The resource “potato” round increment should be 1.
* The resource “potato” count should be 0.
* The resource “banana” count should be 5.
* Skip 1 rounds.
* The resource “potato” count should be 1.

## Cal que el vaixell tingui un deck de PatatesStore
### Anar al vaixell

* Go to see the docked "leonard" "ship" "Beagle".

### Posar un cost, ex: 4 of wood

* Given that the "leonard" "city" "Capital" has resource “wood” count 4.
* Given that the "leonard" "city" "Capital" has resource “potato” count 5.

### Afegir un decks.factories BananaStoreFactory de tipus deck

* The resource “potato” maximum should be 0.
* Go to the contained "deck" "Builder".
* Build the “Potato store”.

### Comprovar que ha funcionat

* There should contain the "deck" “Potato store".
* The resource “potato” maximum should be 5.
* The resource “potato” count should be 0.
* Load 5 of “potato"
* The resource “potato” count should be 5.
* The dock resource “potato” count should be 0.
