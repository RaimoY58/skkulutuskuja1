package com.buutcamp.main;

import com.buutcamp.dao.KulutusDao;
import com.buutcamp.other.KulutusLukema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FrontPage {

    @Autowired

    private KulutusDao kulutusDao;

    @GetMapping("/")
    public String frontPageGet(Model model){

       kulutusDao.saveKulutusLukemat();
        model.addAttribute("testVar", "Use the buttons for action" );


        return "front-page";
    }
    @PostMapping("/saveConsumptions")
    public String saveConsumptions( Model model) {

        kulutusDao.saveKulutusLukemat();

        return "redirect:/";
    }


    @PostMapping("/printElectricityConsumption")
    public String printElectricityConsumption( Model model) {
        List<KulutusLukema> kulutukset = kulutusDao.getKulutusLukemat();

        model.addAttribute("kulutukset", kulutukset);
        model.addAttribute("kulutus", new KulutusLukema());

        return "front-page";
    }
    @PostMapping("/printOneDayConsumption")
    public String printOneDayConsumption(@RequestParam("printOneDay")String day, Model model) {

            model.addAttribute("dayConsumption", kulutusDao.getYksiKulutuslukema(day));
            return "front-page";
    }

}
