package com.med.voll.api.domain.consulta.validacoes;

import com.med.voll.api.domain.ValidacaoException;
import com.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.voll.api.domain.medico.MedicoRepository;
import com.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idPaciente() == null){
            return;
        }

        var pacienteEstaAtivo = repository.findAtivoByid(dados.idMedico());
        if (!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico");
        }
    }
}
