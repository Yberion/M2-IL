# TP1 : Docker

## Etape 2: LoadBalancer with Docker + une image docker pour vos applications

<https://github.com/barais/TPDockerSampleApp/>

(Fait sous mint20)

### Etape 1 (si vous utilisez docker sur votre machine ou à l'ISTIC): Jouons avec docker: mise en place d'un load balancer et d'un reverse proxy avec docker et nginx

Lancement de nginx en resolvproxy :

```docker run -d -p 8080:80 -v /var/run/docker.sock:/tmp/docker.sock -t jwilder/nginx-proxy```

Installation de terminator pour visualiser les effets du load-balancing :

```apt-get install terminator```

Lancement de terminator en root :

```sudo terminator```

Modifiez votre fichier `/etc/hosts` pour faire correspondre `mondomain` vers localhost.

```127.0.0.1	localhost mondomain```

Split terminator en 3 terminal et lancer la commande suivante sur chaque terminal :

```docker run -e VIRTUAL_HOST=mondomain -t -i nginx```

Dans un nouveau terminal lancer plusieurs fois la commande :

```curl mondomain:8080```

On peut regarder le fichier de configuration (récupérer l'id du nginx-proxy avec `docker ps`) :

```docker exec -it ID-nginx-proxy bash```

Et tapez la commande suivante dans le terminal de nginx-proxy :

```cat /etc/nginx/conf.d/default.conf```

Tuez tous les containers nginx avec `docker kill ID-container`

### Etape 2: Utilisation de docker compose

Installer `docker-compose` si besoin <https://docs.docker.com/compose/install/>.

Créez un fichier `docker-compose.yml` pour démarrer les 4 services (`nginx-proxy` et 3 `nginx`).

```yaml
nginx-proxy:
  image: jwilder/nginx-proxy

  ports:
    - "8080:80"

  volumes:
    - /var/run/docker.sock:/tmp/docker.sock

  environment:
    - DEFAULT_HOST=mondomain

nginx:
  image: nginx

  environment:
    - VIRTUAL_HOST=mondomain
```

Pour avoir 3 container nginx :

```docker-compose scale nginx=3```

Tapez 4 fois la commande dans un nouveau terminal :

```curl mondomain:8080```

Et vérifiez avec les logs (nom du container ou ID) :

```docker logs etape2_nginx_1```

```text
...
/docker-entrypoint.sh: Configuration complete; ready for start up
172.17.0.3 - - [06/Jan/2021:10:06:14 +0000] "GET / HTTP/1.1" 200 612 "-" "curl/7.68.0" "172.17.0.1"
```

```docker logs etape2_nginx_2```

```text
...
/docker-entrypoint.sh: Configuration complete; ready for start up
172.17.0.3 - - [06/Jan/2021:10:06:13 +0000] "GET / HTTP/1.1" 200 612 "-" "curl/7.68.0" "172.17.0.1"
```

```docker logs etape2_nginx_3```

```text
...
/docker-entrypoint.sh: Configuration complete; ready for start up
172.17.0.3 - - [06/Jan/2021:10:06:10 +0000] "GET / HTTP/1.1" 200 612 "-" "curl/7.68.0" "172.17.0.1"
172.17.0.3 - - [06/Jan/2021:10:06:17 +0000] "GET / HTTP/1.1" 200 612 "-" "curl/7.68.0" "172.17.0.1"
```

### Etape 3: Dockeriser une application existante

Dockerfile
ubuntu18
install lib native via apt
copy lib

Docker compose
lancer avec la cmd java -D... 3 instance du projet + un nginx-proxy