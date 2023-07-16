package com.quiz_appli.quiz_appli.service;


import com.quiz_appli.quiz_appli.model.Question;
import com.quiz_appli.quiz_appli.model.QuestionForm;
import com.quiz_appli.quiz_appli.model.Result;
import com.quiz_appli.quiz_appli.repository.QuestionRepoi;
import com.quiz_appli.quiz_appli.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class QuizService {

 @Autowired
    QuestionRepoi questionRepoi;
@Autowired
    QuestionForm questionForm;
@Autowired
    Result result;
@Autowired
    ResultRepo resultRepo;
@Autowired
    Question question;

public QuestionForm getQuestions(){
    List<Question> allQues = questionRepoi.findAll();
    List<Question>qList=new ArrayList<>();
    Random random=new Random();
    for(int i=0;i<5;i++)
    {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
    }
    questionForm.setQuestions(qList);
    return questionForm;


}

public  int getresult(QuestionForm questionForm)
{
    int correct=0;
    for(Question q :questionForm.getQuestions()){
        if(q.getAns()==q.getChose())
        {
            correct++;
        }
    }
    return  correct;
}
    public  void savescore( Result result)
    {
        Result savresult=new Result();
        savresult.setUsername(result.getUsername());
        savresult.setTotalCorrect(result.getTotalCorrect());
        resultRepo.save(savresult);
    }
    public List<Result> getTopScore()
    {
        List<Result> sList = resultRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
        return sList;
    }








	


}
