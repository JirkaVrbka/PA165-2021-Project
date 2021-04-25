package cz.idomatojde.facade;

import cz.idomatojde.dto.user.RegisterUserDTO;
import cz.idomatojde.dto.user.UserContactInfoDTO;

/**
 * Facade responsible for all things concerning users
 *
 * @author Michal Hazdra
 */
public interface UserFacade {

    /**
     * Register a new user
     *
     * @param registrationInfo necessary user creation information
     */
    void registerUser(RegisterUserDTO registrationInfo);

    /**
     * Retrieve user's full name and contact information
     *
     * @param userId the unique user identifier
     * @return user's full name, phone number and e-mail account
     */
    UserContactInfoDTO getUserContactInfo(long userId);
}
