#!/bin/sh
rm *.class
clear
echo "\n\t\t 2048 THE CONSOLE GAME !!!\n"

if  !(ls | grep class >+ game.log) ; then
	echo "\n\t Compilation..."
	javac C2048Jeu.java
	else
		echo "compilation déjà faite" > game.log
fi

echo "\n\t lancement du jeu\n"
java C2048Jeu
echo "\n\t fin du jeu\n"
sleep 2
#echo "\t recommencer?\n"

#cat $1
#if ($1 <50); then
	#clear
#	echo "\t recommencer?\n"
#fi