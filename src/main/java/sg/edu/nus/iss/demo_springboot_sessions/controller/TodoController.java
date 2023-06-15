package sg.edu.nus.iss.demo_springboot_sessions.controller;

import java.util.ArrayList;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
  @PostMapping("/add")
  public String add(@RequestParam("todo") String todo, HttpSession sessionObj) {
    List<String> todoList = (List<String>) sessionObj.getAttribute("todoList");
    if (todoList == null) {
      todoList = new ArrayList<String>();
      sessionObj.setAttribute("todoList", todoList);
    }
    todoList.add(todo);
    return "redirect:/list";
  }

  @GetMapping("/list")
  public String list(Model model, HttpSession sessionObj) {
    List<String> todoList = (List<String>) sessionObj.getAttribute("todoList");

    if (todoList == null) {
      todoList = new ArrayList<String>();
      sessionObj.setAttribute("todoList", todoList);
    }
    
    model.addAttribute("todoList", todoList);
    
    return "todos";
  }

  @PostMapping("/clear-session")
  public String destroySession(HttpSession sessionObj) {
    sessionObj.removeAttribute("todoList");
    
    return "redirect:/list";
  }
}
