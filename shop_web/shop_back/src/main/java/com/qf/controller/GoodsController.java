package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/20 17:42
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Value("${img.server}")
    private String imgPath;

    @Reference
    private IGoodsService goodsService;

    @RequestMapping("/goodslist")
    public String goodsList(Model model){
        List<Goods> goodsList = goodsService.getGoodsList();
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("imgPath",imgPath);

        return "goodslist";
    }

    @RequestMapping("/goodsadd")
    public String goodsAdd(Goods goods , String[] imagsPath){
        String imgPath = "";
        for (String s : imagsPath) {
           if(imgPath.length()>0){
               s =";"+s;
           }
           imgPath += s;
        }
        goods.setGimages(imgPath);

        goodsService.insertGoods(goods);
        return "redirect:../goods/goodslist";
    }

    @RequestMapping("/goodsToUpdate/{id}")
    public String goodsToUpdate(@PathVariable("id") Integer id,Model model){
            Goods goods = goodsService.getGoodsById(id);
            String gImages = goods.getGimages();
            String[] imgsPath = gImages.split(";");

            model.addAttribute("goods",goods);
            model.addAttribute("imgPath",imgPath);
            model.addAttribute("imgsPath",imgsPath);

        return "goodstoupdate";
    }

    @RequestMapping("/goodsupdate")
    public String goodsUpdate(Goods goods,String[] imagsPath){
        String imgPath = "";
        for (String s : imagsPath) {
            if(imgPath.length()>0){
                s =";"+s;
            }
            imgPath += s;
        }
        goods.setGimages(imgPath);
        goodsService.updateGoods(goods);
        return "redirect:/goods/goodslist";
    }

    @RequestMapping("/deletGoods/{id}")
    public String deleteGoods(@PathVariable("id") Integer id){
        goodsService.deleteById(id);
        return "redirect:../goodslist";
    }
}
