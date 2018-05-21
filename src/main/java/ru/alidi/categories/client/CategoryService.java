package ru.alidi.categories.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.alidi.categories.shared.CategoryDTO;
import ru.alidi.categories.shared.ItemDTO;

import java.util.List;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("categories")
public interface CategoryService extends RemoteService {

    List<CategoryDTO> list();

    List<ItemDTO> getItems(Long categoryId);

    void saveItem(ItemDTO dto);

    void saveCategory(CategoryDTO dto);
}
