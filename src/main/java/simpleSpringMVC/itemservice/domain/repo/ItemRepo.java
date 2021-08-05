package simpleSpringMVC.itemservice.domain.repo;

import org.springframework.stereotype.Repository;
import simpleSpringMVC.itemservice.domain.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepo {

    //실제에선 HashMap은 안한다 멀티쓰레드 문제로 인해 ConccurenethashMap
    private static final Map<Long, Item> store = new HashMap<>();
    // 동시에 접근하면 값이 꼬일수 있으므로 automic long사용해함
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    public void clear() {
        store.clear();
    }


}
