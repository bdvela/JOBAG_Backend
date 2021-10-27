package com.example.jobagapi.UnitTest;

import com.example.jobagapi.domain.model.Postulant;
import com.example.jobagapi.domain.model.ProfessionalProfile;
import com.example.jobagapi.domain.model.Skill;
import com.example.jobagapi.domain.repository.ProfessionalProfileRepository;
import com.example.jobagapi.domain.repository.SkillRepository;
import com.example.jobagapi.domain.service.ProfessionalProfileService;
import com.example.jobagapi.domain.service.SkillService;
import com.example.jobagapi.exception.ResourceNotFoundException;
import com.example.jobagapi.service.ProfessionalProfileImpl;
import com.example.jobagapi.service.SkillServiceImpl;
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
public class SkillServiceImplTest {
    @MockBean
    private SkillRepository skillRepository;

    @Autowired
    private SkillService skillService;



    @TestConfiguration
    static class SkillServiceImplTestConfiguration {
        @Bean
        public SkillService skillService() { return new SkillServiceImpl(); }


    }

    @Test
    @DisplayName("when SaveSkill With Valid Skill Then Returns Success") //happy path
    public void whenSaveSkillWithValidSkillThenReturnsSuccess() {
        Long id = 1L;
        String name = "Empatico";
        Skill skill = new Skill(id,name,"muy amable con la gente");
        when(skillRepository.save(skill)).thenReturn(skill);
        Skill savedSkill = skillService.createSkill(skill);
        assertThat(savedSkill).isEqualTo(skill);

    }

    @Test
    @DisplayName("when Get Skill By Id With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetSkillByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(skillRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Skill", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Skill foundSkill = skillService.getSkillById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

}