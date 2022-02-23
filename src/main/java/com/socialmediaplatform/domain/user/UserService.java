package com.socialmediaplatform.domain.user;

import com.socialmediaplatform.api.user.dto.UserDetailsDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserService {
    User createUser(Command.CreateUser createUserCommand);
    User whoAmI();
    String login(Query.Login login);
    void follow(Command.Follow followCommand);
    UserDetailsDTO getUserDetails();
    UserDetailsDTO getUserDetails(Query.Search querySearch);


        interface Command {
            interface CreateUser extends Command {
                String getUsername();
                String getPassword();
                String getName();
                LocalDate getDateOfBirth();
                String getSurname();
                String getEmail();
            }

            interface Follow extends Command {
                String getUsername();
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
