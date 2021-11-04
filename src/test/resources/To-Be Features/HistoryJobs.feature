Feature: Historial de Jobs
  Scenario: Mostrar el historial de trabajos pasados en el perfil del postulante.
    Given el empleador desee conocer información extra de los postulantes de su anuncio
    When elija alguno de los postulantes
    Then se mostrará el perfil del postulante seleccionado.

  Scenario: El empleador guarda el perfil de un postulante
    Given el empleador visualiza y se interesa en el perfil de un postulante
    When el empleador visualiza y se interesa en el perfil de un postulante
    Then se guardará el perfil del postulante en cuestión.

  Scenario: El empleador visualiza su lista de postulantes guardados
    Given el empleador desea ver los perfiles de los postulantes que ha guardado
    When le dé click al apartado de postulantes guardados
    Then se le mostrará una lista de los perfiles que guardo previamente.