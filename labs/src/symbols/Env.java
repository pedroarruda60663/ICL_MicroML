package symbols;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Env<T> {
	private Map<String, T> table;
	private Env<T> prev;

	public Env() {
		this.table = new HashMap<>();
		this.prev = null;
	}

	public void bind(String id, T val) {
		table.put(id, val);
	}

	public T find(String id) {
		for (Env<T> e = this; e != null; e = e.prev) {
			T found = e.table.get(id);
			if (found != null) {
				return found;
			}
		}
		throw new RuntimeException("Identifier not found: " + id);
	}

	public Env<T> beginScope() {
		Env<T> newEnv = new Env<>();
		newEnv.prev = this;
		return newEnv;
	}

	public Env<T> endScope() {
		return this.prev;
	}
}

