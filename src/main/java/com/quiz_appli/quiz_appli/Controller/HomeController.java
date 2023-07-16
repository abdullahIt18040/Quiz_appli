package com.quiz_appli.quiz_appli.Controller;

import com.quiz_appli.quiz_appli.model.QuestionForm;
import com.quiz_appli.quiz_appli.model.Result;
import com.quiz_appli.quiz_appli.repository.ResultRepo;
import com.quiz_appli.quiz_appli.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    Boolean submitted = false;
    @Autowired
    Result result;
    @Autowired
    QuizService quizService;
    @Autowired
    private ResultRepo resultRepo;

    @ModelAttribute("result")
  public Result getResult()
    {
        return  result;
    }

    @GetMapping("/home")
    public String Homepage()
    {
        return "index";
    }


    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
        if(username.equals("")) {
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }
        submitted=false;
        result.setUsername(username);
        QuestionForm questionForm=quizService.getQuestions();
        m.addAttribute("questionForm",questionForm);

        return "quiz.html";
    }

@PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm,Model model){
        if(!submitted)
        {
            result.setTotalCorrect(quizService.getresult(questionForm));
            quizService.savescore(result);
            submitted=true;

        }
        return "result";

    }
    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = quizService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard.html";
    }








}
