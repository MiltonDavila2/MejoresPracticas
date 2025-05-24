package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.entidades.Investigador;

import java.util.List;

public interface InvestigadorServicio {
    public List<Investigador> ListarInvestigadores();

    public Investigador GuardarInvestigador(Investigador investigador);

    public Investigador EncontrarInvestigadorPorID(Long id);

    public Investigador actualizarInvestigador(Investigador investigador);

    public Investigador buscarInvestigadorPorNombre(String nombre);

    public boolean existeInvestigador(String cedula);

    public void eliminarInvestigador(Long id);

    public List<Experimento> listarExperimentosPorInvestigador(Long idInvestigador);
}
