package web.api.escola.service;


import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.WriteResult;
import com.google.protobuf.Api;
import org.springframework.stereotype.Service;
import web.api.escola.entity.Aluno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AlunoFirebaseService {

    private static final String COLLETION_NAME = "Alunos";

    public String createAluno(Aluno aluno) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> colletionApiFuture =  dbFirestore.collection(COLLETION_NAME).document(aluno.getNome()).set(aluno);


        return colletionApiFuture.get().getUpdateTime().toString();
    }

    public Aluno getAlunoPeloNome(String nome) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =  dbFirestore.collection(COLLETION_NAME).document(nome);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Aluno aluno = null;
        if(document.exists()){
            aluno = document.toObject(Aluno.class);
            return aluno;
        }else{
            return null;
        }
    }

    public List<Aluno> getAlunos() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference =  dbFirestore.collection(COLLETION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Aluno> alunoList = new ArrayList<>();
        Aluno aluno = null;

        while(iterator.hasNext()){
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            aluno = document.toObject(Aluno.class);
            alunoList.add(aluno);
        }

        return alunoList;
    }

    public String updateAluno(Aluno aluno) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> colletionApiFuture =  dbFirestore.collection(COLLETION_NAME).document(aluno.getNome()).set(aluno);


        return colletionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteAluno(String nome) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> colletionApiFuture =  dbFirestore.collection(COLLETION_NAME).document(nome).delete();


        return "Documento com Aluno de ID "+nome+" foi deletado com sucesso!";
    }
}
