#!/bin/sh

clear
echo "\n\t\t 2048 THE CONSOLE GAME !!!\n"

if  !(ls | grep class > game.log) ; then
	echo "\n\t Compilation..."
	javac C2048Jeu.java
	else
		echo "compilation déjà faite" > game.log
fi

echo "\n\t lancement du jeu"
java C2048Jeu
echo "\n\t\t fin du jeu\n"