package exp.tutorial.annotations;

@Unfinished(value = "Class scope", priority = Unfinished.Priority.LOW)
public class UnfinishedDemo {
	
	@Unfinished("Constructor scope")
	public UnfinishedDemo() {
	}

	@Unfinished(value = "Method scope", owners = "Jason")
	public void foo() {
	}
}
