package io.earths.repo;

import io.earths.model.*;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class StateRepo {

    Dao dao;

    public StateRepo(Dao dao){
        this.dao = dao;
    }

    public State getSaved() {
        String idSql = "select max(id) from states";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public Long getId() {
        String sql = "select max(id) from states";
        Long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public Long getCount() {
        String sql = "select count(*) from states";
        Long count = dao.getLong(sql, new Object[] { });
        return count;
    }

    public State get(Long id) {
        String sql = "select * from states where id = [+]";
        State state = dao.get(sql, new Object[] { id }, State.class);
        return state;
    }

    public State get(String code) {
        String sql = "select * from states where code = '[+]'";
        State state = dao.get(sql, new Object[] { code }, State.class);
        return state;
    }

    public State getTown(Long townId) {
        String sql = "select * from states where town_id = '[+]'";
        State state = dao.get(sql, new Object[] { townId }, State.class);
        return state;
    }

    public List<State> all() {
        String sql = "select * from states";
        List<State> states = dao.getList(sql, new Object[]{}, State.class);
        return states;
    }


    public List<State> find(Long nationId) {
        String sql = "select * from states where nation_id = [+]";
        List<State> states = dao.getList(sql, new Object[]{ nationId }, State.class);
        return states;
    }


    public void save(State state) {
        String sql = "insert into states (nation_id, code, name, lat, lng) values ([+],'[+]','[+]','[+]','[+]')";
        dao.save(sql, new Object[]{
                state.getNationId(),
                state.getCode(),
                state.getName(),
                state.getLat(),
                state.getLng()
        });
    }
}
