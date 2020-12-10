# Questions et généralités sur le TP AOC

## Remarques

- Diagramme de classes

La référence c'est ceux du TD, à compléter.

- Par rapport au sujet : on a enlevé les interfaces "du haut" : `Subject` et `Observer`

Le paramètre de `update()` est CapteurAsync.
Le paramètre de `attach()`/`detach()` est CapteurAsync.

Le causale c'est pareil qu'Atomique sans le `lock()`/`unlock()`, l'estampille c'est la valeur.

## Configuration

Classe `App` : elle crée les composants

- 1 capteur
- 1 strategy (dans les 3)
- 4 afficheurs (loggeurs + enregistreurs)
- 4 canaux

L'interconnexion de ces composants via soit les contructeurs soit des setters.

Qui créé le ScheduledExecutorService ? -> Dans `App`.
