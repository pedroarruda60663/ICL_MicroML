package symbols;

import java.util.HashMap;
import java.util.Map;

public class CompEnv {
    public Map<String, Integer> table;
    public CompEnv prev;

    public CompEnv(CompEnv prev) {
        this.prev = prev;
        table = new HashMap<>();
    }

    private Pair<Integer,Integer> findIt(String id) {
        Integer width;
        CompEnv env = this;
        int depth = -1;
        do {
            width = env.table.get(id);
            env = env.prev;
            depth++;
        } while (width == null && env != null);
        return new Pair<>(depth,width);
    }
    public Pair<Integer,Integer> find(String id) {
        return findIt(id);
    }

    public void put(String id, Integer width) {
        table.put(id, width);
    }
}
