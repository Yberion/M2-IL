### Quelle est la norme de codage à laquelle se réfère le rapport par défaut ?

Il se réfère à la norme de sun définit dans le fichier ``sun_checks.xml`` (Sun Microsystems Definition).

### 1/ Comment imposer la norme de codage de Google ?

Ajouter au plugin :

```xml
<configuration>
    <configLocation>checkstyle.xml</configLocation>
</configuration>
```

### 2/ Modifiez votre classe du projet tp1 de façon à diminuer le nb d'erreur ?

Modification du code pour suivre la convention de google.

Suppression d'espaces et autres modifications (tout n'a pas été corrigé).

### Quelle est la valeur ajoutée de ce plugin ? En particulier, montrez sa complémentarité avec CheckStyle

Permet d'avoir les références croissées sur les classes du projet. Permet aussi de passer du rapport CheckStyle au code source en cliquant sur le numéro de ligne associé au commentaire CheckStyle.


