package io.earths.repo;

import io.earths.model.*;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class TownRepo {

    Dao dao;

    public TownRepo(Dao dao){
        this.dao = dao;
    }

    public Town getSaved() {
        String idSql = "select max(id) from towns";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public Long getId() {
        String sql = "select max(id) from towns";
        Long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public Long getCount() {
        String sql = "select count(*) from towns";
        Long count = dao.getLong(sql, new Object[] { });
        return count;
    }

    public Town get(Long id) {
        String sql = "select * from towns where id = [+]";
        Town town = dao.get(sql, new Object[] { id }, Town.class);
        return town;
    }

    public Town get(String uri) {
        String sql = "select * from towns where uri = '[+]'";
        Town town = dao.get(sql, new Object[] { uri }, Town.class);
        return town;
    }

    public List<Town> all() {
        String sql = "select * from towns";
        List<Town> towns = dao.getList(sql, new Object[]{}, Town.class);
        return towns;
    }

    public List<Town> find(Long stateId) {
        String sql = "select * from towns where state_id = [+]";
        List<Town> towns = dao.getList(sql, new Object[]{ stateId }, Town.class);
        return towns;
    }

    public void save(Town town) {
        String sql = "insert into towns (state_id, nation_id, name, code, lat, lng) values ([+],[+],'[+]','[+]','[+]','[+]')";
        dao.save(sql, new Object[]{
                town.getStateId(),
                town.getNationId(),
                town.getName(),
                town.getCode(),
                town.getLat(),
                town.getLng(),
        });
    }

}
