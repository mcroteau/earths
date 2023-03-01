package io.earths.repo;

import io.earths.model.*;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class NationRepo {

    Dao dao;

    public NationRepo(Dao dao){
        this.dao = dao;
    }

    public Nation getSaved() {
        String idSql = "select max(id) from nations";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public Long getId() {
        String sql = "select max(id) from nations";
        Long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public Long getCount() {
        String sql = "select count(*) from nations";
        Long count = dao.getLong(sql, new Object[] { });
        return count;
    }

    public Nation get(Long id) {
        String sql = "select * from nations where id = [+]";
        Nation nation = dao.get(sql, new Object[] { id }, Nation.class);
        return nation;
    }


    public Nation iso(String iso) {
        String sql = "select * from nations where iso = '[+]'";
        Nation nation = dao.get(sql, new Object[] { iso }, Nation.class);
        return nation;
    }

    public Nation iso3(String iso3) {
        String sql = "select * from nations where lower(iso3) = lower('[+]')";
        Nation nation = dao.get(sql, new Object[] { iso3 }, Nation.class);
        return nation;
    }

    public Nation getTown(Long townId) {
        String sql = "select * from nations where town_id = '[+]'";
        Nation nation = dao.get(sql, new Object[] { townId }, Nation.class);
        return nation;
    }

    public List<Nation> all() {
        String sql = "select * from nations";
        List<Nation> nations = dao.getList(sql, new Object[]{}, Nation.class);
        return nations;
    }

    public void save(Nation nation) {
        String sql = "insert into nations " +
                "(name, iso, iso3, emoji, numeric_code, phone_code, capital, currency, currency_name, currency_symbol, native_name, lat, lng, region, subregion) " +
                "values ('[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]')";
        dao.save(sql, new Object[]{
                nation.getName(),
                nation.getIso(),
                nation.getIso3(),
                nation.getEmoji(),
                nation.getNumericCode(),
                nation.getPhoneCode(),
                nation.getCapital(),
                nation.getCurrency(),
                nation.getCurrencyName(),
                nation.getCurrencySymbol(),
                nation.getNativeName(),
                nation.getLat(),
                nation.getLng(),
                nation.getRegion(),
                nation.getSubregion()
        });
    }

}
