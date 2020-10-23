# TP1 : SUJET OBSERVER

## Conseils de mise en oeuvre

### Classe de configuration (Main)

- Créer le capteur (instance de ``CapteurImpl``)
- Créer 4 canaux de type ``Canal``
- Créer 4 afficheurs, chaque afficheur écrit dans la console (avec Logger.GetGlobal().info(...)) et enregistre dans une liste de valeur reçue
- Connecter tout ça
- Commencer par les cohérences atomique (Pattern Strategy)
- Point important : pas besoin de déclarer Update et GetValue car on a les lambdas
- Pas IHM, à la place un oracle
- Attention à employer sufficament de threads

### Ecriture de l'oracle

Après arrêt de l'exécution (après une 100ène de tick par exemple) :

- Comparaison des traces enregistrées par chaque afficheur
- On ne retient que le plus court préfixe (on prend l'afficheur qui à le moins de valeur, si un 90, un autre 95 et un autre 105, on prend 90 || la longueur la plus courte L parmi les afficheurs)
- Atomique : toutes les traces sont égales à 1, 2, 3, ... L
- Séquentielle : toutes les traces sont croissantes et identique
- A étpoque ou causale : toutes les traces sont strictement croissantes
