package io.earths.repo;

import io.earths.model.*;
import net.plsar.Dao;
import net.plsar.annotations.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserRepo {

    Dao dao;

    public UserRepo(Dao dao){
        this.dao = dao;
    }

    public UserPlace getSavedPlace() {
        String idSql = "select max(id) from user_places";
        long id = dao.getLong(idSql, new Object[]{});
        return getPlace(id);
    }

    public User getSaved() {
        String idSql = "select max(id) from users";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public long getId() {
        String sql = "select max(id) from users";
        long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public long getCount() {
        String sql = "select count(*) from users";
        Long count = dao.getLong(sql, new Object[] { });
        return count;
    }

    public User get(long id) {
        String sql = "select * from users where id = [+]";
        User user = dao.get(sql, new Object[] { id }, User.class);
        return user;
    }

    public User get(String email) {
        String sql = "select * from users where email = '[+]'";
        User user = dao.get(sql, new Object[] { email }, User.class);
        return user;
    }

    public User getUid(String uid) {
        String sql = "select * from users where uid = '[+]'";
        User user = dao.get(sql, new Object[] { uid }, User.class);
        return user;
    }

    public User getPhone(String phone) {
        String sql = "select * from users where phone = '[+]'";
        User user = dao.get(sql, new Object[] { phone }, User.class);
        return user;
    }

    public User getEmail(String email) {
        String sql = "select * from users where email = '[+]'";
        User user = dao.get(sql, new Object[] { email }, User.class);
        return user;
    }

    public List<User> all() {
        String sql = "select * from users";
        List<User> users = dao.getList(sql, new Object[]{}, User.class);
        return users;
    }

    public void save(User user) {
        String sql = "insert into users (uid, guid, ref, refs, sponsor, name, phone, email, passwd, image, quote, needs, user_type, nation_id, state_id, town_id, time_created) " +
                "values ('[+]','[+]','[+]','[+]',[+],'[+]','[+]','[+]','[+]','[+]','[+]','[+]','[+]',[+],[+],[+],[+])";
        dao.save(sql, new Object[]{
                user.getUid(),
                user.getGuid(),
                user.getRef(),
                user.getRefs(),
                user.isFantastic(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPasswd(),
                user.getImage(),
                user.getQuote(),
                user.getNeeds(),
                user.getUserType(),
                user.getNationId(),
                user.getStateId(),
                user.getTownId(),
                user.getTimeCreated()
        });
    }

    public void update(User user) {
        String sql = "update users set name = '[+]', phone = '[+]', email = '[+]', " +
                "bio = '[+]', needs = '[+]', refs = '[+]', lat ='[+]', lng = '[+]', " +
                "nation_id = [+], state_id = [+], town_id = [+], onboarded = [+]," +
                "address = '[+]', address2 = '[+]', zip_code = '[+]', " +
                "has_bank = [+], has_cell = [+], stripe_account_id = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getBio(),
                user.getNeeds(),
                user.getRefs(),
                user.getLat(),
                user.getLng(),
                user.getNationId(),
                user.getStateId(),
                user.getTownId(),
                user.isOnboarded(),
                user.getAddress(),
                user.getAddress2(),
                user.getZipCode(),
                user.isHasBank(),
                user.isHasCell(),
                user.getStripeAccountId(),
                user.getId()
        });
    }

    public void updateImage(User user) {
        String sql = "update users set image = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                user.getImage(),
                user.getId()
        });
    }

    public void updateStripeAccountId(User user) {
        String sql = "update users set stripe_account_id = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                user.getStripeAccountId(),
                user.getId()
        });
    }

    public void updateStripeCustomerId(User user) {
        String sql = "update users set stripe_customer_id = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                user.getStripeAccountId(),
                user.getId()
        });
    }

    public void updatePassword(User user) {
        String sql = "update users set password = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
            user.getPasswd(),
            user.getId()
        });
    }

    public User getReset(String phone, String uid){
        String sql = "select * from users where phone = '[+]' and uid = '[+]'";
        User user = dao.get(sql, new Object[] { phone, uid }, User.class);
        return user;
    }

    public void delete(long id) {
        String sql = "delete from users where id = [+]";
        dao.update(sql, new Object[] { id });
    }

    public String getPersonPassword(String phone) {
        User user = getPhone(phone);
        return user.getPasswd();
    }

    public void savePersonRole(long userId, long roleId){
        String sql = "insert into user_roles (role_id, user_id) values ([+], [+])";
        dao.save(sql, new Object[]{roleId, userId});
    }

    public void savePersonRole(long userId, String roleName){
        Role role = dao.get("select * from roles where name = '[+]'", new Object[]{roleName}, Role.class);
        String sql = "insert into user_roles (role_id, user_id) values ([+], [+])";
        dao.save(sql, new Object[]{role.getId(), userId});
    }

    public void savePermission(long userId, String permission){
        String sql = "insert into user_permissions (user_id, permission) values ([+], '[+]')";
        dao.save(sql, new Object[]{ userId,  permission });
    }

    public Set<String> getPersonRoles(long id) {
        String sql = "select r.name as name from user_roles ur inner join roles r on r.id = ur.role_id where ur.user_id = [+]";
        List<UserRole> rolesList = dao.getList(sql, new Object[]{ id }, UserRole.class);
        Set<String> roles = new HashSet<>();
        for(UserRole role: rolesList){
            roles.add(role.getName());
        }
        return roles;
    }

    public boolean hasPermission(Long id, String permission) {
        System.out.println("haspermission=>" + id + ":" + permission);
        String sql = "select * from user_permissions where user_id = [+] and permission = '[+]'";
        UserPermission userPermission = dao.get(sql, new Object[]{ id, permission }, UserPermission.class);
        return userPermission != null;
    }

    public Set<String> getPersonPermissions(long id) {
        String sql = "select permission from user_permissions where user_id = [+]";
        List<UserPermission> permissionsList = dao.getList(sql, new Object[]{ id }, UserPermission.class);
        Set<String> permissions = new HashSet<>();
        for(UserPermission permission: permissionsList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public List<UserPlace> getPlacePeople(Long id) {
        String sql = "select * from user_places where location_id = [+]";
        List<UserPlace> userBusinesses = dao.getList(sql, new Object[]{ id }, UserPlace.class);
        return userBusinesses;
    }

    public void savePlace(UserPlace userPlace) {
        String sql = "insert into user_places (user_id, place_id) values ('[+],[+])";
        dao.save(sql, new Object[]{
                userPlace.getUserId(),
                userPlace.getPlaceId()
        });
    }

    public UserPlace getPlace(Long placeId) {
        String sql = "select * from user_places where id = [+]";
        UserPlace userPlace = dao.get(sql, new Object[] { placeId }, UserPlace.class);
        return userPlace;
    }

    public User getGuid(String guid) {
        String sql = "select * from user where guid = '[+]'";
        User user = dao.get(sql, new Object[] { guid }, User.class);
        return user;
    }
}
