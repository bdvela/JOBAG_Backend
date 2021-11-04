package com.example.jobagapi.ToBeCucumber;

import com.example.jobagapi.domain.model.Employeer;
import com.example.jobagapi.domain.model.JobOffer;
import com.example.jobagapi.domain.model.Postulant;
import com.example.jobagapi.domain.model.PostulantJob;
import com.example.jobagapi.domain.repository.JobOfferRepository;
import com.example.jobagapi.domain.repository.PostulantJobRepository;
import com.example.jobagapi.domain.repository.PostulantRepository;
import com.example.jobagapi.domain.service.JobOfferService;
import com.example.jobagapi.service.JobOfferServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;


public class HistoryJobsStepsDefs {


    @Mock
    private PostulantJobRepository postulantJobRepository;
    @Mock
    private JobOfferRepository jobOfferRepository;
    @Mock
    private PostulantRepository postulantRepository;
    @InjectMocks
    private JobOfferServiceImpl jobOfferService;

    private static final Postulant postulant = new Postulant();
    private static final PostulantJob postulantJob = new PostulantJob();
    private static final JobOffer jobOffer = new JobOffer();
    private static final Employeer employeer = new Employeer();
    private static final Optional<Postulant> OPTIONAL_POSTULANT = Optional.of(postulant);
    private static final Optional<JobOffer> OPTIONAL_JOB_OFFER = Optional.of(jobOffer);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        //postulant
        postulant.setId(1L);
        postulant.setFirstname("Alonso");
        postulant.setLastname("Yuen");
        postulant.setPassword("Oveja");
        postulant.setEmail("Alonso@yuen.com");
        postulant.setNumber(955987645L);
        postulant.setDocument("123456789");
        postulant.setCivil_status("soltero");
        //postulantjob
        postulantJob.setPostulant(postulant);
        //employer
        employeer.setId(2L);
        employeer.setFirstname("Hugo");
        employeer.setLastname("Rodriguez");
        employeer.setPassword("Doom");
        employeer.setEmail("Hugo@rodriguez.com");
        employeer.setNumber(987654321L);
        employeer.setPosicion("Practicante de recursos humanos");
        employeer.setDocument("987654321");
        //joboffer
        jobOffer.setId(1L);
        jobOffer.setEmployeer(employeer);
        jobOffer.setTitle("Ingeniero de Software");
        jobOffer.setDescription("Programador");
        jobOffer.setDirection("av.nowhere 132");
        jobOffer.setSalary(1000L);
        jobOffer.setType("programacion");
        //postulantjob
        postulantJob.setId(1L);
        postulantJob.setPostulant(postulant);
        postulantJob.setJobOffer(jobOffer);
        postulantJob.setAceppt(true);
    }

    @Given("el empleador desee conocer información extra de los postulantes de su anuncio")
    public void elEmpleadorDeseeConocerInformaciónExtraDeLosPostulantesDeSuAnuncio() {
        when(jobOfferRepository.save(Mockito.any(JobOffer.class))).thenReturn(new JobOffer());
        when(jobOfferRepository.findById(1L)).thenReturn(OPTIONAL_JOB_OFFER);
    }

    @When("elija alguno de los postulantes")
    public void elijaAlgunoDeLosPostulantes() {
        when(postulantRepository.save(Mockito.any(Postulant.class))).thenReturn(new Postulant());
        when(postulantRepository.findById(1L)).thenReturn(OPTIONAL_POSTULANT);

    }

    @Then("se mostrará el perfil del postulante seleccionado.")
    public void seMostraráElPerfilDelPostulanteSeleccionado() {

    }

    @Given("el empleador visualiza y se interesa en el perfil de un postulante")
    public void elEmpleadorVisualizaYSeInteresaEnElPerfilDeUnPostulante() {
        when(jobOfferRepository.save(Mockito.any(JobOffer.class))).thenReturn(new JobOffer());
        when(jobOfferRepository.findById(1L)).thenReturn(OPTIONAL_JOB_OFFER);
        when(postulantRepository.save(Mockito.any(Postulant.class))).thenReturn(new Postulant());
        when(postulantRepository.findById(1L)).thenReturn(OPTIONAL_POSTULANT);

    }

    @Then("se guardará el perfil del postulante en cuestión.")
    public void seGuardaráElPerfilDelPostulanteEnCuestión() {
    }

    @Given("el empleador desea ver los perfiles de los postulantes que ha guardado")
    public void elEmpleadorDeseaVerLosPerfilesDeLosPostulantesQueHaGuardado() {
    }

    @When("le dé click al apartado de postulantes guardados")
    public void leDéClickAlApartadoDePostulantesGuardados() {
    }


    @Then("se le mostrará una lista de los perfiles que guardo previamente.")
    public void seLeMostraráUnaListaDeLosPerfilesQueGuardoPreviamente() {
    }
}
