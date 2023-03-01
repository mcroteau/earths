package io.earths.repo;

import io.earths.model.User;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class SaintRepo {

    Dao dao;

    public SaintRepo(Dao dao){
        this.dao = dao;
    }

    public User get(Long id) {
        String sql = "select * from users where id = [+] and user_type = '[+]' or user_type = '[+]'";
        User saint = dao.get(sql, new Object[] { id, "saint", "mix" }, User.class);
        return saint;
    }

    public User guid(String guid) {
        String sql = "select * from users where guid = '[+]' and user_type = '[+]' or user_type = '[+]'";
        User saint = dao.get(sql, new Object[] { guid, "saint", "mix" }, User.class);
        return saint;
    }

    public User uid(String uid) {
        String sql = "select * from users where uid = '[+]' where user_type = '[+]' or user_type = '[+]'";
        User user = dao.get(sql, new Object[] { uid, "saint", "mix" }, User.class);
        return user;
    }

    public User email(String email) {
        String sql = "select * from users where email = '[+]' and user_type = '[+]' or user_type = '[+]'";
        User saint = dao.get(sql, new Object[] { email, "saint", "mix" }, User.class);
        return saint;
    }

    public List<User> find(String query){
        String sql = "select * from users where lower(name) like lower('%[+]%') and user_type = '[+]' or user_type = '[+]'";
        List<User> saints = dao.getList(sql, new Object[]{ query, "saint", "mix"}, User.class);
        return saints;
    }

    public List<User> all(){
        String sql = "select * from users where user_type = '[+]' or user_type = '[+]'";
        List<User> saints = dao.getList(sql, new Object[]{ "saint", "mix" }, User.class);
        return saints;
    }

}
