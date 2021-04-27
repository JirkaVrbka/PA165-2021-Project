package cz.idomatojde.dao;

import cz.idomatojde.dao.common.BaseDAOImpl;
import cz.idomatojde.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * DAO implementation for {@link OfferDao} API
 *
 * @author Jiri Vrbka
 */
@Repository
public class UserDaoImpl extends BaseDAOImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPhone(String phoneNumber, Long userId) {
        var user = this.getById(userId);
        user.setPhoneNumber(phoneNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCredits(Integer credits, Long userId) {
        var user = this.getById(userId);
        user.setCredits(user.getCredits() + credits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByEmail(String email){
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");

        try {
            return em
                    .createQuery(
                            "Select u From User u Where u.email = :email",
                            User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
