package simpleSpringMVC.itemservice.domain.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import simpleSpringMVC.itemservice.domain.item.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepoTest {

    ItemRepo itemRepo = new ItemRepo();

    @AfterEach
    void afterEach() {
        itemRepo.clear();
    }

    @Test
    void save() {
        //given
        Item item = new Item("노트북",100,5);


        //when
        Item savedItem = itemRepo.save(item);

        //then
        Item findItem = itemRepo.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("책", 10, 3);
        Item item2 = new Item("태블릿", 10, 3);

        itemRepo.save(item1);
        itemRepo.save(item2);

        //when
        List<Item> result = itemRepo.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }

    @Test
    void update() {
        //given
        Item preItem = new Item("책", 100, 3);
        Item savedItem = itemRepo.save(preItem);
        Long itemId = savedItem.getId();

        //when

        Item updateItem = itemRepo.findById(itemId);
        updateItem.setPrice(150);
        updateItem.setQuantity(5);
        updateItem.setItemName("공책");
        itemRepo.update(itemId, updateItem);

        //then
        Item findItem = itemRepo.findById(itemId);
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());

    }

}