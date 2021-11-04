Feature: Mostrar video de explicación del puesto de trabajo.
  Scenario: El postulante visualiza la publicación del video en el anuncio de trabajo
    Given que el postulante se encuentre en buscando ofertas de trabajo
    When le de click a un anuncio de trabajo
    Then se le mostrará la información del puesto de trabajo junto a un enlace de un video de explicación.

  Scenario: El postulante visualiza el video
    Given que el postulante se encuentre en el anuncio de trabajo
    When le de click al enlace del video de explicación
    Then se le redireccionará al video donde se explica el puesto de trabajo con sus obligaciones y exigencias respectivas.

  Scenario: El empleador adjunta el link del video
    Given que el empleador se encuentre creando un anuncio de trabajo
    When termine de agregar la información del puesto de trabajo tendrá la opción de agregar el enlace al video de explicación
    Then solo tendrá que adjuntar el link del video