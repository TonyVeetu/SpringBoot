package uteevbkru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uteevbkru.domain.Message;
import uteevbkru.repos.MessageRepo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main( Map<String, Object> model) {
        model.put("messages", messageRepo);
        model.put("cut", "Are you here?");
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag,  Map<String, Object> model) {
        Message mess = new Message(text,tag);
        messageRepo.save(mess);
        System.out.println(text+", "+tag);

        Iterable<Message> messages = messageRepo.findAll();
        Iterator<Message> iterator = messages.iterator();
        while(iterator.hasNext()) {
            Message fromDB = iterator.next();
            String tag1 = fromDB.getTag();
            String text1 = fromDB.getText();
            Integer id1 = fromDB.getId();
            System.out.println("текст = " + text1 + ", тег = " + tag1 + ", ад = " + id1);
        }
        model.put("Messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else{
            messages = messageRepo.findAll();
        }
        model.put("Messages", messages);
        return "main";
    }
}