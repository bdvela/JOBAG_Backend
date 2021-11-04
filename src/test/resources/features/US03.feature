Feature: Mostrar video de presentación de la empresa
  Scenario: Como postulante quiero poder visualizar un video de presentación de la empresa para poder conocer el rubro y otros datos importantes antes de postular.
    Given que el postulante se encuentre en el anuncio de trabajo
    When le de click al apartado de empresa
    Then se mostrará la información básica junto con el enlace de un video de presentación.

    Given que el postulante se encuentra en el apartado de empresa
    When le de click al enlace del video
    Then se le redireccionará al video de presentación subido a Youtube.

    Given que el empleador se encuentre creando su perfil de empresa
    When haya agregado toda su información requerida
    Then le dará la opción de agregar un enlace a un video de presentación de la empresa.