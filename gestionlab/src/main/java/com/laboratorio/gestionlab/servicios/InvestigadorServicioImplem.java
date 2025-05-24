package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.entidades.Investigador;
import com.laboratorio.gestionlab.repositorios.ExperimentoRepositorio;
import com.laboratorio.gestionlab.repositorios.InvestigadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestigadorServicioImplem implements InvestigadorServicio{

    @Autowired
    private InvestigadorRepositorio repositorio;

    @Autowired
    private ExperimentoRepositorio experimentoRepositorio;

    @Override
    public List<Investigador> ListarInvestigadores() {
        return repositorio.findAll();
    }

    @Override
    public Investigador GuardarInvestigador(Investigador investigador) {
        return repositorio.save(investigador);
    }

    @Override
    public Investigador EncontrarInvestigadorPorID(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Investigador actualizarInvestigador(Investigador investigador) {
        return repositorio.save(investigador);
    }

    @Override
    public Investigador buscarInvestigadorPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public boolean existeInvestigador(String cedula) {
        return repositorio.existsByCedula(cedula);
    }

    @Override
    public void eliminarInvestigador(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Experimento> listarExperimentosPorInvestigador(Long idInvestigador) {
        if(EncontrarInvestigadorPorID(idInvestigador)==null){
            return null;
        }

        return experimentoRepositorio.findByInvestigador(EncontrarInvestigadorPorID(idInvestigador));
    }
}
