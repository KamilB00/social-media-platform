package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDetailsDTO;

import java.util.Date;
import java.util.List;

public interface UserService {
    User createUser(Command.CreateUser createUserCommand);
    User whoAmI();
    String login(Query.Login login);
    UserDetailsDTO getUserDetails();

        interface Command {
            interface CreateUser extends Command {
                String getUsername();
                String getPassword();
                String getName();
                Date getDateOfBirth();
                String getSurname();
                String getEmail();
            }
        }

        interface Query {
            interface Login extends Query {
                String getUsername();
                String getPassword();
            }
            interface Search extends Query{
                String getUsername();
            }
        }
}
