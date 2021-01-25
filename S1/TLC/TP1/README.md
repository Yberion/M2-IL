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

On peut afficher les messages que le container a écrit dans la sortie standard et erreur standard :

```docker logs <friendly-name|container-id>```

On peut démarrer le container en indiquant un nom (ici `redisHostPort` avec comme image `redis:latest`) et on peut rendre un port de notre container disponible sur notre host avec `-p <host-port>:<container-port>` :

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

<https://katacoda.com/courses/docker/2>

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

Pour exposer un port, pour qu'il puisse être accessible sur notre host et qu'on puisse faire un forward sur le host, il faut utiliser la commande `EXPOSE`, par exemple pour exposer le port 80 :

```dockerfile
FROM nginx:1.11-alpine
COPY index.html /usr/share/nginx/html/index.html
EXPOSE 80
```

Pour définir des commandes qui doivent être exécutées au démarrage du container de cette image, il faut utiliser la commande `CMD`.
Si la commande a besoin d'arguments il est recommandé d'utiliser un tableau, par exemple `["a_cmd", "-a", "arga-value", "-b", "argb-value"]` ce qui donnera `cmd -a "arga value" -b argb-value`.

La commande pour démarrer nginx est `nginx -g daemon off;`, ce qui donne :

```dockerfile
FROM nginx:1.11-alpine
COPY index.html /usr/share/nginx/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

Maintenant que le `Dockerfile` est terminé on peut compiler l'image, l'image sera stocké à l'endroit du `Docker Engine`, s'il y a une erreur la compilation s'annule :

```docker build```

On peut aussi lui donner un nom :

```docker build -t my-nginx-image:latest```

Vérifier avec :

```docker images```

On peut maintenant lancer un container à partir de l'image :

```docker run -d -p 80:80 my-nginx-image:latest```

Vérifier avec :

```docker ps```

### Managing Log Files

<https://katacoda.com/courses/docker/8>

Docker permet l'accès à la sortie standard et à l'erreur standard des containers.

Pour afficher les logs :

```docker logs redis-server```

Le formet retourné est du JSON.

On peut rediriger les log vers `syslog` ce qui permet de séparer choses, là il va y avoir une redirection des logs vers `syslog` :

```docker run -d --name redis-syslog --log-driver=syslog redis```

Il faut maintenant y accéder via les `stream syslog`.

Sinon on peut désactiver les logs :

```docker run -d --name redis-none --log-driver=none redis```

On peut afficher le type de log avec la commande (changer `<friendly-name|container-id>` par le nom ou l'id de container) :

```docker inspect --format '{{ .HostConfig.LogConfig }}' <friendly-name|container-id>```

### Ensuring Uptime

<https://katacoda.com/courses/docker/9>

Quand un container s'arrête avec un code différent de `0` Docker considère que c'est un crash, par défaut un container qui s'arrête le reste.

On lance un container spéciale qui retourne un code `1` pour simuler un crash :

```docker run -d --name restart-default scrapbook/docker-restart-example```

On affiche tous les container, même ceux arrêtés avec `-a` :

```docker ps -a```

On peut afficher les logs :

```docker logs restart-default```

```log
Mon Nov 23 16:59:19 UTC 2020 Booting up...
```

On peut indiquer à Docker que l'on veut redémarrer un certain nombre de fois un container qui s'arrête avec une erreur avec le paramètre `--restart=on-failure:#` :

```docker run -d --name restart-3 --restart=on-failure:3 scrapbook/docker-restart-example```

Si on regarde les logs :

```docker logs restart-3```

```log
Mon Nov 23 17:06:59 UTC 2020 Booting up...
Mon Nov 23 17:07:03 UTC 2020 Booting up...
Mon Nov 23 17:07:08 UTC 2020 Booting up...
Mon Nov 23 17:07:11 UTC 2020 Booting up...
```

On peut indiquer de tout le temps le redémarrer avec ``--restart=always`` et ce jusqu'à ce que le container soit explicitement arrêté :

```docker run -d --name restart-always --restart=always scrapbook/docker-restart-example```

### Docker Metadata & Labels

<https://katacoda.com/courses/docker/15>

Il est possible d'ajouter des labels aux containers. Pour ajouter un label on utilise le paramètre `-l label_name=label_value`, ceci permet de faire des requête sur tous les containers avec un/des label spécifique.

Création d'un container avec un label `user` et un ID `12345` :

```docker run -l user=12345 -d redis```

On peut aussi spécifier un fichier qui va contenir plusieurs labels, un label par ligne.

On créé un fichier avec un label `user` et `role` :

```echo 'user=123461' >> labels && echo 'role=cache' >> labels_file```

```text
user=123461
role=cache
```

On utilise ensuite ce fichier pour associer les labels, on utilise le paramètre `--label-file=<filename>` :

```docker run --label-file=labels_file -d redis```

Il est possible d'associer des labels directement dans le `Dockerfile` avec la commande `LABEL`.

Un label `vendor` avec en valeur `Katacoda` :

```dockerfile
LABEL vendor=Katacoda
```

Plusieurs labels, ils doivent être séparés par un `\` :

```dockerfile
LABEL vendor=Katacoda \
com.katacoda.version=0.0.5 \
com.katacoda.build-date=2016-07-01T10:47:29Z \
com.katacoda.course=Docker
```

Un container avec le nom `rd` et une image `katacoda-label-example` sont créés.

Il est possible d'inspecter les labels d'un container, un filtre peut être appliqué avec le paramètre `-f {{json .FILTRE }}` de la commande `docker inspect` :

```docker inspect -f "{{json .Config.Labels }}" rd```

```json
{
    "com.katacoda.created":"automatically",
    "com.katacoda.private-msg":"magic",
    "user":"scrapbook"
}
```

Pour inspecter une image :

```docker inspect -f "{{json .ContainerConfig.Labels }}" katacoda-label-example```

```json
{
    "com.katacoda.build-date":"2015-07-01T10:47:29Z",
    "com.katacoda.course":"Docker",
    "com.katacoda.private-msg":"HelloWorld",
    "com.katacoda.version":"0.0.5",
    "vendor":"Katacoda"
}
```

Si une image est untagged alors il aura le nom `<none>`.

Dans un environnement avec beaucoup de containers et d'images, il est possible de faire des requêtes sur les labels.

Par exemple la commande suivante retournera tous les containers avec le label `user` et sa valeur associée `scrapbook`

```docker ps --filter "label=user=scrapbook"```

De la même manière pour les images :

```docker images --filter "label=vendor=Katacoda"```

Le nom des labels ainsi que la valeur associé est sensible à la casse.

Il est possible d'associer des labels au `Docker Daemon` :

```bash
docker -d \
  -H unix:///var/run/docker.sock \
  --label com.katacoda.environment="production" \
  --label com.katacoda.storage="ssd"
```

### Load Balancing Containers

<https://katacoda.com/courses/docker/10>

Dans ce scénario on veut un service ``NGINX`` qui sera capable de répartir la charge automatiquement sur plusieurs configurations. `nginx-proxy` permet de découvrir et mettre à jour la liste des configurations disponibles.

On veut rediriger toutes les requêtes du port `80` du `host` vers `nginx-proxy`.

La seconde chose est de monter le `docker.sock` qui est une connexion au `Docker daemon` sur la machine `host` ce qui permet aux containers d'accéder aux métadonnées via une API. `NGINX` s'en sert pour écouter les évènements et mettre à jour la configuration d'`NGINX` en fonction de l'adresse IP du container.

Monter un fichier se fait de manière identique à un dossier via `-v /var/run/docker.sock:/tmp/docker.sock:ro`, le `:ro` indique une restriction de lecture seule.

Si une requête ne spécifie pas d'hosts particulier il est possible de définir un container par défaut qui recevra ces requêtes, utilisation de `-e DEFAUL_THOST=<domain>` :

```docker run -d -p 80:80 -e DEFAULT_HOST=proxy.example -v /var/run/docker.sock:/tmp/docker.sock:ro --name nginx jwilder/nginx-proxy```

Maintenant que `NGINX` écoute les évènements, on va tester avec une image, `katacoda/docker-http-server`, qui contient un site simple retournant le nom de la machine et qui tourne en interne avec PHP et Apache2 sur le port 80.

Pour que `NGINX` envoie les requêtes à un container il faut définir la variable d'environnement `VIRTUAL_HOST` lors de la création du container. Il va être identique au `DEFAULT_HOST` défini plus haut pour accepter toutes les requêtes :

```docker run -d -p 80 -e VIRTUAL_HOST=proxy.example katacoda/docker-http-server```

Pour tester utilisez `curl` :

```curl http://docker```

```bash
$ curl http://docker
<h1>This request was processed by host: 43ad3e3039d1</h1>
```

On a un container qui peut gérer les requêtes HTTP. Si on créé un second container pour gérer les requêtes HTTP, `nginx-proxy` va rediriger de façon cyclique (round-robin) les requêtes sur chaque container. Ce qui veut dire que la première requête ira sur le premier container, la seconde sur le second container, la troisième sur le premier container, etc.

Création du second container :

```docker run -d -p 80 -e VIRTUAL_HOST=proxy.example katacoda/docker-http-server```

Et test avec `curl` :

```bash
$ curl http://docker
<h1>This request was processed by host: 20a66e06db69</h1>
$ curl http://docker
<h1>This request was processed by host: 43ad3e3039d1</h1>
$ curl http://docker
<h1>This request was processed by host: 20a66e06db69</h1>
```

On peut voir la configuration que fait `nginx-proxy` via :

```docker exec nginx cat /etc/nginx/conf.d/default.conf```

On peut aussi avoir des informations sur le rechargement de la configuration avec les logs :

```docker logs nginx```

### Orchestration using Docker Compose

<https://katacoda.com/courses/docker/11>

Docker Compose est basé sur un fichier `docker-compose.yml`. Ce fichier défini tous les containers et toutes les configurations nécessaire pour le lancement d'un ensemble de clusters. Il est au format `YAML` (Yet Another Markup Language).

On peut voir le `docker-compose.yml` comme un fichier bash qui contiendrait une suite de commande pour démarrer les containers (avec configuration, etc).

```yaml
container_name:
  property: value
    - or options
```

Dans ce scénario, on a une application Node.js qui a besoin d'une connexion à `Redis`.

Pour commencer, on a besoin de définir le fichier `docker-compose.yml` qui lancera l'application Node.js.

Dans le dossier courant nous avons :

```text
./
  Dockerfile
  Makefile
  docker-compose.yml
  package.json
  server.js
```

On ajoute un container avec le nom `web` et on assigne le répertoire courant à la propriété `build` :

```yaml
web:
  build: .
```

Le `build: .` permet de générer le container via le Dockerfile sur le répertoire courant.

Il est aussi possible de build le container à part avec la commande `docker build -t mon-server:latest` et de renseigner directement le nom de l'image :

```yaml
web:
  image: mon-server:latest
```

Docker Compose supporte toutes les propriétés pouvant être définies via `docker run`.

On veut exposer le port `3001`, dans le dockerfile :

```dockerfile
FROM ocelotuproar/alpine-node:5.7.1-onbuild
COPY server.js .
EXPOSE 3001
CMD ["node", "server.js"]
```

Dans le fichier docker-compose, pour qu'un service puisse pouvoir ping un autre service il fallait ajouter le propriété `links`, notre service `web` doit pouvoir dialoguer avec le service `redis` que l'on va ajouter un peu plus loin :

```yaml
web:
  build: .

  links:
    - redis
```

Ensuite, comme si on faisait `-p 3001:3001`, on map le port host sur le port container :

```yaml
web:
  build: .

  links:
    - redis

  ports:
    - "3001:3001"
```

On peut maintenant définir notre service `redis` :

```yaml
web:
  build: .

  links:
    - redis

  ports:
    - "3001:3001"
    
redis:
  image: redis:alpine
  volumes:
    - /var/redis/data:/data
```

On peut maintenant lancer tous les services (le `-d` indique que les containers sont en tâche de fond) :

```docker-compose up -d```

Docker Compose gère le démarrage de tous les containers mais aussi propose un certain nombre de commande pour gérer ces containers.

```docker-compose ps```

```docker-compose logs```

Pour voir les commandes :

```docker-compose```

Il est possible de définir le nombre d'instance d'un container avec l'option `scale server-name=number`. Si le nombre est supérieur au nombre de container déjà lancé alors il lancera les containers en plus.

par exemple pour définir 3 containers (on en a déjà un de base donc pour en ajouter deux) :

```docker-compose scale web=3```

Là on a un problème car le port 3001 existe déjà vu qu'on bind `3001:3001`.

Pour retourner à un seul container :

```docker-compose scale web=1```

On peut arrêter tous les containers avec :

```docker-compose stop```

Et pour supprimer les containers :

```docker-compose rm```

On peut indiquer quel fichier `build`/`up`/`stop` avec l'argument `-f`.