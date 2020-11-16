# Correction contrôle

## Partie 1 : Questions à réponses ouvertes courtes

### Question 1 : Quelles sont les propriétés apportées par ``RecursiveAction`` par rapport à ``ExecutorService`` ?

Construction davantage structurée qui apporte le ``Fork-Join`` (synchronisation plus élevée) comparé au ``ExecutorService`` qui n'apporte pas de structuration.

### Question 2 : Quels sont les avantages de ``Decorator`` par rapport à l’extension d’une classe par héritage de méthode directe (p. ex. sous-classer directement ``ArrayList``) ?

L'avantage du pattern ``Decorator`` est le découplage par rapport au couplage fort de l'héritage, on ne dépend plus du code existant.

## Partie 2 : Conception d’architecture

### Question 1 : Quel(s) patron(s) de conception vous semblent bien adapté(s) pour faciliter le changement de fonction de hachage ?

Utilisation du pattern stratégie.

### Question 2 : Quel(s) patron(s) de conception vous semblent bien adapté(s) pour faciliter le changement de fonction de hachage ?

Pour le pattern ``Strategy`` on a les rôles suivants :

- Strategy : Hashoir
- ConcreteStrategy : HashoirImpl
- Context (ce qui appelle Hashoir le ``Strategy``) : Launcher

Pour le pattern ``Active Object`` on a les rôles suivants :

- Proxy : ``Launcher``
- Future : ``Future``
- Callable : ``MethodInvocation`` (une lambda)
- Scheduler, Active Queue : ``ExecutorService``
- Servant : ``Hashoir``
- Client : ``LauncherTest``

__TODO__ : Faire un diagramme de classe.

### Question 3 : Donnez une mise en oeuvre de ``HashoirImpl``.

__TODO__ : Faire l'implémentation de ``HashoirImpl`` (avec une ``BlockingQueue`` de ``String`` en input et une ``BlockingQueue`` de ``Pair<String, String>`` en output).

### Question 4 : Donnez une implémentation Java de ``Launcher`` qui emploie la mise en oeuvre de ``Active Object`` faite dans la bibliothèque standard Java (cf le TP Observer asynchrone).

Les principes :

Dans ``Launcher`` :

- On récupère les ``BlockingQueue`` (input et output)
- On instancie un ``ExecutorService`` avec ``parallelCount`` threads
- On fait une boucle de 1 à ``parallelCount`` où on submit une lamba qui appelle la fonction de hachage qui lit input et écrit dans output
- Pas de problème d'accès concurrent entre les ``Callable`` qui s'exécutent en parallèle car la ``BlockingQueue`` gère les accès concurrents (est donc Thread Safe)

### Question 5 : Expliquez pourquoi la classe de test ``LauncherTest`` donnée en exemple emploie un ``ExecutorService`` dans sa méthode ``setup()``.

__TODO__ : Répondre à la question.

### Question 6 : Discutez les problèmes soulevés par le codage d’un oracle pour la classe de test ``LauncherTest``.

On veut vérifier que tous les mots de passe sont bien traités et hachés, de 0 à ``TOTAL_PASSWORD_COUNT``.

On a des activités parallèles, ce qui implique que c'est non-déterministe donc les résultats peuvent être différents en fonction des exécutions, mais correct d'une exécution à l'autre, ce qui rend l'exécution de l'oracle compliqué. On pourrait faire un tri des données reçues pour avoir le bon ordre d'exécution et vérifier auprès de l'oracle.

### Question 7 :  Attention : ne répondez à cette question que si vous avez fourni une réponse aux questions 1 à 6 (IL et ILA) ou 1 à 4 (CCN et CCNA). Donnez une implémentation Java de ``Launcher`` qui emploie la mise en oeuvre de ``Fork-Join`` de la bibliothèque standard Java.

- On aurait pu faire une sous classe de ``RecursiveAction``
- On crée une instance mère qui va créer ``parallelCount`` d'instances fille qui vont appeler la ``Strategy``

__TODO__ : Faire l'implémentation.
