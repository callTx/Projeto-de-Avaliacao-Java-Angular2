package web.api.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.api.escola.entity.Aluno;
import web.api.escola.service.AlunoFirebaseService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class AlunoController {

    @Autowired
    private AlunoFirebaseService alunoFirebaseService;

    @GetMapping("/alunos/{nome}")
    public Aluno getAluno(@PathVariable String nome) throws ExecutionException, InterruptedException {
        return alunoFirebaseService.getAlunoPeloNome(nome);
    }

    @GetMapping("/alunos")
    public List<Aluno> getATodosAlunos() throws ExecutionException, InterruptedException {
        return alunoFirebaseService.getAlunos();
    }

    @PostMapping("/alunos")
    public String createAluno(@RequestBody Aluno aluno) throws ExecutionException, InterruptedException {
        return alunoFirebaseService.createAluno(aluno);
    }

    @PutMapping("/alunos")
    public String updateAluno(@RequestBody Aluno aluno) throws ExecutionException, InterruptedException {
        return alunoFirebaseService.updateAluno(aluno);
    }

    @DeleteMapping("/alunos/{nome}")
    public String deleteAluno(@PathVariable String nome) throws ExecutionException, InterruptedException {
        return alunoFirebaseService.deleteAluno(nome);
    }
}
