# TP1 : Docker

## Etape 1: se familiariser avec Docker

### Deploying Your First Docker Container

<https://katacoda.com/courses/docker/deploying-first-container>

Faire une recherche d'une image :

```docker search redis```

Démarrer une image en background avec `-d` :

```docker run -d redis```

Si on veut une version spécifique on ajoute un tag :

```docker run -d redis:3.2```

Lister tous les containers (image utilisé, temps depuis le démarrage, nom, ID, ...) :

```docker ps```

On peut inspecter le container pour avoir plus d'information comme l'IP par exemple :

```docker inspect <friendly-name|container-id>```

On peut afficher les messages que le container a écrit dans la sortie standard et erreur :

```docker logs <friendly-name|container-id>```

On peut démarrer le container en indiquant un nom et on peut rendre un port de notre container disponible sur notre host avec `-p <host-port>:<container-port>` :

```docker run -d --name redisHostPort -p 6379:6379 redis:latest```

Le forwarding du port est fait sur `0.0.0.0` ce qui rend le port disponible sur toutes les IP. On peut spécifier une adresse IP lors de la création du container :

```docker run -d --name redisHostPort -p 127.0.0.1:6379:6379 redis:latest```

Le problème d'indiquer le forward du container vers le host c'est qu'on ne peut avoir qu'une instance. On peut `randomizer` le port du host en indiquant qu'un seul port (celui du container qu'on veut mapper) :

```docker run -d --name redisDynamic -p 6379 redis:latest```

Pour voir le port assigné sur le host :

```docker port redisDynamic 6379```

On peut aussi avoir cette information via :

```docker ps```

Les containers sont designés pour être sans état. Il est possible de binder un dossier du host vers un dossier du container via ``-v <host-dir>:<container-dir>``, pour `Redis` les données sont sauvegardées dans un dossier `/data`, si une donnée doit être sauvegardée sur le host cela doit se faire dans `/opt/docker/data/redis` :

```docker run -d --name redisMapped -v /opt/docker/data/redis:/data redis```

Docker autorise l'utilisation de `$PWD` pour indiquer le dossier courant :

```docker run -d --name redisMapped -v "$PWD/data":/data redis```

Démarre un container Ubuntu et exécute la commande `ps` :

```docker run ubuntu ps```

Démarre un container Ubuntu et donne l'accès au bash :

```docker run -it ubuntu bash```

### Deploy Static HTML Website as Container

<https://katacoda.com/courses/docker/create-nginx-static-web-server>

Création d'un `Dockerfile` nginx.

Les images Docker commencent depuis une image de base qu'on peut définir avec `FROM image_de_base`.

Dans notre exemple de `Dockerfile` :

```dockerfile
FROM nginx:alpine
COPY . /usr/share/nginx/html
```

La seconde ligne copie le contenu du dossier courant vers un dossier du contener, ici `/usr/share/nginx/html`.

Le fichier `Dockerfile` est utilisé par la commande `build` de `Docker CLI` et permet de construire une image Docker à partir du fichier `Dockerfile`.

On peut construire une image en lui indiquant un nom et un tag de version :

```docker build -t webserver-image:v1```

Lister toutes les images sur le host :

```docker images```

On peut ensuite démarrer l'image créée :

```docker run -d -p 80:80 webserver-image:v1```

On peut accéder à la réponse du serveur par :

```curl docker```

### Building Container Images

Création d'un `Dockerfile`.

Il doit commencer par une image de base :

```dockerfile
FROM nginx:1.11-alpine
```

Il y a deux commandes importantes :

- `RUN <command>` : permet d'exécuter une commande comme si c'était dans la console, par exemple l'installation de package
- `COPY <source> <destination>` : permet de faire une copie du dossier où est contenu le `Dockerfile` vers le container de l'image

Par exemple pour un fichier `index.html` qui est au même endroit que le `Dockerfile`, si on veut le copier vers le container de l'image `/usr/share/nginx/html` :

```dockerfile
FROM nginx:1.11-alpine
COPY index.html /usr/share/nginx/html/index.html
```

À noter que si c'est un fichier la destination doit contenir le nom du fichier aussi.

Pour exposer un port, pour qu'il puisse être accessible sur notre host et qu'on puisse le forward sur le host, il faut utiliser la commande `EXPOSE`, par exemple pour exposer le port 80 :

```dockerfile
FROM nginx:1.11-alpine
COPY index.html /usr/share/nginx/html/index.html
EXPOSE 80
```

Pour définir des commandes qui doivent être exécutées au démarrage du container de cette image, il faut utiliser la commande `CMD`.
Si la commande a besoin d'arguments il est recommandé d'utiliser un tableau, par exemple `["a_cmd", "-a", "arga value", "-b", "argb-value"]` ce qui donnera `cmd -a "arga value" -b argb-value`.

La commande pour démarrer nginx est `nginx -g daemon off;`, ce qui donne :

```dockerfile
FROM nginx:1.11-alpine
COPY index.html /usr/share/nginx/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```
