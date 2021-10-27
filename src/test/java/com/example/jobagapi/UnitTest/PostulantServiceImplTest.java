package com.example.jobagapi.UnitTest;

import com.example.jobagapi.domain.model.Postulant;
import com.example.jobagapi.domain.repository.PostulantRepository;
import com.example.jobagapi.domain.repository.UserRepository;
import com.example.jobagapi.domain.service.PostulantService;
import com.example.jobagapi.domain.service.UserService;
import com.example.jobagapi.exception.ResourceNotFoundException;
import com.example.jobagapi.service.PostulantServiceImpl;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PostulantServiceImplTest {
    @MockBean
    private PostulantRepository postulantRepository;
    @Autowired
    private PostulantService postulantService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @TestConfiguration
    static class PostulantServiceImplTestConfiguration {
        @Bean
        public PostulantService postulantService() {
            return new PostulantServiceImpl();
        }
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("when SavePostulant With Valid Postulant Then Returns Success") //happy path
    public void whenSavePostulantWithValidPostulantThenReturnsSuccess() {
        Long id = 1L;
        String name = "Carolina";
        String password = "Nota#20";
        Postulant postulant = new Postulant(id, name, "Villegas", "email", 2L, password, "document","civil");
        when(postulantRepository.save(postulant)).thenReturn(postulant);
        Postulant savedPostulant = postulantService.createPostulant(postulant);
        assertThat(savedPostulant).isEqualTo(postulant);

    }


    @Test
    @DisplayName("when GetPostulantById With Valid Id Then Returns Postulant") //happy path
    public void whenGetPostulantByIdWithValidIdThenReturnsPostulant() {
        //Arrange
        Long id = 1L;
        Postulant postulant = new Postulant(id, "caro", "Villegas", "email", 2L, "password", "document","civil");
        when(postulantRepository.findById(id)).thenReturn(Optional.of(postulant));
        //Act
        Postulant foundPostulant = postulantService.getPostulantById(id);
        //Assert
        assertThat(foundPostulant.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetPostulantById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetPostulantByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(postulantRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Postulant", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Postulant foundPostulant = postulantService.getPostulantById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("when UpdatePostulant With Valid Postulant Then Returns Success") //happy path
    public void whenUpdatePostulantWithValidPostulantThenReturnsSuccess() {
        //Arrange
        Long id = 1L;
        String name = "example@upc.edu.pe";
        String password = "Nota#20";
        Postulant postulant = new Postulant(id, "caro", "Villegas", "email", 2L, "password", "document","civil");

        String newPassword = "Nota@20";
        postulant.setPassword(newPassword);
        when(postulantRepository.save(postulant)).thenReturn(postulant);
        when(postulantRepository.findById(id)).thenReturn(Optional.of(postulant));
        Postulant saved = postulantService.updatePostulant(id, postulant);

        assertThat(saved).isEqualTo(postulant);
    }
    }
