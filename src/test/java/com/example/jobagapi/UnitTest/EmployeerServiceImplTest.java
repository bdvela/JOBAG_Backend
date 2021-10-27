package com.example.jobagapi.UnitTest;
import com.example.jobagapi.domain.model.Employeer;
import com.example.jobagapi.domain.repository.EmployeerRepository;
import com.example.jobagapi.domain.repository.UserRepository;
import com.example.jobagapi.domain.service.EmployeerService;
import com.example.jobagapi.domain.service.UserService;
import com.example.jobagapi.exception.ResourceNotFoundException;
import com.example.jobagapi.service.EmployeerServiceImpl;
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
public class EmployeerServiceImplTest {

    @MockBean
    private EmployeerRepository employeerRepository;
    @Autowired
    private EmployeerService employeerService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @TestConfiguration
    static class EmployeerServiceImplTestConfiguration
    {
        @Bean
        public EmployeerService employeerService()
        {
            return new EmployeerServiceImpl();
        }
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
    @Test
    @DisplayName("when SaveEmployeer With Valid Employeer Then Returns Success") //happy path
    public void whenSaveEmployeerWithValidEmployeerThenReturnsSuccess() {
        Long id = 1L;
        String name = "Carolina";
        String password = "Nota#20";
        Employeer employeer = new Employeer(id, name, "Villegas", "email", 2L, password, "document","civil");
        when(employeerRepository.save(employeer)).thenReturn(employeer);
        Employeer savedEmployeer = employeerService.createEmployeer(employeer);
        assertThat(savedEmployeer).isEqualTo(employeer);

    }


    @Test
    @DisplayName("whenGetEmployeerByIdWithValidIdThenReturnsEmployeer") //happy path
    public void whenGetEmployeerByIdWithValidIdThenReturnsEmployeer() {
        //Arrange
        Long id = 1L;
        Employeer employeer = new Employeer(id, "caro", "Villegas", "email", 2L, "password", "document","civil");
        when(employeerRepository.findById(id)).thenReturn(Optional.of(employeer));
        //Act
        Employeer foundEmployeer = employeerService.getEmployeerById(id);
        //Assert
        assertThat(foundEmployeer.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetEmployeerById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetEmployeerByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(employeerRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Employeer", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Employeer foundEmployeer = employeerService.getEmployeerById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("when UpdateEmployeer With Valid Employeer Then Returns Success") //happy path
    public void whenUpdateEmployeerWithValidEmployeerThenReturnsSuccess() {
        //Arrange
        Long id = 1L;
        String name = "example@upc.edu.pe";
        String password = "Nota#20";
        Employeer employeer = new Employeer(id, "caro", "Villegas", "email", 2L, "password", "document","civil");

        String newPassword = "Nota@20";
        employeer.setPassword(newPassword);
        when(employeerRepository.save(employeer)).thenReturn(employeer);
        when(employeerRepository.findById(id)).thenReturn(Optional.of(employeer));
        Employeer saved = employeerService.updateEmployeer(id, employeer);

        assertThat(saved).isEqualTo(employeer);
    }


}