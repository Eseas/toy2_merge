package com.fastcampus.toy2.service.Product;

import com.fastcampus.toy2.dao.Product.ProductCategoryDao;
import com.fastcampus.toy2.dao.Product.ProductDao;
import com.fastcampus.toy2.dao.Product.ProductKindDao;
import com.fastcampus.toy2.dao.Product.ProductStockDao;
import com.fastcampus.toy2.domain.Product.ProductCategoryDto;
import com.fastcampus.toy2.domain.Product.ProductDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;
import com.fastcampus.toy2.domain.Product.ProductStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductKindDao productKindDao;

    @Autowired
    ProductStockDao productStockDao;

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public int getCount() throws Exception {
        /**
         * 현재 등록된 전체 상품 개수 조회
         */
        return productDao.count();
    }

    @Override
    public int getSaleCount() throws Exception {
        /**
         * 현재 판매중인 전체 상품 개수 조회
         */
        return productDao.salecount();
    }

    @Override
    public int change_product_sale_state(String product_id) throws Exception {
        /**
         * 매개변수 - product_id
         * product 테이블의 product_id로 판매 상태 변경
         */
        return productDao.updateSaleState(product_id);
    }

    @Override
    public List<ProductDto> findProductCategoryPage(List<Integer> categories, int sort, int page, int size) throws Exception {
        /**
         * 매개변수 1. 조회해야 하는 카테고리를 담은 리스트 : categories
         * 매개변수 2. 정렬 기준 : sort
         * 매개변수 3. 현재 페이지 : page
         * 매개변수 4. 페이지 사이즈 : size
         */
        return productDao.findProductCategoryPage(categories, sort, page, size);
    }

    @Override
    public List<Integer> getCategories(int category_id) throws Exception {
        /**
         * 현재 카테고리의 하위 카테고리를 찾아서 반환
         * 매개변수 1. 찾아야 하는 카테고리 id : category_id
         * 진행
         *  1. 카테고리 리스트 초기화 및 초기 카테고리 추가
         *  2. 카테고리 리스트에 매개변수 카테고리 추가
         *  3. 하위 카테고리를 찾는 getLowerCategories 실행
         *  4. 결과값 lowerCategories를 categories에 추가.
         *  5. categories 반환.
         */
        List<Integer> categories = new ArrayList<>();
        categories.add(category_id);

        List<Integer> lowerCategories = getLowerCategories(category_id);
        categories.addAll(lowerCategories);

        return categories;
    }

    private List<Integer> getLowerCategories(int category_id) throws Exception {
        /**
         * 모든 하위 카테고리를 찾아 반환
         * 1. 매개변수 1. 카테고리 id : category_id
         * 진행
         * 1. 매개변수 category_id를 upper_category_id로 갖는 카테고리를 찾아서 저장
         * 2. 하위 카테고리들을 저장할 lowerCategories 선언
         * 3. 하위 카테고리를 순회하며 재귀적으로 그 하위 카테고리 ID를 추가.
         * 4. 모든 하위 카테고리를 반환
         */
        List<ProductCategoryDto> categories = productCategoryDao.selectUpperEquals(category_id);
        List<Integer> lowerCategories = new ArrayList<>();

        for (ProductCategoryDto category : categories) {
            lowerCategories.add(category.getCategory_id());
            List<Integer> resultLower = getLowerCategories(category.getCategory_id());
            lowerCategories.addAll(resultLower);
        }

        return lowerCategories;
    }

    @Override
    public ProductDto getProductById(String product_id) throws Exception {
        /**
         * 매개변수 product_id를 통해 해당 product를 찾아서 반환
         */
        return productDao.selectById(product_id);
    }

    @Override
    public List<ProductKindDto> getProductKindList(String product_id) throws Exception {
        /**
         * 해당 product_id를 사용하는 모든 product_kind를 찾아서 반환
         * 색상이 다른 같은 상품을 찾아서 반환한다.
         */
        return productKindDao.selectListByProductId(product_id);
    }

    // 아직 안 만듦
    @Override
    public List<ProductStockDto> getProductStockList(String style_num) throws Exception {
        /**
         * 해당 style_num의 모든 상품 사이즈들과 재고를 반환
         */
        return productStockDao.selectByProductId(style_num);
    }

    @Override
    public String getcurrentCategory(int category_id) throws Exception {
        /**
         * 해당 카테고리의 상위 카테고리들을 String으로 변환하여 보여주기 위해 만든 메서드.
         * 카테고리 id의 최상위 카테고리부터 현재 카테고리까지 '카테고리 > 카테고리' 형식으로 만들어 반환함.
         */
        ProductCategoryDto productCategoryDto = productCategoryDao.selectById(category_id);
        String currentCategory = productCategoryDto.getCategory();
        Integer upper_category_id = productCategoryDto.getUpper_category_id();

        while(upper_category_id != null){
            currentCategory =  productCategoryDao.selectById(upper_category_id).getCategory() + " >" +currentCategory;
            upper_category_id = productCategoryDao.selectById(upper_category_id).getUpper_category_id();
        }
        return currentCategory;
    }

    @Override
    public HashMap<Integer, String> getSelectCategory(int category_id) throws Exception {
        /**
         *
         */
        HashMap<Integer, String> selectCategory = new HashMap<>();

        ProductCategoryDto productCategoryDto = productCategoryDao.selectById(category_id);
        selectCategory.put(productCategoryDao.selectById(productCategoryDto.getUpper_category_id()).getCategory_id()
                , "전체");

        List<ProductCategoryDto> productCategoryDtoList = getUpperEqualsCategory(category_id);

        for(ProductCategoryDto productCategoryDto1 : productCategoryDtoList){
            selectCategory.put(productCategoryDto1.getCategory_id(),
                    productCategoryDto1.getCategory());
        }

        return selectCategory;
    }

    @Override
    public List<ProductCategoryDto> getUpperEqualsCategory(int category_id) throws Exception {
        int upper_category_id = productCategoryDao.selectById(category_id).getUpper_category_id();
        return productCategoryDao.selectUpperEquals(upper_category_id);
    }

    @Override
    public boolean ChangeProductSaleCount(String style_num, char reason) throws Exception {
        ProductKindDto productKindDto = productKindDao.selectById(style_num);
        if(productKindDao.updateSaleCount(style_num, reason) == 1 &&
                productDao.updateSaleCount(productKindDto.getProduct_id(), reason) == 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public int findProductCategoryPageCount(List<Integer> categories) throws Exception {
        return productDao.findProductCategoryPageCount(categories);
    }
}
