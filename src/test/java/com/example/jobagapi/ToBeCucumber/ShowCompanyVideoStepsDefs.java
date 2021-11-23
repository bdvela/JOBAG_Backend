package com.example.jobagapi.ToBeCucumber;

import com.example.jobagapi.domain.model.Company;
import com.example.jobagapi.domain.model.Employeer;
import com.example.jobagapi.domain.repository.CompanyRepository;
import com.example.jobagapi.domain.repository.EmployeerRepository;
import com.example.jobagapi.domain.repository.SectorRepository;
import com.example.jobagapi.service.CompanyServiceImpl;
import com.example.jobagapi.service.EmployeerServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.mockito.Mockito.when;

public class ShowCompanyVideoStepsDefs {

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private EmployeerRepository employeerRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private CompanyRepository companyRepository;


    private static final Company company = new Company();

    private static final Optional<Company> OPTIONAL_COMPANY= Optional.of(company);

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        company.setId(1L);
        company.setDescription("Empresa dedicada al software");
        company.setUrlVideo("http://youtube.com");

    }

    @Given("que el postulante se encuentre en el anuncio de trabajo")
    public void queElPostulanteSeEncuentreEnElAnuncioDeTrabajo() {

    }

    @When("le de click al apartado de empresa")
    public void leDeClickAlApartadoDeEmpresa() {
        when(companyRepository.findById(1L)).thenReturn(OPTIONAL_COMPANY);

    }

    @Then("se mostrará la información básica junto con el enlace de un video de presentación.")
    public void seMostraráLaInformaciónBásicaJuntoConElEnlaceDeUnVideoDePresentación() {

        Company company=companyService.getCompanyById(1L);
        Assert.assertEquals(company.getUrlVideo(),"http://youtube.com");
    }

    @Given("que el postulante se encuentra en el apartado de empresa")
    public void queElPostulanteSeEncuentraEnElApartadoDeEmpresa() {
    }

    @When("le de click al enlace del video")
    public void leDeClickAlEnlaceDelVideo() {
        when(companyRepository.findById(1L)).thenReturn(OPTIONAL_COMPANY);
        Assert.assertEquals(company.getUrlVideo(),"http://youtube.com");
    }

    @Then("se le redireccionará al video de presentación subido a Youtube.")
    public void seLeRedireccionaráAlVideoDePresentaciónSubidoAYoutube() {
    }

    @Given("que el empleador se encuentre creando su perfil de empresa")
    public void queElEmpleadorSeEncuentreCreandoSuPerfilDeEmpresa() {
        when(companyRepository.save(Mockito.any(Company.class))).thenReturn(new Company());

    }

    @When("haya agregado toda su información requerida")
    public void hayaAgregadoTodaSuInformaciónRequerida() {
        Company company = new Company();
        companyService.createCompany(1L,1L,company);
    }

    @Then("le dará la opción de agregar un enlace a un video de presentación de la empresa.")
    public void leDaráLaOpciónDeAgregarUnEnlaceAUnVideoDePresentaciónDeLaEmpresa() {
        when(companyRepository.findById(1L)).thenReturn(OPTIONAL_COMPANY);
        Company company = OPTIONAL_COMPANY.get();
        company.setUrlVideo("http://google.com");
    }
}

