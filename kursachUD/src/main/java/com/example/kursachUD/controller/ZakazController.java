package com.example.kursachUD.controller;


import com.example.kursachUD.model.Sotrudnik;
import com.example.kursachUD.model.Tovar;
import com.example.kursachUD.model.Usr;
import com.example.kursachUD.model.Zakaz;
import com.example.kursachUD.repo.SotrudnikRepo;
import com.example.kursachUD.repo.TovarRepo;
import com.example.kursachUD.repo.UsrRepo;
import com.example.kursachUD.repo.ZakazRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
public class ZakazController {
    @Autowired
    private ZakazRepo zakazRepo;
    @Autowired
    private UsrRepo userRepo;
    @Autowired
    private SotrudnikRepo sotrudnikRepo;
    @Autowired
    private TovarRepo tovarRepo;

    @GetMapping("/zakaz")
    public String zakaz(Map<String,Object> model) {
        Iterable<Zakaz> zakazs =  zakazRepo.findAll(); //Поиск всех пользователей в бд
        model.put("zakazs", zakazs);               //Вывод всех пользователей на страничку в списке
        return "zakaz";
    }

    @PostMapping("addZakaz")
    public String addZakaz(@RequestParam Integer userId,@RequestParam String zakazName, @RequestParam Integer sotrId,  @RequestParam Integer tovarId, Map<String,Object> model){
        Zakaz zakaz = new Zakaz(zakazName);
        try {
            Usr user = userRepo.findById(userId).get();
            Sotrudnik sotrudnik = sotrudnikRepo.findById(sotrId).get();
            Tovar tovar = tovarRepo.findById(tovarId).get();
            sotrudnik.getZakazs().add(zakaz);
            user.getZakazs().add(zakaz);
            tovar.getZakazs().add(zakaz);

            
            zakazRepo.save(zakaz);
            tovarRepo.save(tovar);
            userRepo.save(user);
            sotrudnikRepo.save(sotrudnik);

        }catch (Exception e) {

        }

        Iterable<Zakaz> zakazs =  zakazRepo.findAll(); //Поиск всех пользователей в
        model.put("zakazs", zakazs);               //Вывод всех пользователей на страничку в списке


        return "zakaz";
    }

    @PostMapping("deleteOrder")
    public String deleteZakaz(@RequestParam Integer zakazId,Map<String,Object> model){
        Zakaz zakaz = new Zakaz(zakazId);
        zakazRepo.delete(zakaz);
        Iterable<Zakaz> zakazs =  zakazRepo.findAll(); //Поиск всех пользователей в бд
        model.put("zakazs", zakazs);               //Вывод всех пользователей на страничку в списке
        return "zakaz";
    }

//    @PostMapping("updateOrder")
//    public String updateSotr(@RequestParam Integer orderId,@RequestParam Integer sotrId, @RequestParam Integer userId,Map<String,Object> model){
//        Order order = null;
//        try {
//            order = orderRepo.findById(orderId).get();
//        }catch(Exception e)
//        {
//
//        }
//        Iterable<Order> orders =  orderRepo.findAll(); //Поиск всех пользователей в бд
//        model.put("orders", orders);               //Вывод всех пользователей на страничку в списке
//        return "sotr";
//    }
}
