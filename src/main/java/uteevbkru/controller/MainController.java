package uteevbkru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uteevbkru.domain.Message;
import uteevbkru.domain.User;
import uteevbkru.repos.MessageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Map model) {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else{
            messages = messageRepo.findAll();
        }
        model.put("Messages", messages);
        model.put("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model,
                      @RequestParam("file")MultipartFile file) throws IOException {
        Message mess = new Message(user,text,tag);
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile+ "."+file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            mess.setFilename(resultFilename);
        }

        messageRepo.save(mess);

        Iterable<Message> messages = messageRepo.findAll();
        Iterator<Message> iterator = messages.iterator();
        while(iterator.hasNext()) {
            Message fromDB = iterator.next();
            String tag1 = fromDB.getTag();
            String text1 = fromDB.getText();
            Integer id1 = fromDB.getId();
            System.out.println("текст = " + text1 + ", тег = " + tag1 + ", ад = " + id1 + ", userName = " + user.getUsername()  + ", userID = " + user.getId());
        }
        model.put("Messages", messages);
        return "main";
    }
}