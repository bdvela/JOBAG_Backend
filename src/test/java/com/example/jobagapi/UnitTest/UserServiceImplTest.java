package com.example.jobagapi.UnitTest;
import com.example.jobagapi.domain.model.User;
import com.example.jobagapi.domain.repository.UserRepository;
import com.example.jobagapi.domain.service.UserService;
import com.example.jobagapi.exception.ResourceNotFoundException;
import com.example.jobagapi.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
    public class UserServiceImplTest {
        @MockBean
        private UserRepository userRepository;
        @Autowired
        private UserService userService;

        @TestConfiguration

        static class UserServiceImplTestConfiguration {
            @Bean
            public UserService userService() {
                return new UserServiceImpl();
            }
        }



        @Test
        @DisplayName("when GetUserById With Valid Id Then Returns User") //happy path
        public void whenGetUserByIdWithValidIdThenReturnsUser() {
            //Arrange
            Long id = 1L;
            User user = new User().setId(id);
            when(userRepository.findById(id)).thenReturn(Optional.of(user));
            //Act
            User foundUser = userService.getUserById(id);
            //Assert
            assertThat(foundUser.getId()).isEqualTo(id);
        }

        @Test
        @DisplayName("when GetUserById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
        public void whenGetUserByIdWithInvalidIdThenReturnsResourceNotFoundException() {
            //Arrange
            Long id = 1L;
            String template = "Resource %s not found for %s with value %s";
            when(userRepository.findById(id)).thenReturn(Optional.empty());
            String exceptedMessage = String.format(template, "User", "Id", id);
            //Act
            Throwable exception = catchThrowable(() ->{
                User foundUser = userService.getUserById(id);
            });
            //Assert
            assertThat(exception)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage(exceptedMessage);
        }


    }


