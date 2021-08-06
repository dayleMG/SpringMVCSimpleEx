package simpleSpringMVC.itemservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simpleSpringMVC.itemservice.domain.item.Item;
import simpleSpringMVC.itemservice.domain.repo.ItemRepo;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepo itemRepo;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepo.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepo.findById(itemId);
        model.addAttribute( "item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepo.save(item);

        model.addAttribute("item", item);

        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepo.save(item);
        return "/basic/item";
    }

    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        // Item -> item
        itemRepo.save(item);
        return "/basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepo.save(item);
        return "/basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepo.save(new Item("itemA", 1000, 100));
        itemRepo.save(new Item("itemA", 2000, 200));
    }
}
