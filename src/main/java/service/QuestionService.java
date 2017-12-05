package service;

import model.Answer;
import model.Question;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CoT on 12/2/17.
 */
@Transactional
@Service
public class QuestionService {

    @Autowired
    private SessionFactory sessionFactory;

    public int addQuestion(Question question){

        //as when we post a question in json format: id of answer is not available
        for (Answer answer: question.getAnswers()) {
            answer.setQuestion(question);
        }

        int id = (Integer) sessionFactory.getCurrentSession().save(question);

        return id;
    }

    public void updateQuestion(Question question){
        sessionFactory.getCurrentSession().saveOrUpdate(question);
    }

    public Question getQuestion(int id){
        return (Question) sessionFactory.getCurrentSession().get(Question.class, id);
    }

    public List<Question> getAllQuestions(){
        return sessionFactory.getCurrentSession().createQuery("from Question").list();
    }

    public List<Question> searchQuestions(String keyword){
        String hql = "from Question as q inner join q.answers as a " +
                "where q.content like :question or a.content like:answer";

        Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("question", "%"+keyword+"%");
        query.setString("answer", "%"+keyword+"%");

        return query.list();

    }

}
