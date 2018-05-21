package ru.alidi.categories.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import ru.alidi.categories.shared.CategoryDTO;
import ru.alidi.categories.shared.ItemDTO;

import java.util.List;

public class CategoriesTree implements EntryPoint {
  private final CategoryServiceAsync categoryService = GWT.create(CategoryService.class);

  private VerticalPanel itemListPanel = new VerticalPanel();
  private VerticalPanel itemPanel = new VerticalPanel();

  // meta info start
  private VerticalPanel categoryInfo = new VerticalPanel();
  private TextBox metaTitle = new TextBox();
  private Label metaTitleLabel = new Label("meta title");
  private TextBox metaDescription = new TextBox();
  private Label metaDescriptionLabel = new Label("meta description");
  private TextBox metaKeywords = new TextBox();
  private Label metaKeywordsLabel = new Label("meta keywords");
  private Button saveMetaInfoButton = new Button();
  private Label categorySaveResponseLabel = new Label();
  private Label itemSaveResponseLabel = new Label();

  private ListDataProvider<ItemDTO> dataProvider;
  private CategoryDTO selectedCategory;

  // meta info end

  public void onModuleLoad() {
    TreeItem root = new TreeItem();

    categoryService.list(new AsyncCallback<List<CategoryDTO>>() {
      @Override
      public void onFailure(Throwable throwable) {
        for (StackTraceElement e : throwable.getStackTrace())
          root.addTextItem(e.toString());
        root.addTextItem("****************************");
        root.addTextItem(throwable.getMessage());
      }

      @Override
      public void onSuccess(List<CategoryDTO> categoryDTOS) {
        categoryDTOS.forEach(categoryDTO -> addToTree(categoryDTO, root));
      }
    });
    root.setText("Все категории");
    root.addStyleName("tree");

    saveMetaInfoButton.setText("Сохранить");
    saveMetaInfoButton.addStyleName("saveButton");
    saveMetaInfoButton.addClickHandler(clickEvent -> {
      if (selectedCategory == null)
        return;
      selectedCategory.setMetaTitle(metaTitle.getText());
      selectedCategory.setMetaDescription(metaDescription.getText());
      selectedCategory.setMetaKeywords(metaKeywords.getText());
      categorySaveResponseLabel.setText("");
      categoryService.saveCategory(selectedCategory, new AsyncCallback<Void>() {
        @Override
        public void onFailure(Throwable throwable) {
          categorySaveResponseLabel.setText("Ошибка при сохранении");
          categorySaveResponseLabel.addStyleName("fail");
        }

        @Override
        public void onSuccess(Void aVoid) {
          categorySaveResponseLabel.setText("Сохранено успешно");
          categorySaveResponseLabel.addStyleName("success");
        }
      });
    });

    categoryInfo.add(metaTitleLabel);
    categoryInfo.add(metaTitle);
    categoryInfo.add(metaDescriptionLabel);
    categoryInfo.add(metaDescription);
    categoryInfo.add(metaKeywordsLabel);
    categoryInfo.add(metaKeywords);
    categoryInfo.add(saveMetaInfoButton);
    categoryInfo.add(categorySaveResponseLabel);

    Tree tree = new Tree();
    tree.addItem(root);
    tree.addSelectionHandler(selectionEvent -> {
      CategoryTreeItem selectedItem = (CategoryTreeItem) selectionEvent.getSelectedItem();
      selectedCategory = selectedItem.getCategory();
      metaTitle.setText(selectedCategory.getMetaTitle());
      metaDescription.setText(selectedCategory.getMetaDescription());
      metaKeywords.setText(selectedCategory.getMetaKeywords());

      itemListPanel.clear();
      itemPanel.clear();
      categoryService.getItems(selectedCategory.getId(), new AsyncCallback<List<ItemDTO>>() {
        @Override
        public void onFailure(Throwable throwable) {
        }

        @Override
        public void onSuccess(List<ItemDTO> itemDTOS) {
          TextBox searchInput = new TextBox();
          Button searchButton = new Button();
          searchButton.setText("Поиск");
          HorizontalPanel searchPanel = new HorizontalPanel();
          searchPanel.add(searchInput);
          searchPanel.add(searchButton);
          searchPanel.addStyleName("searchPanel");
          itemListPanel.add(searchPanel);

          CellTable<ItemDTO> table = new CellTable<>();
          table.addCellPreviewHandler(event -> {
            boolean isClick = "click".equals(event.getNativeEvent().getType());
            if (isClick) {
              setItem(event.getValue());
            }
          });
          TextColumn<ItemDTO> nameColumn = new TextColumn<ItemDTO>() {
            @Override
            public String getValue(ItemDTO dto) {
              return dto.getName();
            }
          };
          TextColumn<ItemDTO> eanColumn = new TextColumn<ItemDTO>() {
            @Override
            public String getValue(ItemDTO dto) {
              return dto.getEanCode();
            }
          };
          TextColumn<ItemDTO> metaTitleColumn = new TextColumn<ItemDTO>() {
            @Override
            public String getValue(ItemDTO dto) {
              return dto.getMetaTitle();
            }
          };
          TextColumn<ItemDTO> metaDescriptionColumn = new TextColumn<ItemDTO>() {
            @Override
            public String getValue(ItemDTO dto) {
              return dto.getMetaDescription();
            }
          };
          table.addColumn(nameColumn, "Наименование");
          table.addColumn(eanColumn, "EAN");
          table.addColumn(metaTitleColumn, "meta title");
          table.addColumn(metaDescriptionColumn, "meta description");
          table.addStyleName("itemList");

          /*ListDataProvider<ItemDTO> */dataProvider = new ListDataProvider<>();
          dataProvider.addDataDisplay(table);
          List<ItemDTO> list = dataProvider.getList();
          itemDTOS.forEach(itemDTO -> list.add(itemDTO));
          itemListPanel.add(table);
        }
      });
    });

    VerticalPanel treePanel = new VerticalPanel();
    treePanel.add(tree);
    treePanel.add(categoryInfo);
    treePanel.addStyleName("treePanel");

    HorizontalPanel mainPanel = new HorizontalPanel();
    mainPanel.add(treePanel);
    mainPanel.add(itemListPanel);
    mainPanel.add(itemPanel);

    RootPanel.get().add(mainPanel);
  }

  private void addToTree(CategoryDTO dto, TreeItem tree) {
    CategoryTreeItem newNode = new CategoryTreeItem(dto);
    if (dto.getSubcategories().length != 0) {
      for (CategoryDTO categoryDTO : dto.getSubcategories()) {
        addToTree(categoryDTO, newNode);
      }
    }
    tree.addItem(newNode);
  }

  private void setItem(ItemDTO dto) {
    itemPanel.clear();
    HorizontalPanel namePanel = new HorizontalPanel();
    Label nameLabel = new Label("Наименование");
    TextBox name = new TextBox();
    name.setText(dto.getName());
    namePanel.add(nameLabel);
    namePanel.add(name);

    HorizontalPanel eanPanel = new HorizontalPanel();
    Label eanLabel = new Label("EAN");
    TextBox ean = new TextBox();
    ean.setText(dto.getEanCode());
    eanPanel.add(eanLabel);
    eanPanel.add(ean);

    HorizontalPanel descriptionPanel = new HorizontalPanel();
    Label consistsOfLabel = new Label("Состав");
    TextBox consistsOf = new TextBox();
    consistsOf.setText(dto.getConsistsOf());
    descriptionPanel.add(consistsOfLabel);
    descriptionPanel.add(consistsOf);

    //todo сгенерировать остальные поля товара

    Button saveItemButton = new Button();
    saveItemButton.setText("Сохранить");
    saveItemButton.addStyleName("saveButton");
    saveItemButton.addClickHandler(clickEvent -> {
      dto.setName(name.getText());
      dto.setEanCode(ean.getText());
      dto.setConsistsOf(consistsOf.getText());
      itemSaveResponseLabel.setText("");
      categoryService.saveItem(dto, new AsyncCallback<Void>() {
        @Override
        public void onFailure(Throwable throwable) {
          itemSaveResponseLabel.setText("Ошибка при сохранении");
          itemSaveResponseLabel.addStyleName("fail");
        }

        @Override
        public void onSuccess(Void aVoid) {
          itemSaveResponseLabel.setText("Сохранено успешно");
          itemSaveResponseLabel.addStyleName("success");
          dataProvider.refresh();
        }
      });
    });

    itemPanel.add(namePanel);
    itemPanel.add(eanPanel);
    itemPanel.add(descriptionPanel);

    //todo остальные поля товара
    itemPanel.add(saveItemButton);
    itemPanel.add(itemSaveResponseLabel);

    itemPanel.addStyleName("item");

    HorizontalPanel images = new HorizontalPanel();
    for (String url : dto.getImages()) {
      final Image image = new Image();
      image.addErrorHandler(event -> itemSaveResponseLabel.setText("An error occurred while loading image."));
      image.setUrl(url);
      image.setVisibleRect(0, 0, 100, 100);
      image.addClickHandler(new ClickHandler() {
        private boolean hidden = true;
        @Override
        public void onClick(ClickEvent event) {
          if (hidden) {
            image.setUrl(url);
          } else {
            image.setVisibleRect(0, 0, 100, 100);
          }
          hidden = !hidden;
        }
      });
      images.add(image);
    }

    itemPanel.add(images);
  }

  private static class CategoryTreeItem extends TreeItem {

    private CategoryDTO category;

    public CategoryTreeItem(CategoryDTO category) {
      super();
      this.category = category;
      setText(category.getName());
    }

    public CategoryDTO getCategory() {
      return category;
    }
  }
}
