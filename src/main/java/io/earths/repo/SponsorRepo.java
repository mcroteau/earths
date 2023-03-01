package io.earths.repo;

import io.earths.model.User;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.List;

@Repository
public class SponsorRepo {

    Dao dao;

    public SponsorRepo(Dao dao){
        this.dao = dao;
    }


    public User get(Long id) {
        String sql = "select * from users where id = [+] and user_type = '[+]' or user_type = '[+]'";
        User saint = dao.get(sql, new Object[] { id, "sponsor", "mix" }, User.class);
        return saint;
    }

    public User ref(String ref) {
        String sql = "select * from users where lower(ref) = lower('[+]') and user_type = '[+]' or user_type = '[+]'";
        User user = dao.get(sql, new Object[] { ref, "sponsor", "mix" }, User.class);
        return user;
    }

    public User uid(String uid) {
        String sql = "select * from users where uid = '[+]' and user_type = '[+]' or user_type = '[+]'";
        User user = dao.get(sql, new Object[] { uid, "sponsor", "mix" }, User.class);
        return user;
    }

    public User guid(String guid) {
        String sql = "select * from users where guid = '[+]' and user_type = '[+]' or user_type = '[+]'";
        User saint = dao.get(sql, new Object[] { guid, "sponsor", "mix" }, User.class);
        return saint;
    }

    public void bank(User user) {
        String sql = "update users set has_bank = [+] where id = [+]";
        dao.update(sql, new Object[]{
                user.isHasBank(),
                user.getId()
        });
    }

    public List<User> all(){
        String sql = "select * from users where user_type = '[+]' or user_type = '[+]'";
        List<User> sponsors = dao.getList(sql, new Object[]{ "sponsor", "mix" }, User.class);
        return sponsors;
    }
}
