package Project.PetWalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/walks")
@RequiredArgsConstructor
@Slf4j
public class WalkController {

    @GetMapping(path = "")
    public String walk(@RequestParam(name = "userid") int userid,
                       @RequestParam(name = "petid") int petid, Model model) {
        return "walk";
    }

}
