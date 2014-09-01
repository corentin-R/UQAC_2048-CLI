#!/bin/sh

rm *.class
clear
echo "
\t    ___   ____  __ __  ____    
\t   |__ \ / __ \/ // / ( __ ) 
\t   __/ // / / / // /_/ __  |   
\t  / __// /_/ /__  __/ /_/ /   
\t /____/\____/  /_/  \____/      
                           "

if  !(ls | grep class >> game.log) ; then
	echo "\n\tCompilation..."
	javac C2048Jeu.java
	else
		echo  "compilation déjà faite" >> game.log
fi

echo "\n\tlancement du jeu\n"
java C2048Jeu
echo "\n\tfin du jeu\n"
#sleep 2


while true; do
    read -p "        voulez-vous refaire une partie (oui/non)? " yn
    case $yn in
        [Oo]* ) ./run2048.sh ; break;;
        [Nn]* ) clear ; exit;;
        * ) echo "\t repondez par oui ou non svp.";;
    esac
done

#cette méthode marche bien aussi
#
#echo "Do you wish to install this program?"
#select yn in "Yes" "No"; do
#    case $yn in
#        Yes ) make install; break;;
#        No ) exit;;
#    esac
#done


