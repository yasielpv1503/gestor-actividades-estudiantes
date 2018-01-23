package cu.uci.gestoractividadesestudiante.gestoractividadesestudiante.api_client;

import java.util.LinkedList;

/**
 * Created by tatos on 22/01/18.
 */

public class GrupoManager {
    public LinkedList<String> list;

    public GrupoManager() {
        this.list = new LinkedList<>();
        this.list.add("FI01");
        this.list.add("FI02");
        this.list.add("FI03");
        this.list.add("FI04");
        this.list.add("FI05");
    }

    public LinkedList<String> getList() {
        return list;
    }
}
