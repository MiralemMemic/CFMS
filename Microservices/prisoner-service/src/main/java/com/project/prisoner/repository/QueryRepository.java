package com.project.prisoner.repository;

import com.project.prisoner.model.Prisoner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Prisoner> findTheMostEvil(){
        return entityManager.createQuery("SELECT p from Prisoner p order by p.lengthOfSentence desc ",Prisoner.class).setMaxResults(3).getResultList();
    }

    public List mostCommonCriminalAct(){
        return entityManager.createQuery("SELECT p.offense, count(p.offense) as brojPonavljanja from Prisoner p GROUP BY p.offense ORDER BY brojPonavljanja desc ").setMaxResults(1).getResultList();
    }

    public List howManyCellsAreNotEmpty(){
        return entityManager.createQuery("SELECT p.currentCell, count(p.currentCell) from  Prisoner p GROUP BY p.currentCell").getResultList();
    }

    public List comingOutFirst(){
        return entityManager.createQuery("SELECT p from Prisoner p where p.lengthOfSentence=(select min(lengthOfSentence) from Prisoner )").getResultList();
    }

    public List whereIsTheWorstOfThemAll(){
        return entityManager.createQuery("SELECT p.firstName,p.lastName,p.currentCell from Prisoner p where p.lengthOfSentence=(select max(lengthOfSentence) from Prisoner)").getResultList();
    }

    public List prisonersSittingInCell(String cellId){
        return entityManager.createQuery("SELECT p from Prisoner p where p.currentCell="+cellId).getResultList();
    }


}
