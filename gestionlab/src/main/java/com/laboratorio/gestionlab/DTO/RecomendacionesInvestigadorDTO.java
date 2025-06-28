package com.laboratorio.gestionlab.DTO;

public class RecomendacionesInvestigadorDTO {

    private String texto;
    private String nombreInvestigador;
    private String cedulaInvestigador;

    public RecomendacionesInvestigadorDTO(String texto, String nombreInvestigador, String cedulaInvestigador) {
        this.texto = texto;
        this.nombreInvestigador = nombreInvestigador;
        this.cedulaInvestigador = cedulaInvestigador;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreInvestigador() {
        return nombreInvestigador;
    }

    public void setNombreInvestigador(String nombreInvestigador) {
        this.nombreInvestigador = nombreInvestigador;
    }

    public String getCedulaInvestigador() {
        return cedulaInvestigador;
    }

    public void setCedulaInvestigador(String cedulaInvestigador) {
        this.cedulaInvestigador = cedulaInvestigador;
    }
}
