package com.example.kursachUD.controller;

import com.example.kursachUD.model.Harakteristiki;
import com.example.kursachUD.model.Tovar;
import com.example.kursachUD.repo.HaraktRepo;
import com.example.kursachUD.repo.TovarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TovarController {
    @Autowired
    private TovarRepo tovarRepo;
    @Autowired
    private HaraktRepo haraktRepo;


    @GetMapping("/tovar")
    public String tovar(Map<String,Object> model){
        Iterable<Tovar> tovars =  tovarRepo.findAll(); //Поиск всех пользователей в бд
        model.put("tovars", tovars);               //Вывод всех пользователей на страничку в списке
        return "tovar";
    }

    @PostMapping("addTovar")
    public String addTovar(@RequestParam String tovarName, @RequestParam String tovarPrice,@RequestParam Integer harakteristikiId, Map<String,Object> model){
        Tovar tovar = new Tovar(tovarName, tovarPrice);
        Harakteristiki harakteristiki = haraktRepo.findById(harakteristikiId).get();
        harakteristiki.getTovars().add(tovar);
        tovarRepo.save(tovar);
        haraktRepo.save(harakteristiki);
        Iterable<Tovar> tovars =  tovarRepo.findAll(); //Кусок необходим для обновления список с добавленными данными
        model.put("tovars", tovars);
        return "tovar";
    }

    @PostMapping("deleteTovar")
    public String deleteTovar(@RequestParam Integer tovarId,Map<String,Object> model){
        Tovar tovar = new Tovar(tovarId);
        tovarRepo.delete(tovar);
        Iterable<Tovar> tovars =  tovarRepo.findAll(); //Кусок необходим для обновления список с добавленными данными
        model.put("tovars", tovars);
        return "tovar";
    }

    @PostMapping("updateTovar")
    public String updateTovar(@RequestParam Integer tovarId, @RequestParam String tovarName, @RequestParam String tovarPrice, Map<String,Object> model) {
        Tovar tovar = null;
        try {
            tovar = tovarRepo.findById(tovarId).get();
            if(!tovarName.equals("")) {
                tovar.setTovarName(tovarName);
            }
            if(!tovarPrice.equals("")) {
                tovar.setTovarPrice(tovarPrice);
            }

           tovarRepo.save(tovar);
        } catch(Exception e) {

        }
        Iterable<Tovar> tovars = tovarRepo.findAll(); //Кусок необходим для обновления список с добавленными данными
        model.put("tovars", tovars);
        return "tovar";
    }
}
