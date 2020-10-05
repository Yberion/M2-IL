# TAA Examen 2019 - 2020

## Q1

Si l'objet est lié à d'autres objets la persistence par défaut ne s'applique pas aux objets liés.

Si on a des employés liés a un département :


``em.persist(e1)`` ``em.persist(d1)``

OU

Sur la relation ``OneToMany(cascade = CascadeType.persist, Fetch = FetchType.E???)`` vérifier sur le TP.

Voir pdf Q1.

## Q2

Utilisation d'un pattern proxy, utilisation des instances du proxy.

Revoir cours et compléter

## Q3

Digramme de package.

La DTO est bien un package à part même si souvent défini directement dans le package service.

## Q4

Diagrame de classe.

## Q5

@Entity
class Task
{
	@id
	@GeneratedValue
	long id;
	String libelle;
	@Temporal
	Date dateButoir;
	@ManyToMany
	List<Tag> tags;
	@ManyToOne
	Note note;
	@ManyToMany
	User auteur;
	@ManyToMany
	List<User> affect
}

@Entity
class Tag
{
	@id
	@GeneratedValue
	long id;
	@ManyToMany(MappedBy ="tags")
	List<Task> tasks;
}

## Q6

Pour l'injection de dépendance annoter @Repository qui descend de @Component

public interface TaskDAO extends JpaRepository<Long, Task>
{ }

## Q7

"select t from Task as t where t.done and t.dateButoir between ?1 and ?2"

## Q8

@Path("todos")
public class TaskResources
{
	TaskDAO dao = new TaskDAOImpl();
	
	//avec @POST, on créé une classe DateBetween DTO et
	
	@Get
	@Consumes("application/json")
	@Path("/{debut}/{fin}") // Pas besoin en POST
	List<Task> getAllTask(@PathParam("debut") String debut, @PathParam("fin") String debut)
	{
		Date dateDebut = Date.parse(debut);
		Date dateFin = Date.parse(fin);
		
		return this.dao.getAllTask(dateDebut, dateFin);
	}
}

## Q9

@Aspect
public class Log
{
	@Before("execution(* fr.istic.service.*.*(..))")
	public void logBeforeCall(JoinPoint joinPoint)
    {
        System.out.println("LOGGING: ....");
    }
}
