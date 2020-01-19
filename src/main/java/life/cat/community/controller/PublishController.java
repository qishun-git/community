package life.cat.community.controller;

import life.cat.community.mapper.QuestionMapper;
import life.cat.community.model.Question;
import life.cat.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error", "You must have a title!");
            return "publish";
        }
        if (content == null || content.equals("")) {
            model.addAttribute("error", "You must have content!");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "You must have a tag!");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "User not logged in.");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTag(tag);
        question.setAuthor(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
