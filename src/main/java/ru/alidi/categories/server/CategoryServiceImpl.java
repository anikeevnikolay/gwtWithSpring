package ru.alidi.categories.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.alidi.categories.client.CategoryService;
import ru.alidi.categories.shared.CategoryDTO;
import ru.alidi.categories.shared.ItemDTO;

import java.util.*;

public class CategoryServiceImpl extends RemoteServiceServlet implements
        CategoryService {

    @Override
    public List<CategoryDTO> list() {
        LinkedList<CategoryDTO> categoryDTOS = new LinkedList<>();
        CategoryDTO cat1 = generateCategory(null, "Продовольственные товары");
        CategoryDTO cat2 = generateCategory(null, "Непродовольственные товары");
        CategoryDTO cat3 = generateCategory(null, "Техника");

        CategoryDTO sub11 = generateCategory(cat1, "Мясо");
        CategoryDTO sub12 = generateCategory(cat1, "Рыба");
        CategoryDTO sub13 = generateCategory(cat1, "Курица");
        CategoryDTO sub14 = generateCategory(cat1, "Ещё какая нибудь херня");

        CategoryDTO sub21 = generateCategory(cat2, "Мыло");
        CategoryDTO sub22 = generateCategory(cat2, "Веревки");

        categoryDTOS.add(cat1);
        categoryDTOS.add(cat2);
        categoryDTOS.add(cat3);

        return categoryDTOS;
//        return categoryRepository
//                .findAllByParentIsNull()
//                .stream()
//                .map(entity -> new CategoryDTO(entity.getName()))
//                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getItems(Long categoryId) {
        List<ItemDTO> items = new ArrayList<>();
        Random random = new Random();
        for (int i = -5; i < random.nextInt(10); i++) {
            ItemDTO newItem = new ItemDTO();
            newItem.setActive(true);
            newItem.setConsistsOf("сложный-пресложный состав");
            newItem.setName("Товар " + i);
            newItem.setCreateDate(new Date());
            newItem.setMetaDescription("описание для поисковиков");
            newItem.setMetaKeywords("купить,недорого");
            newItem.setMetaTitle(newItem.getName() + " meta");
            newItem.setEanCode(random.nextInt() + "10");
            newItem.setImages(new String[]{"http://www.google.com/images/logo.gif",
                    "http://www.google.com/images/logo.gif",
                    "http://www.google.com/images/logo.gif"});
            items.add(newItem);
        }
        return items;
    }

    @Override
    public void saveItem(ItemDTO dto) {

    }

    @Override
    public void saveCategory(CategoryDTO dto) {

    }

    private CategoryDTO generateCategory(CategoryDTO parent, String name) {
        CategoryDTO dto = new CategoryDTO(name);
        if (parent != null) {
            dto.setParent(parent);
            CategoryDTO[] subcategories = parent.getSubcategories();
            subcategories = Arrays.copyOf(subcategories, subcategories.length + 1);
            subcategories[subcategories.length - 1] = dto;
            parent.setSubcategories(subcategories);
        }

        dto.setMetaKeywords("скачать,бесплатно,без регистрации");
        dto.setMetaTitle("meta " + name);
        dto.setMetaDescription("описание для " + name);
        //todo generate other data

        return dto;
    }
}
