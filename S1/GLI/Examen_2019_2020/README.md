# GLI Examen 2019 - 2020

## Q1

Utilisation de VirtualDom, appliquer toutes les modifications sur un DOM virtuel pour le calcule d'impacte et une diff est faite et les changements sont merge dans le vrai DOM. La fonction de paint coûte cher.

## Q2

Il y a de plus en plus de problématique d'orchestration du build, plusieurs étape à faire pour obtenir notre application web. Problématique de dépendance. On fait du re-use massif, on explicite maintenant ce processus de mise en place des dépendances. On a un système de gestion de dépendances et un système de génération de build.

## Q3

OpenAPI

## Q4

Principe de binding implicite. Pattern qui se base sur l'idée d'avoir un binding implicite entre le model et la vue. Capacité à avoir une structure de donnée plutôt proche. On ne gère avec une granularité basse pas les évènement entre la vue et le modèle.

## Q5

Pas à nous de gérer les dépendances.

L'intérêt est de réduit le couplage entre les module et facilite la testabilité (utilisation facile de mock).

Comment on fait ça dans Angular :

```Typescript
constructor(private http: HttpServer)
{ }
```

## Q6

### Avantage

#### Javascript

Langage pas très compliqué, documentation plutôt simple à trouver, pas mal de librairie disponible.

Simple pour des développements simple.

#### Jquery

Javascript avec quelques raccourcis, simplification du code, accès au DOM plus simple.

Système de composant qui permet le re-use de librairie.

Multi-navigateur, uniformisation des API browser.

Bonne liste de bibliothèque disponible.

#### GWT, Dart

Relativement simple.

Permet de faire de la bonne conception objet.

Uniformisation du langage de programmation (compilateur Java -> JS par exemple).

Bonne liste de bibliothèque disponible.

#### VueJS

Notion de composant web permettant la structuration et réutilisation.

Plus facile pour la maintenabilité.

Système de DOM Virtuel.

Performance.

Documentation/communauté.

Bonne liste de bibliothèque disponible.

### Inconvénient

#### Javascript

Problème de gestion multi-navigateur, incohérence sémantique.

Difficulté lié au refactoring, pas de structure imposée, maintenance difficile (non typé).

Moins de bon développeur Javascript.

#### Jquery

Identique à la partie Javascript pour les points :

- Difficulté lié au refactoring, pas de structure imposée, maintenance difficile (non typé).
- Moins de bon développeur Javascript.

Problème de performance sur de très grosses applications. Déclenchement en chaîne d'évènement modifiant le DOM (pas de VirtualDOM).

#### GWT, Dart

Compilateur lent (1 min à 5 min pour un simple changement).

Intégration Javascript distante.

#### VueJS

Complexité de mise en oeuvre et de prise en main.

## Q7

### Angular

(Le professeur va mettre le code en ligne, j'éditerai)

Notion de port (entrée/sortie).

@Component()
public class Parent
{
	 : int // ??
	
	foo()
	{
	}
}

@Component 
public class Enfant
{
	@Inputport()
	timer: int; // ??
	
	@Outputport()
	timeout: eventEmitter<> // ??
	
	timer // ??
}

<enfant [timer]="??" // ?? />

### React



## B/ Exercice

(Le professeur va mettre le code en ligne, j'éditerai)

```Typescript
@Component({...})
export class TodoListComponent
{
	todos: Array<Todo>
	newTask: String
	constructor(private todoService: TodoService)
	{ }
	
	NgOnInit()
	{
		this.todoService.getAll().subscribe(data -> this.todos = data ??)
	}
}
```
```Typescript
class Todo
{
	constructor(private id: int, private taskName: String, private done: Boolean)
	{ }
	
}
```

```Typescript
@Service
class TodoService
{
	constructor(private http: HtppClient)
	{ }
	
	getAll(): Observable<Array<Todo>>
	{
		return this.http.get<Array<Todo>>('/todos');
	}
	
	addTask() ...
}
```

```html
<h2>My TodoList</h2>
<input type="text" [(ng??)]="??">
<button (old)="addTask()">add</button>

<ul>
	<li *ngFor="let todo for tasks">{{ task.name }}</li>
</ul>
```




