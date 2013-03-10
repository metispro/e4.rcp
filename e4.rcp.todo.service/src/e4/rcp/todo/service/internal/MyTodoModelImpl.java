package e4.rcp.todo.service.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import e4.rcp.todo.model.ITodoModel;
import e4.rcp.todo.model.Todo;

public class MyTodoModelImpl implements ITodoModel {

	static int current = 1;
	private List<Todo> model = null;

	public MyTodoModelImpl() {
		this.model = this.createInitialModel();
	}

	// Always return a new copy of the data
	@Override
	public List<Todo> getTodos() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		final ArrayList<Todo> list = new ArrayList<Todo>();
		for (final Todo todo : this.model) {
			list.add(todo.copy());
		}
		return list;
	}

	@Override
	public boolean saveTodo(final Todo newTodo) {
		Todo updateTodo = null;
		for (final Todo todo : this.model) {
			if (todo.getId() == newTodo.getId()) {
				updateTodo = todo;
			}
		}
		if (updateTodo != null) {
			updateTodo.setSummary(newTodo.getSummary());
			updateTodo.setDescription(newTodo.getDescription());
			updateTodo.setDone(newTodo.isDone());
			updateTodo.setDueDate(newTodo.getDueDate());
		} else {
			newTodo.setId(current++);
			this.model.add(newTodo);
		}

		return true;
	}

	@Override
	public Todo getTodo(final long id) {
		for (final Todo todo : this.model) {
			if (todo.getId() == id)
				return todo.copy();
		}
		return null;
	}

	@Override
	public boolean deleteTodo(final long id) {
		if (this.model == null)
			return false;

		final Todo removeTodo = this.getTodo(id);

		if (removeTodo == null)
			throw new RuntimeException("Todo not found: id = " + id);

		return this.model.remove(removeTodo);
	}

	// Example data, change if you like
	private List<Todo> createInitialModel() {
		final ArrayList<Todo> list = new ArrayList<Todo>();
		list.add(this.createTodo("SWT", "Learn Widgets"));
		list.add(this.createTodo("JFace", "Especially Viewers!"));
		list.add(this.createTodo("DI", "@Inject looks interesting"));
		list.add(this.createTodo("OSGi", "Services"));
		list.add(this.createTodo("Compatibility Layer", "Run Eclipse 3.x"));
		return list;
	}

	private Todo createTodo(final String summary, final String description) {
		return new Todo(current++, summary, description, false, new Date());
	}

}
