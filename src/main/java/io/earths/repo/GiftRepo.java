package io.earths.repo;

import io.earths.model.Gift;
import net.plsar.Dao;
import net.plsar.annotations.Repository;
import java.util.List;

@Repository
public class GiftRepo {

    Dao dao;

    public GiftRepo(Dao dao){
        this.dao = dao;
    }

    public long getId() {
        String sql = "select max(id) from gifts";
        long id = dao.getLong(sql, new Object[]{});
        return id;
    }

    public Gift getSaved() {
        String idSql = "select max(id) from gifts";
        long id = dao.getLong(idSql, new Object[]{});
        return get(id);
    }

    public Long getCount() {
        String sql = "select count(*) from gifts";
        Long count = dao.getLong(sql, new Object[]{});
        return count;
    }

    public Gift get(long id){
        String sql = "select * from gifts where id = [+]";
        Gift donation = dao.get(sql, new Object[] { id }, Gift.class);
        return donation;
    }

    public Gift get(String subscriptionId){
        String sql = "select * from gifts where subscription_id = '[+]'";
        Gift donation = dao.get(sql, new Object[] { subscriptionId }, Gift.class);
        return donation;
    }

    public Gift guid(String guid){
        String sql = "select * from gifts where guid = '[+]'";
        Gift donation = dao.get(sql, new Object[] { guid }, Gift.class);
        return donation;
    }

    public void save(Gift gift){
        String sql = "insert into gifts " +
                "(guid, amount, amount_cents, charge_id, subscription_id, kind_person_id, kind_person_name, recipient_id, gift_time, email, monthly) values " +
                "('[+]',[+],[+],'[+]','[+]',[+],'[+]',[+],[+],'[+]',[+])";
        dao.save(sql, new Object[] {
                gift.getGuid(),
                gift.getAmount(),
                gift.getAmountCents(),
                gift.getChargeId(),
                gift.getSubscriptionId(),
                gift.getKindPersonId(),
                gift.getKindPersonName(),
                gift.getRecipientId(),
                gift.getGiftTime(),
                gift.getEmail(),
                gift.isMonthly()
        });
    }

    public void update(Gift gift) {
        System.out.println(
                gift.getProcessed() + " :: charge : " +
                        gift.getChargeId() + " :: subscription : " +
                        gift.getSubscriptionId() + " :: " +
                        gift.getId());

        String sql = "update gifts set processed = [+], charge_id = '[+]', subscription_id = '[+]' where id = [+]";
        dao.update(sql, new Object[]{
                gift.getProcessed(),
                gift.getChargeId(),
                gift.getSubscriptionId(),
                gift.getId()
        });
    }

    public List<Gift> getList(){
        String sql = "select * from gifts";
        List<Gift> gifts = dao.getList(sql, new Object[]{}, Gift.class);
        return gifts;
    }

    public List<Gift> all() {
        String sql = "select * from gifts ordered by gift_time desc";
        List<Gift> gifts = dao.getList(sql, new Object[]{ }, Gift.class);
        return gifts;
    }

    public List<Gift> all(long userId) {
        String sql = "select * from gifts where recipient_id = [+] and processed = true order by gift_time desc";
        List<Gift> gifts = dao.getList(sql, new Object[]{ userId }, Gift.class);
        return gifts;
    }

}
