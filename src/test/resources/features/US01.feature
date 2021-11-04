Feature: Mostrar video de explicación del puesto de trabajo.
  Scenario: Como postulante quiero que los anuncios de trabajo tengan un video de explicación del puesto de trabajo para tener información detallada de las obligaciones y exigencias del puesto de trabajo.
    Given que el postulante se encuentre en buscando ofertas de trabajo
    When le de click a un anuncio de trabajo
    Then se le mostrará la información del puesto de trabajo junto a un enlace de un video de explicación.

    Given que el postulante se encuentre en el anuncio de trabajo
    When le de click al enlace del video de explicación
    Then se le redireccionará al video donde se explica el puesto de trabajo con sus obligaciones y exigencias respectivas.

    Given que el empleador se encuentre creando un anuncio de trabajo
    When termine de agregar la información del puesto de trabajo tendrá la opción de agregar el enlace al video de explicación
    Then solo tendrá que adjuntar el link del video