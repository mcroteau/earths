package io.earths.repo;

import io.earths.model.*;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class PlaceRepo {

    Dao dao;

    public PlaceRepo(Dao dao){
        this.dao = dao;
    }

    public Place getSaved() {
        String idSql = "select max(id) from places";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public Long getId() {
        String sql = "select max(id) from places";
        Long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public Long getCount() {
        String sql = "select count(*) from places";
        Long count = dao.getLong(sql, new Object[] { });
        return count;
    }

    public Place find(String lat, String lng) {
        String sql = "select * from places where lat = '[+]' and lng = '[+]'";
        Place place = dao.get(sql, new Object[] { lat, lng }, Place.class);
        return place;
    }

    public Place get(Long id) {
        String sql = "select * from places where id = [+]";
        Place place = dao.get(sql, new Object[] { id }, Place.class);
        return place;
    }

    public Place get(String uri) {
        String sql = "select * from places where uri = '[+]'";
        Place place = dao.get(sql, new Object[] { uri }, Place.class);
        return place;
    }

    public List<Place> getList() {
        String sql = "select * from places";
        List<Place> places = dao.getList(sql, new Object[]{}, Place.class);
        return places;
    }

    public void save(Place place) {
        String sql = "insert into places (town_id, uri, name) values ([+],'[+]','[+]')";
        dao.save(sql, new Object[]{
                place.getTownId(),
                place.getUri(),
                place.getName(),
        });
    }

    public boolean update(Place place) {
        String sql = "update places set uri = '[+]', name = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                place.getUri(),
                place.getName(),
                place.getId()
        });
        return true;
    }

    public boolean delete(long id) {
        String sql = "delete from places where id = [+]";
        dao.update(sql, new Object[] { id });
        return true;
    }

    public List<Place> findTown(Long townId) {
        String sql = "select * from places where town_id = [+]";
        List<Place> places = dao.getList(sql, new Object[]{ townId }, Place.class);
        return places;
    }

    public List<Place> findState(Long stateId) {
        String sql = "select * from places where state_id = [+]";
        List<Place> places = dao.getList(sql, new Object[]{ stateId }, Place.class);
        return places;
    }

    public List<Place> findNation(Long nationId) {
        String sql = "select * from places where nation_id = [+]";
        List<Place> places = dao.getList(sql, new Object[]{ nationId }, Place.class);
        return places;
    }
}
