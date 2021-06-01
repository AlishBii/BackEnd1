package com.shop.printshop.controllers;

import com.shop.printshop.OrderNotFoundException;
import com.shop.printshop.models.Shapee;
import com.shop.printshop.repository.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Optional;

@EnableSwagger2
@Controller
public class BlogController {
    @Autowired
    private ShapeRepository orderRepository;

    public BlogController(ShapeRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Shapee> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String imagelink,
                              @RequestParam String color, @RequestParam String material,
                              @RequestParam String size, @RequestParam String description,
                              @RequestParam String clientContact, Model model){
        Shapee order = new Shapee(title, imagelink, color, material, size, description, clientContact);
        orderRepository.save(order);
        return "redirect:/blog";
    }

    @PutMapping("/blog/add")
    public String blogPostAdd1(@RequestParam String title, @RequestParam String imagelink,
                               @RequestParam String color, @RequestParam String material,
                               @RequestParam String size, @RequestParam String description,
                               @RequestParam String clientContact, Model model){
        Shapee order = new Shapee(title, imagelink, color, material, size, description, clientContact);
        orderRepository.save(order);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Shapee> order = orderRepository.findById(id);
        ArrayList<Shapee> result = new ArrayList<>();
        order.ifPresent(result::add);
        model.addAttribute("order", result);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Shapee> order = orderRepository.findById(id);
        ArrayList<Shapee> result = new ArrayList<>();
        order.ifPresent(result::add);
        model.addAttribute("post", result);
        return "blog-edit";
    }


    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,
                                 @RequestParam String imagelink, @RequestParam String color,
                                 @RequestParam String material,  @RequestParam(value = "size", required = false) String size,
                                 @RequestParam String description, @RequestParam(value = "clientContact", required = false) String clientContact, Model model){
        Shapee order = orderRepository.findById(id).orElseThrow(IllegalStateException::new);
        order.setTitle(title);
        order.setImagelink(imagelink);
        order.setColor(color);
        order.setMaterial(material);
        order.setSize(size);
        order.setDescription(description);
        order.setClientContact(clientContact);
        orderRepository.save(order);
        return "redirect:/blog";
    }


    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Shapee post = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
        orderRepository.delete(post);
        return "redirect:/blog";
    }
}
